package exercise3;

import java.util.EventListener;

public interface ImageAppearanceListener extends EventListener {
        void imageAppearanceChanged(ImageAppearanceEvent event);
}
