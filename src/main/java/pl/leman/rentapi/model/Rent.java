package pl.leman.rentapi.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import java.util.Date;

@Entity
@Data
public class Rent {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Data wypożyczenia jest wymagana")
    @JsonFormat(pattern = "dd-mm-yyyy")
    @Column(name = "RENT_DATE")
    private Date rentDate;

    @JsonFormat(pattern = "dd-mm-yyyy")
    @Column(name = "RETURN_DATE")
    private Date returnDate;

    @Column(name = "COST")
    private String cost;

    @JsonFormat(pattern = "dd-mm-yyyy")
    @JsonIgnore
    private Date created_at;

    @JsonFormat(pattern = "dd-mm-yyyy")
    @JsonIgnore
    private Date updated_at;


    @NotEmpty(message = "Użytkownik jest wymagany")
    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "USER_ID", referencedColumnName = "id")
    private Client clientId;

    @NotEmpty(message = "Przedmiot jest wymagany")
    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "ITEM_ID", referencedColumnName = "id")
    @JsonIgnore
    private Item itemId;



    @PrePersist
    protected void onCreate() { this.created_at = new Date(); }

    @PreUpdate
    protected void onUpdate() { this.updated_at = new Date(); }
}
