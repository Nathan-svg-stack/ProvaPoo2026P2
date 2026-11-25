
package br.com.fatec;

import java.util.Comparator;
import java.util.List;
import java.util.Random;

import javax.swing.JOptionPane;

public class Jogo {

    private static final int CORRIDAS = 3;
    private static final int NITROS = 2;

    public static void main(String[] args) {

        int opcao;

        do {
            opcao = lerInteiro(
                    "GTA VI\n\n"
                    + "1 - Jogar\n"
                    + "2 - Ranking\n"
                    + "0 - Sair"
            );

            switch (opcao) {

                case 1:
                    jogarCampeonato();
                    break;

                case 2:
                exibirRanking();
                    break;

                case 0:
                    JOptionPane.showMessageDialog(
                            null, "Ate a proxima!"
                    );
                    break;

                default:
                    JOptionPane.showMessageDialog(
                            null, "Opcao invalida"
                    );
            }

        } while (opcao != 0);
    }

    private static void jogarCampeonato() {

        String nomeJogador = JOptionPane.showInputDialog(
                "Digite seu nome:"
        );

        if (nomeJogador == null || nomeJogador.isBlank()) {
            return;
        }

        int escolha;

        do {
            escolha = lerInteiro(
                    "Escolha seu piloto:\n"
                    + "1 - Jason\n"
                    + "2 - Lucia"
            );

        } while (escolha != 1 && escolha != 2);

        Piloto jogador;

        Concessionaria concessionaria = new Concessionaria();

        switch (escolha) {

            case 1:
                jogador = new Piloto(
                        "Jason", concessionaria.sortear()
                );
                break;

            case 2:
                jogador = new Piloto(
                        "Lucia", concessionaria.sortear()
                );
                break;

            default:
                return;
        }

        Campeonato campeonato = new Campeonato();

        Piloto raul = new Piloto(
                "Raul", concessionaria.sortear()
        );

        Piloto boobie = new Piloto(
                "Boobie", concessionaria.sortear()
        );

        Piloto cal = new Piloto(
                "Cal", concessionaria.sortear()
        );

        campeonato.inscrever(jogador);
        campeonato.inscrever(raul);
        campeonato.inscrever(boobie);
        campeonato.inscrever(cal);

        JOptionPane.showMessageDialog(
                null, "Seu carro: " + jogador.getCarro()
        );

        Random gerador = new Random();

        int nitrosRestantes = NITROS;

        for (int i = 0; i < CORRIDAS; i++) {

            JOptionPane.showMessageDialog(
                    null,
                    "Corrida " + (i + 1) + "/" + CORRIDAS
                    + "\n\n"
                    + formatarClassificacao(
                            campeonato.getClassificacao()
                    )
            );

            int opcaoNitro;

            do {
                opcaoNitro = lerInteiro(
                        "Usar nitro? 1-Sim 2-Nao (restam "
                        + nitrosRestantes + ")"
                );

            } while (opcaoNitro != 1 && opcaoNitro != 2);

            boolean usarNitro = false;

            if (opcaoNitro == 1) {

                if (nitrosRestantes > 0) {

                    usarNitro = true;
                    nitrosRestantes--;

                } else {

                    JOptionPane.showMessageDialog(
                            null, "Sem nitro restante"
                    );
                }
            }

            List<Piloto> chegada = campeonato.disputarCorrida(
                    gerador, jogador, usarNitro
            );

            StringBuilder resultado = new StringBuilder("Chegada\n");

            for (int j = 0; j < chegada.size(); j++) {

                Piloto piloto = chegada.get(j);

                resultado.append(j + 1)
                        .append(". ")
                        .append(piloto.getNome())
                        .append(" - ")
                        .append(piloto.getTempo())
                        .append("s\n");
            }

            JOptionPane.showMessageDialog(
                    null, resultado.toString()
            );
        }

        List<Piloto> classificacao =
                campeonato.getClassificacao();

        JOptionPane.showMessageDialog(
                null, formatarClassificacao(classificacao)
        );

        int posicao = classificacao.indexOf(jogador) + 1;

        if (posicao == 1) {

            JOptionPane.showMessageDialog(
                    null, "Campeao de Vice City!"
            );

        } else {

            JOptionPane.showMessageDialog(
                    null, "Posicao final: " + posicao
            );
        }
        Partida partida = Partida.builder()
                .jogador(nomeJogador)
                .pontuacao(jogador.getPontos())
                .build();

        PartidaDAO dao = new PartidaDAO();

        if (dao.cadastrar(partida)) {

            JOptionPane.showMessageDialog(
                    null, "Partida salva"
            );

        } else {

            JOptionPane.showMessageDialog(
                    null, "Erro ao salvar a partida"
            );
        }
    }
    private static String formatarClassificacao(
            List<Piloto> pilotos) {

        StringBuilder texto = new StringBuilder();

        for (int i = 0; i < pilotos.size(); i++) {

            Piloto piloto = pilotos.get(i);

            texto.append(i + 1)
                    .append(". ")
                    .append(piloto.getNome())
                    .append(" - ")
                    .append(piloto.getPontos())
                    .append(" pontos - ")
                    .append(piloto.getVitorias())
                    .append(" vitoria(s)\n");
        }

        return texto.toString();
    }
    
    private static int lerInteiro(String mensagem) {

        String entrada = JOptionPane.showInputDialog(
                null, mensagem
        );

        if (entrada == null) {
            return 0;
        }

        try {
            return Integer.parseInt(entrada);

        } catch (NumberFormatException e) {
            return -1;
        }
    }

    private static void exibirRanking() {

        PartidaDAO dao = new PartidaDAO();

        List<Partida> partidas = dao.listar();

        if (partidas.isEmpty()) {
            JOptionPane.showMessageDialog(
                    null, "Nenhuma partida encontrada."
            );
            return;
        }

        partidas.sort(
                Comparator.comparingInt(
                        Partida::getPontuacao
                ).reversed()
        );

        StringBuilder ranking = new StringBuilder();

        ranking.append("RANKING\n\n");

        int limite = Math.min(5, partidas.size());

        for (int i = 0; i < limite; i++) {

            Partida partida = partidas.get(i);

            ranking.append(i + 1)
                    .append("º - ")
                    .append(partida.getJogador())
                    .append(" | ")
                    .append(partida.getPontuacao())
                    .append(" pontos\n");
        }

        JOptionPane.showMessageDialog(
                null, ranking.toString()
        );
    }
}
