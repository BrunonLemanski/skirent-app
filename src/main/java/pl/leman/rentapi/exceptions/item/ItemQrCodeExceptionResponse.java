package pl.leman.rentapi.exceptions.item;

public class ItemQrCodeExceptionResponse {

    private String qrCode;

    public ItemQrCodeExceptionResponse(String qrCode) {
        this.qrCode = qrCode;
    }

    public String getQrCode() {
        return qrCode;
    }

    public void setQrCode(String qrCode) {
        this.qrCode = qrCode;
    }
}
