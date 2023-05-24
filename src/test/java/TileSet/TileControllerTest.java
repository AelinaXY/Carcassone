package TileSet;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.isA;
import static org.mockito.Mockito.doNothing;

@RunWith(MockitoJUnitRunner.class)
public class TileControllerTest {

    TileController controller;
    private final PrintStream standardOut = System.out;
    private final ByteArrayOutputStream outputStreamCaptor = new ByteArrayOutputStream();

    @Mock
    Meeple mockeeple;

    @Before
    public void setUp()
    {
        controller = new TileController();
    }

    @After
    public void tearDown() {
        controller = null;
    }

    @Test
    public void placeTileBoard() {
    }

    @Test
    public void drawTileReducesDeckSize() {
        int tileDeckSize = controller.getTileDeckSize();
        Tile drawn = controller.drawTile();
        assertEquals(tileDeckSize-1, controller.getTileDeckSize());
    }

    @Test
    public void drawTileFromEmptyDeckReturnsNull() {
        int tileDeckSize = controller.getTileDeckSize();
        while (controller.getTileDeckSize() > 0)
        {
            Tile drawn = controller.drawTile();
        }
        assertNull(controller.drawTile());
    }

    @Test
    public void returnTile() {
        int tileDeckSize = controller.getTileDeckSize();
        Tile drawn = controller.drawTile();
        controller.returnTile(drawn);
        assertEquals(tileDeckSize, controller.getTileDeckSize());
    }

    @Test
    public void ValidPlacementsSizeAroundInitialTileIsCorrect() {
        int validPlacementsSize = controller.getValidPlacements().size();
        assertEquals(4, validPlacementsSize);
    }

    @Test
    public void ValidPlacementsSizeAfterAddingATile() {
        controller.placeTileBoard(new Tile(TYPE.FIELD,TYPE.ROAD,TYPE.FIELD,TYPE.ROAD,false,false,false,false), new int[]{1, 0});
        int validPlacementsSize = controller.getValidPlacements().size();
        assertEquals(6, validPlacementsSize);
    }

    @Test
    public void placeMeepleWithoutValidCoordinates() {
        System.setOut(new PrintStream(outputStreamCaptor));
        controller.placeMeeple(new int[]{-1,-1}, null, 0);
        assertEquals("INVALID INPUT COORDINATES", outputStreamCaptor.toString().trim());
        System.setOut(standardOut);
    }
}