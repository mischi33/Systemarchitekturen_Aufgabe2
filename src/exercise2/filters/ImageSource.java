package exercise2.filters;

import pmp.filter.Source;
import pmp.interfaces.Writeable;

import javax.media.jai.JAI;
import javax.media.jai.PlanarImage;
import java.io.StreamCorruptedException;

public class ImageSource extends Source<PlanarImage> {
    private String path;
    private boolean created = false;

    public ImageSource(String path) {
        this.path = path;
    }

    public ImageSource(Writeable<PlanarImage> output, String path) {
        super(output);
        this.path = path;

    }


    @Override
    public PlanarImage read() throws StreamCorruptedException {
        if (!created) {
            created = true;
            return JAI.create("fileload", path);
        }
        return null;
    }
}
