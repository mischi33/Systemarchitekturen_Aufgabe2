package exercise2.filters;

import com.sun.media.jai.widget.DisplayJAI;
import pmp.filter.DataTransformationFilter1;
import pmp.interfaces.Readable;
import pmp.interfaces.Writeable;


import javax.media.jai.PlanarImage;
import javax.swing.*;
import java.awt.*;
import java.security.InvalidParameterException;

public class DisplayFilter extends DataTransformationFilter1<PlanarImage> {
    private String frameTitle;


    public DisplayFilter(Readable<PlanarImage> input, String frameTitle) throws InvalidParameterException {
        super(input);
        this.frameTitle = frameTitle;
    }

    public DisplayFilter(Writeable<PlanarImage> output, String frameTitle) throws InvalidParameterException {
        super(output);
        this.frameTitle = frameTitle;
    }

    @Override
    protected void process(PlanarImage entity) {
        JFrame frame = new JFrame();
        frame.setTitle(frameTitle);
        Container contentPane = frame.getContentPane();
        contentPane.setLayout(new BorderLayout());
        DisplayJAI displayJAI = new DisplayJAI(entity);

        contentPane.add(new JScrollPane(displayJAI), BorderLayout.CENTER);

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(500,400);
        frame.setVisible(true);
    }
}
