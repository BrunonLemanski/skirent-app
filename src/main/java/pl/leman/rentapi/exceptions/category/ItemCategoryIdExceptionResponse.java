package pl.leman.rentapi.exceptions.category;

public class ItemCategoryIdExceptionResponse {

    private String itemId;

    public ItemCategoryIdExceptionResponse(String itemId) {
        this.itemId = itemId;
    }

    public String getItemId() {
        return itemId;
    }

    public void setItemId(String itemId) {
        this.itemId = itemId;
    }
}
