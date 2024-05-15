import java.util.*;

class Vertices2 {
    int rotulo;
    ArrayList<Arestas> arestas;
    int grauSaida; // Adiciona o grau de saída
    int grauEntrada; // Adiciona o grau de entrada

    public Vertices2(int rotulo) {
        this.rotulo = rotulo;
        this.arestas = new ArrayList<>();
        this.grauSaida = 0;
        this.grauEntrada = 0;
    }
}
