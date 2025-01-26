package br.com.lrafasouza.VollMed.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import br.com.lrafasouza.VollMed.models.Medico.MedicoModel;
import br.com.lrafasouza.VollMed.repository.MedicoRepository;
import jakarta.servlet.http.HttpServletRequest;

@Service
public class MedicoService {

	@Autowired
	private MedicoRepository medicoRepository;

	// Inicio -> Telefone
	public String validarTelefone(String telefone) {
		if (telefone == null || telefone.isEmpty()) {
			throw new IllegalArgumentException("Telefone obrigatório");
		}

		String numeroLimpo = telefone.replaceAll("[^\\d]", "");

		if (numeroLimpo.length() == 11 && numeroLimpo.charAt(2) == '9') {
			return numeroLimpo;
		} else {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Número de telefone inválido");
		}
	}
	// Fim -> Telefone

	public List<MedicoModel> getAll() {
		return medicoRepository.findAll();
	}

	public boolean existsBycrm(String crm) {
		return medicoRepository.existsBycrm(crm);

	}

	public Page<MedicoModel> findAllByAtivoTrue(Pageable pageable) {
		return medicoRepository.findAllByAtivoTrue(pageable);
	}



	public ResponseEntity<MedicoModel> salvarPessoa(MedicoModel medicoModel, HttpServletRequest request) {

		String telefone = validarTelefone(medicoModel.getTelefone());

		if (telefone == null) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Preencher Telefone");
		}

		if (medicoModel.getCrm() == null) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Preencher CRM");
		}

		if (existsBycrm(medicoModel.getCrm())) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "CRM já cadastrado");
		}

		medicoModel.setTelefone(telefone);
		MedicoModel savedMedico = medicoRepository.save(medicoModel);

		return ResponseEntity.status(HttpStatus.CREATED).body(savedMedico);
	}

}
