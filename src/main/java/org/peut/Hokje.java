package org.peut;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.control.Button;


public class Hokje extends Button {
    //length and width of each square
    public static final int SIDE = 60;
    // class wide counts to determine position of a new square
    private static int xcount = 0;
    private static int ycount = 0;

    public static void resetXYcount() {
        xcount = 0;
        ycount = 0;
    }

    // is there a ship in this square?

    private boolean occupied = false;
    // reference to the ship if so
    private Ship ship;
    //back reference to the board
    private Board board;
    private int index;


    Hokje(Board board) {
        this.board = board;

        //lamba function as per the discussion on
        // https://stackoverflow.com/questions/25409044/javafx-multiple-buttons-to-same-handler

        this.setOnAction(e -> {
            Hokje h = (Hokje) e.getSource();
            h.checkSquare();
        });


        this.place();
    }

    private void place() {

        this.setLayoutX(xcount * SIDE);
        this.setLayoutY(ycount * SIDE);

        this.setMinSize(SIDE, SIDE);
        this.setMaxSize(SIDE, SIDE);
        this.setPrefSize(SIDE, SIDE);

        if (((xcount + (ycount + 1) & 1) == 1)) {
            this.setStyle("-fx-background-color: lightblue");
        } else {
            this.setStyle("-fx-background-color: skyblue");
        }


        this.index = xcount + (ycount * Board.BOARDSIDE);

        ++xcount;
        if (xcount >= Board.BOARDSIDE) {
            ++ycount;
            xcount = 0;
        }

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

    public void setShip(Ship ship) {
        this.ship = ship;
    }

    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }

    public void show() {
        System.out.println("- Hokje " + index);
    }

    private void checkSquare() {

        board.setGrenades(board.getGrenades() - 1);
        if (board.getGrenades() <= 0) {
            int shipsLeft = board.getShipsLeft();
            board.setStageTitle("No more grenades.  You lost.");
            board.gameOver();
            return;
        }

        if (isOccupied()) {

            if (ship.isDead()) {
                board.setStageTitle("Shooting wreckage is counter productive");
            } else {
                if (ship.isDamaged(this)) {
                    board.setStageTitle("Ship #" + ship.getNumber() + " already damaged here");
                } else {
                    board.setStageTitle("Hit!");

                    this.setStyle("-fx-background-color: gray");

                    if (ship.hit(this)) {
                        for (Hokje d : ship.getDamagedParts()) {
                            d.setStyle("-fx-background-color: darkblue");
                        }

                        board.setShipsLeft(board.getShipsLeft() - 1);
                        if (board.getShipsLeft() <= 0) return;

                        board.setGrenades(board.getGrenades() + (Board.BOARDSIDE) / 2);
                        board.setStageTitle(ship.getName() + " destroyed. " + board.getShipsLeft() + " more ships remain.");
                        return;
                    } else {
                        board.setStageTitle("Ship #" + ship.getNumber() + " is damaged");
                    }
                }
            }
        } else {
            board.setStageTitle("Missed, nothing at " + index);
        }
    }


}


