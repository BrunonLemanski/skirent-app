package pl.leman.rentapi.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Date;
import java.util.UUID;

@Entity
@Data
public class Item {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull(message = "Marka jest wymagana")
    @Column(name = "MAKE")
    private String make;

    @NotNull(message = "Model jest wymagany")
    @Column(name = "MODEL")
    private String model;

    @NotNull(message = "Cena jest wymagana")
    @Column(name = "PRICE")
    private String price;

    @NotNull(message = "Dostępność jest wymagana")
    @Column(name = "AVAILABILITY")
    private Boolean availability;

    @Column(name = "QR_CODE", unique = true, updatable = false)
    private String qrCode;

    @JsonFormat(pattern = "dd-mm-yyyy")
    @Column(name = "RESERVATION_DATE")
    private Date reservationDate;

    @JsonFormat(pattern = "dd-mm-yyyy")
    @Column(updatable = false)
    @JsonIgnore
    private Date created_at;

    @JsonFormat(pattern = "dd-mm-yyyy")
    @JsonIgnore
    private Date updated_at;

    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.REFRESH)
    @JoinColumn(name = "item_category_id", updatable = false, nullable = false)
    @JsonIgnore
    private ItemsCategory itemCategory;


    // -------- mapping --------
    @OneToOne(mappedBy = "itemId")
    private Rent rent;


    public Item() {
    }

    @PrePersist
    protected void onCreate() {
        this.qrCode = "QR_" + UUID.randomUUID();
        this.created_at = new Date();
    }

    @PreUpdate
    protected void onUpdate() { this.updated_at = new Date(); }


    //Builder pattern implementation

    /*public static final class Builder {
        private String make;
        private String model;
        private String price;
        private Boolean availability;
        private String qrCode;
        private Date reservationDate;
        private ItemsCategory itemsCategory;

        public Builder make(String make) {
            this.make = make;
            return this;
        }

        public Builder model(String model) {
            this.model = model;
            return this;
        }

        public Builder price(String price) {
            this.price = price;
            return this;
        }

        public Builder availability(Boolean availability) {
            this.availability = availability;
            return this;
        }

        public Builder qrCode(String qrCode) {
            this.qrCode = qrCode;
            return this;
        }

        public Builder reservationDate(Date reservationDate) {
            this.reservationDate = reservationDate;
            return this;
        }

        public Builder itemsCategory(ItemsCategory itemsCategory) {
            this.itemsCategory = itemsCategory;
            return this;
        }

        public Item build() {
            Item item = new Item();
            item.make = this.make;
            item.model = this.model;
            item.price = this.price;
            item.availability = this.availability;
            item.qrCode = this.qrCode;
            item.reservationDate = this.reservationDate;
            item.itemCategory = this.itemsCategory;
            return item;
        }
    }*/
}
