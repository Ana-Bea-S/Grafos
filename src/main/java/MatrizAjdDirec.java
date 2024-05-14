import java.util.Arrays;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Scanner;
import java.util.Stack;

public class MatrizAjdDirec {

  public static void criaAresta(Grafo grafo, int origem, int destino) {
    try {
      grafo.matrizAdj[origem][destino] = 1; // aresta que sai do vértice i
      grafo.matrizAdj[destino][origem] = -1; // aresta que entra no vértice i
    } catch (ArrayIndexOutOfBoundsException index) {
      System.out.println("Vértice não existente");
    }
  }

  public static void removeAresta(Grafo grafo, int origem, int destino) {
    grafo.matrizAdj[origem][destino] = 0;
  }

  public static void imprimeGrafo(Grafo grafo, int numVertice) {
    System.out.println("Matriz Adjacente:  ");

    int maxLength = String.valueOf(numVertice).length();

    System.out.print("   ");
    for (int i = 0; i < numVertice; i++) {
      System.out.printf("%-" + maxLength + "d ", i);
    }
    System.out.println();

    System.out.print("---");
    for (int i = 0; i < numVertice; i++) {
      for (int j = 0; j < maxLength; j++) {
        System.out.print("-");
      }
      System.out.print(" ");
    }
    System.out.println();

    for (int i = 0; i < numVertice; i++) {
      System.out.printf("%-" + maxLength + "d |", i);
      for (int j = 0; j < numVertice; j++) {
        System.out.printf("%-" + maxLength + "d ", grafo.matrizAdj[i][j]);
      }
      System.out.println();
    }
  }

  public static void grauVertice(Grafo grafo, int numVertice) {
    for (int i = 0; i <  numVertice; i++) {
      int grau = 0;
      for (int j = 1; j <= numVertice; j++) {
        grau += Math.abs(grafo.matrizAdj[i][j]);
      }
      System.out.println("Grau do vértice " + i + ": " + grau);
    }
  }

  public static void imprimeSucessores(Grafo grafo, int numVertice) {
    for (int i = 0; i <  numVertice; i++) {
      System.out.print("Sucessores do vértice " + i + ": ");
      for (int j = 1; j <= numVertice; j++) {
        if (grafo.matrizAdj[i][j] == 1) {
          System.out.print(j + " ");
        }
      }
      System.out.println();
    }
  }

  public static void imprimePredecessores(Grafo grafo, int numVertice) {
    for (int i = 0; i <  numVertice; i++) {
      System.out.print("Predecessores do vértice " + i + ": ");
      for (int j = 1; j <= numVertice; j++) {
        if (grafo.matrizAdj[i][j] == -1) {
          System.out.print(j + " ");
        }
      }
      System.out.println();
    }
  }

  public static void grafoSimples(Grafo grafo, int numVertice) {
    boolean simples = true;
    for (int i = 0; i <  numVertice; i++) {
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
    for (int i = 0; i <  numVertice; i++) {
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
    for (int i = 0; i <  numVertice; i++) {
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

    for (int i = 2; i <  numVertice; i++) {
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
    for (int i = 0; i <  numVertice; i++) {
      cor[i] = -1;
    }

    // Inicia a busca em largura em todos os vértices
    for (int i = 0; i <  numVertice; i++) {
      if (cor[i] == -1) { // Se o vértice ainda não foi visitei, inicia a BFS a partir dele
        cor[i] = 1; // Define a cor do vértice inicial como 1
        Queue<Integer> fila = new LinkedList<>();
        fila.add(i); // Adiciona o vértice à fila

        // Realiza a busca em largura
        while (!fila.isEmpty()) {
          int u = fila.poll(); // Remove o vértice da fila

          // Percorre todos os vértices adjacentes de u
          for (int v = 1; v <= numVertice; v++) {
            // Se existir uma aresta entre u e v e v ainda não foi visitei
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

  public static void buscaProfundidade(Grafo grafo, int vertOrigem, int numVertices) {
    boolean[] visitei = new boolean[numVertices + 1];
    int numArvores = 0; // contador para o número de árvores/componentes

    if (!visitei[vertOrigem]) {
      numArvores++;
      System.out.print("Árvore " + numArvores + ": ");
      pesqProfundidade(grafo, vertOrigem, visitei);
      System.out.println();
    }

    for (int i = 0; i <  numVertices; i++) {
      if (!visitei[i]) {
        numArvores++;
        System.out.print("Árvore " + numArvores + ": ");
        pesqProfundidade(grafo, i, visitei);
        System.out.println();
      }
    }
  }

  private static void pesqProfundidade(Grafo grafo, int vertice, boolean[] visitei) {
    visitei[vertice] = true;
    System.out.print(vertice + " ");

    for (int i = 0; i < grafo.matrizAdj.length; i++) {
      if (grafo.matrizAdj[vertice][i] == -1 && !visitei[i]) {
        System.out.print(" -> ");
        pesqProfundidade(grafo, i, visitei);
      }
    }
  }

  public static void buscaLargura(Grafo grafo, int vertOrigem, int numVertices) {
    boolean[] visitei = new boolean[numVertices + 1];
    Queue<Integer> busca = new LinkedList<>();

    visitei[vertOrigem] = true;
    busca.add(vertOrigem);

    System.out.print("Árvore:  ");
    while (!busca.isEmpty()) {
      int vertAtual = busca.poll();
      System.out.print(vertAtual);

      boolean primVizinho = true;
      for (int i = 0; i <  numVertices; i++) {
        if (grafo.matrizAdj[vertAtual][i] == -1 && !visitei[i]) {
          if (primVizinho) {
            primVizinho = false;
            System.out.print(" -> ");
          } else {
            System.out.print(", ");
          }
          visitei[i] = true;
          busca.add(i);
          System.out.print(i);
        }
      }
      System.out.println();
    }
    System.out.println();
  }

  public static boolean dfsTopologico(Grafo grafo, int vertice, boolean[] visitei, boolean[] pilhaRec,
      Stack<Integer> pilha) {
    if (pilhaRec[vertice]) {
      return true; // Ciclo encontrado
    }

    if (visitei[vertice]) {
      return false; // Já foi visitei e não forma ciclo
    }

    visitei[vertice] = true;
    pilhaRec[vertice] = true;

    for (int i = 0; i < grafo.matrizAdj.length; i++) {
      if (grafo.matrizAdj[vertice][i] == -1 && dfsTopologico(grafo, i, visitei, pilhaRec, pilha)) {
        return true; // Ciclo encontrado
      }
    }

    pilhaRec[vertice] = false;
    pilha.push(vertice);

    return false;
  }

  public static void ordenacaoTopologica(Grafo grafo, int numVertices) {
    Stack<Integer> pilha = new Stack<>();
    boolean[] visitei = new boolean[numVertices + 1];
    boolean[] pilhaRec = new boolean[numVertices + 1];

    for (int i = 0; i <  numVertices; i++) {
      if (!visitei[i] && dfsTopologico(grafo, i, visitei, pilhaRec, pilha)) {
        System.out.println("O grafo contém um ciclo!");
        return;
      }
    }

    System.out.println("Ordenação Topológica:");
    while (!pilha.isEmpty()) {
      System.out.print(pilha.pop());
      if (!pilha.isEmpty()) {
        System.out.print(" -> ");
      }
    }
    System.out.println();
  }

  public static void dfsConexo(Grafo grafo, int vertice, boolean[] visitei) {
    visitei[vertice] = true;

    for (int i = 0; i < grafo.matrizAdj.length; i++) {
      if (grafo.matrizAdj[vertice][i] == -1 && !visitei[i]) {
        dfsConexo(grafo, i, visitei);
      }
    }
  }

  public static void grafoConexo(Grafo grafo, int numVertices) {
    boolean[] visitado = new boolean[numVertices + 1];
    boolean conexo = true;

    // Realiza a busca em profundidade a partir do primeiro vértice
    dfsConexo(grafo, 1, visitado);

    // Verifica se todos os vértices foram alcançados
    for (int i = 0; i <  numVertices; i++) {
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

  public static void dijkstra(Grafo grafo, int origem, int destino, int numVertices) {
    int[] distancias = new int[numVertices + 1];
    Arrays.fill(distancias, Integer.MAX_VALUE);
    distancias[origem] = 0;

    PriorityQueue<Integer> fila = new PriorityQueue<>(Comparator.comparingInt(v -> distancias[v]));
    fila.add(origem);

    while (!fila.isEmpty()) {
      int u = fila.poll();

      for (int v = 0; v < numVertices; v++) {
        if (grafo.matrizAdj[u][v] == -1) {
          int peso = grafo.matrizAdj[u][v]; 
          if (distancias[v] > distancias[u] + peso) {
            distancias[v] = distancias[u] + peso;
            fila.add(v);
          }
        }
      }
    }

    if (distancias[destino] == Integer.MAX_VALUE) {
      System.out.println("Não há caminho de " + origem + " para " + destino);
    } else {
      System.out.println("Distância mínima de " + origem + " para " + destino + ": " + distancias[destino]);
    }
  }

  public static Grafo ponderador(Grafo g) {
    Scanner sc = new Scanner(System.in);
    String peso;
    System.out.println(g.vertices);
    Grafo copia = new Grafo(g.vertices);
    for (int i = 0; i < copia.vertices; i++) {
        for (int j = 0; j < copia.vertices; j++) {
            if (g.matrizAdj[i][j] == 1) {
                System.out.println("Adicione o peso para " + i + "," + j + ":");
                peso = sc.nextLine();
                copia.matrizAdj[i][j] = Integer.parseInt(peso); // Modifica a matriz de adjacência de copia
            }
            else{
              System.out.println(g.matrizAdj[i][j]);
            }
        }
    }
    return copia;
}

  public static void menu() {
    int numVertice, numAresta;
    Scanner sc = new Scanner(System.in);
    Grafo grafo = null;
    try {
      System.out.println("Digite o número de vértices: ");
      numVertice = Integer.parseInt(sc.nextLine());

      System.out.println("Digite o número de arestas: ");
      numAresta = Integer.parseInt(sc.nextLine());


      grafo = new Grafo(numVertice);

      for (int i = 0; i < numAresta; i++) {
        int origem, destino;
        System.out.println("Digite as arestas, formato - 1,2: ");
        String combinacao = sc.nextLine();
        String[] aux = combinacao.split(",");
        origem = Integer.parseInt(aux[0]);
        destino = Integer.parseInt(aux[1]);

        criaAresta(grafo, origem -1, destino -1);
      }

      String op;
      do {
        System.out.println("======= MENU =======");
        System.out.println("[ 1 ] - Criar Arestas");
        System.out.println("[ 2 ] - Remover Arestas");
        System.out.println("[ 3 ] - Imprimir Grafo");
        System.out.println("[ 4 ] - Sucessores");
        System.out.println("[ 5 ] - Predecessores");
        System.out.println("[ 6 ] - Grau de cada Vértice");
        System.out.println("[ 7 ] - Verificar Grafo (Bipartido com problema)");
        System.out.println("[ 8 ] - Busca em Largura");
        System.out.println("[ 9 ] - Busca em Profundidade");
        System.out.println("[ 10 ] - Ordenação Topológica");
        System.out.println("[ 11 ] - Caminho mínimo entre dois vértices**");
        System.out.println("[ 0 ] - Sair");
        System.out.println("Qual opção você deseja? ");
        op = sc.nextLine();
        switch (op) {
          case "1": {
              
            int origem, destino;
            System.out.println("Digite a aresta que deseja adicionar, formato - 1,2: ");
            String combinacao = sc.nextLine();
            String[] aux = combinacao.split(",");
            origem = Integer.parseInt(aux[0]);
            destino = Integer.parseInt(aux[1]);

            criaAresta(grafo, origem -1, destino -1);
             
            break;
          }
          case "2": {
              
            int origem, destino;
            System.out.println("Digite a aresta que deseja remover, formato - 1,2: ");
            String combinacao = sc.nextLine();
            String[] aux = combinacao.split(",");
            origem = Integer.parseInt(aux[0]);
            destino = Integer.parseInt(aux[1]);
            removeAresta(grafo, origem -1, destino -1);
             
            break;
          }
          case "3": {
              

            imprimeGrafo(grafo, numVertice);

             
            break;
          }
          case "4": {
              

            imprimeSucessores(grafo, numVertice);

             
            break;
          }
          case "5": {
              

            imprimePredecessores(grafo, numVertice);

             
            break;
          }
          case "6": {
              

            grauVertice(grafo, numVertice);
             
            break;
          }
          case "7": {
              
            // verificar se é simples
            grafoSimples(grafo, numVertice);
              
            // verificar se é completo
            grafoCompleto(grafo, numVertice);
              
            // verificar se é regular
            grafoRegular(grafo, numVertice);
              
            // verificar se é bipartido
            grafoBipartido(grafo, numVertice);
              
            // verificar se é conexo
            grafoConexo(grafo, numVertice);
             
            break;
          }
          case "8": {
               
            System.out.println("Digite o vértice em que deseja iniciar sua busca em largura: ");
            int vertOrigem = Integer.parseInt(sc.nextLine());

            System.out.print("Busca em Largura:  ");
            buscaLargura(grafo, vertOrigem, numVertice);
             
            break;
          }
          case "9": {
               
            System.out.println("Digite o vértice em que deseja iniciar sua busca em profundidade: ");
            int vertOrigem = Integer.parseInt(sc.nextLine());

            System.out.print("Busca em Profundidade:  ");
            buscaProfundidade(grafo, vertOrigem, numVertice);
             
            break;
          }
          case "10": {
               
            ordenacaoTopologica(grafo, numVertice);
              
            break;
          }
          case "11": {
            Grafo copia = ponderador(grafo);

            System.out.println("Digite o vértice de origem para o algoritmo de Dijkstra: ");
            int vertOrigem = Integer.parseInt(sc.nextLine());

            System.out.println("Digite o vértice de destino para o algoritmo de Dijkstra: ");
            int vertDestino = Integer.parseInt(sc.nextLine());

            dijkstra(copia, vertOrigem, vertDestino, numVertice);

            break;
          }
          case "0": {
            System.out.println("Até logo!");
            break;
          }
          default: {
            System.out.println("Opção inválida, tente novamente");
            break;
          }
        }

      } while (op != "0");

    } catch (Exception E) {
      System.out.println(E.getMessage());
    }

  }
}
