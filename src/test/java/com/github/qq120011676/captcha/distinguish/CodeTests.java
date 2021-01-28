package com.github.qq120011676.captcha.distinguish;

import net.sourceforge.tess4j.ITesseract;
import net.sourceforge.tess4j.Tesseract;
import net.sourceforge.tess4j.TesseractException;
import net.sourceforge.tess4j.util.ImageHelper;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class CodeTests {
    public static void main(String[] args) {
        var filenames = new ArrayList<String>();
        filenames.add("52JW.png");
        filenames.add("D11X.png");
        filenames.add("DUYA.png");
        filenames.add("7364.png");
        String path = "/home/say/Downloads/";
        ITesseract instance = new Tesseract();
        instance.setDatapath("/home/say/Downloads/tessdata");
        filenames.forEach(o -> {
            try {
                BufferedImage bufferedImage = ImageIO.read(new File(path, o));
                bufferedImage = ImageHelper.convertImageToGrayscale(bufferedImage);
                String result = instance.doOCR(bufferedImage).trim();
                String code = o.substring(0, o.indexOf("."));
                System.out.println(code.equals(result) + "=" + result + ":" + code);
            } catch (TesseractException | IOException e) {
                e.printStackTrace();
            }
        });
    }
}
