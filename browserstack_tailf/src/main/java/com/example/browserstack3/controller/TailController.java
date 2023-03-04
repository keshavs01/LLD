package com.example.browserstack3.controller;

import com.example.browserstack3.service.FileMonitoringService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;

import java.io.File;

@Controller
public class TailController {

    private String fileLocation = "/home/singh/Desktop/browserstack3/src/main/resources/logs.txt";


    @Autowired
    FileMonitoringService fileMonitoringService;
//
//    TailController(){
//        fileMonitoringService.monitor(new File(fileLocation));
//    }

    @MessageMapping("/hello")
    @SendTo("/topic/greetings")
    public String getLogs(Object obj){
        File file = new File(fileLocation);
        fileMonitoringService.setFile(file);
        fileMonitoringService.action();
        fileMonitoringService.monitor();
        return "Hello"+obj;
    }


}
