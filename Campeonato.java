import java.util.Scanner;

public class Campeonato {
    private Jogador[] jogadores = new Jogador[10];
    private int qntJogadores = 0;

    public void incluirJogador(){
        Scanner scan = new Scanner(System.in);
        int cont = 0;
        String nome;
        String aux;

        if(qntJogadores < 10){
            for(int i = 0; i < jogadores.length; i++)
                if(jogadores[i] == null && cont == 0){
                    System.out.println("Insira o nome do jogador: ");
                    nome = scan.nextLine();
                    try{
                        do{
                            System.out.println("Insira o tipo do jogador [H para Humano ou M para maquina]: ");
                            aux = scan.nextLine().toUpperCase();

                            if(aux.charAt(0) == 'H'){
                                String cpf;
                                String agencia;
                                String conta;
                                int numeroBanco;

                                System.out.print("Insira o CPF: ");
                                cpf = scan.nextLine();
                                System.out.print("Insira a agencia: ");
                                agencia = scan.nextLine();
                                System.out.print("Insira a conta: ");
                                conta = scan.nextLine();
                                System.out.print("Insira o numero do banco: ");
                                numeroBanco = scan.nextInt();

                                jogadores[i] = new Humano( nome, cpf, agencia, conta, numeroBanco);
                                qntJogadores++;
                            }
                            else if(aux.charAt(0) == 'M'){
                                jogadores[i] = new Maquina(nome);
                                qntJogadores++;
                            }
                            else
                                System.out.println("Tipo inserido invalido! Por favor insira um tipo valido.");
                        }while(aux.charAt(0) != 'H' && aux.charAt(0) != 'M');
                        cont++;
                    }catch(Exception e){
                        System.out.println("erro: " + e);
                    }
                }
        }else
            System.out.println("Numero maximo de jogadores ja foi atingido, nao e possivel adicionar novos jogadores");
    }

    public void removerJogador(){
        if(qntJogadores > 0){
        Scanner scan = new Scanner(System.in);
        listaJogadores();
        System.out.print("\nInforme o nome do jogador que deseja remover: ");
        
        String nome = scan.nextLine();
        int removido = 0;

        for(int i = 0; i < qntJogadores; i++)
            if(jogadores[i].getNome().equals(nome)){
                for(int j = i; j < qntJogadores; j++)
                    jogadores[j] = jogadores[j + 1];
                
                jogadores[qntJogadores - 1] = null;
                qntJogadores--;
                removido++;

                System.out.println("\nJogador " + nome + " foi removido com sucesso!\n");

            }
            if(removido == 0)
                System.out.println("\nNao foi possivel encontrar o jogador " + nome);
    }
    else
        System.out.println("Nao existem jogadores cadastrados no momento!");
    }

    public void listaJogadores(){
        for(int i = 0; i < qntJogadores; i++)
            if(i < qntJogadores - 1)
                System.out.print(jogadores[i].getNome() + " - ");
            else
                System.out.println(jogadores[i].getNome());
    }

    public void iniciarCampeonato(){
        if(qntJogadores > 0){
            int i;

            for(i = 0; i < qntJogadores; i++){
                if(jogadores[i] instanceof Humano){
                    Humano h = (Humano) jogadores[i];

                    int tipoJogo = h.escolherJogo();

                    jogadores[i].jogarDados(tipoJogo);
                }
                else{
                    Maquina m = (Maquina) jogadores[i];
                }
            }
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
