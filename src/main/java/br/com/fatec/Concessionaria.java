
package br.com.fatec;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Concessionaria {

    private List<Carro> estoque;
    private Random gerador;

    public Concessionaria() {

        estoque = new ArrayList<>();
        gerador = new Random();

        String[] modelos = {
            "Truffade Adder",
            "Grotti Cheetah",
            "Pegassi Infernus",
            "Vapid Dominator",
            "Bravado Banshee",
            "Declasse Vigero",
            "Karin Sultan",
            "Dinka Blista"
        };

        int[] potencias = {
            520, 500, 480, 420, 400, 350, 300, 200
        };

        for (int i = 0; i < modelos.length; i++) {
            Carro carro = new Carro(modelos[i], potencias[i]);
            estoque.add(carro);
        }
    }

    public Carro sortear() {
        int indice = gerador.nextInt(estoque.size());

        Carro carro = estoque.remove(indice);

        return carro;
    }

    public int restantes() {
        return estoque.size();
    }
}
