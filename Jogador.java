import java.util.Scanner;

public abstract class Jogador {
    private String nome;
    private JogoDados[] jogos = new JogoDados[10];
    private int jogo = 0;

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

    public void jogarDados(){
        // jogos[jogo] = new JogoDados();
    }
}
