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
        assertEquals("ROAD COMPLETE SWITCH\r\nMEEPLE NOT ADDED", outputStreamCaptor.toString().trim());
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
        assertEquals("MONASTERY COMPLETE SWITCH\r\nMEEPLE NOT ADDED", outputStreamCaptor.toString().trim());
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

    //STARTING ON UNCONNECTED CITY
    @Test
    void integrationTestSimpleCompleteCity() {
        Player test = new Player(1);
        TileController controller = new TileController();
        controller.placeTileBoard(new Tile(TYPE.FIELD, TYPE.FIELD, TYPE.CITY, TYPE.FIELD, false, false, false, false), new int[]{0, 1});
        System.setOut(new PrintStream(outputStreamCaptor));
        controller.placeMeeple(new int[]{0, 0}, test.getFirstFreeMeeple(), 0);
        assertEquals("CITY COMPLETE SWITCH\r\nMEEPLE NOT ADDED", outputStreamCaptor.toString().trim());
        System.setOut(standardOut);
    }

    //STARTING ON UNCONNECTED TILE
    @Test
    void integrationTestSimpleIncompleteConnectedCity() {
        Player test = new Player(1);
        TileController controller = new TileController();
        controller.placeTileBoard(new Tile(TYPE.FIELD, TYPE.CITY, TYPE.CITY, TYPE.FIELD, false, true, false, false), new int[]{0, 1});
        System.setOut(new PrintStream(outputStreamCaptor));
        controller.placeMeeple(new int[]{0, 0}, test.getFirstFreeMeeple(), 0);
        assertEquals("CITY INCOMPLETE SWITCH\r\nMEEPLE ADDED", outputStreamCaptor.toString().trim());
        System.setOut(standardOut);
    }

    //STARTING ON UNCONNECTED TILE
    @Test
    void integrationTestSimpleCompleteUnconnectedCity() {
        Player test = new Player(1);
        TileController controller = new TileController();
        controller.placeTileBoard(new Tile(TYPE.CITY, TYPE.FIELD, TYPE.CITY, TYPE.FIELD, false, false, false, false), new int[]{0, 1});
        System.setOut(new PrintStream(outputStreamCaptor));
        controller.placeMeeple(new int[]{0, 0}, test.getFirstFreeMeeple(), 0);
        assertEquals("CITY COMPLETE SWITCH\r\nMEEPLE NOT ADDED", outputStreamCaptor.toString().trim());
        System.setOut(standardOut);
    }

    //STARTING ON UNCONNECTED TILE
    @Test
    void integrationTestComplexCompleteCity() {
        Player test = new Player(1);
        TileController controller = new TileController();
        //Cannot place -1 first so 0 is used first
        controller.placeTileBoard(new Tile(TYPE.CITY, TYPE.CITY, TYPE.CITY, TYPE.CITY, false, true, false, false), new int[]{0, 1});
        controller.placeTileBoard(new Tile(TYPE.CITY, TYPE.FIELD, TYPE.FIELD, TYPE.CITY, false, true, false, false), new int[]{1, 1});
        controller.placeTileBoard(new Tile(TYPE.CITY, TYPE.CITY, TYPE.FIELD, TYPE.FIELD, false, true, false, false), new int[]{-1, 1});
        controller.placeTileBoard(new Tile(TYPE.CITY, TYPE.CITY, TYPE.CITY, TYPE.FIELD, false, true, false, false), new int[]{-1, 2});
        controller.placeTileBoard(new Tile(TYPE.CITY, TYPE.CITY, TYPE.CITY, TYPE.CITY, false, true, false, false), new int[]{0, 2});
        controller.placeTileBoard(new Tile(TYPE.CITY, TYPE.FIELD, TYPE.CITY, TYPE.CITY, false, true, false, false), new int[]{1, 2});
        controller.placeTileBoard(new Tile(TYPE.FIELD, TYPE.CITY, TYPE.CITY, TYPE.FIELD, false, true, false, false), new int[]{-1, 3});
        controller.placeTileBoard(new Tile(TYPE.FIELD, TYPE.CITY, TYPE.CITY, TYPE.CITY, false, true, false, false), new int[]{0, 3});
        controller.placeTileBoard(new Tile(TYPE.FIELD, TYPE.FIELD, TYPE.CITY, TYPE.CITY, false, true, false, false), new int[]{1, 3});
        System.setOut(new PrintStream(outputStreamCaptor));
        controller.placeMeeple(new int[]{0, 0}, test.getFirstFreeMeeple(), 0);
        assertEquals("CITY COMPLETE SWITCH\r\nMEEPLE NOT ADDED", outputStreamCaptor.toString().trim());
        System.setOut(standardOut);
    }

    //STARTING ON UNCONNECTED TILE
    @Test
    void integrationTestComplexIncompleteCity() {
        Player test = new Player(1);
        TileController controller = new TileController();
        //Cannot place -1 first so 0 is used first
        controller.placeTileBoard(new Tile(TYPE.CITY, TYPE.CITY, TYPE.CITY, TYPE.CITY, false, true, false, false), new int[]{0, 1});
        controller.placeTileBoard(new Tile(TYPE.CITY, TYPE.FIELD, TYPE.FIELD, TYPE.CITY, false, true, false, false), new int[]{1, 1});
        controller.placeTileBoard(new Tile(TYPE.CITY, TYPE.CITY, TYPE.FIELD, TYPE.FIELD, false, true, false, false), new int[]{-1, 1});
        controller.placeTileBoard(new Tile(TYPE.CITY, TYPE.CITY, TYPE.CITY, TYPE.FIELD, false, true, false, false), new int[]{-1, 2});
        controller.placeTileBoard(new Tile(TYPE.CITY, TYPE.FIELD, TYPE.CITY, TYPE.CITY, false, true, false, false), new int[]{1, 2});
        controller.placeTileBoard(new Tile(TYPE.FIELD, TYPE.CITY, TYPE.CITY, TYPE.FIELD, false, true, false, false), new int[]{-1, 3});
        controller.placeTileBoard(new Tile(TYPE.FIELD, TYPE.CITY, TYPE.CITY, TYPE.CITY, false, true, false, false), new int[]{0, 3});
        controller.placeTileBoard(new Tile(TYPE.FIELD, TYPE.FIELD, TYPE.CITY, TYPE.CITY, false, true, false, false), new int[]{1, 3});
        System.setOut(new PrintStream(outputStreamCaptor));
        controller.placeMeeple(new int[]{0, 0}, test.getFirstFreeMeeple(), 0);
        assertEquals("CITY INCOMPLETE SWITCH\r\nMEEPLE ADDED", outputStreamCaptor.toString().trim());
        System.setOut(standardOut);
    }


    //STARTING ON CONNECTED TILE
    @Test
    void integrationTestSimpleIncompleteConnectedCityConnectedStart() {
        Player test = new Player(1);
        TileController controller = new TileController();
        controller.placeTileBoard(new Tile(TYPE.FIELD, TYPE.CITY, TYPE.CITY, TYPE.FIELD, false, true, false, false), new int[]{0, 1});
        System.setOut(new PrintStream(outputStreamCaptor));
        controller.placeMeeple(new int[]{0, 1}, test.getFirstFreeMeeple(), 2);
        assertEquals("CITY INCOMPLETE SWITCH\r\nMEEPLE ADDED", outputStreamCaptor.toString().trim());
        System.setOut(standardOut);
    }

    //STARTING ON CONNECTED TILE
    @Test
    void integrationTestSimpleCompleteUnconnectedCityConnectedStart() {
        Player test = new Player(1);
        TileController controller = new TileController();
        controller.placeTileBoard(new Tile(TYPE.CITY, TYPE.FIELD, TYPE.CITY, TYPE.FIELD, false, false, false, false), new int[]{0, 1});
        System.setOut(new PrintStream(outputStreamCaptor));
        controller.placeMeeple(new int[]{0, 1}, test.getFirstFreeMeeple(), 2);
        assertEquals("CITY COMPLETE SWITCH\r\nMEEPLE NOT ADDED", outputStreamCaptor.toString().trim());
        System.setOut(standardOut);
    }

    //STARTING ON CONNECTED TILE
    @Test
    void integrationTestComplexCompleteCityConnectedStartConnectedStart() {
        Player test = new Player(1);
        TileController controller = new TileController();
        //Cannot place -1 first so 0 is used first
        controller.placeTileBoard(new Tile(TYPE.CITY, TYPE.CITY, TYPE.CITY, TYPE.CITY, false, true, false, false), new int[]{0, 1});
        controller.placeTileBoard(new Tile(TYPE.CITY, TYPE.FIELD, TYPE.FIELD, TYPE.CITY, false, true, false, false), new int[]{1, 1});
        controller.placeTileBoard(new Tile(TYPE.CITY, TYPE.CITY, TYPE.FIELD, TYPE.FIELD, false, true, false, false), new int[]{-1, 1});
        controller.placeTileBoard(new Tile(TYPE.CITY, TYPE.CITY, TYPE.CITY, TYPE.FIELD, false, true, false, false), new int[]{-1, 2});
        controller.placeTileBoard(new Tile(TYPE.CITY, TYPE.CITY, TYPE.CITY, TYPE.CITY, false, true, false, false), new int[]{0, 2});
        controller.placeTileBoard(new Tile(TYPE.CITY, TYPE.FIELD, TYPE.CITY, TYPE.CITY, false, true, false, false), new int[]{1, 2});
        controller.placeTileBoard(new Tile(TYPE.FIELD, TYPE.CITY, TYPE.CITY, TYPE.FIELD, false, true, false, false), new int[]{-1, 3});
        controller.placeTileBoard(new Tile(TYPE.FIELD, TYPE.CITY, TYPE.CITY, TYPE.CITY, false, true, false, false), new int[]{0, 3});
        controller.placeTileBoard(new Tile(TYPE.FIELD, TYPE.FIELD, TYPE.CITY, TYPE.CITY, false, true, false, false), new int[]{1, 3});
        System.setOut(new PrintStream(outputStreamCaptor));
        controller.placeMeeple(new int[]{0, 1}, test.getFirstFreeMeeple(), 2);
        assertEquals("CITY COMPLETE SWITCH\r\nMEEPLE NOT ADDED", outputStreamCaptor.toString().trim());
        System.setOut(standardOut);
    }

    //STARTING ON CONNECTED TILE
    @Test
    void integrationTestComplexIncompleteCityConnectedStart() {
        Player test = new Player(1);
        TileController controller = new TileController();
        //Cannot place -1 first so 0 is used first
        controller.placeTileBoard(new Tile(TYPE.CITY, TYPE.CITY, TYPE.CITY, TYPE.CITY, false, true, false, false), new int[]{0, 1});
        controller.placeTileBoard(new Tile(TYPE.CITY, TYPE.FIELD, TYPE.FIELD, TYPE.CITY, false, true, false, false), new int[]{1, 1});
        controller.placeTileBoard(new Tile(TYPE.CITY, TYPE.CITY, TYPE.FIELD, TYPE.FIELD, false, true, false, false), new int[]{-1, 1});
        controller.placeTileBoard(new Tile(TYPE.CITY, TYPE.CITY, TYPE.CITY, TYPE.FIELD, false, true, false, false), new int[]{-1, 2});
        controller.placeTileBoard(new Tile(TYPE.CITY, TYPE.FIELD, TYPE.CITY, TYPE.CITY, false, true, false, false), new int[]{1, 2});
        controller.placeTileBoard(new Tile(TYPE.FIELD, TYPE.CITY, TYPE.CITY, TYPE.FIELD, false, true, false, false), new int[]{-1, 3});
        controller.placeTileBoard(new Tile(TYPE.FIELD, TYPE.CITY, TYPE.CITY, TYPE.CITY, false, true, false, false), new int[]{0, 3});
        controller.placeTileBoard(new Tile(TYPE.FIELD, TYPE.FIELD, TYPE.CITY, TYPE.CITY, false, true, false, false), new int[]{1, 3});
        System.setOut(new PrintStream(outputStreamCaptor));
        controller.placeMeeple(new int[]{0, 1}, test.getFirstFreeMeeple(), 2);
        assertEquals("CITY INCOMPLETE SWITCH\r\nMEEPLE ADDED", outputStreamCaptor.toString().trim());
        System.setOut(standardOut);
    }


    @Test
    void integrationTestCityMeepleCompleteAfterPlacement() {
        Player test = new Player(1);
        TileController controller = new TileController();
        //Cannot place -1 first so 0 is used first
        controller.placeTileBoard(new Tile(TYPE.CITY, TYPE.CITY, TYPE.CITY, TYPE.CITY, false, true, false, false), new int[]{0, 1});
        controller.placeTileBoard(new Tile(TYPE.CITY, TYPE.FIELD, TYPE.FIELD, TYPE.CITY, false, true, false, false), new int[]{1, 1});
        controller.placeTileBoard(new Tile(TYPE.CITY, TYPE.CITY, TYPE.FIELD, TYPE.FIELD, false, true, false, false), new int[]{-1, 1});
        controller.placeTileBoard(new Tile(TYPE.CITY, TYPE.CITY, TYPE.CITY, TYPE.FIELD, false, true, false, false), new int[]{-1, 2});
        controller.placeTileBoard(new Tile(TYPE.CITY, TYPE.FIELD, TYPE.CITY, TYPE.CITY, false, true, false, false), new int[]{1, 2});
        controller.placeTileBoard(new Tile(TYPE.FIELD, TYPE.CITY, TYPE.CITY, TYPE.FIELD, false, true, false, false), new int[]{-1, 3});
        controller.placeTileBoard(new Tile(TYPE.FIELD, TYPE.CITY, TYPE.CITY, TYPE.CITY, false, true, false, false), new int[]{0, 3});
        controller.placeTileBoard(new Tile(TYPE.FIELD, TYPE.FIELD, TYPE.CITY, TYPE.CITY, false, true, false, false), new int[]{1, 3});
        controller.placeMeeple(new int[]{0, 1}, test.getFirstFreeMeeple(), 2);
        controller.placeTileBoard(new Tile(TYPE.CITY, TYPE.CITY, TYPE.CITY, TYPE.CITY, false, true, false, false), new int[]{0, 2});
        System.setOut(new PrintStream(outputStreamCaptor));
        test.updateMeeples();
        assertEquals("CITY COMPLETE SWITCH\r\nCOMPLETED CITY\r\nFREE MEEPLE\r\nPlayer 1 scored 20 points!", outputStreamCaptor.toString().trim());
        System.setOut(standardOut);
    }

    @Test
    void integrationTestRoadScoringRoadMiddle() {
        Player test = new Player(1);
        TileController controller = new TileController();
        controller.placeTileBoard(new Tile(TYPE.FIELD,TYPE.ROAD,TYPE.FIELD,TYPE.ROAD,false,false,false,true), new int[]{1, 0});
        controller.placeTileBoard(new Tile(TYPE.FIELD,TYPE.ROAD,TYPE.ROAD,TYPE.FIELD,false,false,false,false), new int[]{-1, 0});
        controller.placeTileBoard(new Tile(TYPE.ROAD,TYPE.ROAD,TYPE.FIELD,TYPE.FIELD,false,false,false,false), new int[]{-1, -1});
        controller.placeTileBoard(new Tile(TYPE.FIELD,TYPE.ROAD,TYPE.FIELD,TYPE.ROAD,false,false,false,false), new int[]{0, -1});
        //System.setOut(new PrintStream(outputStreamCaptor));
        controller.placeMeeple(new int[]{-1,0},test.getFirstFreeMeeple(),2);
        controller.placeTileBoard(new Tile(TYPE.FIELD,TYPE.ROAD,TYPE.FIELD,TYPE.ROAD,false,false,false,true), new int[]{1, -1});
        test.updateMeeples();
        assertEquals(5, test.getPlayerScore());
        //System.setOut(standardOut);
    }

    @Test
    void integrationTestRoadScoringRoadEnd() {
        Player test = new Player(1);
        TileController controller = new TileController();
        controller.placeTileBoard(new Tile(TYPE.FIELD,TYPE.ROAD,TYPE.FIELD,TYPE.ROAD,false,false,false,true), new int[]{1, 0});
        controller.placeTileBoard(new Tile(TYPE.FIELD,TYPE.ROAD,TYPE.ROAD,TYPE.FIELD,false,false,false,false), new int[]{-1, 0});
        controller.placeTileBoard(new Tile(TYPE.ROAD,TYPE.ROAD,TYPE.FIELD,TYPE.FIELD,false,false,false,false), new int[]{-1, -1});
        controller.placeTileBoard(new Tile(TYPE.FIELD,TYPE.ROAD,TYPE.FIELD,TYPE.ROAD,false,false,false,false), new int[]{0, -1});
        //System.setOut(new PrintStream(outputStreamCaptor));
        controller.placeMeeple(new int[]{1,0},test.getFirstFreeMeeple(),3);
        controller.placeTileBoard(new Tile(TYPE.FIELD,TYPE.ROAD,TYPE.FIELD,TYPE.ROAD,false,false,false,true), new int[]{1, -1});
        test.updateMeeples();
        assertEquals(5, test.getPlayerScore());
        //System.setOut(standardOut);
    }

    @Test
    void integrationTestRoadScoringRoadMiddleIncomplete() {
        Player test = new Player(1);
        TileController controller = new TileController();
        controller.placeTileBoard(new Tile(TYPE.FIELD,TYPE.ROAD,TYPE.FIELD,TYPE.ROAD,false,false,false,true), new int[]{1, 0});
        controller.placeTileBoard(new Tile(TYPE.FIELD,TYPE.ROAD,TYPE.ROAD,TYPE.FIELD,false,false,false,false), new int[]{-1, 0});
        controller.placeTileBoard(new Tile(TYPE.ROAD,TYPE.ROAD,TYPE.FIELD,TYPE.FIELD,false,false,false,false), new int[]{-1, -1});
        controller.placeTileBoard(new Tile(TYPE.FIELD,TYPE.ROAD,TYPE.FIELD,TYPE.ROAD,false,false,false,false), new int[]{0, -1});
        //System.setOut(new PrintStream(outputStreamCaptor));
        controller.placeMeeple(new int[]{-1,0},test.getFirstFreeMeeple(),2);
        test.finalMeeples();
        assertEquals(4, test.getPlayerScore());
        //System.setOut(standardOut);
    }

    //END OF GAME
    @Test
    void integrationTestRoadScoringRoadEndIncomplete() {
        Player test = new Player(1);
        TileController controller = new TileController();
        controller.placeTileBoard(new Tile(TYPE.FIELD,TYPE.ROAD,TYPE.FIELD,TYPE.ROAD,false,false,false,true), new int[]{1, 0});
        controller.placeTileBoard(new Tile(TYPE.FIELD,TYPE.ROAD,TYPE.ROAD,TYPE.FIELD,false,false,false,false), new int[]{-1, 0});
        controller.placeTileBoard(new Tile(TYPE.ROAD,TYPE.ROAD,TYPE.FIELD,TYPE.FIELD,false,false,false,false), new int[]{-1, -1});
        controller.placeTileBoard(new Tile(TYPE.FIELD,TYPE.ROAD,TYPE.FIELD,TYPE.ROAD,false,false,false,false), new int[]{0, -1});
        //System.setOut(new PrintStream(outputStreamCaptor));
        controller.placeMeeple(new int[]{1,0},test.getFirstFreeMeeple(),3);
        test.finalMeeples();
        assertEquals(4, test.getPlayerScore());
        //System.setOut(standardOut);
    }

    @Test
    void integrationTestCompletedMonasteryScoring() {
        Player test = new Player(1);
        TileController controller = new TileController();
        controller.placeTileBoard(new Tile(TYPE.FIELD, TYPE.FIELD, TYPE.FIELD, TYPE.FIELD, true, false, false, true), new int[]{0, -1});
        controller.placeTileBoard(new Tile(TYPE.FIELD, TYPE.ROAD, TYPE.FIELD, TYPE.ROAD, false, false, false, false), new int[]{-1, 0});
        controller.placeTileBoard(new Tile(TYPE.FIELD, TYPE.ROAD, TYPE.FIELD, TYPE.ROAD, false, false, false, false), new int[]{1, 0});
        controller.placeTileBoard(new Tile(TYPE.FIELD, TYPE.FIELD, TYPE.FIELD, TYPE.FIELD, false, false, false, false), new int[]{1, -1});
        controller.placeTileBoard(new Tile(TYPE.FIELD, TYPE.FIELD, TYPE.FIELD, TYPE.FIELD, false, false, false, false), new int[]{1, -2});
        controller.placeTileBoard(new Tile(TYPE.FIELD, TYPE.FIELD, TYPE.FIELD, TYPE.FIELD, false, false, false, false), new int[]{0, -2});
        controller.placeTileBoard(new Tile(TYPE.FIELD, TYPE.FIELD, TYPE.FIELD, TYPE.FIELD, false, false, false, false), new int[]{-1, -2});
        controller.placeMeeple(new int[]{0, -1}, test.getFirstFreeMeeple(), 2);
        controller.placeTileBoard(new Tile(TYPE.FIELD, TYPE.FIELD, TYPE.FIELD, TYPE.FIELD, false, false, false, false), new int[]{-1, -1});
        test.updateMeeples();
        assertEquals(9, test.getPlayerScore());
        System.setOut(standardOut);
    }

    @Test
    void integrationTestIncompleteMonasteryScoring() {
        Player test = new Player(1);
        TileController controller = new TileController();
        controller.placeTileBoard(new Tile(TYPE.FIELD, TYPE.FIELD, TYPE.FIELD, TYPE.FIELD, true, false, false, true), new int[]{0, -1});
        controller.placeTileBoard(new Tile(TYPE.FIELD, TYPE.ROAD, TYPE.FIELD, TYPE.ROAD, false, false, false, false), new int[]{-1, 0});
        controller.placeTileBoard(new Tile(TYPE.FIELD, TYPE.ROAD, TYPE.FIELD, TYPE.ROAD, false, false, false, false), new int[]{1, 0});
        controller.placeTileBoard(new Tile(TYPE.FIELD, TYPE.FIELD, TYPE.FIELD, TYPE.FIELD, false, false, false, false), new int[]{1, -1});
        controller.placeTileBoard(new Tile(TYPE.FIELD, TYPE.FIELD, TYPE.FIELD, TYPE.FIELD, false, false, false, false), new int[]{1, -2});
        controller.placeTileBoard(new Tile(TYPE.FIELD, TYPE.FIELD, TYPE.FIELD, TYPE.FIELD, false, false, false, false), new int[]{0, -2});
        controller.placeTileBoard(new Tile(TYPE.FIELD, TYPE.FIELD, TYPE.FIELD, TYPE.FIELD, false, false, false, false), new int[]{-1, -2});
        controller.placeMeeple(new int[]{0, -1}, test.getFirstFreeMeeple(), 2);
        test.finalMeeples();
        assertEquals(8, test.getPlayerScore());
        System.setOut(standardOut);
    }

    @Test
    void integrationTestSimpleCompleteUnconnectedCityConnectedStartScoring() {
        Player test = new Player(1);
        TileController controller = new TileController();
        controller.placeMeeple(new int[]{0, 0}, test.getFirstFreeMeeple(), 0);
        controller.placeTileBoard(new Tile(TYPE.CITY, TYPE.FIELD, TYPE.CITY, TYPE.FIELD, false, false, false, false), new int[]{0, 1});


        test.updateMeeples();
        assertEquals(4, test.getPlayerScore());
        System.setOut(standardOut);
    }
    @Test
    void integrationTestSimpleCompleteConnectedCityConnectedStartScoring() {
        Player test = new Player(1);
        TileController controller = new TileController();
        controller.placeMeeple(new int[]{0, 0}, test.getFirstFreeMeeple(), 0);
        controller.placeTileBoard(new Tile(TYPE.CITY, TYPE.FIELD, TYPE.CITY, TYPE.FIELD, false, true, false, false), new int[]{0, 1});
        controller.placeTileBoard(new Tile(TYPE.CITY, TYPE.FIELD, TYPE.CITY, TYPE.FIELD, false, true, false, false), new int[]{0, 2});
        controller.placeTileBoard(new Tile(TYPE.CITY, TYPE.FIELD, TYPE.CITY, TYPE.FIELD, false, true, false, false), new int[]{0, 3});
        controller.placeTileBoard(new Tile(TYPE.CITY, TYPE.FIELD, TYPE.CITY, TYPE.FIELD, false, false, false, false), new int[]{0, 4});


        test.updateMeeples();
        assertEquals(10, test.getPlayerScore());
        System.setOut(standardOut);
    }

    //STARTING ON CONNECTED TILE
    @Test
    void integrationTestComplexCompleteCityConnectedStartConnectedStartScoring() {
        Player test = new Player(1);
        TileController controller = new TileController();
        //Cannot place -1 first so 0 is used first
        controller.placeTileBoard(new Tile(TYPE.CITY, TYPE.CITY, TYPE.CITY, TYPE.CITY, false, true, false, false), new int[]{0, 1});
        controller.placeTileBoard(new Tile(TYPE.CITY, TYPE.FIELD, TYPE.FIELD, TYPE.CITY, false, true, false, false), new int[]{1, 1});
        controller.placeTileBoard(new Tile(TYPE.CITY, TYPE.CITY, TYPE.FIELD, TYPE.FIELD, false, true, false, false), new int[]{-1, 1});
        controller.placeTileBoard(new Tile(TYPE.CITY, TYPE.CITY, TYPE.CITY, TYPE.FIELD, false, true, false, false), new int[]{-1, 2});
        controller.placeTileBoard(new Tile(TYPE.CITY, TYPE.CITY, TYPE.CITY, TYPE.CITY, false, true, false, false), new int[]{0, 2});
        controller.placeTileBoard(new Tile(TYPE.CITY, TYPE.FIELD, TYPE.CITY, TYPE.CITY, false, true, false, false), new int[]{1, 2});
        controller.placeTileBoard(new Tile(TYPE.FIELD, TYPE.CITY, TYPE.CITY, TYPE.FIELD, false, true, false, false), new int[]{-1, 3});
        controller.placeTileBoard(new Tile(TYPE.FIELD, TYPE.CITY, TYPE.CITY, TYPE.CITY, false, true, false, false), new int[]{0, 3});
        controller.placeMeeple(new int[]{0, 1}, test.getFirstFreeMeeple(), 2);
        controller.placeTileBoard(new Tile(TYPE.FIELD, TYPE.FIELD, TYPE.CITY, TYPE.CITY, false, true, false, false), new int[]{1, 3});
        test.updateMeeples();
        assertEquals(20, test.getPlayerScore());
        System.setOut(standardOut);
    }

    //STARTING ON CONNECTED TILE
    @Test
    void integrationTestComplexIncompleteCityConnectedStartScoring() {
        Player test = new Player(1);
        TileController controller = new TileController();
        //Cannot place -1 first so 0 is used first
        controller.placeTileBoard(new Tile(TYPE.CITY, TYPE.CITY, TYPE.CITY, TYPE.CITY, false, true, false, false), new int[]{0, 1});
        controller.placeTileBoard(new Tile(TYPE.CITY, TYPE.FIELD, TYPE.FIELD, TYPE.CITY, false, true, false, false), new int[]{1, 1});
        controller.placeTileBoard(new Tile(TYPE.CITY, TYPE.CITY, TYPE.FIELD, TYPE.FIELD, false, true, false, false), new int[]{-1, 1});
        controller.placeTileBoard(new Tile(TYPE.CITY, TYPE.CITY, TYPE.CITY, TYPE.FIELD, false, true, false, false), new int[]{-1, 2});
        controller.placeTileBoard(new Tile(TYPE.CITY, TYPE.FIELD, TYPE.CITY, TYPE.CITY, false, true, false, false), new int[]{1, 2});
        controller.placeTileBoard(new Tile(TYPE.FIELD, TYPE.CITY, TYPE.CITY, TYPE.FIELD, false, true, false, false), new int[]{-1, 3});
        controller.placeTileBoard(new Tile(TYPE.FIELD, TYPE.CITY, TYPE.CITY, TYPE.CITY, false, true, false, false), new int[]{0, 3});
        controller.placeTileBoard(new Tile(TYPE.FIELD, TYPE.FIELD, TYPE.CITY, TYPE.CITY, false, true, false, false), new int[]{1, 3});
        controller.placeMeeple(new int[]{0, 1}, test.getFirstFreeMeeple(), 2);
        test.finalMeeples();
        assertEquals(9, test.getPlayerScore());
        System.setOut(standardOut);
    }

    //STARTING ON CONNECTED TILE
    @Test
    void integrationTestComplexCompleteCityConnectedStartConnectedStartScoringShield() {
        Player test = new Player(1);
        TileController controller = new TileController();
        //Cannot place -1 first so 0 is used first
        controller.placeTileBoard(new Tile(TYPE.CITY, TYPE.CITY, TYPE.CITY, TYPE.CITY, false, true, false, false), new int[]{0, 1});
        controller.placeTileBoard(new Tile(TYPE.CITY, TYPE.FIELD, TYPE.FIELD, TYPE.CITY, false, true, false, false), new int[]{1, 1});
        controller.placeTileBoard(new Tile(TYPE.CITY, TYPE.CITY, TYPE.FIELD, TYPE.FIELD, false, true, false, false), new int[]{-1, 1});
        controller.placeTileBoard(new Tile(TYPE.CITY, TYPE.CITY, TYPE.CITY, TYPE.FIELD, false, true, true, false), new int[]{-1, 2});
        controller.placeTileBoard(new Tile(TYPE.CITY, TYPE.CITY, TYPE.CITY, TYPE.CITY, false, true, false, false), new int[]{0, 2});
        controller.placeTileBoard(new Tile(TYPE.CITY, TYPE.FIELD, TYPE.CITY, TYPE.CITY, false, true, false, false), new int[]{1, 2});
        controller.placeTileBoard(new Tile(TYPE.FIELD, TYPE.CITY, TYPE.CITY, TYPE.FIELD, false, true, true, false), new int[]{-1, 3});
        controller.placeTileBoard(new Tile(TYPE.FIELD, TYPE.CITY, TYPE.CITY, TYPE.CITY, false, true, false, false), new int[]{0, 3});
        controller.placeMeeple(new int[]{0, 1}, test.getFirstFreeMeeple(), 2);
        controller.placeTileBoard(new Tile(TYPE.FIELD, TYPE.FIELD, TYPE.CITY, TYPE.CITY, false, true, false, false), new int[]{1, 3});
        test.updateMeeples();
        assertEquals(24, test.getPlayerScore());
        System.setOut(standardOut);
    }

    //STARTING ON CONNECTED TILE
    @Test
    void integrationTestComplexIncompleteCityConnectedStartScoringShield() {
        Player test = new Player(1);
        TileController controller = new TileController();
        //Cannot place -1 first so 0 is used first
        controller.placeTileBoard(new Tile(TYPE.CITY, TYPE.CITY, TYPE.CITY, TYPE.CITY, false, true, true, false), new int[]{0, 1});
        controller.placeTileBoard(new Tile(TYPE.CITY, TYPE.FIELD, TYPE.FIELD, TYPE.CITY, false, true, false, false), new int[]{1, 1});
        controller.placeTileBoard(new Tile(TYPE.CITY, TYPE.CITY, TYPE.FIELD, TYPE.FIELD, false, true, false, false), new int[]{-1, 1});
        controller.placeTileBoard(new Tile(TYPE.CITY, TYPE.CITY, TYPE.CITY, TYPE.FIELD, false, true, false, false), new int[]{-1, 2});
        controller.placeTileBoard(new Tile(TYPE.CITY, TYPE.FIELD, TYPE.CITY, TYPE.CITY, false, true, false, false), new int[]{1, 2});
        controller.placeTileBoard(new Tile(TYPE.FIELD, TYPE.CITY, TYPE.CITY, TYPE.FIELD, false, true, true, false), new int[]{-1, 3});
        controller.placeTileBoard(new Tile(TYPE.FIELD, TYPE.CITY, TYPE.CITY, TYPE.CITY, false, true, false, false), new int[]{0, 3});
        controller.placeTileBoard(new Tile(TYPE.FIELD, TYPE.FIELD, TYPE.CITY, TYPE.CITY, false, true, false, false), new int[]{1, 3});
        controller.placeMeeple(new int[]{0, 1}, test.getFirstFreeMeeple(), 2);
        test.finalMeeples();
        assertEquals(11, test.getPlayerScore());
        System.setOut(standardOut);
    }

    @Test
    void integrationTestMultipleMonasteryEndGameScoring() {
        Player test = new Player(1);
        TileController controller = new TileController();
        assertEquals(0, test.getPlayerScore());

        //Incomplete Monastery
        controller.placeTileBoard(new Tile(TYPE.FIELD, TYPE.FIELD, TYPE.FIELD, TYPE.FIELD, true, false, false, true), new int[]{0, -1});
        controller.placeTileBoard(new Tile(TYPE.FIELD, TYPE.ROAD, TYPE.FIELD, TYPE.ROAD, false, false, false, false), new int[]{-1, 0});
        controller.placeTileBoard(new Tile(TYPE.FIELD, TYPE.ROAD, TYPE.FIELD, TYPE.ROAD, false, false, false, false), new int[]{1, 0});
        controller.placeTileBoard(new Tile(TYPE.FIELD, TYPE.FIELD, TYPE.FIELD, TYPE.FIELD, false, false, false, false), new int[]{1, -1});
        controller.placeTileBoard(new Tile(TYPE.FIELD, TYPE.FIELD, TYPE.FIELD, TYPE.FIELD, false, false, false, false), new int[]{1, -2});
        controller.placeTileBoard(new Tile(TYPE.FIELD, TYPE.FIELD, TYPE.FIELD, TYPE.FIELD, false, false, false, false), new int[]{0, -2});
        controller.placeTileBoard(new Tile(TYPE.FIELD, TYPE.FIELD, TYPE.FIELD, TYPE.FIELD, false, false, false, false), new int[]{-1, -2});
        controller.placeMeeple(new int[]{0, -1}, test.getFirstFreeMeeple(), 2);

        //Complete Monastery
        controller.placeTileBoard(new Tile(TYPE.FIELD, TYPE.FIELD, TYPE.FIELD, TYPE.FIELD, false, false, false, false), new int[]{2, -2});
        controller.placeTileBoard(new Tile(TYPE.FIELD, TYPE.FIELD, TYPE.FIELD, TYPE.FIELD, false, false, false, false), new int[]{3, -2});
        controller.placeTileBoard(new Tile(TYPE.FIELD, TYPE.FIELD, TYPE.FIELD, TYPE.FIELD, true, false, false, true), new int[]{3, -1});
        controller.placeTileBoard(new Tile(TYPE.FIELD, TYPE.ROAD, TYPE.FIELD, TYPE.ROAD, false, false, false, false), new int[]{2, 0});
        controller.placeTileBoard(new Tile(TYPE.FIELD, TYPE.FIELD, TYPE.FIELD, TYPE.FIELD, false, false, false, false), new int[]{4, -1});
        controller.placeTileBoard(new Tile(TYPE.FIELD, TYPE.FIELD, TYPE.FIELD, TYPE.FIELD, false, false, false, false), new int[]{4, -2});
        controller.placeTileBoard(new Tile(TYPE.FIELD, TYPE.ROAD, TYPE.FIELD, TYPE.ROAD, false, false, false, false), new int[]{4, 0});
        controller.placeTileBoard(new Tile(TYPE.FIELD, TYPE.ROAD, TYPE.FIELD, TYPE.ROAD, false, false, false, false), new int[]{3, 0});
        controller.placeMeeple(new int[]{3, -1}, test.getFirstFreeMeeple(), 2);
        controller.placeTileBoard(new Tile(TYPE.FIELD, TYPE.FIELD, TYPE.FIELD, TYPE.FIELD, false, false, false, false), new int[]{2, -1});
        test.finalMeeples();
        assertEquals(17, test.getPlayerScore());
        System.setOut(standardOut);

    }

    //WRONG LOGIC FOR GAME, RIGHT LOGIC FOR CODE
    @Test
    void integrationTestContestedCompleteCity() {
        Player test = new Player(1);
        Player test2 = new Player(2);
        TileController controller = new TileController();
        //Cannot place -1 first so 0 is used first
        assertEquals(0, test.getPlayerScore());
        assertEquals(0, test2.getPlayerScore());
        controller.placeTileBoard(new Tile(TYPE.CITY, TYPE.CITY, TYPE.CITY, TYPE.FIELD, false, true, false, false), new int[]{0, 1});
        controller.placeMeeple(new int[]{0, 1}, test.getFirstFreeMeeple(), 2);
        controller.placeTileBoard(new Tile(TYPE.FIELD, TYPE.FIELD, TYPE.FIELD, TYPE.CITY, false, false, false, false), new int[]{1, 1});
        controller.placeTileBoard(new Tile(TYPE.FIELD, TYPE.FIELD, TYPE.ROAD, TYPE.ROAD, false, false, false, false), new int[]{-1, 1});
        controller.placeTileBoard(new Tile(TYPE.CITY, TYPE.CITY, TYPE.FIELD, TYPE.FIELD, false, false, false, false), new int[]{-1, 2});
        controller.placeMeeple(new int[]{-1, 2}, test2.getFirstFreeMeeple(), 1);
        controller.placeTileBoard(new Tile(TYPE.FIELD, TYPE.FIELD, TYPE.FIELD, TYPE.CITY, false, false, false, false), new int[]{1, 2});
        controller.placeMeeple(new int[]{1, 2}, test2.getFirstFreeMeeple(), 3);
        controller.placeTileBoard(new Tile(TYPE.FIELD, TYPE.CITY, TYPE.CITY, TYPE.CITY, false, true, false, false), new int[]{0, 2});
//        assertEquals(6, test.numberOfFreeMeeples());
//        assertEquals(5, test2.numberOfFreeMeeples());
        test.updateMeeples();
        test2.updateMeeples();
//        assertEquals(7, test.numberOfFreeMeeples());
//        assertEquals(7, test2.numberOfFreeMeeples());

        assertEquals(12, test.getPlayerScore());
        assertEquals(24, test2.getPlayerScore());
        System.setOut(standardOut);
    }
}