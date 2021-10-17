package za.ac.cput.util;

import java.io.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.Scanner;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

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

    public static void createEmployeeTracker(String currentId) {
        String file = "2864437281.txt";
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

    public static String getEmployeeId() {
        String file = "2864437281.txt";
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

    public static int CalculateDifferenceBetweenDates(String dateOne, String dateTwo) throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy", Locale.ENGLISH);
        Date firstDate = sdf.parse(dateOne);
        Date secondDate = sdf.parse(dateTwo);

        long diffInMillies = Math.abs(secondDate.getTime() - firstDate.getTime());
        long diff = TimeUnit.DAYS.convert(diffInMillies, TimeUnit.MILLISECONDS);


        return (int) diff;
    }
}
