package pt.pa.patterns;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class StudentRecord {

    private String studentId;
    private String studentName;

    private Map<Course, Integer> record;

    public StudentRecord(String studentId, String studentName) {
        this.studentId = studentId;
        this.studentName = studentName;

        this.record = new HashMap<>();
    }

    public String getStudentId() {
        return studentId;
    }

    public String getStudentName() {
        return studentName;
    }

    public double computeAverage() {
        double sum = 0;
        for (Integer grade : record.values()) {
            sum += grade;
        }
        return sum / record.size();
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder(String.format("Record of: %s | %s \n", studentId, studentName));

        String header = String.format("%6s | %10s | %50s | %8s | %5s \n",
                "Year",
                "Course ID",
                "Name",
                "Semester",
                "Grade");

        sb.append(header);

        for (Map.Entry<Course, Integer> entry : record.entrySet()) {
            Course c = entry.getKey();
            int grade = entry.getValue();

            String line = String.format("%6d | %10s | %50s | %8s | %5s \n",
                    c.getYear(),
                    c.getId(),
                    c.getName(),
                    c.getSemester(),
                    grade);

            sb.append(line);
        }

        return sb.toString();
    }

    public void importFromFile(String filename) throws FileNotFoundException {
        try (BufferedReader br = new BufferedReader(new FileReader(filename))) {
            String line;

            /* discard header*/
            br.readLine();

            while ((line = br.readLine()) != null) {
                String[] values = line.split(",");

                int year = Integer.valueOf( values[0] );
                String name = values[1].trim();
                String id = values[2].trim();
                String semester = values[3].trim();
                int ects = Integer.valueOf( values[4] );

                int grade = Integer.valueOf( values[5] );

                Course c = new Course(id, year, name, semester, ects);

                record.put(c, grade);

            }
        } catch(IOException e) {
            System.err.println(e.getMessage());
        }
    }
}
