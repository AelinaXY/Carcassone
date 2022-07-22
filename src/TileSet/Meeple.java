package TileSet;

import java.util.ArrayList;

public class Meeple {
    private Player owner;
    private Tile placedTile;
    private ArrayList<Tile> cityTileList = new ArrayList<>();
    private boolean completedFeature = false;

    public Meeple(Player owner) {
        this.owner = owner;
    }

    //Checks to see if a Meeple has a tile
    public boolean hasTile(){return placedTile != null;}

    //Returns the opposite tile
    public int oppositeDirection(int i)
    {
        return (i+2)%4;
    }

    //Adds a Meeple to a tile
    public boolean addMeepleToTile(Tile tile, int direction)
    {
        cityTileList.removeAll(cityTileList);
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

    //Total city check
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

    //Checks a Roads end points
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

    //Checks a cities end points
    private int checkEndCityPoints(Tile tile, int total)
    {

        //if(cityTileList == null)
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

    //Returns if a Meeple has a completed feature
    public boolean isCompleted(){return this.completedFeature;}

    //Frees a meeple from it's tile
    public void freeMeeple()
    {
        this.placedTile.freeMeeple(this);
        this.placedTile = null;
        this.completedFeature = false;
        this.cityTileList.removeAll(cityTileList);
    }

    //Updates a Meeples state
    public void updateMeeple()
    {
        if(this.checkMeepleCompleteness(placedTile,placedTile.getMeepleDirection(this)))
        {
            completedFeature = true;
            System.out.println("COMPLETED CITY\r\nFREE MEEPLE");
        }
    }

    //Returns whether a Meeple is complete or not
    private boolean checkMeepleCompleteness(Tile tile, int direction)
    {
        cityTileList.removeAll(cityTileList);
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

                    if(total == 0)
                    {
                        System.out.println("CITY COMPLETE SWITCH");
                        return true;
                    }
                    else
                    {

                        System.out.println("CITY INCOMPLETE SWITCH");
                        return false;
                    }
                }

                else
                {
                    total = checkEndCityPoints(tile.getConnect(direction), 0);

                    if(total == 0)
                    {
                        System.out.println("CITY COMPLETE SWITCH");
                        return true;
                    }
                    else
                    {
                        System.out.println("CITY INCOMPLETE SWITCH");
                        return false;
                    }
                }

            case ROAD:
                if(tile.isRoadEnd())
                {
                    if (checkEndRoadPoints(tile.getConnect(direction), oppositeDirection(direction), 0) == 1)
                    {
                        System.out.println("ROAD COMPLETE SWTICH");
                        return true;
                    }
                    else
                    {
                        System.out.println("ROAD INCOMPLETE SWITCH");
                        return false;
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
                        return true;
                    } else {
                        System.out.println("ROAD INCOMPLETE SWITCH");
                        return false;
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
                            return true;
                        }
                    }
                    System.out.println("MONASTERY INCOMPLETE SWITCH");
                    return false;
                }

                return false;
        }
        return false;

    }

    //Only called if complete
    public int scoreMeeple()
    {
        int direction = -1;
        Tile tile = null;
        cityTileList.removeAll(cityTileList);
        int total = 0;
        switch(this.placedTile.getSide(this.placedTile.getMeepleDirection(this))){
            case CITY:
                if(this.placedTile.hasConnectedCity())
                {
                    boolean visitFlag = false;
                    if (this.placedTile.getWestSide() == TYPE.CITY && !visitFlag) {
                        total += checkEndCityPointsScore(this.placedTile.getWestConnect(), 0);
                        visitFlag = true;
                    }

                    if (this.placedTile.getEastSide() == TYPE.CITY && !visitFlag) {
                        total += checkEndCityPointsScore(this.placedTile.getEastConnect(), 0);
                        visitFlag = true;
                    }

                    if (this.placedTile.getSouthSide() == TYPE.CITY && !visitFlag) {
                        total += checkEndCityPointsScore(this.placedTile.getSouthConnect(), 0);
                        visitFlag = true;

                    }

                    if (this.placedTile.getNorthSide() == TYPE.CITY && !visitFlag) {
                        total += checkEndCityPointsScore(this.placedTile.getNorthConnect(), 0);
                        visitFlag = true;

                    }

                }

                else
                {
                    if(!this.placedTile.hasConnectedCity() && !this.placedTile.getConnect(this.placedTile.getMeepleDirection(this)).hasConnectedCity())
                    {
                        total = 4;
                    }
                    else
                    {
                        total = checkEndCityPointsScore(this.placedTile.getConnect(this.placedTile.getMeepleDirection(this)), 0);

                    }

                }
                if(this.completedFeature)
                {
                    return total;
                }
                else{return total/2;}

            case ROAD:
                if(this.placedTile.isRoadEnd())
                {
                    total = checkEndRoadPointsScore(this.placedTile.getConnect(this.placedTile.getMeepleDirection(this)), oppositeDirection(this.placedTile.getMeepleDirection(this)), 0);
                }

                else {

                    if (this.placedTile.getWestSide() == TYPE.ROAD) {
                        total += checkEndRoadPointsScore(this.placedTile.getWestConnect(), 1, 0);
                    }

                    if (this.placedTile.getEastSide() == TYPE.ROAD) {
                        total += checkEndRoadPointsScore(this.placedTile.getEastConnect(), 3, 0);
                    }

                    if (this.placedTile.getSouthSide() == TYPE.ROAD) {
                        total += checkEndRoadPointsScore(this.placedTile.getSouthConnect(), 0, 0);
                    }

                    if (this.placedTile.getNorthSide() == TYPE.ROAD) {
                        total += checkEndRoadPointsScore(this.placedTile.getNorthConnect(), 2, 0);
                    }

                }
                return total;

            case FIELD:
                if (this.placedTile.hasMonastery())
                {
                    boolean northEast = false;
                    boolean northWest = false;
                    boolean southEast = false;
                    boolean southWest = false;

                    if(this.placedTile.hasNorthConnect())
                    {
                        total++;
                        if(this.placedTile.getNorthConnect().hasEastConnect() && !northEast)
                        {
                            total++;
                            northEast=true;
                        }
                        if(this.placedTile.getNorthConnect().hasWestConnect() && !northWest)
                        {
                            total++;
                            northWest=true;
                        }
                    }
                    if(this.placedTile.hasEastConnect())
                    {
                        total++;
                        if(this.placedTile.getEastConnect().hasNorthConnect() && !northEast)
                        {
                            total++;
                            northEast=true;
                        }
                        if(this.placedTile.getEastConnect().hasSouthConnect() && !southEast)
                        {
                            total++;
                            southEast=true;
                        }
                    }
                    if(this.placedTile.hasSouthConnect())
                    {
                        total++;
                        if (this.placedTile.getSouthConnect().hasEastConnect() && !southEast) {
                            total++;
                            southEast = true;
                        }
                        if (this.placedTile.getSouthConnect().hasWestConnect() && !southWest) {
                            total++;
                            southWest = true;
                        }
                    }
                    if(this.placedTile.hasWestConnect())
                    {
                        total++;
                        if(this.placedTile.getWestConnect().hasNorthConnect() && !northWest)
                        {
                            total++;
                            northWest=true;
                        }
                        if(this.placedTile.getWestConnect().hasSouthConnect() && !southWest)
                        {
                            total++;
                            southWest=true;
                        }
                    }
                    total++;
                    return total;
                }

        }
        return total;
    }

    //Goes through road and returns score
    private int checkEndRoadPointsScore(Tile tile, int sideCameFrom, int total)
    {
        if (tile == null) return total;

        if (tile.isRoadEnd()) return ++total;

        for(int i = 0; i<4; i++)
        {
            if(tile.getSide(i) == TYPE.ROAD && sideCameFrom != i)
            {
                total = checkEndRoadPointsScore(tile.getConnect(i), oppositeDirection(i), total);
            }
        }

        return ++total;

    }

    //Goes through city and returns score
    private int checkEndCityPointsScore(Tile tile, int total)
    {

        if(!cityTileList.contains(tile)) cityTileList.add(tile);

        if (tile == null) return total;

        //System.out.println("I AM ON TILE: " + tile.toString());
        if (!tile.hasConnectedCity()) return 2+total;

        for(int i = 0; i<4; i++)
        {
            if(tile.getSide(i) == TYPE.CITY && !cityTileList.contains(tile.getConnect(i)))
            {

                total = checkEndCityPointsScore(tile.getConnect(i), total);
            }
        }
        if(tile.hasShield()){total+=2;}

        return 2+total;

    }


}
