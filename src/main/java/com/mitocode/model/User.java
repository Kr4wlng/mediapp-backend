package com.mitocode.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Table(name = "user_data")
public class User {

    @Id
    @EqualsAndHashCode.Include
    private Integer idUser;

    @Column(nullable = false, length = 60, unique = true)
    private String username;

    @Column(nullable = false, length = 60)
    private String password;

    @Column(nullable = false)
    private boolean enabled;

}
