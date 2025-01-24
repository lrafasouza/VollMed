package br.com.lrafasouza.VollMed.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.lrafasouza.VollMed.Utils.ErrorResponse;
import br.com.lrafasouza.VollMed.dto.EnderecoDTO;
import br.com.lrafasouza.VollMed.dto.MedicoResponseDTO;
import br.com.lrafasouza.VollMed.models.Medico.MedicoModel;
import br.com.lrafasouza.VollMed.service.CEPServices;
import br.com.lrafasouza.VollMed.service.Medico.MedicoService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/vollmed/medicos")
public class MedicosController {

	@Autowired
	private CEPServices cepServices;
	@Autowired
	private MedicoService medicoService;


	// Visão para os pacientes
	@GetMapping("/lista")
	public Page<MedicoResponseDTO> listaMedicos(@PageableDefault(sort = {"nome"}) Pageable paginacao) {
		return medicoService.getAll(paginacao).map(MedicoResponseDTO::new);
	}

	// Visão para os Administradores
	@GetMapping("/lista/admin")
	public List<MedicoModel> getAll() {
		return medicoService.getAll();
	}

	@PostMapping("/criar")
	public ResponseEntity<?> create(@Valid @RequestBody MedicoModel medicoModel, HttpServletRequest request) {
	    try {
	        if (medicoModel.getCep() != null && !medicoModel.getCep().isEmpty()) {
	            EnderecoDTO endereco = cepServices.buscarCep(medicoModel.getCep());
	            medicoModel.setEndereco(endereco);
	        }
	        return medicoService.salvarPessoa(medicoModel, request);
	    } catch (IllegalArgumentException e) {
	        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ErrorResponse(e.getMessage()));
	    }
	}
}
