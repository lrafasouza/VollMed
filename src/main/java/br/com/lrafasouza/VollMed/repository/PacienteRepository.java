package br.com.lrafasouza.VollMed.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.lrafasouza.VollMed.models.Paciente.PacienteModel;

public interface PacienteRepository extends JpaRepository<PacienteModel, Long>{

	PacienteModel findByCpf(String documento);
	
}
