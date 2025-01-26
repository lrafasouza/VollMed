package br.com.lrafasouza.VollMed.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.lrafasouza.VollMed.Utils.CEPServices;
import br.com.lrafasouza.VollMed.Utils.ErrorResponse;
import br.com.lrafasouza.VollMed.dto.EnderecoDTO;
import br.com.lrafasouza.VollMed.models.Paciente.PacienteModel;
import br.com.lrafasouza.VollMed.service.PacienteService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/vollmed/pacientes")
public class PacientesController {

	@Autowired
	private CEPServices cepServices;
	@Autowired
	private PacienteService pacienteService;

	public List<PacienteModel> getAll() {
		return pacienteService.getAll();
	}

	@PostMapping("/criar")
	public ResponseEntity<?> create(@Valid @RequestBody PacienteModel pacienteModel, HttpServletRequest request) {
		try {
			if (pacienteModel.getCep() != null && !pacienteModel.getCep().isEmpty()) {
				EnderecoDTO endereco = cepServices.buscarCep(pacienteModel.getCep());
				pacienteModel.setEndereco(endereco);
			}
			return pacienteService.salvarPessoa(pacienteModel, request);
		} catch (IllegalArgumentException e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ErrorResponse(e.getMessage()));
		}
	}

}
