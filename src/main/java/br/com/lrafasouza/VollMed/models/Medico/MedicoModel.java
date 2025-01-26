package br.com.lrafasouza.VollMed.models.Medico;


import br.com.lrafasouza.VollMed.Utils.CEPServices;
import br.com.lrafasouza.VollMed.dto.EnderecoDTO;
import br.com.lrafasouza.VollMed.dto.Medico.MedicoDTO;
import br.com.lrafasouza.VollMed.dto.Medico.MedicoUpdateDTO;
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

	
	@Column(nullable = false)
	private Boolean ativo = true;
	
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
		this.ativo = true;
        this.nome = dados.nome();
        this.email = dados.email();
        this.crm = dados.crm();
        this.telefone = dados.telefone();
        this.especialidades = dados.especialidades();
        this.cep = dados.cep();
    }

    public void atualizarInformacoes(MedicoUpdateDTO dados, CEPServices cepServices) {
        if (dados.nome() != null) {
            this.nome = dados.nome();
        }

        if (dados.telefone() != null) {
            this.telefone = dados.telefone();
        }

        if (dados.cep() != null && !dados.cep().equals(this.cep)) {
            this.cep = dados.cep();
            EnderecoDTO novoEndereco = cepServices.buscarCep(this.cep);
            if (novoEndereco != null) {
                this.endereco = novoEndereco;
            }
        }
    }
    
    public void atualizarEndereco(EnderecoDTO dados) {
        if (dados.getNumero() != null) {
            this.endereco.setNumero(dados.getNumero());
        }
        if (dados.getComplemento() != null) {
            this.endereco.setComplemento(dados.getComplemento()); 
        }
    }

	public void excluir() {
		this.ativo = false;
	}

  
}
	
