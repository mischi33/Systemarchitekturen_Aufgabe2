package exercise3;

import java.util.EventObject;

public class ImageAppearanceEvent extends EventObject{
    /**
     * Constructs a prototypical Event.
     *
     * @param source The object on which the Event initially occurred.
     * @throws IllegalArgumentException if source is null.
     */
    public ImageAppearanceEvent(Object source) {
        super(source);
    }
}
