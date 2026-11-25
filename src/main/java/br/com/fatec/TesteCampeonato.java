package br.com.fatec;

import java.util.ArrayList;
import java.util.List;

public class TesteCampeonato {
    public static void main(String[] args) {
        Carro carro = new Carro("Dinka Blista", 200);
        Piloto lucia = new Piloto("Lucia", carro);
        Piloto jason = new Piloto("Jason", carro);
        Piloto raul = new Piloto("Raul", carro);
        Piloto cal = new Piloto("Cal", carro);

        Campeonato campeonato = new Campeonato();
        campeonato.inscrever(lucia);
        campeonato.inscrever(jason);
        campeonato.inscrever(new Piloto("Lucia", carro));
        campeonato.inscrever(raul);
        campeonato.inscrever(cal);
        campeonato.inscrever(new Piloto("Boobie", carro));

        List<Piloto> chegada1 = new ArrayList<>();
        chegada1.add(lucia);
        chegada1.add(jason);
        chegada1.add(raul);
        chegada1.add(cal);
        campeonato.registrarCorrida(chegada1);

        List<Piloto> chegada2 = new ArrayList<>();
        chegada2.add(raul);
        chegada2.add(cal);
        chegada2.add(jason);
        chegada2.add(lucia);
        campeonato.registrarCorrida(chegada2);

        List<Piloto> chegada3 = new ArrayList<>();
        chegada3.add(cal);
        chegada3.add(raul);
        chegada3.add(jason);
        chegada3.add(lucia);
        campeonato.registrarCorrida(chegada3);

        List<Piloto> classificacao = campeonato.getClassificacao();
        for (int i = 0; i < classificacao.size(); i++) {
            System.out.println((i + 1) + ". " + classificacao.get(i));
        }
    }
}
