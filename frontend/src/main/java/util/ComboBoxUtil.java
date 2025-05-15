package util;

import javafx.geometry.Insets;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.util.Callback;

public class ComboBoxUtil {

    public static ComboBox<String> createStyledComboBox(String[] items, double x, double y, double width, double height) {
        ComboBox<String> comboBox = new ComboBox<>();
        comboBox.getItems().addAll(items);
        comboBox.setLayoutX(x);
        comboBox.setLayoutY(y);
        comboBox.setPrefSize(width, height);

        styleComboBox(comboBox);
        comboBox.setValue(items[0]);
        return comboBox;
    }

    private static void styleComboBox(ComboBox<String> comboBox) {
        // 대체 폰트로 변경해보기 (예: Arial)
        comboBox.setStyle("-fx-font-family: 'Arial'; -fx-font-size: 30px; -fx-background-color: #C1905E; -fx-text-fill: white;");


        comboBox.setButtonCell(new CustomListCell());
        comboBox.setCellFactory(new Callback<ListView<String>, ListCell<String>>() {
            @Override
            public ListCell<String> call(ListView<String> param) {
                return new CustomListCell();
            }
        });
    }

    private static class CustomListCell extends ListCell<String> {
        @Override
        protected void updateItem(String item, boolean empty) {
            super.updateItem(item, empty);
            if (empty || item == null) {
                setText(null);
                setBackground(null);
            } else {
                setText(item);
                setFont(Font.font("나눔손글씨 붓", 18));
                if (isSelected()) {
                    setBackground(new Background(new BackgroundFill(Color.web("#A07850"), CornerRadii.EMPTY, Insets.EMPTY)));
                } else {
                    setBackground(new Background(new BackgroundFill(Color.web("#C1905E"), CornerRadii.EMPTY, Insets.EMPTY)));
                }
                setTextFill(Color.WHITE);
            }
        }
    }
}
