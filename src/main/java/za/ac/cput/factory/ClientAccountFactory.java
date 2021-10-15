package za.ac.cput.factory;


import za.ac.cput.entity.ClientAccount;

public class ClientAccountFactory {

    public static ClientAccount createClientAccount(String accountNumber, String numberBorrowed){

        ClientAccount clientAccount = new ClientAccount.Builder()
                .setAccountNumber(Integer.parseInt(accountNumber))
                .setNumberBorrowed(Integer.parseInt(numberBorrowed))
                .build();
        return clientAccount;
    }
}
