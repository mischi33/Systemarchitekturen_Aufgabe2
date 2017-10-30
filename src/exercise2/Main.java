package exercise2;

import exercise2.filters.ImageSink;
import exercise2.filters.ImageSource;

import javax.media.jai.PlanarImage;
import java.io.StreamCorruptedException;

/**
 * Created by Michelle on 30.10.2017.
 */
public class Main {
    public static void main(String[] args) {
        ImageSource imageSource = new ImageSource();
        try {
            imageSource.read();
        } catch (StreamCorruptedException e) {
            e.printStackTrace();
        }
    }
}
