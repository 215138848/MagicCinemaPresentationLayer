package za.ac.cput.service;

import com.google.gson.Gson;
import kong.unirest.json.JSONArray;
import kong.unirest.json.JSONObject;
import okhttp3.*;
import za.ac.cput.entity.rent.EquipmentRental;
import za.ac.cput.factory.EquipmentRentalFactory;
import za.ac.cput.util.GenericHelper;

import javax.swing.*;
import java.io.IOException;
import java.util.ArrayList;

public class RentalFunctionality {
    public static final MediaType JSON = MediaType.get("application/json; charset=utf-8");
    private static OkHttpClient client = new OkHttpClient();

    private static String run(String URL) throws IOException {
        Request request = new Request.Builder()
                .url(URL)
                .build();
        try(Response response = client.newCall(request).execute()) {
            return response.body().string();
        }
    }

    public static ArrayList<EquipmentRental> getAllRentals() {
        try {
            ArrayList<EquipmentRental> equipmentRentalsList = new ArrayList<>();
            final String URL = "http://localhost:8080/rental/getall";
            String responseBody = run(URL);
            JSONArray equipmentRentals = new JSONArray(responseBody);

            for (int i = 0; i < equipmentRentals.length(); i++) {
                JSONObject equipment = equipmentRentals.getJSONObject(i);

                Gson g = new Gson();
                EquipmentRental e = g.fromJson(equipment.toString(), EquipmentRental.class);
                equipmentRentalsList.add(e);
                System.out.println(e.toString());
            }
            return equipmentRentalsList;

        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static ArrayList<EquipmentRental> getRentalsFromCustomerId(String customerId) {
        try {
            ArrayList<EquipmentRental> equipmentRentalsList = new ArrayList<>();
            final String URL = "http://localhost:8080/rental/getRentalsFromCustomerId/" + customerId;
            String responseBody = run(URL);
            JSONArray equipmentRentals = new JSONArray(responseBody);

            for (int i = 0; i < equipmentRentals.length(); i++) {
                JSONObject equipmentRental = equipmentRentals.getJSONObject(i);

                Gson g = new Gson();
                EquipmentRental e = g.fromJson(equipmentRental.toString(), EquipmentRental.class);
                equipmentRentalsList.add(e);
                System.out.println(e.toString());
            }
            return equipmentRentalsList;

        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static String post(final String url, String json) throws IOException {
        RequestBody body = RequestBody.create(JSON, json);
        Request request = new Request.Builder()
                .url(url)
                .post(body)
                .build();
        try(Response response = client.newCall(request).execute()) {
            return response.body().string();
        }
    }

    public static void saveRental(EquipmentRental rental) {
        try {
            System.out.println(rental);
            final String URL = "http://localhost:8080/rental/create";
            Gson g = new Gson();
            String jsonString = g.toJson(rental);
            String r = post(URL, jsonString);
            if(r != null) {
                JOptionPane.showMessageDialog(null, "Record has been saved.");
            }
            else {
                JOptionPane.showMessageDialog(null, "Record was not stored.");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


}
