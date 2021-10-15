package za.ac.cput.service;

import com.google.gson.Gson;
import okhttp3.*;
import za.ac.cput.entity.rentalcart.Cart;
import za.ac.cput.factory.CartFactory;
import za.ac.cput.util.GenericHelper;

import javax.swing.*;
import java.io.IOException;

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
}
