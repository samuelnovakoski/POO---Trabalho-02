import java.util.Scanner;

public abstract class Jogador {
    private String nome;
    private JogoDados[] jogos = new JogoDados[10];
    private int njogo = 0;

    public Jogador(){
        setNome();
    }

    public Jogador(String nome){
        this.nome = nome;
    }

    public void setNome(){
        Scanner scan = new Scanner(System.in);
        System.out.println("Insira o nome do jogador: ");
        this.nome = scan.nextLine();
    }

    public String getNome(){
        return nome;
    }

    public JogoDados[] getJogos(){
        return jogos;
    }

    public int getJogo(){
        return njogo;
    }

    public void jogarDados(int tipoJogo){
        if(njogo < 10){
            if(tipoJogo == 1){
                jogos[njogo] = new JogoGeneral();
            }
            else{
                jogos[njogo] = new JogoAzar();

                if(njogo - 1 >= 0)
                    jogos[njogo].setSaldo(jogos[njogo - 1].getSaldo());
                else
                    jogos[njogo].setSaldo(100);

                if(jogos[njogo].getSaldo() > 0){
                    jogos[njogo].rolarDados();

                    System.out.println("dados: " + jogos[njogo].toString());

                    JogoAzar jogox = (JogoAzar) jogos[njogo];

                    jogox.executarRegrasJogo();

                    System.out.println("Saldo: " + jogox.getSaldo());
                }
                else
                    System.out.println("o jogador nao tem saldo suficiente para fazer uma aposta.");
            }

            njogo++;
        }
        else
            System.out.println("Numero maximo de jogos ja foi atingido.");
    }

    // public void mostrarJogadasExecutadas(){
        
    // }
}
