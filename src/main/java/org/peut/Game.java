package org.peut;

import javafx.stage.Stage;

public class Game {
    Board b;
    Ship[] ships;
    int numberOfShips;

    Game(Stage stage){

        b = new Board( stage );

        numberOfShips = 3;
        ships = new Ship[ numberOfShips ];
        ships[0] = new Ship( "de Ruyter", b );
        ships[1] = new Ship( "Tromp", b );
        ships[2] = new Ship( "de Wit", b );

        b.show();

    }

    public int getNumberOfShips() {
        return numberOfShips;
    }

    public void setNumberOfShips(int numberOfShips) {
        this.numberOfShips = numberOfShips;
    }

}
