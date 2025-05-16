package Models;

public class Tour {

    private int tourID;
    private String tourName;
    private double tourPrice;
    private int quantity;
    private String tourImage;
    private String tourDescription;
    private int categoryID;
    private String createdBy;

    public Tour() {
    }

    public Tour(int tourID, String tourName, double tourPrice, int quantity, String tourImage, String tourDescription, int categoryID, String createdBy) {
        this.tourID = tourID;
        this.tourName = tourName;
        this.tourPrice = tourPrice;
        this.quantity = quantity;
        this.tourImage = tourImage;
        this.tourDescription = tourDescription;
        this.categoryID = categoryID;
        this.createdBy = createdBy;
    }

    public int getTourID() {
        return tourID;
    }

    public void setTourID(int tourID) {
        this.tourID = tourID;
    }

    public String getTourName() {
        return tourName;
    }

    public void setTourName(String tourName) {
        this.tourName = tourName;
    }

    public double getTourPrice() {
        return tourPrice;
    }

    public void setTourPrice(double tourPrice) {
        this.tourPrice = tourPrice;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public String getTourImage() {
        return tourImage;
    }

    public void setTourImage(String tourImage) {
        this.tourImage = tourImage;
    }

    public String getTourDescription() {
        return tourDescription;
    }

    public void setTourDescription(String tourDescription) {
        this.tourDescription = tourDescription;
    }

    public int getCategoryID() {
        return categoryID;
    }

    public void setCategoryID(int categoryID) {
        this.categoryID = categoryID;
    }

    public String getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    @Override
    public String toString() {
        return "Tour{" + "tourID=" + tourID + ", tourName=" + tourName + ", tourPrice=" + tourPrice + ", quantity=" + quantity + ", tourImage=" + tourImage + ", tourDescription=" + tourDescription + ", categoryID=" + categoryID + ", createdBy=" + createdBy + '}';
    }

}
