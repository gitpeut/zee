package org.peut;

import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Board extends Group {
    public static final int BOARDSIDE=7;

    private Stage stage;

    Board( Stage stage ){

        // add BOARDSIDE * BOARDSIDE Hokjes to the Board

        for (int i = 0; i < 49 ; i++) {
            Hokje h = new Hokje( Integer.toString(i) );
            this.getChildren().add( h );
            //System.out.println( "Hokje "+ i + " style:" + h.getStyle() );
        }

        // set the stage
        this.stage = stage;
    }

    public void show(){
        stage.setScene(new Scene( this, BOARDSIDE*Hokje.SIDE+1, BOARDSIDE*Hokje.SIDE+1));
        stage.show();
    }


}
