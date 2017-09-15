/*
* Código fonte  produzido  pelo  Núcleo  de  Pesquisa,  Desenvolvimento e Inovação (NPDI) da  
* FAI - Centro de Ensino Superior em Gestão, Tecnologia e Educação.
* Todos os direitos reservados à DL Comércio e Indústria de Produtos Eletrônicos, 
* através do Convênio para Capacitação, Pesquisa e Desenvolvimento de Projetos 
* assinado em 30 de setembro de 2013.
 */
package br.com.lifestories.api.utils;

import br.com.lifestories.api.controllers.AdministradorController;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Base64;
import javax.servlet.ServletContext;
import static org.springframework.security.core.context.SecurityContextHolder.getContext;

/**
 * Classe de utilidade direcionadas a facilitar a implementação do Controller de
 * Imagem
 *
 * @version 1.0
 * @since Release 01
 *
 */
public class ImagemUtils {

    public static final String UPLOAD_PATH = System.getProperty("user.home") + "\\LIFE_STORIES\\IMG\\";

    public static final String IMAGEM_DEFAULT_NOTICIA = "IMG_DEFAULT_NOTICIA.png";
    public static final String IMAGEM_DEFAULT_PROMOCAO = "IMG_DEFAULT_PROMOCAO.png";
    public static final String IMAGEM_DEFAULT_PESQUISA = "IMG_DEFAULT_PESQUISA.png";
    public static final String IMAGEM_DEFAULT_APLICATIVO = "IMG_DEFAULT_APLICATIVO.png";
    public static final String IMAGEM_DEFAULT_PRODUTO = "IMG_DEFAULT_PRODUTO.png";
    public static final String IMAGEM_DEFAULT_USUARIO = "IMG_DEFAULT_USUARIO.png";

    public static String writeToFile(String base64, String fileName) {
        try {            
            String test = AdministradorController.class.getResource("").getPath();
            int index = test.indexOf("WEB-INF/");
            test = test.substring(0, index + 8);
            test += "resources/";
            test = test.substring(1, test.length());
            test = test.replace("/", "\\");
            //test = "D:\\Joao Pedro\\Projetos\\TCC - Life Stories\\ls-api-heroku-git\\ls-api\\target\\ls-api\\WEB-INF\\resources\\";

            File diretorio = new File(test);
            if (!diretorio.exists()) {
                diretorio.mkdirs();
            }
            base64 = base64.split(",")[1];
            byte[] bytes = Base64.getDecoder().decode(new String(base64).getBytes("UTF-8"));

            OutputStream out = new FileOutputStream(new File(test + fileName));
            out.write(bytes);

            out.flush();
            out.close();
            return test + fileName;

        } catch (IOException e) {
            return "Ocorreu um erro interno: " + e.getMessage();

        }
    }

    /*public static void deleteFile(String nome, String path) throws Exception {
        try {
            ImagemService imagemService = new ImagemService();
            Imagem imagem = null;

            if (!isDefaultImage(nome)) {
                File file = new File(path);
                file.delete();
                imagem = imagemService.readByNome(nome);
                imagemService.delete(imagem.getId());
            }
        } catch (Exception e) {

        }
    }*/
    public static String getImagemFromBase64(String base64, String imgNome) throws Exception {

        String imgUrl;

        if (base64 != null && !base64.isEmpty()) {
            imgNome += System.currentTimeMillis() + ".png";
            imgUrl = ImagemUtils.writeToFile(base64, imgNome);
        } else {
            imgUrl = "";
        }
        return imgUrl;
    }
}
