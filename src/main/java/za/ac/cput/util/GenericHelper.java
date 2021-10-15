package za.ac.cput.util;

import java.io.*;
import java.util.Scanner;
import java.util.UUID;

public class GenericHelper {

    public static String generateId() {

        return UUID.randomUUID().toString();
    }

    public static void createUserTracker(String currentId) {
        String file = "2864437280.txt";
        String user = currentId;

        File tmpDir = new File(file);
        boolean exists = tmpDir.exists();

        if (!exists) {
            try {
                FileOutputStream fos = new FileOutputStream(file);
                fos.write(currentId.getBytes());
                fos.flush();
                fos.close();
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            try {
                PrintWriter writer = new PrintWriter(file);
                writer.print("");
                writer.close();

                FileOutputStream fos = new FileOutputStream(file);
                fos.write(currentId.getBytes());
                fos.flush();
                fos.close();
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }


        }
    }

    public static String getUserName() {
        String file = "2864437280.txt";
        String user = "";

        try {
            File myObj = new File(file);
            Scanner myReader = new Scanner(myObj);
            while (myReader.hasNextLine()) {
                user = myReader.nextLine();
                System.out.println(user);
            }
            myReader.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        return user;

    }
}
