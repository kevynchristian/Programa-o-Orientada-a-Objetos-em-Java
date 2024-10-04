
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class AnalisadorDesempenho {

    private final String caminhoFaixa = "data/dados_na_faixa.csv";
    private final String caminhoForaFaixa = "data/dados_fora_faixa.csv";

    public void analisarDesempenho() throws IOException {
        Map<String, double[]> dadosFaixa = carregarDados(caminhoFaixa);
        Map<String, double[]> dadosForaFaixa = carregarDados(caminhoForaFaixa);

        // Análise e geração de relatórios
        RelatorioDesempenho relatorio = new RelatorioDesempenho();
        relatorio.gerarGrafico(dadosFaixa, dadosForaFaixa);
    }

    private Map<String, double[]> carregarDados(String caminho) throws IOException {
        Map<String, double[]> dados = new HashMap<>();
        try (BufferedReader br = new BufferedReader(new FileReader(caminho))) {
            String linha;
            br.readLine(); // Pular o cabeçalho
            while ((linha = br.readLine()) != null) {
                String[] colunas = linha.split(",");

                System.out.println("Número de colunas: " + colunas.length + " - Conteúdo: " + linha);

                if (colunas.length < 5) {
                    System.out.println("Linha inválida (colunas insuficientes): " + linha);
                    continue;
                }

                if (colunas[1].trim().isEmpty() || colunas[3].trim().isEmpty() || colunas[4].trim().isEmpty()) {
                    System.out.println("Linha inválida (dados ausentes): " + linha);
                    continue;
                }

                String etapa = colunas[1].trim();
                double desempenhoPortugues;
                double desempenhoMatematica;

                try {
                    desempenhoPortugues = Double.parseDouble(colunas[3].trim());
                    desempenhoMatematica = Double.parseDouble(colunas[4].trim());
                } catch (NumberFormatException e) {
                    System.out.println("Linha inválida (número mal formatado): " + linha);
                    continue;
                }

                double mediaDesempenho = (desempenhoPortugues + desempenhoMatematica) / 2;

                dados.put(etapa, new double[]{mediaDesempenho, desempenhoPortugues, desempenhoMatematica});
            }
        }
        return dados;
    }

}
