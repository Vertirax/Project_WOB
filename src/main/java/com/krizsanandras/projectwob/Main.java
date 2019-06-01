package com.krizsanandras.projectwob;

import java.io.IOException;
import java.sql.SQLException;

public class Main {
    public static void main(String[] argv) throws SQLException, IOException {

        DatabaseHelper command = new DatabaseHelper();

        // INSERTs to database
        command.insertMarketplace();
        command.insertListingStatus();
        command.insertLocation();
        command.insertListing();

        // SELECTs from database for report
        command.selectTotalReportData();
        command.selectBestListerEmail();
        command.selectMonthlyReportData();
        command.selectMonthlyBestListerEmail();

        // writes the report
        command.writeReport();

        // uploads the file
        UploadToFTP upload = new UploadToFTP();
        upload.uploadReport("report.json");

    }
}