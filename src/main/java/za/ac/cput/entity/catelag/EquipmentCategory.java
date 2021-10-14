package za.ac.cput.entity.catelag;

import com.sun.istack.NotNull;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.io.Serializable;

@Entity
public class EquipmentCategory implements Serializable {

    @Id
    @NotNull
    private String gearCategoryID;
    @NotNull
    private String gearCategoryTitle;

    public EquipmentCategory() {};

    public EquipmentCategory(Builder builder) {
        this.gearCategoryID = builder.gearCategoryID;
        this.gearCategoryTitle = builder.gearCategoryTitle;
    }

    public static class Builder {
        private String gearCategoryID, gearCategoryTitle;

        public Builder setGearCategoryID(String gearCategoryID) {
            this.gearCategoryID = gearCategoryID;
            return this;
        }
        public Builder setCategoryTitle(String gearCategoryTitle) {
            this.gearCategoryTitle = gearCategoryTitle;
            return this;
        }

        public EquipmentCategory build()
        {
            return new EquipmentCategory(this);
        }

        public Builder copy(EquipmentCategory equipmentCategory) {
            this.gearCategoryID = equipmentCategory.gearCategoryID;
            this.gearCategoryTitle = equipmentCategory.gearCategoryTitle;
            return this;
        }
    }

    public String getGearCategoryID() {
        return gearCategoryID;
    }

    public String getGearCategoryTitle() {
        return gearCategoryTitle;
    }

    @Override
    public String toString() {
        return "EquipmentCategory{" +
                "gearCategoryID='" + gearCategoryID + '\'' +
                ", gearCategoryTitle='" + gearCategoryTitle + '\'' +
                '}';
    }
}
