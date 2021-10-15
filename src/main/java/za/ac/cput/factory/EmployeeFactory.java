package za.ac.cput.factory;
import za.ac.cput.entity.Employee;
import za.ac.cput.util.GenericHelper;

import java.text.SimpleDateFormat;
import java.util.Date;

public class EmployeeFactory {
    public static Employee createEmployee(String username, String name, String surname,String email, int password, String role){
        String employeeNumber = GenericHelper.generateId();

        Date date = new Date();
        SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
        String currentDate = formatter.format(date);

        Employee employee = new Employee.Builder()
                .setEmployeeNumber(employeeNumber)
                .setUsername(username)
                .setName(name)
                .setSurname(surname)
                .setEmail(email)
                .setPassword(password)
                .setRole(role)
                .setDateCreated(currentDate)
                .build();

        return employee;
    }

}
