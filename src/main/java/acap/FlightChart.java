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

import javafx.scene.chart.ValueAxis;

class LogarithmicAxis extends ValueAxis<Number> {
    public LogarithmicAxis() {
        super();
    }

    @Override
    protected List<Number> calculateMinorTickMarks() {
        return List.of();
    }

    @Override
    protected void setRange(Object range, boolean animate) { }

    @Override
    protected Object getRange() {
        return null;
    }

    @Override
    protected List<Number> calculateTickValues(double length, Object range) {
        List<Number> ticks = new ArrayList<>();
        double lower = getLowerBound();
        double upper = getUpperBound();

        if (lower <= 0 && upper >= 0) {
            ticks.add(0);
        }

        int startPow = (int) Math.ceil(log2(Math.max(lower, 1)));
        int endPow = (int) Math.floor(log2(upper));

        for (int pow = startPow; pow <= endPow; pow++) {
            double value = Math.pow(2, pow);
            if (value >= lower && value <= upper) {
                ticks.add(value);
            }
        }

        return ticks;
    }

    @Override
    protected String getTickMarkLabel(Number value) {
        double v = value.doubleValue();
        if (v == 0) return "0";
        if (Math.abs(v - Math.round(v)) < 1) {
            return String.valueOf((int) v);
        }
        return String.format("%f", v);
    }

    @Override
    public double getDisplayPosition(Number value) {
        double val = value.doubleValue();
        double lower = getLowerBound();
        double upper = getUpperBound();

        double axisLength = getHeight();
        if (val <= 0) return axisLength;

        double logVal = log2(val);
        double logLower = log2(Math.max(lower, 1));
        double logUpper = log2(upper);

        double ratio = (logVal - logLower) / (logUpper - logLower);
        return axisLength - (ratio * axisLength);
    }

    @Override
    public Number getValueForDisplay(double displayPosition) {
        double axisLength = getHeight();
        double ratio = 1 - (displayPosition / axisLength);

        double lower = getLowerBound();
        double upper = getUpperBound();
        double logLower = log2(Math.max(lower, 1));
        double logUpper = log2(upper);

        double logValue = logLower + ratio * (logUpper - logLower);
        return Math.pow(2, logValue);
    }

    @Override
    public boolean isValueOnAxis(Number value) {
        double val = value.doubleValue();
        return val >= getLowerBound() && val <= getUpperBound();
    }

    @Override
    public double toNumericValue(Number value) {
        return value.doubleValue();
    }

    @Override
    public Number toRealValue(double value) {
        return value;
    }

    private double log2(double x) {
        return Math.log(x) / Math.log(2);
    }
}

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
        stage.setTitle("ACAP");

        NumberAxis xAxis = new NumberAxis();
        xAxis.setLabel("Intervalle de 15 min");
        xAxis.setTickUnit(1);

        LogarithmicAxis yAxis = new LogarithmicAxis();
        yAxis.setLabel("Nombre de vols");
        yAxis.setMinorTickCount(0);

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

            int maxVal = 0;
            for (int i = 0; i < data.size(); i++) {
                Row row = data.get(i);
                int interval = i;

                planifieSeries.getData().add(new XYChart.Data<>(interval, row.planifie()));
                retardeSeries.getData().add(new XYChart.Data<>(interval, row.retarde()));
                enCoursSeries.getData().add(new XYChart.Data<>(interval, row.enCours()));
                annuleSeries.getData().add(new XYChart.Data<>(interval, row.annule()));
                termineSeries.getData().add(new XYChart.Data<>(interval, row.termine()));

                maxVal = Math.max(maxVal, row.planifie());
                maxVal = Math.max(maxVal, row.retarde());
                maxVal = Math.max(maxVal, row.enCours());
                maxVal = Math.max(maxVal, row.annule());
                maxVal = Math.max(maxVal, row.termine());
            }
            int upperPower = (int) Math.pow(2, Math.ceil(Math.log(Math.max(maxVal, 2)) / Math.log(2)));
            yAxis.setUpperBound(upperPower);

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
