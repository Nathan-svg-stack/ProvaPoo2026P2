package br.com.fatec;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class TesteCarro {
    
    public static void main(String[] args){
        Carro carro1 = new Carro("Grotti Cheetah", 500);
        Carro carro2 = new Carro("Bravado Banshee", 400);
        Carro carro3 = new Carro("Dinka Blista", 200);

        System.out.println(carro1 + " faz a volta em " + carro1.getTempoDeVolta() + "s");
        System.out.println(carro2 + " faz a volta em " + carro2.getTempoDeVolta() + "s");
        System.out.println(carro3 + " faz a volta em " + carro3.getTempoDeVolta() + "s");

        Carro carro4 = new Carro("Grotti Cheetah", 500);
        Carro carro5 = new Carro("Pegassi Infernus", 500);

        System.out.println("Iguais? " + carro1.equals(carro4));
        System.out.println("Iguais? " + carro1.equals(carro5));

        List<Carro> carros = new ArrayList<>();

        carros.add(carro3);
        carros.add(new Carro("Ocelot Pariah", 500));
        carros.add(carro2);
        carros.add(carro1);

        Collections.sort(carros);

        System.out.println(carros);
    }
}
