public abstract class JogoDados {
    private int nDados;
    private String nomeJogo;
    private float saldo;
    private Dado dados[];

    public JogoDados(String jogo, int n){
        this.nomeJogo = jogo;
        this.nDados = n;
    }

    public void rolarDados(){
        for(int i = 0; i < nDados; i++){
            dados[i] = new Dado();
            dados[i].roll();
        }
    }

    public Dado getDado(int i){
        return dados[i];
    }

    public void teste(){
        if(nomeJogo == "")
    }
}
