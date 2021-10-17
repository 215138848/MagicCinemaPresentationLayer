package za.ac.cput.service;

import com.google.gson.Gson;
import kong.unirest.json.JSONArray;
import kong.unirest.json.JSONObject;
import okhttp3.*;
import za.ac.cput.entity.ClientAccount;
import za.ac.cput.util.GenericHelper;

import javax.swing.*;
import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

public class ClientAccountFunctionality {
    public static final MediaType JSON
            = MediaType.get("application/json; charset=utf-8");
    private static OkHttpClient client = new OkHttpClient();

    public static void saveClientAccountTable(String surname , String name, int id, String accountNumber, int contactNumber, String numberBorrowed) {
        try {
            final String URL = "http://localhost:8080/clientaccount/create";
            String clientID = GenericHelper.getUserName();
            Gson g = new Gson();
            String jsonString = g.toJson(client);
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

    public static String delete(final String url, String json) throws IOException {
        RequestBody body = RequestBody.create(JSON, json);
        Request request = new Request.Builder()
                .delete(body)
                .addHeader("Accept", "application/json")
                .url(url)
                .build();
        try(Response response = client.newCall(request).execute()) {
            return response.body().string();
        }
    }

    public static void clearTable(String clientID) {
        try {

            final String URL = "http://localhost:8080/clientaccount/cleartable/" + clientID;
            String responseBody = run(URL);
            if(responseBody != null) {
                JOptionPane.showMessageDialog(null, "Table cleared.");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
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

    private static String run(String URL) throws IOException {
        Request request = new Request.Builder()
                .url(URL)
                .build();
        try(Response response = client.newCall(request).execute()) {
            return response.body().string();
        }
    }

    public static Set<ClientAccount> getInformation(String clientID) {
        try {
            Set<ClientAccount> clientAccountSet = new HashSet<>();
            final String URL = "http://localhost:8080/cart/getTableInfo/" + clientID;
            String responseBody = run(URL);
            JSONArray clientAccounts = new JSONArray(responseBody);

            for (int i = 0; i < clientAccounts.length(); i++) {
                JSONObject clientAccount = clientAccounts.getJSONObject(i);

                Gson g = new Gson();
                ClientAccount c = g.fromJson(clientAccount.toString(), ClientAccount.class);
                clientAccountSet.add(c);
            }
            return clientAccountSet;

        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
