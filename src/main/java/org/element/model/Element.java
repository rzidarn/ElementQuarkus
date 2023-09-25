package org.element.model;

import io.quarkus.hibernate.reactive.panache.PanacheEntity;
import jakarta.persistence.*;
import org.hibernate.annotations.CreationTimestamp;

import java.time.ZonedDateTime;

@Entity
@Table(
        name = "elements",
        uniqueConstraints = {
                @UniqueConstraint(columnNames = {"description", "user_id"})  // constraint on User.java
        }
)
public class Element extends PanacheEntity {

    @Column(nullable = false)
    public String description;

    @ManyToOne(optional = false)
    public User user;

    @CreationTimestamp
    @Column(updatable = false, nullable = false)
    public ZonedDateTime created;

    @Version
    public int version;
}
