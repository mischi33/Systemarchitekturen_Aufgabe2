package exercise2;

import exercise2.filters.ImageSink;
import exercise2.filters.ImageSource;
import exercise2.filters.ROIFilter;
import pmp.interfaces.Writeable;
import pmp.pipes.SimplePipe;
import java.awt.*;

/**
 * Created by Michelle on 30.10.2017.
 */
public class Main {
    public static void main(String[] args) {
        ImageSink imageSink = new ImageSink();
        SimplePipe pipe_1 = new SimplePipe(imageSink);
        ROIFilter roiFilter = new ROIFilter(pipe_1, new Rectangle(0, 13, 448, 100));
        SimplePipe pipe_2 = new SimplePipe((Writeable) roiFilter);
        ImageSource imageSource = new ImageSource(pipe_2, "loetstellen.jpg");
        imageSource.run();

    }
}
