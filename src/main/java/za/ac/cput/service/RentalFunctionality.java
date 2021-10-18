package za.ac.cput.service;

import com.google.gson.Gson;
import kong.unirest.json.JSONArray;
import kong.unirest.json.JSONObject;
import okhttp3.*;
import za.ac.cput.entity.catelag.Equipment;
import za.ac.cput.entity.rent.RentItems;
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

    public static ArrayList<RentItems> getAllRentals() {
        try {
            ArrayList<RentItems> equipmentRentalsList = new ArrayList<>();
            final String URL = "http://localhost:8080/rentitems/getall";
            String responseBody = run(URL);
            JSONArray equipmentRentals = new JSONArray(responseBody);

            for (int i = 0; i < equipmentRentals.length(); i++) {
                JSONObject equipment = equipmentRentals.getJSONObject(i);

                Gson g = new Gson();
                RentItems e = g.fromJson(equipment.toString(), RentItems.class);
                equipmentRentalsList.add(e);
            }
            return equipmentRentalsList;

        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static ArrayList<RentItems> getRentalsFromCustomerId(String customerId) {
        try {
            ArrayList<RentItems> equipmentRentalsList = new ArrayList<>();
            final String URL = "http://localhost:8080/rentitems/getRentalByCustomer/" + customerId;
            String responseBody = run(URL);
            JSONArray equipmentRentals = new JSONArray(responseBody);

            for (int i = 0; i < equipmentRentals.length(); i++) {
                JSONObject equipmentRental = equipmentRentals.getJSONObject(i);

                Gson g = new Gson();
                RentItems e = g.fromJson(equipmentRental.toString(), RentItems.class);
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

    public static String put(final String url, String json) throws IOException {
        RequestBody body = RequestBody.create(JSON, json);
        Request request = new Request.Builder()
                .url(url)
                .put(body)
                .build();
        try(Response response = client.newCall(request).execute()) {
            return response.body().string();
        }
    }

    public static void saveRental(RentItems rental) {
        try {
            System.out.println(rental);
            final String URL = "http://localhost:8080/rentitems/create";
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


    public static RentItems getRentalItemById(String id) {
        try {

            final String URL = "http://localhost:8080/rentitems/read/" + id;
            String responseBody = run(URL);
            JSONObject rentInfo = new JSONObject(responseBody);
            Gson g = new Gson();
            RentItems rentItem = g.fromJson(rentInfo.toString(), RentItems.class);

            return rentItem;

        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static void updateRentalInformation(RentItems rentItems) {
        try {
            System.out.println(rentItems);
            final String URL = "http://localhost:8080/rentitems/update";
            Gson g = new Gson();
            String jsonString = g.toJson(rentItems);
            String r = put(URL, jsonString);
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
