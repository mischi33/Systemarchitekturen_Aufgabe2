package exercise2.filters;


import exercise2.Ball;
import exercise2.filters.calcCentroidsFilter.Coordinate;
import pmp.filter.DataTransformationFilter2;
import pmp.interfaces.Readable;
import pmp.interfaces.Writeable;
import javax.media.jai.JAI;
import javax.media.jai.PlanarImage;
import java.awt.image.BufferedImage;
import java.security.InvalidParameterException;
import java.util.ArrayList;

public class CalcDiameterFilter extends DataTransformationFilter2<ArrayList<Coordinate>, ArrayList<Ball>> {

    PlanarImage _image;

    public CalcDiameterFilter(Readable<ArrayList<Coordinate>> input, String imagePath) throws InvalidParameterException {
        super(input);
        _image = JAI.create("fileload", imagePath);
    }

    public CalcDiameterFilter(Writeable<ArrayList<Ball>> output, String imagePath) throws InvalidParameterException {
        super(output);
        _image = JAI.create("fileload", imagePath);
    }


    @Override
    protected ArrayList<Ball> process(ArrayList<Coordinate> entity) {
        ArrayList<Ball> balls = new ArrayList<>();

        for (int i = 0; i < entity.size(); i++) {

            int x = entity.get(i)._x;
            int y = entity.get(i)._y;

            BufferedImage img = _image.getAsBufferedImage();
            int xRight = 0;
            int xLeft = 0;
            int xCount = x;
            while (img.getRaster().getSample(xCount, y, 0) == 255) {
                xRight++;
                xCount++;
            }
            xCount = x;
            while (img.getRaster().getSample(xCount, y, 0) == 255) {
                xLeft++;
                xCount--;
            }

            balls.add(new Ball(entity.get(i), xRight + xLeft));
        }
        return balls;
    }
}
