package com.krizsanandras.projectwob;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.opencsv.CSVWriter;

import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.*;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.ResourceBundle;

public class DatabaseHelper {

    private ResourceBundle bundle = ResourceBundle.getBundle("application");
    private final String url = bundle.getString("spring.datasource.url");
    private final String user = bundle.getString("spring.datasource.username");
    private final String password = bundle.getString("spring.datasource.password");

    private Map<String, Object> map = new LinkedHashMap<>();
    private Map<Object, Object> monthlyMap = new LinkedHashMap<>();

    private Connection conn = connect();
    private GetData getData = new GetData();

    public DatabaseHelper() throws SQLException {
    }

    private Connection connect() throws SQLException {
        return DriverManager.getConnection(url, user, password);
    }

    // inserts to database
    void insertMarketplace() throws SQLException {

        JsonNode node = getData.getRestData("marketplace");

        conn.setAutoCommit(false);

        int id;
        String marketplace_name;

        for (int i = 0; i < node.size(); i++) {
            id = node.get(i).get("id").asInt();
            marketplace_name = node.get(i).get("marketplace_name").textValue();

            String SQL = "INSERT INTO marketplace(id, marketplace_name) VALUES (" + id + ","
                    + "'" + marketplace_name + "');";

            PreparedStatement statement = conn.prepareStatement(SQL);
            statement.execute();
        }
        conn.commit();
    }

    void insertLocation() throws SQLException {

        JsonNode node = getData.getRestData("location");
        conn.setAutoCommit(false);

        String id, manager_name, phone, address_primary, address_secondary, country, town, postal_code;

        for (int i = 0; i < node.size(); i++) {

            id = node.get(i).get("id").textValue();
            manager_name = node.get(i).get("manager_name").textValue();
            phone = node.get(i).get("phone").textValue();
            address_primary = node.get(i).get("address_primary").textValue();
            address_secondary = node.get(i).get("address_secondary").textValue();
            country = node.get(i).get("country").textValue();
            town = node.get(i).get("town").textValue();
            postal_code = node.get(i).get("postal_code").textValue();

            String SQL = "INSERT INTO location(id, manager_name, phone, address_primary, address_secondary," +
                    " country, town, postal_code) VALUES ("
                    + "'" + id + "',"
                    + "'" + manager_name + "',"
                    + "'" + phone + "'" + ","
                    + "'" + address_primary + "',"
                    + "'" + address_secondary + "',"
                    + "'" + country + "',"
                    + "'" + town + "',"
                    + "'" + postal_code + "');";

            PreparedStatement statement = conn.prepareStatement(SQL);
            statement.execute();
        }
        conn.commit();
    }

    void insertListingStatus() throws SQLException {

        String getTable = "listingStatus";
        JsonNode node = getData.getRestData(getTable);
        conn.setAutoCommit(false);

        int id;
        String status_name;

        for (int i = 0; i < node.size(); i++) {

            id = node.get(i).get("id").asInt();
            status_name = node.get(i).get("status_name").textValue();

            String SQL = "INSERT INTO " + getTable + "(id, status_name) VALUES (" + id + ","
                    + "'" + status_name + "');";

            PreparedStatement statement = conn.prepareStatement(SQL);
            statement.execute();
        }
        conn.commit();
    }

    void insertListing() throws SQLException, IOException {

        JsonNode node = getData.getRestData("listing");
        conn.setAutoCommit(false);

        int quantity, listing_status, marketplace;
        double listing_price;
        String id, location_id, title, description, currency, owner_email_address, upload_timeString, marketplaceName;
        String[] entries = null;

        // writes header string if importLog.csv does not exist
        if (!(new File("importLog.csv").isFile())) {
            entries = new String[]{"ListingId", "MarketplaceName", "InvalidField"};
        }
        CSVWriter csvWriter = new CSVWriter(new FileWriter("importLog.csv", true), ';',
                CSVWriter.NO_QUOTE_CHARACTER,
                CSVWriter.DEFAULT_ESCAPE_CHARACTER,
                CSVWriter.DEFAULT_LINE_END);

        csvWriter.writeNext(entries);

        // validation and insert
        for (int i = 0; i < node.size(); i++) {

            //gets current value
            id = node.get(i).get("id").textValue();
            location_id = node.get(i).get("location_id").textValue();
            listing_price = node.get(i).get("listing_price").asDouble();
            quantity = node.get(i).get("quantity").asInt();
            listing_status = node.get(i).get("listing_status").asInt();
            marketplace = node.get(i).get("marketplace").asInt();
            title = node.get(i).get("title").textValue();
            description = node.get(i).get("description").textValue();
            currency = node.get(i).get("currency").textValue();
            owner_email_address = node.get(i).get("owner_email_address").textValue();
            upload_timeString = node.get(i).get("upload_time").textValue();

            // replace in order to insert correctly
            if (title.contains("'")) {
                title = title.replaceAll("'", "''");
            }
            if (description.contains("'")) {
                description = description.replaceAll("'", "''");
            }

            if (marketplace == 1){
                marketplaceName = "Ebay";
            }
            else marketplaceName = "Amazon";

            //email validation
            boolean emailIsValid = false;
            InternetAddress emailAddr = new InternetAddress();
            try {
                emailAddr = new InternetAddress(owner_email_address);
            } catch (AddressException e) {
                // not needed to be printed out
                // e.printStackTrace();
            }
            try {
                emailAddr.validate();
                emailIsValid = true;

            } catch (AddressException e) {
                //e.printStackTrace();
            }

            //validate everything
            if (upload_timeString == null || listing_status == 0 || listing_price <= 0 || !emailIsValid) {
                if (upload_timeString == null) {
                    entries = new String[]{id, marketplaceName, "upload_time"};
                    csvWriter.writeNext(entries);
                } else if (listing_status == 0) {
                    entries = new String[]{id, marketplaceName, "listing_status"};
                    csvWriter.writeNext(entries);
                } else if (listing_price <= 0) {
                    entries = new String[]{id, marketplaceName, "listing_price"};
                    csvWriter.writeNext(entries);
                } else if (!emailIsValid) {
                    entries = new String[]{id, marketplaceName, "owner_email_address"};
                    csvWriter.writeNext(entries);
                }
            } else {
                String SQL = "INSERT INTO listing (id, title, description, location_id, listing_price, currency," +
                        " quantity, listing_status, marketplace, upload_time, owner_email_address) VALUES ("
                        + "'" + id + "', "
                        + "'" + title + "', "
                        + "'" + description + "', "
                        + "'" + location_id + "', "
                        + listing_price + ", "
                        + "'" + currency + "', "
                        + quantity + ", "
                        + listing_status + ", "
                        + marketplace + ", "
                        + "to_date('" + upload_timeString + "', 'MM/DD/YYYY'), "
                        + "'" + owner_email_address + "');";

                PreparedStatement statement = conn.prepareStatement(SQL);
                statement.execute();
            }
        }
        conn.commit();
        csvWriter.close();
    }

    // selects for report
    void selectTotalReportData() {

        String SQL = "SELECT count(id), sum(listing_price), round(avg(listing_price), 3) " +
                     "FROM listing group by marketplace ORDER BY marketplace;";

        try (
                PreparedStatement statement = conn.prepareStatement(SQL)) {
            ResultSet resultSet = statement.executeQuery();

            int totalListingCount;

            while (resultSet.next()) {

                map.put("Total Ebay listing count: ", resultSet.getInt(1));
                map.put("Total Ebay listing price: ", resultSet.getDouble(2));
                map.put("Average Ebay listing price: ", resultSet.getDouble(3));

                totalListingCount = resultSet.getInt(1);

                resultSet.next();

                // adds the two count, instead of another query
                totalListingCount = totalListingCount + resultSet.getInt(1);

                map.put("Total Amazon listing count: ", resultSet.getInt(1));
                map.put("Total Amazon listing price: ", resultSet.getDouble(2));
                map.put("Average Amazon listing price: ", resultSet.getDouble(3));

                map.put("Total listing count: ", totalListingCount);

            }
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
    }

    void selectBestListerEmail() {

        String SQL = "SELECT mode() WITHIN GROUP (ORDER BY owner_email_address) FROM listing;";

        try (
                PreparedStatement statement = conn.prepareStatement(SQL)) {
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                //System.out.println("Best lister's email address: " + resultSet.getString(1));
                map.put("Best lister's email address: ", resultSet.getString(1));
            }

        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
    }

    // selecting monthly reports
    void selectMonthlyReportData() {

        String SQL = "SELECT extract(YEAR FROM upload_time), extract(MONTH FROM upload_time), count(id)," +
                " sum(listing_price), round(avg(listing_price), 3), marketplace" +
                " FROM listing GROUP BY extract(YEAR FROM upload_time), extract(MONTH FROM upload_time), marketplace" +
                " ORDER BY marketplace, extract(YEAR FROM upload_time), extract(MONTH FROM upload_time);";

        try (
                PreparedStatement statement = conn.prepareStatement(SQL)) {
            ResultSet resultSet = statement.executeQuery();

            String market = "Ebay";
            while (resultSet.next()) {

                if (resultSet.getInt(6) == 2) {
                    market = "Amazon";
                }
            /*
            System.out.println(resultSet.getString(1) + "., " + resultSet.getString(2) + ". month's total " + market + " listing count: " + resultSet.getString(3));
             */
                monthlyMap.put((resultSet.getString(1) + ". " + resultSet.getString(2) +
                        ". month's total " + market + " listing count: "), resultSet.getInt(3));
                monthlyMap.put((resultSet.getString(1) + ". " + resultSet.getString(2) +
                        ". month's total " + market + " listing price: "), resultSet.getDouble(4));
                monthlyMap.put((resultSet.getString(1) + ". " + resultSet.getString(2) +
                        ". month's average " + market + " listing price: "), resultSet.getDouble(5));
            }
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
    }

    void selectMonthlyBestListerEmail() {

        String SQL = "SELECT extract(YEAR FROM upload_time), extract(MONTH FROM upload_time), mode() WITHIN GROUP (ORDER BY owner_email_address)" +
                " FROM listing GROUP BY extract(YEAR FROM upload_time), extract(MONTH FROM upload_time)" +
                " ORDER BY extract(YEAR FROM upload_time), extract(MONTH FROM upload_time);";

        try (
                PreparedStatement statement = conn.prepareStatement(SQL)) {
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                //System.out.println("Best lister's email address: " + resultSet.getString(1));
                monthlyMap.put(resultSet.getString(1) + ". " + resultSet.getString(2) + ". month's" +
                        " best lister's email address: ", resultSet.getString(3));
            }
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
    }

    void writeReport() {

        File reportFile = new File("report.json");
        ObjectMapper mapper = new ObjectMapper().enable(SerializationFeature.INDENT_OUTPUT);

        try {
            map.put("Monthly report: ", monthlyMap);
            mapper.writerWithDefaultPrettyPrinter().writeValue(reportFile, map);
            System.out.println("The report file has been created!");

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}