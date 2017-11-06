package exercise2.filters;

import pmp.filter.DataTransformationFilter2;
import pmp.interfaces.Writeable;

import javax.media.jai.JAI;
import javax.media.jai.PlanarImage;
import java.awt.image.RenderedImage;
import java.awt.image.renderable.ParameterBlock;
import java.awt.image.renderable.RenderableImage;
import java.security.InvalidParameterException;

/**
 * Created by Michelle on 06.11.2017.
 */
public class ThresholdFilter extends DataTransformationFilter2<PlanarImage, PlanarImage> {

    public ThresholdFilter(Writeable<PlanarImage> output) throws InvalidParameterException {
        super(output);
    }

    @Override
    protected PlanarImage process(PlanarImage entity) {
        Integer[] lowVal = new Integer[10];
        Integer[] highVal = new Integer[10];
        Integer[] constant = new Integer[10];

        lowVal[0] = 50;
        highVal[0] = 50;
        constant[0] = 50;


        ParameterBlock parameterBlock = new ParameterBlock();
        parameterBlock.addSource(entity);
        parameterBlock.add(lowVal);
        parameterBlock.add(highVal);
        parameterBlock.add(constant);
        RenderedImage dest = JAI.create("threshold", parameterBlock);

        return PlanarImage.wrapRenderedImage(dest);
    }
}
