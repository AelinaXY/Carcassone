package com.company;

import TileSet.Player;
import TileSet.TYPE;
import TileSet.Tile;
import TileSet.TileController;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.*;

class MainTest {

    private final PrintStream standardOut = System.out;
    private final ByteArrayOutputStream outputStreamCaptor = new ByteArrayOutputStream();

    @Test
    void integrationTestCompletedRoad() {
        Player test = new Player(1);
        TileController controller = new TileController();
        controller.placeTileBoard(new Tile(TYPE.FIELD,TYPE.ROAD,TYPE.FIELD,TYPE.ROAD,false,false,false,true), new int[]{1, 0});
        controller.placeTileBoard(new Tile(TYPE.FIELD,TYPE.ROAD,TYPE.ROAD,TYPE.FIELD,false,false,false,false), new int[]{-1, 0});
        controller.placeTileBoard(new Tile(TYPE.ROAD,TYPE.ROAD,TYPE.FIELD,TYPE.FIELD,false,false,false,false), new int[]{-1, -1});
        controller.placeTileBoard(new Tile(TYPE.FIELD,TYPE.ROAD,TYPE.FIELD,TYPE.ROAD,false,false,false,false), new int[]{0, -1});
        controller.placeTileBoard(new Tile(TYPE.FIELD,TYPE.ROAD,TYPE.FIELD,TYPE.ROAD,false,false,false,true), new int[]{1, -1});

        System.setOut(new PrintStream(outputStreamCaptor));
        controller.placeMeeple(new int[]{-1,0},test.getFirstFreeMeeple(),2);
        assertEquals("ROAD COMPLETE SWITCH\r\nMEEPLE ADDED", outputStreamCaptor.toString().trim());
        System.setOut(standardOut);

    }

    @Test
    void integrationTestIncompleteRoad() {
        Player test = new Player(1);
        TileController controller = new TileController();
        controller.placeTileBoard(new Tile(TYPE.FIELD,TYPE.ROAD,TYPE.FIELD,TYPE.ROAD,false,false,false,false), new int[]{1, 0});
        controller.placeTileBoard(new Tile(TYPE.FIELD,TYPE.ROAD,TYPE.ROAD,TYPE.FIELD,false,false,false,false), new int[]{-1, 0});
        controller.placeTileBoard(new Tile(TYPE.ROAD,TYPE.ROAD,TYPE.FIELD,TYPE.FIELD,false,false,false,false), new int[]{-1, -1});
        controller.placeTileBoard(new Tile(TYPE.FIELD,TYPE.ROAD,TYPE.FIELD,TYPE.ROAD,false,false,false,false), new int[]{0, -1});
        controller.placeTileBoard(new Tile(TYPE.FIELD,TYPE.ROAD,TYPE.FIELD,TYPE.ROAD,false,false,false,true), new int[]{1, -1});

        System.setOut(new PrintStream(outputStreamCaptor));
        controller.placeMeeple(new int[]{-1,0},test.getFirstFreeMeeple(),2);
        assertEquals("ROAD INCOMPLETE SWITCH\r\nMEEPLE ADDED", outputStreamCaptor.toString().trim());
        System.setOut(standardOut);

    }

    @Test
    void integrationTestCompletedMonastery() {
        Player test = new Player(1);
        TileController controller = new TileController();
        controller.placeTileBoard(new Tile(TYPE.FIELD, TYPE.FIELD, TYPE.FIELD, TYPE.FIELD, true, false, false, true), new int[]{0, -1});
        controller.placeTileBoard(new Tile(TYPE.FIELD, TYPE.ROAD, TYPE.FIELD, TYPE.ROAD, false, false, false, false), new int[]{-1, 0});
        controller.placeTileBoard(new Tile(TYPE.FIELD, TYPE.ROAD, TYPE.FIELD, TYPE.ROAD, false, false, false, false), new int[]{1, 0});
        controller.placeTileBoard(new Tile(TYPE.FIELD, TYPE.FIELD, TYPE.FIELD, TYPE.FIELD, false, false, false, false), new int[]{1, -1});
        controller.placeTileBoard(new Tile(TYPE.FIELD, TYPE.FIELD, TYPE.FIELD, TYPE.FIELD, false, false, false, false), new int[]{1, -2});
        controller.placeTileBoard(new Tile(TYPE.FIELD, TYPE.FIELD, TYPE.FIELD, TYPE.FIELD, false, false, false, false), new int[]{0, -2});
        controller.placeTileBoard(new Tile(TYPE.FIELD, TYPE.FIELD, TYPE.FIELD, TYPE.FIELD, false, false, false, false), new int[]{-1, -2});
        controller.placeTileBoard(new Tile(TYPE.FIELD, TYPE.FIELD, TYPE.FIELD, TYPE.FIELD, false, false, false, false), new int[]{-1, -1});


        System.setOut(new PrintStream(outputStreamCaptor));
        controller.placeMeeple(new int[]{0, -1}, test.getFirstFreeMeeple(), 2);
        assertEquals("MONASTERY COMPLETE SWITCH\r\nMEEPLE ADDED", outputStreamCaptor.toString().trim());
        System.setOut(standardOut);
    }

    @Test
    void integrationTestIncompleteMonastery() {
        Player test = new Player(1);
        TileController controller = new TileController();
        controller.placeTileBoard(new Tile(TYPE.FIELD, TYPE.FIELD, TYPE.FIELD, TYPE.FIELD, true, false, false, true), new int[]{0, -1});
        controller.placeTileBoard(new Tile(TYPE.FIELD, TYPE.ROAD, TYPE.FIELD, TYPE.ROAD, false, false, false, false), new int[]{-1, 0});
        controller.placeTileBoard(new Tile(TYPE.FIELD, TYPE.ROAD, TYPE.FIELD, TYPE.ROAD, false, false, false, false), new int[]{1, 0});
        controller.placeTileBoard(new Tile(TYPE.FIELD, TYPE.FIELD, TYPE.FIELD, TYPE.FIELD, false, false, false, false), new int[]{1, -1});
        controller.placeTileBoard(new Tile(TYPE.FIELD, TYPE.FIELD, TYPE.FIELD, TYPE.FIELD, false, false, false, false), new int[]{1, -2});
        controller.placeTileBoard(new Tile(TYPE.FIELD, TYPE.FIELD, TYPE.FIELD, TYPE.FIELD, false, false, false, false), new int[]{0, -2});
        controller.placeTileBoard(new Tile(TYPE.FIELD, TYPE.FIELD, TYPE.FIELD, TYPE.FIELD, false, false, false, false), new int[]{-1, -2});


        System.setOut(new PrintStream(outputStreamCaptor));
        controller.placeMeeple(new int[]{0, -1}, test.getFirstFreeMeeple(), 2);
        assertEquals("MONASTERY INCOMPLETE SWITCH\r\nMEEPLE ADDED", outputStreamCaptor.toString().trim());
        System.setOut(standardOut);
    }
}