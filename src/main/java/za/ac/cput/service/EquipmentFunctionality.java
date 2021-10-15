package za.ac.cput.service;

import com.google.gson.Gson;
import kong.unirest.json.JSONArray;
import kong.unirest.json.JSONObject;
import kong.unirest.json.JSONString;
import za.ac.cput.entity.catelag.Equipment;

import okhttp3.*;
import za.ac.cput.entity.catelag.EquipmentCategory;

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
                System.out.println(e.toString());
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
                System.out.println(e.toString());
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
                System.out.println(e.toString());
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
            System.out.println(responseBody);
            return responseBody;

        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
