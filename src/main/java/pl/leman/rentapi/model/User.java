package pl.leman.rentapi.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.util.Date;

@Entity
@Data
public class User {

    // -------- variables --------

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull(message = "Imię jest wymagane")
    @Column(name = "NAME")
    private String name;

    @NotNull(message = "Nazwisko jest wymagane")
    @Column(name = "LASTNAME")
    private String lastname;

    @NotNull(message = "PESEL jest wymagany")
    @Column(name = "PESEL")
    private Long pesel;

    @NotNull(message = "Numer dowodu jest wymagany")
    @Pattern(regexp = "[A-Z]{3}[0-9]{6}")
    @Column(name = "PERSONAL_ID_NUMBER")
    private String personalIdNumber;

    @JsonFormat(pattern = "dd-mm-yyyy")
    @JsonIgnore
    private Date created_at;

    @JsonFormat(pattern = "dd-mm-yyyy")
    @JsonIgnore
    private Date updated_at;



    // -------- mapping --------
    @OneToOne(mappedBy = "userId")
    private Rent rent;



    @PrePersist
    protected void onCreate() { this.created_at = new Date(); }

    @PreUpdate
    protected void onUpdate() { this.updated_at = new Date(); }
}
