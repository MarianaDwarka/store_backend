package com.marianadwarka.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Entity//(name = "category_entity")
//@Table(name = "tbl_category", schema = "sistemas")
public class Category {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    private Integer idCategory; //camelCase -> lowerCamelCase / DB: snake _

    @Column(nullable = false, length = 50)
    private String name;

    @Column(nullable = false, length = 50)
    private String description;

    @Column(nullable = false)
    private boolean enabled;

    public Category(String name, boolean enabled) {
        this.name = name;
        this.enabled = enabled;
    }
}