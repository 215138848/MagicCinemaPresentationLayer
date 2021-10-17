package za.ac.cput.service;

import com.google.gson.Gson;
import kong.unirest.json.JSONArray;
import kong.unirest.json.JSONObject;
import okhttp3.*;
import za.ac.cput.entity.Employee;
import za.ac.cput.entity.catelag.Equipment;
import za.ac.cput.entity.rent.EquipmentRental;
import za.ac.cput.factory.EquipmentFactory;

import javax.swing.*;
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

            return employee;

        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static ArrayList<Employee> getAllEmployees() {
            try {
                ArrayList<Employee> employeeArrayList = new ArrayList<>();
                final String URL = "http://localhost:8080/employee/getall/";
                String responseBody = run(URL);
                JSONArray employees = new JSONArray(responseBody);

                for (int i = 0; i < employees.length(); i++) {
                    JSONObject employee = employees.getJSONObject(i);

                    Gson g = new Gson();
                    Employee e = g.fromJson(employee.toString(), Employee.class);
                    employeeArrayList.add(e);
                }
                return employeeArrayList;

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

    public static void saveEmployee(Employee employee) {
        try {
            final String URL = "http://localhost:8080/employee/create";
            Gson g = new Gson();
            String jsonString = g.toJson(employee);
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

    public static Employee getEmployeeById(String id) {
        try {

            final String URL = "http://localhost:8080/employee/read/" + id;
            String responseBody = run(URL);
            JSONObject employeeInfo = new JSONObject(responseBody);
            Gson g = new Gson();
            Employee employee = g.fromJson(employeeInfo.toString(), Employee.class);

            return employee;

        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static void updateEmployee(Employee updateEmployee) {
        try {
            final String URL = "http://localhost:8080/employee/update";
            Gson g = new Gson();
            String jsonString = g.toJson(updateEmployee);
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

    public static void deleteEmployee(String id) {
        try {
            final String URL = "http://localhost:8080/employee/delete/" + id;
            String response = delete(URL);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
