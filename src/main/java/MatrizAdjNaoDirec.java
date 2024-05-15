import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class MatrizAdjNaoDirec {

  public static void criaAresta(Grafo2 grafo, int origem, int destino, int peso) {
    int numVertices = grafo.vertices;
    if (origem >= 0 && origem < numVertices && destino >= 0 && destino < numVertices) {
      grafo.matrizAdj[origem][destino] = peso;
      // Deixe a matriz simétrica
      grafo.matrizAdj[destino][origem] = peso;
    } else {
      System.out.println("Vértice não existente");
    }
  }

  public static void removeAresta(Grafo2 grafo, int origem, int destino) {

    grafo.matrizAdj[origem][destino] = 0;
    grafo.matrizAdj[destino][origem] = 0;

  }

  public static void imprimeGrafo(Grafo2 grafo, int numVertice) {
    System.out.println("Matriz Adjacente: \n");

    // Calcula o comprimento máximo dos índices
    int maxLength = String.valueOf(numVertice).length();

    // Imprime o cabeçalho das colunas
    System.out.print("   ");
    for (int i = 0; i < numVertice; i++) {
      System.out.printf("%-" + maxLength + "d ", i + 1);
    }
    System.out.println();

    // Imprime a linha divisória entre o cabeçalho das colunas e o conteúdo da
    // matriz
    System.out.print("---");
    for (int i = 0; i < numVertice; i++) {
      for (int j = 0; j < maxLength; j++) {
        System.out.print("-");
      }
      System.out.print(" ");
    }
    System.out.println();

    // Imprime o conteúdo da matriz
    for (int i = 0; i < numVertice; i++) {
      // Imprime o índice da linha
      System.out.printf("%-" + maxLength + "d |", (i + 1));
      for (int j = 0; j < numVertice; j++) {
        // Imprime o valor da matriz (ou o peso da aresta)
        System.out.printf("%-" + maxLength + "d ", grafo.matrizAdj[i][j]);
      }
      System.out.println();
    }
  }

  public static void grauVertice(Grafo2 grafo, int numVertice) {
    for (int i = 0; i < numVertice; i++) {
      int grau = 0;
      for (int j = 0; j < numVertice; j++) {
        grau += grafo.matrizAdj[i][j];
      }
      System.out.println("Grau do vértice " + (i + 1) + ": " + grau);
    }
  }

  public static void imprimeVizinhanca(Grafo2 grafo, int numVertice) {
    for (int i = 0; i < numVertice; i++) {
      System.out.print("Vizinhanças do vértice " + i + ": ");
      for (int j = 0; j < numVertice; j++) {
        if (grafo.matrizAdj[i][j] != 0) {
          System.out.print(j + "(" + grafo.matrizAdj[i][j] + ") ");
        }
      }
      System.out.println();
    }
  }

  public static void grafoSimples(Grafo2 grafo, int numVertice) {
    boolean simples = true;
    // procura se tem arestas paralelas ou laços
    for (int i = 0; i < numVertice; i++) {
      for (int j = 0; j < numVertice; j++) {
        if (grafo.matrizAdj[i][j] > 1 || (i == j && grafo.matrizAdj[i][j] > 0)) {
          simples = false;
          break;
        }
      }
      if (!simples) {
        break;
      }
    }

    if (simples) {
      System.out.println("O grafo é simples.");
    } else {
      System.out.println("O grafo não é simples.");
    }
  }

  public static void grafoRegular(Grafo2 grafo, int numVertice) {
    boolean regular = true;
    int grau = 0;
    for (int j = 0; j < numVertice; j++) {
      grau += grafo.matrizAdj[1][j];
    }

    for (int i = 2; i <= numVertice; i++) {
      int grauAtual = 0;
      for (int j = 0; j < numVertice; j++) {
        grauAtual += grafo.matrizAdj[i][j];
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

  public static void grafoCompleto(Grafo2 grafo, int numVertice) {
    boolean completo = true;
    for (int i = 0; i < numVertice; i++) {
      for (int j = 0; j < numVertice; j++) {
        if (i != j && grafo.matrizAdj[i][j] == 0) {
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

  public static void buscaEmLargura(Grafo2 grafo, int verticeInicial, int numVertices) {
    boolean visitados[] = new boolean[numVertices];
    Queue<Integer> fila = new LinkedList<>();

    visitados[verticeInicial] = true;
    fila.add(verticeInicial);

    while (!fila.isEmpty()) {
      int verticeAtual = fila.poll();
      System.out.print(verticeAtual + 1 + " ");

      for (int i = 0; i < numVertices; i++) {
        if (grafo.matrizAdj[verticeAtual][i] != 0 && !visitados[i]) {
          visitados[i] = true;
          fila.add(i);
        }
      }
    }
    System.out.println();
  }

  public static void buscaEmProfundidade(Grafo2 grafo, int verticeInicial, boolean[] visitados) {
    visitados[verticeInicial] = true;
    System.out.print(verticeInicial + 1 + " ");

    for (int i = 0; i < visitados.length; i++) {
      if (grafo.matrizAdj[verticeInicial][i] != 0 && !visitados[i]) {
        buscaEmProfundidade(grafo, i, visitados);
      }
    }
  }

  public static void buscaEmProfundidadeMain(Grafo2 grafo, int verticeInicial, int numVertices) {
    boolean visitados[] = new boolean[numVertices];
    buscaEmProfundidade(grafo, verticeInicial, visitados);
    System.out.println();
  }

  public static void menu() {
    int numVertice, numAresta;
    Scanner sc = new Scanner(System.in);
    Grafo2 grafo = null;
    try {
      System.out.println("Digite o número de vértices: ");
      numVertice = sc.nextInt();
      System.out.println("Digite o número de arestas: ");
      numAresta = sc.nextInt();

      grafo = new Grafo2(numVertice);

      for (int i = 0; i < numAresta; i++) {
        int origem, destino, peso;
        System.out.println("Digite as arestas e o peso, formato - 1,2,peso: ");
        String combinacao = sc.next();
        String[] aux = combinacao.split(",");
        origem = Integer.parseInt(aux[0]);
        destino = Integer.parseInt(aux[1]);
        peso = Integer.parseInt(aux[2]);

        criaAresta(grafo, origem - 1, destino - 1, peso);

      }

      System.out.println("\n\n");
      int op = 0;
      do {
        System.out.println("======= MENU =======");
        System.out.println("[ 1 ] - Criar Arestas");
        System.out.println("[ 2 ] - Remover Arestas");
        System.out.println("[ 3 ] - Imprimir Grafo");
        System.out.println("[ 4 ] - Vizinhança");
        System.out.println("[ 5 ] - Grau de cada Vértice");
        System.out.println("[ 6 ] - Verificação do Grafo");
        System.out.println("[ 7 ] - Busca em Largura");
        System.out.println("[ 8 ] - Busca em Profundidade");
        System.out.println("[ 9 ] - Árvore Geradora Mínima**");
        System.out.println("[ 10 ] - Caminho mínimo entre dois vértices**");
        System.out.println("[ 0 ] - Sair");
        System.out.println("Qual opção você deseja? ");
        op = sc.nextInt();

        switch (op) {
          case 1: {
            System.out.println("\n\n");
            int origem, destino, peso;
            System.out.println("Digite a aresta que deseja adicionar, formato - 1,2,peso: ");
            String combinacao = sc.next();
            String[] aux = combinacao.split(",");
            origem = Integer.parseInt(aux[0]);
            destino = Integer.parseInt(aux[1]);
            peso = Integer.parseInt(aux[2]);

            criaAresta(grafo, origem - 1, destino - 1, peso);
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
            removeAresta(grafo, origem - 1, destino - 1);
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

            imprimeVizinhanca(grafo, numVertice);

            System.out.println("\n\n\n\n");
            break;
          }
          case 5: {
            System.out.println("\n\n");

            grauVertice(grafo, numVertice);
            System.out.println("\n\n\n\n");
            break;
          }
          case 6: {
            System.out.println("\n\n");
            // verificação se o grafo é simples
            grafoSimples(grafo, numVertice);
            System.out.println("\n");

            // verificação se o grafo é regular
            grafoRegular(grafo, numVertice);
            System.out.println("\n");

            // verificação se o grafo é completo
            grafoCompleto(grafo, numVertice);
            System.out.println("\n");
            // verificação se o grafo é bipartido

            System.out.println("\n\n\n\n");
            break;

          }
          case 7: {
            System.out.println("\n\n");
            System.out.println("Digite o vértice inicial para a busca em largura: ");
            int verticeInicial = sc.nextInt();
            System.out.println("Busca em Largura: ");
            buscaEmLargura(grafo, verticeInicial - 1, numVertice);
            System.out.println("\n\n\n\n");
            break;
          }

          case 8: {
            System.out.println("\n\n");
            System.out.println("Digite o vértice inicial para a busca em profundidade: ");
            int verticeInicial = sc.nextInt();
            System.out.println("Busca em Profundidade: ");
            buscaEmProfundidadeMain(grafo, verticeInicial - 1, numVertice);
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

  }
}