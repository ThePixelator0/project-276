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

    @Column(name="meal-type")
    private String mealType;

    @Column(name = "product")
    private String product;

    @Column(name = "calorie")
    private String calorie;

    @Column(name = "protein")
    private String protein;

    @Column(name = "fat")
    private String fat;

    @CreationTimestamp
    @Column(name = "creation-date-time")
    private LocalDateTime creationDateTime; // The date and time when this entry was created

    @Column(name = "user-id")
    private Long userId; // The user associated with this nutrition entry
}
