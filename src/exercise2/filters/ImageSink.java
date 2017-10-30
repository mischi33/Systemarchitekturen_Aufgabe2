package exercise2.filters;

import pmp.filter.Sink;

import javax.media.jai.PlanarImage;
import javax.swing.*;
import java.io.StreamCorruptedException;

/**
 * Created by Michelle on 30.10.2017.
 */
public class ImageSink extends Sink<PlanarImage> {


    @Override
    public void write(PlanarImage value) throws StreamCorruptedException {
        return;
    }
}
