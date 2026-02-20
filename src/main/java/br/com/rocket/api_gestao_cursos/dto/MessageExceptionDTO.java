package br.com.rocket.api_gestao_cursos.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@AllArgsConstructor
public class MessageExceptionDTO {
    private String error;
    private String component;
}
