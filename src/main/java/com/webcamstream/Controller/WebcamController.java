package com.webcamstream.Controller;

import com.webcamstream.Service.WebcamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Controller;


@Controller
public class WebcamController {

    @Autowired
    private SimpMessagingTemplate template;

    @Autowired
    private WebcamService webcamService;

    @Scheduled(fixedDelay = 1000)
    public void broadcastWebcamImage() {
            byte[] image = webcamService.captureImage();
            template.convertAndSend("/topic/webcam-image", image);
    }

}