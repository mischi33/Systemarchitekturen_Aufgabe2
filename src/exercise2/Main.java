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
        List<Coordinate> expectedCoordinates = createListFromFile();
        List<Coordinate> min = createMinList(expectedCoordinates, 2);
        List<Coordinate> max = createMaxList(expectedCoordinates, 2);
        pushPipe(min, max, 0, 50);
        pullPipe(min, max, 0, 50);

    }

    private static void pushPipe(List<Coordinate> min, List<Coordinate> max, int offsetX, int offsetY) {
        FileSink fileSink = new FileSink("resultPushPipe.txt");
        SimplePipe pipe_1 = new SimplePipe(fileSink);
        CalcToleranceFilter calcToleranceFilter = new CalcToleranceFilter((Writeable) pipe_1, 19, 21, min, max);
        SimplePipe pipe_2 = new SimplePipe((Writeable) calcToleranceFilter);
        AddOffsetFilter addOffsetFilter = new AddOffsetFilter((Writeable) pipe_2, offsetX, offsetY);
        SimplePipe pipe_3 = new SimplePipe((Writeable) addOffsetFilter);
        CalcDiameterFilter calcDiameterFilter = new CalcDiameterFilter((Writeable) pipe_3, "loetstellenErgebnis.png");
        SimplePipe pipe_4 = new SimplePipe((Writeable) calcDiameterFilter);
        CalcCentroidsFilter calcCentroidsFilter = new CalcCentroidsFilter((Writeable) pipe_4);
        SimplePipe pipe_5 = new SimplePipe((Writeable) calcCentroidsFilter);
        SaveResultFilter saveResultFilter = new SaveResultFilter((Writeable) pipe_5);
        SimplePipe pipe_6 = new SimplePipe((Writeable) saveResultFilter);
        DisplayFilter displayFilter0 = new DisplayFilter((Writeable) pipe_6, "Push Pipe: Opening-Filter (erode, dilate)");
        SimplePipe pipe_7 = new SimplePipe((Writeable) displayFilter0);
        OpeningFilter openingFilter = new OpeningFilter((Writeable) pipe_7);
        SimplePipe pipe_8 = new SimplePipe((Writeable) openingFilter);
        DisplayFilter displayFilter1 = new DisplayFilter((Writeable) pipe_8, "Push Pipe: Median-Filter");
        SimplePipe pipe_9 = new SimplePipe((Writeable) displayFilter1);
        MedianFilter medianFilter = new MedianFilter((Writeable) pipe_9);
        SimplePipe pipe_10 = new SimplePipe((Writeable) medianFilter);
        DisplayFilter displayFilter = new DisplayFilter((Writeable) pipe_10, "Push Pipe: Threshold-Filter");
        SimplePipe pipe_11 = new SimplePipe((Writeable) displayFilter);
        ThresholdFilter thresholdFilter = new ThresholdFilter((Writeable) pipe_11);
        SimplePipe pipe_12 = new SimplePipe((Writeable) thresholdFilter);
        DisplayFilter df2 = new DisplayFilter((Writeable) pipe_12, "Push Pipe: ROI-Filter");
        SimplePipe pipe_13 = new SimplePipe((Writeable) df2);
        ROIFilter roiFilter = new ROIFilter((Writeable) pipe_13, new Rectangle(offsetX, offsetY, 448, 100));
        SimplePipe pipe_14 = new SimplePipe((Writeable) roiFilter);
        DisplayFilter df = new DisplayFilter((Writeable) pipe_14, "Push Pipe: Original");
        SimplePipe pipe_15 = new SimplePipe((Writeable) df);
        ImageSource imageSource = new ImageSource(pipe_15, "loetstellen.jpg");
        imageSource.run();
    }

    private static void pullPipe(List<Coordinate> min, List<Coordinate> max, int offsetX, int offsetY) {
        ImageSource imageSource = new ImageSource(("loetstellen.jpg"));
        SimplePipe pipe_1 = new SimplePipe(imageSource);
        DisplayFilter displayFilter = new DisplayFilter((Readable) pipe_1, "Pull Pipe: Original");
        SimplePipe pipe_2 = new SimplePipe((Readable) displayFilter);
        ROIFilter roiFilter = new ROIFilter((Readable) pipe_2, new Rectangle(offsetX, offsetY, 448, 100));
        SimplePipe pipe_3 = new SimplePipe((Readable) roiFilter);
        DisplayFilter displayFilter1 = new DisplayFilter((Readable) pipe_3, "Pull Pipe: ROI-Filter");
        SimplePipe pipe_4 = new SimplePipe((Readable) displayFilter1);
        ThresholdFilter thresholdFilter = new ThresholdFilter((Readable) pipe_4);
        SimplePipe pipe_5 = new SimplePipe((Readable) thresholdFilter);
        DisplayFilter displayFilter2 = new DisplayFilter((Readable) pipe_5, "Pull Pipe: Threshold-Filter");
        SimplePipe pipe_6 = new SimplePipe((Readable) displayFilter2);
        MedianFilter medianFilter = new MedianFilter((Readable) pipe_6);
        SimplePipe pipe_7 = new SimplePipe((Readable) medianFilter);
        DisplayFilter displayFilter3 = new DisplayFilter((Readable) pipe_7, "Pull Pipe: Median-Filter");
        SimplePipe pipe_8 = new SimplePipe((Readable) displayFilter3);
        OpeningFilter openingFilter = new OpeningFilter((Readable) pipe_8);
        SimplePipe pipe_9 = new SimplePipe((Readable) openingFilter);
        DisplayFilter displayFilter4 = new DisplayFilter((Readable) pipe_9, "Pull Pipe: Opening-Filter (erode, dilate)");
        SimplePipe pipe_10 = new SimplePipe((Readable) displayFilter4);
        SaveResultFilter saveResultFilter = new SaveResultFilter((Readable) pipe_10);
        SimplePipe pipe_11 = new SimplePipe((Readable) saveResultFilter);
        CalcCentroidsFilter calcCentroidsFilter = new CalcCentroidsFilter((Readable) pipe_11);
        SimplePipe pipe_12 = new SimplePipe((Readable) calcCentroidsFilter);
        CalcDiameterFilter calcDiameterFilter = new CalcDiameterFilter((Readable) pipe_12, "loetstellenErgebnis.png");
        SimplePipe pipe_13 = new SimplePipe((Readable) calcDiameterFilter);
        AddOffsetFilter addOffsetFilter = new AddOffsetFilter((Readable) pipe_13, offsetX, offsetY);
        SimplePipe pipe_14 = new SimplePipe((Readable) addOffsetFilter);
        CalcToleranceFilter calcToleranceFilter = new CalcToleranceFilter((Readable) pipe_14, 19, 21, min, max);
        SimplePipe pipe_15 = new SimplePipe((Readable) calcToleranceFilter);
        FileSink fileSink = new FileSink(pipe_15, "resultPullPipe.txt");
        fileSink.run();
    }


    private static List<Coordinate> createMinList(List<Coordinate> expected, int tolerance) {
        List<Coordinate> min = new ArrayList<>();
        for (Coordinate c : expected) {
            min.add(new Coordinate(c._x - tolerance, c._y - tolerance));
        }
        return min;
    }

    private static List<Coordinate> createMaxList(List<Coordinate> expected, int tolerance) {
        List<Coordinate> max = new ArrayList<>();
        for (Coordinate c : expected) {
            max.add(new Coordinate(c._x - tolerance, c._y - tolerance));
        }
        return max;
    }

    private static List<Coordinate> createListFromFile() {
        List<Coordinate> expectedCoords = new ArrayList<>();
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
                Integer x = Integer.valueOf(seperatedCoords[0]);
                Integer y = Integer.valueOf(seperatedCoords[1]);
                Coordinate coordinate = new Coordinate(x, y);
                expectedCoords.add(coordinate);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
        return expectedCoords;
    }
}
