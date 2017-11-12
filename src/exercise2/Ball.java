package exercise2;

import exercise2.filters.calcCentroidsFilter.Coordinate;

public class Ball {

    private Coordinate coordinate;
    private Integer diameter;
    private boolean coordToleranceOk;
    private boolean diameterToleranceOk;

    public Ball(Coordinate coordinate, Integer diameter) {

        this.coordinate = coordinate;
        this.diameter = diameter;
        coordToleranceOk = false;
        diameterToleranceOk = false;
    }

    public Coordinate getCoordinate() {
        return coordinate;
    }

    public Integer getDiameter() {
        return diameter;
    }

    public boolean isDiameterToleranceOk() {
        return diameterToleranceOk;
    }

    public void setDiameterToleranceOk(boolean diameterToleranceOk) {
        this.diameterToleranceOk = diameterToleranceOk;
    }

    public boolean isCoordToleranceOk() {
        return coordToleranceOk;
    }

    public void setCoordToleranceOk(boolean coordToleranceOk) {
        this.coordToleranceOk = coordToleranceOk;
    }
}
