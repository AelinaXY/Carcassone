package TileSet;

import java.util.ArrayList;

public class Meeple {
    private Player owner;
    private Tile placedTile;
    private ArrayList<Tile> cityTileList = new ArrayList<>();

    public Meeple(Player owner) {
        this.owner = owner;
    }

    public boolean hasTile(){return placedTile != null;}

    public int oppositeDirection(int i)
    {
        return (i+2)%4;
    }

    public boolean addMeepleToTile(Tile tile, int direction)
    {
        int total = 0;
        switch(tile.getSide(direction)){
            case CITY:
                if(tile.hasConnectedCity())
                {
                    if (tile.getWestSide() == TYPE.CITY) {
                        total += checkEndCityPoints(tile.getWestConnect(), total);
                    }

                    if (tile.getEastSide() == TYPE.CITY) {
                        total += checkEndCityPoints(tile.getEastConnect(), total);
                    }

                    if (tile.getSouthSide() == TYPE.CITY) {
                        total += checkEndCityPoints(tile.getSouthConnect(), total);
                    }

                    if (tile.getNorthSide() == TYPE.CITY) {
                        total += checkEndCityPoints(tile.getNorthConnect(), total);
                    }

                    if (cityTotalCheck(total, tile, direction)) return true;
                }

                else
                {
                    total = checkEndCityPoints(tile.getConnect(direction), 0);

                    if (cityTotalCheck(total, tile, direction)) return true;
                }
                break;

            case ROAD:
                if(tile.isRoadEnd())
                {
                    if (checkEndRoadPoints(tile.getConnect(direction), oppositeDirection(direction), 0) == 1)
                    {
                        System.out.println("ROAD COMPLETE SWTICH");
                        return false;
                    }
                    else
                    {
                        this.placedTile = tile;
                        tile.setMeeple(this, direction);
                        System.out.println("ROAD INCOMPLETE SWITCH");
                        return true;
                    }
                }

                else {

                    if (tile.getWestSide() == TYPE.ROAD) {
                        total += checkEndRoadPoints(tile.getWestConnect(), 1, 0);
                    }

                    if (tile.getEastSide() == TYPE.ROAD) {
                        total += checkEndRoadPoints(tile.getEastConnect(), 3, 0);
                    }

                    if (tile.getSouthSide() == TYPE.ROAD) {
                        total += checkEndRoadPoints(tile.getSouthConnect(), 0, 0);
                    }

                    if (tile.getNorthSide() == TYPE.ROAD) {
                        total += checkEndRoadPoints(tile.getNorthConnect(), 2, 0);
                    }

                    if (total == 2) {
                        System.out.println("ROAD COMPLETE SWITCH");
                        return false;
                    } else {
                        this.placedTile = tile;
                        tile.setMeeple(this, direction);
                        System.out.println("ROAD INCOMPLETE SWITCH");
                        return true;
                    }
                }

            case FIELD:
                if (tile.hasMonastery())
                {
                    if(tile.hasNorthConnect() &&
                            tile.hasEastConnect() &&
                            tile.hasSouthConnect() &&
                            tile.hasWestConnect())
                    {
                        if (tile.getNorthConnect().hasEastConnect() &&
                                tile.getNorthConnect().hasWestConnect() &&
                                tile.getSouthConnect().hasEastConnect() &&
                                tile.getSouthConnect().hasWestConnect())
                        {
                            System.out.println("MONASTERY COMPLETE SWITCH");
                            return false;
                        }
                    }
                    this.placedTile = tile;
                    tile.setMeeple(this, direction);
                    System.out.println("MONASTERY INCOMPLETE SWITCH");
                    return true;
                }

                return false;
        }
        return false;

    }

    private boolean cityTotalCheck(int total, Tile tile, int direction) {

        if(total == 0)
        {
            System.out.println("CITY COMPLETE SWITCH");
        }
        else
        {
            this.placedTile = tile;
            tile.setMeeple(this, direction);
            System.out.println("CITY INCOMPLETE SWITCH");
            return true;
        }
        return false;
    }

    private int checkEndRoadPoints(Tile tile, int sideCameFrom, int total)
    {
        if (tile == null) return total;

        if (tile.isRoadEnd()) return ++total;

        for(int i = 0; i<4; i++)
        {
            if(tile.getSide(i) == TYPE.ROAD && sideCameFrom != i)
            {
                total = checkEndRoadPoints(tile.getConnect(i), oppositeDirection(i), total);
            }
        }

        return total;

    }

    private int checkEndCityPoints(Tile tile, int total)
    {
        if(!cityTileList.contains(tile)) cityTileList.add(tile);

        if (tile == null) return ++total;

        if (!tile.hasConnectedCity()) return total;

        for(int i = 0; i<4; i++)
        {
            if(tile.getSide(i) == TYPE.CITY && !cityTileList.contains(tile.getConnect(i)))
            {

                total = checkEndCityPoints(tile.getConnect(i), total);
            }
        }

        return total;

    }


}
