import java.util.*;

public class ListaAdjNDirec {
  public static void adicionaAresta(ArrayList<Vertices> grafo, String aresta) {

    String[] aux = aresta.split(",");
    int origem = Integer.parseInt(aux[0]) - 1;
    int destino = Integer.parseInt(aux[1]) - 1;

    grafo.get(origem).arestas.add(destino);
    grafo.get(destino).arestas.add(origem);

  }

  public static void removeAresta(ArrayList<Vertices> grafo, String aresta) {
    String[] aux = aresta.split(",");
    int origem = Integer.parseInt(aux[0]) - 1;
    int destino = Integer.parseInt(aux[1]) - 1;
    ArrayList<Integer> verticeO = grafo.get(origem).arestas;
    ArrayList<Integer> verticeD = grafo.get(destino).arestas;

    for (int i = 0; i < verticeO.size(); i++) {
      if (verticeO.get(i) == destino) {
        grafo.get(origem).arestas.remove(i);
      }
    }
    if (grafo.get(origem).arestas.size() == 0) {
      boolean flag = true;
      for (int i = 0; i < grafo.size(); i++) {
        ArrayList<Integer> arestas = grafo.get(i).arestas;
        for (int j = 0; j < arestas.size(); j++) {
          if (origem + 1 == arestas.get(j)) {
            flag = false;
            break;
          }
        }
      }
      if (flag) {
        grafo.remove(origem);
      }
    }

    for (int i = 0; i < verticeD.size(); i++) {
      if (verticeD.get(i) == origem) {
        grafo.get(destino).arestas.remove(i);
      }
    }
    if (grafo.get(destino).arestas.size() == 0) {
      boolean flag = true;
      for (int i = 0; i < grafo.size(); i++) {
        ArrayList<Integer> arestas = grafo.get(i).arestas;
        for (int j = 0; j < arestas.size(); j++) {
          if (origem + 1 == arestas.get(j)) {
            flag = false;
            break;
          }
        }
      }
      if (flag) {
        grafo.remove(destino);
      }
    }
  }

  public static void imprimeVizinhos(ArrayList<Vertices> grafo) {
    System.out.println("\nVizinhos:");
    for (int i = 0; i < grafo.size(); i++) {
      Vertices vertice = grafo.get(i);
      System.out.print("Vértice " + vertice.rotulo + ": [");
      for (int j = 0; j < vertice.arestas.size(); j++) {
        // encontra o vértice adjacente à aresta atual
        int verticeAdj = vertice.arestas.get(j);

        System.out.print(verticeAdj + 1);
        // aqui ta verificando se tem mais vizinhos
        if (j < vertice.arestas.size() - 1) {
          System.out.print(", ");
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

  // GRAFO SIMPLES COM ERRO E BIPARTIDO
  public static void grafoSimples(ArrayList<Vertices> grafo) {
    boolean simples = true;

    // Itera sobre cada vértice no grafo
    for (Vertices vertice : grafo) {
      ArrayList<Integer> arestas = vertice.arestas;

      // Verifica se não existe duplicação entre as arestas
      for (int i = 0; i < arestas.size(); i++) {
        int vizinho = arestas.get(i);
        for (int j = i + 1; j < arestas.size(); j++) {
          if (vizinho == arestas.get(j)) {
            simples = false;
            break;
          }
        }
        // Verifica se há laço
        if (vizinho == vertice.rotulo) {
          simples = false;
          break;
        }
      }

      if (!simples) {
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
      System.out.println("Digite as arestas, formato - 1,2: ");
      String combinacao = sc.next();

      String[] aux = combinacao.split(",");
      int origem = Integer.parseInt(aux[0]) - 1;
      int destino = Integer.parseInt(aux[1]) - 1;

      grafo.get(origem).arestas.add(destino);
      grafo.get(destino).arestas.add(origem);

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
      System.out.println("[ 0 ] - Sair");
      System.out.println("Qual opção você deseja? ");
      op = sc.nextInt();

      switch (op) {
        case 1: {
          System.out.println("\n\n");

          sc.nextLine();
          System.out.println("Digite a aresta que deseja adicionar - Exemplo: 1,2: ");
          String novaAresta = sc.nextLine();
          adicionaAresta(grafo, novaAresta);

          System.out.println("\n\n\n\n");

          break;

        }
        case 2: {
          System.out.println("\n\n");

          sc.nextLine();
          System.out.println("Digite a aresta que deseja remover - Exemplo: 1,2: ");
          String removerAresta = sc.nextLine();
          removeAresta(grafo, removerAresta);
          System.out.println("\n\n\n\n");
          break;

        }
        case 3: {

          imprimeVizinhos(grafo);
          System.out.println("\n\n\n\n");
          break;

        }
        case 4: {
          imprimeGrauVertice(grafo);
          System.out.println("\n\n\n\n");
          break;

        }
        case 5: {
          System.out.println("\n\n");
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
    sc.close();
  }

}