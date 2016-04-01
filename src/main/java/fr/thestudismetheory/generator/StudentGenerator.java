package fr.thestudismetheory.generator;

import fr.thestudismetheory.data.City;
import fr.thestudismetheory.data.Model;
import fr.thestudismetheory.data.Student;
import fr.thestudismetheory.data.dao.StudentDAO;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Hashtable;
import java.util.Random;
import java.util.Vector;

public class StudentGenerator {

    private StudentDAO studentDAO;

    // Futur tableau de Student
    private String[] specialStudents = {
        "Alexandre Sanigou",
        "Benoit Petiteau",
        "Nazim Lachter",
        "Maeva Lauzier",
        "Vincent Quatrevieux"
    };

    public StudentGenerator(StudentDAO studentDAO) {
        this.studentDAO = studentDAO;
    }

    public String newStudent() {
        return "";
    }

}
