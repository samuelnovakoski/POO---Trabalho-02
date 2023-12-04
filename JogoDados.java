import java.io.Serializable;

public abstract class JogoDados implements Estatistica, Serializable{
    private int nDados;
    private String nomeJogo;
    private float valorAposta;
    private Dado dados[];
    private int[] statDados;
    private int resultado;

    //construtor padrão
    public JogoDados(){
        nDados = 0;
    }

    //construtor sobrecarregado
    public JogoDados(String jogo, int nDados){
        this.nomeJogo = jogo;
        this.nDados = nDados;

        dados = new Dado[nDados];

        for(int i = 0; i < nDados; i++)
            dados[i] = new Dado();

        statDados = new int[numFaces]; //numFaces é o atributo fixo da interface Estatisica
    }

    //retorna o nome (tipo) do jogo (JogoGeneral ou JogoAzar)
    public String getNomeJogo(){
        return nomeJogo;
    }

    //retorna a aposta do jogo
    public float getAposta(){
        return valorAposta;
    }

    //seta a aposta do jogo
    public void setAposta(float valorAposta){
        this.valorAposta = valorAposta;
    }

    //rola os dados
    public void rolarDados(){
        for(int i = 0; i < nDados; i++)
            dados[i].roll(); 
        
        this.somarFacesSorteadas(dados); //soma as faces sorteadas toda vez que os dados sao rolados
    }

    //retorna um dado especifico
    public Dado getDado(int pos){
        return dados[pos];
    }

    //retorna o vetor de dados
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

    //retorna o vetor de estatistica
    public int[] getStatDados(){
        return statDados;
    }

    //seta o resultado em 1 (ganhou) ou 0 (perdeu)
    public void setResultado(int resultado){
        this.resultado = resultado;
    }

    //retorna o resultado
    public int getResultado(){
        return resultado;
    }

    //soma as faces dos dados
    public int somarFaces(Dado dados[]){
        int x = 0;

        for(int i = 0; i < nDados; i++)
            x += dados[i].getSideUp();

        return x;
    }

    //implementação do metodo da interface Estatistica: armazena os dados sorteados no vetor de estatistica
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
