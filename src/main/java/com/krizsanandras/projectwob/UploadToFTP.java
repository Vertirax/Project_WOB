package com.krizsanandras.projectwob;

import org.apache.commons.net.ftp.FTPClient;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

public class UploadToFTP {
    public void uploadReport(String filename) throws FileNotFoundException {

        FTPClient client = new FTPClient();
        FileInputStream fis = new FileInputStream(filename);

        try {
            client.connect("ftp.dlptest.com");
        } catch (IOException e) {
            e.printStackTrace();
        }

        try {
            client.login("dlpuser@dlptest.com", "5p2tvn92R0di8FdiLCfzeeT0b");
        } catch (IOException e) {
            e.printStackTrace();
        }

        try {
            client.storeFile(filename, fis);
            System.out.println("Report file uploaded successfully.");
        } catch (IOException e) {
            e.printStackTrace();
        }

        try {
            client.logout();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
