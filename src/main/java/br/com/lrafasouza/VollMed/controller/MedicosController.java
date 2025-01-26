package br.com.lrafasouza.VollMed.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.lrafasouza.VollMed.Utils.CEPServices;
import br.com.lrafasouza.VollMed.Utils.ErrorResponse;
import br.com.lrafasouza.VollMed.dto.EnderecoDTO;
import br.com.lrafasouza.VollMed.dto.Medico.MedicoUpdateDTO;
import br.com.lrafasouza.VollMed.dto.Medico.DetalhesMedicoDTO;
import br.com.lrafasouza.VollMed.models.Medico.MedicoModel;
import br.com.lrafasouza.VollMed.repository.MedicoRepository;
import br.com.lrafasouza.VollMed.service.MedicoService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/vollmed/medicos")
public class MedicosController {

	@Autowired
	private CEPServices cepServices;
	@Autowired
	private MedicoService medicoService;

	@Autowired
	private MedicoRepository medicoRepository;

	// Visão para os pacientes
	@GetMapping("/lista")
	public Page<DetalhesMedicoDTO> listaMedicos(@PageableDefault(sort = { "nome" }) Pageable paginacao) {
		return medicoService.findAllByAtivoTrue(paginacao).map(DetalhesMedicoDTO::new);
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

	@PutMapping("/atualizar/{id}")
	@Transactional
	public void atualizar(@Valid @PathVariable Long id, @RequestBody MedicoUpdateDTO dados) {
		var medico = medicoRepository.getReferenceById(dados.id());
		medico.atualizarInformacoes(dados, cepServices);
	}

	@PutMapping("/atualizar/{id}/endereco")
	@Transactional
	public void atualizarEndereco(@Valid @PathVariable Long id, @RequestBody MedicoUpdateDTO dados) {
	    var medico = medicoRepository.getReferenceById(id);
	    if (dados.endereco() != null) {
	        medico.atualizarEndereco(dados.endereco());
	    }
	}
	
	@DeleteMapping("/deletar/{id}")
	@Transactional
	public void deletar(@Valid @PathVariable Long id, @RequestBody MedicoModel medicoModel) {
		 var medico = medicoRepository.getReferenceById(id);
		 medico.excluir();
	    }
	}


