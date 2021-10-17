package za.ac.cput.service;

import com.google.gson.Gson;
import kong.unirest.json.JSONArray;
import kong.unirest.json.JSONObject;
import okhttp3.*;
import za.ac.cput.entity.catelag.Equipment;
import za.ac.cput.entity.rentalcart.Cart;
import za.ac.cput.factory.CartFactory;
import za.ac.cput.util.GenericHelper;

import javax.swing.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

public class CartFunctionality {
    public static final MediaType JSON
            = MediaType.get("application/json; charset=utf-8");
    private static OkHttpClient client = new OkHttpClient();

    public static void saveCart(String equipmentId, double price, int quantity) {
        try {
            final String URL = "http://localhost:8080/cart/create";
            String customerId = GenericHelper.getUserName();
            double subtotal = price * quantity;
            Cart cart = CartFactory.createCart(customerId, equipmentId, price, "Not Processed", quantity, subtotal);
            Gson g = new Gson();
            String jsonString = g.toJson(cart);
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

    public static void clearCart(String customerId) {
        try {

            final String URL = "http://localhost:8080/cart/clearcart/" + customerId;
            String responseBody = run(URL);
            if(responseBody != null) {
                JOptionPane.showMessageDialog(null, "Cart has been cleared.");
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

    public static Set<Cart> getCustomerCartInformation(String customerId) {
        try {
            Set<Cart> cartSet = new HashSet<>();
            final String URL = "http://localhost:8080/cart/getCartFromCustomerId/" + customerId;
            String responseBody = run(URL);
            JSONArray carts = new JSONArray(responseBody);

            for (int i = 0; i < carts.length(); i++) {
                JSONObject cart = carts.getJSONObject(i);

                Gson g = new Gson();
                Cart c = g.fromJson(cart.toString(), Cart.class);
                cartSet.add(c);
            }
            return cartSet;

        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
