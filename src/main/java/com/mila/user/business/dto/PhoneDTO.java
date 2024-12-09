package com.mila.user.business.dto;

import jakarta.persistence.Column;
import lombok.*;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PhoneDTO {
    private Long id;
    private String phonenumber;
    private String ddi;
    private String ddd;
}
