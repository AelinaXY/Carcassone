package TileSet;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Locale;

public class TileController {

    private ArrayList<Tile> tileDeck = new ArrayList<>();
    private ArrayList<Tile> tileBoard = new ArrayList<>();
    private ArrayList<int[]> validPlacements = new ArrayList<>();

    public TileController(){
        parseInputCSV();
        tileBoard.add(tileDeck.get(33));
        tileDeck.remove(33);
        //Gets the origin and then all the coordinates around the origin
        validPlacements.addAll(coordsAroundTile(tileDeck.get(0).getCoords()));
        Collections.shuffle(tileDeck);

    }


    private void parseInputCSV(){
        try(BufferedReader br = new BufferedReader(new FileReader("Tileset.csv"))){
            System.out.println("Found file");
            String line;
            while ((line = br.readLine()) != null){
                String[] values = line.split(",");
                tileDeck.add(new Tile(TYPE.valueOf(values[0].toUpperCase(Locale.ROOT)),
                        TYPE.valueOf(values[1].toUpperCase(Locale.ROOT)),
                        TYPE.valueOf(values[2].toUpperCase(Locale.ROOT)),
                        TYPE.valueOf(values[3].toUpperCase(Locale.ROOT)),
                        Boolean.parseBoolean(values[4]),
                        Boolean.parseBoolean(values[5]),
                        Boolean.parseBoolean(values[6]),
                        Boolean.parseBoolean(values[7])));
            }
        }
        catch (Exception e){
            System.out.println("File not found");
        }
    }

    public boolean placeTileBoard(Tile placedTile, int[] coords)
    {
        if(validPlacements.stream().noneMatch((a -> Arrays.equals(a, coords))))
        {
            System.out.println("INVALID INPUT COORDINATES");
        }
        else
        {
            boolean tileFlag = true;
            Tile[] placementTiles = new Tile[]{null,null,null,null};
            for(Tile i: tileBoard)
            {
                //Checks if the tile to the east can connect to the tile being placed
                if(Arrays.equals(i.getCoords(),new int[]{coords[0] + 1, coords[1]}))
                {
                    if(!i.getWestSide().equals(placedTile.getEastSide()))
                    {
                        tileFlag = false;
                        System.out.println("East side of placed Tile does not connect to West side of tile on the ground");
                        continue;
                    }
                    else
                    {
                        placementTiles[0] = i;
                    }
                }
                //Checks if the tile to the west can connect to the tile being placed

                if(Arrays.equals(i.getCoords(),new int[]{coords[0] - 1, coords[1]}))
                {
                    if(!i.getEastSide().equals(placedTile.getWestSide()))
                    {
                        tileFlag = false;
                        System.out.println("West side of placed Tile does not connect to East side of tile on the ground");
                        continue;
                    }
                    else
                    {
                        placementTiles[1] = i;
                    }
                }
                //Checks if the tile to the north can connect to the tile being placed
                if(Arrays.equals(i.getCoords(),new int[]{coords[0], coords[1] + 1}))
                {
                    if(!i.getSouthSide().equals(placedTile.getNorthSide()))
                    {
                        tileFlag = false;
                        System.out.println("North side of placed Tile does not connect to South side of tile on the ground");
                        continue;
                    }
                    else
                    {
                        placementTiles[2] = i;
                    }
                }
                //Checks if the tile to the south can connect to the tile being placed

                if(Arrays.equals(i.getCoords(),new int[]{coords[0], coords[1] - 1}))
                {
                    if(!i.getNorthSide().equals(placedTile.getSouthSide()))
                    {
                        tileFlag = false;
                        System.out.println("South side of placed Tile does not connect to North side of tile on the ground");
                    }
                    else
                    {
                        placementTiles[3] = i;
                    }
                }
            }

            if(tileFlag)
            {
                //East, West, North, South
                if(placementTiles[0] != null)
                {
                    placedTile.addEastConnection(placementTiles[0]);
                }
                if(placementTiles[1] != null)
                {
                    placedTile.addWestConnection(placementTiles[1]);
                }
                if(placementTiles[2] != null)
                {
                    placedTile.addNorthConnection(placementTiles[2]);
                }
                if(placementTiles[3] != null)
                {
                    placedTile.addSouthConnection(placementTiles[3]);
                }
                tileBoard.add(placedTile);
                placedTile.setCoordsX(coords[0]);
                placedTile.setCoordsY(coords[1]);
                validPlacements.addAll(coordsAroundTile(coords));
                for(Tile i: tileBoard)
                {
                    validPlacements.removeIf(a -> Arrays.equals(a,i.getCoords()));
                }
                System.out.println("SUCCESSFUL PLACEMENT");
                return true;
            }

        }
        return false;
    }

    public Tile drawTile()
    {
        return tileDeck.size() == 0 ? null : tileDeck.remove(0);
    }

    public void returnTile(Tile i)
    {
        tileDeck.add(i);
    }

    public int getTileDeckSize()
    {
        return tileDeck.size();
    }

    public ArrayList<int[]> getValidPlacements()
    {
        return validPlacements;
    }
    private ArrayList<int []> coordsAroundTile(int [] coords)
    {
        ArrayList<int []> returnArrayList = new ArrayList<>();
        returnArrayList.add(new int[]{coords[0] + 1, coords[1]});
        returnArrayList.add(new int[]{coords[0] - 1, coords[1]});
        returnArrayList.add(new int[]{coords[0], coords[1] + 1});
        returnArrayList.add(new int[]{coords[0], coords[1] - 1});
        return returnArrayList;
    }



    public ArrayList<Tile> getTileDeck() {
        return tileDeck;
    }

    public void placeMeeple(int[] coords, Meeple meeple, int side)
    {
        if(tileBoard.stream().noneMatch((a -> Arrays.equals(a.getCoords(), coords))))
        {
            System.out.println("INVALID INPUT COORDINATES");
        }
        else
        {
            Tile tile = tileBoard.stream().filter(a -> Arrays.equals(a.getCoords(), coords)).findFirst().get();
            meeple.addMeepleToTile(tile, side);
            System.out.println("MEEPLE ADDED");
        }
    }
}
