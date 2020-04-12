package pl.leman.rentapi.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.leman.rentapi.exceptions.rent.RentException;
import pl.leman.rentapi.model.Client;
import pl.leman.rentapi.model.Item;
import pl.leman.rentapi.model.Rent;
import pl.leman.rentapi.repository.RentRepository;

import java.util.Optional;

@Service
public class RentService {

    @Autowired
    private RentRepository rentRepository;

    @Autowired
    private ClientService clientService;

    @Autowired
    private ItemService itemService;


    public Iterable<Rent> findAllRents() {
        return rentRepository.findAll();
    }

    public Rent addNewRent(Rent rent, Integer clientId, String qrCode) {

        try{

            if(rent.getClientId() == null || rent.getItems() == null){
                Optional<Client> client = clientService.findClientById(clientId);

                Item item = itemService.findByQrCode(qrCode);

                if(!client.isPresent()) {
                    throw new RentException("Nie można odnaleźć klienta");
                }

                if(item == null) {
                    throw new RentException("Nie można odnaleźć przedmiotu");
                }

                rent.setClientId(client.get());
                //rent.setItems(item);

                //for creating
                return rentRepository.save(rent);
            }


            //for updating
            return rentRepository.save(rent);

        } catch (Exception ex) {
            throw new RentException("Nie można dodać ");
        }

    }
}
