package br.com.neurotech.challenge.domain.usecase;

import br.com.neurotech.challenge.domain.model.NeurotechClient;
import br.com.neurotech.challenge.domain.model.VehicleModel;
import br.com.neurotech.challenge.domain.service.ClientService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class AutomotiveCreditAvailableUseCase {

    public boolean executar(NeurotechClient client, VehicleModel model) {
        return ClientService.checkCredit(client,model);
    }
}
