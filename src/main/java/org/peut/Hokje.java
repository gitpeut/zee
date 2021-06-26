package org.peut;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.control.Button;


public class Hokje extends Button{
    //length and width of each square
    public static final int SIDE=60;
    // class wide counts to determine position of a new square
    private static int xcount=0;
    private static int ycount=0;

    public static void resetXYcount(){
        xcount = 0;
        ycount = 0;
    }

    // is there a ship in this square?

    private boolean occupied = false;
    // reference to the ship if so
    private Ship    ship;
    //back reference to the board
    private Board   board;
    private int     index;

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

        this.index =  xcount + (ycount* Board.BOARDSIDE);

        ++xcount;
        if ( xcount >= Board.BOARDSIDE ){
            ++ycount;
            xcount = 0;
        }

    }

    Hokje( Board board) {
        this.board = board;

        this.setOnAction(new EventHandler<ActionEvent>() {
            @Override public void handle(ActionEvent e) {
                Hokje hokje = (Hokje) e.getSource();

                System.out.println("clicked " + hokje.getIndex());

                hokje.checkSquare();
            }
        });

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

    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }

    public void show(){
        System.out.println("- Hokje " + index );
    }

    private void checkSquare() {
        if ( isOccupied()) {

            if ( ship.isDead()) {
                System.out.println("Shooting wreckage is counter productive");
            } else {
                if ( ship.isDamaged( this)){
                    System.out.println("Ship #" + ship.getNumber() + " - " + ship.getName() + " already damaged here");
                }else {
                    System.out.println( "Hit!" );

                    this.setStyle("-fx-background-color: gray");

                    if ( ship.hit( this)) {
                        System.out.println("Ship #" + ship.getNumber() + " - " + ship.getName() + " is dead");
                        for( Hokje d : ship.getDamagedParts() ){
                            d.setStyle("-fx-background-color: black");
                        }
                        board.setShipsLeft(  board.getShipsLeft() - 1  );
                    } else {
                        System.out.println("Ship #" + ship.getNumber() + " - " + ship.getName() + " is damaged");
                    }
                }
            }
        }else{
            System.out.println("Missed, nothing at " +  index );
        }
    }


}


