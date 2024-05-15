import java.util.Arrays;
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
      grau += grafo.matrizAdj[0][j]; // Corrigido para matrizAdj[0][j]
    }

    for (int i = 1; i < numVertice; i++) { // Iniciando de 1 pois o índice 0 já foi verificado
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

  public static void grafoBipartido(Grafo2 grafo, int numVertices) {
    // Array para registrar a cor de cada vértice
    // Usaremos 0 para vértices ainda não coloridos, 1 para uma cor e -1 para a outra cor
    int[] cor = new int[numVertices];
    // Inicializa todas as cores como não atribuídas (0)
    for (int i = 0; i < numVertices; i++) {
        cor[i] = 0;
    }

    // Vamos usar uma busca em largura (BFS) para atribuir cores aos vértices
    // Começamos com um vértice arbitrário (0) e o marcamos com a cor 1
    cor[0] = 1;
    // Criamos uma fila para realizar a busca em largura
    Queue<Integer> fila = new LinkedList<>();
    fila.add(0);

    // Realizamos a busca em largura
    while (!fila.isEmpty()) {
        int u = fila.poll();
        // Para cada vértice adjacente a u
        for (int v = 0; v < numVertices; v++) {
            // Ajustando para começar a contar a partir de 1
            int verticeU = u + 1;
            int verticeV = v + 1;
            // Se existe uma aresta de u para v e v ainda não foi colorido
            if (grafo.matrizAdj[u][v] != 0 && cor[v] == 0) {
                // Atribuímos a cor oposta a u a v
                cor[v] = -cor[u];
                fila.add(v);
            }
            // Se existe uma aresta de u para v e v já foi colorido com a mesma cor que u
            else if (grafo.matrizAdj[u][v] != 0 && cor[v] == cor[u]) {
                // Imprime que o grafo não é bipartido
                System.out.println("O grafo não é bipartido.");
                return;
            }
        }
    }

    // Se chegamos aqui, o grafo é bipartido
    System.out.println("O grafo é bipartido.");
}

  public static void grafoConexo(Grafo2 grafo, int numVertices) {
    boolean[] visitados = new boolean[numVertices];
    Queue<Integer> fila = new LinkedList<>();
    int verticeInicial = 0; // Você pode escolher qualquer vértice inicial para iniciar a busca

    // Marca o vértice inicial como visitado e o adiciona à fila
    visitados[verticeInicial] = true;
    fila.add(verticeInicial);

    // Enquanto a fila não estiver vazia, continua a busca
    while (!fila.isEmpty()) {
        int verticeAtual = fila.poll();

        // Para cada vértice adjacente ao vértice atual
        for (int i = 0; i < numVertices; i++) {
            // Se houver uma aresta entre o vértice atual e o vértice i e i não foi visitado ainda
            if (grafo.matrizAdj[verticeAtual][i] != 0 && !visitados[i]) {
                // Marca o vértice i como visitado e o adiciona à fila
                visitados[i] = true;
                fila.add(i);
            }
        }
    }

    // Verifica se todos os vértices foram visitados
    for (boolean visitado : visitados) {
        if (!visitado) {
            // Se algum vértice não foi visitado, o grafo não é conexo
            System.out.println("O grafo não é conexo.");
            return;
        }
    }

    // Se todos os vértices foram visitados, o grafo é conexo
    System.out.println("O grafo é conexo.");
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

    public static void dijkstra(Grafo2 grafo, int origem, int destino, int numVertices) {
        // Array para armazenar a menor distância de origem até cada vértice
        int[] distancia = new int[numVertices];
        // Array para registrar se o vértice correspondente já foi visitado
        boolean[] visitado = new boolean[numVertices];

        // Inicializa todas as distâncias como infinito e todos os vértices como não visitados
        Arrays.fill(distancia, Integer.MAX_VALUE);
        Arrays.fill(visitado, false);

        // A distância da origem até ela mesma é 0
        distancia[origem] = 0;

        // Encontra o caminho mais curto para todos os vértices
        for (int count = 0; count < numVertices - 1; count++) {
            // Encontra o vértice com a menor distância do conjunto de vértices ainda não visitados
            int u = minDistance(distancia, visitado, numVertices);
            // Marca o vértice como visitado
            visitado[u] = true;
            // Se o vértice de destino for alcançado, podemos parar a busca
            if (u == destino) {
                break;
            }
            // Atualiza a distância de todos os vértices adjacentes ao vértice atual
            for (int v = 0; v < numVertices; v++) {
                // Atualiza a distância se:
                // 1. Houver uma aresta de u para v
                // 2. v ainda não foi visitado
                // 3. A distância total até v passando por u é menor do que a distância atualmente armazenada para v
                if (grafo.matrizAdj[u][v] != 0 && !visitado[v] && distancia[u] != Integer.MAX_VALUE &&
                        distancia[u] + grafo.matrizAdj[u][v] < distancia[v]) {
                    distancia[v] = distancia[u] + grafo.matrizAdj[u][v];
                }
            }
        }

        // Imprime a distância mínima até o vértice de destino
        System.out.println("Distância mínima de " + (origem+1) + " para " + (destino+1) + ": " + distancia[destino]);
    }

    // Função auxiliar para encontrar o vértice com a menor distância do conjunto de vértices ainda não visitados
    private static int minDistance(int[] distancia, boolean[] visitado, int numVertices) {
        int min = Integer.MAX_VALUE;
        int minIndex = -1;
        for (int v = 0; v < numVertices; v++) {
            if (!visitado[v] && distancia[v] <= min) {
                min = distancia[v];
                minIndex = v;
            }
        }
        return minIndex;
    }

    public static void primMST(Grafo2 grafo, int numVertices) {
      // Array para armazenar a chave de cada vértice
      int[] chave = new int[numVertices];
      // Array para armazenar o vértice pai de cada vértice na árvore geradora mínima
      int[] pai = new int[numVertices];
      // Array para registrar se o vértice correspondente já foi incluído na árvore geradora mínima
      boolean[] incluido = new boolean[numVertices];

      // Inicializa todas as chaves como infinito e todos os vértices como não incluídos na árvore
      Arrays.fill(chave, Integer.MAX_VALUE);
      Arrays.fill(incluido, false);

      // Ajusta o primeiro vértice para começar do 0
      int u = 0;

      // Constrói a árvore geradora mínima com base na chave de cada vértice
      for (int count = 0; count < numVertices - 1; count++) {
          // Marca o vértice como incluído na árvore
          incluido[u] = true;
          // Atualiza a chave de cada vértice adjacente ao vértice atual
          for (int v = 0; v < numVertices; v++) {
              // Ajusta os índices dos vértices para começar do 0
              int verticeU = u - 1;
              int verticeV = v - 1;
              // Atualiza a chave se:
              // 1. Houver uma aresta de u para v
              // 2. v ainda não foi incluído na árvore
              // 3. O peso da aresta entre u e v for menor do que a chave atual de v
              if (grafo.matrizAdj[u][v] != 0 && !incluido[v] && grafo.matrizAdj[u][v] < chave[v]) {
                  pai[v] = u;
                  chave[v] = grafo.matrizAdj[u][v];
              }
          }
          // Encontra o vértice com a chave mínima que ainda não foi incluído na árvore
          u = minKey(chave, incluido, numVertices);
      }

      // Imprime as arestas da árvore geradora mínima
      System.out.println("Arestas da Árvore Geradora Mínima:");
      for (int i = 1; i < numVertices; i++) {
          System.out.println("Aresta " + (pai[i] + 1) + " - " + (i + 1) + " -> Peso: " + grafo.matrizAdj[i][pai[i]]);
      }
  }

  // Função auxiliar para encontrar o vértice com a chave mínima que ainda não foi incluído na árvore
  private static int minKey(int[] chave, boolean[] incluido, int numVertices) {
      int min = Integer.MAX_VALUE;
      int minIndex = -1;
      for (int v = 0; v < numVertices; v++) {
          if (!incluido[v] && chave[v] < min) {
              min = chave[v];
              minIndex = v;
          }
      }
      return minIndex;
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
            grafoBipartido(grafo, numVertice);
            System.out.println("\n");
            //verifica se o grafo é conexo
            grafoConexo(grafo, numVertice);

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
          case 9: {
            System.out.println("\n\n");
            primMST(grafo, numVertice);
            System.out.println("\n\n\n\n");
            break;
        }
        
          case 10: {
            System.out.println("\n\n");
            System.out.println("Digite o vértice de origem para o algoritmo de Dijkstra: ");
            int origemDijkstra = sc.nextInt();
            System.out.println("Digite o vértice de destino para o algoritmo de Dijkstra: ");
            int destinoDijkstra = sc.nextInt();
            dijkstra(grafo, origemDijkstra -1 , destinoDijkstra -1, numVertice);
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
      System.out.println(E.getMessage());
    }

  }
}