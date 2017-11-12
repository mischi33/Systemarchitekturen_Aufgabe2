package exercise2;

import exercise2.filters.*;
import exercise2.filters.calcCentroidsFilter.CalcCentroidsFilter;
import exercise2.filters.calcCentroidsFilter.Coordinate;
import pmp.interfaces.Readable;
import pmp.interfaces.Writeable;
import pmp.pipes.SimplePipe;


import java.awt.*;
import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        createList();
        pullPipe();

    }

    private static void pushPipe() {
        List<Coordinate> min = createMinList();
        List<Coordinate> max = createMaxList();

        FileSink fileSink = new FileSink();
        SimplePipe pipe_1 = new SimplePipe(fileSink);
        CalcToleranceFilter calcToleranceFilter = new CalcToleranceFilter((Writeable) pipe_1, 19, 21, min, max);
        SimplePipe pipe_2 = new SimplePipe((Writeable) calcToleranceFilter);
        AddOffsetFilter addOffsetFilter = new AddOffsetFilter((Writeable) pipe_2, 0, 50);
        SimplePipe pipe_3 = new SimplePipe((Writeable)addOffsetFilter);
        CalcDiameterFilter calcDiameterFilter = new CalcDiameterFilter((Writeable) pipe_3, "loetstellenErgebnis.png");
        SimplePipe pipe_4 = new SimplePipe((Writeable) calcDiameterFilter);
        CalcCentroidsFilter calcCentroidsFilter = new CalcCentroidsFilter((Writeable) pipe_4);
        SimplePipe pipe_5 = new SimplePipe((Writeable) calcCentroidsFilter);
        SaveResultFilter saveResultFilter = new SaveResultFilter((Writeable) pipe_5);
        SimplePipe pipe_6 = new SimplePipe((Writeable) saveResultFilter);
        DisplayFilter displayFilter0 = new DisplayFilter((Writeable) pipe_6);
        SimplePipe pipe_7 = new SimplePipe((Writeable) displayFilter0);
        OpeningFilter openingFilter = new OpeningFilter((Writeable) pipe_7);
        SimplePipe pipe_8 = new SimplePipe((Writeable) openingFilter);
        DisplayFilter displayFilter1 = new DisplayFilter((Writeable) pipe_8);
        SimplePipe pipe_9 = new SimplePipe((Writeable) displayFilter1);
        MedianFilter medianFilter = new MedianFilter((Writeable) pipe_9);
        SimplePipe pipe_10 = new SimplePipe((Writeable) medianFilter);
        DisplayFilter displayFilter = new DisplayFilter((Writeable) pipe_10);
        SimplePipe pipe_11 = new SimplePipe((Writeable) displayFilter);
        ThresholdFilter thresholdFilter = new ThresholdFilter((Writeable) pipe_11);
        SimplePipe pipe_12 = new SimplePipe((Writeable) thresholdFilter);
        DisplayFilter df2 = new DisplayFilter((Writeable) pipe_12);
        SimplePipe pipe_13 = new SimplePipe((Writeable) df2);
        ROIFilter roiFilter = new ROIFilter((Writeable) pipe_13, new Rectangle(0, 50, 448, 100));
        SimplePipe pipe_14 = new SimplePipe((Writeable) roiFilter);
        DisplayFilter df = new DisplayFilter((Writeable) pipe_14);
        SimplePipe pipe_15 = new SimplePipe((Writeable) df);
        ImageSource imageSource = new ImageSource(pipe_15, "loetstellen.jpg");
        imageSource.run();
    }

    private static void pullPipe() {
        List<Coordinate> min = createMinList();
        List<Coordinate> max = createMaxList();
        ImageSource imageSource = new ImageSource(("loetstellen.jpg"));
        SimplePipe pipe_1 = new SimplePipe(imageSource);
        DisplayFilter displayFilter = new DisplayFilter((Readable) pipe_1);
        SimplePipe pipe_2 = new SimplePipe((Readable) displayFilter);
        ROIFilter roiFilter = new ROIFilter((Readable) pipe_2, new Rectangle(0, 50, 448, 100));
        SimplePipe pipe_3 = new SimplePipe((Readable) roiFilter);
        DisplayFilter displayFilter1 = new DisplayFilter((Readable) pipe_3);
        SimplePipe pipe_4 = new SimplePipe((Readable) displayFilter1);
        ThresholdFilter thresholdFilter = new ThresholdFilter((Readable) pipe_4);
        SimplePipe pipe_5 = new SimplePipe((Readable) thresholdFilter);
        DisplayFilter displayFilter2 = new DisplayFilter((Readable) pipe_5);
        SimplePipe pipe_6 = new SimplePipe((Readable) displayFilter2);
        MedianFilter medianFilter = new MedianFilter((Readable) pipe_6);
        SimplePipe pipe_7 = new SimplePipe((Readable) medianFilter);
        DisplayFilter displayFilter3 = new DisplayFilter((Readable) pipe_7);
        SimplePipe pipe_8 = new SimplePipe((Readable) displayFilter3);
        OpeningFilter openingFilter=new OpeningFilter((Readable)pipe_8);
        SimplePipe pipe_9 =new SimplePipe((Readable)openingFilter);
        DisplayFilter displayFilter4 =new DisplayFilter((Readable)pipe_9);
        SimplePipe pipe_10 = new SimplePipe((Readable)displayFilter4);
        SaveResultFilter saveResultFilter=new SaveResultFilter((Readable)pipe_10);
        SimplePipe pipe_11 =new SimplePipe((Readable)saveResultFilter);
        CalcCentroidsFilter calcCentroidsFilter=new CalcCentroidsFilter((Readable)pipe_11);
        SimplePipe pipe_12=new SimplePipe((Readable)calcCentroidsFilter);
        CalcDiameterFilter calcDiameterFilter=new CalcDiameterFilter((Readable)pipe_12,"loetstellenErgebnis.png");
        SimplePipe pipe_13 =new SimplePipe((Readable)calcDiameterFilter);
        AddOffsetFilter addOffsetFilter=new AddOffsetFilter((Readable) pipe_13,0,50);
        SimplePipe pipe_14 =new SimplePipe((Readable)addOffsetFilter);
        CalcToleranceFilter calcToleranceFilter=new CalcToleranceFilter((Readable)pipe_14,19,21,min,max);
        SimplePipe pipe_15=new SimplePipe((Readable)calcToleranceFilter);
        FileSink fileSink=new FileSink(pipe_15);
        fileSink.run();


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

    private static List<Coordinate> createList() {
        List<Coordinate> min = new ArrayList<>();
        try {
            BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(new File("expectedCentroids.txt"))));
            String line = br.readLine();
            int i = line.indexOf("[") + 1;
            int j = line.indexOf("]");
            String toSplit = line.substring(i, j);
            String[] coords = toSplit.split(", ");
            for (String s : coords) {
                String c = s.substring(1, s.length() - 1);
                String[] seperatedCoords = c.split(",");

            }

        } catch (IOException e) {
            e.printStackTrace();
        }
        return  min;
    }
}
