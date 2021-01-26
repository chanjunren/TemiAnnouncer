package com.robosolutions.temiannouncer.google;

import com.google.api.client.googleapis.javanet.GoogleNetHttpTransport;
import com.google.api.client.http.HttpRequestFactory;
import com.google.api.client.http.HttpTransport;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.JsonFactory;
import com.google.api.client.json.jackson2.JacksonFactory;
import com.google.api.services.drive.Drive;
import com.google.api.services.drive.model.File;
import com.google.api.services.drive.model.FileList;

import java.io.IOException;

public class MediaRetriever {
    private static final String APPLICATION_NAME = "Google Drive API Java Quickstart";
    private static final JsonFactory JSON_FACTORY = JacksonFactory.getDefaultInstance();
    private static final String TOKENS_DIRECTORY_PATH = "tokens";
    private NetHttpTransport HTTP_TRANSPORT;

    private Drive driveService;

    public MediaRetriever() {
        try {
            this.HTTP_TRANSPORT = GoogleNetHttpTransport.newTrustedTransport();
//            driveService = new Drive.Builder(HTTP_TRANSPORT, JSON_FACTORY, );
        } catch(Exception e) {
            System.err.println(e);
        }

    }

    public void getImgFiles() {
        try {
            String pageToken = null;
            System.out.println("getImgFiles called");
            do {
                System.out.println("doing...");
                FileList result = driveService.files().list()
                        .setQ("mimeType='image/jpeg'")
                        .setSpaces("drive")
                        .setFields("nextPageToken, files(id, name)")
                        .setPageToken(pageToken)
                        .execute();
                for (File file : result.getFiles()) {
                    System.out.printf("Found file: %s (%s)\n",
                            file.getName(), file.getId());
                }
                pageToken = result.getNextPageToken();
            } while (pageToken != null);
        } catch (Exception e) {
            System.err.println(e);
        }
    }
}
