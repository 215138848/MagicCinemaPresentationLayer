package za.ac.cput.factory;

import za.ac.cput.entity.rent.RentItems;
import za.ac.cput.util.GenericHelper;

import java.text.SimpleDateFormat;
import java.util.Date;

public class RentItemsFactory {
    public static RentItems createRentItems(String client, String equipment, double cost, String estimateEndDate,
                                            String endDate, int daysOverdue, int quantity, double finalCost) {

        Date date = new Date();
        SimpleDateFormat formatter = new SimpleDateFormat("MM/dd/yyyy");
        String currentDate = formatter.format(date);

        int difference = GenericHelper.CalculateDifferenceBetweenDates(currentDate, estimateEndDate);
        double costOverTime;
        if(difference <= 0) {
            costOverTime = cost;
        }
        else {
            costOverTime = cost * difference;
        }

        int differenceCalculation = 0;

        if(!endDate.equals("")) {
            differenceCalculation = GenericHelper.CalculateDifferenceBetweenDates(estimateEndDate, endDate);
            if(differenceCalculation <= 0) {
                finalCost = cost;
            }
            else {
                finalCost = cost + ((cost/6) * difference);
            }
        }

        RentItems rent = new RentItems.Builder()
                .setClient(client)
                .setEquipment(equipment)
                .setCost(costOverTime)
                .setStartDate(currentDate)
                .setEstimateEndDate(estimateEndDate)
                .setEndDate(endDate)
                .setDaysOverdue(differenceCalculation)
                .setQuantity(quantity)
                .setFinalCost(finalCost)
                .build();

        return rent;
    }


}
