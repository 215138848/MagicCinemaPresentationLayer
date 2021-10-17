package za.ac.cput.factory;

import za.ac.cput.entity.rent.EquipmentRental;
import za.ac.cput.util.GenericHelper;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class EquipmentRentalFactory {
    public static EquipmentRental createEquipmentRental(String clientID, String equipmentID, String employeeNumber,
                                                        String rentalEstimatedEndDate, String rentalEndDate,
                                                        int daysOverdue, double setPenalty, int quantity,
                                                        double rentCost, double finalReturnCost) throws ParseException {
        SimpleDateFormat formatter = new SimpleDateFormat("MM/dd/yyyy");
        Date date = new Date();
        String currentDate = formatter.format(date);


        int difference = GenericHelper.CalculateDifferenceBetweenDates(currentDate, rentalEstimatedEndDate);
        double costOverTime = rentCost * difference;


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
