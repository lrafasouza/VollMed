package br.com.lrafasouza.VollMed.dto.Medico;

import br.com.lrafasouza.VollMed.models.Medico.Especialidades;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record MedicoDTO(

	    @NotNull(message = "CRM é obrigatório")
	    @NotBlank(message = "CRM não pode ser vazio")
	    String crm,

	    @NotNull(message = "Nome é obrigatório")
	    @NotBlank(message = "Nome não pode ser vazio")
	    String nome,

	    @NotNull(message = "E-mail é obrigatório")
	    @Email(message = "E-mail deve ser válido")
	    String email,

	    @NotNull(message = "Telefone é obrigatório")
	    @NotBlank(message = "Telefone não pode ser vazio")
	    String telefone,

	    @NotNull(message = "Especialidade é obrigatória")
	    Especialidades especialidades,

	    @NotNull(message = "CEP é obrigatório")
	    @NotBlank(message = "CEP não pode ser vazio")
	    String cep

	) {
	}
