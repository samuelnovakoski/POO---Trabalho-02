import java.util.Scanner;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.ObjectInputStream;
import java.io.Serializable;

public class Campeonato implements Serializable{
    private Jogador[] jogadores;
    private int qntJogadores;
    private File file;

    public Campeonato(){
        jogadores = new Jogador[10];
        qntJogadores = 0;
        file = new File("Campeonato.dat");
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
                if(jogadores[i].getSaldo() > 0 && jogadores[i].getNJogo() < 10){
                    if(jogadores[i] instanceof Humano){
                        Humano h = (Humano) jogadores[i];
                        int tipoJogo = h.escolherJogo();

                        if(tipoJogo == 1){
                            JogoGeneral novoJogo = new JogoGeneral();

                            h.setJogo(novoJogo, h.getNJogo());
                            fazerAposta(h, novoJogo);

                            for(int j = 0; j < 13; j++){
                                h.jogarDados(1);
                                System.out.println("Dados: " + novoJogo.toString());
                                h.escolherJogada(novoJogo);
                            }
                        
                            if(novoJogo.getResultado() == 1){
                                h.setSaldo(h.getSaldo() + novoJogo.getAposta());
                                System.out.println("Parabens voce ganhou!");
                                System.out.println("Saldo: " + String.format("%.2f", h.getSaldo()));
                            }
                            else{
                                h.setSaldo(h.getSaldo() - novoJogo.getAposta());
                                System.out.println("Que pena, voce perdeu!");
                                System.out.println("Saldo: " + String.format("%.2f", h.getSaldo()));
                            }

                            h.setNJogo(h.getNJogo() + 1);
                        }
                        else{
                            JogoAzar novoJogo = new JogoAzar();

                            h.setJogo(novoJogo, h.getNJogo());
                            fazerAposta(h, novoJogo);

                            h.jogarDados(2); 
                            novoJogo.executarRegrasJogo();
                            
                            if(novoJogo.getResultado() == 1){
                                h.setSaldo(h.getSaldo() + novoJogo.getAposta());
                            }
                            else if(novoJogo.getResultado() == 0){
                                h.setSaldo(h.getSaldo() - novoJogo.getAposta());
                            }

                            System.out.println("\nSaldo: R$" + String.format("%.2f", h.getSaldo()));
                            h.setNJogo(h.getNJogo() + 1);
                        }
                    }
                    else{
                        Maquina m = (Maquina) jogadores[i];
                        m.aplicarEstrategia();
                    }
                }
                else if(jogadores[i].getSaldo() <= 0)
                    System.out.println("O jogador " + jogadores[i].getNome() + " nao possui saldo suficiente para fazer uma aposta");
                else
                    System.out.println("O jogador " + jogadores[i].getNome() + " ja fez suas 10 jogadas");
            }
        }
        else
            System.out.println("Numero de jogadores insuficiente para executar esse comando!");
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
                    System.out.println("Valor da aposta excede o saldo suficiente. Insira um valor mais baixo.");
            }while(aposta > jogador.getSaldo() || aposta <= 0);
        }catch(Exception e){
            System.out.println("Erro: " + e);
        }
    }

    public void imprimirSaldo(){
        Scanner scan = new Scanner(System.in);
        String aux = "";
        int cont = 0;

        if(qntJogadores > 0){
            System.out.println("\nInsira o tipo desejado para imprimir os saldos [H para Humano, M para maquina ou T para Todos]: ");
            try{
                do{
                    aux = scan.nextLine().toUpperCase();
                    if(aux.charAt(0) != 'H' && aux.charAt(0) != 'M' && aux.charAt(0)  != 'T')
                        System.out.println("\nTipo invalido, digite novamente\n");
                }while(aux.charAt(0) != 'H' && aux.charAt(0) != 'M' && aux.charAt(0)  != 'T');
            }catch(Exception e){
                System.out.println("\nError: " + e);
            }

            for(int i = 0; i < qntJogadores; i++){
                if(aux.charAt(0) == 'H'){
                    if(jogadores[i] instanceof Humano){
                        System.out.println("\nJogador [H]: " + jogadores[i].getNome() + ", Saldo: " + String.format("%.2f", jogadores[i].getSaldo()));
                        cont++;
                    }
                    else
                        if(cont == 0 && i == qntJogadores - 1)
                            System.out.println("\nNao existem jogadores humanos registrados");
                }
                else if(aux.charAt(0) == 'M'){
                    if(jogadores[i] instanceof Maquina){
                        System.out.println("\nJogador [M]: " + jogadores[i].getNome() + ", Saldo: " + String.format("%.2f", jogadores[i].getSaldo()));
                        cont++;
                    }
                    else
                        if(cont == 0 && i == qntJogadores - 1)
                            System.out.println("\nNao existem jogadores maquina registrados");
                }
                else if(aux.charAt(0) == 'T'){
                    if(jogadores[i] instanceof Humano)
                        System.out.println("\nJogador [H]: " + jogadores[i].getNome() + ", Saldo: " + String.format("%.2f", jogadores[i].getSaldo()));
                    else
                        System.out.println("\nJogador [M]: " + jogadores[i].getNome() + ", Saldo: " + String.format("%.2f", jogadores[i].getSaldo()));
                }
            }
        }
        else
            System.out.println("\nNumero de jogadores insuficiente para executar esse comando!\n");
    }

    public void imprimirExtratos(){
        Scanner scan = new Scanner(System.in);
        String tipoJogo = "", tipoJogador = "";
        int contJ = 0, contG = 0;

        if(qntJogadores > 0){
            System.out.print("\nPara qual tipo de jogo deseja imprimir os extratos? [G para Jogo General, A para Jogo de Azar ou T para Todos]: ");
            try{
                do{
                    tipoJogo = scan.nextLine().toUpperCase();
                    if(tipoJogo.charAt(0) != 'G' && tipoJogo.charAt(0) != 'A' && tipoJogo.charAt(0) != 'T')
                        System.out.println("\nJogo invalido, digite novamente\n");
                }while(tipoJogo.charAt(0) != 'G' && tipoJogo.charAt(0) != 'A' && tipoJogo.charAt(0) != 'T');
            }catch(Exception e){
                System.out.println("\nError: " + e);
            }

            System.out.print("\nPara qual tipo de jogador deseja imprimir os extratos? [H para Humano, M para Maquina ou T para Todos]: ");
            try{
                do{
                    tipoJogador = scan.nextLine().toUpperCase();
                    if(tipoJogador.charAt(0) != 'H' && tipoJogador.charAt(0) != 'M' && tipoJogador.charAt(0) != 'T')
                        System.out.println("\nTipo invalido, digite novamente\n");
                }while(tipoJogador.charAt(0) != 'H' && tipoJogador.charAt(0) != 'M' && tipoJogador.charAt(0) != 'T');
            }catch(Exception e){
                System.out.println("\nError: " + e);
            }

            for(int i = 0; i < qntJogadores; i++){
                if(tipoJogador.charAt(0) == 'H'){
                    if(jogadores[i] instanceof Humano){
                        System.out.println("\nJogador [H]: " + jogadores[i].getNome());
                        contJ++;
                        for(int j = 0; j < jogadores[i].getNJogo(); j++){
                            if(tipoJogo.charAt(0) == 'G'){
                                if(jogadores[i].getJogo(j) instanceof JogoGeneral){
                                    JogoGeneral auxG = (JogoGeneral) jogadores[i].getJogo(j);
                                    if(auxG.getResultado() == 1){
                                        System.out.println("[Jogo General] Valor apostado: R$" + String.format("%.2f", String.format("%.2f", auxG.getAposta())) + ", Resultado da jogada: Ganhou");
                                        contG++;
                                    }
                                    else{
                                        System.out.println("[Jogo General] Valor apostado: R$" + String.format("%.2f", auxG.getAposta()) + ", Resultado da jogada: Perdeu");
                                        contG++;
                                    }
                                }
                                else
                                    if(contG == 0 && j == jogadores[i].getNJogo() - 1)
                                        System.out.println("O jogador nao fez nenhum Jogo General");
                            }
                            else if(tipoJogo.charAt(0) == 'A'){
                                if(jogadores[i].getJogo(j) instanceof JogoAzar){
                                    JogoAzar auxA = (JogoAzar)jogadores[i].getJogo(j);
                                    if(auxA.getResultado() == 1){
                                        System.out.println("[Jogo de Azar] Valor apostado: R$" + String.format("%.2f", String.format("%.2f", auxA.getAposta())) + ", Resultado da Jogada: Ganhou");
                                        contG++;
                                    }
                                    else{
                                        System.out.println("[Jogo de Azar] Valor apostado: R$" + String.format("%.2f", auxA.getAposta()) + ", Resultado da Jogada: Perdeu");
                                        contG++;
                                    }
                                }
                                else
                                    if(contG == 0 && j == jogadores[i].getNJogo() - 1)
                                        System.out.println("O jogador nao fez nenhum Jogo de Azar");
                            }
                            else if(tipoJogo.charAt(0) == 'T'){
                                if(jogadores[i].getJogo(j) instanceof JogoGeneral){
                                    JogoGeneral auxG = (JogoGeneral)jogadores[i].getJogo(j);
                                    if(auxG.getResultado() == 1)
                                        System.out.println("[Jogo General] Valor apostado: R$" + String.format("%.2f", auxG.getAposta()) + ", Resultado da Jogada: Ganhou");
                                    else
                                        System.out.println("[Jogo General] Valor apostado: R$" + String.format("%.2f", auxG.getAposta()) + ", Resultado da jogada: Perdeu");
                                }
                                else{
                                    JogoAzar auxA = (JogoAzar)jogadores[i].getJogo(j);
                                    if(auxA.getResultado() == 1)
                                        System.out.println("[Jogo de Azar] Valor apostado: R$" + String.format("%.2f", auxA.getAposta()) + ", Resultado da Jogada: Ganhou");
                                    else
                                        System.out.println("[Jogo de Azar] Valor apostado: R$" + String.format("%.2f", auxA.getAposta()) + ", Resultado da jogada: Perdeu");
                                }
                            }
                        }
                    }
                    else
                        if(contJ == 0 && i == qntJogadores - 1)
                            System.out.println("\nNao existem jogadores humanos registrados");
                }
                else if(tipoJogador.charAt(0) == 'M'){
                    if(jogadores[i] instanceof Maquina){
                        System.out.println("\nJogador [M]: " + jogadores[i].getNome());
                        contJ++;
                        for(int j = 0; j < jogadores[i].getNJogo(); j++){
                            if(tipoJogo.charAt(0) == 'G'){
                                if(jogadores[i].getJogo(j) instanceof JogoGeneral){
                                    JogoGeneral auxG = (JogoGeneral)jogadores[i].getJogo(j); 
                                    if(auxG.getResultado() == 1){
                                        System.out.println("[Jogo General] Valor apostado: R$" + String.format("%.2f", auxG.getAposta()) + ", Resultado da jogada: Ganhou");
                                        contG++;
                                    }
                                    else{
                                        System.out.println("[Jogo General] Valor apostado: R$" + String.format("%.2f", auxG.getAposta()) + ", Resultado da jogada: Perdeu");
                                        contG++;
                                    }
                                }
                                else
                                    if(contG == 0 && j == jogadores[i].getNJogo() - 1)
                                        System.out.println("O jogador nao fez nenhum Jogo General");
                            }
                            else if(tipoJogo.charAt(0) == 'A'){
                                if(jogadores[i].getJogo(j) instanceof JogoAzar){
                                    JogoAzar auxA = (JogoAzar)jogadores[i].getJogo(j);
                                    if(auxA.getResultado() == 1){
                                        System.out.println("[Jogo de Azar] Valor apostado: R$" + String.format("%.2f", auxA.getAposta()) + ", Resultado da Jogada: Ganhou");
                                        contG++;
                                    }
                                    else{
                                        System.out.println("[Jogo de Azar] Valor apostado: R$" + String.format("%.2f", auxA.getAposta()) + ", Resultado da Jogada: Perdeu");
                                        contG++;
                                    }
                                }
                                else
                                    if(contG == 0 && j == jogadores[i].getNJogo() - 1)
                                        System.out.println("O jogador nao fez nenhum Jogo de Azar");
                            }
                            else if(tipoJogo.charAt(0) == 'T'){
                                if(jogadores[i].getJogo(j) instanceof JogoGeneral){
                                    JogoGeneral auxG = (JogoGeneral)jogadores[i].getJogo(j);
                                    if(auxG.getResultado() == 1)
                                        System.out.println("[Jogo General] Valor apostado: R$" + String.format("%.2f", auxG.getAposta()) + ", Resultado da Jogada: Ganhou");
                                    else
                                        System.out.println("[Jogo General] Valor apostado: R$" + String.format("%.2f", auxG.getAposta()) + ", Resultado da jogada: Perdeu");
                                }
                                else{
                                    JogoAzar auxA = (JogoAzar)jogadores[i].getJogo(j);
                                    if(auxA.getResultado() == 1)
                                        System.out.println("[Jogo de Azar] Valor apostado: R$" + String.format("%.2f", auxA.getAposta()) + ", Resultado da Jogada: Ganhou");
                                    else
                                        System.out.println("[Jogo de Azar] Valor apostado: R$" + String.format("%.2f", auxA.getAposta()) + ", Resultado da jogada: Perdeu");
                                }
                            }
                        }
                    }
                    else
                        if(contJ == 0 && i == qntJogadores -1)
                            System.out.println("\nNao existem jogadores maquina registrados");
                }
                else if(tipoJogador.charAt(0) == 'T'){
                    contG = 0;
                    if(jogadores[i] instanceof Humano){
                        System.out.println("\nJogador [H]: " + jogadores[i].getNome());

                        for(int j = 0; j < jogadores[i].getNJogo(); j++){
                            if(tipoJogo.charAt(0) == 'G'){
                                if(jogadores[i].getJogo(j) instanceof JogoGeneral){
                                    JogoGeneral auxG = (JogoGeneral)jogadores[i].getJogo(j);
                                    if(auxG.getResultado() == 1){
                                        System.out.println("[Jogo General] Valor apostado: R$" + String.format("%.2f", auxG.getAposta()) + ", Resultado da jogada: Ganhou");
                                        contG++;
                                    }
                                    else{
                                        System.out.println("[Jogo General] Valor apostado: R$" + String.format("%.2f", auxG.getAposta()) + ", Resultado da jogada: Perdeu");
                                        contG++;
                                    }
                                }
                                else
                                    if(contG == 0 && j == jogadores[i].getNJogo() - 1)
                                        System.out.println("O jogador nao fez nenhum Jogo General");
                            }
                            else if(tipoJogo.charAt(0) == 'A'){
                                if(jogadores[i].getJogo(j) instanceof JogoAzar){
                                    JogoAzar auxA = (JogoAzar)jogadores[i].getJogo(j);
                                    if(auxA.getResultado() == 1){
                                        System.out.println("[Jogo de Azar] Valor apostado: R$" + String.format("%.2f", auxA.getAposta()) + ", Resultado da Jogada: Ganhou");
                                        contG++;
                                    }
                                    else{
                                        System.out.println("[Jogo de Azar] Valor apostado: R$" + String.format("%.2f", auxA.getAposta()) + ", Resultado da Jogada: Perdeu");
                                        contG++;
                                    }
                                }
                                else
                                    if(contG == 0 && j == jogadores[i].getNJogo() - 1)
                                        System.out.println("O jogador nao fez nenhum Jogo de Azar");
                            }
                            else if(tipoJogo.charAt(0) == 'T'){
                                if(jogadores[i].getJogo(j) instanceof JogoGeneral){
                                    JogoGeneral auxG = (JogoGeneral)jogadores[i].getJogo(j);
                                    if(auxG.getResultado() == 1)
                                        System.out.println("[Jogo General] Valor apostado: R$" + String.format("%.2f", auxG.getAposta()) + ", Resultado da Jogada: Ganhou");
                                    else
                                        System.out.println("[Jogo General] Valor apostado: R$" + String.format("%.2f", auxG.getAposta()) + ", Resultado da jogada: Perdeu");
                                }
                                else if(jogadores[i].getJogo(j) instanceof JogoAzar){
                                    JogoAzar auxA = (JogoAzar)jogadores[i].getJogo(j);
                                    if(auxA.getResultado() == 1)
                                        System.out.println("[Jogo de Azar] Valor apostado: R$" + String.format("%.2f", auxA.getAposta()) + ", Resultado da Jogada: Ganhou");
                                    else
                                        System.out.println("[Jogo de Azar] Valor apostado: R$" + String.format("%.2f", auxA.getAposta()) + ", Resultado da jogada: Perdeu");
                                }
                                else{
                                    System.out.println("Jogador nao fez nenhum jogo!");
                                }
                            }
                        }
                    }
                    else{
                        System.out.println("\nJogador [M]: " + jogadores[i].getNome());

                        for(int j = 0; j < jogadores[i].getNJogo(); j++){
                            if(tipoJogo.charAt(0) == 'G'){
                                if(jogadores[i].getJogo(j) instanceof JogoGeneral){
                                    JogoGeneral auxG = (JogoGeneral)jogadores[i].getJogo(j);
                                    if(auxG.getResultado() == 1){
                                        System.out.println("[Jogo General] Valor apostado: R$" + String.format("%.2f", auxG.getAposta()) + ", Resultado da jogada: Ganhou");
                                        contG++;
                                    }
                                    else{
                                        System.out.println("[Jogo General] Valor apostado: R$" + String.format("%.2f", auxG.getAposta()) + ", Resultado da jogada: Perdeu");
                                        contG++;
                                    }
                                }
                                else
                                    if(contG == 0 && j == jogadores[i].getNJogo() - 1)
                                        System.out.println("O jogador nao fez nenhum Jogo General");
                            }
                            else if(tipoJogo.charAt(0) == 'A'){
                                if(jogadores[i].getJogo(j) instanceof JogoAzar){
                                    JogoAzar auxA = (JogoAzar)jogadores[i].getJogo(j);
                                    if(auxA.getResultado() == 1){
                                        System.out.println("[Jogo de Azar] Valor apostado: R$" + String.format("%.2f", auxA.getAposta()) + ", Resultado da Jogada: Ganhou");
                                        contG++;
                                    }
                                    else{
                                        System.out.println("[Jogo de Azar] Valor apostado: R$" + String.format("%.2f", auxA.getAposta()) + ", Resultado da Jogada: Perdeu");
                                        contG++;
                                    }
                                }
                                else
                                    if(contG == 0 && j == jogadores[i].getNJogo() - 1)
                                        System.out.println("O jogador nao fez nenhum Jogo de Azar");
                            }
                            else if(tipoJogo.charAt(0) == 'T'){
                                if(jogadores[i].getJogo(j) instanceof JogoGeneral){
                                    JogoGeneral auxG = (JogoGeneral)jogadores[i].getJogo(j);
                                    if(auxG.getResultado() == 1)
                                        System.out.println("[Jogo General] Valor apostado: R$" + String.format("%.2f", auxG.getAposta()) + ", Resultado da Jogada: Ganhou");
                                    else
                                        System.out.println("[Jogo General] Valor apostado: R$" + String.format("%.2f", auxG.getAposta()) + ", Resultado da jogada: Perdeu");
                                }
                                else if(jogadores[i].getJogo(j) instanceof JogoAzar){
                                    JogoAzar auxA = (JogoAzar)jogadores[i].getJogo(j);
                                    if(auxA.getResultado() == 1)
                                        System.out.println("[Jogo de Azar] Valor apostado: R$" + String.format("%.2f", auxA.getAposta()) + ", Resultado da Jogada: Ganhou");
                                    else
                                        System.out.println("[Jogo de Azar] Valor apostado: R$" + String.format("%.2f", auxA.getAposta()) + ", Resultado da jogada: Perdeu");
                                }
                                else
                                    System.out.println("Jogador nao fez nenhum jogo!");
                                
                            }
                        }
                    }
                }
            }
        }
        else
            System.out.println("\nNumero de jogadores insuficiente para executar esse comando!\n");
    }

    public void imprimirEstatisticas(){
        Scanner scan = new Scanner(System.in);
        Scanner scan2 = new Scanner(System.in);
        int opcao = 0, cont = 0;
        String tipoJogador = "";
        JogoDados auxJD;
        int[] statTotal = new int[6];

        if(qntJogadores > 0){
            System.out.print("\nComo deseja imprimir as estatisticas? [1] Por tipo de jogador, [2] Por tipo de jogo de cada jogador, [3] Total por jogos, [4] Total do Campeonato: ");
            try{
                do{
                    opcao = scan.nextInt();
                    if(opcao != 1 && opcao != 2 && opcao != 3 && opcao != 4)
                        System.out.println("\nOpcao invalida, digite novamente\n");
                }while(opcao != 1 && opcao != 2 && opcao != 3 && opcao != 4);
            }catch(Exception e){
                System.out.println("\nError: " + e);
            }

            switch(opcao){
                case 1 :
                    System.out.println("\nPara qual tipo de jogador deseja imprimir? [H para Humano ou M para Maquina]: ");
                    try{
                        do{
                            tipoJogador = scan2.nextLine().toUpperCase();
                            if(tipoJogador.charAt(0) != 'H' && tipoJogador.charAt(0) != 'M')
                                System.out.println("\nTipo invalido, digite novamente\n");
                       }while(tipoJogador.charAt(0) != 'H' && tipoJogador.charAt(0) != 'M');
                    }catch(Exception e){
                        System.out.println("\nError: " + e);
                    }

                    int[] statJogador = new int[6];

                    if(tipoJogador.charAt(0) == 'H'){
                        System.out.println("\nEstatisticas para jogadores humanos: ");
                        for(int i = 0; i < qntJogadores; i++){
                            if(jogadores[i] instanceof Humano){
                                for(int j = 0; j < jogadores[i].getNJogo(); j++){
                                    auxJD = jogadores[i].getJogo(j);
                                    for(int k = 0; k < auxJD.getStatDados().length; k++){
                                        statJogador[k] += auxJD.getStatDados()[k];
                                    }
                                }
                            }
                            else
                                if(cont == 0 && i == qntJogadores - 1)
                                    System.out.println("\nNao existem jogadores humanos registrados");
                        }
                    }

                    if(tipoJogador.charAt(0) == 'M'){
                        System.out.println("\nEstatisticas para jogadores maquinas: ");
                        for(int i = 0; i < qntJogadores; i++){
                            if(jogadores[i] instanceof Maquina){
                                for(int j = 0; j < jogadores[i].getNJogo(); j++){
                                    auxJD = jogadores[i].getJogo(j);
                                    for(int k = 0; k < auxJD.getStatDados().length; k++){
                                        statJogador[k] += auxJD.getStatDados()[k];
                                    }
                                }
                            }
                            else
                                if(cont == 0 && i == qntJogadores - 1)
                                    System.out.println("\nNao existem jogadores maquinas registrados");
                        }
                    }   
                    
                    for(int k = 0; k < statJogador.length; k++){
                        System.out.println(k+1 + ": " + statJogador[k]);
                    }
                    break;
                case 2 :
                    for(int i = 0; i < qntJogadores; i++){
                        int[] statJG = new int[6];
                        int[] statJA = new int[6];

                        System.out.println("\nJogador: " + jogadores[i].getNome());
                        for(int j = 0; j < jogadores[i].getNJogo(); j++){
                            auxJD = jogadores[i].getJogo(j);
                            if(auxJD instanceof JogoGeneral){
                                for(int k = 0; k < auxJD.getStatDados().length; k++){
                                    statJG[k] += auxJD.getStatDados()[k];
                                }
                            }
                            else{
                                for(int k = 0; k < auxJD.getStatDados().length; k++){
                                    statJA[k] += auxJD.getStatDados()[k];
                                }
                            }
                        }
                        System.out.println("\nJogo General: ");
                        for(int k = 0; k < statJG.length; k++){
                            System.out.println(k+1 + ": " + statJG[k]);
                        }
                        System.out.println("\nJogo de Azar: ");
                        for(int k = 0; k < statJA.length; k++){
                            System.out.println(k+1 + ": " + statJA[k]);
                        }
                    }
                    break;
                case 3 :
                    int[] statJG = new int[6];
                    int[] statJA = new int[6];

                    for(int i = 0; i < qntJogadores; i++){
                        for(int j = 0; j < jogadores[i].getNJogo(); j++){
                            auxJD = jogadores[i].getJogo(j);
                            if(auxJD instanceof JogoGeneral){
                                for(int k = 0; k < auxJD.getStatDados().length; k++){
                                    statJG[k] += auxJD.getStatDados()[k];
                                }
                            }
                            else{
                                for(int k = 0; k < auxJD.getStatDados().length; k++){
                                    statJA[k] += auxJD.getStatDados()[k];
                                }
                            }
                        }
                    }
                    System.out.println("\nJogo General: ");
                    for(int k = 0; k < statJG.length; k++){
                        System.out.println(k+1 + ": " + statJG[k]);
                    }
                    System.out.println("\nJogo de Azar: ");
                    for(int k = 0; k < statJA.length; k++){
                        System.out.println(k+1 + ": " + statJA[k]);
                    }
                    break;
                case 4 :
                    System.out.println("\nEstatisticas totais do campeonato: ");
                    for(int i = 0; i< qntJogadores; i++){
                        for(int j = 0; j < jogadores[i].getNJogo(); j++){
                            auxJD = jogadores[i].getJogo(j);
                            for(int k = 0; k < auxJD.getStatDados().length; k++){
                                statTotal[k] += auxJD.getStatDados()[k];
                            }
                        }
                    }
                    for(int i = 0; i < 6; i++){
                        System.out.println(i+1 + ": " + statTotal[i]);
                    }
                    break;
                default: System.out.println("\nOpcao invalida, digite novamente\n");
            }
        }
        else
            System.out.println("Numero de jogadores insuficiente para executar esse comando!");
        
    }

    public void gravarEmArquivo(Campeonato camp){
        try{
            FileOutputStream fout = new FileOutputStream(file);
            ObjectOutputStream oos = new ObjectOutputStream(fout);

            oos.writeObject(camp);
            oos.flush();
            oos.close();
            fout.close();

            System.out.println("Dados gravados com sucesso!");
        }catch(Exception e){
            System.out.println("erro: " + e);
        }
    }

    public void lerDoArquivo(){
        try{
            FileInputStream fin = new FileInputStream(file);
            ObjectInputStream oin = new ObjectInputStream(fin);
            Campeonato camp = (Campeonato) oin.readObject();

            oin.close();
            fin.close();

            for(int i = 0; i < 10; i++){
                this.jogadores[i] = camp.jogadores[i];
                this.qntJogadores = camp.qntJogadores;
            }

            System.out.println("Dados carregados com sucesso!\n");
        }catch(Exception e){
            System.out.println("erro: " + e);
        }
    }
}