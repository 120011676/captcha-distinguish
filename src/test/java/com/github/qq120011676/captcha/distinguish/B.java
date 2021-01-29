package com.github.qq120011676.captcha.distinguish;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;

public class B {
    public static void main(String[] args) throws IOException {
        String filepath = "/Users/say/Downloads/52JW.png";
        String newFilepath = "/Users/say/Downloads/test-n.png";

        BufferedImage img = ImageIO.read(new File(filepath));
        int width = img.getWidth();
        int height = img.getHeight();
        var corsMap = new HashMap<Integer, Integer>(45);
        for (int w = 0; w < width; w++) {
            for (int h = 0; h < height; h++) {
                var rgb = img.getRGB(w, h);
                var count = 0;
                if (corsMap.containsKey(rgb)) {
                    count = corsMap.get(rgb);
                }
                corsMap.put(rgb, ++count);
            }
        }
        System.out.println(corsMap.size());
        System.out.println(corsMap);
        var colors = new ArrayList<>(corsMap.keySet());
        colors.sort(Comparator.comparingInt(corsMap::get));
        Collections.reverse(colors);
        System.out.println(colors);
        int[] codeColors = new int[]{colors.get(1), colors.get(2), colors.get(3), colors.get(4)};
        var codeColorsMap = new HashMap<>(codeColors.length);
        for (int codeColor : codeColors) {
            codeColorsMap.put(codeColor, null);
        }
        for (int w = 0; w < width; w++) {
            for (int h = 0; h < height; h++) {
                var rgb = img.getRGB(w, h);
                if (!codeColorsMap.containsKey(rgb)) {
                    img.setRGB(w, h, Color.WHITE.getRGB());
                } else {
                    img.setRGB(w, h, Color.black.getRGB());
                }
            }
        }
        System.out.println(Color.black.getRGB());
        File file = new File(newFilepath);
//        if (!file.exists()) {
//            File dir = file.getParentFile();
//            if (!dir.exists()) {
//                dir.mkdirs();
//            }
//            file.createNewFile();
//        }
        ImageIO.write(img, "png", file);
    }
}
