package za.ac.cput.service;

import com.google.gson.Gson;
import kong.unirest.json.JSONArray;
import kong.unirest.json.JSONObject;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import za.ac.cput.entity.Client;
import za.ac.cput.entity.catelag.Equipment;
import za.ac.cput.entity.catelag.EquipmentCategory;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

public class CustomerFunctionality {
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

    public static ArrayList<Client> getAllClients() {
        try {
            ArrayList<Client> clientArrayList = new ArrayList<>();
            final String URL = "http://localhost:8080/client/getall";
            String responseBody = run(URL);
            JSONArray clients = new JSONArray(responseBody);

            for (int i = 0; i < clients.length(); i++) {
                JSONObject client = clients.getJSONObject(i);

                Gson g = new Gson();
                Client e = g.fromJson(client.toString(), Client.class);
                clientArrayList.add(e);
                System.out.println(e.toString());
            }
            return clientArrayList;

        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static String[] getClientNames() {
        try {
            ArrayList<String> equipmentCategoriesList = new ArrayList<>();
            final String URL = "http://localhost:8080/client/getall";
            String responseBody = run(URL);
            JSONArray clientNames = new JSONArray(responseBody);

            for (int i = 0; i < clientNames.length(); i++) {
                JSONObject client = clientNames.getJSONObject(i);

                Gson g = new Gson();
                Client e = g.fromJson(client.toString(), Client.class);
                equipmentCategoriesList.add(e.getName() + " " + e.getSurname());
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
}
