package util;

import javafx.scene.image.Image;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class File {

    public static List<String> getFileName(String screenName) {
        List<String> imageNames = new ArrayList<>();
        String listPath = "/" + screenName + "/list.txt";

        try (InputStream in = File.class.getResourceAsStream(listPath);
             BufferedReader reader = new BufferedReader(new InputStreamReader(in))) {
            String line;
            while ((line = reader.readLine()) != null) {
                if (line.endsWith(".png")) {
                    imageNames.add(line.trim());
                }
            }
        } catch (IOException | NullPointerException e) {
            System.err.println("리스트 파일을 읽을 수 없습니다: " + listPath);
            e.printStackTrace();
        }

        return imageNames;
    }

    public static Map<String, Image> imageLoading(List<String> imageNames, String screenName) {
        Map<String, Image> images = new HashMap<>();
        for (String imageName : imageNames) {
            String imagePath = "/" + screenName + "/" + imageName;
            try (InputStream imageStream = File.class.getResourceAsStream(imagePath)) {
                if (imageStream != null) {
                    Image img = new Image(imageStream);
                    images.put(imageName, img);
                } else {
                    System.err.println("이미지를 찾을 수 없습니다: " + imagePath);
                }
            } catch (IOException e) {
                System.err.println("이미지 로딩 실패: " + imagePath);
                e.printStackTrace();
            }
        }
        return images;
    }
}
