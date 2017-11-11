package exercise2.filters;

import pmp.filter.DataTransformationFilter2;
import pmp.interfaces.Writeable;

import javax.media.jai.JAI;
import javax.media.jai.KernelJAI;
import javax.media.jai.PlanarImage;
import java.awt.image.renderable.ParameterBlock;
import java.security.InvalidParameterException;

/**
 * Created by Michelle on 06.11.2017.
 */
public class OpeningFilter extends DataTransformationFilter2<PlanarImage, PlanarImage> {
    public OpeningFilter(Writeable<PlanarImage> output) throws InvalidParameterException {
        super(output);
    }

    @Override
    protected PlanarImage process(PlanarImage entity) {
        float[] kernelMatrix = {0, 0, 0, 0, 0, 0, 0,
                0, 1, 1, 1, 1, 1, 0,
                0, 1, 1, 1, 1, 1, 0,
                0, 1, 1, 1, 1, 1, 0,
                0, 1, 1, 1, 1, 1, 0,
                0, 1, 1, 1, 1, 1, 0,
                0, 0, 0, 0, 0, 0, 0};


        float[] kernelMatrix1 = {0,0,1,
                0, 1, 0,
                1, 0, 0};
        KernelJAI kernel = new KernelJAI(7, 7, kernelMatrix);

        ParameterBlock p = new ParameterBlock();
        p.addSource(entity);
        p.add(kernel);

        PlanarImage output = JAI.create("erode", p);

        p = new ParameterBlock();
        p.addSource(output);
        p.add(kernel);
        output = JAI.create("dilate", p);
        return output;
    }
}
