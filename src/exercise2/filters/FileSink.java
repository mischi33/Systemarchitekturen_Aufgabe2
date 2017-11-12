package exercise2.filters;

import exercise2.Ball;
import pmp.filter.Sink;
import pmp.interfaces.Readable;

import java.io.*;
import java.security.InvalidParameterException;
import java.util.List;

public class FileSink extends Sink<List<Ball>> {
    private BufferedWriter writer;

    public FileSink (String path) {
        try {
            writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(path), "UTF-8"));
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    public FileSink(Readable<List<Ball>> input, String path) throws InvalidParameterException {
        super(input);
        try {
            writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(path), "UTF-8"));
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void write(List<Ball> value) throws StreamCorruptedException {
        try {
            if (value != null) {
                for (Ball ball : value) {
                    writer.write(ball.toString());
                    writer.write(System.getProperty("line.separator"));
                    writer.write("-----------------------------------------------------");
                    writer.write(System.getProperty("line.separator"));
                }
                writer.flush();
            }
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
