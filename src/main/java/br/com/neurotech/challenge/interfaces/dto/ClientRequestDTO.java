// Copyright (c) 2025, Your Company. All rights reserved.

package br.com.neurotech.challenge.interfaces.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ClientRequestDTO {

    @NotNull(message = "Name value cannot be null")
    @NotEmpty(message = "Name value must not be empty")
    private String name;

    @Min(value = 18, message = "Age must be at least 18")
    private Integer age;

    @PositiveOrZero
    private Double income;
}
