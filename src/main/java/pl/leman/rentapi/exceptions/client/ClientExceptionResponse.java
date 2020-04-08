package pl.leman.rentapi.exceptions.client;

public class ClientExceptionResponse {

    private String exception;

    public ClientExceptionResponse(String exception) {
        this.exception = exception;
    }

    public String getException() {
        return exception;
    }

    public void setException(String exception) {
        this.exception = exception;
    }
}
