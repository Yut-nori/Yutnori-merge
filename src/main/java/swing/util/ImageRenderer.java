package swing.util;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.Map;

public class ImageRenderer {

    public static void renderImages(Graphics g, JComponent comp,
                                    Map<String, BufferedImage> images,
                                    Object[][] imageData) {
        for (Object[] data : imageData) {
            String name = (String) data[0];
            int x = (int) data[1];
            int y = (int) data[2];
            boolean fullSize = data.length > 3 && (boolean) data[3];

            Image img = images.get(name);
            if (img != null) {
                int w = fullSize ? comp.getWidth() : img.getWidth(comp);
                int h = fullSize ? comp.getHeight() : img.getHeight(comp);
                g.drawImage(img, x, y, w, h, comp);
            }
        }
    }
}
