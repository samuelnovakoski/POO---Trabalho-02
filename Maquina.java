import java.util.Random;

public class Maquina extends Jogador implements JogarComoMaquina{

    public Maquina(String nome){
        super(nome);
    }
    public int aplicarEstrategia() {
        System.out.print("Jogo: ");
        System.out.println(escolherJogo() == 1 ? "jogo General" : "jogoAzar");
        
        return 0;
    }

    public int escolherJogo(){
        int jogo;

        Random x = new Random();
        jogo = x.nextInt(2) + 1;

        return jogo;
    }
}
