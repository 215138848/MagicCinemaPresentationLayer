package za.ac.cput.service;

import com.google.gson.Gson;
import kong.unirest.json.JSONArray;
import kong.unirest.json.JSONObject;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import za.ac.cput.entity.Employee;
import za.ac.cput.entity.rent.EquipmentRental;

import java.io.IOException;
import java.util.ArrayList;

public class EmployeeFunctionality {
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

    public static Employee getEmployeeByPassword(int password) {
        try {

            final String URL = "http://localhost:8080/employee/readpassword/" + password;
            String responseBody = run(URL);
            JSONObject employeeInfo = new JSONObject(responseBody);
            Gson g = new Gson();
            Employee employee = g.fromJson(employeeInfo.toString(), Employee.class);
            System.out.println(employee);

            return employee;

        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
