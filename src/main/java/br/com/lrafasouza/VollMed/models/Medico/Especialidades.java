package br.com.lrafasouza.VollMed.models.Medico;

import com.fasterxml.jackson.annotation.JsonCreator;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

public enum Especialidades {
    ORTOPEDIA,
    CARDIOLOGIA,
    GINECOLOGIA,
    DERMATOLOGIA;

    @JsonCreator
    public static Especialidades fromValue(String value) {
        for (Especialidades especialidade : Especialidades.values()) {
            if (especialidade.name().equalsIgnoreCase(value)) {
                return especialidade;
            }
        }
        throw new ResponseStatusException(
                HttpStatus.BAD_REQUEST,
                "Especialidade inválida: " + value + "\nEscolha uma das opções: " +
                "\n- ORTOPEDIA" +
                "\n- CARDIOLOGIA" +
                "\n- GINECOLOGIA" +
                "\n- DERMATOLOGIA"
        );
    }
}
