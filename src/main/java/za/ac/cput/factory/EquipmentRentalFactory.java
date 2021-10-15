package za.ac.cput.factory;

import za.ac.cput.entity.rent.EquipmentRental;
import za.ac.cput.util.GenericHelper;

import java.text.SimpleDateFormat;
import java.util.Date;

public class EquipmentRentalFactory {
    public static EquipmentRental createEquipmentRental(String clientID, String equipmentID, String employeeNumber,
                                                        String rentalEstimatedEndDate, String rentalEndDate,
                                                        double costOverTime, int daysOverdue, double setPenalty, int quantity,
                                                        double rentCost, double finalReturnCost) {
        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
        Date date = new Date();
        String currentDate = formatter.format(date);


        String rentalID = GenericHelper.generateId();
        EquipmentRental equipmentRental = new EquipmentRental.Builder()
                .setRentalID(rentalID)
                .setClientID(clientID)
                .setEquipmentID(equipmentID)
                .setEmployeeNumber(employeeNumber)
                .setRentCost(rentCost)
                .setCostOverTime(costOverTime)
                .setDaysOverdue(daysOverdue)
                .setPenalty(setPenalty)
                .setRentalStartDate(currentDate)
                .setRentalReturnEstimatedDate(rentalEstimatedEndDate)
                .setRentalEndDate(rentalEndDate)
                .setQuantity(quantity)
                .setFinalReturnCost(finalReturnCost)
                .build();

        return equipmentRental;
    }

}
