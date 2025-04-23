package br.com.neurotech.challenge.domain.usecase;

import br.com.neurotech.challenge.domain.model.NeurotechClient;
import br.com.neurotech.challenge.domain.repository.ClientRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class FindClientUseCase {
    private final ClientRepository repository;

    public NeurotechClient executar(Long id) {
        return repository.findById(id).orElse(null);
    }
}
