package com.softuni.gamestore.domain.dtos;

import com.softuni.gamestore.domain.entities.Game;

import java.math.BigDecimal;
import java.time.LocalDate;

public class GameDTO {

    private String title;
    private String trailerId;
    private String imageUrl;
    private float size;
    private BigDecimal price;
    private String description;
    private LocalDate releaseDate;

    public GameDTO() {
    }

    public GameDTO(String title, String trailerId, String imageUrl, float size,
                   BigDecimal price, String description, LocalDate releaseDate) {
        setTitle(title);
        setTrailerId(trailerId);
        setImageUrl(imageUrl);
        setSize(size);
        setPrice(price);
        setDescription(description);
        this.releaseDate = releaseDate;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        if (title != null &&
        Character.isUpperCase(title.charAt(0))
        && title.length() >= 3
        && title.length() <= 100) {
            this.title = title;
        } else {
            throw new IllegalArgumentException("Not a valid game title");
        }
    }

    public String getTrailerId() {
        return trailerId;
    }

    public void setTrailerId(String trailerId) {
        if (trailerId != null && trailerId.length() == 11) {
            this.trailerId = trailerId;
        } else {
            throw new IllegalArgumentException("Trailer ID should be exactly 11.");
        }
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        if (imageUrl != null && imageUrl.startsWith("http://") || imageUrl.startsWith("https://")) {
            this.imageUrl = imageUrl;
        } else {
            throw new IllegalArgumentException("Link should begin with http ...");
        }
    }

    public float getSize() {
        return size;
    }

    public void setSize(float size) {
        if (size > 0.0) {
            this.size = size;
        } else {
            throw new IllegalArgumentException("Price or size should be positive number.");
        }
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        if (price != null && price.longValue() > 0) {
            this.price = price;
        } else {
            throw new IllegalArgumentException("Price or size should be positive number.");
        }
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        if (description != null && description.length() > 20) {
            this.description = description;
        } else {
            throw new IllegalArgumentException("Description should be at least 20 characters");
        }
    }

    public LocalDate getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(LocalDate releaseDate) {
        this.releaseDate = releaseDate;
    }

    public Game toGame() {
        return new Game (title, trailerId, imageUrl, size, price, description, releaseDate);
    }
}
