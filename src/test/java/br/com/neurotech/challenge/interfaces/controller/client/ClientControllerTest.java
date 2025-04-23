package br.com.neurotech.challenge.interfaces.controller.client;

import br.com.neurotech.challenge.domain.model.VehicleModel;
import br.com.neurotech.challenge.interfaces.dto.ClientRequestDTO;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.web.servlet.MockMvc;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
@TestPropertySource(properties = {
        "server.servlet.context-path=/api"
})
class ClientControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    private final String BASE_PATH = "/client";

    @Test
    @DisplayName("Deve cadastrar cliente com sucesso e retornar status 201 com Location")
    void deveCadastrarClienteComSucesso() throws Exception {
        ClientRequestDTO dto = new ClientRequestDTO("Carlos Novo", 30, 7000.0);

        mockMvc.perform(post(BASE_PATH + "/new")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(dto)))
                .andExpect(status().isCreated())
                .andExpect(header().string("Location", org.hamcrest.Matchers.containsString(BASE_PATH)));
    }

    @Test
    @DisplayName("Deve cadastrar cliente com sucesso e buscar os dados por id")
    void deveBuscarClientePorIdComSucesso() throws Exception {
        ClientRequestDTO dto = new ClientRequestDTO("Joana Cliente", 28, 6000.0);

        String location = mockMvc.perform(post(BASE_PATH + "/new")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(dto)))
                .andExpect(status().isCreated())
                .andReturn().getResponse().getHeader("Location");

        Long id = Long.valueOf(location.substring(location.lastIndexOf("/") + 1));

        mockMvc.perform(get(BASE_PATH + "/" + id))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("Joana Cliente"))
                .andExpect(jsonPath("$.age").value(28))
                .andExpect(jsonPath("$.income").value(6000.0));
    }

    @Test
    @DisplayName("Deve retornar cliente elegível para SUV")
    void deveRetornarElegivelParaSUV() throws Exception {
        ClientRequestDTO dto = new ClientRequestDTO("Maria SUV", 35, 8500.0);

        String location = mockMvc.perform(post(BASE_PATH + "/new")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(dto)))
                .andExpect(status().isCreated())
                .andReturn()
                .getResponse()
                .getHeader("Location");

        assertThat(location).isNotNull();
        Long id = Long.valueOf(location.substring(location.lastIndexOf("/") + 1));

        mockMvc.perform(get(BASE_PATH + "/" + id + "/automotive-credit")
                        .param("vehicleType", VehicleModel.SUV.name()))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.eligible").value(true))
                .andExpect(jsonPath("$.model").value("SUV"))
                .andExpect(jsonPath("$.client.name").value("Maria SUV"))
                .andExpect(jsonPath("$.client.age").value(35))
                .andExpect(jsonPath("$.client.income").value(8500.0));
    }

    @Test
    @DisplayName("Deve retornar erro 404 para cliente inexistente")
    void deveRetornar404ParaClienteInexistente() throws Exception {
        Long idInexistente = 9999L;

        mockMvc.perform(get(BASE_PATH + "/" + idInexistente))
                .andExpect(status().isNotFound());

        mockMvc.perform(get(BASE_PATH + "/" + idInexistente + "/automotive-credit")
                        .param("vehicleType", VehicleModel.SUV.name()))
                .andExpect(status().isNotFound());
    }
}
