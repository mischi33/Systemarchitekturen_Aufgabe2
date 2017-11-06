package exercise2.filters;

import com.sun.media.jai.widget.DisplayJAI;
import pmp.filter.Source;
import pmp.interfaces.Writeable;

import javax.media.jai.JAI;
import javax.media.jai.KernelJAI;
import javax.media.jai.PlanarImage;
import javax.swing.*;
import java.awt.*;
import java.io.StreamCorruptedException;

/**
 * Created by Michelle on 30.10.2017.
 */
public class ImageSource extends Source<PlanarImage> {
    private String path;

    public ImageSource(Writeable<PlanarImage> output, String path) {
        super(output);
        this.path = path;

    }


    @Override
    public PlanarImage read() throws StreamCorruptedException {
        return JAI.create("fileload", path);
    }
}
