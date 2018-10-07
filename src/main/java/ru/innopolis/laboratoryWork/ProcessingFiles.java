package ru.innopolis.laboratoryWork;


import org.apache.log4j.Logger;

import java.net.URL;
import java.net.URLConnection;
import java.util.Scanner;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.regex.Pattern;


public class ProcessingFiles implements Runnable {

    private static final Logger LOGGER = Logger.getLogger(ProcessingFiles.class);
    private String pathSourcesFile;
    private ConcurrentLinkedQueue<String> finishList;
    private Pattern pattern;

    public ProcessingFiles(String pathSourcesFile, ConcurrentLinkedQueue finishList, Pattern pattern) {
        this.pathSourcesFile = pathSourcesFile;
        this.finishList = finishList;
        this.pattern = pattern;
    }

    public ProcessingFiles(String pathSourcesFile) {
    }

    public ConcurrentLinkedQueue<String> getFinishList() {
        return finishList;
    }

    public Pattern getPattern() {
        return pattern;
    }

    void addFoundOffers() {
        String offer;
        Scanner scanner = connection();
        if (scanner != null) {
            while (scanner.hasNext()) {
                offer = scanner.next();
                if (pattern.matcher(offer).find()) {
                    finishList.add(offer);
                }
            }
        }
    }

    Scanner connection() {
        Scanner scanner = null;
        try {
            URLConnection connection = new URL(pathSourcesFile).openConnection();
            scanner = new Scanner(connection.getInputStream()).useDelimiter("[.!?]");
        } catch (Exception e) {
            LOGGER.debug("could not read file " + pathSourcesFile);
        }
        return scanner;
    }


    @Override
    public void run() {
        addFoundOffers();
    }

}

