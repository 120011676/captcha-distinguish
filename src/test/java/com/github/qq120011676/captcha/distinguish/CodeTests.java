package com.github.qq120011676.captcha.distinguish;

import net.sourceforge.tess4j.ITesseract;
import net.sourceforge.tess4j.Tesseract;
import net.sourceforge.tess4j.TesseractException;

import java.io.File;
import java.util.ArrayList;

public class CodeTests {
    public static void main(String[] args) {
        var filenames = new ArrayList<String>();
        filenames.add("52JW.jpeg");
        filenames.add("D11X.jpeg");
        filenames.add("DUYA.jpeg");
        String path = "code/";
        ITesseract instance = new Tesseract();
        filenames.forEach(o -> {
            try {
                String result = instance.doOCR(new File(path + o));
                String code = o.substring(0, o.indexOf("."));
                System.out.println(code.equals(result) + "=" + result + ":" + o);
            } catch (TesseractException e) {
                e.printStackTrace();
            }
        });
    }
}
