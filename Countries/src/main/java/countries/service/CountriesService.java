package countries.service;

import countries.dto.Country;
import reactor.core.publisher.Flux;

public interface CountriesService {

	public Flux<Country> getAll();
}
