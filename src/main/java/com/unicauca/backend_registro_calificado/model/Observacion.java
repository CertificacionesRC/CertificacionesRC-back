package com.unicauca.backend_registro_calificado.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "observacion")
public class Observacion {
    @Id
    private String o_id;

    private String rc_id;

    private String o_observacion;

}
