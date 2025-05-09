package swing.util;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class File {
    public static List<String> getFileName(String screenName) {
        List<String> imageNames = new ArrayList<>();
        URL resource = File.class.getResource("/" + screenName);
        if(resource != null) {
            java.io.File folder = new java.io.File(resource.getPath());  // 이미지가 위치한 폴더
            java.io.File[] files = folder.listFiles((dir, name) -> name.endsWith(".png"));
            if(files != null) {
                for (java.io.File file : files) {
                    imageNames.add(file.getName());
                }
            } else {
                System.out.println("File not found");
            }
        }
        return imageNames;
    }

    public static Map<String, BufferedImage> imageLoading(List<String> imageNames, String screenName) {
        Map<String, BufferedImage> images = new HashMap<>();
        try {
            for (String imageName : imageNames) {
                String imagePath = screenName + "/" + imageName;
                URL imageUrl = File.class.getClassLoader().getResource(imagePath);
                if (imageUrl != null) {
                    BufferedImage img = ImageIO.read(imageUrl);
                    images.put(imageName, img); // 이미지 이름으로 저장
                } else {
                    System.err.println("이미지를 찾을 수 없습니다: " + imagePath);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return images;
    }
}
