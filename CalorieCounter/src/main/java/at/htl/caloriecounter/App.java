package at.htl.caloriecounter;

import at.htl.caloriecounter.database.SqlRunner;
import at.htl.caloriecounter.database.SqlScript;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Objects;

public class App extends Application {

    @Override
    public void start(Stage stage) throws IOException {
        SqlRunner.dropAndCreateTablesWithExampleData();
        Scene scene = new Scene(loadFXML("/login"), 640, 550);
        stage.setScene(scene);
        stage.setTitle("Calorie Counter");
        stage.getIcons().add(new Image(Objects.requireNonNull(App.class.getResourceAsStream("/icon.png"))));
        stage.show();
    }

    public static Parent loadFXML(String fxml) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource(fxml + ".fxml"));
        return fxmlLoader.load();
    }

    public static void main(String[] args) {
        launch();
    }
}
