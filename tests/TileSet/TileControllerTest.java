package TileSet;

import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.ArgumentMatchers.isA;
import static org.mockito.Mockito.doNothing;

@ExtendWith(MockitoExtension.class)
class TileControllerTest {

    TileController controller;
    private final PrintStream standardOut = System.out;
    private final ByteArrayOutputStream outputStreamCaptor = new ByteArrayOutputStream();

    @Mock
    Meeple mockeeple;

    @org.junit.jupiter.api.BeforeEach
    void setUp()
    {
        controller = new TileController();
    }

    @org.junit.jupiter.api.AfterEach
    void tearDown() {
        controller = null;
    }

    @org.junit.jupiter.api.Test
    void placeTileBoard() {
    }

    @org.junit.jupiter.api.Test
    void drawTileReducesDeckSize() {
        int tileDeckSize = controller.getTileDeckSize();
        Tile drawn = controller.drawTile();
        assertEquals(tileDeckSize-1, controller.getTileDeckSize());
    }

    @org.junit.jupiter.api.Test
    void drawTileFromEmptyDeckReturnsNull() {
        int tileDeckSize = controller.getTileDeckSize();
        while (controller.getTileDeckSize() > 0)
        {
            Tile drawn = controller.drawTile();
        }
        assertNull(controller.drawTile());
    }

    @org.junit.jupiter.api.Test
    void returnTile() {
        int tileDeckSize = controller.getTileDeckSize();
        Tile drawn = controller.drawTile();
        controller.returnTile(drawn);
        assertEquals(tileDeckSize, controller.getTileDeckSize());
    }

    @org.junit.jupiter.api.Test
    void ValidPlacementsSizeAroundInitialTileIsCorrect() {
        int validPlacementsSize = controller.getValidPlacements().size();
        assertEquals(4, validPlacementsSize);
    }

    @org.junit.jupiter.api.Test
    void ValidPlacementsSizeAfterAddingATile() {
        controller.placeTileBoard(new Tile(TYPE.FIELD,TYPE.ROAD,TYPE.FIELD,TYPE.ROAD,false,false,false,false), new int[]{1, 0});
        int validPlacementsSize = controller.getValidPlacements().size();
        assertEquals(6, validPlacementsSize);
    }

    @org.junit.jupiter.api.Test
    void placeMeepleWithoutValidCoordinates() {
        System.setOut(new PrintStream(outputStreamCaptor));
        controller.placeMeeple(new int[]{-1,-1}, null, 0);
        assertEquals("INVALID INPUT COORDINATES", outputStreamCaptor.toString().trim());
        System.setOut(standardOut);
    }

    @org.junit.jupiter.api.Test
    void placeMeepleInValidPlace() {
        doNothing().when(mockeeple).addMeepleToTile(isA(Tile.class), isA(Integer.class));
        controller.placeTileBoard(new Tile(TYPE.FIELD,TYPE.ROAD,TYPE.FIELD,TYPE.ROAD,false,false,false,false), new int[]{1, 0});
        controller.placeTileBoard(new Tile(TYPE.FIELD,TYPE.ROAD,TYPE.ROAD,TYPE.FIELD,false,false,false,false), new int[]{-1, 0});
        controller.placeTileBoard(new Tile(TYPE.ROAD,TYPE.ROAD,TYPE.FIELD,TYPE.FIELD,false,false,false,false), new int[]{-1, -1});
        controller.placeTileBoard(new Tile(TYPE.FIELD,TYPE.ROAD,TYPE.FIELD,TYPE.ROAD,false,false,false,false), new int[]{0, -1});
        controller.placeTileBoard(new Tile(TYPE.FIELD,TYPE.ROAD,TYPE.FIELD,TYPE.ROAD,false,false,false,true), new int[]{1, -1});

        System.setOut(new PrintStream(outputStreamCaptor));
        controller.placeMeeple(new int[]{-1,0},mockeeple,2);
        assertEquals("MEEPLE ADDED", outputStreamCaptor.toString().trim());
        System.setOut(standardOut);
    }
}