package br.com.lrafasouza.VollMed.models.Medico;


import br.com.lrafasouza.VollMed.dto.EnderecoDTO;
import br.com.lrafasouza.VollMed.dto.MedicoDTO;
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
@Entity(name = "tb_medicos")
public class MedicoModel {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	@Column (unique = true,nullable = false)
	private String crm;
	@Column(nullable = false)
	private String nome;
	@Column(nullable = false)
	private String email;
	@Column (nullable = false)
	private String telefone;
	@Column (nullable = false)
	private Especialidades especialidades;
	@Column(nullable = false)
	private String cep;
	@Embedded
	private EnderecoDTO endereco;
	
	public MedicoModel(MedicoDTO dados) {
	    this.nome = dados.nome();
	    this.email = dados.email();
	    this.crm = dados.crm();
	    this.telefone = dados.telefone();
	    this.especialidades = dados.especialidades();
	    this.cep = dados.cep();
}
}
