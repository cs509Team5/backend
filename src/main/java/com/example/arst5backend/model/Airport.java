package com.example.arst5backend.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "airport_view")
public class Airport {
    @Id
    @Column(name = "airport_name")
    private String name;

    // private int minLayoverDurationSecond;
    // private int luggageTransferDurationSecond;
}
