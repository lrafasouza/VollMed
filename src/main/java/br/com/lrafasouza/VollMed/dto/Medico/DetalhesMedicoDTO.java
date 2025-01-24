package br.com.lrafasouza.VollMed.dto.Medico;

import br.com.lrafasouza.VollMed.models.Medico.Especialidades;
import br.com.lrafasouza.VollMed.models.Medico.MedicoModel;


// Record -> Lista para os clientes
public record DetalhesMedicoDTO(
		Long id,
		String crm,

		String nome,

		String email,

		String telefone,

		Especialidades especialidades) {
	
		public DetalhesMedicoDTO(MedicoModel medicoModel) {
		this(
				medicoModel.getId(),
				medicoModel.getCrm(), 
				medicoModel.getNome(),
				medicoModel.getEmail(), 
				medicoModel.getTelefone(), 
				medicoModel.getEspecialidades());
	}

}
