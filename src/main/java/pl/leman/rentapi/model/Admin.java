package pl.leman.rentapi.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import java.util.Date;

@Entity
@Data
public class Admin {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Imię jest wymagane")
    private String name;

    @NotBlank(message = "Nazwisko jest wymagane")
    private String lastname;

    @NotBlank(message = "ID pracownika jest wymagana")
    @Pattern(regexp = "ID202[0-9][0-9]{4}")
    @Column(updatable = false)
    private String workerId;

    @JsonIgnore
    private String password;

    @JsonFormat(pattern = "dd-mm-yyyy")
    @JsonIgnore
    private Date created_at;

    @JsonFormat(pattern = "dd-mm-yyyy")
    @JsonIgnore
    private Date updated_at;

    @PrePersist
    protected void onCreate() { this.created_at = new Date(); }

    @PreUpdate
    protected void onUpdate() { this.updated_at = new Date(); }

}
