package com.mitocode.dto;

import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PatientDTO {

    private Integer idPatient;

    @NotNull
    // @NotEmpty
    // @NotBlank
    @Size(min = 3, max = 70)
    private String firstName;

    @NotNull
    @Size(min = 3, max = 70)
    private String lastName;

    @NotNull
    private String dni;

    @NotNull
    private String address;

    @NotNull
    @Pattern(regexp = "[0-9]+")
    private String phone;

    @NotNull
    @Email // Valida si lo que envias se trata de un correo electrónico
    private String email;

    /* @Max(value = 999)
    @Min(value = 1)
    private int age; */

}
