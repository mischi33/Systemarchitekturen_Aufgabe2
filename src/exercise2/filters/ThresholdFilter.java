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
        double thresholdLow = 0.0;
        double thresholdHigh = 50.0;
        double thresholdConst = 255.0;
        int band = 255;

        double[] lowVal = new double[band];
        double[] highVal = new double[band];
        double[] constant = new double[band];

        for (int i = 0; i < band; i++) {
            lowVal[i] = thresholdLow;
            highVal[i] = thresholdHigh;
            constant[i] = thresholdConst;
        }


        ParameterBlock parameterBlock = new ParameterBlock();
        parameterBlock.addSource(entity);
        parameterBlock.add(lowVal);
        parameterBlock.add(highVal);
        parameterBlock.add(constant);
        RenderedImage dest = JAI.create("threshold", parameterBlock);

        return PlanarImage.wrapRenderedImage(dest);
    }
}
