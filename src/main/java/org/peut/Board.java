package org.peut;

import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.Arrays;

public class Board extends Group {
    public static final int BOARDSIDE = 7;
    public static final int SHIPCOUNT = 1;
    private Hokje[] board;
    private Stage stage;
    Ship    ships[];
    private int shipsLeft;

    Board( Stage stage ){

        // set the stage
        this.stage = stage;

        init();
        show();
    }


    private void init(){

        board = new Hokje[ (BOARDSIDE * BOARDSIDE) ];

        Hokje.resetXYcount();
        Arrays.fill( board, null);

        // add BOARDSIDE x BOARDSIDE Hokjes to the Board

        for (int i = 0; i < (BOARDSIDE * BOARDSIDE) ; i++) {
            board[i] = new Hokje( this );
            this.getChildren().add( board[i] );
            //System.out.println( "Hokje "+ i + " style:" + h.getStyle() );
        }

        // add SHIPCOUNT ships
        ships = new Ship[ SHIPCOUNT ];
        for (int i = 0; i < SHIPCOUNT; i++) {
            ships[i] = new Ship( "de Ruyter "+i , this );
        }


    }

    public int getShipsLeft() {
        return shipsLeft;
    }

    public void setShipsLeft(int shipsLeft) {
        this.shipsLeft = shipsLeft;

        if( this.shipsLeft == 0 ){
            stage.setTitle( "GAME OVER");
            init();
        }
    }

    public int rowcolToNumber(int row, int column){
        return (row + (column * BOARDSIDE));
    }

    public int[] numberToRowcol( int number ){
        int rowcol[] = new int[2];
        rowcol[0] = (int)(number/Board.BOARDSIDE);
        rowcol[1] = number % Board.BOARDSIDE;

        return( rowcol );
    }

    public Hokje getHokje( int row, int column){
        return board[ rowcolToNumber( row, column) ];
    }

    public void show(){
        stage.setScene(new Scene( this, BOARDSIDE*Hokje.SIDE+1, BOARDSIDE*Hokje.SIDE+1));
        stage.show();
    }


}
