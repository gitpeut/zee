package org.peut;

import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.effect.DropShadow;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.TextAlignment;
import javafx.stage.Stage;


import java.util.Arrays;

public class Board extends Group {
    public static final int BOARDSIDE = 10;
    public static final int SHIPCOUNT = 6;
    private Hokje[] board;
    private Stage stage;
    Ship[] ships;
    private int shipsLeft;
    private int grenades;

    Board(Stage stage) {

        // set the stage
        this.stage = stage;

        init();
        show();
    }


    private void init() {

        // removing all children of the board, avoid previously used objects staying around.
        while( ! this.getChildren().isEmpty() ){
            this.getChildren().remove(0);
        }

        // remove old board by assigning a new array
        board = new Hokje[(BOARDSIDE * BOARDSIDE)];

        // make sure the first allocated Hokje is at zero,zero

        Hokje.resetXYcount();

        // add BOARDSIDE x BOARDSIDE Hokjes to the Board

        for (int i = 0; i < (BOARDSIDE * BOARDSIDE); i++) {
            board[i] = new Hokje(this);
            this.getChildren().add(board[i]);
            //System.out.println( "Hokje "+ i + " style:" + h.getStyle() );
        }
        // make sure hokje 0,0 gets focus. Handy for keyboard operation
        board[0].requestFocus();

        // add SHIPCOUNT ships
        ships = new Ship[SHIPCOUNT];
        int shipSurface = 0;
        for (int i = 0; i < SHIPCOUNT; i++) {
            ships[i] = new Ship("BMS Baddy" + (i + 1), this);
            shipSurface += ships[i].getLength();
        }

        grenades  = (BOARDSIDE * BOARDSIDE) / 2 + shipSurface;
        shipsLeft = SHIPCOUNT;

        setStageTitle(SHIPCOUNT + " ship" + (( SHIPCOUNT == 1)?"":"s") + " of unknown size must be destroyed");

    }

    public int getShipsLeft() {
        return shipsLeft;
    }

    public void gameOver() {
        Label label = new Label("GAME OVER");

        label.setFont(new Font("Arial", 32));
        label.setMinSize(200, 100);
        label.setMaxSize(200, 100);
        label.setPrefSize(200, 100);
        label.setStyle("-fx-text-fill: white");
        label.setTextAlignment(TextAlignment.CENTER);

        Button quitButton = new Button();

        quitButton.setMinSize(100, 100);
        quitButton.setMaxSize(100, 100);
        quitButton.setPrefSize(100, 100);
        quitButton.setFont(new Font("Arial", 24));
        quitButton.setStyle("-fx-text-fill: black");
        quitButton.setText("Quit");
        quitButton.setCancelButton(true);

        quitButton.setOnAction(e -> stage.close());

        Button againButton = new Button();

        againButton.setMinSize(100, 100);
        againButton.setMaxSize(100, 100);
        againButton.setPrefSize(100, 100);
        againButton.setFont(new Font("Arial", 24));
        againButton.setStyle("-fx-text-fill: black");
        againButton.setText("Again");
        againButton.setDefaultButton(true);

        againButton.setOnAction(e -> init());

        FlowPane box = new FlowPane();
        box.setStyle("-fx-background-color: skyblue");
        box.setPrefSize(210, 210);
        box.setAlignment(Pos.CENTER);

        box.getChildren().add(label);
        box.getChildren().add(quitButton);
        box.getChildren().add(againButton);

        box.setHgap(7);
        box.setVgap(7);
        box.setLayoutX(((BOARDSIDE * Hokje.SIDE) - 210) / 2);
        box.setLayoutY(((BOARDSIDE * Hokje.SIDE) - 210) / 2);

        DropShadow dropShadow1 = new DropShadow();
        dropShadow1.setRadius(7);
        dropShadow1.setOffsetX(3);
        dropShadow1.setOffsetY(3);
        dropShadow1.setColor(Color.DEEPSKYBLUE);

        box.setEffect(dropShadow1);


        this.getChildren().add(box);

        // to make sure enter will reach the againButton (which has been set as
        // as defaultButton. If not the last boardButton will be selected.

        box.requestFocus();

    }

    public void setShipsLeft(int shipsLeft) {
        this.shipsLeft = shipsLeft;

        if (this.shipsLeft == 0) {
            setStageTitle(" You WON! ");
            gameOver();
        }
    }

    public int getGrenades() {
        return grenades;
    }

    public void setGrenades(int grenades) {
        this.grenades = grenades;
    }

    public void setStageTitle(String title) {
        stage.setTitle("Grenades left: [" + grenades + "] Ships left: [" + shipsLeft + "] " + title);
    }

    public int rowcolToNumber(int row, int column) {
        return row + (column * BOARDSIDE);
    }

    public int[] numberToRowcol(int number) {
        int[] rowcol = new int[2];
        rowcol[0] = number / Board.BOARDSIDE;
        rowcol[1] = number % Board.BOARDSIDE;

        return (rowcol);
    }

    public Hokje getHokje(int row, int column) {
        return board[rowcolToNumber(row, column)];
    }

    public void show() {
        stage.setScene(new Scene(this, BOARDSIDE * Hokje.SIDE + 1, BOARDSIDE * Hokje.SIDE + 1));
        stage.show();
    }


}
