package com.company;

import TileSet.*;

import java.util.Collections;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

public class Main {

    public static void main(String[] args) {
//        Tile tile1 = new Tile(TYPE.EMPTY, TYPE.EMPTY, TYPE.ROAD, TYPE.EMPTY, true);
//        Tile tile2 = new Tile();
//        Tile tile3 = new Tile(TYPE.FIELD, TYPE.CITY, TYPE.CITY, TYPE.EMPTY, false);
//
//        tile1.addNorthConnection(tile2);
//        tile2.addNorthConnection(tile3);
//        tile3.rotateLeft();
//        tile2.addNorthConnection(tile3);
//
//        System.out.println(tile1.toConnectString());
//        System.out.println(tile2.toConnectString());
//        System.out.println(tile3.toConnectString());

        TileController controller = new TileController();
        Tile i = controller.drawTile();
        while(i != null) {
//            cout++;
//            System.out.println(cout + ":\t" + i);
            int rand = ThreadLocalRandom.current().nextInt(0,controller.getValidPlacements().size());
            if(controller.placeTileBoard(i, new int[]{controller.getValidPlacements().get(rand)[0],controller.getValidPlacements().get(rand)[1]}))
            {
                System.out.println(i.toString());
            }
            else
            {
                controller.returnTile(i);
            }
            i = controller.drawTile();
        }
        System.out.println("DONE");
    }
}
