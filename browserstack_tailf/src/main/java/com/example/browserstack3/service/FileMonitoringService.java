package com.example.browserstack3.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.RandomAccessFile;
import java.nio.file.*;
import java.util.concurrent.TimeUnit;
import java.util.logging.Logger;

@Service
public class FileMonitoringService {

    private static final Logger LOGGER = Logger.getLogger(FileMonitoringService.class.getName());

    private long noOfLastRead = 0, lineCounter=0;

    private File file;
    private Boolean isServiceStop = false;


//    @Autowired
//    SimpMessagingTemplate simpMessagingTemplate;
//
    @Autowired
    MessagingService messagingService;

//    @Override
    public void monitor() {
//        this.file = file;
        try (WatchService watcher = FileSystems.getDefault().newWatchService()) {
            Path path = file.toPath().getParent();
            path.register(watcher, StandardWatchEventKinds.ENTRY_MODIFY);
            while (!isServiceStopped()) {
                WatchKey key;
                try { key = watcher.poll(25, TimeUnit.MILLISECONDS); }
                catch (InterruptedException e) { return; }
                if (key == null) { continue; }

                for (WatchEvent<?> event : key.pollEvents()) {
                    WatchEvent.Kind<?> kind = event.kind();

                    if (kind == StandardWatchEventKinds.OVERFLOW) {
                        continue;
                    } else if (kind == StandardWatchEventKinds.ENTRY_MODIFY) {
                        action();
                    }
                    boolean valid = key.reset();
                    if (!valid) { break; }
                }
            }
        } catch (Exception e) {
            System.out.println(e);
        }
    }

//    @Override
    public void stopMonitor() {
        isServiceStop = true;
    }

    public void setFile(File file){
        this.file = file;
    }

//    @Override
    public void action() {


//        messagingService.sendMessage("hello");

        try {
            long fileLength = file.length();
            if (fileLength > noOfLastRead) {
                RandomAccessFile readWriteFileAccess = new RandomAccessFile(file, "r");
                readWriteFileAccess.seek(noOfLastRead);
                String line = null;
                while ((line = readWriteFileAccess.readLine()) != null) {
                    messagingService.sendMessage(line);
                    System.out.println(line);
                    lineCounter++;
                }
                noOfLastRead = readWriteFileAccess.getFilePointer();
                readWriteFileAccess.close();
            }
        } catch (Exception e) {
            System.out.println(e);
        }
    }


    private boolean isServiceStopped() {
        return isServiceStop;
    }
}
