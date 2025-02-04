package vttp2023.batch3.assessment.paf.bookings.models;

import org.bson.Document;
import org.bson.types.ObjectId;

import vttp2023.batch3.assessment.paf.bookings.Utils.Constants;

public class Accoms {
    private String name;
    private String image;
    private double price;
    private String id;

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    
    public double getPrice() {
        return price;
    }
    public void setPrice(double price) {
        this.price = price;
    }

    public String getImage() {
        return image;
    }
    public void setImage(String image) {
        this.image = image;
    }

    public String getId() {
        return id;
    }
    public void setId(String id) {
        this.id = id;
    }


    public static Accoms fromDocument(Document document){
        Accoms accoms = new Accoms();
        //Name
        Document address = (Document) document.get(Constants.F_ADDRESS);
        accoms.setName(address.getString(Constants.F_STREET));
        //image
        Document images = (Document) document.get(Constants.F_IMAGES);
        accoms.setImage(images.getString(Constants.F_PICTURE));

        //price
        accoms.setPrice((Double)document.get(Constants.F_PRICE));

        //id
        Object id = document.get(Constants.F_ID);
        if (id instanceof ObjectId) {
            accoms.setId(((ObjectId) id).toString());
        } else {
            accoms.setId(id.toString());
        }

        return accoms;

    }


    
}
