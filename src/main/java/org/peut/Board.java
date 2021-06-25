package org.peut;

import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.util.ArrayList;

public class Board extends Group {
    public static final int BOARDSIDE=7;
    private Hokje[] board;
    private Stage stage;
    private int shipCount;

    Board( Stage stage ){

        board = new Hokje[ (BOARDSIDE * BOARDSIDE) ];
        // add BOARDSIDE * BOARDSIDE Hokjes to the Board

        for (int i = 0; i < 49 ; i++) {
            board[i] = new Hokje( Integer.toString(i) );
            this.getChildren().add( board[i] );
            //System.out.println( "Hokje "+ i + " style:" + h.getStyle() );
        }

        // set the stage
        this.stage = stage;
    }

    public int getShipCount() {
        return shipCount;
    }

    public void setShipCount(int shipCount) {
        this.shipCount = shipCount;
    }

    public int rowcolToNumber( int row, int column){
        return (row + (column * BOARDSIDE));
    }

    public Hokje getHokje( int row, int column){
        return board[ rowcolToNumber( row, column) ];
    }

    public void show(){
        stage.setScene(new Scene( this, BOARDSIDE*Hokje.SIDE+1, BOARDSIDE*Hokje.SIDE+1));
        stage.show();
    }


}
