package com.krizsanandras.projectwob;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.opencsv.CSVWriter;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.*;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.ResourceBundle;
import java.util.Set;

public class DatabaseHelper {

    // gets database resources
    private ResourceBundle bundle = ResourceBundle.getBundle("application");
    private final String url = bundle.getString("spring.datasource.url");
    private final String user = bundle.getString("spring.datasource.username");
    private final String password = bundle.getString("spring.datasource.password");

    // HashMaps to store report data
    private Map<String, Object> reportMap = new LinkedHashMap<>();
    private Map<Object, Object> monthlyReportMap = new LinkedHashMap<>();

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

        JsonNode node = getData.getRestData("listingStatus");
        conn.setAutoCommit(false);

        int id;
        String status_name;

        for (int i = 0; i < node.size(); i++) {

            id = node.get(i).get("id").asInt();
            status_name = node.get(i).get("status_name").textValue();

            String SQL = "INSERT INTO listingstatus (id, status_name) VALUES ("
                            + id + ","
                            + "'" + status_name + "');";

            PreparedStatement statement = conn.prepareStatement(SQL);
            statement.execute();
        }
        conn.commit();
    }

    void insertListing() throws SQLException, IOException {

        JsonNode node = getData.getRestData("listing");
        conn.setAutoCommit(false);

        String marketplaceName, title, description;
        String[] entries = null;

        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        Validator validator = factory.getValidator();
        Set<ConstraintViolation<Listing>> violations;
        Listing listing = new Listing();

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

            // sets current values
            listing.setId(node.get(i).get("id").textValue());
            listing.setLocation_id(node.get(i).get("location_id").textValue());
            listing.setListing_price(node.get(i).get("listing_price").asDouble());
            listing.setQuantity(node.get(i).get("quantity").asInt());
            listing.setListing_status(node.get(i).get("listing_status").asInt());
            listing.setMarketplace(node.get(i).get("marketplace").asInt());
            listing.setTitle(node.get(i).get("title").textValue());
            listing.setDescription(node.get(i).get("description").textValue());
            listing.setCurrency(node.get(i).get("currency").textValue());
            listing.setOwner_email_address(node.get(i).get("owner_email_address").textValue());
            listing.setUpload_timeString(node.get(i).get("upload_time").textValue());

            title = listing.getTitle();
            description = listing.getDescription();

            // validate, puts violations into a Set
            violations = validator.validate(listing);

            if (listing.getMarketplace() == 1){
                marketplaceName = "Ebay";
            }
            else marketplaceName = "Amazon";

            // replace title and description in order to insert correctly
            if (listing.getTitle().contains("'")) {
                title = listing.getTitle().replaceAll("'", "''");
            }
            if (listing.getDescription().contains("'")) {
                description = listing.getDescription().replaceAll("'", "''");
            }

            // iterate through the violations, writes them into importLog.csv
            if (violations.size() != 0) {

                for (ConstraintViolation<Listing> violation : violations) {

                    entries = new String[]{listing.getId(), marketplaceName, violation.getMessage()};
                    csvWriter.writeNext(entries);
                }
                violations.clear();
            }

            else{
                String SQL = "INSERT INTO listing (id, title, description, location_id, listing_price, currency," +
                        " quantity, listing_status, marketplace, upload_time, owner_email_address) VALUES ("
                        + "'" + listing.getId() + "', "
                        + "'" + title + "', "
                        + "'" + description + "', "
                        + "'" + listing.getLocation_id() + "', "
                        + listing.getListing_price() + ", "
                        + "'" + listing.getCurrency() + "', "
                        + listing.getQuantity() + ", "
                        + listing.getListing_status() + ", "
                        + listing.getMarketplace() + ", "
                        + "to_date('" + listing.getUpload_timeString() + "', 'MM/DD/YYYY'), "
                        + "'" + listing.getOwner_email_address() + "');";

                PreparedStatement statement = conn.prepareStatement(SQL);
                statement.execute();
            }
        }
        conn.commit();
        csvWriter.close();

        System.out.println("Data inserted successfully into database.");
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

                reportMap.put("Total Ebay listing count: ", resultSet.getInt(1));
                reportMap.put("Total Ebay listing price: ", resultSet.getDouble(2));
                reportMap.put("Average Ebay listing price: ", resultSet.getDouble(3));

                totalListingCount = resultSet.getInt(1);

                resultSet.next();

                // adds the two count, instead of another query
                totalListingCount = totalListingCount + resultSet.getInt(1);

                reportMap.put("Total Amazon listing count: ", resultSet.getInt(1));
                reportMap.put("Total Amazon listing price: ", resultSet.getDouble(2));
                reportMap.put("Average Amazon listing price: ", resultSet.getDouble(3));

                reportMap.put("Total listing count: ", totalListingCount);

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
                reportMap.put("Best lister's email address: ", resultSet.getString(1));
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
                monthlyReportMap.put((resultSet.getString(1) + ". " + resultSet.getString(2) +
                        ". month's total " + market + " listing count: "), resultSet.getInt(3));
                monthlyReportMap.put((resultSet.getString(1) + ". " + resultSet.getString(2) +
                        ". month's total " + market + " listing price: "), resultSet.getDouble(4));
                monthlyReportMap.put((resultSet.getString(1) + ". " + resultSet.getString(2) +
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

                monthlyReportMap.put(resultSet.getString(1) + ". " + resultSet.getString(2) + ". month's" +
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
            reportMap.put("Monthly report: ", monthlyReportMap);
            mapper.writerWithDefaultPrettyPrinter().writeValue(reportFile, reportMap);
            System.out.println("The report file has been created.");

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}