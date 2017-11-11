package exercise2.filters;

import pmp.filter.DataTransformationFilter2;
import pmp.interfaces.Writeable;

import javax.imageio.ImageIO;
import javax.media.jai.PlanarImage;
import java.io.File;
import java.io.IOException;
import java.security.InvalidParameterException;

public class SaveResultFilter extends DataTransformationFilter2<PlanarImage, PlanarImage> {

    public SaveResultFilter(Writeable<PlanarImage> output) throws InvalidParameterException {
        super(output);
    }

    @Override
    protected PlanarImage process(PlanarImage entity) {
        try {
            ImageIO.write(entity, "png",new File("loetstellenErgebnis.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return entity;
    }
}