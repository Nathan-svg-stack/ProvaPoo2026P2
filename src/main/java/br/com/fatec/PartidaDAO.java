package br.com.fatec;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class PartidaDAO {

    public boolean cadastrar(Partida p) {
        String sql = "INSERT INTO tb_partida (jogador, pontuacao) VALUES (?, ?)";
        try (Connection c = ConnectionFactory.obterConexao();
             PreparedStatement ps = c.prepareStatement(sql)) {
            ps.setString(1, p.getJogador());
            ps.setInt(2, p.getPontuacao());
            ps.execute();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public List<Partida> listar() {
        List<Partida> partidas = new ArrayList<>();
        String sql = "SELECT * FROM tb_partida";
        try (Connection c = ConnectionFactory.obterConexao();
             PreparedStatement ps = c.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                Partida p = Partida.builder()
                    .codigo(rs.getInt("codigo"))
                    .jogador(rs.getString("jogador"))
                    .pontuacao(rs.getInt("pontuacao"))
                    .build();
                partidas.add(p);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return partidas;
    }
}
