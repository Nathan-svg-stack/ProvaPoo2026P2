package br.com.fatec;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor 
public class Carro implements Comparable<Carro>{
    
    private String modelo;
    private int potencia;

    public int getTempoDeVolta(){
        return 30 + 6000 / potencia;
    }

    @Override 
    public String toString(){
        return modelo + " (" + potencia + " cv)";
    }

    @Override
    public int compareTo(Carro outro){
        if (this.potencia != outro.potencia){
            return outro.potencia - this.potencia;
        }

        return this.modelo.compareTo(outro.modelo);
    }

    @Override 
    public boolean equals(Object obj){
        if(this == obj) return true;

        if(obj == null || getClass() != obj.getClass()) return false;

        Carro outro = (Carro) obj;

        return modelo.equals(outro.modelo);
    }
}
