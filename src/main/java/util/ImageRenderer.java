package util;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.layout.Region;

import java.util.Map;

public class ImageRenderer {

    /**
     * 이미지를 캔버스에 렌더링합니다.
     *
     * @param gc         GraphicsContext (Canvas에서 가져옴)
     * @param region     크기 기준이 될 Region (예: Pane)
     * @param images     이미지 Map
     * @param imageData  [imageName, x, y, fullSize] 형식의 데이터 배열
     */
    public static void renderImages(GraphicsContext gc,
                                    Region region,
                                    Map<String, Image> images,
                                    Object[][] imageData) {
        for (Object[] data : imageData) {
            String name = (String) data[0];
            double x = ((Number) data[1]).doubleValue();
            double y = ((Number) data[2]).doubleValue();
            boolean fullSize = data.length > 3 && (boolean) data[3];

            Image img = images.get(name);
            if (img != null) {
                double w = fullSize ? region.getWidth() : img.getWidth();
                double h = fullSize ? region.getHeight() : img.getHeight();
                gc.drawImage(img, x, y, w, h);
            }
        }
    }
}

