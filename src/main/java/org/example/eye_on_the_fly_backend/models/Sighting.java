package org.example.eye_on_the_fly_backend.models;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;

import java.util.Objects;

@Entity
public class Sighting {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String location;
    private String date;
    private String time;
    private String imageUrl;
    private String description;

    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.PERSIST)
    @JoinColumn(name = "county_id")
    @JsonIgnoreProperties({"sightings"})
    private County county;

    public Sighting() {}

    public Sighting(String location, String date, String time, String imageUrl, String description) {
        this.location = location;
        this.date = date;
        this.time = time;
        this.imageUrl = imageUrl;
        this.description = description;
    }
    public Long getId() {
        return id;
    }

    public String getLocation() {
        return location;

    }
    public void setLocation(String location) {
        this.location = location;
    }
    public String getDate() {
        return date;
    }
    public void setDate(String date) {
        this.date = date;
    }
    public String getTime() {
        return time;
    }
    public void setTime(String time) {
        this.time = time;
    }
    public String getImageUrl() {
        return imageUrl;
    }
    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }
    public String getDescription() {
        return description;
    }
    public void setDescription(String description) {
        this.description = description;
    }

    public County getCounty() {
        return county;
    }
    public void setCounty(County county) {
        this.county = county;}
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Sighting sighting)) return false;
        return Objects.equals(id, sighting.id);
    }
    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
    @Override
    public String toString() {
        return "Sighting{" +
                "id=" + id +
                ", location='" + location + '\'' +
                ", date='" + date + '\'' +
                ", time='" + time + '\'' +
                ", imageUrl='" + imageUrl + '\'' +
                ", description='" + description + '\'' +
                '}';
    }

}
