package org.peut;

import javafx.scene.Group;
import javafx.scene.control.Button;


public class Hokje extends Button{
    private static int xcount=0;
    private static int ycount=0;
    public static final int SIDE=60;

    private void place(){

        this.setLayoutX(xcount * SIDE);
        this.setLayoutY(ycount * SIDE);

        this.setMinSize(SIDE,SIDE);
        this.setMaxSize(SIDE,SIDE);
        this.setPrefSize(SIDE,SIDE);

        if ( ((xcount + (ycount+1)&1) == 1)  ) {
            this.setStyle("-fx-background-color: lightblue");
        }else{
            this.setStyle("-fx-background-color: skyblue");
        }

        ++xcount;
        if ( xcount > 6 ){
            ++ycount;
            xcount = 0;
        }

    }

    Hokje() {
        this.place();
    }

    Hokje( String title){
        super( title );
        this.place();
    }

}


