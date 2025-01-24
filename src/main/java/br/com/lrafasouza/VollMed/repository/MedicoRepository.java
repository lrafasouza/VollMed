package br.com.lrafasouza.VollMed.repository;


import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import br.com.lrafasouza.VollMed.models.Medico.MedicoModel;

public interface MedicoRepository extends JpaRepository<MedicoModel, Long>{

	
	Page<MedicoModel> findAllByAtivoTrue(Pageable paginacao);
	
	boolean existsBycrm(String crm);
	
	
}
