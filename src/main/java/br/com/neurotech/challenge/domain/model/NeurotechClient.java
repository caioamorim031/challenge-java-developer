package br.com.neurotech.challenge.domain.model;

import jakarta.persistence.*;
import lombok.*;

@Builder
@Getter
@Setter
@Entity
@Table(name = "client")
@AllArgsConstructor
@NoArgsConstructor
public class NeurotechClient {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private int age;
    private double income;
}
