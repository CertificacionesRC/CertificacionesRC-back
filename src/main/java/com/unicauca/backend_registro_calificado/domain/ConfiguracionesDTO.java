package com.unicauca.backend_registro_calificado.domain;

import jakarta.persistence.Column;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class ConfiguracionesDTO {
    private Integer id;

    private String nombreVariable;

    private String contenido;

}

