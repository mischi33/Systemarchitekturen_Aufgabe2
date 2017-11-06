package exercise2;

import exercise2.filters.DisplayFilter;
import exercise2.filters.ImageSink;
import exercise2.filters.ImageSource;
import exercise2.filters.ROIFilter;
import pmp.interfaces.Writeable;
import pmp.pipes.SimplePipe;
import java.awt.*;

public class Main {
    public static void main(String[] args) {
        ImageSink imageSink = new ImageSink();
        SimplePipe pipe_1 = new SimplePipe(imageSink);
        DisplayFilter df2 = new DisplayFilter(pipe_1);
        SimplePipe pipe_1b = new SimplePipe((Writeable) df2);
        ROIFilter roiFilter = new ROIFilter(pipe_1b, new Rectangle(0, 50, 448, 100));
        SimplePipe pipe_1a = new SimplePipe((Writeable) roiFilter);
        DisplayFilter df = new DisplayFilter(pipe_1a);
        SimplePipe pipe_2 = new SimplePipe((Writeable) df);
        ImageSource imageSource = new ImageSource(pipe_2, "loetstellen.jpg");
        imageSource.run();

    }
}
