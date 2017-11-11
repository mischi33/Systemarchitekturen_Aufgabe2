package exercise2.filters;

import com.sun.media.jai.widget.DisplayJAI;
import pmp.filter.DataTransformationFilter1;
import pmp.interfaces.Readable;
import pmp.interfaces.Writeable;

import javax.media.jai.PlanarImage;
import javax.swing.*;
import java.awt.*;
import java.security.InvalidParameterException;

/**
 * Created by Michelle on 30.10.2017.
 */
public class DisplayFilter extends DataTransformationFilter1<PlanarImage> {


    public DisplayFilter(Readable<PlanarImage> input) throws InvalidParameterException {
        super(input);
    }

    public DisplayFilter(Writeable<PlanarImage> output) throws InvalidParameterException {
        super(output);
    }

    @Override
    protected void process(PlanarImage entity) {
        JFrame frame = new JFrame();
        frame.setTitle("Lötstellen.jpg");
        Container contentPane = frame.getContentPane();
        contentPane.setLayout(new BorderLayout());
        DisplayJAI displayJAI = new DisplayJAI(entity);

        contentPane.add(new JScrollPane(displayJAI), BorderLayout.CENTER);

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(500,400);
        frame.setVisible(true);
    }
}
