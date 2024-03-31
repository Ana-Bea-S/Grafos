import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class MatrizAjdDirec {

  public static void criaAresta(Grafo grafo, int origem, int destino) {
    try {
      grafo.matrizAdj[origem][destino] = -1; // aresta que sai do vértice i
      grafo.matrizAdj[destino][origem] = 1; // aresta que entra no vértice i
    } catch (ArrayIndexOutOfBoundsException index) {
      System.out.println("Vértice não existente");
    }
  }

  public static void removeAresta(Grafo grafo, int origem, int destino) {
    grafo.matrizAdj[origem][destino] = 0;
  }

  public static void imprimeGrafo(Grafo grafo, int numVertice) {
    System.out.println("Matriz Adjacente: \n");

    int maxLength = String.valueOf(numVertice).length();

    System.out.print("   ");
    for (int i = 1; i <= numVertice; i++) {
      System.out.printf("%-" + maxLength + "d ", i);
    }
    System.out.println();

    System.out.print("---");
    for (int i = 1; i <= numVertice; i++) {
      for (int j = 0; j < maxLength; j++) {
        System.out.print("-");
      }
      System.out.print(" ");
    }
    System.out.println();

    for (int i = 1; i <= numVertice; i++) {
      System.out.printf("%-" + maxLength + "d |", i);
      for (int j = 1; j <= numVertice; j++) {
        System.out.printf("%-" + maxLength + "d ", grafo.matrizAdj[i][j]);
      }
      System.out.println();
    }
  }

  public static void grauVertice(Grafo grafo, int numVertice) {
    for (int i = 1; i <= numVertice; i++) {
      int grau = 0;
      for (int j = 1; j <= numVertice; j++) {
        grau += Math.abs(grafo.matrizAdj[i][j]);
      }
      System.out.println("Grau do vértice " + i + ": " + grau);
    }
  }

  public static void imprimeSucessores(Grafo grafo, int numVertice) {
    for (int i = 1; i <= numVertice; i++) {
      System.out.print("Sucessores do vértice " + i + ": ");
      for (int j = 1; j <= numVertice; j++) {
        if (grafo.matrizAdj[i][j] == -1) {
          System.out.print(j + " ");
        }
      }
      System.out.println();
    }
  }

  public static void imprimePredecessores(Grafo grafo, int numVertice) {
    for (int i = 1; i <= numVertice; i++) {
      System.out.print("Predecessores do vértice " + i + ": ");
      for (int j = 1; j <= numVertice; j++) {
        if (grafo.matrizAdj[i][j] == 1) {
          System.out.print(j + " ");
        }
      }
      System.out.println();
    }
  }

  public static void grafoSimples(Grafo grafo, int numVertice) {
    boolean simples = true;
    for (int i = 1; i <= numVertice; i++) {
      for (int j = 1; j <= numVertice; j++) {
        if (i != j && Math.abs(grafo.matrizAdj[i][j]) != 1 && grafo.matrizAdj[i][j] != 0) {
          simples = false;
          break;
        }
      }
      if (!simples) {
        break;
      }
    }
    // Verifica loops
    for (int i = 1; i <= numVertice; i++) {
      if (grafo.matrizAdj[i][i] != 0) {
        simples = false;
        break;
      }
    }
    if (simples) {
      System.out.println("O grafo é simples.");
    } else {
      System.out.println("O grafo não é simples.");
    }
  }

  public static void grafoCompleto(Grafo grafo, int numVertice) {
    boolean completo = true;
    for (int i = 1; i <= numVertice; i++) {
      for (int j = 1; j <= numVertice; j++) {
        if (i != j && grafo.matrizAdj[i][j] != -1 && grafo.matrizAdj[i][j] != 1) {
          completo = false;
          break;
        }
      }
      if (!completo) {
        break;
      }
    }
    if (completo) {
      System.out.println("O grafo é completo.");
    } else {
      System.out.println("O grafo não é completo.");
    }
  }

  public static void grafoRegular(Grafo grafo, int numVertice) {
    boolean regular = true;
    int grau = 0;
    for (int j = 1; j <= numVertice; j++) {
      grau += Math.abs(grafo.matrizAdj[1][j]);
    }

    for (int i = 2; i <= numVertice; i++) {
      int grauAtual = 0;
      for (int j = 1; j <= numVertice; j++) {
        grauAtual += Math.abs(grafo.matrizAdj[i][j]);
      }
      if (grauAtual != grau) {
        regular = false;
        break;
      }
    }

    if (regular) {
      System.out.println("O grafo é regular.");
    } else {
      System.out.println("O grafo não é regular.");
    }
  }

  public static void grafoBipartido(Grafo grafo, int numVertice) {
    int[] cor = new int[numVertice + 1]; // Array para armazenar a cor de cada vértice (1 e -1)

    // Inicializa todas as cores como não visitadas (-1)
    for (int i = 1; i <= numVertice; i++) {
        cor[i] = -1;
    }

    // Inicia a busca em largura em todos os vértices
    for (int i = 1; i <= numVertice; i++) {
        if (cor[i] == -1) { // Se o vértice ainda não foi visitado, inicia a BFS a partir dele
            cor[i] = 1; // Define a cor do vértice inicial como 1
            Queue<Integer> fila = new LinkedList<>();
            fila.add(i); // Adiciona o vértice à fila

            // Realiza a busca em largura
            while (!fila.isEmpty()) {
                int u = fila.poll(); // Remove o vértice da fila

                // Percorre todos os vértices adjacentes de u
                for (int v = 1; v <= numVertice; v++) {
                    // Se existir uma aresta entre u e v e v ainda não foi visitado
                    if (grafo.matrizAdj[u][v] == -1 && cor[v] == -1) {
                        cor[v] = 1 - cor[u]; // Atribui a cor oposta ao vértice v
                        fila.add(v); // Adiciona v à fila para explorar seus vizinhos
                    }
                    // Se existir uma aresta entre u e v e v tem a mesma cor que u
                    else if (grafo.matrizAdj[u][v] == -1 && cor[v] == cor[u]) {
                        System.out.println("O grafo não é bipartido.");
                        return;
                    }
                }
            }
        }
    }

    System.out.println("O grafo é bipartido.");
}


  public static void menu() {
    int numVertice, numAresta;
    Scanner sc = new Scanner(System.in);
    Grafo grafo = null;
    try {
      System.out.println("Digite o número de vértices: ");
      numVertice = sc.nextInt();
      System.out.println("Digite o número de arestas: ");
      numAresta = sc.nextInt();

      grafo = new Grafo(numVertice);

      for (int i = 0; i < numAresta; i++) {
        int origem, destino;
        System.out.println("Digite as arestas, formato - 1,2: ");
        String combinacao = sc.next();
        String[] aux = combinacao.split(",");
        origem = Integer.parseInt(aux[0]);
        destino = Integer.parseInt(aux[1]);

        criaAresta(grafo, origem, destino);
      }

      System.out.println("\n\n");
      int op = 0;
      do {
        System.out.println("======= MENU =======");
        System.out.println("[ 1 ] - Criar Arestas");
        System.out.println("[ 2 ] - Remover Arestas");
        System.out.println("[ 3 ] - Imprimir Grafo");
        System.out.println("[ 4 ] - Sucessores");
        System.out.println("[ 5 ] - Predecessores");
        System.out.println("[ 6 ] - Grau de cada Vértice");
        System.out.println("[ 7 ] - Verificar Grafo");
        System.out.println("[ 0 ] - Sair");
        System.out.println("Qual opção você deseja? ");
        op = sc.nextInt();

        switch (op) {
          case 1: {
            System.out.println("\n\n");
            int origem, destino;
            System.out.println("Digite a aresta que deseja adicionar, formato - 1,2: ");
            String combinacao = sc.next();
            String[] aux = combinacao.split(",");
            origem = Integer.parseInt(aux[0]);
            destino = Integer.parseInt(aux[1]);

            criaAresta(grafo, origem, destino);
            System.out.println("\n\n\n\n");
            break;
          }
          case 2: {
            System.out.println("\n\n");
            int origem, destino;
            System.out.println("Digite a aresta que deseja remover, formato - 1,2: ");
            String combinacao = sc.next();
            String[] aux = combinacao.split(",");
            origem = Integer.parseInt(aux[0]);
            destino = Integer.parseInt(aux[1]);
            removeAresta(grafo, origem, destino);
            System.out.println("\n\n\n\n");
            break;
          }
          case 3: {
            System.out.println("\n\n");

            imprimeGrafo(grafo, numVertice);

            System.out.println("\n\n\n\n");
            break;
          }
          case 4: {
            System.out.println("\n\n");

            imprimeSucessores(grafo, numVertice);

            System.out.println("\n\n\n\n");
            break;
          }
          case 5: {
            System.out.println("\n\n");

            imprimePredecessores(grafo, numVertice);

            System.out.println("\n\n\n\n");
            break;
          }
          case 6: {
            System.out.println("\n\n");

            grauVertice(grafo, numVertice);
            System.out.println("\n\n\n\n");
            break;
          }
          case 7: {
            System.out.println("\n\n");
            // verificar se é simples
            grafoSimples(grafo, numVertice);
            System.out.println("\n\n");
            // verificar se é completo
            grafoCompleto(grafo, numVertice);
            System.out.println("\n\n");
            // verificar se é regular
            grafoRegular(grafo, numVertice);
            System.out.println("\n\n");
            // verificar se é bipartido
            grafoBipartido(grafo, numVertice);
            System.out.println("\n\n\n\n");
            break;
          }
          case 0: {
            System.out.println("Até logo!");
            break;
          }
          default: {
            System.out.println("Opção inválida, tente novamente");
            System.out.println("\n\n");
            break;
          }
        }

      } while (op != 0);

    } catch (Exception E) {
      System.out.println("Erro!");
    }

    sc.close();
  }
}
