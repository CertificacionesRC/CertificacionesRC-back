package com.unicauca.backend_registro_calificado.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class ObservacionRegistroCalificado {

    private Integer observacion_registro_calificado_id;

    private String observacion;

    @JsonIgnore
    private RegistroCalificadoDTO registro_calificado_id;

}
