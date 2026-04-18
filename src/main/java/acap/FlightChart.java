package acap;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.stage.Stage;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class FlightChart extends Application {
    private record Row(int planifie, int retarde, int enCours, int annule, int termine) {}

    private List<Row> loadData(String filePath) throws IOException {
        List<Row> rows = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            br.readLine(); // skip header
            String line;
            while ((line = br.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length >= 5) {
                    int planifie = Integer.parseInt(parts[0]);
                    int retarde = Integer.parseInt(parts[1]);
                    int enCours = Integer.parseInt(parts[2]);
                    int annule = Integer.parseInt(parts[3]);
                    int termine = Integer.parseInt(parts[4]);
                    rows.add(new Row(planifie, retarde, enCours, annule, termine));
                }
            }
        }
        return rows;
    }

    @Override
    public void start(Stage stage) {
        stage.setTitle("Évolution des états de vol");

        NumberAxis xAxis = new NumberAxis();
        xAxis.setLabel("Intervalle de 15 min");
        xAxis.setForceZeroInRange(false);
        xAxis.setTickUnit(1);

        NumberAxis yAxis = new NumberAxis();
        yAxis.setLabel("Nombre de vols");

        LineChart<Number, Number> lineChart = new LineChart<>(xAxis, yAxis);
        lineChart.setTitle("Statistiques des vols");

        try {
            List<Row> data = loadData("data.csv");

            XYChart.Series<Number, Number> planifieSeries = new XYChart.Series<>();
            planifieSeries.setName("Planifié");

            XYChart.Series<Number, Number> retardeSeries = new XYChart.Series<>();
            retardeSeries.setName("Retardé");

            XYChart.Series<Number, Number> enCoursSeries = new XYChart.Series<>();
            enCoursSeries.setName("En cours");

            XYChart.Series<Number, Number> annuleSeries = new XYChart.Series<>();
            annuleSeries.setName("Annulé");

            XYChart.Series<Number, Number> termineSeries = new XYChart.Series<>();
            termineSeries.setName("Terminé");

            for (int i = 0; i < data.size(); i++) {
                Row row = data.get(i);
                int interval = i;

                planifieSeries.getData().add(new XYChart.Data<>(interval, row.planifie()));
                retardeSeries.getData().add(new XYChart.Data<>(interval, row.retarde()));
                enCoursSeries.getData().add(new XYChart.Data<>(interval, row.enCours()));
                annuleSeries.getData().add(new XYChart.Data<>(interval, row.annule()));
                termineSeries.getData().add(new XYChart.Data<>(interval, row.termine()));
            }

            lineChart.getData().add(planifieSeries);
            lineChart.getData().add(retardeSeries);
            lineChart.getData().add(enCoursSeries);
            lineChart.getData().add(annuleSeries);
            lineChart.getData().add(termineSeries);

        } catch (IOException exc) {
            System.err.println(exc.getMessage());
            exc.printStackTrace();
        }

        Scene scene = new Scene(lineChart, 800, 600);
        scene.getStylesheets().add(getClass().getResource("chart.css").toExternalForm());
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
