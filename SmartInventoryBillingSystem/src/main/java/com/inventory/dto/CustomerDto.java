package com.inventory.dto; import lombok.Data; @Data public class CustomerDto { private Long id; private String name, email, phone, address; }package com.inventory.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CustomerDto {

    private Long id;
    private String name;
    private String email;
    private String phone;
    private String address;
}