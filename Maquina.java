import java.util.Random;

public class Maquina extends Jogador implements JogarComoMaquina{
    private float valorAposta;
    private int jogoEscolhido;

    //construtor chamando o construtor herdado da superclasse
    public Maquina(String nome){
        super(nome);
    }

    //implementação da interface JogarComoMaquina: randomiza a jogada
    public int aplicarEstrategia() {
        jogoEscolhido = escolherJogo();

        System.out.print("\nJogo escolhido pelo jogador " + getNome() + ": ");
        System.out.println(jogoEscolhido == 1 ? "jogo General" : "jogoAzar");

        if(jogoEscolhido == 1){
            JogoGeneral jogoG = new JogoGeneral();

            setJogo(jogoG, getNJogo());

            if(getSaldo() >= 10){
                Random aposta = new Random();
                valorAposta = aposta.nextFloat() * getSaldo();
                jogoG.setAposta(Math.min(valorAposta, getSaldo()));
            }
            else
                jogoG.setAposta(getSaldo());

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

            if(jogoG.getResultado() == 1){
                setSaldo(getSaldo() + jogoG.getAposta());
                System.out.println("Ganhou");
            }
            else{
                setSaldo(getSaldo() - jogoG.getAposta());
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
                jogoA.setAposta(Math.min(valorAposta, getSaldo()));
            }
            else
                jogoA.setAposta(getSaldo());

            System.out.println("Saldo " + getNome() + " R$" + String.format("%.2f", getSaldo()));
            System.out.println("Valor apostado pelo jogador " + getNome() + " R$" + String.format("%.2f", valorAposta));

            jogarDados(2);
            jogoA.executarRegrasJogo();

            if(jogoA.getResultado() == 1)
                setSaldo(getSaldo() + jogoA.getAposta());
            else if(jogoA.getResultado() == 0)
                setSaldo(getSaldo() - jogoA.getAposta());
            
            System.out.println("\nSaldo: R$" + String.format("%.2f", getSaldo()));
            setNJogo(getNJogo() + 1);
            
            return 1;
        }
    }

    public int getJogoEscolhido(){
        return jogoEscolhido;
    }

    //implementação da interface JogarComoMaquina: escolhe um jogo aleatorio entre 1 e 2
    public int escolherJogo(){
        int jogo;

        Random x = new Random();
        jogo = x.nextInt(2) + 1;

        return jogo;
    }
}
