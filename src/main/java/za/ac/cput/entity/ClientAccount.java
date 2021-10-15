package za.ac.cput.entity;

import com.sun.istack.NotNull;

import javax.persistence.*;

@Entity
@Table(name = "clientaccount")
public class ClientAccount {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    @NotNull
    private String name;
    @NotNull
    private String surname;
    @NotNull
    private int accountNumber;
    @NotNull
    private int contactNumber;
    @NotNull
    private int numberBorrowed;


    private ClientAccount(Builder builder) {
        this.id = builder.id;
        this.name = builder.name;
        this.surname = builder.surname;
        this.accountNumber = builder.accountNumber;
        this.contactNumber = builder.contactNumber;
        this.numberBorrowed = builder.numberBorrowed;
    }

    @Override
    public String toString() {
        return "Cart{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", surname='" + surname + '\'' +
                ", accountNumber=" + accountNumber +
                ", contactNumber=" + contactNumber +
                ", numberBorrowed=" + numberBorrowed +
                '}';
    }

    public static class Builder {
        private int id, accountNumber, contactNumber, numberBorrowed;
        private String name, surname;

        public Builder setId(int id) {
            this.id = id;
            return this;
        }

        public Builder setAccountNumber(int accountNumber) {
            this.accountNumber = accountNumber;
            return this;
        }

        public Builder setContactNumber(int contactNumber) {
            this.contactNumber = contactNumber;
            return this;
        }

        public Builder setNumberBorrowed(int numberBorrowed) {
            this.numberBorrowed = numberBorrowed;
            return this;
        }

        public Builder setName(String name) {
            this.name = name;
            return this;
        }

        public Builder setSurname(String surname) {
            this.surname = surname;
            return this;
        }

        public ClientAccount build() {
            return new ClientAccount(this);
        }

        public Builder copy(ClientAccount clientAccount) {
            this.id = clientAccount.id;
            this.name = clientAccount.name;
            this.surname = clientAccount.surname;
            this.accountNumber = clientAccount.accountNumber;
            this.contactNumber = clientAccount.contactNumber;
            this.numberBorrowed = clientAccount.numberBorrowed;
            return this;
        }
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getSurname() {
        return surname;
    }

    public int getAccountNumber() {
        return accountNumber;
    }

    public int getContactNumber() {
        return contactNumber;
    }

    public int getNumberBorrowed() {
        return numberBorrowed;
    }
}
