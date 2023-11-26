import java.util.Scanner;

public class JogoGeneral extends JogoDados{
    private int valoresJogadas[] = new int[13];
    private int resultado;

    public JogoGeneral(){
        super("JogoGeneral", 5);

        for(int i = 0; i < 13; i++)
            valoresJogadas[i] = -1;
    }

    // public String toString(){
    //     return "";
    // }

    public int[] getJogadas(){
        return valoresJogadas;
    }

    public void validarJogada(int x){
        Scanner scanner = new Scanner(System.in);
        try{
            while(x < 1 || x > 13 || valoresJogadas[x - 1] != -1){
                if(x < 1 || x > 13)
                    System.out.println("jogada invalida por favor insira uma jogada valida [1 - 13]");
                else if(valoresJogadas[x - 1] != -1)
                    System.out.println("Essa jogada ja foi feita, por favor insira uma jogada que ainda nao foi realizada");
                
                x = scanner.nextInt();
            }
            pontuarJogada(x);
        } catch(java.util.InputMismatchException e){
            System.out.println("Insira apenas valores inteiros");
        }
    }

    public void pontuarJogada(int x){
        resultado = 0;

        switch(x){
            case 1:
                for(int i = 0; i < 5; i++)
                    if(getDados()[i].getSideUp() == 1)
                        resultado += 1;
        }

        valoresJogadas[x] = resultado;
    }




}