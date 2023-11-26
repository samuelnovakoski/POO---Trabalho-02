import java.util.Scanner;

public class Humano extends Jogador implements JogarComoHumano{
    private String cpf;
    private String agencia;
    private String conta;
    private int numeroBanco;

    public Humano(String nome, String cpf, String agencia, String conta, int numeroBanco){
        super(nome);
        this.cpf = cpf;
        this.agencia = agencia;
        this.conta = conta;
        this.numeroBanco = numeroBanco;
    }

    public int escolherJogo(){
        Scanner scan = new Scanner(System.in);
        int jogo = -1;

        try{
            do{
                System.out.println("Escolha o jogo que deseja jogar: [1 = Jogo General | 2 = Jogo de azar] ");
                jogo = scan.nextInt();
                
                if(jogo == 1 || jogo == 2)
                    return jogo;
                else
                    System.out.println("Por favor escolha um dos jogos existentes [1 = Jogo General | 2 = Jogo de azar] ");
            }while(jogo != 1 && jogo != 2);
        }catch(Exception e){
            System.out.println("Error: " + e.toString());
        }
        return 1;
    }

    public int escolherJogada(JogoGeneral jogo){
        Scanner scan = new Scanner(System.in);
        int jogada = -1;

        try{
            System.out.print("Escolha uma jogada: ");
            
            jogada = scan.nextInt();
            jogo.validarJogada(jogada);
        }catch(Exception e){
            System.out.println("erro: " + e.toString());
        }
        return jogada;
    }  
}
