import java.util.Scanner;

public class usaCampeonato {
    public static void main(String[] args){
        Campeonato campeonato = new Campeonato();
        menu(campeonato);
    }

    public static void menu(Campeonato campeonato){
        char opc;

        do {
            Scanner scanner = new Scanner(System.in);
        
            System.out.println(" ____________________________________________");
            System.out.println("|  a - Adicionar Jogador                     |");
            System.out.println("|  b - Remover Jogador                       |");
            System.out.println("|  c - Executar jogada                       |");
            System.out.println("|  d - Mostrar Cartela de resultados         |");
            System.out.println("|  e - Gravar dados do campeonato em arquivo |");
            System.out.println("|  f - Ler dados do campeonato de arquivo    |");
            System.out.println("|  g - Sair da aplicacao                     |");
            System.out.println("|____________________________________________|");
            System.out.print(" Escolha uma opcao: ");
            opc = scanner.nextLine().charAt(0);

            switch(opc){
                case 'a':
                    campeonato.incluirJogador();
                    break;
                case 'b': 
                    campeonato.removerJogador();
                    break;
                case 'c':
                    campeonato.iniciarCampeonato();
                    break;
            //     case 'a': 
            //         //novo jogador
            //         campeonato.incluirJogador();
            //         break;
            //     case 'b':
            //         //remover jogador
            //         campeonato.removerJogador();
            //         break;
            //     case 'c':
            //         // executa jogada
            //         campeonato.iniciarCampeonato();
            //         break;
            //     case 'd':
            //         //mostra cartela de resultados
            //         campeonato.mostrarCartela();
            //         break;
            //     case 'e':
            //         //gravar em arquivo
            //         campeonato.gravarEmArquivo(campeonato);
            //         break;
            //     case 'f':
            //         //ler arquivo
            //         campeonato.lerDoArquivo();
            //         break;
            //     case 'g':
            //         //sair da aplicação
            //         System.out.println("Obrigado por jogar!! :)");
            //         break;
            //     default:
            //         System.out.println("Opcao invalida!");
            // }
            }
        }while(opc != 'g');
    }
}