package br.com.lrafasouza.VollMed.dto.Medico;

import br.com.lrafasouza.VollMed.dto.EnderecoDTO;
import jakarta.validation.constraints.NotNull;

public record MedicoUpdateDTO(
		@NotNull
		Long id,
		String nome,
		String telefone,
		String cep,
		EnderecoDTO endereco
) {
	

}
