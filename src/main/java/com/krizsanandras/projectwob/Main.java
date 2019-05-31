package com.krizsanandras.projectwob;

import java.io.IOException;
import java.sql.SQLException;

public class Main {
    public static void main(String[] argv) throws SQLException, IOException {

        long startTime = System.nanoTime();

        DatabaseHelper command = new DatabaseHelper();
        //command.insertMarketplace();
        //command.insertListingStatus();
        //command.insertLocation();

        command.insertListing();
        command.insertListing();

        command.selectTotalReportData();
        command.selectBestListerEmail();
        command.selectMonthlyReportData();
        command.selectMonthlyBestListerEmail();

        command.writeReport();

        UploadToFTP upload = new UploadToFTP();
        upload.uploadReport("report.json");

        long endTime = System.nanoTime();
        long totalTime = endTime - startTime;
        System.out.println(totalTime / 1000000 );

    }
}