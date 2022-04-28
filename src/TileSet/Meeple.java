package TileSet;

public class Meeple {
    Player owner;
    Tile placedTile;

    public Meeple(Player owner) {
        this.owner = owner;
    }

    public boolean hasTile(){return placedTile != null;}

    public void addMeepleToTile(Tile tile, int direction)
    {
        //North Side
        if (direction == 0){
            TYPE tileType = tile.getNorthSide();
            if (tileType == TYPE.CITY)
            {

            }
            if (tileType == TYPE.ROAD)
            {
                if (tile.isRoadEnd())
                {
                    if (checkEndRoadPoints(tile.getNorthConnect(), 2, 0) == 1)
                    {
                        System.out.println("Complete Road");
                    }

                    else
                    {
                        this.placedTile = tile;
                        tile.setMeeple(this);
                        System.out.println("ROAD NOT COMPLETE");
                    }
                }
                else {
                    int total = 0;
                    if (tile.getWestSide() == TYPE.ROAD) {
                        total += checkEndRoadPoints(tile.getWestConnect(), 1, 0);
                    } else if (tile.getEastSide() == TYPE.ROAD) {
                        total += checkEndRoadPoints(tile.getEastConnect(), 3, 0);
                    } else if (tile.getSouthSide() == TYPE.ROAD) {
                        total += checkEndRoadPoints(tile.getSouthConnect(), 0, 0);
                    }

                    total += checkEndRoadPoints(tile.getNorthConnect(), 2, 0);

                    if (total == 2) {
                        System.out.println("ROAD COMPLETE");
                    } else {
                        this.placedTile = tile;
                        tile.setMeeple(this);
                        System.out.println("ROAD INCOMPLETE");
                    }
                }
            }
            if (tileType == TYPE.FIELD && tile.hasMonastry())
            {

            }

        }

        //East Side
        if (direction == 1){
            TYPE tileType = tile.getEastSide();
            if (tileType == TYPE.CITY)
            {

            }
            if (tileType == TYPE.ROAD)
            {
                if (tile.isRoadEnd())
                {
                    if (checkEndRoadPoints(tile.getEastConnect(), 3, 0) == 1)
                    {
                        System.out.println("Complete Road");
                    }

                    else
                    {
                        this.placedTile = tile;
                        tile.setMeeple(this);
                        System.out.println("ROAD NOT COMPLETE");
                    }
                }
                else {
                    int total = 0;
                    if (tile.getNorthSide() == TYPE.ROAD) {
                        total += checkEndRoadPoints(tile.getNorthConnect(), 2, 0);
                    } else if (tile.getWestSide() == TYPE.ROAD) {
                        total += checkEndRoadPoints(tile.getWestConnect(), 1, 0);
                    } else if (tile.getSouthSide() == TYPE.ROAD) {
                        total += checkEndRoadPoints(tile.getSouthConnect(), 0, 0);
                    }

                    total += checkEndRoadPoints(tile.getEastConnect(), 3, 0);

                    if (total == 2) {
                        System.out.println("ROAD COMPLETE");
                    } else {
                        this.placedTile = tile;
                        tile.setMeeple(this);
                        System.out.println("ROAD INCOMPLETE");
                    }
                }
            }
            if (tileType == TYPE.FIELD && tile.hasMonastry())
            {

            }

        }

        //South Side
        if (direction == 2){
            TYPE tileType = tile.getSouthSide();
            if (tileType == TYPE.CITY)
            {

            }
            if (tileType == TYPE.ROAD)
            {
                if (tile.isRoadEnd())
                {
                    if (checkEndRoadPoints(tile.getSouthConnect(), 0, 0) == 1)
                    {
                        System.out.println("Complete Road");
                    }

                    else
                    {
                        this.placedTile = tile;
                        tile.setMeeple(this);
                        System.out.println("ROAD NOT COMPLETE");
                    }
                }
                else {
                    int total = 0;
                    if (tile.getNorthSide() == TYPE.ROAD) {
                        total += checkEndRoadPoints(tile.getNorthConnect(), 2, 0);
                    } else if (tile.getEastSide() == TYPE.ROAD) {
                        total += checkEndRoadPoints(tile.getEastConnect(), 3, 0);
                    } else if (tile.getWestSide() == TYPE.ROAD) {
                        total += checkEndRoadPoints(tile.getWestConnect(), 1, 0);
                    }

                    total += checkEndRoadPoints(tile.getSouthConnect(), 0, 0);

                    if (total == 2) {
                        System.out.println("ROAD COMPLETE");
                    } else {
                        this.placedTile = tile;
                        tile.setMeeple(this);
                        System.out.println("ROAD INCOMPLETE");
                    }
                }
            }
            if (tileType == TYPE.FIELD && tile.hasMonastry())
            {

            }

        }

        //West Side
        if (direction == 3){
            TYPE tileType = tile.getWestSide();
            if (tileType == TYPE.CITY)
            {

            }
            if (tileType == TYPE.ROAD)
            {
                if (tile.isRoadEnd())
                {
                    if (checkEndRoadPoints(tile.getWestConnect(), 1, 0) == 1)
                    {
                        System.out.println("Complete Road");
                    }

                    else
                    {
                        this.placedTile = tile;
                        tile.setMeeple(this);
                        System.out.println("ROAD NOT COMPLETE");
                    }
                }
                else
                {
                    int total = 0;
                    if (tile.getNorthSide() == TYPE.ROAD)
                    {
                       total += checkEndRoadPoints(tile.getNorthConnect(), 2, 0);
                    }

                    else if (tile.getEastSide() == TYPE.ROAD)
                    {
                        total += checkEndRoadPoints(tile.getEastConnect(), 3, 0);
                    }

                    else if (tile.getSouthSide() == TYPE.ROAD)
                    {
                        total += checkEndRoadPoints(tile.getSouthConnect(), 0, 0);
                    }

                    total += checkEndRoadPoints(tile.getWestConnect(), 1, 0);

                    if (total == 2)
                    {
                        System.out.println("ROAD COMPLETE");
                    }
                    else
                    {
                        this.placedTile = tile;
                        tile.setMeeple(this);
                        System.out.println("ROAD INCOMPLETE");
                    }
                }
            }
            if (tileType == TYPE.FIELD && tile.hasMonastry())
            {

            }

        }

    }

    private int checkEndRoadPoints(Tile tile, int sideCameFrom, int total)
    {
        if (tile == null)
        {
            total += 0;
        }
        else if (tile.isRoadEnd())
            total += 1;
        else
        {
            if (tile.getNorthSide() == TYPE.ROAD && sideCameFrom != 0)
            {
                total = checkEndRoadPoints(tile.getNorthConnect(), 2, total);
            }

            if (tile.getEastSide() == TYPE.ROAD && sideCameFrom != 1)
            {
                total = checkEndRoadPoints(tile.getEastConnect(), 3, total);
            }

            if (tile.getSouthSide() == TYPE.ROAD && sideCameFrom != 2)
            {
                total = checkEndRoadPoints(tile.getSouthConnect(), 0, total);
            }

            if (tile.getWestSide() == TYPE.ROAD && sideCameFrom != 3)
            {
                total = checkEndRoadPoints(tile.getWestConnect(), 1, total);
            }

        }
        return total;

    }


}
