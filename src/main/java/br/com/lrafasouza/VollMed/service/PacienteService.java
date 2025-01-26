package br.com.lrafasouza.VollMed.service;

import java.util.InputMismatchException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import br.com.lrafasouza.VollMed.models.Medico.MedicoModel;
import br.com.lrafasouza.VollMed.models.Paciente.PacienteModel;
import br.com.lrafasouza.VollMed.repository.PacienteRepository;
import jakarta.servlet.http.HttpServletRequest;


@Service
public class PacienteService {
	
	
	@Autowired
	private PacienteRepository pacienteRepository;
	
	
	// Inicio - Validação CPF
	public boolean validarCPF(String cpf) {
		if (cpf.equals("00000000000") || cpf.equals("11111111111") || cpf.equals("22222222222")
				|| cpf.equals("33333333333") || cpf.equals("44444444444") || cpf.equals("55555555555")
				|| cpf.equals("66666666666") || cpf.equals("77777777777") || cpf.equals("88888888888")
				|| cpf.equals("99999999999") || (cpf.length() != 11) || cpf == null)
			return false;

		char dig10, dig11;
		int sm, i, r, num, peso;

		try {
			sm = 0;
			peso = 10;
			for (i = 0; i < 9; i++) {
				num = (int) (cpf.charAt(i) - 48);
				sm += (num * peso);
				peso--;
			}

			r = 11 - (sm % 11);
			dig10 = (r == 10 || r == 11) ? '0' : (char) (r + 48);

			sm = 0;
			peso = 11;
			for (i = 0; i < 10; i++) {
				num = (int) (cpf.charAt(i) - 48);
				sm += (num * peso);
				peso--;
			}

			r = 11 - (sm % 11);
			dig11 = (r == 10 || r == 11) ? '0' : (char) (r + 48);

			return (dig10 == cpf.charAt(9)) && (dig11 == cpf.charAt(10));
		} catch (InputMismatchException erro) {
			return false;
		}
	}
	// Fim - Validação CPF
	
	public boolean validarDocumento(String cpf) {
		String numero = cpf.replaceAll("[^\\d]", "");

		if (numero.length() == 11) {
			return validarCPF(numero);
		}
		return false;
	}
	
	public boolean existsByDocumento(String documento) {
		return pacienteRepository.findBycpf(documento) != null;
	}
	
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
	
	public List<PacienteModel> getAll() {
		return pacienteRepository.findAll();
	}
	
	
	public ResponseEntity<PacienteModel> salvarPessoa(PacienteModel pacienteModel, HttpServletRequest request) {

		String telefone = validarTelefone(pacienteModel.getTelefone());

		if (telefone == null) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Preencher Telefone");
		}
		
		if(!validarDocumento(pacienteModel.getCpf())) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "CPF invalido");
		}
	

		if (pacienteModel.getCpf() == null) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Preencher CPF");
		}

		if (existsByDocumento(pacienteModel.getCpf())) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "CPF já cadastrado");
		}

		pacienteModel.setTelefone(telefone);
		PacienteModel savedPaciente = pacienteRepository.save(pacienteModel);

		return ResponseEntity.status(HttpStatus.CREATED).body(savedPaciente);
	}
	
}
