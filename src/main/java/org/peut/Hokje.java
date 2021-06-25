package org.peut;

import javafx.scene.Group;
import javafx.scene.control.Button;


public class Hokje extends Button{
    private static int xcount=0;
    private static int ycount=0;
    public static final int SIDE=60;

    private boolean occupied = false;
    Ship    ship;

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

    public boolean isOccupied() {
        return occupied;
    }

    public void setOccupied(boolean occupied) {
        this.occupied = occupied;
    }

    public Ship getShip() {
        return ship;
    }

    public void setShip( Ship ship) {
        this.ship = ship;
    }

    public void show(){

    }

    private void checkSquare( Hokje square ) {
        if (square.isOccupied()) {
            Ship tmpShip = square.getShip();

            if (tmpShip.isDead()) {
                System.out.println("Shooting wreckage is counter productive");
            } else {
                if (tmpShip.isDamaged(square)){
                    System.out.println("Crypto #" + tmpShip.getNumber() + " - " + tmpShip.getName() + " already damaged here");
                }else {
                    System.out.println( "Hit!" );
                    if (tmpShip.hit(square)) {
                        System.out.println("Crypto #" + tmpShip.getNumber() + " - " + tmpShip.getName() + " is dead");
                        numberOfCryptos--;
                    } else {
                        System.out.println("Crypto #" + tmpCrypto.getNumber() + " - " + tmpCrypto.getName() + " is damaged");
                    }
                }
            }
        }else{
            System.out.println("Missed, nothing at " +  (char)(square.getRow() + 'A') + ", column " + square.getColumn() );
        }
    }


}


