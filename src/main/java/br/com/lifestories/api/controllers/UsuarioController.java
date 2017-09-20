package br.com.lifestories.api.controllers;

import br.com.lifestories.api.utils.StringUtils;
import br.com.lifestories.model.criteria.UsuarioCriteria;
import br.com.lifestories.model.entity.Administrador;
import br.com.lifestories.model.entity.Estudante;
import br.com.lifestories.model.entity.Idoso;
import br.com.lifestories.model.entity.InstituicaoLongaPermanencia;
import br.com.lifestories.model.entity.Usuario;
import br.com.lifestories.model.service.AdministradorService;
import br.com.lifestories.model.service.EstudanteService;
import br.com.lifestories.model.service.IdosoService;
import br.com.lifestories.model.service.InstituicaoLongaPermanenciaService;
import br.com.lifestories.model.service.UsuarioService;
import io.swagger.annotations.Api;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author Joao Pedro
 */
@RestController
@RequestMapping(value = "/usuario")
@Api(value = "lifestories", description = "Classe correspondente aos métodos de acesso relacionados aos usuários.")
public class UsuarioController {

    AdministradorService admService = new AdministradorService();
    EstudanteService estService = new EstudanteService();
    IdosoService idoService = new IdosoService();
    InstituicaoLongaPermanenciaService insService = new InstituicaoLongaPermanenciaService();
    UsuarioService usuarioService = new UsuarioService();

    @GetMapping(value = "/{idUsuario}")
    public ResponseEntity readById(@PathVariable Long idUsuario) {
        try {
            return ResponseEntity.ok(usuarioService.readById(idUsuario));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }

    @GetMapping(value = "/validar")
    public ResponseEntity validarUsuario(@RequestParam(value = "hashUsuario", required = true) String hashUsuario) throws Exception {
        try {
            String idUsuario = hashUsuario.substring(32, hashUsuario.length());
            Usuario usuario = usuarioService.readById(Long.parseLong(idUsuario));
            String novoHash = "";
            if (usuario.getTipo().equals("ido")) {
                Idoso idoso = idoService.readById(Long.parseLong(idUsuario));
                novoHash = idoso.getCodigo() + usuario.getSenha();
            } else if (usuario.getTipo().equals("est")) {
                Estudante estudante = estService.readById(Long.parseLong(idUsuario));
                novoHash = estudante.getEmail() + usuario.getSenha();
            } else if (usuario.getTipo().equals("ins")) {
                InstituicaoLongaPermanencia instituicao = insService.readById(Long.parseLong(idUsuario));
                novoHash = instituicao.getEmail() + usuario.getSenha();
            } else if (usuario.getTipo().equals("adm")) {
                Administrador adm = admService.readById(Long.parseLong(idUsuario));
                novoHash = adm.getEmail() + usuario.getSenha();
            }
            novoHash = StringUtils.convertStringToMD5(novoHash);
            hashUsuario = hashUsuario.substring(0, 32);
            if (novoHash.equals(hashUsuario)) {
                return ResponseEntity.ok(usuarioService.readById(Long.parseLong(idUsuario)));
            } else {
                return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).body("Este hash não é equivalente ao usuário informado");
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }

    @GetMapping(value = "/login")
    public ResponseEntity validarLogin(
            @RequestParam(value = "identificador", required = true) String identificador,
            @RequestParam(value = "senha", required = true) String senha,
            @RequestParam(value = "tipoUsuario", required = true) String tipoUsuario
    ) throws Exception {

        try {
            Map<Long, Object> criteria = new HashMap<>();
            if (tipoUsuario.equals("estudante")) {
                criteria.put(UsuarioCriteria.EST_TYPE, true);
                List<Estudante> estList = estService.readByCriteria(criteria, null, null);
                for (Estudante est : estList) {
                    if (est.getEmail().equals(identificador) && est.getSenha().equals(senha)) {
                        return ResponseEntity.ok(est);
                    }
                }
            } else if (tipoUsuario.equals("idoso")) {
                criteria.put(UsuarioCriteria.IDO_TYPE, true);
                List<Idoso> idoList = idoService.readByCriteria(criteria, null, null);
                for (Idoso ido : idoList) {
                    if (ido.getCodigo().equals(identificador) && ido.getSenha().equals(senha)) {
                        return ResponseEntity.ok(ido);
                    }
                }
            } else if (tipoUsuario.equals("instituicao")) {
                criteria.put(UsuarioCriteria.INS_TYPE, true);
                List<InstituicaoLongaPermanencia> insList = insService.readByCriteria(criteria, null, null);
                for (InstituicaoLongaPermanencia ins : insList) {
                    if (ins.getEmail().equals(identificador) && ins.getSenha().equals(senha)) {
                        if (ins.getTipo().equals("ains")) {
                            return ResponseEntity.status(HttpStatus.UNAVAILABLE_FOR_LEGAL_REASONS).body("Esta instituição ainda não foi aprovada por um administrador.");
                        } else {
                            return ResponseEntity.ok(ins);
                        }
                    }
                }

                criteria.clear();
                criteria.put(UsuarioCriteria.ADM_TYPE, true);
                List<Administrador> admList = admService.readByCriteria(criteria, null, null);
                for (Administrador adm : admList) {
                    if (adm.getEmail().equals(identificador) && adm.getSenha().equals(senha)) {
                        return ResponseEntity.ok(adm);
                    }
                }
            }
            return ResponseEntity.ok(null);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }
}
