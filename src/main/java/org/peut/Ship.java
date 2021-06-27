package org.peut;

import java.util.ArrayList;
import java.util.Random;

public class Ship {

    private int length;

    private ArrayList<Hokje> healthyParts;
    private ArrayList<Hokje> damagedParts;

    private String name;
    private int number;
    private boolean dead;


    Ship(String name, Board board) {
        this.name = name;
        this.dead = false;
        healthyParts = new ArrayList<>();
        damagedParts = new ArrayList<>();

        placeShip(board);
    }

    private void placeShip(Board board) {
        boolean vertical = false;
        if ((Math.random() * 10) < 5) vertical = true;


        length = (int) Math.floor((Math.random() * 3.2) + 1);
        System.out.println("length of" + this.name + " is " + length);

        boolean ready = false;
        int maxcount = 100;

        while (!ready && maxcount >= 0) {
            int endrow = Board.BOARDSIDE;
            int endcol = Board.BOARDSIDE;

            if (vertical) {
                endrow -= (length - 1);
            } else {
                endcol -= (length - 1);
            }

            Random r = new Random();
            int startRow = r.nextInt(endrow);
            int startCol = r.nextInt(endcol);

            ready = true;
            if (vertical) {
                for (int i = 0; i < length; ++i) {
                    if (board.getHokje(startRow + i, startCol).isOccupied()) {
                        ready = false;
                        break;
                    }
                }
            } else {
                for (int i = 0; i < length; ++i) {
                    if (board.getHokje(startRow, startCol + i).isOccupied()) {
                        ready = false;
                        break;
                    }
                }
            }

            if (ready) {
                if (vertical) {
                    for (int i = 0; i < length; ++i) {

                        Hokje square = board.getHokje(startRow + i, startCol);
                        square.setOccupied(true);
                        square.setShip(this);
                        healthyParts.add(square);
                    }
                } else {
                    for (int i = 0; i < length; ++i) {

                        Hokje square = board.getHokje(startRow, startCol + i);
                        square.setOccupied(true);
                        square.setShip(this);
                        healthyParts.add(square);
                    }
                }
            } else {
                System.out.println("Ship replace " + name);
                maxcount--;
            }

        }

        if (maxcount >= 0) {
            board.setShipsLeft((board.getShipsLeft() + 1));
            this.number = board.getShipsLeft();

            this.show();
        } else {
            throw new IllegalArgumentException("No space found for " + name + ". Reduce number of ships.");
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

    public int getLength() {
        return length;
    }

    public void setLength(int length) {
        this.length = length;
    }

    public ArrayList<Hokje> getDamagedParts() {
        return damagedParts;
    }

    public void setDamagedParts(ArrayList<Hokje> damagedParts) {
        this.damagedParts = damagedParts;
    }


    public boolean isDamaged(Hokje square) {
        if (!healthyParts.contains(square)) {
            return (true);
        }
        return (false);
    }

    public boolean hit(Hokje square) {
        healthyParts.remove(square);
        damagedParts.add(square);
        if (healthyParts.isEmpty()) {
            dead = true;
        }
        return dead;
    }

    public void show() {
        System.out.println("Ship name: " + name);
        if (!dead) {
            System.out.println("Hokjes occupied by #" + number + " - " + name);
            for (Hokje a : healthyParts) {
                a.show();
            }
        }
    }
}