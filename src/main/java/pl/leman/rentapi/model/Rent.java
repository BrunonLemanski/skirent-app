package pl.leman.rentapi.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Data
public class Rent {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    //@NotBlank(message = "Data wypożyczenia jest wymagana")
    @JsonFormat(pattern = "dd-mm-yyyy")
    @Column(name = "RENT_DATE")
    @JsonIgnore
    private Date rentDate;

    @JsonFormat(pattern = "dd-mm-yyyy")
    @Column(name = "RETURN_DATE")
    @JsonIgnore
    private Date returnDate;

    @NotBlank(message = "Kwota jest wymagana")
    @Column(name = "COST")
    private String cost;

    @JsonFormat(pattern = "dd-mm-yyyy")
    @JsonIgnore
    private Date created_at;

    @JsonFormat(pattern = "dd-mm-yyyy")
    @JsonIgnore
    private Date updated_at;

    @JsonBackReference
    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.REFRESH)
    @JoinColumn(name = "CLIENT_ID", updatable = false, nullable = false)
    private Client clientId;

    @OneToMany(mappedBy = "rent", targetEntity = Item.class, fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<Item> items = new ArrayList<>();

//TODO: dodac archiwum dla wypozyczen, Rent bedzie przechowywac jedynie aktualne wypozyczenia


    @PrePersist
    protected void onCreate() { this.created_at = new Date(); }

    @PreUpdate
    protected void onUpdate() { this.updated_at = new Date(); }

}
