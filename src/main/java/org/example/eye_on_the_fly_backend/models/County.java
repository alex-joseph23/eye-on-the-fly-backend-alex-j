package org.example.eye_on_the_fly_backend.models;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.JsonBackReference;
@Entity
public class County {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    @OneToMany(mappedBy = "county", cascade = CascadeType.ALL)
    @JsonIgnoreProperties({"county"})
    private List<Sighting> sightings = new ArrayList<>();
    public County() {}

    public County(String name) {
        this.name = name;}
    public long getId() {
        return id;
    }
    public String getName() {
        return name;}
    public void setName(String name) {
        this.name = name;}

    public List<Sighting> getSightings() {
        return sightings;}
    public void setSightings(List<Sighting> sightings) {
        this.sightings = sightings;}

@Override
public boolean equals(Object o) {
    if (this == o) return true;
    if (!(o instanceof County county)) return false;
    return Objects.equals(id, county.id);
}
@Override public int hashCode() {
    return Objects.hash(id);
}}