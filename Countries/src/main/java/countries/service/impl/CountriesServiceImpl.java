package countries.service.impl;

import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import countries.dto.Country;
import countries.service.CountriesService;
import reactor.core.publisher.Flux;
@Service
public class CountriesServiceImpl implements CountriesService {
	private WebClient webClient = WebClient.create("https://restcountries.eu/rest/v2");
	@Override
	public Flux<Country> getAll() {
		Flux<Country> country= webClient.get().uri("/all").retrieve()
		        .bodyToFlux(Country.class);
		return country;
	}

}
