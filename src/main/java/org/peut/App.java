package org.peut;

import javafx.application.Application;
import javafx.stage.Stage;


/**
 * JavaFX App
 */
public class App extends Application {

    @Override
    public void start(Stage primaryStage){

        primaryStage.setTitle("Zee");
        primaryStage.setResizable(false);

        Board board = new Board(primaryStage);

    }

    public static void main(String[] args) {
        launch();
    }

}