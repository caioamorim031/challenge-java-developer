package br.com.neurotech.challenge.domain.service;

import br.com.neurotech.challenge.domain.model.NeurotechClient;
import br.com.neurotech.challenge.domain.model.VehicleModel;

public class ClientService {

    public static boolean checkCredit(NeurotechClient client, VehicleModel model) {
        return switch (model) {
            case VehicleModel.HATCH -> client.getIncome() >= 5000 && client.getIncome() <= 15000;
            case VehicleModel.SUV -> client.getAge() > 20 && client.getIncome() > 8000;
            default -> false;
        };
    }
}
