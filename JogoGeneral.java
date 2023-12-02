import java.util.Scanner;

public class JogoGeneral extends JogoDados{
    private int valoresJogadas[];
    private int resultado;
    private boolean ganhou;

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

        switch(jogada){
            case 1:
                for(int i = 0; i < 5; i++)
                    if(getDados()[i].getSideUp() == 1)
                        resultado += getDados()[i].getSideUp();
                break;
            case 2:
                for(int i = 0; i < 5; i++)
                    if(getDados()[i].getSideUp() == 2){
                        System.out.println("voce escolhou a jogada 2");
                        resultado += 2;
                    }
                break;
            case 3:
                for(int i = 0; i < 5; i++)
                    if(getDados()[i].getSideUp() == 3)
                        resultado += 3;
                break;
            case 4:
                for(int i = 0; i < 5; i++)
                    if(getDados()[i].getSideUp() == 4)
                        resultado += 4;
                break;
            case 5:
                for(int i = 0; i < 5; i++)
                    if(getDados()[i].getSideUp() == 5)
                        resultado += 5;
                break;
            case 6:
                for(int i = 0; i < 5; i++)
                    if(getDado(i).getSideUp() == 6)
                        resultado += 6;
                break;
            case 7:
                boolean trinca = false;
                for(int i = 1; i <= 6; i++){
                    int contagem = 0;

                    for(int j = 0; j < 5; j++)
                        if(getDados()[j].getSideUp() == i + 1)
                            contagem++;
                        
                        if(contagem >= 3)
                            trinca = true;
                }
                if(trinca == true)
                    for(int i = 0; i < 5; i++)
                        resultado += getDados()[i].getSideUp();
                break;
            case 8:
                boolean quadra = false;
                for(int i = 1; i <= 6; i++){
                    int contagem = 0;

                    for(int j = 0; j < 5; j++)
                        if(getDado(j).getSideUp() == i)
                            contagem++;
                        
                        if(contagem >= 4)
                            quadra = true;
                }
                if(quadra == true)
                    for(int i = 0; i < 5; i++)
                        resultado += getDado(i).getSideUp();
                break;
            case 9:
                trinca = false;
                boolean par = false;

                for(int i = 1; i <= 6; i++){
                    int contagem = 0;

                    for(int j = 0; j < 5; j++)
                        if(getDado(j).getSideUp() == i)
                            contagem++;
                    
                    if(contagem == 3)
                        trinca = true;
                    else if(contagem == 2)
                        par = true;
                }

                if(trinca == true && par == true)
                    resultado = 25;
                break;
            case 10:
                ordenarDados();
                int highSeq = 0;

                if(getDado(0).getSideUp() == 2)
                    highSeq++;
                
                for(int i = 0; i < 4; i++){
                    if(getDado(i).getSideUp() == (getDado(i + 1).getSideUp() - 1))
                        highSeq++;
                }

                if(highSeq == 5)
                    resultado = 30;
                break;
            case 11:
                ordenarDados();
                int lowSeq = 0;

                if(getDado(0).getSideUp() == 1)
                    lowSeq++;
                
                for(int i = 0; i < 4; i++){
                    if(getDado(i).getSideUp() == (getDado(i + 1).getSideUp() - 1))
                        lowSeq++;
                }

                if(lowSeq == 5)
                    resultado = 30;
                break;
            case 12:
                boolean general = false;

                for(int i = 0; i < 6; i++){
                    int contagem = 0;

                    for(int j = 0; j < 5; j++)
                        if(getDado(j).getSideUp() == i)
                            contagem++;

                    if(contagem == 5)
                        general = true;
                }

                if(general)
                    resultado = 50;
                break;
            case 13:
                for(int i = 0; i < 5; i++)
                    resultado += getDado(i).getSideUp();
                break;
            default:
                System.out.println("Opcao invalida!");
                break;
        }

        if(resultado == 0)
            System.out.println("Os dados nao cumprem o requisito para marcar esta jogada!");

        valoresJogadas[jogada - 1] = resultado;
    }

    public void ordenarDados(){
        int aux;

        for(int i = 0; i < 5; i++)
            for(int j = 0; j < 4; j++)
                if(getDados()[j].getSideUp() > getDados()[j + 1].getSideUp()){
                    aux = getDados()[j].getSideUp();
                    getDados()[j].setSideUp(getDados()[j + 1].getSideUp());
                    getDados()[j + 1].setSideUp(aux);
                }
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

    public void ganhou(){
        int soma12 = 0;

        for(int i = 0; i < 13; i++){
            soma12 += getJogadas(i);
        }

        System.out.println("Soma das 12 jogadas: " + soma12);
        System.out.println("Dobro da ultima jogada: " + getJogadas(12) * 2);

        if(soma12 > (valoresJogadas[12] * 2))
            ganhou = true;
        else
            ganhou = false;
    }

    public boolean getGanhou(){
        return ganhou;
    }
}
