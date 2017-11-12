package exercise2.filters;

import exercise2.Ball;
import pmp.filter.Sink;

import java.io.*;
import java.util.List;

public class FileSink extends Sink<List<Ball>> {


    @Override
    public void write(List<Ball> value) throws StreamCorruptedException {
        try {
            BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream("result.txt"), "UTF-8"));
            if (value != null) {
                for (Ball ball : value) {
                    writer.write(ball.toString());
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
