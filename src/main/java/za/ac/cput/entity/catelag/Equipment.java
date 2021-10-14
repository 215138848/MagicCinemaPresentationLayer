package za.ac.cput.entity.catelag;

import com.sun.istack.NotNull;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.List;

@Entity
@Table(name="equipment")
public class Equipment implements Serializable {

    @Id
    @NotNull
    private String equipmentID;
    @NotNull
    private String gearCategory;
    @NotNull
    private String gearSubCategory;
    private String make;
    private String model;
    private String location;
    @Column(name = "cost_per_unit")
    private double rentalPrice;
    @Column(name = "availability")
    private boolean available;
    private int quantity;
    private String imagePath;

    public Equipment() {}

    private Equipment (Builder builder)
    {
        this.equipmentID = builder.equipmentID;
        this.gearCategory = builder.gearCategory;
        this.gearSubCategory =builder.gearSubCategory;
        this.make = builder.make;
        this.model = builder.model;
        this.location = builder.location;
        this.rentalPrice = builder.rentalPrice;
        this.available = builder.available;
        this.quantity = builder.quantity;
        this.imagePath = builder.imagePath;
    }
    public static class Builder
    {
        private String equipmentID,gearCategory,gearSubCategory,make,model,location, imagePath;
        private double rentalPrice;
        private boolean available;
        private int quantity;


        public Builder setEquipmentID(String equipmentID) {
            this.equipmentID = equipmentID;
            return this;
        }

        public Builder setGearCategory(String gearCategory) {
            this.gearCategory = gearCategory;
            return this;

        }

        public Builder setGearSubCategory(String gearSubCategory) {
            this.gearSubCategory = gearSubCategory;
            return this;
        }

        public Builder setMake(String make) {
            this.make = make;
            return this;
        }

        public Builder setModel(String model) {
            this.model = model;
            return this;
        }

        public Builder setLocation(String location) {
            this.location = location;
            return this;
        }

        public Builder setRentalPrice(double rentalPrice) {
            this.rentalPrice = rentalPrice;
            return this;
        }

        public Builder setQuantity(int quantity) {
            this.quantity = quantity;
            return this;
        }

        public Builder setAvailable() {
            if(quantity > 0) {
                available = true;
            }
            else {
                available = false;
            }
            return this;
        }

        public Builder setImagePath(String imagePath) {
            this.imagePath = imagePath;
            return this;
        }


        public Equipment build()
        {
            return new Equipment(this);
        }

        public Builder copy(Equipment equipment)
        {
            this.equipmentID = equipment.equipmentID;
            this.gearCategory = equipment.gearCategory;
            this.gearSubCategory = equipment.gearSubCategory;
            this.make = equipment.make;
            this.model = equipment.model;
            this.location = equipment.location;
            this.rentalPrice = equipment.rentalPrice;
            this.quantity = equipment.quantity;
            this.available = equipment.available;
            this.imagePath = equipment.imagePath;
            return this;
        }
    }

    public String getEquipmentID() {
        return equipmentID;
    }

    public String getGearCategory() {
        return gearCategory;
    }

    public String getGearSubCategory() {
        return gearSubCategory;
    }

    public String getMake() {
        return make;
    }

    public String getModel() {
        return model;
    }

    public String getLocation() {
        return location;
    }

    public double getRentalPrice() {
        return rentalPrice;
    }

    public boolean isAvailable() {
        return available;
    }

    public int getQuantity() {
        return quantity;
    }

    public String getImagePath() {return imagePath;}

    @Override
    public String toString() {
        return "Equipment{" +
                "equipmentID='" + equipmentID + '\'' +
                ", gearCategory='" + gearCategory + '\'' +
                ", gearSubCategory='" + gearSubCategory + '\'' +
                ", make='" + make + '\'' +
                ", model='" + model + '\'' +
                ", location='" + location + '\'' +
                ", rentalPrice=" + rentalPrice +
                ", rented=" + available +
                '}';
    }
}
