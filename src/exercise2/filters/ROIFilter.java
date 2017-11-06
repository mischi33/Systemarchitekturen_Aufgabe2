package exercise2.filters;

import pmp.filter.DataTransformationFilter2;
import pmp.interfaces.Writeable;

import javax.media.jai.PlanarImage;
import java.awt.*;
import java.awt.image.RenderedImage;
import java.security.InvalidParameterException;

/**
 * Created by Michelle on 30.10.2017.
 */
public class ROIFilter extends DataTransformationFilter2<PlanarImage, PlanarImage> {
    private Rectangle rectangle;

    public ROIFilter(Writeable<PlanarImage> output, Rectangle rectangle) throws InvalidParameterException {
        super(output);
        this.rectangle = rectangle;
    }

    @Override
    public PlanarImage process(PlanarImage entity) {
        return PlanarImage.wrapRenderedImage((RenderedImage)entity.getAsBufferedImage(rectangle, entity.getColorModel()));
    }
}
