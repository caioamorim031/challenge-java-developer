package br.com.neurotech.challenge.interfaces.controller.client;

import br.com.neurotech.challenge.domain.model.NeurotechClient;
import br.com.neurotech.challenge.domain.model.VehicleModel;
import br.com.neurotech.challenge.domain.usecase.AutomotiveCreditAvailableUseCase;
import br.com.neurotech.challenge.domain.usecase.CreateClientUseCase;
import br.com.neurotech.challenge.domain.usecase.FindClientUseCase;
import br.com.neurotech.challenge.interfaces.dto.AutomotiveCreditResponseDTO;
import br.com.neurotech.challenge.interfaces.dto.ClientRequestDTO;
import br.com.neurotech.challenge.interfaces.dto.ClientResponseDTO;
import br.com.neurotech.challenge.interfaces.mapper.ClientRequestMapper;
import br.com.neurotech.challenge.interfaces.mapper.ClientResponseMapper;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.Objects;

@RestController
@AllArgsConstructor
@RequestMapping("/client")
@Tag(name = "Client", description = "Services related to clients")
public class ClientController {

    private final ClientRequestMapper clientRequestMapper;
    private final ClientResponseMapper clientResponseMapper;
    private final CreateClientUseCase createClientUseCase;
    private final FindClientUseCase findClientUseCase;
    private final AutomotiveCreditAvailableUseCase automotiveCreditAvailableUseCase;

    @Operation(summary = "Add new client")
    @PostMapping("/new")
    public ResponseEntity<Void> add(@RequestBody @Valid ClientRequestDTO dto) {
        NeurotechClient client = clientRequestMapper.toEntity(dto);
        Long idGerado = createClientUseCase.executar(client);
        return ResponseEntity.created(URI.create("/api/client/" + idGerado)).build();
    }

    @Operation(summary = "Find client data by id")
    @GetMapping("/{id}")
    public ResponseEntity<ClientResponseDTO> findClientById(@PathVariable Long id) {
        NeurotechClient client = findClientUseCase.executar(id);

        if (Objects.isNull(client)) {
            return ResponseEntity.notFound().build();
        }

        ClientResponseDTO response = clientResponseMapper.toDto(client);
        return ResponseEntity.ok(response);
    }

    @Operation(summary = "Find automotive credit available based on the given model")
    @GetMapping("/{id}/automotive-credit")
    public ResponseEntity<AutomotiveCreditResponseDTO> creditAvailable(
            @PathVariable Long id,
            @RequestParam VehicleModel vehicleType) {
        NeurotechClient client = findClientUseCase.executar(id);

        if (Objects.isNull(client)) {
            return ResponseEntity.notFound().build();
        }

        boolean eligible = automotiveCreditAvailableUseCase.executar(client, vehicleType);
        return ResponseEntity.ok(new AutomotiveCreditResponseDTO(clientResponseMapper.toDto(client), eligible, vehicleType));
    }
}

