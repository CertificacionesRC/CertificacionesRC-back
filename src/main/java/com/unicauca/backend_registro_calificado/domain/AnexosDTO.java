package com.unicauca.backend_registro_calificado.domain;

import com.unicauca.backend_registro_calificado.model.RegistroCalificado;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class AnexosDTO {
    private String anexoId;

    private String anContenido;

    private RegistroCalificadoDTO registroCalificadoIdDTO;



}

