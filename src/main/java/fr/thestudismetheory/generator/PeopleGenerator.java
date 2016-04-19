package fr.thestudismetheory.generator;

import fr.thestudismetheory.Resources;
import fr.thestudismetheory.data.*;
import fr.thestudismetheory.data.enums.InstitutionType;
import fr.thestudismetheory.data.enums.StudentFlaw;
import fr.thestudismetheory.data.subentity.Graduate;

import java.util.*;

public class PeopleGenerator {

    private static final String[] names = Resources.STUDENT_NAMES;
    private static Random random = new Random();
    private static String[] specialStudents = {
            "Alexandre Sanigou",
            "Benoit Petiteau",
            "Nazim Lachter",
            "Maeva Lauzier",
            "Vincent Quatrevieux"
    };

    private static String nameGeneration() {
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

    private static Date randomDateBetweenTwoYears(int start, int end) {
        GregorianCalendar gc = new GregorianCalendar();

        int year = start + random.nextInt(end - start);
        gc.set(gc.YEAR, year);

        int day = 1 + random.nextInt(gc.getActualMaximum(gc.DAY_OF_YEAR) + 1);
        gc.set(gc.DAY_OF_YEAR, day);

        return gc.getTime();
    }

    private static int studismGeneration() {
        int studism = 0;
        return studism;
    }

    private static int procrassGeneration() {
        int studism = 0;
        return studism;
    }

    /*
     * Génération d'un nouvel étudiant
     */
    public static Student newStudent(Date currentDate, City city) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(currentDate);

        String name = nameGeneration();
        Date birth = randomDateBetweenTwoYears(calendar.get(Calendar.YEAR) - 18, (calendar.get(Calendar.YEAR) - 18) + 4);
        int studism = studismGeneration();
        int procrass = procrassGeneration();
        Set<StudentFlaw> flaws = new HashSet<>();
        
        //TODO
        flaws.add(StudentFlaw.PROCRASTINATION);
        flaws.add(StudentFlaw.TRAVAILLEUR);
        
        Map<Category, Integer> interests = new HashMap<>();
        Map<Integer, Graduate> graduations = new HashMap<>();

        return new Student(Model.ID_NOT_DEFINED, name, birth, city, studism, procrass, flaws, interests, graduations);
    }

    /*
     * Génération de plusieurs nouveaux étudiants
     */
    public static List<Student> newStudentList(int number, Date currentDate, City currentCity) {
        List<Student> list = new ArrayList<>();
        for (int i = 0; i < number; i++)
            list.add(newStudent(currentDate, currentCity));
        return list;
    }

    /*
     * Génération d'un nouvel enseignant
     */
    public static Teacher newTeacher(Date currentDate) {
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
        birth = randomDateBetweenTwoYears(calendar.get(Calendar.YEAR) - 25, (calendar.get(Calendar.YEAR) - 25) + 20);
        //}

        Date entering = currentDate;
        int charisma = 0;
        int skill = 0;
        int punct = 0;
        int teachSkill = 0;
        Category category = new Category(1, "Test", 0);

        Set<StudentFlaw> cond = new HashSet<>();
        Division division = new Division(new School(0, new City(0, "Test", 0),
                new Institution(0, "Test", InstitutionType.PUBLIC), "Test", 0, 0, 0), category, 0, 0, cond);

        return new Teacher(Model.ID_NOT_DEFINED, name, birth, entering, charisma, skill, punct, teachSkill, category, division);
    }

    /*
     * Génération de plusieurs nouveaux enseignants
     */
    public static List<Teacher> newTeacherList(int number, Date currentDate) {
        List<Teacher> list = new ArrayList<>();
        for (int i = 0; i < number; i++)
            list.add(newTeacher(currentDate));
        return list;
    }

}
