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
        dados = new Dado[nDados];
        for(int i = 0; i < nDados; i++)
            dados[i] = new Dado();
    }

    public String getJogo(){
        return nomeJogo;
    }

    public float getSaldo(){
        return saldo;
    }

    public void setSaldo(float novoSaldo){
        this.saldo = novoSaldo;
    }

    public void rolarDados(){
        for(int i = 0; i < nDados; i++){
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

    public String toString(){
        String s = "";

        for(int i = 0; i < nDados; i++){
            if(i < nDados - 1)
                s += dados[i].getSideUp() + " - ";
            else
                s += dados[i].getSideUp();
        }

        return s;
    }
}
