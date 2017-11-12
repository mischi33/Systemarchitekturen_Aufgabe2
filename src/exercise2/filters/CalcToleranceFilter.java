package exercise2.filters;

import exercise2.Ball;
import exercise2.filters.calcCentroidsFilter.Coordinate;
import pmp.filter.DataTransformationFilter2;
import pmp.interfaces.Readable;
import pmp.interfaces.Writeable;

import java.security.InvalidParameterException;
import java.util.List;

public class CalcToleranceFilter extends DataTransformationFilter2<List<Ball>, List<Ball>>{
    private List<Coordinate> coordinateTolerance;
    private Integer diameterTolerance;

    public CalcToleranceFilter(Readable<List<Ball>> input, Integer diameterTolerance, List<Coordinate> coordinateTolerance) throws InvalidParameterException {
        super(input);
        this.coordinateTolerance = coordinateTolerance;
        this.diameterTolerance = diameterTolerance;
    }

    public CalcToleranceFilter(Writeable<List<Ball>> output, Integer diameterTolerance, List<Coordinate> coordinateTolerance) throws InvalidParameterException {
        super(output);
        this.coordinateTolerance = coordinateTolerance;
        this.diameterTolerance = diameterTolerance;
    }

    @Override
    protected List<Ball> process(List<Ball> entity) {
        for (int i = 0; i < entity.size(); i++) {
            Ball ball = entity.get(i);
            Coordinate toleranceCoord = coordinateTolerance.get(i);
            if (ball.getCoordinate().equals(toleranceCoord)) {
                ball.setCoordToleranceOk(true);
            } else {
                ball.setCoordToleranceOk(false);
            }
            if (ball.getDiameter() == diameterTolerance) {
                ball.setDiameterToleranceOk(true);
            }
        }
        return null;
    }
}
