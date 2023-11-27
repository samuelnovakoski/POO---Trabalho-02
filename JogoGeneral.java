import java.util.Scanner;

public class JogoGeneral extends JogoDados{
    private int valoresJogadas[];
    private int resultado;

    public JogoGeneral(){
        super("JogoGeneral", 5);

        valoresJogadas = new int[13];

        for(int i = 0; i < 13; i++)
            valoresJogadas[i] = -1;
    }

    public int[] getJogadas(){
        return valoresJogadas;
    }

    public int getJogadas(int pos){
        return valoresJogadas[pos];
    }

    public void validarJogada(int jogada){
        Scanner scan = new Scanner(System.in);

        try{
            while(jogada < 1 || jogada > 13 || valoresJogadas[jogada -1] != -1){
                if(jogada < 1 || jogada > 13)
                    System.out.println("Jogada invalida! Por favor insira uma jogada valida [1 - 13]");
                else if(valoresJogadas[jogada - 1] != -1)
                    System.out.println("Essa jogada ja foi feita, por favor escolha uma outra jogada que ainda nao foi realizada");
                
                jogada = scan.nextInt();
            }

            pontuarJogada(jogada);
        }catch(java.util.InputMismatchException e){
            System.out.println("Insira apenas numeros inteiros");
        }catch(Exception e){
            System.out.println("erro: " + e);
        }
    }

    public void pontuarJogada(int jogada){
        resultado = 0;

        // switch(jogada){
        //     case 1:
        //         for(int i = 0; i < 5; i++)
        //             if(getDados()[i].getSideUp() == 1)
        //                 resultado += 1;
        //         break;
        //     case 2:
        //         for(int i = 0; i < 5; i++)
        //             if(getDados()[i].getSideUp() == 2)
        //                 resultado += 2;
        //         break;
        //     case 3:
        //         for(int i = 0; i < 5; i++)
        //             if(getDados()[i].getSideUp() == 3)
        //                 resultado += 3;
        //         break;
        //     case 4:
        //         for(int i = 0; i < 5; i++)
        //             if(getDados()[i].getSideUp() == 4)
        //                 resultado += 4;
        //         break;
        //     case 5:
        //         for(int i = 0; i < 5; i++)
        //             if(getDados()[i].getSideUp() == 5)
        //                 resultado += 5;
        //         break;
        //     case 6:
        //         for(int i = 0; i < 5; i++)
        //             if(getDado(i).getSideUp() == 6)
        //                 resultado += 6;
        //         break;
        // }

        for (int i = 0; i < 5; i++) {
            if (getDados()[i].getSideUp() == jogada) {
                resultado += jogada;
            }
        }

        valoresJogadas[jogada - 1] = resultado;
    }

    public void mostrarJogada(){
        for(int i = 0; i < 13; i++){
            if(getJogadas()[i] == -1)
                System.out.print("-\t");
            else
                System.out.print(getJogadas(i) + "\t");
        }

        System.out.println("\n");
    }
}
