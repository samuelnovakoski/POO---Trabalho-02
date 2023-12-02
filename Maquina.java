import java.util.Random;

public class Maquina extends Jogador implements JogarComoMaquina{
    private float valorAposta;
    private int jogoEscolhido;

    public Maquina(String nome){
        super(nome);
    }
    public int aplicarEstrategia() {
        jogoEscolhido = escolherJogo();

        System.out.print("Jogo: ");
        System.out.println(jogoEscolhido == 1 ? "jogo General" : "jogoAzar");

        if(jogoEscolhido == 1){
            JogoGeneral jogoG = new JogoGeneral();

            setJogo(jogoG, getNJogo());

            if(getSaldo() >= 10){
                Random aposta = new Random();
                valorAposta = aposta.nextFloat() * getSaldo();
                valorAposta = Math.min(valorAposta, getSaldo());
            }
            else
                valorAposta = getSaldo();

            System.out.println("Saldo " + getNome() + " R$" + String.format("%.2f", getSaldo()));
            System.out.println("Valor apostado pelo jogador " + getNome() + " R$" + String.format("%.2f", valorAposta));

            int j = 1;
            for(int i = 0; i < 13; i++){
                jogarDados(1);
                jogoG.mostrarDados();
                jogoG.validarJogada(j);
                System.out.println("Jogada escolhida: " + j);
                jogoG.mostrarJogada();
                j++;
            }

            jogoG.ganhou();

            if(jogoG.getGanhou()){
                setSaldo(getSaldo() + valorAposta);
                System.out.println("Ganhou");
            }
            else{
                setSaldo(getSaldo() - valorAposta);
                System.out.println("Perdeu");
            }

            System.out.println("\nSaldo: R$" + String.format("%.2f", getSaldo()));
            setNJogo(getNJogo() + 1);

            return 1;
        }
        else{
            JogoAzar jogoA = new JogoAzar();

            setJogo(jogoA, getNJogo());

            if(getSaldo() >= 10){
                Random aposta = new Random();
                valorAposta = aposta.nextFloat() * getSaldo();
                valorAposta = Math.min(valorAposta, getSaldo());
            }
            else
                valorAposta = getSaldo();

            System.out.println("Saldo " + getNome() + " R$" + String.format("%.2f", getSaldo()));
            System.out.println("Valor apostado pelo jogador " + getNome() + " R$" + String.format("%.2f", valorAposta));

            jogarDados(2);
            jogoA.executarRegrasJogo();

            if(jogoA.getResultado() == 1)
                setSaldo(getSaldo() + valorAposta);
            else if(jogoA.getResultado() == 0)
                setSaldo(getSaldo() - valorAposta);
            
            System.out.println("\nSaldo: R$" + String.format("%.2f", getSaldo()));
            setNJogo(getNJogo() + 1);
            
            return 1;
        }
    }

    public int getJogoEscolhido(){
        return jogoEscolhido;
    }

    public int escolherJogo(){
        int jogo;

        Random x = new Random();
        jogo = x.nextInt(2) + 1;

        return jogo;
    }
}
