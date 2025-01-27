package br.com.lrafasouza.VollMed.dto.Paciente;

import br.com.lrafasouza.VollMed.models.Paciente.PacienteModel;


// Visão pacientes
public record DetalhesPacienteDTO(
		
		Long id,
		String nome,
		String cpf,
		String email
		
		) {

	public DetalhesPacienteDTO(PacienteModel pacienteModel) {
this(
		 pacienteModel.getId(),
		 pacienteModel.getNome(),
		 pacienteModel.getEmail(),
         pacienteModel.getCpf()
		);
	}
}
