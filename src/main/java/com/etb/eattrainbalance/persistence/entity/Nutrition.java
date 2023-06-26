package com.etb.eattrainbalance.persistence.entity;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "nutritions")
public class Nutrition {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "product")
    private String product;

    @Column(name = "calorie")
    private Double calorie;

    @Column(name = "protein")
    private Double protein;

    @Column(name = "fat")
    private Double fat;

    @CreationTimestamp
    @Column(name = "creation-date-time")
    private LocalDateTime creationDateTime; // The date and time when this entry was created

    @ManyToOne
    @JoinColumn(name = "user-id")
    private User user; // The user associated with this nutrition entry
}
