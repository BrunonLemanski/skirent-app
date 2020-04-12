package pl.leman.rentapi.exceptions.rent;

public class RentExceptionRespone {

    private String rentException;

    public RentExceptionRespone(String rentException) {
        this.rentException = rentException;
    }

    public String getRentException() {
        return rentException;
    }

    public void setRentException(String rentException) {
        this.rentException = rentException;
    }
}
