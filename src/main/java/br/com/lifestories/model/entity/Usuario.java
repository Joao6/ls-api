package br.com.lifestories.model.entity;

import br.com.lifestories.model.base.BaseEntity;
import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;

/**
 *
 * @author Marcelo
 */
@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, include = JsonTypeInfo.As.PROPERTY, property = "tipo")
@JsonSubTypes({
    @JsonSubTypes.Type(value = Estudante.class, name = "est"),
    @JsonSubTypes.Type(value = Administrador.class, name = "adm"),
    @JsonSubTypes.Type(value = Idoso.class, name = "ido"),
    @JsonSubTypes.Type(value = InstituicaoLongaPermanencia.class, name = "ins")
})
public abstract class Usuario extends BaseEntity{
    
    private String nome;
    private String senha;
    private String tipo; 

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }
}
