import java.util.*;

public class Main {
  public static void main(String[] args) {
    Scanner sc = new Scanner(System.in);
    String op;

    do{
      System.out.println("======= MENU =======");
      System.out.println("[ 1 ] - Matriz de Adjacência");
      System.out.println("[ 2 ] - Lista de Adjacência");
      System.out.println("[ 0 ] - Sair");
      System.out.println("Qual opção você deseja? ");
      op = sc.nextLine();

      switch (op) {
        case "1":{
          String opp;
          System.out.println("======= MENU =======");
          System.out.println("[ 1 ] - Direcionada");
          System.out.println("[ 2 ] - Não-Direcionada");
          System.out.println("Qual opção você deseja? ");
          opp = sc.nextLine();

          switch(opp){
            case "1":{
              MatrizAjdDirec.menu();
              break;
            }
            case "2":{
              MatrizAdjNaoDirec.menu();
              break;
            }
            default:{
              System.out.println("Opção inválida, tente novamente");
              break;
            }
          }
          break;

        }
        case "2":{
          String opp;
          System.out.println("======= MENU =======");
          System.out.println("[ 1 ] - Direcionada");
          System.out.println("[ 2 ] - Não-Direcionada");
          System.out.println("Qual opção você deseja? ");
          opp = sc.nextLine();

          switch(opp){
            case "1":{
              ListaAdjDirecionado.menu();
              break;
            }
            case "2":{
              ListaAdjNDirec.menu();
              break;
            }
            default:{
              System.out.println("Opção inválida, tente novamente");
              break;
            }
          }
          break;
        }
        case "0":{

          System.out.println("Até, logo!");
          break;
        }
        default: {
          System.out.println("Opção inválida, tente novamente");
          break;
        }
      }
    }while(op!="0");

    sc.close();
  }

}