package exercise2;

import exercise2.filters.*;
import exercise2.filters.calcCentroidsFilter.CalcCentroidsFilter;
import exercise2.filters.calcCentroidsFilter.Coordinate;
import pmp.interfaces.Writeable;
import pmp.pipes.SimplePipe;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        pushPipe();

    }

    private static void pushPipe() {
        List<Coordinate> min = createMinList();
        List<Coordinate> max = createMaxList();

        FileSink fileSink = new FileSink();
        SimplePipe pipe_1 = new SimplePipe(fileSink);
        CalcToleranceFilter calcToleranceFilter = new CalcToleranceFilter((Writeable) pipe_1, 19, 21, min, max);
        SimplePipe pipe_y = new SimplePipe((Writeable) calcToleranceFilter);
        CalcDiameterFilter calcDiameterFilter = new CalcDiameterFilter((Writeable) pipe_y,"loetstellenErgebnis.png");
        SimplePipe pipe_x = new SimplePipe((Writeable) calcDiameterFilter) ;
        CalcCentroidsFilter calcCentroidsFilter = new CalcCentroidsFilter((Writeable) pipe_x);
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

    private static List<Coordinate> createMinList() {
        List<Coordinate> min = new ArrayList<>();
        min.add(new Coordinate(71, 75));
        min.add(new Coordinate(108, 78));
        min.add(new Coordinate(200, 78));
        min.add(new Coordinate(263, 77));
        min.add(new Coordinate(328, 79));
        min.add(new Coordinate(394, 79));
        return min;
    }

    private static List<Coordinate> createMaxList() {
        List<Coordinate> max = new ArrayList<>();
        max.add(new Coordinate(75, 79));
        max.add(new Coordinate(112, 82));
        max.add(new Coordinate(204, 82));
        max.add(new Coordinate(267, 81));
        max.add(new Coordinate(332, 83));
        max.add(new Coordinate(398, 83));
        return max;
    }
}
