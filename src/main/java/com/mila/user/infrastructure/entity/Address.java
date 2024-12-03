package com.mila.user.infrastructure.entity;

import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "address")
@Builder
public class Address {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "streetline1", length = 200)
    private String streetLine1;
    @Column(name = "number", length = 50)
    private Long number;
    @Column(name = "streetline2", length = 50)
    private String streetLine2;
    @Column(name = "city", length = 150)
    private String city;
    @Column(name = "state", length = 2)
    private String state;
    @Column(name = "zipcode", length = 20)
    private String zipcode;
}
