package za.ac.cput.factory;

import za.ac.cput.entity.catelag.Equipment;

public class EquipmentFactory
{
    public static Equipment createEquipment (String equipmentId, String gearCategory, String gearSubCategory, String make, String model,
                                             String location, int quantity, double rentalPrice, String imagePath) {

        Equipment equipment = new Equipment.Builder()
                .setEquipmentID(equipmentId)
                .setGearCategory(gearCategory)
                .setGearSubCategory(gearSubCategory)
                .setMake(make)
                .setModel(model)
                .setLocation(location)
                .setRentalPrice(rentalPrice)
                .setQuantity(quantity)
                .setAvailable()
                .setImagePath(imagePath)
                .build();

        return equipment;
    }
}
