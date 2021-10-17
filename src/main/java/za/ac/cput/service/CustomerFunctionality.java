package za.ac.cput.service;

import com.google.gson.Gson;
import kong.unirest.json.JSONArray;
import kong.unirest.json.JSONObject;
import okhttp3.*;
import za.ac.cput.entity.Client;
import za.ac.cput.entity.Employee;
import za.ac.cput.factory.ClientFactory;

import javax.swing.*;
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
            }

            Object[]objectArray = equipmentCategoriesList.toArray();
            String[] categories = Arrays.copyOf(objectArray, objectArray.length, String[].class);
            return categories;

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

    public static void saveCustomer(String customerName, String customerSurname, String customerNumber) {
        try {
            final String URL = "http://localhost:8080/client/create";
            Client customer = new ClientFactory().createClient(customerName, customerSurname, customerNumber);
            Gson g = new Gson();
            String jsonString = g.toJson(customer);
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

    public static Client getClientById(String id) {
        try {

            final String URL = "http://localhost:8080/client/read/" + id;
            String responseBody = run(URL);
            JSONObject clientInfo = new JSONObject(responseBody);
            Gson g = new Gson();
            Client client = g.fromJson(clientInfo.toString(), Client.class);

            return client;

        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static void updateCustomer(Client updateClient) {
        try {
            final String URL = "http://localhost:8080/client/update";
            Gson g = new Gson();
            String jsonString = g.toJson(updateClient);
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

    public static void deleteCustomer(String id) {
        try {
            final String URL = "http://localhost:8080/client/delete/" + id;
            String response = delete(URL);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
