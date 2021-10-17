package za.ac.cput.factory;


import za.ac.cput.entity.Client;
import za.ac.cput.util.GenericHelper;

public class ClientFactory {
    public static Client createClient(String name, String surname, String contactNumber) {
        String clientID = GenericHelper.generateId();
        Client client = new Client.Builder()
                .setClientID(clientID)
                .setName(name)
                .setSurname(surname)
                .setContactNumber(contactNumber)
                .build();

        return client;
    }
}
