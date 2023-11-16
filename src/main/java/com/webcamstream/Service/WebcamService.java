package com.webcamstream.Service;

import com.github.sarxos.webcam.Webcam;
import org.springframework.stereotype.Service;
import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;

@Service
public class WebcamService {

    private final Webcam webcam;

    public WebcamService() {
        this.webcam = Webcam.getDefault();
        this.webcam.open();
    }

    public byte[] captureImage() {
        try {
            BufferedImage image = webcam.getImage();
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            ImageIO.write(image, "PNG", baos);
            return baos.toByteArray();
        } catch (IOException e) {
            throw new RuntimeException("Webcam Cant Capture Image", e);
        }
    }
}