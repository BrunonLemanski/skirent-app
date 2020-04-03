package pl.leman.rentapi.model;

import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
public class ItemsCategory {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull(message = "Nazwa kategorii jest wymagana")
    @Size(min = 3, max = 15)
    @Column(unique = true)
    private String categoryName;

    @OneToMany(mappedBy = "itemCategory", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<Item> items = new ArrayList<>();

    public ItemsCategory() {
    }
}