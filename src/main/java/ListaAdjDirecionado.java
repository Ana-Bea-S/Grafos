import java.util.*;

public class ListaAdjDirecionado {

  public static void adicionaAresta(ArrayList<Vertices2> grafo, String aresta) {
    String[] aux = aresta.split(",");
    int origem = Integer.parseInt(aux[0]) - 1;
    int destino = Integer.parseInt(aux[1]) - 1;
    int peso = Integer.parseInt(aux[2]); 


    grafo.get(origem).arestas.add(new Arestas(destino, peso));
    grafo.get(origem).grauSaida++;
    grafo.get(destino).grauEntrada++;
  }

  public static void removeAresta(ArrayList<Vertices2> grafo, String aresta) {
    String[] aux = aresta.split(",");
    int origem = Integer.parseInt(aux[0]) - 1;
    int destino = Integer.parseInt(aux[1]) - 1;
    int peso = Integer.parseInt(aux[2]); 

    ArrayList<Arestas> arestasOrigem = grafo.get(origem).arestas;
    ArrayList<Arestas> arestasDestino = grafo.get(destino).arestas;

    // Remove a aresta do vértice de origem
    for (int i = 0; i < arestasOrigem.size(); i++) {
        Arestas a = arestasOrigem.get(i);
        if (a.destino == destino && a.peso == peso) {
            arestasOrigem.remove(i);
            break;
        }
    }

    // Remove a aresta correspondente do vértice de destino
    for (int j = 0; j < arestasDestino.size(); j++) {
        Arestas a = arestasDestino.get(j);
        if (a.destino == destino && a.peso == peso) {
            arestasDestino.remove(j);
            break;
        }
    }

    // Atualize o grau de saída do vértice de origem e de entrada do vértice de destino
    grafo.get(origem).grauSaida--;
    grafo.get(destino).grauEntrada--;
}



public static void printPredecessores(ArrayList<Vertices2> grafo) {
  ArrayList<Vertices2> predecessores = new ArrayList<Vertices2>();
  for (int i = 0; i < grafo.size(); i++) {
      predecessores.add(new Vertices2(grafo.get(i).rotulo));
  }

  System.out.println("\nPredecessores:");
  for (int i = 0; i < grafo.size(); i++) {
      Vertices2 vertice = grafo.get(i);
      for (Arestas aresta : vertice.arestas) {
          // Adiciona o vértice de origem e o peso da aresta como predecessores do vértice de destino
          predecessores.get(aresta.destino - 1).arestas.add(new Arestas(vertice.rotulo, aresta.peso));
      }
  }
  for (int i = 0; i < predecessores.size(); i++) {
      Vertices2 vertice = predecessores.get(i);
      System.out.print("Vértice " + vertice.rotulo + ": [");
      for (int j = 0; j < vertice.arestas.size(); j++) {
          System.out.print("(" + vertice.arestas.get(j).destino + "," + vertice.arestas.get(j).peso + ")");
          if (j < vertice.arestas.size() - 1) {
              System.out.print(", ");
          }
      }
      System.out.println("]");
  }
}



  public static void printSucessores(ArrayList<Vertices2> grafo) {
    System.out.println("\nSucessores:");
    for (int i = 0; i < grafo.size(); i++) {
      Vertices2 vertice = grafo.get(i);
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

  public static void grauVertice(ArrayList<Vertices2> grafo, int[] grauTotalV) {
    for (int i = 0; i < grafo.size(); i++) {
      Vertices2 vertice = grafo.get(i);
      int grauEnt = 0;
      int grauSai = vertice.arestas.size();

      for (Vertices2 outroVertice : grafo) {
        for (Arestas aresta : outroVertice.arestas) {
          if (aresta.destino == vertice.rotulo) {
            grauEnt++;
          }
        }
      }

      grauTotalV[i] = grauEnt + grauSai;
    }
  }

  public static void imprimeGrauVertice(ArrayList<Vertices2> grafo) {
    System.out.println("\nGrau de cada Vértice:");
    for (Vertices2 vertice : grafo) {
        int grauEntrada = 0;

        // Calcula o grau de entrada contando quantas arestas apontam para o vértice
        for (Vertices2 outroVertice : grafo) {
            for (Arestas aresta : outroVertice.arestas) {
                if (aresta.destino == vertice.rotulo) {
                    grauEntrada++;
                }
            }
        }

        // O grau de saída é obtido diretamente do atributo grauSaida do vértice
        int grauSaida = vertice.arestas.size();

        System.out.println("Vértice " + vertice.rotulo + ": Grau de Entrada = " + grauEntrada + ", Grau de Saída = " + grauSaida);
    }
}

  public static void grafoSimples(ArrayList<Vertices2> grafo) {
    boolean simples = true;

    for (int i = 0; i < grafo.size(); i++) {
      Vertices2 vertice = grafo.get(i);
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

  public static void grafoRegular(ArrayList<Vertices2> grafo, int[] grauTotalV) {
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

  public static void grafoCompleto(ArrayList<Vertices2> grafo, int[] grauTotalV) {
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

  public static boolean verificaBipartido(ArrayList<Vertices2> grafo, int vertice, int[] cores) {
    Queue<Integer> fila = new LinkedList<>();
    fila.add(vertice);
    cores[vertice] = 1; // Inicia com a cor 1

    while (!fila.isEmpty()) {
        int atual = fila.poll();
        int corAtual = cores[atual];

        for (Arestas aresta : grafo.get(atual).arestas) {
            int vizinho = aresta.destino - 1; // Ajuste para acessar o índice correto
            if (cores[vizinho] == 0) {
                cores[vizinho] = 3 - corAtual; // Atribui a outra cor ao vizinho
                fila.add(vizinho);
            } else if (cores[vizinho] == corAtual) {
                return false; // Encontrou um vértice adjacente com a mesma cor, não é bipartido
            }
        }
    }

    return true; // Todos os vértices foram coloridos sem conflitos de cor, é bipartido
}

public static void grafoBipartido(ArrayList<Vertices2> grafo) {
    int[] cores = new int[grafo.size()];
    Arrays.fill(cores, 0); // Inicializa todas as cores como não atribuídas (0)

    boolean bipartido = true;
    for (int i = 0; i < grafo.size(); i++) {
        if (cores[i] == 0) {
            if (!verificaBipartido(grafo, i, cores)) {
                bipartido = false;
                break;
            }
        }
    }

    if (bipartido) {
        System.out.println("O grafo é bipartido.");
    } else {
        System.out.println("O grafo não é bipartido.");
    }
}

  public static void buscaLargura(ArrayList<Vertices2> grafo, int vertOrigem) {
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

  public static void buscaProfundidade(ArrayList<Vertices2> grafo, int vertOrigem) {
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

  private static void pesqProfundidade(ArrayList<Vertices2> grafo, int vertice, boolean[] visitei) {
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

public static void dfsConexo(ArrayList<Vertices2> grafo, int vertice, boolean[] visitei) {
    visitei[vertice - 1] = true;

    for (Arestas vizinhoAresta : grafo.get(vertice - 1).arestas) {
        int vizinho = vizinhoAresta.destino;
        if (!visitei[vizinho - 1]) {
            dfsConexo(grafo, vizinho, visitei);
        }
    }
}

public static void grafoConexo(ArrayList<Vertices2> grafo, int numVertices) {
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

public static void dijkstra(ArrayList<Vertices2> grafo, int origem, int destino) {
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
          int v = aresta.destino -1;
          int peso = aresta.peso;
          if (!visitado[v]) {
              if (distancia[u] != Integer.MAX_VALUE && distancia[u] + peso < distancia[v]) {
                  distancia[v] = distancia[u] + peso;
              }
          }
      }
  }
  System.out.println("Caminho mínimo entre o vértice " + (origem + 1) + " e o vértice " + (destino + 1) + ": " + distancia[destino]);
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

public static void dfsTopologico(ArrayList<Vertices2> grafo, int vertice, boolean[] visitei, boolean[] pilhaRec, Stack<Integer> pilha) {
  int indice = vertice - 1; // Obtém o índice real do vértice

  if (pilhaRec[indice]) {
      System.out.println("O grafo contém um ciclo!");
      return; // Retorna imediatamente se um ciclo for encontrado
  }

  if (visitei[indice]) {
      return; // Retorna se o vértice já foi visitado
  }

  visitei[indice] = true;
  pilhaRec[indice] = true;

  for (Arestas vizinhoAresta : grafo.get(indice).arestas) {
      int vizinho = vizinhoAresta.destino;
      dfsTopologico(grafo, vizinho, visitei, pilhaRec, pilha);
  }

  pilhaRec[indice] = false;
  pilha.push(vertice); // Adiciona o vértice à pilha após a visita de todos os seus vizinhos
}

public static void ordenacaoTopologica(ArrayList<Vertices2> grafo) {
  boolean[] visitei = new boolean[grafo.size()];
  boolean[] pilhaRec = new boolean[grafo.size()];
  Stack<Integer> pilha = new Stack<>();

  for (int i = 0; i < grafo.size(); i++) {
      if (!visitei[i]) {
          dfsTopologico(grafo, i + 1, visitei, pilhaRec, pilha);
      }
  }

  // Imprime a ordenação topológica
  System.out.println("\nOrdenação Topológica:");
  while (!pilha.isEmpty()) {
      System.out.print(pilha.pop() + " ");
  }
  System.out.println();
}



  public static void menu() {
    Scanner sc = new Scanner(System.in);

    System.out.println("Digite o número de vertices do seu grafo: ");
    int numVertices = Integer.parseInt(sc.nextLine());
    System.out.println("Digite o número de arestas do seu grafo: ");
    int numArestas = Integer.parseInt(sc.nextLine());

    ArrayList<Vertices2> grafo = new ArrayList<Vertices2>();
    for (int i = 0; i < numVertices; i++) {
      grafo.add(new Vertices2(i + 1));
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
      System.out.println("[ 6 ] - Verificação do Grafo");
      System.out.println("[ 7 ] - Busca em Largura");
      System.out.println("[ 8 ] - Busca em Profundidade");
      System.out.println("[ 9 ] - Ordenação Topológica");
      System.out.println("[ 10 ] - Caminho mínimo entre dois vértices");
      System.out.println("[ 0 ] - Sair");
      System.out.println("Qual opção você deseja? ");
      op = Integer.parseInt(sc.nextLine());

      switch (op) {
        case 1: {
          System.out.println("Digite a aresta que deseja adicionar - Exemplo: 1,2,peso: ");
          String novaAresta = sc.nextLine();
          adicionaAresta(grafo, novaAresta);
          break;
        }
        case 2: {
          System.out.println("Digite a aresta que deseja remover - Exemplo: 1,2,peso: ");
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
          grafoBipartido(grafo);
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
          ordenacaoTopologica(grafo);
          break;
      }
      case 10: {
        System.out.println("Digite o vértice de origem para encontrar o caminho mínimo:");
        int origemDij = Integer.parseInt(sc.nextLine());
        System.out.println("Digite o vértice final para o caminho mínimo:");
        int destinoDij = Integer.parseInt(sc.nextLine());
        dijkstra(grafo, origemDij -1, destinoDij -1);
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