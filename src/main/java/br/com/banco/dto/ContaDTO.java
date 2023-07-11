package br.com.banco.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ContaDTO {
    private Long id;
    private String nomeResponsavel;
}
