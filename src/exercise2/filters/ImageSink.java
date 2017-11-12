package exercise2.filters;

import pmp.filter.Sink;

import javax.media.jai.PlanarImage;
import javax.swing.*;
import java.io.StreamCorruptedException;
import java.util.ArrayList;

public class ImageSink extends Sink<ArrayList> {


    @Override
    public void write(ArrayList value) throws StreamCorruptedException {
        return;
    }
}
