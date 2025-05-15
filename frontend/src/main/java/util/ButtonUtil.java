package util;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.util.List;
import java.util.Map;

public class ButtonUtil {

    /**
     * 이미지 기반 버튼 생성
     */
    public static Button createImageButton(Image image, EventHandler<ActionEvent> handler) {
        ImageView imageView = new ImageView(image);
        Button button = new Button("", imageView);
        button.setStyle("-fx-background-color: transparent;");
        button.setOnAction(handler);
        return button;
    }

    /**
     * 이미지 이름이 존재할 경우 버튼 생성 (없으면 null 반환)
     */
    public static Button createButtonIfExists(List<String> imageNames, String imageName,
                                              String folderName, double x, double y,
                                              EventHandler<ActionEvent> handler,
                                              Map<String, Image> imageMap) {
        if (!imageNames.contains(imageName)) return null;

        Image image = imageMap.get(imageName);
        if (image == null) return null;

        Button button = createImageButton(image, handler);
        button.setLayoutX(x);
        button.setLayoutY(y);
        return button;
    }
}
