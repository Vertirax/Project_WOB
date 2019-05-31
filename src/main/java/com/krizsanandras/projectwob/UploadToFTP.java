package com.krizsanandras.projectwob;

import org.apache.commons.net.ftp.FTPClient;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

public class UploadToFTP {
    public void uploadReport(String filename) throws FileNotFoundException {

        FTPClient client = new FTPClient();
        //String filename = "report.json";

        FileInputStream fis = new FileInputStream(filename);

        try {
            client.connect("localhost");
        } catch (IOException e) {
            e.printStackTrace();
        }

        try {
            client.login("user", "password");
        } catch (IOException e) {
            e.printStackTrace();
        }

        try {
            client.storeFile(filename, fis);
            System.out.println("Report file uploaded successfully!");
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
