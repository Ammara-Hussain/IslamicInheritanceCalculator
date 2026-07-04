//Sets up the JavaFX Stage and Scene.

//This class is the Launcher.
//We need to change the window size and point it to our new FXML file and CSS.
package com.faraid.ui;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class FaraidApplication extends Application {
    @Override
    public void start(Stage stage) throws Exception {
        // Change "main-view.fxml" to "main-view.fxml" (we will create this next)
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("main-view.fxml"));

        // Professional resolution for the calculator
        Scene scene = new Scene(fxmlLoader.load(), 1200, 800);
        scene.getStylesheets().add(getClass().getResource("/style.css").toExternalForm());

        // Link the Emerald & Gold CSS
        String css = getClass().getResource("/style.css").toExternalForm();
        scene.getStylesheets().add(css);

        stage.setTitle("Al-Faraid: Islamic Inheritance System");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}