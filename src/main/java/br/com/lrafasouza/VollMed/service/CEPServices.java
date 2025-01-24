package br.com.lrafasouza.VollMed.service;

import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.WebClientResponseException;

import br.com.lrafasouza.VollMed.dto.EnderecoDTO;
import reactor.core.publisher.Mono;

@Service
public class CEPServices {



	private final WebClient webClient;

	public CEPServices(WebClient.Builder webClientBuilder) {
		this.webClient = webClientBuilder.baseUrl("https://viacep.com.br").build();
	}

	public EnderecoDTO buscarCep(String cep) {
		Mono<EnderecoDTO> enderecoMono = this.webClient.get().uri("/ws/{cep}/json/", cep).retrieve()
				.bodyToMono(EnderecoDTO.class).onErrorResume(WebClientResponseException.class, e -> Mono.empty());

		return enderecoMono.block();
	}

}
