package br.com.lifestories.model.entity;

/**
 *
 * @author Marcelo
 */
public class Idoso extends Usuario{
    
    private String codigo;
    private String imagem;
    private InstituicaoLongaPermanencia instituicao;

    public Idoso() {
        this.instituicao = new InstituicaoLongaPermanencia();
    }
    
    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getImagem() {
        return imagem;
    }

    public void setImagem(String imagem) {
        this.imagem = imagem;
    }

    public InstituicaoLongaPermanencia getInstituicao() {
        return instituicao;
    }

    public void setInstituicao(InstituicaoLongaPermanencia instituicao) {
        this.instituicao = instituicao;
    }
}
