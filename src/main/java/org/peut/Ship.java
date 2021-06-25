package org.peut;

import java.util.ArrayList;
import java.util.Random;

public class Ship {

    public static final int length=3;

    private ArrayList<Hokje> hokjes;
    private String      name;
    private int         number;
    private boolean     dead;


    Ship( String name, Board board ){
        this.name    = name;
        this.dead    = false;
        hokjes = new ArrayList<Hokje>();

        placeShip( board );
    }

    private void placeShip( Board board ){
        boolean vertical = false;
        if ( (Math.random() * 10) < 5 ) vertical = true;


        boolean ready = false;
        int maxcount = 100;

        while ( ! ready && maxcount >= 0 ){
            int endrow = Board.BOARDSIDE;
            int endcol = Board.BOARDSIDE;

            if( vertical ){
                endrow -= 2;
            }else{
                endcol -= 2;
            }

            Random r = new Random();
            int startRow = r.nextInt( endrow);
            int startCol = r.nextInt( endcol);

            ready = true;
            if ( vertical ) {
                for( int i = 0; i < length; ++i) {
                    if (board.getHokje(startRow + i, startCol).isOccupied()) {
                        ready = false;
                        break;
                    }
                }
            }else {
                for (int i = 0; i < length; ++i) {
                    if (board.getHokje(startRow, startCol + i).isOccupied()) {
                        ready = false;
                        break;
                    }
                }
            }

            if ( ready ) {
                if ( vertical ) {
                    for( int i = 0; i < length; ++i) {

                        Hokje square = board.getHokje(startRow + i, startCol);
                        square.setOccupied( true);
                        square.setShip( this);
                        hokjes.add( square );
                    }
                }else {
                    for (int i = 0; i < length; ++i) {

                        Hokje square = board.getHokje(startRow, startCol + i);
                        square.setOccupied( true);
                        square.setShip( this );
                        hokjes.add( square );
                    }
                }
            }else{
                System.out.println("Crypto replace " + name);
                maxcount--;
            }

        }

        if ( maxcount >= 0 ){
            board.setShipCount( (board.getShipCount() + 1) );
            this.number = board.getShipCount();
        }else{
            throw new IllegalArgumentException( "No space found for " + name + ". Reduce number of ships.");
        }

    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public boolean isDead() {
        return dead;
    }

    public boolean isDamaged(Hokje square ){
        if ( !hokjes.contains( square ) ){
            return( true );
        }
        return(false);
    }

    public boolean hit(Hokje square) {
        hokjes.remove(square);
        if ( hokjes.isEmpty() ){
            dead = true;
        }
        return dead;
    }

    public void show(){
        System.out.println("Ship name: " + name);
        if ( ! dead ) {
            System.out.println("Hokjes occupied by #" + number + " - " + name );
            for ( Hokje a :hokjes) {
                a.show();
            }
        }
    }
}