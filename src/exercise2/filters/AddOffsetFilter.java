package exercise2.filters;

import exercise2.Ball;
import exercise2.filters.calcCentroidsFilter.Coordinate;
import pmp.filter.DataTransformationFilter2;
import pmp.interfaces.Readable;
import pmp.interfaces.Writeable;

import java.lang.reflect.Array;
import java.security.InvalidParameterException;
import java.util.ArrayList;
import java.util.List;

public class AddOffsetFilter  extends DataTransformationFilter2<List<Ball>,List<Ball>> {

    private int offsetX;
    private int offsetY;

    public AddOffsetFilter(Readable<List<Ball>> input,int offsetX ,int offsetY) throws InvalidParameterException {
        super(input);
        this.offsetX=offsetX;
        this.offsetY=offsetY;
    }

    public AddOffsetFilter(Writeable<List<Ball>> output, int offsetX, int offsetY) throws InvalidParameterException {
        super(output);
        this.offsetX=offsetX;
        this.offsetY=offsetY;
    }

    @Override
    protected List<Ball> process(List<Ball> entity) {
        List<Ball> result = new ArrayList<>();
        for(int i=0;i<entity.size();i++){
            Coordinate coordinate =new Coordinate(entity.get(i).getCoordinate()._x+offsetX,entity.get(i).getCoordinate()._y+offsetY);
            result.add(new Ball(coordinate,entity.get(i).getDiameter()));
        }

        return result;
    }
}
