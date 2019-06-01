package com.krizsanandras.projectwob;

import javax.validation.constraints.*;

public class Listing {

    // annotations for the validation
    @NotNull(message = "id")
    private String id;

    @NotNull(message = "title")
    private String title;

    @NotNull(message = "description")
    private String description;

    @NotNull(message = "location_id")
    private String location_id;

    @NotNull(message = "listing_price")
    @Positive(message = "listing_price")
    @Digits(integer = 999999999, fraction = 2, message = "listing_price")
    private double listing_price;

    @NotNull(message = "currency")
    @Size(min = 3, max = 3, message = "currency")
    private String currency;

    @NotNull(message = "quantity")
    @Positive(message = "quantity")
    private int quantity;

    @NotNull(message = "listing_status")
    @Positive(message = "listing_status")
    private int listing_status;

    @NotNull(message = "marketplace")
    @Positive(message = "marketplace")
    private int  marketplace;

    @NotNull(message = "owner_email_address")
    @Email(message = "owner_email_address")
    private String owner_email_address;

    @NotNull(message = "upload_time")
    private String upload_timeString;

    // getters, setters
    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public int getListing_status() {
        return listing_status;
    }

    public void setListing_status(int listing_status) {
        this.listing_status = listing_status;
    }

    public int getMarketplace() {
        return marketplace;
    }

    public void setMarketplace(int marketplace) {
        this.marketplace = marketplace;
    }

    public double getListing_price() {
        return listing_price;
    }

    public void setListing_price(double listing_price) {
        this.listing_price = listing_price;
    }

    public String getOwner_email_address() {
        return owner_email_address;
    }

    public void setOwner_email_address(String owner_email_address) {
        this.owner_email_address = owner_email_address;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getLocation_id() {
        return location_id;
    }

    public void setLocation_id(String location_id) {
        this.location_id = location_id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public String getUpload_timeString() {
        return upload_timeString;
    }

    public void setUpload_timeString(String upload_timeString) {
        this.upload_timeString = upload_timeString;
    }
}
