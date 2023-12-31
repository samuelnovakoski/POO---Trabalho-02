import java.util.Scanner;

public class Humano extends Jogador implements JogarComoHumano{
    private String cpf;
    private String agencia;
    private String conta;
    private int numBanco;

    //construtor chamando o construtor herdado da superclasse
    public Humano(String nome, String cpf, String agencia, String conta, int numBanco){
        super(nome);
        this.cpf = cpf;
        this.agencia = agencia;
        this.conta = conta;
        this.numBanco = numBanco;
    }

    //implementação do método da interface JogarComoHumano: escolhe entre JogoGeneral ou JogoAzar (Herança Múltipla)
    public int escolherJogo(){
        Scanner scan = new Scanner(System.in);
        int jogo = -1;

        try{
            do{
                System.out.print("\n" + getNome() + ", escolha o jogo que deseja jogar [1 = Jogo General || 2 = Jogo Azar]: ");
                jogo = scan.nextInt();

                if(jogo == 1 || jogo == 2)
                    return jogo;
                else
                    System.out.print("Jogo invalido!\nPor favor escolha um dos jogos validos [1 = Jogo Genera || 2 = Jogo Azar]: ");
            }while(jogo != 1 && jogo != 2);
        }catch(Exception e){
            System.out.println("Erro: " + e);
        }
        return jogo;
    }

    //implementação do método da interface JogarComoHumano: escolhe uma jogada para o JogoGeneral (Herança Múltipla)
    public int escolherJogada(JogoGeneral jogo){
        Scanner scan = new Scanner(System.in);
        int jogada = -1;

        try{
            System.out.print("Escolha uma jogada: ");

            jogada = scan.nextInt();
            jogo.validarJogada(jogada);
            jogo.mostrarJogada();
            return 1;
        }catch(Exception e){
            System.out.println("Erro: " + e);
        }

        return 0;
    }
}
