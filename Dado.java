import java.util.Random;
import java.io.Serializable;

public class Dado implements Serializable{
    //atributos
    private int sideUp;

    //métodos
    //construtor padrão
    public Dado(){
        sideUp = 1;
    }

    //construtor sobrecarregado
    public Dado(int n){
        sideUp = n;
    }

    //gerando um valor aleatório para o dado
    public void roll(){
        Random x = new Random();
        sideUp = x.nextInt(6) + 1;
    }

    //retorna o lado
    public int getSideUp(){
        return sideUp;
    }

    //atribui um valor ao lado
    public void setSideUp(int n){
        sideUp = n;
    }

    public String toString(){
        return sideUp + "-";
    }
}
