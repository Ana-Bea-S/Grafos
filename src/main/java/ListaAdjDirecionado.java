import java.util.*;

public class ListaAdjDirecionado {
  public static void adicionaAresta(ArrayList<Vertices> grafo, String aresta) {
    String[] aux = aresta.split(",");

    int origem = Integer.parseInt(aux[0]) - 1;
    int destino = Integer.parseInt(aux[1]) - 1;

    grafo.get(origem).arestas.add(destino + 1);
  }

  public static void removeAresta(ArrayList<Vertices> grafo, String aresta) {
    String[] aux = aresta.split(",");
    int origem = Integer.parseInt(aux[0]) - 1;
    int destino = Integer.parseInt(aux[1]) - 1;
    ArrayList<Integer> vertice = grafo.get(origem).arestas;

    for (int i = 0; i < vertice.size(); i++) {
      if (vertice.get(i) == destino + 1) {
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
  }

  public static void printPredecessores(ArrayList<Vertices> grafo) {
    ArrayList<Vertices> predecessores = new ArrayList<Vertices>();
    for (int i = 0; i < grafo.size(); i++) {
      predecessores.add(new Vertices(grafo.get(i).rotulo));
    }

    System.out.println("\nPredecessores:");
    for (int i = 0; i < grafo.size(); i++) {
      Vertices vertice = grafo.get(i);
      for (int j = 0; j < vertice.arestas.size(); j++) {
        predecessores.get(vertice.arestas.get(j) - 1).arestas.add(vertice.rotulo);
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
      // aqui está imprimindo o rotulo do vértice, ex: 1, 2, 3, 4
      Vertices vertice = grafo.get(i);
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

  public static void grauVertice(ArrayList<Vertices> grafo, int[] grauTotalV) {
    for (int i = 0; i < grafo.size(); i++) {
      Vertices vertice = grafo.get(i);
      int grauEnt = 0; // Inicializa o grau de entrada para cada vértice
      int grauSai = vertice.arestas.size(); // Obtém o grau de saída

      for (int j = 0; j < grafo.size(); j++) {
        Vertices outroVertice = grafo.get(j);
        for (int k = 0; k < outroVertice.arestas.size(); k++) {
          if (outroVertice.arestas.get(k) == vertice.rotulo) {
            grauEnt++;
          }
        }
      }

      grauTotalV[i] = grauEnt + grauSai; // Calcula o grau total do vértice
    }
  }

  public static void imprimeGrauVertice(ArrayList<Vertices> grafo) {
    System.out.println("\nGrau de cada Vértice:");
    for (int i = 0; i < grafo.size(); i++) {
      Vertices vertice = grafo.get(i);
      int grauEntrada = 0;
      // Calcula o grau de entrada
      for (Vertices outroVertice : grafo) {
        for (int k = 0; k < outroVertice.arestas.size(); k++) {
          if (outroVertice.arestas.get(k) == vertice.rotulo) {
            grauEntrada++;
          }
        }
      }
      // Calcula o grau de saída
      int grauSaida = vertice.arestas.size();
      // Imprime o grau de entrada, o grau de saída e o grau total do vértice
      System.out.println("Vértice " + vertice.rotulo + ": Grau de Entrada = " + grauEntrada + ", Grau de Saída = "
          + grauSaida);
    }
  }

  public static void grafoSimples(ArrayList<Vertices> grafo) {
    boolean simples = true;
    for (int i = 0; i < grafo.size(); i++) {
      Vertices vertice = grafo.get(i);
      ArrayList<Integer> arestas = vertice.arestas;

      // Verifica se não existe duplicação entre as arestas, se tiver, não é um grafo
      // simples
      for (int j = 0; j < arestas.size() - 1; j++) {
        for (int k = j + 1; k < arestas.size(); k++) {
          if (arestas.get(j).equals(arestas.get(k))) {
            simples = false;
            break;
          }
        }
      }

      // verifica se tem laço, ou seja, se um vertice se conecta a ele mesmo, se
      // tiver, não é um grafo simples
      if (arestas.contains(vertice.rotulo)) {
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
      for (Integer vizinho : grafo.get(vertAtual - 1).arestas) {
        if (!visitei[vizinho - 1]) {
          if (primVizinho) {
            primVizinho = false;
            System.out.print(" -> ");
          } else {
            System.out.print(", ");
          }
          visitei[vizinho - 1] = true;
          busca.add(vizinho);
          System.out.print(vizinho);
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
    visitei[vertice] = true;
    System.out.print(vertice + " ");

    for (Integer vizinho : grafo.get(vertice - 1).arestas) {
      if (!visitei[vizinho]) {
        System.out.print(" -> ");
        pesqProfundidade(grafo, vizinho, visitei);
      }
    }
  }

  public static boolean dfsTopologico(ArrayList<Vertices> grafo, int vertice, boolean[] visitei, boolean[] pilhaRec,
  Stack<Integer> pilha) {
int indice = vertice - 1; // Obtém o índice real do vértice

if (pilhaRec[indice]) {
  return true; // Ciclo encontrado
}

if (visitei[indice]) {
  return false; // Já foi visitado e não forma ciclo
}

visitei[indice] = true;
pilhaRec[indice] = true;

for (int i = 0; i < grafo.get(indice).arestas.size(); i++) {
  int vizinho = grafo.get(indice).arestas.get(i);
  if (dfsTopologico(grafo, vizinho, visitei, pilhaRec, pilha)) {
      return true; // Ciclo encontrado
  }
}

pilhaRec[indice] = false;
pilha.push(vertice);

return false;
}



  public static void dfsConexo(ArrayList<Vertices> grafo, int vertice, boolean[] visitei) {
    visitei[vertice] = true;

    for (int i = 0; i < grafo.get(vertice - 1).arestas.size(); i++) {
      int vizinho = grafo.get(vertice - 1).arestas.get(i);
      if (!visitei[vizinho]) {
        dfsConexo(grafo, vizinho, visitei);
      }
    }
  }

  public static void grafoConexo(ArrayList<Vertices> grafo, int numVertices) {
    boolean[] visitado = new boolean[numVertices + 1];
    boolean conexo = true;

    // Realiza a busca em profundidade a partir do primeiro vértice
    dfsConexo(grafo, 1, visitado);

    // Verifica se todos os vértices foram alcançados
    for (int i = 1; i <= numVertices; i++) {
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
      // aqui está adicionando as arestas no grafo, -1 para corrigir o indice do
      // vetor, pois o indice começa em 0 e não em 1.
      int origem = Integer.parseInt(aux[0]) - 1;
      int destino = Integer.parseInt(aux[1]) - 1;

      grafo.get(origem).arestas.add(destino + 1);
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
      op = sc.nextInt();

      switch (op) {
        case 1: {
          sc.nextLine();
          System.out.println("Digite a aresta que deseja adicionar - Exemplo: 1,2: ");
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
          // verificação se o grafo é simples
          grafoSimples(grafo);
          System.out.println("\n");

          // verificação se o grafo é regular
          grafoRegular(grafo, grauTotalV);
          System.out.println("\n");

          // verificação se o grafo é completo
          grafoCompleto(grafo, grauTotalV);
          System.out.println("\n");
          // verificação se o grafo é bipartido
          grafoConexo(grafo, numVertices);

 
          break;

        }
        case 7: {
          System.out.println("\n\n");
          System.out.println("Digite o vértice de início para a busca em largura:");
          int inicio = sc.nextInt();
          buscaLargura(grafo, inicio);
 
          break;
        }
        case 8: {
          System.out.println("Digite o vértice de início para a busca em profundidade:");
          int inicio = sc.nextInt();
          buscaProfundidade(grafo, inicio);
 
          break;
        }
        case 9: {

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