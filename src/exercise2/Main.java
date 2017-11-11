package exercise2;

import exercise2.filters.*;
import pmp.interfaces.Writeable;
import pmp.pipes.SimplePipe;
import java.awt.*;

public class Main {
    public static void main(String[] args) {
        ImageSink imageSink = new ImageSink();
        SimplePipe simplePipee = new SimplePipe(imageSink);
        SaveResultFilter saveResultFilter = new SaveResultFilter(simplePipee);
        SimplePipe simplePipe1 = new SimplePipe((Writeable) saveResultFilter);
        DisplayFilter displayFilter0 = new DisplayFilter(simplePipe1);
        SimplePipe simplePipe = new SimplePipe((Writeable) displayFilter0);
        OpeningFilter openingFilter = new OpeningFilter(simplePipe);
        SimplePipe pipe0 = new SimplePipe((Writeable) openingFilter);
        DisplayFilter displayFilter1 = new DisplayFilter(pipe0);
        SimplePipe pipe1 = new SimplePipe((Writeable) displayFilter1);
        MedianFilter medianFilter = new MedianFilter(pipe1);
        SimplePipe pipe_0 = new SimplePipe((Writeable) medianFilter);
        DisplayFilter displayFilter = new DisplayFilter(pipe_0);
        SimplePipe pipe_00 = new SimplePipe((Writeable) displayFilter);
        ThresholdFilter thresholdFilter = new ThresholdFilter(pipe_00);
        SimplePipe pipe_1 = new SimplePipe((Writeable) thresholdFilter);
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
