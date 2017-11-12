package exercise2.filters;


import exercise2.filters.calcCentroidsFilter.Coordinate;
import pmp.filter.DataTransformationFilter2;
import pmp.interfaces.Readable;
import pmp.interfaces.Writeable;
import sun.java2d.xr.XRBackend;

import javax.media.jai.JAI;
import javax.media.jai.PlanarImage;
import java.awt.image.BufferedImage;
import java.security.InvalidParameterException;
import java.util.ArrayList;

public class CalcAusdehnungsFilter extends DataTransformationFilter2<ArrayList<Coordinate>, ArrayList<Integer>> {

    PlanarImage _image;

    public CalcAusdehnungsFilter(Readable<ArrayList<Coordinate>> input, String imagePath) throws InvalidParameterException {
        super(input);
        _image= JAI.create("fileload",imagePath);
    }

    public CalcAusdehnungsFilter(Writeable<ArrayList<Integer>> output, String imagePath) throws InvalidParameterException {
        super(output);
        _image= JAI.create("fileload",imagePath);
    }



    @Override
    protected ArrayList<Integer> process(ArrayList<Coordinate> entity) {
        ArrayList<Integer> ausdehnung = new ArrayList<>();

        for(int i=0; i<entity.size();i++)   {

            int x=entity.get(i)._x;
            int y=entity.get(i)._y;

            BufferedImage img =_image.getAsBufferedImage();
            int xRight=0;
            int xLeft=0;
            int xCount=x;
            while (img.getRaster().getSample(xCount,y,0)==255){
                xRight++;
                xCount++;
            }
            xCount=x;
            while (img.getRaster().getSample(xCount,y,0)==255){
                xLeft++;
                xCount--;
            }

            ausdehnung.add(xRight+xLeft);
        }
        return ausdehnung;
    }
}
