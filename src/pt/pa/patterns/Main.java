package pt.pa.patterns;

import java.io.FileNotFoundException;

public class Main {

    public static void main(String[] args) {

        StudentRecord record = new StudentRecord("2018", "João Meireles");
        try {
            record.importFromFile("record.csv");
        } catch (FileNotFoundException e) {
            System.err.println(e.getMessage());
        }

        /* Print record */
        System.out.println(record);

        /* Compute average */
        double average = record.computeAverage();
        System.out.println(String.format("Course average: %.2f", average));
    }
}
