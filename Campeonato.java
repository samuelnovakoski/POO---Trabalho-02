import java.util.Scanner;

public class Campeonato {
    private Jogador[] jogadores;
    private int qntJogadores;

    public Campeonato(){
        jogadores = new Jogador[10];
        qntJogadores = 0;
    }

    public void incluirJogador(){
        Scanner scan = new Scanner(System.in);
        int cont = 0;
        String nome;
        String aux;

        if(qntJogadores < 10){
            for(int i = 0; i < jogadores.length; i++)
                if(jogadores[i] == null && cont == 0){
                    System.out.print("Insira o nome do jogador: ");
                    nome = scan.nextLine();

                    try{
                        do{
                            System.out.print("Insira o tipo do jogador [H = Humano || M = maquina]: ");
                            aux = scan.nextLine();

                            if(aux.charAt(0) == 'h'|| aux.charAt(0) == 'H'){
                                String cpf, agencia, conta;
                                int numBanco;

                                System.out.print("Insira o CPF: ");
                                cpf = scan.nextLine();
                                
                                System.out.print("Insira a agencia: ");
                                agencia = scan.nextLine();

                                System.out.print("Insira a conta: ");
                                conta = scan.nextLine();

                                System.out.print("Insira o numero do banco: ");
                                numBanco = scan.nextInt();

                                jogadores[i] = new Humano(nome, cpf, agencia, conta, numBanco);
                                jogadores[i].setSaldo(100);
                                qntJogadores++;
                            }
                            else if(aux.charAt(0) == 'm'){
                                jogadores[i] = new Maquina(nome);
                                jogadores[i].setSaldo(100);
                                qntJogadores++;
                            }
                            else
                                System.out.println("Tipo de jogador invalido!\nPor favor insira um tipo valido [H = Humano || M = Maquina]: ");
                        }while(aux.charAt(0) != 'h' && aux.charAt(0) != 'H' && aux.charAt(0) != 'm');
                        
                        cont++;
                        System.out.println("\nJogador " + nome + " adicionado com sucesso");
                    }catch(Exception e){
                        System.out.println("Erro: " + e);
                    }
                }
        }else
            System.out.println("Numero maximo de jogadores atingido, nao e possivel adicionar novos jogadores.");
    }

    public void removerJogador(){
        if(qntJogadores > 0){
            Scanner scan = new Scanner(System.in);

            listaJogadores();

            System.out.print("\nInsira o nome do jogador que deseja remover: ");
            String nome = scan.nextLine();
            int removido = 0;

            for(int i = 0; i < qntJogadores; i++)
                if(jogadores[i].getNome().equals(nome)){
                    for(int j = i; j < qntJogadores; j++)
                        jogadores[j] = jogadores[j + 1];
                    
                    jogadores[qntJogadores - 1] = null;
                    qntJogadores--;
                    removido++;

                    System.out.println("\nJogador " + nome + " foi removido com sucesso!");
                }
            if(removido == 0)
                System.out.println("\nNao foi possivel encontrar o jogador " + nome);
        }
        else
            System.out.println("Nao existem jogadores no campeonato no momento.");
    }

    public void listaJogadores(){
        for(int i = 0; i < qntJogadores; i++){
            if(i < qntJogadores - 1)
                System.out.print(jogadores[i].getNome() + " - ");
            else
                System.out.println(jogadores[i].getNome());
        }
    }

    public void iniciarCampeonato(){
        if(qntJogadores > 0){
            int i;

            for(i = 0; i < qntJogadores; i++){
                if(jogadores[i] instanceof Humano){

                    Humano h = (Humano) jogadores[i];

                    int tipoJogo = h.escolherJogo();

                    if(tipoJogo == 1)
                        jogoG(h);
                    else{
                        jogoA(h);
                    }
                }
            }
        }
    }

    public void jogoG(Humano jogador){
        JogoGeneral novoJogo = new JogoGeneral();

        jogador.setJogo(novoJogo, jogador.getNJogo());

        fazerAposta(jogador, novoJogo);

        for(int i = 0; i < 13; i++){
            System.out.println("Jogadas: " + i + "\n");
            jogador.jogarDados(1);
            System.out.println("Dados: " + novoJogo.toString());
            jogador.escolherJogada(novoJogo);
        }

        jogador.setNJogo(jogador.getNJogo() + 1);
    }

    public void jogoA(Humano jogador){
        JogoAzar novoJogo = new JogoAzar();

        jogador.setJogo(novoJogo, jogador.getNJogo());
        fazerAposta(jogador, novoJogo);

        jogador.jogarDados(2); 
        novoJogo.executarRegrasJogo();
        
        if(novoJogo.getResultado() == 1){
            jogador.setSaldo(jogador.getSaldo() + novoJogo.getAposta());
        }
        else if(novoJogo.getResultado() == 0){
            jogador.setSaldo(jogador.getSaldo() - novoJogo.getAposta());
        }

        System.out.println("\nSaldo: R$" + jogador.getSaldo());

        jogador.setNJogo(jogador.getNJogo() + 1);

    }

    public void fazerAposta(Jogador jogador, JogoDados jogo){
        Scanner scan = new Scanner(System.in);

        System.out.println("Saldo disponivel: R$" + jogador.getSaldo());
        System.out.print("Insira o valor que deseja apostar: R$");
        try{
            float aposta;

            do{
                aposta = scan.nextFloat();

                if(aposta <= jogador.getSaldo() && aposta > 0)
                    jogo.setAposta(aposta);
                else if(aposta <= 0)
                    System.out.println("Aposta invalida, o valor da aposta deve ser maior que 0!");
                else
                    System.out.println("Valor da aposta excedo o saldo suficiente. Insira um valor mais baixo.");
            }while(aposta > jogador.getSaldo() && aposta <= 0);
        }catch(Exception e){
            System.out.println("Erro: " + e);
        }
    }

    public void imprimirSaldo(){
        Scanner scan = new Scanner(System.in);
        String aux;
        int cont = 0;

        if(qntJogadores > 0){
            System.out.println("\nInsira o tipo desejado para imprimir os saldos [H para Humano, M para maquina ou T para Todos]: ");
            aux = scan.nextLine().toUpperCase();

            for(int i = 0; i < qntJogadores; i++){
                if(aux.charAt(0) == 'H'){
                    if(jogadores[i] instanceof Humano){
                        System.out.println("\nJogador [H]: " + jogadores[i].getNome() + ", Saldo: " + jogadores[i].getSaldo());
                        cont++;
                    }
                    else{
                        if(cont == 0 && i == qntJogadores - 1)
                            System.out.println("\nNao existem jogadores humanos registrados");
                    }
                }
                else if(aux.charAt(0) == 'M'){
                    if(jogadores[i] instanceof Maquina){
                        System.out.println("\nJogador [M]: " + jogadores[i].getNome() + ", Saldo: " + jogadores[i].getSaldo());
                        cont++;
                    }
                    else{
                        if(cont == 0 && i == qntJogadores - 1)
                            System.out.println("\nNao existem jogadores maquina registrados");
                    }
                }
                else if(aux.charAt(0) == 'T'){
                    System.out.println("\nJogador [" + aux + "]: " + jogadores[i].getNome() + ", Saldo: " + jogadores[i].getSaldo());
                }
                else{
                    System.out.println("\nTipo invalido, digite novamente\n");
                }
            }
        }
        else{
            System.out.println("\nNumero de jogadores insuficiente para executar esse comando!\n");
        }
    }
}