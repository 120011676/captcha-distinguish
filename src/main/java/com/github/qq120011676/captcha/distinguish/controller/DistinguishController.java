package com.github.qq120011676.captcha.distinguish.controller;

import net.sourceforge.tess4j.ITesseract;
import net.sourceforge.tess4j.Tesseract;
import net.sourceforge.tess4j.TesseractException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.imageio.ImageIO;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@RestController
public class DistinguishController {
    @PostMapping("file")
    public List<String> file(MultipartFile[] files) throws IOException, TesseractException {
        var results = new ArrayList<String>();
        ITesseract iTesseract = new Tesseract();
        for (MultipartFile file : files) {
            results.add(iTesseract.doOCR(ImageIO.read(file.getInputStream())));
        }
        return results;
    }
}
