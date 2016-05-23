/**
 * Created by anton on 16.05.16.
 */

import java.io.File;
import java.io.IOException;
import java.util.*;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.embed.swing.SwingFXUtils;
import javafx.scene.Scene;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.image.WritableImage;
import javafx.stage.Stage;
import javax.imageio.ImageIO;
import java.math.*;


public class MainClass extends Application {
    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage) {

        Map<String, List<Double>> data = getChartData(5000);
    //    System.out.println(data);
        final NumberAxis xAxis = new NumberAxis();
        final CategoryAxis yAxis = new CategoryAxis();
        final BarChart<Number, String> bc = new BarChart<Number, String>(xAxis, yAxis);
        bc.setTitle("Summary");
        bc.setAnimated(false);
        xAxis.setLabel("Log(Tweets' number)");
        xAxis.setTickLabelRotation(90);
        yAxis.setLabel("Language");

        XYChart.Series series1 = new XYChart.Series();
        series1.setName("Real Madrid");
        for(String lang : data.keySet()) {
            series1.getData().add(new XYChart.Data(data.get(lang).get(0), lang));
        }

        XYChart.Series series2 = new XYChart.Series();
        series2.setName("Atletico Madrid");
        for(String lang : data.keySet()) {
            series2.getData().add(new XYChart.Data(data.get(lang).get(1), lang));
        }

        Scene scene = new Scene(bc, 800, 600);
        bc.getData().addAll(series1, series2);
        stage.setScene(scene);
    //    stage.show();

        WritableImage snapShot = scene.snapshot(null);

        try {
            ImageIO.write(SwingFXUtils.fromFXImage(snapShot, null), "png", new File("diagram.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        Platform.exit();
    }

    private Map<String, List<Double>> getChartData(int sampleSize) {

        Map<String, List<Double>> result = new HashMap<String, List<Double>>();

        /* Real Madrid tweets */
        Collection<Tweet> tweetsRealCollection = Accessor.search("Real Madrid", "2016-04-23", sampleSize);
        TweetsContainer<Tweet> tweetsReal = new TweetsContainerImpl<Tweet>();

        tweetsReal.addAll(tweetsRealCollection);

        Map<String, Collection<Tweet>> groupedReal = tweetsReal.groupByLang();

        /* Atletico Madrid tweets */
        Collection<Tweet> tweetsAtleticoCollection = Accessor.search("Atletico Madrid", "2016-04-23", sampleSize);
        TweetsContainer<Tweet> tweetsAtletico = new TweetsContainerImpl<Tweet>();

        tweetsAtletico.addAll(tweetsAtleticoCollection);

        Map<String, Collection<Tweet>> groupedAtletico = tweetsAtletico.groupByLang();

        for(String lang : groupedReal.keySet()) {
            if(!result.containsKey(lang)) {
                result.put(lang, new ArrayList<Double>());
                result.get(lang).add((double) 0);
                result.get(lang).add((double) 0);
            }
            result.get(lang).set(0, (result.get(lang).get(0) + Math.log(groupedReal.get(lang).size()+1)));
        }

        for(String lang : groupedAtletico.keySet()) {
            if(!result.containsKey(lang)) {
                result.put(lang, new ArrayList<Double>());
                result.get(lang).add((double) 0);
                result.get(lang).add((double) 0);
            }
            result.get(lang).set(1, (result.get(lang).get(1) + Math.log(groupedAtletico.get(lang).size()+1)));
        }

        return result;
    }
}