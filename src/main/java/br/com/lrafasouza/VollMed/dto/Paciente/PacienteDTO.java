package br.com.lrafasouza.VollMed.dto.Paciente;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record PacienteDTO(

	    @NotNull(message = "CPF é obrigatório")
	    @NotBlank(message = "CPF não pode ser vazio")
	    String cpf,

	    @NotNull(message = "Nome é obrigatório")
	    @NotBlank(message = "Nome não pode ser vazio")
	    String nome,

	    @NotNull(message = "E-mail é obrigatório")
	    @Email(message = "E-mail deve ser válido")
	    String email,

	    @NotNull(message = "Telefone é obrigatório")
	    @NotBlank(message = "Telefone não pode ser vazio")
	    String telefone,
	    
	    @NotNull(message = "CEP é obrigatório")
	    @NotBlank(message = "CEP não pode ser vazio")
	    String cep

	) {
	}