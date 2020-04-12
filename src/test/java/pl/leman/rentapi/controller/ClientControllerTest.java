package pl.leman.rentapi.controller;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import pl.leman.rentapi.model.Client;
import pl.leman.rentapi.service.ClientService;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
public class ClientControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ClientService clientService;

    private static final String TEST_CLIENT_JSON = "{\"name\":\"User\",\"lastname\":\"Tester\",\"pesel\":\"91010105399\",\"personalIdNumber\":\"ABC123416\"}";


    /**
     * Testing endpoint to getting all Clients from database.
     * GET method.
     * @throws Exception
     */
    @Test
    void shouldReturnOk_whenAllUsersWillBeTaken() throws Exception {

        Client client = createTestData();

        List<Client> clientsList = Arrays.asList(client);

        given(clientService.findAll()).willReturn(clientsList);

        mockMvc.perform(get("/client/all").contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(1)))
                .andExpect(jsonPath("$[0].name", is(client.getName())))
                .andExpect(jsonPath("$[0].lastname", is(client.getLastname())))
                .andExpect(jsonPath("$[0].pesel", is(client.getPesel())))
                .andExpect(jsonPath("$[0].personalIdNumber", is(client.getPersonalIdNumber())))
                .andDo(print());
    }

    /**
     * Testing endpoint to getting Client by ID from database.
     * GET method.
     * @throws Exception
     */
    @Test
    void shouldReturnOk_whenClientWillBeTakenById() throws Exception {

        Client client = createTestData();

        when(clientService.findClientById(1)).thenReturn(Optional.ofNullable(client));

        mockMvc.perform(get("/client?id=1").contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name", is(client.getName())))
                .andExpect(jsonPath("$.lastname", is(client.getLastname())))
                .andExpect(jsonPath("$.pesel", is(client.getPesel())))
                .andExpect(jsonPath("$.personalIdNumber", is(client.getPersonalIdNumber())))
                .andDo(print());

        verify(clientService, times(1)).findClientById(1);
    }

    /**
     * Testing endpoint to creating new Client.
     * POST method.
     * @throws Exception
     */
    @Test
    void shouldReturnCreated_whenClientWillBeCreated() throws Exception {

        mockMvc.perform(post("/client/add")
                .content(TEST_CLIENT_JSON)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated())
                .andDo(print());

        verify(clientService, times(1)).addOrUpdateUser(any(Client.class));
    }

    /**
     * Testing enpoint to deleting Client from database.
     * DELETE method.
     * @throws Exception
     */
    @Test
    void shouldReturnOk_whenClientWillBeDeleted() throws Exception {

        Client client = createTestData();

        when(clientService.findClientById(1)).thenReturn(Optional.ofNullable(client));

        String value = "Użytkownik z ID: 1 został usunięty";

        mockMvc.perform(delete("/client/remove?id=1"))
                .andExpect(status().isOk())
                .andExpect(content().string(value))
                .andDo(print());
    }

    /**
     * Method to creating test data.
     * @return object Client.
     */
    private Client createTestData() {
        Client client = new Client();
        client.setId(1L);
        client.setName("Brunon");
        client.setLastname("Lemanski");
        client.setPesel("91010101549");
        client.setPersonalIdNumber("ABC123456");

        return client;
    }
}