package com.mila.user.infrastructure.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "phone")
public class Phone {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "phonenumber", length = 10)
    private String phonenumber;
    @Column(name = "ddi", length = 4)
    private String ddi;
    @Column(name = "ddd", length = 3)
    private String ddd;

}
