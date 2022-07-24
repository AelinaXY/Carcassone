package com.company;

import TileSet.Meeple;
import TileSet.Player;
import TileSet.Tile;
import TileSet.TileController;

import java.util.ArrayList;
import java.util.Collections;
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
        int numOfPlayers = 4;
/*
    while (!(numOfPlayers >= 1) || !(numOfPlayers <= 4))
    {
        System.out.println("How many players (2-4): ");
        numOfPlayers = myScanner.nextInt(); //FAILS IF THERE IS A NON INT INPUT
    }
    */


        ArrayList<Player> playersList = new ArrayList<>();

        for (int i = 1; i <= numOfPlayers; i++) {
            playersList.add(new Player(i));
            //Change number of human players (i > number of human players)
            if (i > 0) {
                playersList.get(i - 1).setAi(true);
                playersList.get(i - 1).setAiType(1);
                playersList.get(i - 1).setMeepleChance(i);

            }
        }

        //TURNS

        while (tileController.getTileDeckSize() > 0) {
        /* Stage 1:
    Draw a tile
    Display all possible tile placement locations
    Place tile on board
     */
            if (!playersList.get(0).isAi()) {
                //PHASE 1: Drawing
                //STEP 1: Draw a Tile from Deck
                System.out.println("Player " + playersList.get(0).getPlayerNumber() + "'s turn!");
                Tile drawnTile = tileController.drawTile();
                System.out.println("Drawn Tile is: " + drawnTile.toOutputString());
                //STEP 2: Display Valid Locations
                boolean locationFlag = false;
                int placeVsRotate, rotateDirection;
                int[] placementCoord = {0, 0};
                while (!locationFlag) {
                    System.out.print("Would you like to place the tile, or rotate it (1 or 2): ");
                    placeVsRotate = myScanner.nextInt();
                    if (placeVsRotate == 1) {
                        System.out.println("Where would you like to place the tile.");
                        System.out.println("These are the valid placements: " + tileController.getValidPlacementsToString());
                        System.out.print("Enter X coordinate: ");
                        placementCoord[0] = myScanner.nextInt(); //FAILS IF THERE IS A NON INT INPUT
                        System.out.print("Enter Y coordinate: ");
                        placementCoord[1] = myScanner.nextInt(); //FAILS IF THERE IS A NON INT INPUT
                        System.out.println("Coordinates are: [" + placementCoord[0] + "," + placementCoord[1] + "]\n");
                        //STEP 3: Place Tile
                        locationFlag = tileController.placeTileBoard(drawnTile, placementCoord);
                    }
                    if (placeVsRotate == 2) {
                        System.out.print("Would you like to rotate left or right? (1 or 2): ");
                        rotateDirection = myScanner.nextInt();
                        if (rotateDirection == 1) {
                            drawnTile.rotateLeft();
                        } else if (rotateDirection == 2) {
                            drawnTile.rotateRight();
                        } else {
                            System.out.println("Not 1 or 2");
                        }
                        System.out.println("Drawn Tile is: " + drawnTile.toOutputString() + "\n");

                    }

                }
                //PHASE 2: Placing Meeples
                //Step 1: Evaulate whether player has free Meeples
                //Step 2: Evaluate where the player can place Meeples
                //Step 3: Place Meeple on Tile
                boolean meepleFlag = true;
                int sidePlacement;
                while (meepleFlag) {
                    System.out.print("Would you like to place a Meeple (Y/N)?: ");
                    myScanner.nextLine();
                    String output = myScanner.nextLine();

                    if (output.equals("y") || output.equals("Y") || output.equals("yes") || output.equals("Yes")) {
                        Meeple freeMeeple = playersList.get(0).getFirstFreeMeeple();

                        if (freeMeeple != null) {
                            System.out.println("Where would you like to place the meeple.");
                            System.out.println("These are the valid placements: " + tileController.getBoardToString());
                            System.out.print("Enter X coordinate: ");
                            placementCoord[0] = myScanner.nextInt(); //FAILS IF THERE IS A NON INT INPUT
                            System.out.print("Enter Y coordinate: ");
                            placementCoord[1] = myScanner.nextInt(); //FAILS IF THERE IS A NON INT INPUT
                            System.out.print("Enter direction of meeple (0 = north, 1 = east, 2 = south, 3 = west): ");
                            sidePlacement = myScanner.nextInt(); //FAILS IF THERE IS A NON INT INPUT
                            System.out.println("Coordinates are: [" + placementCoord[0] + "," + placementCoord[1] + "," + sidePlacement + "]\n");
                            tileController.placeMeeple(placementCoord, freeMeeple, sidePlacement);
                            System.out.println("Meeple Placed");
                            meepleFlag = false;
                        } else {
                            System.out.println("No Free Meeples");
                            meepleFlag = false;
                        }
                    } else if (output.equals("n") || output.equals("N") || output.equals("no") || output.equals("No")) {
                        System.out.println("No Meeple Placed");
                        meepleFlag = false;
                    } else {
                        System.out.println("Invalid Input");
                    }
                }

                //PHASE 3: Resolving Meeples
                //Step 1: Check all Meeples
                //Step 2: See if Meeples are now on completed features
                //Step 3: Score appropriately

                for (Player player : playersList) {
                    player.updateMeeples();
                }


            }

            //AI Turn Logic
            else {
                //Random Logic
                if (playersList.get(0).getAiType() == 1) {
                    //PHASE 1: Placing Tile
                    //Building a list of all places a tile can be placed
                    ArrayList<int[]> allValidTilePlacements = new ArrayList<>(3);
                    Tile drawnTile = tileController.drawTile();
                    System.out.println("Drawn Tile is: " + drawnTile.toOutputString());
                    ArrayList<int[]> validPlacements = tileController.getValidPlacements();
                    Collections.shuffle(validPlacements);
                    for (int i = 0; i < validPlacements.size(); i++) {
                        for (int j = 0; j < 4; j++) {
                            if (tileController.placeTileBoardCheck(drawnTile, validPlacements.get(i))) {
                                allValidTilePlacements.add(new int[]{validPlacements.get(i)[0], validPlacements.get(i)[1], j});
                            }
                            drawnTile.rotateRight();
                        }
                    }
                    Collections.shuffle(allValidTilePlacements);

                    for (int i = 0; i < allValidTilePlacements.get(0)[2]; i++) {
                        drawnTile.rotateRight();
                    }
                    tileController.placeTileBoard(drawnTile, new int[]{allValidTilePlacements.get(0)[0], allValidTilePlacements.get(0)[1]});
                    System.out.println("Player " + playersList.get(0).getPlayerNumber() + " placed tile at [" + allValidTilePlacements.get(0)[0] + "," + allValidTilePlacements.get(0)[1] + "] with " + allValidTilePlacements.get(0)[2] + " rotate(s) right.");

                    //PHASE 2: Placing Meeple
                    Meeple meeple = playersList.get(0).getFirstFreeMeeple();
                    ArrayList<int[]> meeplePlacements = new ArrayList<>();
                    meeplePlacements.add(new int[]{-1, 0});
                    ArrayList<Tile> board = tileController.getBoard();
                    if (meeple != null) {
                        for (int i = 0; i < board.size(); i++) {
                            for (int j = 0; j < 4; j++) {
                                if (meeple.addMeepleToTileTest(board.get(i), j)) {
                                    if (board.get(i).hasNoMeepleAt(j)) {
                                        meeplePlacements.add(new int[]{i, j});
                                    }
                                }
                            }
                        }
                        int meeplePlacementSize = meeplePlacements.size();
                        //In a 4 AI game, Meeple Chance should be ~50% for player 1,
                        //~33% for player 2, ~25% for player 3, ~20% for player 4
                        for (int i = 0; i < meeplePlacementSize*playersList.get(0).getMeepleChance(); i++) {
                            meeplePlacements.add(new int[]{-1, 0});
                        }
                        Collections.shuffle(meeplePlacements);

                        if (meeplePlacements.get(0)[0] != -1) {
                            System.out.println("Player " + playersList.get(0).getPlayerNumber() + " placed a meeple at [" + board.get(meeplePlacements.get(0)[0]).getCoords()[0] + "," + board.get(meeplePlacements.get(0)[0]).getCoords()[1] + "] with " + meeplePlacements.get(0)[1] + " direction.");
                            meeple.addMeepleToTile(board.get(meeplePlacements.get(0)[0]), meeplePlacements.get(0)[1]);
                        }
                    }
                }


//                    System.out.println("\n\n Meeple Positions Pre Update: ");
//                    for (Player player : playersList) {
//                        System.out.println("Player " + player.getPlayerNumber());
//                        player.printMeepleLocations();
//                    }

                //PHASE 3: Resolving Meeples
                //Step 1: Check all Meeples
                //Step 2: See if Meeples are now on completed features
                //Step 3: Score appropriately

                for (Player player : playersList) {
                    player.updateMeeples();
                }

            }

//                System.out.println("\n\n Meeple Positions Post Update : ");
//                for (Player player : playersList) {
//                    System.out.println("Player " + player.getPlayerNumber());
//                    player.printMeepleLocations();
//                }

            System.out.println("\n\nEND OF TURN\n\n");
            nextPlayer(playersList);
            //System.out.println(playersList.get(0).isAi());
        }

        for (Player player : playersList) {
            player.finalMeeples();
        }
        System.out.println("Final Scores:");
        int[] playerWin = {-1, -1};
        for (Player player : playersList) {
            System.out.println("Player " + player.getPlayerNumber() + " scored " + player.getPlayerScore() + "!");
            if (player.getPlayerScore() > playerWin[1]) {
                playerWin[0] = player.getPlayerNumber();
                playerWin[1] = player.getPlayerScore();
            }
        }
        System.out.println("\nCONGRATULATIONS, Player " + playerWin[0] + " won with a score of " + playerWin[1] + "!");

        System.out.println("DONE\n\n\n");
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
