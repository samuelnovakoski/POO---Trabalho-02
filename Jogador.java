import java.util.Scanner;

public abstract class Jogador{
    private String nome;
    private JogoDados[] jogos;
    private int nJogo;
    private float saldo;

    public Jogador(){
        setNome();
        jogos = new JogoDados[10];
        nJogo = 0;
        saldo = 100.00f;
    }

    public Jogador(String nome){
        this.nome = nome;
        jogos = new JogoDados[10];
        nJogo = 0;
    }

    public void setNome(){
        Scanner scan = new Scanner(System.in);
        System.out.println("Insira o nome do jogador: ");
        this.nome = scan.nextLine();
    }

    public String getNome(){
        return nome;
    }

    public JogoDados getJogo(int pos){
        return jogos[pos];
    }

    public JogoDados[] getJogos(){
        return jogos;
    }

    public int getNJogo(){
        return nJogo;
    }

    public void setNJogo(int nJogo){
        this.nJogo = nJogo;
    }

    public void setJogo(JogoDados jogo, int pos){
        jogos[pos] = jogo;
    }

    public float getSaldo(){
        return saldo;
    }

    public void setSaldo(float novoSaldo){
        this.saldo = novoSaldo;
    }

    public void jogarDados(int tipoJogo){
        if(tipoJogo == 1)
            if(jogos[nJogo] == null)
                jogos[nJogo] = new JogoGeneral();
        else
            if(jogos[nJogo] == null)
                jogos[nJogo] = new JogoAzar();
        
        jogos[nJogo].rolarDados();
    }
}
