package exercise2.filters;

import com.sun.media.jai.widget.DisplayJAI;
import pmp.filter.Source;

import javax.media.jai.JAI;
import javax.media.jai.KernelJAI;
import javax.media.jai.PlanarImage;
import javax.swing.*;
import java.awt.*;
import java.io.StreamCorruptedException;

/**
 * Created by Michelle on 30.10.2017.
 */
public class ImageSource extends Source<PlanarImage> {
    @Override
    public PlanarImage read() throws StreamCorruptedException {
        PlanarImage image = JAI.create("fileload", "loetstellen.jpg");
        String imageInfo = "Dimensions: "+image.getWidth()+"x"+image.getHeight()+ " Bands:"+image.getNumBands();
        JFrame frame = new JFrame();
        frame.setTitle("Lötstellen.jpg");
        Container contentPane = frame.getContentPane();
        contentPane.setLayout(new BorderLayout());
        DisplayJAI displayJAI = new DisplayJAI(image);

        contentPane.add(new JScrollPane(displayJAI), BorderLayout.CENTER);
        contentPane.add(new JLabel(imageInfo), BorderLayout.SOUTH);

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(500,400);
        frame.setVisible(true);


        return image;
    }
}
