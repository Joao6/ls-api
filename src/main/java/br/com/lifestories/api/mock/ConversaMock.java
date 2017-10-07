package br.com.lifestories.api.mock;

import br.com.lifestories.model.entity.Estudante;
import br.com.lifestories.model.entity.Idoso;

/**
 *
 * @author Joao Pedro
 */
public class ConversaMock {
    
    private Long id;
    private Idoso idoso;
    private Estudante estudante;
    private Integer estudanteAvaliacao;
    private Integer idosoAvaliacao;
    private String usuarioTransmissor;

    public String getUsuarioTransmissor() {
        return usuarioTransmissor;
    }

    public void setUsuarioTransmissor(String usuarioTransmissor) {
        this.usuarioTransmissor = usuarioTransmissor;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Idoso getIdoso() {
        return idoso;
    }

    public void setIdoso(Idoso idoso) {
        this.idoso = idoso;
    }

    public Estudante getEstudante() {
        return estudante;
    }

    public void setEstudante(Estudante estudante) {
        this.estudante = estudante;
    }

    public Integer getEstudanteAvaliacao() {
        return estudanteAvaliacao;
    }

    public void setEstudanteAvaliacao(Integer estudanteAvaliacao) {
        this.estudanteAvaliacao = estudanteAvaliacao;
    }

    public Integer getIdosoAvaliacao() {
        return idosoAvaliacao;
    }

    public void setIdosoAvaliacao(Integer idosoAvaliacao) {
        this.idosoAvaliacao = idosoAvaliacao;
    }
    
}
