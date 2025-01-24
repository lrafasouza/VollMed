package br.com.lrafasouza.VollMed.repository;


import org.springframework.data.jpa.repository.JpaRepository;

import br.com.lrafasouza.VollMed.models.Medico.MedicoModel;

public interface MedicoRepository extends JpaRepository<MedicoModel, Long>{

	
	boolean existsBycrm(String crm);
}
