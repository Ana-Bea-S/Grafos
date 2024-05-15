import java.util.*;

public class ListaAdjNDirec {
  public static void adicionaAresta(ArrayList<Vertices> grafo, String aresta) {
    String[] aux = aresta.split(",");
    int origem = Integer.parseInt(aux[0]) - 1;
    int destino = Integer.parseInt(aux[1]) - 1;
    int peso = Integer.parseInt(aux[2]);

    grafo.get(origem).arestas.add(new Arestas(destino, peso));
  }

  public static void removeAresta(ArrayList<Vertices> grafo, String aresta) {
    String[] aux = aresta.split(",");
    int origem = Integer.parseInt(aux[0]) - 1;
    int destino = Integer.parseInt(aux[1]) - 1;
    int peso = Integer.parseInt(aux[2]);

    // Remove a aresta do vértice de origem
    ArrayList<Arestas> verticeOrigem = grafo.get(origem).arestas;
    for (int i = 0; i < verticeOrigem.size(); i++) {
      Arestas arestaAtual = verticeOrigem.get(i);
      if (arestaAtual.destino == destino && arestaAtual.peso == peso) {
        verticeOrigem.remove(i);
        break;
      }
    }

    // Remove a aresta do vértice de destino (caso seja diferente do vértice de
    // origem)
    if (origem != destino) {
      ArrayList<Arestas> verticeDestino = grafo.get(destino).arestas;
      for (int i = 0; i < verticeDestino.size(); i++) {
        Arestas arestaAtual = verticeDestino.get(i);
        if (arestaAtual.destino == origem && arestaAtual.peso == peso) {
          verticeDestino.remove(i);
          break;
        }
      }
    }
  }

  public static void imprimeVizinhos(ArrayList<Vertices> grafo) {
    System.out.println("\nVizinhos:");
    for (int i = 0; i < grafo.size(); i++) {
      Vertices vertice = grafo.get(i);
      System.out.print("Vértice " + vertice.rotulo + ": [");

      // Percorrendo todas as arestas do vértice
      for (int j = 0; j < vertice.arestas.size(); j++) {
        Arestas aresta = vertice.arestas.get(j);

        // Imprimindo o destino da aresta
        System.out.print(aresta.destino + 1 + "(" + aresta.peso + ")");
        if (j < vertice.arestas.size() - 1) {
          System.out.print(", ");
        }

        // Se a aresta não for a última, também imprime a origem (caso não seja um laço)
        if (j == vertice.arestas.size() - 1 && aresta.destino == i) {
          System.out.print(", " + (i + 1) + "(" + aresta.peso + ")");
        }
      }
      System.out.println("]");
    }
  }

  public static void imprimeGrauVertice(ArrayList<Vertices> grafo) {
    for (int i = 0; i < grafo.size(); i++) {
      int grau = grafo.get(i).arestas.size();
      System.out.println("O grau do vértice " + (i + 1) + " é: " + grau);
    }
  }

  public static void grafoSimples(ArrayList<Vertices> grafo) {
    boolean simples = true;

    for (Vertices vertice : grafo) {
      ArrayList<Integer> destinos = new ArrayList<>();
      for (Arestas aresta : vertice.arestas) {
        if (destinos.contains(aresta.destino) || aresta.destino == grafo.indexOf(vertice)) {
          simples = false;
          break;
        }
        destinos.add(aresta.destino);
      }
      if (!simples)
        break;
    }

    if (!simples) {
      System.out.println("Não é um grafo simples");
    } else {
      System.out.println("É um grafo simples");
    }
    System.out.println();
  }

  public static void grafoRegular(ArrayList<Vertices> grafo) {
    boolean regular = true;
    int grau = grafo.get(0).arestas.size(); // Grau do primeiro vértice
    for (int i = 1; i < grafo.size(); i++) {
      if (grafo.get(i).arestas.size() != grau) {
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

  public static void grafoCompleto(ArrayList<Vertices> grafo) {
    boolean completo = true;
    int numVertices = grafo.size();
    for (int i = 0; i < numVertices; i++) {
      Vertices vertice = grafo.get(i);
      if (vertice.arestas.size() != numVertices - 1) {
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
    int numVertices = grafo.size();
    int[] cores = new int[numVertices]; // Array para armazenar as cores dos vértices
    Arrays.fill(cores, -1); // Inicializa todas as cores como não atribuídas (-1)
    Queue<Integer> fila = new LinkedList<>();
    boolean bipartido = true;

    for (int i = 0; i < numVertices; i++) {
      if (cores[i] == -1 && bipartido) { // Se a cor ainda não foi atribuída ao vértice e o grafo ainda é considerado
                                         // bipartido
        fila.add(i);
        cores[i] = 0; // Atribui a cor 0 ao vértice atual
        while (!fila.isEmpty()) {
          int verticeAtual = fila.poll();
          for (Arestas aresta : grafo.get(verticeAtual).arestas) {
            int vizinho = aresta.destino;
            if (cores[vizinho] == -1) { // Se a cor do vizinho ainda não foi atribuída
              cores[vizinho] = 1 - cores[verticeAtual]; // Atribui a cor oposta ao vértice atual
              fila.add(vizinho);
            } else if (cores[vizinho] == cores[verticeAtual]) { // Se houver conflito de cores
              bipartido = false; // O grafo não é bipartido
              break;
            }
          }
        }
      }
    }

    if (bipartido) {
      System.out.println("O grafo é bipartido.");
    } else {
      System.out.println("O grafo não é bipartido.");
    }
  }

  public static void buscaLargura(ArrayList<Vertices> grafo, int vertOrigem) {
    boolean[] visitei = new boolean[grafo.size()];
    Queue<Integer> busca = new LinkedList<>();

    visitei[vertOrigem - 1] = true;
    busca.add(vertOrigem - 1);

    System.out.print("Árvore:\n ");
    while (!busca.isEmpty()) {
      int vertAtual = busca.poll();
      System.out.print(vertAtual + 1);

      boolean primVizinho = true;
      for (Arestas aresta : grafo.get(vertAtual).arestas) {
        int vizinho = aresta.destino;
        if (!visitei[vizinho]) {
          if (primVizinho) {
            primVizinho = false;
            System.out.print(" -> ");
          } else {
            System.out.print(", ");
          }
          visitei[vizinho] = true;
          busca.add(vizinho);
          System.out.print(vizinho + 1);
        }
      }
      System.out.println();
    }
    System.out.println();
  }

  public static void buscaProfundidade(ArrayList<Vertices> grafo, int vertOrigem) {
    boolean[] visitei = new boolean[grafo.size()];
    int numArvores = 0; // contador para o número de árvores/componentes

    System.out.print("Árvore " + ++numArvores + ": ");
    pesqProfundidade(grafo, vertOrigem - 1, visitei);
    System.out.println();

    for (int i = 0; i < grafo.size(); i++) {
      if (!visitei[i]) {
        System.out.print("Árvore " + ++numArvores + ": ");
        pesqProfundidade(grafo, i, visitei);
        System.out.println();
      }
    }
  }

  private static void pesqProfundidade(ArrayList<Vertices> grafo, int vertice, boolean[] visitei) {
    visitei[vertice] = true;
    System.out.print(vertice + 1 + " ");

    for (Arestas aresta : grafo.get(vertice).arestas) {
      int vizinho = aresta.destino;
      if (!visitei[vizinho]) {
        System.out.print(" -> ");
        pesqProfundidade(grafo, vizinho, visitei);
      }
    }
  }

  public static void dfsConexo(ArrayList<Vertices> grafo, int vertice, boolean[] visitei) {
    visitei[vertice] = true;

    for (Arestas aresta : grafo.get(vertice).arestas) {
      int vizinho = aresta.destino;
      if (!visitei[vizinho]) {
        dfsConexo(grafo, vizinho, visitei);
      }
    }
  }

  public static void grafoConexo(ArrayList<Vertices> grafo, int numVertices) {
    boolean[] visitado = new boolean[numVertices];

    // Realiza a busca em profundidade a partir do primeiro vértice
    dfsConexo(grafo, 0, visitado);

    // Verifica se todos os vértices foram alcançados
    for (int i = 0; i < numVertices; i++) {
      if (!visitado[i]) {
        System.out.println("O grafo não é conexo.");
        return;
      }
    }
    System.out.println("O grafo é conexo.");
  }

  public static void AGMPrim(ArrayList<Vertices> grafo) {
    int numVertices = grafo.size();
    int[] pai = new int[numVertices]; // Array para armazenar o pai de cada vértice na MST
    int[] chave = new int[numVertices]; // Array para armazenar as chaves (pesos mínimos) de cada vértice
    boolean[] mstSet = new boolean[numVertices]; // Array para verificar se um vértice está incluído na MST

    // Inicializa todas as chaves como infinito e todos os vértices como não
    // incluídos na MST
    for (int i = 0; i < numVertices; i++) {
      chave[i] = Integer.MAX_VALUE;
      mstSet[i] = false;
    }

    // Raiz da MST é o primeiro vértice
    chave[0] = 0;
    pai[0] = -1; // Não há pai do primeiro vértice na MST

    // Construindo a MST
    for (int count = 0; count < numVertices - 1; count++) {
      // Encontra o vértice com a chave mínima ainda não incluído na MST
      int u = minAGM(chave, mstSet);

      // Inclui o vértice na MST
      mstSet[u] = true;

      // Atualiza as chaves dos vértices adjacentes do vértice incluído recentemente
      for (Arestas aresta : grafo.get(u).arestas) {
        int v = aresta.destino;
        int peso = aresta.peso;
        if (!mstSet[v] && peso < chave[v]) {
          pai[v] = u;
          chave[v] = peso;
        }
      }
    }

    // Imprime a MST
    System.out.println("Arestas da Árvore Geradora Mínima:");
    for (int i = 1; i < numVertices; i++) {
      System.out.println(pai[i] + 1 + " -> " + (i + 1) + " Peso: " + chave[i]);
    }
  }

  // Função auxiliar para encontrar o vértice com a chave mínima ainda não
  // incluído na MST
  private static int minAGM(int[] chave, boolean[] mstSet) {
    int min = Integer.MAX_VALUE, minIndex = -1;
    for (int v = 0; v < chave.length; v++) {
      if (!mstSet[v] && chave[v] < min) {
        min = chave[v];
        minIndex = v;
      }
    }
    return minIndex;
  }

  public static void dijkstra(ArrayList<Vertices> grafo, int origem, int destino) {
    int numVertices = grafo.size();

    // Array para armazenar a distância mínima de origem para cada vértice
    int[] distancia = new int[numVertices];
    Arrays.fill(distancia, Integer.MAX_VALUE);
    distancia[origem] = 0;

    // Array para marcar se o vértice já foi visitado
    boolean[] visitado = new boolean[numVertices];

    // Enquanto não visitarmos todos os vértices
    for (int count = 0; count < numVertices - 1; count++) {
      // Encontra o vértice não visitado mais próximo da origem
      int u = minDistancia(distancia, visitado);

      // Marca o vértice como visitado
      visitado[u] = true;

      // Atualiza a distância mínima dos vértices adjacentes ao vértice selecionado
      for (Arestas aresta : grafo.get(u).arestas) {
        int v = aresta.destino;
        int peso = aresta.peso;
        if (!visitado[v] && distancia[u] != Integer.MAX_VALUE && distancia[u] + peso < distancia[v]) {
          distancia[v] = distancia[u] + peso;
        }
      }
    }

    // Imprime a distância mínima do vértice de origem para o vértice de destino
    System.out
        .println("A distância mínima de " + (origem + 1) + " para " + (destino + 1) + " é: " + distancia[destino]);
  }

  // Função auxiliar para encontrar o vértice com a menor distância não visitado
  private static int minDistancia(int[] distancia, boolean[] visitado) {
    int min = Integer.MAX_VALUE, minIndex = -1;
    for (int v = 0; v < distancia.length; v++) {
      if (!visitado[v] && distancia[v] <= min) {
        min = distancia[v];
        minIndex = v;
      }
    }
    return minIndex;
  }

  public static void menu() {
    Scanner sc = new Scanner(System.in);

    System.out.println("Digite o número de vertices do seu grafo: ");
    int numVertices = sc.nextInt();
    System.out.println("Digite o número de arestas do seu grafo: ");
    int numArestas = sc.nextInt();
    // alocando para receber as futuras listas
    ArrayList<Vertices> grafo = new ArrayList<Vertices>();
    // aqui está criando a lista inicial que futuramente receberá suas arestas, ex:
    // 1, 2, 3 e 4.
    for (int i = 0; i < numVertices; i++) {
      grafo.add(new Vertices(i + 1));
    }

    // aqui está adicionando as arestas no grafo
    for (int i = 0; i < numArestas; i++) {
      sc.nextLine(); // Consumir quebra de linha
      System.out.println("Digite as arestas, formato - 1,2,peso: ");
      String combinacao = sc.next();

      String[] aux = combinacao.split(",");
      int origem = Integer.parseInt(aux[0]) - 1;
      int destino = Integer.parseInt(aux[1]) - 1;
      int peso = Integer.parseInt(aux[2]);

      grafo.get(origem).arestas.add(new Arestas(destino, peso));
      grafo.get(destino).arestas.add(new Arestas(origem, peso));

      grafo.get(origem).rotulo = origem + 1;
    }

    int op = 0;
    do {
      System.out.println("======= MENU =======");
      System.out.println("[ 1 ] - Criar Arestas");
      System.out.println("[ 2 ] - Remover Arestas");
      System.out.println("[ 3 ] - Vizinhança");
      System.out.println("[ 4 ] - Grau de cada Vértice");
      System.out.println("[ 5 ] - Verificação do Grafo");
      System.out.println("[ 6 ] - Busca em Largura");
      System.out.println("[ 7 ] - Busca em Profundidade");
      System.out.println("[ 8 ] - Árvore Geradora Mínima");
      System.out.println("[ 9 ] - Caminho mínimo entre dois vértices");
      System.out.println("[ 0 ] - Sair");
      System.out.println("Qual opção você deseja? ");
      op = sc.nextInt();

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
          System.out.println("Digite a aresta que deseja remover - Exemplo: 1,2,peso: ");
          String removerAresta = sc.nextLine();
          removeAresta(grafo, removerAresta);

          break;

        }
        case 3: {

          imprimeVizinhos(grafo);

          break;

        }
        case 4: {
          imprimeGrauVertice(grafo);

          break;

        }
        case 5: {

          // verificação se o grafo é simples
          grafoSimples(grafo);
          System.out.println("\n");

          // verificação se o grafo é regular
          grafoRegular(grafo);
          System.out.println("\n");

          // verificação se o grafo é completo
          grafoCompleto(grafo);
          System.out.println("\n");
          // verificação se o grafo é bipartido
          grafoBipartido(grafo);
          System.out.println("\n");
          // verificação se o grafo é conexo
          grafoConexo(grafo, numVertices);

          break;

        }
        case 6: {

          System.out.println("Digite o vértice de início para a busca em largura:");
          int inicio = sc.nextInt();
          buscaLargura(grafo, inicio);

          break;
        }
        case 7: {
          System.out.println("Digite o vértice de início para a busca em profundidade:");
          int inicio = sc.nextInt();
          buscaProfundidade(grafo, inicio);

          break;
        }
        case 8: {
          AGMPrim(grafo);
          break;
        }
        case 9: {
          System.out.println("Digite o vértice de origem: ");
          int origemDij = sc.nextInt();

          System.out.println("Digite o vértice de destino: ");
          int destinoDij = sc.nextInt();

          // Executa o algoritmo de Dijkstra
          dijkstra(grafo, origemDij - 1, destinoDij - 1);
          break;
        }
        case 0: {

          System.out.println("Até logo!");
          break;

        }
        default: {

          System.out.println("Opção inválida, tente novamente");

          break;

        }
      }

    } while (op != 0);

  }

}