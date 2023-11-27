public abstract class JogoDados implements Estatistica{
    private int nDados;
    private String nomeJogo;
    private float valorAposta;
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

    public String getNomeJogo(){
        return nomeJogo;
    }

    public float getAposta(){
        return valorAposta;
    }

    public void setAposta(float valorAposta){
        this.valorAposta = valorAposta;
    }

    public void rolarDados(){
        for(int i = 0; i < nDados; i++)
            dados[i].roll();   
    }

    public Dado getDado(int pos){
        return dados[pos];
    }

    public Dado[] getDados(){
        return dados;
    }

    public String toString(){
        String s = "";

        for(int i = 0; i < nDados; i++){
            if(i < nDados - 1)
                s += dados[i].toString();
            else
                s += dados[i].getSideUp();
        }
        return s;
    }

    public void mostrarDados(){
        System.out.println("Dados: " + toString());
    }

    public int somarFacesSorteadas(Dado dados[]){
        int x = 0;

        for(int i = 0; i < nDados; i++)
            x += dados[i].getSideUp();

        return x;
    }
}
