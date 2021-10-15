package za.ac.cput.entity;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

@Entity
@Table(name = "employee")
public class Employee implements Serializable {
    @Id
    private String employeeNumber;
    private String username;
    private String name, surname, email, dateCreated;
    private int password;
    private String role;
    private String imagePath;

    public Employee(){};

    private Employee(Builder builder) {
        this.employeeNumber = builder.employeeNumber;
        this.username = builder.username;
        this.name = builder.name;
        this.surname = builder.surname;
        this.email = builder.email;
        this.password = builder.password;
        this.role = builder.role;
        this.dateCreated = builder.dateCreated;
        this.imagePath = builder.imagePath;
    }

    @Override
    public String toString() {
        return "Employee{" +
                "employeeNumber='" + employeeNumber + '\'' +
                "username='" + username + '\'' +
                ", name='" + name + '\'' +
                ", surname='" + surname + '\'' +
                ", userType='" + role + '\'' +
                ", password='" + password + '\'' +
                ", email='" + email + '\'' +
                ", dateCreated='" + dateCreated + '\'' +
                '}';
    }

    public static class Builder{
        private String employeeNumber, username, name, surname, email, dateCreated;
        private int password;
        private String role, imagePath;

        public Builder setEmployeeNumber(String employeeNumber){
            this.employeeNumber = employeeNumber;
            return this;
        }
        public Builder setUsername(String username){
            this.username = username;
            return this;
        }
        public Builder setName(String name){
            this.name = name;
            return this;
        }
        public Builder setSurname(String surname){
            this.surname = surname;
            return this;
        }
        public Builder setEmail(String email){
            this.email = email;
            return this;
        }
        public Builder setRole(String role){
            this.role = role;
            return this;
        }
        public Builder setPassword(int password){
            this.password = password;
            return this;
        }
        public Builder setDateCreated(String dateCreated){
            this.dateCreated = dateCreated;
            return this;
        }
        public Builder setImagePath(String imagePath) {
            this.imagePath = imagePath;
            return this;
        }
        public Employee build(){
            return new Employee(this);
        }

        public Builder copy(Employee employee){
            this.employeeNumber = employee.employeeNumber;
            this.username = employee.username;
            this.name = employee.name;
            this.surname = employee.surname;
            this.email = employee.email;
            this.password = employee.password;
            this.role = employee.role;
            this.dateCreated = employee.dateCreated;
            this.imagePath = employee.imagePath;
            return this;
        }

    }//Inner Class
    public String getEmployeeNumber() {return employeeNumber;}
    public String getName() {return name;}
    public String getSurname() {return surname;}
    public String getEmail() {return email;}
    public String getRole() {
        return role;
    }
    public int getPassword() {return password;}
    public String getDateCreated() {return dateCreated;}
    public String getImagePath() {return imagePath;}
    public String getUsername() {
        return username;
    }
}// Outer Class
