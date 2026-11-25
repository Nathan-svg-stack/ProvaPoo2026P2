package br.com.fatec;

import java.util.List;

public class TestePartidaDAO {
    public static void main(String[] args) {
        PartidaDAO dao = new PartidaDAO();

        Partida ana = Partida.builder().jogador("Ana").pontuacao(26).build();
        Partida bia = Partida.builder().jogador("Bia").pontuacao(13).build();

        System.out.println("Cadastrou Ana? " + dao.cadastrar(ana));
        System.out.println("Cadastrou Bia? " + dao.cadastrar(bia));

        List<Partida> partidas = dao.listar();
        for (Partida p : partidas) {
            System.out.println(p.getCodigo() + " - " + p.getJogador() + " - " + p.getPontuacao());
        }
    }
}
