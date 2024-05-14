public class Grafo {
  int vertices;
  int[][] matrizAdj;

  public Grafo(int v) {
    this.vertices = v;
    matrizAdj = new int[vertices][vertices];
  }
}
