import java.util.*;

/**
 * Test program for user input
 **/
class DBUserInputTest {
  public static void main(String[] args) throws Exception {
    Scanner sc = new Scanner(System.in);

    // Create a student database for stfx
    StudentDatabase stfx = new StudentDatabase("STFX.txt");
    stfx.readDatabase();

    // Declare variables
    boolean done = false;

    while (done != true) {

      // Takes user inputted information
      System.out.println("\nSelect an action... \n 1. Search \n 2. Get Number of Students With Certain Criteria \n 3. Quit");
      int selectedAction = sc.nextInt();

      // Selected search option
      if (selectedAction == 1) {

        // Collect user input
        System.out.println("Search For:");
        String criteriaToSearch = sc.next();

        // Declare variables
        boolean status = false;
        int criteriaTypeInt = 6;

        // While criteriaType is not valid
        while (status == false) {

          // Collect needed input variables
          System.out.println("Type of Criteria: ");
          String criteriaType = sc.next();

          // Determine and set integer value for specific criteria type
          if (criteriaType.equals("FirstName")) {
            criteriaTypeInt = 0;
            status = true;
          } else if (criteriaType.equals("LastName")) {
            criteriaTypeInt = 1;
            status = true;
          } else if (criteriaType.equals("DOB")) {
            criteriaTypeInt = 2;
            status = true;
          } else if (criteriaType.equals("StudentID")) {
            criteriaTypeInt = 3;
            status = true;
          } else if (criteriaType.equals("Gender")) {
            criteriaTypeInt = 4;
            status = true;
          } else if (criteriaType.equals("Courses")) {
            criteriaTypeInt = 5;
            status = true;
          } else if (criteriaType.equals("ALL")) {
            criteriaTypeInt = 6;
            status = true;
          } else {
            System.out.println(
                "Please enter one of the following valid criteria types: FirstName, LastName, DOB, StudentID, Gender, Courses, ALL.");
          }
        }
        // Start of timing functions
        //long time1 = System.currentTimeMillis();

        // Search with given information
        System.out.println(stfx.search(criteriaToSearch, criteriaTypeInt));

        // End of timing functions
        //long time2 = System.currentTimeMillis(); 
        //System.out.println("That took: "+(time2 - time1)+"ms");
      }
      // Selected counter option
      else if (selectedAction == 2) {

        // Collect user input
        System.out.println("Criteria To Count:");
        String criteriaToCount = sc.next();

        // Declare variables
        boolean status = false;
        int criteriaTypeInt = 6;

        // While criteriaType is not valid
        while (status == false) {

          // Collect needed input variables
          System.out.println("Type of Criteria: ");
          String criteriaType = sc.next();

          // Determine and set integer value for specific criteria type
          if (criteriaType.equals("FirstName")) {
            criteriaTypeInt = 0;
            status = true;
          } else if (criteriaType.equals("LastName")) {
            criteriaTypeInt = 1;
            status = true;
          } else if (criteriaType.equals("DOB")) {
            criteriaTypeInt = 2;
            status = true;
          } else if (criteriaType.equals("StudentID")) {
            criteriaTypeInt = 3;
            status = true;
          } else if (criteriaType.equals("Gender")) {
            criteriaTypeInt = 4;
            status = true;
          } else if (criteriaType.equals("Courses")) {
            criteriaTypeInt = 5;
            status = true;
          } else if (criteriaType.equals("ALL")) {
            criteriaTypeInt = 6;
            status = true;
          } else {
            System.out.println(
                "Please enter one of the following valid criteria types: FirstName, LastName, DOB, StudentID, Gender, Courses, ALL.");
          }
          // Count with given information
          System.out.println(stfx.getNumStudents(criteriaToCount, criteriaTypeInt));
        }
      }

      // Quit
      else if (selectedAction == 3) {
        done = true;
      }
    }
    // Update database
    stfx.saveDatabase();
    System.out.println("Database Updated.");
    sc.close();
  }
}