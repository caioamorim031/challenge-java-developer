package br.com.neurotech.challenge.domain.usecase;

import br.com.neurotech.challenge.domain.model.NeurotechClient;
import br.com.neurotech.challenge.domain.repository.ClientRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class CreateClientUseCase {
    private final ClientRepository repository;

    public Long executar(NeurotechClient client) {
        return repository.save(client).getId();
    }


}
