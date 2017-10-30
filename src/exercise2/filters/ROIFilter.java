package exercise2.filters;

import com.sun.media.jai.widget.DisplayJAI;
import pmp.filter.DataCompositionFilter;
import pmp.filter.DataTransformationFilter1;
import pmp.filter.DataTransformationFilter2;
import pmp.interfaces.Writeable;

import javax.media.jai.PlanarImage;
import javax.swing.*;
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
        entity = PlanarImage.wrapRenderedImage((RenderedImage)entity.getAsBufferedImage(rectangle, entity.getColorModel()));
        return entity;
    }
}
