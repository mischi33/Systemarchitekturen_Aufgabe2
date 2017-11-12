package exercise2;

import exercise2.filters.calcCentroidsFilter.Coordinate;

public class Ball {

    private Coordinate coordinate;
    private Integer diameter;

    public Ball (Coordinate coordinate, Integer diameter){

        this.coordinate=coordinate;
        this.diameter=diameter;
    }

    public Coordinate getCoordinate() {
        return coordinate;
    }

    public Integer getDiameter() {
        return diameter;
    }
}
