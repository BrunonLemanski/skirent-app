package pl.leman.rentapi.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.leman.rentapi.exceptions.client.ClientException;
import pl.leman.rentapi.model.Client;
import pl.leman.rentapi.repository.ClientRepository;

@Service
public class ClientService {

    @Autowired
    private ClientRepository clientRepository;

    public Client addOrUpdateUser(Client client) {
        try {
            client.setPersonalIdNumber(client.getPersonalIdNumber().toUpperCase());

            return clientRepository.save(client);
        } catch (Exception e) {
            throw new ClientException("Klient '" + client.getName() + " " + client.getLastname() + "' istnieje w bazie");
        }
    }


    public Iterable<Client> findAll() {
        return clientRepository.findAll();
    }

    
}
