package iv;

import java.util.*;

public class Student {
    String name;
    int marks;

    Student(String name, int marks) {
        this.name = name;
        this.marks = marks;
    }

}

class MainMethod {
    static ArrayList<Student> students;

    public static void main(String[] args) {
       // students = new ArrayList<>();
        insertStudents();
    }

    static void insertStudents() {
        students = new ArrayList<>();
        Student student1 = new Student("Rahul", 50);
        Student student2 = new Student("Abhinav", 45);
        Student student3 = new Student("Rahul", 65);
        Student student4 = new Student("Ajay", 25);
        Student student5 = new Student("Ajith", 45);
        students.add(student1);
        students.add(student2);
        students.add(student3);
        students.add(student4);
        students.add(student5);
        sortListByName();
    }

    static void sortListByName() {
        Comparator<Student> c = Comparator.comparing(student -> student.name);
        students.sort(c);
        for (Student s: students) {
            println(s.name + " :: " + s.marks);
        }
        println("--------------");
        Comparator<Student> c1 = Comparator.comparing(student -> student.marks);
        students.sort(c1);

        for (Student s: students) {
            println(s.name + " :: " + s.marks);
        }

    }

   static void println(String message) {
        System.out.println(message);
    }
}

