import java.util.Scanner;

public class usaCampeonato {
    public static void main(String[] args){
        Campeonato campeonato = new Campeonato();
        menu(campeonato);
    }

    public static void menu(Campeonato campeonato){
        String opc;

        do {
            Scanner scanner = new Scanner(System.in);
        
            System.out.println(" ____________________________________________");
            System.out.println("|  a - Adicionar Jogador                     |");
            System.out.println("|  b - Remover Jogador                       |");
            System.out.println("|  c - Executar jogada                       |");
            System.out.println("|  d - Imprimir saldo(s)                     |");
            System.out.println("|  e - Imprimir extratos dos resultados      |");
            System.out.println("|  f - Imprimir estatisticas                 |");
            System.out.println("|  g - Gravar dados do campeonato em arquivo |");
            System.out.println("|  h - Ler dados do campeonato de arquivo    |");
            System.out.println("|  i - Sair da aplicacao                     |");
            System.out.println("|____________________________________________|");
            System.out.print(" Escolha uma opcao: ");
            opc = scanner.nextLine().toLowerCase();

            switch(opc.charAt(0)){
                case 'a':
                    campeonato.incluirJogador();
                    break;
                case 'b': 
                    campeonato.removerJogador();
                    break;
                case 'c':
                    campeonato.iniciarCampeonato();
                    break;
                case 'd':
                    campeonato.imprimirSaldo();;
                    break;
                case 'e':
                    campeonato.imprimirExtratos();
                    break;
                 case 'f':
                     campeonato.imprimirEstatisticas();
                     break;
                case 'g':
                    campeonato.gravarEmArquivo(campeonato);
                    break;
                case 'h':
                    campeonato.lerDoArquivo();
                    break;
                case 'i':
                    System.out.println("Obrigado por jogar!");
                    break;
                default:
                    System.out.println("Opcao invalida!");
                    break;
            }
        }while(opc.charAt(0) != 'i');
    }
}