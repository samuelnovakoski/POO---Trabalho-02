import java.io.Serializable;

public abstract class JogoDados implements Estatistica, Serializable{
    private int nDados;
    private String nomeJogo;
    private float valorAposta;
    private Dado dados[];
    private int[] statDados;
    private int resultado;

    public JogoDados(){
        nDados = 0;
    }

    public JogoDados(String jogo, int nDados){
        this.nomeJogo = jogo;
        this.nDados = nDados;

        dados = new Dado[nDados];

        for(int i = 0; i < nDados; i++)
            dados[i] = new Dado();

        statDados = new int[6];
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
        
        this.somarFacesSorteadas(dados);
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

    public int[] getStatDados(){
        return statDados;
    }

    public void setResultado(int resultado){
        this.resultado = resultado;
    }

    public int getResultado(){
        return resultado;
    }

    public int somarFaces(Dado dados[]){
        int x = 0;

        for(int i = 0; i < nDados; i++)
            x += dados[i].getSideUp();

        return x;
    }

    public void somarFacesSorteadas(Dado dados[]){
        for(int i = 0; i < 6; i++){
            for(int j = 0; j < nDados; j++){
                if(i+1 == dados[j].getSideUp()){
                    statDados[i]++;
                }
            }
        }
    }
}
