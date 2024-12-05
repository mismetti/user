package com.mila.user.business.dto;

import jakarta.persistence.Column;
import lombok.*;


@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AddressDTO {

    private Long id;
    private String streetLine1;
    private Long number;
    private String streetLine2;
    private String city;
    private String state;
    private String zipcode;
}
