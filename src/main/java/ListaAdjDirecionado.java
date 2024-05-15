import java.util.*;

public class ListaAdjDirecionado {

  public static void adicionaAresta(ArrayList<Vertices> grafo, String aresta) {
    String[] aux = aresta.split(",");
    int origem = Integer.parseInt(aux[0]) - 1;
    int destino = Integer.parseInt(aux[1]) - 1;
    int peso = Integer.parseInt(aux[2]); // Adiciona a terceira parte como o peso

    grafo.get(origem).arestas.add(new Arestas(destino + 1, peso));
  }

  public static void removeAresta(ArrayList<Vertices> grafo, String aresta) {
    String[] aux = aresta.split(",");
    int origem = Integer.parseInt(aux[0]) - 1;
    int destino = Integer.parseInt(aux[1]) - 1;
    int peso = Integer.parseInt(aux[2]); // Adiciona a terceira parte como o peso

    ArrayList<Arestas> arestas = grafo.get(origem).arestas;

    for (int i = 0; i < arestas.size(); i++) {
      Arestas a = arestas.get(i);
      if (a.destino == destino + 1 && a.peso == peso) {
        grafo.get(origem).arestas.remove(i);
        break;
      }
    }
  }

  public static void printPredecessores(ArrayList<Vertices> grafo) {
    ArrayList<Vertices> predecessores = new ArrayList<Vertices>();
    for (int i = 0; i < grafo.size(); i++) {
      predecessores.add(new Vertices(grafo.get(i).rotulo));
    }

    System.out.println("\nPredecessores:");
    for (int i = 0; i < grafo.size(); i++) {
      Vertices vertice = grafo.get(i);
      for (Arestas aresta : vertice.arestas) {
        predecessores.get(aresta.destino - 1).arestas.add(new Arestas(aresta.destino,vertice.rotulo));
      }
    }
    for (int i = 0; i < predecessores.size(); i++) {
      // aqui está imprimindo o rotulo do vértice, ex: 1, 2, 3, 4
      Vertices vertice = predecessores.get(i);
      System.out.print("Vértice " + vertice.rotulo + ": [");
      for (int j = 0; j < vertice.arestas.size(); j++) {
        System.out.print((vertice.arestas.get(j)));
        if (j < vertice.arestas.size() - 1) {
          System.out.print(", ");
        }
      }
      System.out.println("]");
    }
}


  public static void printSucessores(ArrayList<Vertices> grafo) {
    System.out.println("\nSucessores:");
    for (int i = 0; i < grafo.size(); i++) {
      Vertices vertice = grafo.get(i);
      System.out.print("Vértice " + vertice.rotulo + ": [");
      for (Arestas aresta : vertice.arestas) {
        System.out.print(aresta.destino);
        if (grafo.indexOf(vertice) < grafo.size() - 1) {
          System.out.print(", ");
        }
      }
      System.out.println("]");
    }
  }

  public static void grauVertice(ArrayList<Vertices> grafo, int[] grauTotalV) {
    for (int i = 0; i < grafo.size(); i++) {
      Vertices vertice = grafo.get(i);
      int grauEnt = 0;
      int grauSai = vertice.arestas.size();

      for (Vertices outroVertice : grafo) {
        for (Arestas aresta : outroVertice.arestas) {
          if (aresta.destino == vertice.rotulo) {
            grauEnt++;
          }
        }
      }

      grauTotalV[i] = grauEnt + grauSai;
    }
  }

  public static void imprimeGrauVertice(ArrayList<Vertices> grafo) {
    System.out.println("\nGrau de cada Vértice:");
    for (Vertices vertice : grafo) {
      int grauEntrada = 0;
      for (Vertices outroVertice : grafo) {
        for (Arestas aresta : outroVertice.arestas) {
          if (aresta.destino == vertice.rotulo) {
            grauEntrada++;
          }
        }
      }
      int grauSaida = vertice.arestas.size();
      System.out.println("Vértice " + vertice.rotulo + ": Grau de Entrada = " + grauEntrada + ", Grau de Saída = "
          + grauSaida);
    }
  }

  public static void grafoSimples(ArrayList<Vertices> grafo) {
    boolean simples = true;

    for (int i = 0; i < grafo.size(); i++) {
      Vertices vertice = grafo.get(i);
      ArrayList<Arestas> arestas = vertice.arestas;

      boolean contemRotulo = false;
      for (Arestas aresta : arestas) {
        if (aresta.destino == vertice.rotulo) {
          contemRotulo = true;
          break;
        }
      }
      if (contemRotulo) {
        simples = false;
        break;
      }
    }

    if (!simples) {
      System.out.println("Não é um grafo simples");
    } else {
      System.out.println("É um grafo simples");
    }
    System.out.println();
  }

  public static void grafoRegular(ArrayList<Vertices> grafo, int[] grauTotalV) {
    boolean regular = true;
    int grauTotalG = 0;
    for (int i = 0; i < grafo.size(); i++) {
      grauTotalG += grauTotalV[i]; // Calcula o grau total do grafo
    }
    for (int i = 0; i < grafo.size(); i++) {
      if (grauTotalV[i] != grauTotalG / grafo.size()) {
        regular = false;
        break;
      }
    }

    if (!regular) {
      System.out.println("Não é um grafo regular");
    } else {
      System.out.println("É um grafo regular");
    }
    System.out.println();

  }

  public static void grafoCompleto(ArrayList<Vertices> grafo, int[] grauTotalV) {
    boolean completo = true;
    int numVertices = grafo.size(); // Obter o número de vértices do grafo

    for (int i = 0; i < numVertices; i++) {
      // Verificar se o grau total de cada vértice é igual a numVertices - 1
      if (grauTotalV[i] != numVertices - 1) {
        completo = false;
        break;
      }
    }

    if (!completo) {
      System.out.println("Não é um grafo completo");
    } else {
      System.out.println("É um grafo completo");
    }
    System.out.println();
  }

  public static void grafoBipartido(ArrayList<Vertices> grafo) {

  }

  public static void buscaLargura(ArrayList<Vertices> grafo, int vertOrigem) {
    boolean[] visitei = new boolean[grafo.size()];
    Queue<Integer> busca = new LinkedList<>();

    visitei[vertOrigem - 1] = true;
    busca.add(vertOrigem);

    System.out.print("Árvore:\n ");
    while (!busca.isEmpty()) {
      int vertAtual = busca.poll();
      System.out.print(vertAtual);

      boolean primVizinho = true;
      for (Arestas vizinho : grafo.get(vertAtual - 1).arestas) {
        if (!visitei[vizinho.destino - 1]) {
          if (primVizinho) {
            primVizinho = false;
            System.out.print(" -> ");
          } else {
            System.out.print(", ");
          }
          visitei[vizinho.destino - 1] = true;
          busca.add(vizinho.destino);
          System.out.print(vizinho.destino);
        }
      }
      System.out.println();
    }
    System.out.println();
  }

  public static void buscaProfundidade(ArrayList<Vertices> grafo, int vertOrigem) {
    boolean[] visitei = new boolean[grafo.size() + 1];
    int numArvores = 0; // contador para o número de árvores/componentes

    System.out.print("Árvore " + ++numArvores + ": ");
    pesqProfundidade(grafo, vertOrigem, visitei);
    System.out.println();

    for (int i = 1; i <= grafo.size(); i++) {
      if (!visitei[i]) {
        System.out.print("Árvore " + ++numArvores + ": ");
        pesqProfundidade(grafo, i, visitei);
        System.out.println();
      }
    }
  }

  private static void pesqProfundidade(ArrayList<Vertices> grafo, int vertice, boolean[] visitei) {
    visitei[vertice - 1] = true;
    System.out.print(vertice + " ");

    for (Arestas vizinhoAresta : grafo.get(vertice - 1).arestas) {
        int vizinho = vizinhoAresta.destino;
        if (!visitei[vizinho - 1]) {
            System.out.print(" -> ");
            pesqProfundidade(grafo, vizinho, visitei);
        }
    }
}

public static boolean dfsTopologico(ArrayList<Vertices> grafo, int vertice, boolean[] visitei, boolean[] pilhaRec, Stack<Integer> pilha) {
    int indice = vertice - 1; // Obtém o índice real do vértice

    if (pilhaRec[indice]) {
        return true; // Ciclo encontrado
    }

    if (visitei[indice]) {
        return false; // Já foi visitado e não forma ciclo
    }

    visitei[indice] = true;
    pilhaRec[indice] = true;

    for (Arestas vizinhoAresta : grafo.get(indice).arestas) {
        int vizinho = vizinhoAresta.destino;
        if (dfsTopologico(grafo, vizinho, visitei, pilhaRec, pilha)) {
            return true; // Ciclo encontrado
        }
    }

    pilhaRec[indice] = false;
    pilha.push(vertice);

    return false;
}

public static void dfsConexo(ArrayList<Vertices> grafo, int vertice, boolean[] visitei) {
    visitei[vertice - 1] = true;

    for (Arestas vizinhoAresta : grafo.get(vertice - 1).arestas) {
        int vizinho = vizinhoAresta.destino;
        if (!visitei[vizinho - 1]) {
            dfsConexo(grafo, vizinho, visitei);
        }
    }
}

public static void grafoConexo(ArrayList<Vertices> grafo, int numVertices) {
    boolean[] visitado = new boolean[numVertices];
    boolean conexo = true;

    // Realiza a busca em profundidade a partir do primeiro vértice
    dfsConexo(grafo, 1, visitado);

    // Verifica se todos os vértices foram alcançados
    for (int i = 0; i < numVertices; i++) {
        if (!visitado[i]) {
            conexo = false; // Grafo não é conexo
            break;
        }
    }

    if (conexo) {
        System.out.println("O grafo é conexo.");
    } else {
        System.out.println("O grafo não é conexo.");
    }
}

public static void prim(ArrayList<Vertices> grafo) {
  int numVertices = grafo.size();
  boolean[] visitado = new boolean[numVertices];
  int[] custoMinimo = new int[numVertices];
  int[] pai = new int[numVertices];

  for (int i = 0; i < numVertices; i++) {
      custoMinimo[i] = Integer.MAX_VALUE;
  }

  custoMinimo[0] = 0;
  pai[0] = -1;

  for (int i = 0; i < numVertices - 1; i++) {
      int u = minimoCusto(grafo, custoMinimo, visitado);

      visitado[u] = true;

      for (Arestas aresta : grafo.get(u).arestas) {
          int v = aresta.destino;
          int peso = aresta.peso;
          if (!visitado[v] && peso < custoMinimo[v]) {
              pai[v] = u;
              custoMinimo[v] = peso; 
          }
      }
  }

  System.out.println("Árvore geradora mínima (Prim):");
  for (int i = 1; i < numVertices; i++) {
      System.out.println("Aresta: " + (pai[i]+1) + " - " + (i+1) + ", Peso: " + custoMinimo[i]);
  }
}

private static int minimoCusto(ArrayList<Vertices> grafo, int[] custoMinimo, boolean[] visitado) {
  int minimo = Integer.MAX_VALUE;
  int minimoIndice = -1;

  for (int v = 0; v < grafo.size(); v++) {
      if (!visitado[v] && custoMinimo[v] < minimo) {
          minimo = custoMinimo[v];
          minimoIndice = v;
      }
  }

  return minimoIndice;
}


public static void dijkstra(ArrayList<Vertices> grafo, int origem) {
  int numVertices = grafo.size();
  boolean[] visitado = new boolean[numVertices];
  int[] distancia = new int[numVertices];

  for (int i = 0; i < numVertices; i++) {
      distancia[i] = Integer.MAX_VALUE;
  }

  distancia[origem] = 0;

  for (int i = 0; i < numVertices; i++) {
      int u = minimaDistancia(distancia, visitado);

      visitado[u] = true;

      for (Arestas aresta : grafo.get(u).arestas) {
          int v = aresta.destino;
          int peso = aresta.peso;
          if (!visitado[v]) {
            if (distancia[u] != Integer.MAX_VALUE && distancia[u] + peso < distancia[v]) {
                distancia[v] = distancia[u] + peso;
            }
        }
      }
  }

  System.out.println("Caminhos mínimos (Dijkstra) a partir do vértice " + origem + ":");
  for (int i = 0; i < numVertices; i++) {
      System.out.println("Vértice " + (origem+1) + " -> Vértice " + (i+1) + ": " + distancia[i]);
  }
}

private static int minimaDistancia(int[] distancia, boolean[] visitado) {
  int minimo = Integer.MAX_VALUE;
  int minimoIndice = -1;

  for (int v = 0; v < distancia.length; v++) {
      if (!visitado[v] && distancia[v] < minimo) {
          minimo = distancia[v];
          minimoIndice = v;
      }
  }

  return minimoIndice;
}



  public static void menu() {
    Scanner sc = new Scanner(System.in);

    System.out.println("Digite o número de vertices do seu grafo: ");
    int numVertices = Integer.parseInt(sc.nextLine());
    System.out.println("Digite o número de arestas do seu grafo: ");
    int numArestas = Integer.parseInt(sc.nextLine());

    ArrayList<Vertices> grafo = new ArrayList<Vertices>();
    for (int i = 0; i < numVertices; i++) {
      grafo.add(new Vertices(i + 1));
    }

    for (int i = 0; i < numArestas; i++) {
      
      System.out.println("Digite as arestas, formato - 1,2,peso: ");
      String combinacao = sc.nextLine();

      String[] aux = combinacao.split(",");
      int origem = Integer.parseInt(aux[0]) - 1;
      int destino = Integer.parseInt(aux[1]) - 1;
      int peso = Integer.parseInt(aux[2]);

      grafo.get(origem).arestas.add(new Arestas(destino + 1, peso));
      grafo.get(origem).rotulo = origem + 1;
    }

    int[] grauTotalV = new int[numVertices];
    grauVertice(grafo, grauTotalV);

    int op = 0;
    do {
      System.out.println("======= MENU =======");
      System.out.println("[ 1 ] - Criar Arestas");
      System.out.println("[ 2 ] - Remover Arestas");
      System.out.println("[ 3 ] - Sucessores");
      System.out.println("[ 4 ] - Predecessores");
      System.out.println("[ 5 ] - Grau de cada Vértice");
      System.out.println("[ 6 ] - Verificação do Grafo (Bipartido)");
      System.out.println("[ 7 ] - Busca em Largura");
      System.out.println("[ 8 ] - Busca em Profundidade");
      System.out.println("[ 9 ] - Árvore geradora mínima**");
      System.out.println("[ 10 ] - Caminho mínimo entre dois vértices**");
      System.out.println("[ 0 ] - Sair");
      System.out.println("Qual opção você deseja? ");
      op = Integer.parseInt(sc.nextLine());

      switch (op) {
        case 1: {
          sc.nextLine();
          System.out.println("Digite a aresta que deseja adicionar - Exemplo: 1,2,peso: ");
          String novaAresta = sc.nextLine();
          adicionaAresta(grafo, novaAresta);
          break;
        }
        case 2: {
          sc.nextLine();
          System.out.println("Digite a aresta que deseja remover - Exemplo: 1,2: ");
          String removerAresta = sc.nextLine();
          removeAresta(grafo, removerAresta);
          break;
        }
        case 3: {
          printSucessores(grafo);
          break;
        }
        case 4: {
          printPredecessores(grafo);
          break;
        }
        case 5: {
          imprimeGrauVertice(grafo);
          break;
        }
        case 6: {
          System.out.println("\n\n");
          grafoSimples(grafo);
          System.out.println("\n");
          grafoRegular(grafo, grauTotalV);
          System.out.println("\n");
          grafoCompleto(grafo, grauTotalV);
          System.out.println("\n");
          grafoConexo(grafo, numVertices);
          break;
        }
        case 7: {
          System.out.println("\n\n");
          System.out.println("Digite o vértice de início para a busca em largura:");
          int inicio = Integer.parseInt(sc.nextLine());
          buscaLargura(grafo, inicio);
          break;
        }
        case 8: {
          System.out.println("Digite o vértice de início para a busca em profundidade:");
          int inicio = Integer.parseInt(sc.nextLine());
          buscaProfundidade(grafo, inicio);
          break;
        }
        case 9: {
          prim(grafo);
          break;
      }
      case 10: {
          System.out.println("Digite o vértice de origem para encontrar o caminho mínimo:");
          int origem10 = Integer.parseInt(sc.nextLine());
          dijkstra(grafo, origem10);
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
  }
}