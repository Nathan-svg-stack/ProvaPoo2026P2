package br.com.fatec;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Campeonato {

    private static final int[] PONTOS = {10, 6, 3, 1};

    private List<Piloto> pilotos;

    public Campeonato() {
        this.pilotos = new ArrayList<>();
    }

    public void inscrever(Piloto piloto) {
        if (this.pilotos.size() == 4) {
            System.out.println("Grid cheio.");
        } else if (this.pilotos.contains(piloto)) {
            System.out.println("Piloto repetido.");
        } else {
            this.pilotos.add(piloto);
        }
    }

    public void registrarCorrida(List<Piloto> chegada) {
        for (int i = 0; i < chegada.size(); i++) {
            chegada.get(i).somarPontos(PONTOS[i]);
        }
        chegada.get(0).registrarVitoria();
    }

    public List<Piloto> disputarCorrida(Random gerador, Piloto jogador, boolean nitro) {
        for (Piloto p : this.pilotos) {
            boolean usaNitro = nitro && p.equals(jogador);
            p.correr(gerador, usaNitro);
        }
        List<Piloto> chegada = new ArrayList<>(this.pilotos);
        chegada.sort((a, b) -> Integer.compare(a.getTempo(), b.getTempo()));
        registrarCorrida(chegada);
        return chegada;
    }

    public List<Piloto> getClassificacao() {
        List<Piloto> classificacao = new ArrayList<>(this.pilotos);
        classificacao.sort((a, b) -> {
            int resultado = Integer.compare(b.getPontos(), a.getPontos());
            if (resultado != 0) {
                return resultado;
            }
            resultado = Integer.compare(b.getVitorias(), a.getVitorias());
            if (resultado != 0) {
                return resultado;
            }
            return a.getNome().compareTo(b.getNome());
        });
        return classificacao;
    }
}
