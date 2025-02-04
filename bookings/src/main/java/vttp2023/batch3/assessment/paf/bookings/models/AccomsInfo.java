package vttp2023.batch3.assessment.paf.bookings.models;

import java.util.List;

import org.bson.Document;
import org.bson.types.ObjectId;

import vttp2023.batch3.assessment.paf.bookings.Utils.Constants;

public class AccomsInfo {
    private String id;
    private String description;
    private String address;
    private String image;
    private Double price;
    private List<String> amenities;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public List<String> getAmenities() {
        return amenities;
    }

    public void setAmenities(List<String> amenities) {
        this.amenities = amenities;
    }

    public static AccomsInfo fromDocument(Document document) {
        AccomsInfo info = new AccomsInfo();
        // Name
        Document address = (Document) document.get(Constants.F_ADDRESS);
        info.setAddress(address.getString(Constants.F_STREET));
        // image
        Document images = (Document) document.get(Constants.F_IMAGES);
        info.setImage(images.getString(Constants.F_PICTURE));

        // price
        info.setPrice((Double) document.get(Constants.F_PRICE));

        // id
        Object id = document.get(Constants.F_ID);
        if (id instanceof ObjectId) {
            info.setId(((ObjectId) id).toString());
        } else {
            info.setId(id.toString());
        }

        info.setAmenities(document.getList(Constants.F_AMENITIES, String.class));
        info.setDescription(document.getString(Constants.F_DESCRIPTION));

        return info;

    }

}
