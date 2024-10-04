import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.chart.plot.PlotOrientation;

import javax.swing.*;
import java.util.Map;

public class RelatorioDesempenho {

    private static final String[] ETAPAS_ORDEM = {
        "1 Ano", "2 Ano", "3 Ano", "4 Ano", "5 Ano", 
        "6 Ano", "7 Ano", "8 Ano", "9 Ano"
    };

    public void gerarGrafico(Map<String, double[]> dadosFaixa, Map<String, double[]> dadosForaFaixa) {
        DefaultCategoryDataset dataset = new DefaultCategoryDataset();

        for (String etapa : ETAPAS_ORDEM) {
            if (dadosFaixa.containsKey(etapa)) {
                double[] valores = dadosFaixa.get(etapa);
                dataset.addValue(valores[0], "Desempenho na Faixa", etapa);
            }
        }

        for (String etapa : ETAPAS_ORDEM) {
            if (dadosForaFaixa.containsKey(etapa)) {
                double[] valores = dadosForaFaixa.get(etapa);
                dataset.addValue(valores[0], "Desempenho Fora da Faixa", etapa);
            }
        }

        JFreeChart chart = ChartFactory.createBarChart(
            "Desempenho por Etapa", // Título do gráfico
            "Etapa",                // Eixo X
            "Desempenho",          // Eixo Y
            dataset,               // Dataset
            PlotOrientation.VERTICAL, // Orientação
            true,                  // Legenda
            true,                  // Tooltips
            false                  // URLs
        );

        ChartPanel chartPanel = new ChartPanel(chart);
        JFrame frame = new JFrame("Gráfico de Desempenho");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.add(chartPanel);
        frame.pack();
        frame.setVisible(true);
    }
}
