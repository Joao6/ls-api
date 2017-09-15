package br.com.lifestories.api.controllers;

import br.com.lifestories.api.utils.ImagemUtils;
import br.com.lifestories.model.criteria.UsuarioCriteria;
import br.com.lifestories.model.entity.Idoso;
import br.com.lifestories.model.service.IdosoService;
import java.util.HashMap;
import java.util.Map;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author NPDI-03
 */
@RestController
@RequestMapping(value = "/instituicoes/{id}/idosos")
public class IdosoController {
    
    IdosoService idosoService = new IdosoService();
    
    @GetMapping
    public ResponseEntity readByCriteria(@PathVariable Long id, Long limit, Long offset) throws Exception{
        try {
            Map<Long, Object> criteria = new HashMap<>();
            criteria.put(UsuarioCriteria.IDOSO_INSTITUICAO, id);
            return ResponseEntity.ok(idosoService.readByCriteria(criteria, limit, offset));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }
    
    @PostMapping
    public ResponseEntity create(@RequestBody Idoso idoso){
        try {
            //TODO: manipular a imagem do idoso
            idoso.setTipo("ido");  
            //idoso.setImagem(ImagemUtils.getImagemFromBase64(idoso.getImagem(), idoso.getCodigo()));
            idosoService.create(idoso);
            return ResponseEntity.ok(idoso);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }    
    
}
