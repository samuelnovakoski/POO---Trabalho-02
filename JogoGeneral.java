public class JogoGeneral extends JogoDados{
    private int valoresJogadas[] = new int[13];
    private int resultado;

    public JogoGeneral(){
        super("JogoGeneral", 5);

        for(int i = 0; i < 13; i++)
            valoresJogadas[i] = -1;
    }

    public String toString(){
        return "";
    }

    public void validarJogada(int x){
        Scanner scanner = new Scanner(System.in);
        
        while(x < 1 || x > 13 || valoresJogadas[x - 1] != -1){
            if(x < 1 || x > 13)
                System.out.println("jogada invalida por favor insira uma jogada valida [1 - 13]");
            else if(valoresJogadas[x - 1] != -1)
                System.out.println("Essa jogada ja foi feita, por favor insira uma jogada que ainda nao foi realizada");
            
            x = scanner.nextInt();
        }
        pontuarJogada(x);
    }

    public void pontuarJogada(int x){
        resultado = 0;

        switch(x){
            case 1:
                for(int i = 0; i < 5; i++)
                    if(super.getDado(i).getSideUp() == 1)
                        resultado += 1;
        }

        valoresJogadas[x] = resultado;
    }




}