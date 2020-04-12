package pl.leman.rentapi.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Data
public class Client implements Serializable {

    // -------- variables --------

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Imię jest wymagane")
    @Column(name = "NAME")
    private String name;

    @NotBlank(message = "Nazwisko jest wymagane")
    private String lastname;

    @NotBlank(message = "PESEL jest wymagany")
    @Column(name = "PESEL", unique = true, updatable = false)
    private String pesel;

    @NotBlank(message = "Numeru dowodu jest wymagany")
    @Pattern(regexp = "[A-Z]{3}[0-9]{6}", message = "Numer dowodu musi mieć format 'ABC123456'")
    @Column(name = "PERSONAL_ID_NUMBER", unique = true, updatable = false)
    private String personalIdNumber;

    @JsonFormat(pattern = "dd-mm-yyyy")
    @JsonIgnore
    @Column(updatable = false)
    private Date created_at;

    @JsonFormat(pattern = "dd-mm-yyyy")
    @JsonIgnore
    private Date updated_at;


    // -------- mapping --------
    @JsonManagedReference
    @OneToMany(mappedBy = "clientId", targetEntity = Rent.class, cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<Rent> rents = new ArrayList<>();

    public Client() {
    }

    @PrePersist
    protected void onCreate() { this.created_at = new Date(); }

    @PreUpdate
    protected void onUpdate() { this.updated_at = new Date(); }

}
