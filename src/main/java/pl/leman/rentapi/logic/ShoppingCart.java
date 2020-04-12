package pl.leman.rentapi.logic;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import pl.leman.rentapi.model.Client;
import pl.leman.rentapi.model.Item;

import java.util.ArrayList;
import java.util.List;

@Component
public class ShoppingCart {

    private Client client;

    private List<Item> itemsList;

    public ShoppingCart(Client client) {
        this.client = client;
        this.itemsList = new ArrayList<>();
    }

}
