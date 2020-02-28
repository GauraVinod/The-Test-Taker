import java.io.*;
import java.util.*;
import java.lang.*;

class WrongPassword extends Exception
{
    WrongPassword(String announce)
    {
        super(announce);
    }
}

public class TheTestTaker {

    private static final int PasswordSize = 8;

    private static Scanner Parent_Scanner = new Scanner(System.in);

    public static void Student_Login() throws IOException {
        String Rollno;
        String studentPassword;

        System.out.println("Enter your Roll no. : ");
        Rollno = Parent_Scanner.nextLine();
        System.out.println();
        System.out.println("Enter your Password : ");
        studentPassword = Parent_Scanner.nextLine();
        System.out.println();

        File StudentFolder = new File("..\\TheTestTaker\\StudentDetails");
        try {
            if (!(StudentFolder.exists()))
                throw new FileNotFoundException("Folder Does not Exist!");
        } catch (Exception e) {
            System.out.println();
            System.out.println(e.getMessage());
            Student_Signup();
        }
        if (StudentFolder.exists()) {
            File StudentFiles = new File("..\\TheTestTaker\\StudentDetails\\" + Rollno + ".txt");

            try {
                if (!(StudentFiles.exists()))
                    throw new FileNotFoundException("File Does Not Exist");
            } catch (Exception e) {
                System.out.println();
                System.out.println(e.getMessage());
                Student_Signup();
            }
            if (StudentFiles.exists()) {
                BufferedReader S_Login_BR = new BufferedReader(new FileReader("..\\TheTestTaker\\StudentDetails\\" + Rollno + ".txt"));
                String PassCheck;
                S_Login_BR.readLine(); // skip gender
                S_Login_BR.readLine(); // skip name
                S_Login_BR.readLine(); // skip rollno
                S_Login_BR.readLine(); //skip semester
                PassCheck = S_Login_BR.readLine(); // go to password
                try {
                    if (!PassCheck.equals(studentPassword))
                        throw new WrongPassword("Wrong Password!");
                } catch (WrongPassword e) {
                    System.out.println();
                    System.out.println(e.getMessage());
                    System.out.println("Password doesn't match.");
                    Student_Login();
                }
                if (PassCheck.equals(studentPassword))
                    System.out.println("Logged In Successfully.");
                S_Login_BR.close();
            }
        }
    }

    public static void Student_Signup() throws IOException {
        File Student_Directory = new File("..\\TheTestTaker\\StudentDetails");
        if (Student_Directory.mkdir()) {
            System.out.println("A new Parent folder (StudentDetails) has been created.");
        } else {
            System.out.println("StudentDetails Folder exists.");
        }

        String studentGender;
        String studentName;
        String Rollno;
        int semester;
        String studentPassword;
        String ConfirmStudentPassword;

        System.out.println("Enter Your Gender : ");
        System.out.println("Enter 'M' for Male \nEnter 'F' for Female.\n\n");
        studentGender = Parent_Scanner.nextLine().toUpperCase();

        System.out.println("Please Enter Your Name : ");
        studentName = Parent_Scanner.nextLine();

        System.out.println("Enter Your Roll no. : ");
        Rollno = Parent_Scanner.nextLine();

        System.out.println("Enter Your Semester :");
        semester = Parent_Scanner.nextInt();

        do {
            System.out.println("Please Create your Password : \n");
            System.out.println("1. A password must have at least 8 characters.\n" +
                    "2. It must consist of only letters and digits.\n" +
                    "3. It must contain at least 2 digits \n");
            Parent_Scanner.nextLine();
            studentPassword = Parent_Scanner.nextLine();
            if (PasswordChecker(studentPassword)) {
                System.out.println("Password is valid!");
            } else {
                System.out.println("Password is not valid!");
            }

            System.out.println("Confirm Your Password : ");
            ConfirmStudentPassword = Parent_Scanner.nextLine();
            if (ConfirmStudentPassword.equals(studentPassword)) {
                System.out.println("Authentication Granted!");
            } else {
                System.out.println("Passwords don't match!");
                Student_Signup();
            }
        }while (!ConfirmStudentPassword.equals(studentPassword));

        File studentFile = new File("..\\TheTestTaker\\StudentDetails\\" + Rollno + ".txt");
        if (studentFile.createNewFile()) {
            System.out.println("Your Account has been created!");
        }
        FileWriterStudent();
    }

    public static void Teacher_Login() throws IOException {
        String teacherName;
        String teacherPassword;

        System.out.println("Enter your Name : ");
        teacherName = Parent_Scanner.nextLine();
        System.out.println();
        System.out.println("Enter your Password : ");
        teacherPassword = Parent_Scanner.nextLine();
        System.out.println();

        File TeacherFolder = new File("..\\TheTestTaker\\StudentDetails");
        try {
            if (!(TeacherFolder.exists()))
                throw new FileNotFoundException("Folder Does not Exist!");
        } catch (Exception e) {
            System.out.println();
            System.out.println(e.getMessage());
            Teacher_Signup();
        }
        if (TeacherFolder.exists()) {
            System.out.println("New Folder (TeacherDetails) has been created.");
            File TeacherFiles = new File("..\\TheTestTaker\\TeacherDetails\\" + teacherName + ".txt");

            try {
                if (!(TeacherFiles.exists()))
                    throw new FileNotFoundException("File Does Not Exist");
            } catch (Exception e) {
                System.out.println();
                System.out.println(e.getMessage());
                Teacher_Signup();
            }
            if (TeacherFiles.exists()) {
                BufferedReader T_Login_BR = new BufferedReader(new FileReader("..\\TheTestTaker\\StudentDetails\\" + teacherName + ".txt"));
                String PassCheck;
                T_Login_BR.readLine();
                PassCheck = T_Login_BR.readLine();
                try {
                    if (!(PassCheck.equals(teacherPassword)))
                        throw new WrongPassword("INVALID PASSWORD!");
                } catch (WrongPassword e) {
                    System.out.println();
                    System.out.println(e.getMessage());
                    System.out.println("Password doesn't match.");
                    T_Login_BR.close();
                    Teacher_Login();
                    
                }
                if (PassCheck.equals(teacherPassword))
                    System.out.println("Logged In Successfully.");

            T_Login_BR.close();
            }
            //T_Login_BR.close();
        }
        Teacher some_teacher= new Teacher(teacherName);
        some_teacher.welcome();
    }

    public static void Teacher_Signup() throws IOException {
        File Teacher_Directory = new File("..\\TheTestTaker\\TeacherDetails");
        if (Teacher_Directory.mkdir()) {
            System.out.println("A new Parent folder (TeacherDetails) has been created.");
        } else {
            System.out.println("TeacherDetails Folder exists.");
        }

        int teacherGender;
        String String_teacher_gender;
        String teacherName;
        int teacherDesignation;
        String String_teacher_designation;
        String teacherPassword;
        String ConfirmTeacherPassword;

        do{
        System.out.println("Select Your Gender : ");
        System.out.println("1.Male\n2.Female");
        teacherGender = Parent_Scanner.nextInt();
        if (teacherGender == 1) {
            String_teacher_gender = "Male";
            System.out.println("Please Enter Your Name : ");
            teacherName = Parent_Scanner.next();
            System.out.println("Welcome Mr." + teacherName);
            teacherName = "Mr."+teacherName;
        } else if (teacherGender == 2) {
            String_teacher_gender = "Female";
            System.out.println("Please Enter Your Name : ");
            teacherName = Parent_Scanner.next();
            System.out.println("Welcome Ms." + teacherName);
            teacherName = "Ms."+teacherName;
        }else
        {
            System.out.println("Enter a valid gender!");
        }
        }while(!(teacherGender == 1||teacherDesignation == 2));

        do {
            System.out.println("Select your Designation : ");
            System.out.println("1. Professor\n" + "2. Associate Proffessor\n" + "3. Intern");
            teacherDesignation = Parent_Scanner.nextLine;
            if (teacherDesignation==1)
                String_teacher_designation="Professor";
            if (teacherDesignation==2)
                String_teacher_designation="Associate Proffessor";
            if (teacherDesignation==3)
                String_teacher_designation="Intern";
        }
        while (!(teacherDesignation == 1 ||teacherDesignation == 2 ||teacherDesignation == 3));

        do {
            System.out.println("Please Create your Password : \n");
            System.out.println("1. A password must have at least 8 characters.\n" +
                    "2. It must consist of only letters and digits.\n" +
                    "3. It must contain at least 2 digits \n");
            Parent_Scanner.nextLine();
            teacherPassword = Parent_Scanner.nextLine();
            if (PasswordChecker(teacherPassword)) {
                System.out.println("Password is valid!");
            } else {
                System.out.println("Password is not valid!");
            }

            System.out.println("Confirm Your Password : ");
            ConfirmTeacherPassword = Parent_Scanner.nextLine();
            if (ConfirmTeacherPassword.equals(teacherPassword)) {
                System.out.println("Authentication Granted!");
            } else {
                System.out.println("Passwords don't match!");
                Teacher_Signup();
            }
        }while (!ConfirmTeacherPassword.equals(teacherPassword));

        File teacherFile = new File("..\\TheTestTaker\\TeacherDetails\\" + teacherName + ".txt");
        if (teacherFile.createNewFile()) {
            System.out.println("Your Account has been created!");
        }
        FileWriterTeacher();
    }

    public static boolean PasswordChecker(String password) {

        if (password.length() < PasswordSize)
            return false;

        int charCount = 0;
        int numCount = 0;

        for (int i = 0; i < password.length(); i++) {

            char Passwordch = password.charAt(i);

            if (is_Numeric(Passwordch))
                numCount++;
            else if (is_Letter(Passwordch))
                charCount++;
            else
                return false;
        }
        return (charCount >= 2 && numCount >= 2);
    }

    public static boolean is_Letter(char ch) {
        ch = Character.toUpperCase(ch);
        return (ch >= 'A' && ch <= 'Z');
    }

    public static boolean is_Numeric(char ch) {
        return (ch >= '0' && ch <= '9');
    }

    public static void FileWriterStudent() throws IOException
    {
        BufferedWriter BW_StudentDetails = new BufferedWriter(new FileWriter("..\\TheTestTaker\\StudentDetails\\" + Rollno + ".txt"));
        //change the path names!!
        BW_StudentDetails.write("###################################################################################");
        BW_StudentDetails.write("This is the master file of the individual Student-"+studentName);
        BW_StudentDetails.write("###################################################################################");
        BW_StudentDetails.write("Name- "+studentName);
        BW_StudentDetails.write("Roll no- "+Rollno);
        BW_StudentDetails.write("Gender- "+studentGender);
        BW_StudentDetails.write("Semester- "+semester);
        BW_StudentDetails.write("Subjects offered:- \n(1)OOP\n(2)DE\n(3)DSA");
        BW_StudentDetails.write("*****************************************************************************");
        BW_StudentDetails.write("Quiz Data");
        BW_StudentDetails.write("*****************************************************************************");
        BW_StudentDetails.write("Subject- OOP");
        BW_StudentDetails.write("List of Quizes taken with their marks");
        BW_StudentDetails.write("-----------------------------------------------------------------------------");
        BW_StudentDetails.write("Quiz_1- 0");
        BW_StudentDetails.write("Quiz_2- 0");
        BW_StudentDetails.write("Quiz_3- 0");
        BW_StudentDetails.write("Quiz_4- 0");
        BW_StudentDetails.write("-----------------------------------------------------------------------------");
        BW_StudentDetails.write("Subject- DE");
        BW_StudentDetails.write("List of Quizes taken with their marks");
        BW_StudentDetails.write("-----------------------------------------------------------------------------");
        BW_StudentDetails.write("Quiz_1- 0");
        BW_StudentDetails.write("Quiz_2- 0");
        BW_StudentDetails.write("Quiz_3- 0");
        BW_StudentDetails.write("-----------------------------------------------------------------------------");
        BW_StudentDetails.write("Subject- DSA");
        BW_StudentDetails.write("List of Quizes taken with their marks");
        BW_StudentDetails.write("-----------------------------------------------------------------------------");
        BW_StudentDetails.write("Quiz_1- 0");
        BW_StudentDetails.write("Quiz_2- 0");
        BW_StudentDetails.write("Quiz_3- 0");
        BW_StudentDetails.write("*****************************************************************************");
        BW_StudentDetails.write("Total marks- 0");
        BW_StudentDetails.write("Average- 0");
        BW_StudentDetails.write("*****************************************************************************");

        BW_StudentDetails.close();
    }
    public static void FileWriterTeacher() throws IOException
    {
        BufferedWriter BW_TeacherDetails = new BufferedWriter(new FileWriter("..\\TheTestTaker\\TeacherDetails\\" + teacherName + ".txt"));

        BW_TeacherDetails.write("#########################################################################");
        BW_TeacherDetails.write("This is the master file of the teacher-"+teacherName);
        BW_TeacherDetails.write("#########################################################################");
        BW_TeacherDetails.write("Name:- "+teacherName);
        BW_TeacherDetails.write("Age:- "+teacherAge);
        BW_TeacherDetails.write("Gender:-"+String_teacher_gender);
        BW_TeacherDetails.write("Designation:-"+String_teacher_designation);
        BW_TeacherDetails.write("#########################################################################");

        BW_TeacherDetails.close();
        Teacher some_teacher= new Teacher(teacherName);
        some_teacher.welcome();
    }

    public static void main(String[] args) throws IOException {

        File Parent_Directory = new File("..\\TheTestTaker");
        if (Parent_Directory.mkdir()) {
            System.out.println("A new Parent folder (TheTestTaker) has been created.");
        } else {
            System.out.println("Parent Folder exists.");
        }

        int choice;
        System.out.println("-*-*-*-*-*-*-*-*-*-MAIN MENU-*-*-*-*-*-*-*-*-*-");
        System.out.println("1. Student Sign-In");
        System.out.println("2. Student Sign-Up");
        System.out.println("3. Teacher Sign-In");
        System.out.println("4. Teacher Sign-Up");
        System.out.println("-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-");

        choice = Parent_Scanner.nextInt();

        switch (choice) {
            case 1:
                Student_Login();
                break;

            case 2:
                Student_Signup();
                break;

            case 3:
                Teacher_Login();
                break;

            case 4:
                Teacher_Signup();
                break;

            default:
                System.out.println("Invalid Choice Input!");
                break;
        }
    }
}
