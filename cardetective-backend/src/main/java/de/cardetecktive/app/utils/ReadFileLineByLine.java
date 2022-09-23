package de.cardetecktive.app.utils;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class ReadFileLineByLine {

    public static byte[] fileReader() {

        try {
            File file = new File("D:/cardetective_aqa/cardetective-backend/src/main/resources/graphql/autotrader_makes_query.txt");

            FileReader fr = new FileReader(file);

            BufferedReader reader = new BufferedReader(fr);

            String line = reader.readLine();
            while (line != null) {
                System.out.println(line);
                line = reader.readLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return new byte[0];
    }
}
