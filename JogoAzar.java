import java.util.Scanner;

public class JogoAzar extends JogoDados{
    private float valorAposta;

    public JogoAzar(){
        super("JogoAzar", 2);
    }

    public void executarRegrasJogo(){
        System.out.print("Insira o valor da aposta: ");
        try{
            float x;
            do{
                Scanner scan = new Scanner(System.in);
                x = scan.nextFloat();

                if(x <= getSaldo())
                    valorAposta = x;
                else
                    System.out.println("Valor da aposta excede o saldo, insira um valor mais baixo.");
            }while(x > getSaldo());
        }catch(Exception e){
            System.out.println("erro: " + e);
        }

        int x = somarFacesSorteadas(getDados());

        System.out.println("Resultado: " + x + "\n");
        if(x == 7 || x == 11){
            System.out.println("Jogador ganhou");
            setSaldo(getSaldo() + valorAposta);
        }
        else if(x == 2 || x == 3 || x == 12){
            System.out.println("Jogador perdeu");
            setSaldo(getSaldo() - valorAposta);
        }
        
        else{
            int valorBusca = 0;
            do{
                valorBusca = x;
                System.out.println("valor a buscar: " + x);
                rolarDados();
                System.out.println("Dados: " + toString());
                x = somarFacesSorteadas(getDados());
                System.out.println("Resultado: " + x + "\n");
                if(x == valorBusca){
                    System.out.println("jogador ganhou");
                    setSaldo(getSaldo() + valorAposta);
                }
                else if(x == 2 || x == 3 || x == 12){
                    setSaldo(getSaldo() - valorAposta);
                    System.out.println("Jogador perdeu");
                }
            }while(x != valorBusca && x != 2 && x != 3 && x != 12);
        }
    } 
}
