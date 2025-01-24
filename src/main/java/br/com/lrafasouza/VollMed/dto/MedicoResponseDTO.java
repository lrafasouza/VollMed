package br.com.lrafasouza.VollMed.dto;

import br.com.lrafasouza.VollMed.models.Medico.Especialidades;
import br.com.lrafasouza.VollMed.models.Medico.MedicoModel;


// Record -> Lista para os clientes
public record MedicoResponseDTO(
		String crm,

		String nome,

		String email,

		String telefone,

		Especialidades especialidades) {
	
		public MedicoResponseDTO(MedicoModel medicoModel) {
		this(medicoModel.getCrm(), medicoModel.getNome(), medicoModel.getEmail(), medicoModel.getTelefone(), medicoModel.getEspecialidades());
	}

}
