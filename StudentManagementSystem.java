import java.util.Scanner;      

/* ========================= Student class ========================= */
class Student {

    private String studentName;    
    private Integer[] marks;       

    /* Constructor: set the name and create an empty marks array        */
    public Student(String studentName, int numberOfSubjects) {
        this.studentName = studentName;
        this.marks       = new Integer[numberOfSubjects]; // elements null
    }

    /* Prompt the user to enter marks for every subject (0-100)          */
    public void enterMarks(Scanner sc) {
        System.out.println("Enter marks for " + studentName + ":");

        for (int i = 0; i < marks.length; i++) {
            while (true) {
                try {
                    System.out.print("Subject " + (i + 1) + " Marks: ");
                    int mark = sc.nextInt();                // primitive int

                    if (mark < 0 || mark > 100) {           // range check
                        throw new IllegalArgumentException(
                                "Marks must be between 0 and 100.");
                    }

                    marks[i] = mark;    // Autoboxing: int → Integer
                    break;              // valid mark entered → exit loop
                } catch (Exception e) { // catches InputMismatch or IllegalArg
                    System.out.println(
                            "Invalid input! Please enter a valid number (0-100).");
                    sc.nextLine();      // clear buffer so we can read again
                }
            }
        }
    }

    
    public double calculateAverage() {
        int sum = 0;
        for (Integer m : marks) {
            sum += m;       // Unboxing: Integer → int
        }
        return (double) sum / marks.length;
    }

   
    public int getHighestMark() {
        int highest = marks[0];       // Unboxing first element
        for (Integer m : marks) {
            if (m > highest) highest = m;
        }
        return highest;
    }

  
    public int getLowestMark() {
        int lowest = marks[0];
        for (Integer m : marks) {
            if (m < lowest) lowest = m;
        }
        return lowest;
    }

   
    public void displayReport() {
        System.out.println("\n===== Report Card =====");
        System.out.println("Student: " + studentName);

        System.out.print("Marks: ");
        for (Integer m : marks) {
            System.out.print(m + " ");
        }

        System.out.println("\nAverage: " + calculateAverage());
        System.out.println("Highest: " + getHighestMark());
        System.out.println("Lowest : " + getLowestMark());
    }
}

/* =====================main class ======================= */
public class StudentManagementSystem {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        System.out.print("Enter number of students: ");
        int studentCount = sc.nextInt();
        sc.nextLine();                       

        Student[] students = new Student[studentCount];

        for (int i = 0; i < studentCount; i++) {

            System.out.print("\nEnter name of Student " + (i + 1) + ": ");
            String name = sc.nextLine();

            System.out.print("Enter number of subjects for " + name + ": ");
            int subjectCount = sc.nextInt();
            sc.nextLine();                  

            students[i] = new Student(name, subjectCount);
            students[i].enterMarks(sc);      
        }

       
        System.out.println("\n========= All Students Report =========");
        for (Student s : students) {
            s.displayReport();
        }
    }
}
