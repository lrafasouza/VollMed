package br.com.lrafasouza.VollMed.models.Paciente;

import br.com.lrafasouza.VollMed.dto.EnderecoDTO;
import br.com.lrafasouza.VollMed.dto.Paciente.PacienteDTO;
import br.com.lrafasouza.VollMed.models.Medico.Especialidades;
import jakarta.persistence.Column;
import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Entity (name = "tb_pacientes")
public class PacienteModel {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	
	@Column (unique = true, nullable = false)
	private String cpf;
	
	@Column(nullable = false)
	private String nome;
	
	@Column(nullable = false)
	private String email;
	
	@Column (nullable = false)
	private String telefone;
	
	@Column(nullable = false)
	private String cep;
	
	@Embedded
	private EnderecoDTO endereco;
	
	public PacienteModel(PacienteDTO dados) {
		this.nome = dados.nome();
		this.email = dados.email();
		this.cpf = dados.cpf();
	}
	
}
