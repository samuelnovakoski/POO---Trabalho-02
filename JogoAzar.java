public class JogoAzar extends JogoDados{
    //construtor chamando o construtor herdado da superclasse
    public JogoAzar(){
        super("JogoAzar", 2);
    }

    public void executarRegrasJogo(){
        mostrarDados();
        
        int soma = somarFaces(getDados()); //método herdado de JogoDados

        System.out.println("Resultado: " + soma + "\n");

        if(soma == 7 || soma == 11){
            System.out.println("Jogador ganhou"); //caso a soma seja 7 ou 11 o jogador ganha
            setResultado(1);
        }
        else if(soma == 2 || soma == 3 || soma == 12){ //caso a soma seja 2, 3 ou 12 o jogador perde
            System.out.println("Jogador perdeu");
            setResultado(0);
        }
        else{ //caso a soma nao seja nenhuma das outras acima, o jogo continua
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
                    setResultado(1);
                }
                else if(soma == 2 || soma == 3 || soma == 12){
                    System.out.println("Jogador perdeu");
                    setResultado(0);
                }
            }while(soma != valorBusca && soma != 2 && soma != 3 && soma != 12);
        }
    }
}
