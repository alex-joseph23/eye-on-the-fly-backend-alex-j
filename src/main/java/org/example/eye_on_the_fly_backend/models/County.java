package org.example.eye_on_the_fly_backend.models;
import jakarta.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
@Entity
public class County {
    @Id
    @GeneratedValue(strategy = GenrationType.IDENTITY)
    private Long id;
    private String name;
}
