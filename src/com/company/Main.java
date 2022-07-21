package com.company;

import TileSet.Player;
import TileSet.Tile;
import TileSet.TileController;

import java.util.ArrayList;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        /* SETUP:
        Create Tile Controller
        Create Players
        Choose number of people
        Choose number of AI
         */
        Scanner myScanner = new Scanner(System.in);
        TileController tileController = new TileController();
        int numOfPlayers = -1;
        while (!(numOfPlayers >= 1) || !(numOfPlayers <= 4))
        {
            System.out.println("How many players (2-4): ");
            numOfPlayers = myScanner.nextInt(); //FAILS IF THERE IS A NON INT INPUT
        }

        ArrayList<Player> playersList = new ArrayList<Player>();

        for(int i = 1; i <= numOfPlayers; i++)
        {
            playersList.add(new Player(i));
            if(i > 1)
            {
                playersList.get(i-1).setAi(true);
            }
        }

        //for(int i = 0; i>)

        //TURNS

        while (tileController.getTileDeckSize() > 0)
        {
            /* STAGE 1:
        Draw a tile
        Display all possible tile placement locations
        Place tile on board
         */
            if(!playersList.get(0).isAi())
            {
                //STEP 1: Draw
                System.out.println("Player " + playersList.get(0).getPlayerNumber() +"'s turn!");
                Tile drawnTile = tileController.drawTile();
                System.out.println("Drawn Tile is: " + drawnTile.toOutputString());
                //STEP 2: Display Valid Locations
                boolean locationFlag = false;
                int xCoord, yCoord, placeVsRotate, rotateDirection;
                int[] placementCoord = {0,0};
                while(!locationFlag) {
                    System.out.print("Would you like to place the tile, or rotate it (1 or 2): ");
                    placeVsRotate = myScanner.nextInt();
                    if(placeVsRotate == 1)
                    {
                        System.out.println("Where would you like to place the tile.");
                        System.out.println("These are the valid placements: " + tileController.getValidPlacementsToString());
                        System.out.print("Enter X coordinate: ");
                        placementCoord[0] = myScanner.nextInt(); //FAILS IF THERE IS A NON INT INPUT
                        System.out.print("Enter Y coordinate: ");
                        placementCoord[1] = myScanner.nextInt(); //FAILS IF THERE IS A NON INT INPUT
                        System.out.println("Coordinates are: [" + placementCoord[0] + "," + placementCoord[1] + "]\n");
                        locationFlag = tileController.placeTileBoard(drawnTile, placementCoord);
                    }
                    if(placeVsRotate == 2)
                    {
                        System.out.print("Would you like to rotate left or right? (1 or 2): ");
                        rotateDirection = myScanner.nextInt();
                        if(rotateDirection == 1)
                        {
                            drawnTile.rotateLeft();
                        }
                        else if(rotateDirection == 2)
                        {
                            drawnTile.rotateRight();
                        }
                        else
                        {
                            System.out.println("Not 1 or 2");
                        }
                        System.out.println("Drawn Tile is: " + drawnTile.toOutputString() + "\n");

                    }

                }

            }

            else
            {
                System.out.println("\nAI TURN\n");
            }


            nextPlayer(playersList);
            //System.out.println(playersList.get(0).isAi());
        }
        System.out.println("DONE");
    }

    private static void nextPlayer(ArrayList<Player> playersList)
    {
        Player temp = playersList.get(0);
        for(int i = 1; i< playersList.size(); i++)
        {
            playersList.set(i-1, playersList.get(i));
        }
        playersList.set(playersList.size()-1, temp);

    }

}
