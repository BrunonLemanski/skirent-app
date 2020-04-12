package pl.leman.rentapi.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import pl.leman.rentapi.model.Client;
import pl.leman.rentapi.service.ClientService;
import pl.leman.rentapi.service.MapValidationErrorService;

import javax.validation.Valid;
import java.util.Optional;

@RestController
@RequestMapping("/client")
public class ClientController {

    @Autowired
    private ClientService clientService;

    @Autowired
    private MapValidationErrorService mapValidationErrorService;

    @GetMapping("/all")
    public Iterable<Client> getAllClients() { return clientService.findAll(); }

    @GetMapping("")
    public ResponseEntity<?> getClientById(@RequestParam Integer id) {
        Optional<Client> client = clientService.findClientById(id);

        return new ResponseEntity<>(client, HttpStatus.OK);
    }

    @PostMapping("/add")
    public ResponseEntity<?> addNewClient(@Valid @RequestBody Client client, BindingResult result) {

        ResponseEntity<?> errorMap = mapValidationErrorService.mapValidationError(result);
        if(errorMap != null) {
            return errorMap;
        }

        Client client1 = clientService.addOrUpdateUser(client);

        return new ResponseEntity<>(client1, HttpStatus.CREATED);
    }

    @DeleteMapping("/remove")
    public ResponseEntity<?> deleteClient(@RequestParam Integer id) {
        clientService.deleteClientById(id);

        return new ResponseEntity<>("Użytkownik z ID: " + id +" został usunięty", HttpStatus.OK);
    }
}
