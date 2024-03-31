import java.util.*;

public class Main {
  public static void main(String[] args) {
    Scanner sc = new Scanner(System.in);
    int op = 0;

    do{
      System.out.println("======= MENU =======");
      System.out.println("[ 1 ] - Matriz de Adjacência");
      System.out.println("[ 2 ] - Lista de Adjacência");
      System.out.println("[ 0 ] - Sair");
      System.out.println("Qual opção você deseja? ");
      op = sc.nextInt();

      switch (op) {
        case 1:{
          int opp = 0;
          System.out.println("======= MENU =======");
          System.out.println("[ 1 ] - Direcionada");
          System.out.println("[ 2 ] - Não-Direcionada");
          System.out.println("Qual opção você deseja? ");
          opp = sc.nextInt();

          switch(opp){
            case 1:{
              MatrizAjdDirec.menu();
            }
            case 2:{
              MatrizAdjNaoDirec.menu();
            }
            default:{
              System.out.println("Opção inválida, tente novamente");
            }
          }

        }
        case 2:{
          int opp = 0;
          System.out.println("======= MENU =======");
          System.out.println("[ 1 ] - Direcionada");
          System.out.println("[ 2 ] - Não-Direcionada");
          System.out.println("Qual opção você deseja? ");
          opp = sc.nextInt();

          switch(opp){
            case 1:{
              ListaAdjDirecionado.menu();
            }
            case 2:{
              ListaAdjNDirec.menu();
            }
            default:{
              System.out.println("Opção inválida, tente novamente");
            }
          }
        }
        case 0:{

          System.out.println("Até, logo!");
        }
        default: {
          System.out.println("Opção inválida, tente novamente");
        }
      }
    }while(op!=0);

    sc.close();
  }

}