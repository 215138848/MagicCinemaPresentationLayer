package za.ac.cput.service;

import com.google.gson.Gson;
import kong.unirest.json.JSONArray;
import kong.unirest.json.JSONObject;
import kong.unirest.json.JSONString;
import za.ac.cput.entity.Client;
import za.ac.cput.entity.Employee;
import za.ac.cput.entity.catelag.Equipment;

import okhttp3.*;
import za.ac.cput.entity.catelag.EquipmentCategory;
import za.ac.cput.factory.ClientFactory;
import za.ac.cput.factory.EquipmentFactory;

import javax.swing.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

public class EquipmentFunctionality {
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

    public static ArrayList<Equipment> getAllEquipment() {
        try {
            ArrayList<Equipment> equipmentList = new ArrayList<>();
            final String URL = "http://localhost:8080/equipment/getAllValidEquipment";
            String responseBody = run(URL);
            JSONArray equipments = new JSONArray(responseBody);

            for (int i = 0; i < equipments.length(); i++) {
                JSONObject equipment = equipments.getJSONObject(i);

                Gson g = new Gson();
                Equipment e = g.fromJson(equipment.toString(), Equipment.class);
                equipmentList.add(e);
            }
            return equipmentList;

        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static ArrayList<Equipment> getAll() {
        try {
            ArrayList<Equipment> equipmentList = new ArrayList<>();
            final String URL = "http://localhost:8080/equipment/getAllEquipmentRented";
            String responseBody = run(URL);
            JSONArray equipments = new JSONArray(responseBody);

            for (int i = 0; i < equipments.length(); i++) {
                JSONObject equipment = equipments.getJSONObject(i);

                Gson g = new Gson();
                Equipment e = g.fromJson(equipment.toString(), Equipment.class);
                equipmentList.add(e);
            }
            return equipmentList;

        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static String[] getAllCategories() {
        try {
            ArrayList<String> equipmentCategoriesList = new ArrayList<>();
            equipmentCategoriesList.add(" ");
            final String URL = "http://localhost:8080/equipment/getEquipmentCategories";
            String responseBody = run(URL);
            JSONArray equipmentCategories = new JSONArray(responseBody);

            for (int i = 0; i < equipmentCategories.length(); i++) {
                JSONObject equipment = equipmentCategories.getJSONObject(i);

                Gson g = new Gson();
                EquipmentCategory e = g.fromJson(equipment.toString(), EquipmentCategory.class);
                equipmentCategoriesList.add(e.getGearCategoryTitle());
            }

            Object[]objectArray = equipmentCategoriesList.toArray();
            String[] categories = Arrays.copyOf(objectArray, objectArray.length, String[].class);
            return categories;

        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static ArrayList<Equipment> getAllEquipmentByCategory(String category) {
        try {
            ArrayList<Equipment> equipmentList = new ArrayList<>();
            final String URL = "http://localhost:8080/equipment/getAllEquipmentByCategory/" + category;
            String responseBody = run(URL);
            JSONArray equipments = new JSONArray(responseBody);

            for (int i = 0; i < equipments.length(); i++) {
                JSONObject equipment = equipments.getJSONObject(i);

                Gson g = new Gson();
                Equipment e = g.fromJson(equipment.toString(), Equipment.class);
                equipmentList.add(e);
            }
            return equipmentList;

        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static String readNameById(String equipmentId) {
        try {
            final String URL = "http://localhost:8080/equipment/readNameById/" + equipmentId;
            String responseBody = run(URL);
            return responseBody;

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

    public static String delete(final String url) throws IOException {
        Request request = new Request.Builder()
                .url(url)
                .delete()
                .build();
        try(Response response = client.newCall(request).execute()) {
            return response.body().string();
        }
    }

    public static void saveEquipment(String equipmentId, String gearCategory, String gearSubCategory, String make,
                                     String model, double costPerUnit, int quantity) {
        try {
            final String URL = "http://localhost:8080/equipment/create";
            Equipment equipment = new EquipmentFactory().createEquipment(equipmentId, gearCategory, gearSubCategory, make,
                    model, null, quantity, costPerUnit, "images/equipment/" + equipmentId + ".jpg");
            Gson g = new Gson();
            String jsonString = g.toJson(equipment);
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

    public static String[] getCategoryIds() {
        try {
            ArrayList<String> equipmentCategoriesList = new ArrayList<>();
            equipmentCategoriesList.add(" ");
            final String URL = "http://localhost:8080/equipment/getEquipmentCategories";
            String responseBody = run(URL);
            JSONArray equipmentCategories = new JSONArray(responseBody);

            for (int i = 0; i < equipmentCategories.length(); i++) {
                JSONObject equipment = equipmentCategories.getJSONObject(i);

                Gson g = new Gson();
                EquipmentCategory e = g.fromJson(equipment.toString(), EquipmentCategory.class);
                equipmentCategoriesList.add(e.getGearCategoryID());
            }

            Object[]objectArray = equipmentCategoriesList.toArray();
            String[] categories = Arrays.copyOf(objectArray, objectArray.length, String[].class);
            return categories;

        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static Equipment getEquipmentById(String selectedId) {
        try {

            final String URL = "http://localhost:8080/equipment/read/" + selectedId;
            String responseBody = run(URL);
            JSONObject equipmentInfo = new JSONObject(responseBody);
            Gson g = new Gson();
            Equipment equipment = g.fromJson(equipmentInfo.toString(), Equipment.class);

            return equipment;

        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;

    }

    public static void updateEquipment(String equipmentId, String gearCategory, String gearSubCategory, String make,
                                     String model, double costPerUnit, int quantity) {
        try {
            final String URL = "http://localhost:8080/equipment/update";
            Equipment equipment = new EquipmentFactory().createEquipment(equipmentId, gearCategory, gearSubCategory, make,
                    model, null, quantity, costPerUnit, "images/equipment/" + equipmentId + ".jpg");
            Gson g = new Gson();
            String jsonString = g.toJson(equipment);
            String r = put(URL, jsonString);
            if(r != null) {
                JOptionPane.showMessageDialog(null, "Record has been updated.");
            }
            else {
                JOptionPane.showMessageDialog(null, "Record was not updated.");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void deleteEquipment(String equipmentId) {
        try {
            final String URL = "http://localhost:8080/equipment/delete/" + equipmentId;
            String response = delete(URL);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

