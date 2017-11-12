package exercise2.filters;

import exercise2.Ball;
import exercise2.filters.calcCentroidsFilter.Coordinate;
import pmp.filter.DataTransformationFilter2;
import pmp.interfaces.Readable;
import pmp.interfaces.Writeable;

import java.security.InvalidParameterException;
import java.util.ArrayList;
import java.util.List;

public class CalcToleranceFilter extends DataTransformationFilter2<List<Ball>, List<Ball>>{
    private List<Coordinate> coordinateToleranceMin;
    private List<Coordinate> coordinateToleranceMax;
    private Integer diameterToleranceMin;
    private Integer diameterToleranceMax;

    public CalcToleranceFilter(Readable<List<Ball>> input, Integer diameterToleranceMin, Integer diameterToleranceMax, List<Coordinate> coordinateToleranceMin, List<Coordinate> coordinateToleranceMax) throws InvalidParameterException {
        super(input);
        this.coordinateToleranceMin = coordinateToleranceMin;
        this.coordinateToleranceMax = coordinateToleranceMax;
        this.diameterToleranceMin = diameterToleranceMin;
        this.diameterToleranceMax = diameterToleranceMax;
    }

    public CalcToleranceFilter(Writeable<List<Ball>> output, Integer diameterToleranceMin, Integer diameterToleranceMax, List<Coordinate> coordinateToleranceMin, List<Coordinate> coordinateToleranceMax) throws InvalidParameterException {
        super(output);
        this.coordinateToleranceMin = coordinateToleranceMin;
        this.coordinateToleranceMax = coordinateToleranceMax;
        this.diameterToleranceMin = diameterToleranceMax;
        this.diameterToleranceMax = diameterToleranceMax;
    }

    @Override
    protected List<Ball> process(List<Ball> entity) {
        List<Ball> balls = new ArrayList<>();
        for (int i = 1; i < entity.size(); i++) {
            Ball ball = entity.get(i);
            Coordinate toleranceCoordMin = coordinateToleranceMin.get(i);
            Coordinate toleranceCoordMax = coordinateToleranceMax.get(i);
            if (ball.getCoordinate()._x >= toleranceCoordMin._x &&
                    ball.getCoordinate()._x <= toleranceCoordMax._x &&
                    ball.getCoordinate()._y >= toleranceCoordMin._y &&
                    ball.getCoordinate()._y <= toleranceCoordMax._y) {
                ball.setCoordToleranceOk(true);
            } else {
                ball.setDiameterToleranceOk(false);
            }
            if (ball.getDiameter() >= diameterToleranceMin && ball.getDiameter() <= diameterToleranceMax) {
                ball.setDiameterToleranceOk(true);
            } else {
                ball.setDiameterToleranceOk(false);
            }
            balls.add(ball);
        }
        return balls;
    }
}
