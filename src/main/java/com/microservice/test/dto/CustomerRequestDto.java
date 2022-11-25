package com.microservice.test.dto;

import com.microservice.test.constant.GenericConstant;
import lombok.Data;

import javax.validation.constraints.*;

@Data
public class CustomerRequestDto {
    private Integer idCustomer;

    private Integer idPerson;

    @NotBlank(message = "El nombre es requerido.")
    @NotNull(message = "El nombre es requerido.")
    private String name;

    @NotBlank(message = "La dirección es requerida.")
    @NotNull(message = "La dirección es requerida.")
    private String address;

    @NotNull(message = "El teléfono es requerido.")
    @Pattern(message = "El teléfono debe ser de 10 digitos.", regexp = "^\\d{10}$")
    private String phone;

    @NotNull(message = "La contraseña es requerida.")
    @Size(message = "La contraseña es {min} y {max} caracteres.", min = 0, max = 50)
    private String password;

    @NotNull(message = "El estado es requerido.")
    private Boolean state = GenericConstant.ACTIVE_STATE;

    @NotNull(message = "El género es requerido.")
    @Pattern(message = "El género sólo puede ser la letra inicial de Masculino o Femenino.", regexp = "[M,F]")
    private String gender;

    @Min(message = "La edad mínima es de al menos {value} años.", value = 0)
    @Max(message = "La edad máxima es de {value} años.", value = 150)
    @NotNull(message = "La edad es requerida.")
    private Integer age;

    @NotNull(message = "La identificación es requerida.")
    @Pattern(message = "La identificación debe de ser de 13 carácteres alfanúmericos.", regexp = "[a-zA-Z0-9]{13}")
    private String identification = GenericConstant.DEFAULT_STRING;
}
