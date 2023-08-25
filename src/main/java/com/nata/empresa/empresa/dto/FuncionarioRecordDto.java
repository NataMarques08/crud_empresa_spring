package com.nata.empresa.empresa.dto;

import jakarta.validation.constraints.NotBlank;

public record FuncionarioRecordDto(
    @NotBlank
    String nome,
    @NotBlank
    String cargo,
    Double salario
) {
    
}
