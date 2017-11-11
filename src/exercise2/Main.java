package exercise2;

import exercise2.filters.*;
import exercise2.filters.calcCentroidsFilter.CalcCentroidsFilter;
import pmp.interfaces.Writeable;
import pmp.pipes.SimplePipe;
import java.awt.*;

public class Main {
    public static void main(String[] args) {
        pushPipe();

    }

    private static void pushPipe() {
        ImageSink imageSink = new ImageSink();
        SimplePipe pipe_1 = new SimplePipe(imageSink);
        CalcCentroidsFilter calcCentroidsFilter = new CalcCentroidsFilter((Writeable) pipe_1);
        SimplePipe pipe_2 = new SimplePipe((Writeable) calcCentroidsFilter);
        SaveResultFilter saveResultFilter = new SaveResultFilter((Writeable) pipe_2);
        SimplePipe pipe_3 = new SimplePipe((Writeable) saveResultFilter);
        DisplayFilter displayFilter0 = new DisplayFilter((Writeable) pipe_3);
        SimplePipe pipe_4 = new SimplePipe((Writeable) displayFilter0);
        OpeningFilter openingFilter = new OpeningFilter((Writeable) pipe_4);
        SimplePipe pipe_5 = new SimplePipe((Writeable) openingFilter);
        DisplayFilter displayFilter1 = new DisplayFilter((Writeable) pipe_5);
        SimplePipe pipe_6 = new SimplePipe((Writeable) displayFilter1);
        MedianFilter medianFilter = new MedianFilter((Writeable) pipe_6);
        SimplePipe pipe_7 = new SimplePipe((Writeable) medianFilter);
        DisplayFilter displayFilter = new DisplayFilter((Writeable) pipe_7);
        SimplePipe pipe_8 = new SimplePipe((Writeable) displayFilter);
        ThresholdFilter thresholdFilter = new ThresholdFilter((Writeable) pipe_8);
        SimplePipe pipe_9 = new SimplePipe((Writeable) thresholdFilter);
        DisplayFilter df2 = new DisplayFilter((Writeable) pipe_9);
        SimplePipe pipe_10 = new SimplePipe((Writeable) df2);
        ROIFilter roiFilter = new ROIFilter((Writeable) pipe_10, new Rectangle(0, 50, 448, 100));
        SimplePipe pipe_11 = new SimplePipe((Writeable) roiFilter);
        DisplayFilter df = new DisplayFilter((Writeable) pipe_11);
        SimplePipe pipe_12 = new SimplePipe((Writeable) df);
        ImageSource imageSource = new ImageSource(pipe_12, "loetstellen.jpg");
        imageSource.run();
    }
}
