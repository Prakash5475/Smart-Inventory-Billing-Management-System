package com.inventory.model;
import jakarta.persistence.*; import lombok.*;
@Entity @Table(name="customers") @Getter @Setter @NoArgsConstructor @AllArgsConstructor
public class Customer {
    @Id @GeneratedValue(strategy=GenerationType.IDENTITY) private Long id;
    private String name, email, phone, address;
}