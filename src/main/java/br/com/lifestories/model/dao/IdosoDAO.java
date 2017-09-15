package br.com.lifestories.model.dao;

import br.com.lifestories.model.entity.Idoso;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Types;

/**
 *
 * @author Marcelo
 */
public class IdosoDAO {

    public void create(Connection conn, Idoso entity) throws Exception {
        String sql = "INSERT INTO idoso(ido_usuario_fk, ido_codigo, ido_imagem, ido_instituicao_longa_permanencia_fk) VALUES (?, ?, ?, ?);";
        PreparedStatement ps = conn.prepareStatement(sql);
        int i = 0;
        ps.setLong(++i, entity.getId());
        ps.setString(++i, entity.getCodigo());
        if (entity.getImagem() != null && !entity.getImagem().isEmpty()) {
            ps.setString(++i, entity.getImagem());
        } else {
            ps.setNull(++i, Types.VARCHAR);
        }
        ps.setLong(++i, entity.getInstituicao().getId());
        ps.execute();
        ps.close();
    }

    public void update(Connection conn, Idoso entity) throws Exception {
        String sql = "UPDATE idoso SET ido_codigo=?, ido_imagem=?, ido_instituicao_longa_permanencia_fk=? WHERE ido_usuario_fk=?;";
        PreparedStatement ps = conn.prepareStatement(sql);
        int i = 0;
        ps.setString(++i, entity.getCodigo());
        if (entity.getImagem() != null && !entity.getImagem().isEmpty()) {
            ps.setString(++i, entity.getImagem());
        } else {
            ps.setNull(++i, Types.VARCHAR);
        }
        ps.setLong(++i, entity.getInstituicao().getId());
        ps.setLong(++i, entity.getId());
        ps.execute();
        ps.close();
    }

    public void readById(Connection conn, Idoso entity) throws Exception {
        String sql = "SELECT * FROM idoso LEFT JOIN usuario ON usu_id = ido_instituicao_longa_permanencia_fk LEFT JOIN localizacao ON ido_instituicao_longa_permanencia_fk = loc_instituicao_longa_permanencia_fk WHERE ido_usuario_fk = ?";
        PreparedStatement ps = conn.prepareStatement(sql);

        int i = 0;
        ps.setLong(++i, entity.getId());

        ResultSet rs = ps.executeQuery();

        if (rs.next()) {
            entity.setCodigo(rs.getString("ido_codigo"));
            entity.setImagem(rs.getString("ido_imagem"));
            entity.getInstituicao().setId(rs.getLong("ido_instituicao_longa_permanencia_fk"));
            entity.getInstituicao().setNome(rs.getString("usu_nome"));
            entity.getInstituicao().getLocalizacao().setId(rs.getLong("ido_instituicao_longa_permanencia_fk"));
            entity.getInstituicao().getLocalizacao().setLatitude(rs.getBigDecimal("loc_latitude"));
            entity.getInstituicao().getLocalizacao().setLongitude(rs.getBigDecimal("loc_longitude"));
        }

        rs.close();
        ps.close();

    }
}
