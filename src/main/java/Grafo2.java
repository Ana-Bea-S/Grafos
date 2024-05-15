public class Grafo2 {
    int vertices;
    int[][] matrizAdj;
  
    public Grafo2(int v) {
      this.vertices = v;
      matrizAdj = new int[vertices][vertices];
      // Inicialize a matriz com valores padrão para indicar ausência de arestas
      for (int i = 0; i < vertices; i++) {
        for (int j = 0; j < vertices; j++) {
          matrizAdj[i][j] = 0; // Pode ser qualquer valor padrão que indique ausência de aresta
        }
      }
    }
  }
  