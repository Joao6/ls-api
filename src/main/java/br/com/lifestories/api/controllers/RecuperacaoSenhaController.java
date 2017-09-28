package br.com.lifestories.api.controllers;

import br.com.lifestories.model.entity.RecuperacaoSenha;
import br.com.lifestories.model.service.RecuperacaoSenhaService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author Joao Pedro
 */
@RestController
@RequestMapping(value = "/recuperacao")
public class RecuperacaoSenhaController {

    RecuperacaoSenhaService recuperacaoSenhaService = new RecuperacaoSenhaService();

    @PostMapping
    public ResponseEntity create(@RequestBody RecuperacaoSenha recuperacaoSenha) {
        try {
            recuperacaoSenhaService.create(recuperacaoSenha);
            return ResponseEntity.ok(recuperacaoSenha);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }

    @PutMapping
    public ResponseEntity update(@RequestBody RecuperacaoSenha recuperacaoSenha) {
        try {
            recuperacaoSenhaService.update(recuperacaoSenha);
            return ResponseEntity.ok(recuperacaoSenha);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }

    @DeleteMapping(value = "/{idDelete}")
    public ResponseEntity delete(@PathVariable Long idDelete) {
        try {
            recuperacaoSenhaService.delete(idDelete);
            return ResponseEntity.ok("Excluído com sucesso");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }
    
    @GetMapping(value = "/{hash}")
    public ResponseEntity readByHashCode(@PathVariable String hash) {
        try {            
            return ResponseEntity.ok(recuperacaoSenhaService.readByHashCode(hash));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }

}
