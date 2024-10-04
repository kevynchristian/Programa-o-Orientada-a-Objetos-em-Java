import java.io.IOException;

public class Main {
    public static void main(String[] args) {
        AnalisadorDesempenho analisador = new AnalisadorDesempenho();
        try {
            analisador.analisarDesempenho();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
