import java.util.Scanner;

public abstract class Jogador {
    private String nome;
    private JogoDados[] jogos = new JogoDados[10];
    private int jogo = 0;
    private int tipo;

    public Jogador(){
        setNome();
    }

    public Jogador(String nome, int tipo){
        this.nome = nome;
        this.tipo = tipo;
    }

    public void setNome(){
        Scanner scan = new Scanner(System.in);
        System.out.println("Insira o nome do jogador: ");
        this.nome = scan.nextLine();
    }

    public void setTipo(){
        Scanner scan = new Scanner(System.in);
        System.out.println("Insira o tipo de jogador (1 - Humano; 2 - Maquina): ");
        this.tipo = scan.nextLine();
    }

    public String getNome(){
        return nome;
    }

    public JogoDados[] getJogos(){
        return jogos;
    }

    public int getJogo(){
        return jogo;
    }

    public int getTipo(){
        return tipo;
    }

    public void jogarDados(){
        if(jogo < 10){
            jogos[jogo].rolarDados();
            jogos[jogo].mostrarDados(); //mostrar os dados na tela
            jogo++;
        }
    }

    public void mostrarJogadasExecutadas(){
        for(int i = 0; i < jogo; i++)
            jogos[i].getDados();
    }
}
