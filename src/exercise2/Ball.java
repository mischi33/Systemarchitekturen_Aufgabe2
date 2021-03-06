package exercise2;

import com.sun.istack.internal.Nullable;
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

    @Override
    public String toString(){
        String data="Centroid Coordinates:\nx = "+coordinate._x + "\ny = "+coordinate._y + "\n\n"+"Diameter = " + diameter +"\n\n";
        if (coordToleranceOk && diameterToleranceOk){
            return data+ "Coordinate is in Tolerance\nDiameter is in Tolerance";

        }
        if (coordToleranceOk && !diameterToleranceOk){
            return data+ "Coordinate is in Tolerance\nDiameter is not in Tolerance";

        }
        if (!coordToleranceOk && diameterToleranceOk){
            return data+ "Coordinate is not in Tolerance\nDiameter is in Tolerance";

        }
            return data+ "Coordinate is not in Tolerance\nDiameter is not in Tolerance";
    }
}
