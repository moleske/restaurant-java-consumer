package com.oleske;

public class Restaurant {
    private long id;
    private String name;
    private String ownerName;
    private String headChefName;
    private String cuisineType;
    private String shortDescription;
    private String fullDescription;
    private String websiteUrl;
    private int rating;
    private int michelinStarRating;
    private int zagatRating;

    public Restaurant() {
    }

    public Restaurant(long id, String name, String ownerName, String headChefName, String cuisineType, String shortDescription, String fullDescription, String websiteUrl, int rating, int michelinStarRating, int zagatRating) {
        this.id = id;
        this.name = name;
        this.ownerName = ownerName;
        this.headChefName = headChefName;
        this.cuisineType = cuisineType;
        this.shortDescription = shortDescription;
        this.fullDescription = fullDescription;
        this.websiteUrl = websiteUrl;
        this.rating = rating;
        this.michelinStarRating = michelinStarRating;
        this.zagatRating = zagatRating;
    }

    public long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getOwnerName() {
        return ownerName;
    }

    public String getHeadChefName() {
        return headChefName;
    }

    public String getCuisineType() {
        return cuisineType;
    }

    public String getShortDescription() {
        return shortDescription;
    }

    public String getFullDescription() {
        return fullDescription;
    }

    public String getWebsiteUrl() {
        return websiteUrl;
    }

    public int getRating() {
        return rating;
    }

    public int getMichelinStarRating() {
        return michelinStarRating;
    }

    public int getZagatRating() {
        return zagatRating;
    }
}
