public abstract class JogoDados implements  Estatistica{
    private int nDados;
    private String nomeJogo;
    private float saldo;
    private Dado dados[];

    public JogoDados(){
        nDados = 0;
    }

    public JogoDados(String jogo, int nDados){
        this.nomeJogo = jogo;
        this.nDados = nDados;
    }

    public void rolarDados(){
        for(int i = 0; i < nDados; i++){
            dados[i] = new Dado();
            dados[i].roll();
        }
    }

    public Dado[] getDados(){
        return dados;
    }

    public int somarFacesSorteadas(Dado dados[]) {
        int x = 0;
        
        for(int i = 0; i < nDados; i++){
            x += dados[i].getSideUp();
        }

        return x;
    }
}
