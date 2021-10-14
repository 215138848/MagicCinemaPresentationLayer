package za.ac.cput.factory;


import za.ac.cput.entity.catelag.EquipmentCategory;

public class EquipmentCategoryFactory {
    public static EquipmentCategory createEquipmentCategoryFactory(String id, String title){

        EquipmentCategory equipmentCategory = new EquipmentCategory.Builder()
                .setGearCategoryID(id)
                .setCategoryTitle(title)
                .build();

        return equipmentCategory;

    }
}
