package exercise2.filters;

import pmp.filter.DataTransformationFilter2;
import pmp.interfaces.Readable;
import pmp.interfaces.Writeable;

import javax.media.jai.JAI;
import javax.media.jai.PlanarImage;
import javax.media.jai.operator.MedianFilterDescriptor;
import java.awt.image.RenderedImage;
import java.awt.image.renderable.ParameterBlock;
import java.awt.image.renderable.RenderableImage;
import java.security.InvalidParameterException;

/**
 * Created by Michelle on 06.11.2017.
 */
public class MedianFilter extends DataTransformationFilter2<PlanarImage, PlanarImage> {
//    public MedianFilter(Readable<PlanarImage> input, Writeable<PlanarImage> output) throws InvalidParameterException {
//        super(input, output);
//    }
//
//    public MedianFilter(Readable<PlanarImage> input) throws InvalidParameterException {
//        super(input);
//    }

    public MedianFilter(Writeable<PlanarImage> output) throws InvalidParameterException {
        super(output);
    }

    @Override
    protected PlanarImage process(PlanarImage entity) {
        ParameterBlock parameterBlock = new ParameterBlock();
        parameterBlock.addSource(entity);
        parameterBlock.add(MedianFilterDescriptor.MEDIAN_MASK_SQUARE);
        parameterBlock.add(4);
        RenderedImage renderedImage = JAI.create("MedianFilter", parameterBlock);
        return PlanarImage.wrapRenderedImage(renderedImage);
    }
}
