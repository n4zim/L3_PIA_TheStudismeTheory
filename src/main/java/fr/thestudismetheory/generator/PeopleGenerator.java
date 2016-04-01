package fr.thestudismetheory.generator;

import fr.thestudismetheory.Resources;
import fr.thestudismetheory.data.*;
import fr.thestudismetheory.data.enums.StudentFlaw;
import fr.thestudismetheory.data.subentity.Graduate;
import fr.thestudismetheory.data.Category;

import java.util.*;

public class PeopleGenerator {

    private Random random = new Random();
    private final String[] names = Resources.STUDENT_NAMES;

    private String[] specialStudents = {
            "Alexandre Sanigou",
            "Benoit Petiteau",
            "Nazim Lachter",
            "Maeva Lauzier",
            "Vincent Quatrevieux"
    };

    private String nameGeneration() {
        String firstName = "", lastName = "";

        int rand = random.nextInt(names.length);
        Scanner firstNameGen = new Scanner(names[rand]).useDelimiter("\\t");
        if (firstNameGen.hasNext()) firstName = firstNameGen.next();
        firstNameGen.close();

        rand = random.nextInt(names.length);
        Scanner lastNameGen = new Scanner(names[rand]).useDelimiter("\\t");
        if (lastNameGen.hasNext()) lastName = lastNameGen.next();
        lastNameGen.close();

        return firstName.substring(0, 1).toUpperCase() + firstName.substring(1)
                + " " + lastName.substring(0, 1).toUpperCase() + lastName.substring(1);
    }

    private Date randomDateBetweenTwoYears(int start, int end) {
        GregorianCalendar gc = new GregorianCalendar();

        int year = start + random.nextInt(end - start);
        gc.set(gc.YEAR, year);

        int day = 1 + random.nextInt(gc.getActualMaximum(gc.DAY_OF_YEAR) + 1);
        gc.set(gc.DAY_OF_YEAR, day);

        return gc.getTime();
    }

    private int studismGeneration() {
        int studism = 0;
        return studism;
    }

    private int procrassGeneration() {
        int studism = 0;
        return studism;
    }

    /*
     * Génération d'un nouvel étudiant
     */
    public Student newStudent(Date currentDate, City city) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(currentDate);

        String name = nameGeneration();
        Date birth = randomDateBetweenTwoYears(calendar.get(Calendar.YEAR)-18, (calendar.get(Calendar.YEAR)-18)+4);
        int studism = studismGeneration();
        int procrass = procrassGeneration();
        Set<StudentFlaw> flaws = new HashSet<>();
        Map<Category, Integer> interests = new HashMap<>();
        Map<Integer, Graduate > graduations = new HashMap<>();

        return new Student(Model.ID_NOT_DEFINED, name, birth, city, studism, procrass, flaws, interests, graduations);
    }

    /*
     * Génération de plusieurs nouveaux étudiants
     */
    public List<Student> newStudentList(int number, Date currentDate, City currentCity) {
        List<Student> list = new ArrayList<>();
        for(int i = 0; i < number; i++)
            list.add(newStudent(currentDate, currentCity));
        return list;
    }

    /*
     * Génération d'un nouvel enseignant
     */
    public Teacher newTeacher(Date currentDate) {
        String name;
        Date birth;

        // TODO : Faire un système qui check si des étudiants ont finis leurs études pour devenir prof
        boolean convertFromStudent = false;
        //Student student = newStudent(currentDate, new City(0,"Test",1)); // A modifier avec l'existant
        //name = student.getName();
        //birth = student.getBirth();
        //convertFromStudent = true;
        // Fin du T O D O

        //if(!convertFromStudent) {
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(currentDate);

            name = nameGeneration();
            birth = randomDateBetweenTwoYears(calendar.get(Calendar.YEAR)-25, (calendar.get(Calendar.YEAR)-25)+20);
        //}

        Date entering = currentDate;
        int charisma = 0;
        int skill = 0;
        int punct = 0;
        int teachSkill = 0;
        Category category = new Category(1, "Test", 0);
        return new Teacher(Model.ID_NOT_DEFINED, name, birth, entering, charisma, skill, punct, teachSkill, category);
    }

    /*
     * Génération de plusieurs nouveaux enseignants
     */
    public List<Teacher> newTeacherList(int number, Date currentDate) {
        List<Teacher> list = new ArrayList<>();
        for(int i = 0; i < number; i++)
            list.add(newTeacher(currentDate));
        return list;
    }

}
