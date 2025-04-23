package br.com.neurotech.challenge.interfaces.dto;

import br.com.neurotech.challenge.domain.model.VehicleModel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class AutomotiveCreditResponseDTO {

    private ClientResponseDTO client;
    private boolean eligible;
    private VehicleModel model;
}
