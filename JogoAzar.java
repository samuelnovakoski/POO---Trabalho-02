public class JogoAzar extends JogoDados{
    private float valorAposta;

    public JogoAzar(){
        super("JogoAzar", 2);
    }

    public void executarRegrasJogo(){
        int x = somarFacesSorteadas(getDados());

        if(x == 7 || x == 11)
            System.out.println("Jogador ganhou");
        else if(x == 2 || x == 3 || x == 12)
            System.out.println("Jogador perdeu");
        else{
            int valorBusca = x;
            do{
                rolarDados();
                x = somarFacesSorteadas(getDados());
                if(x == valorBusca)
                    System.out.println("jogador ganhou");
            }while(x != valorBusca || x != 2 || x != 3 || x != 12);
        }
    } 
}
