package br.com.lrafasouza.VollMed.dto;

import jakarta.persistence.Embeddable;
import lombok.Data;

@Data
@Embeddable
public class EnderecoDTO {
	private String logradouro;
	private String numero;
	private String complemento;
	private String bairro;
	private String localidade;
	private String uf;
	private String estado;
}
