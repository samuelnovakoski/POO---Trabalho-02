public class JogoAzar extends JogoDados{
    private int resultadoJogo;

    public JogoAzar(){
        super("JogoAzar", 2);
    }

    public void executarRegrasJogo(){
        mostrarDados();
        
        int soma = somarFaces(getDados());

        System.out.println("Resultado: " + soma + "\n");

        if(soma == 7 || soma == 11){
            System.out.println("Jogador ganhou");
            resultadoJogo = 1;
        }
        else if(soma == 2 || soma == 3 || soma == 12){
            System.out.println("Jogador perdeu");
            resultadoJogo = 0;
        }
        else{
            int valorBusca = 0;

            do{
                valorBusca = soma;
                
                System.out.println("Valor a buscar: " + soma);
                
                rolarDados();
                System.out.println("Dados: " + toString());

                soma = somarFaces(getDados());
                System.out.println("Resultado: " + soma);

                if(soma == valorBusca){
                    System.out.println("Jogador ganhou");
                    resultadoJogo = 1;
                }
                else if(soma == 2 || soma == 3 || soma == 12){
                    System.out.println("Jogador perdeu");
                    resultadoJogo = 0;
                }
            }while(soma != valorBusca && soma != 2 && soma != 3 && soma != 12);
        }
    }

    public int getResultado(){
        return resultadoJogo;
    }
}
