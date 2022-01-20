import java.util.*;
import java.io.*;

/**
 * Student Database class
 **/
public class StudentDatabase {
  // Attributes
  private String name = "";
  private ArrayList<Student> students = new ArrayList<Student>();
  private int sorted = -1;
  private int criteriaType = 0;

  /**
   * Constructor
   * 
   * @param nameOfDatabase
   */
  public StudentDatabase(String nameOfDatabase) {
    // Assign attribute parameter
    this.name = nameOfDatabase;
  }

  // Behaviours

  /**
   * Bubble sort
   */
  public void bubbleSort() {

    // Declare swap counter
    int fullPassCounter = 0;

    // Keep sorting arrayList until it is sorted
    while (this.sorted == -1) {

      // Loop through students arrayList
      for (int i = 0; i < students.size() - 1 && sorted == -1; i++) {

        // Store the first and second student objects
        Student firstStudent = students.get(i);
        Student secondStudent = students.get(i + 1);

        // If first is greater than second
        if (firstStudent.compareTo(secondStudent, criteriaType) > 0) {

          // Swap the two students
          students.set(i, secondStudent);
          students.set(i + 1, firstStudent);
        } else {

          // Swap is not needed
          fullPassCounter++;

          // If full pass has been done then array is sorted
          if (fullPassCounter >= students.size()) {
            this.sorted = criteriaType;
          }
        }
      }
    }
  }

  /**
   * Insert sort
   */
  public void insertSort() {

    // Loop through each student
    for (int i = 1; i < students.size(); i++) {

      // Assign key
      Student keyStudent = students.get(i);

      // Declare counter and assign value
      int counter = i - 1;

      // While counter is greater than or equal to 0 and key student is less than given student
      while (counter >= 0 && keyStudent.compareTo(students.get(counter), criteriaType) < 0) {

        // Set next student as given student
        students.set(counter + 1, students.get(counter));

        // Take away 1 from counter
        counter--;
      }
      // Insert keyStudent after the element that is smaller than it or at the beginning
      students.set(counter + 1, keyStudent);
    }
  }

  /**
   * Read database (read, parse and create students)
   */
  public void readDatabase() throws Exception {
    Scanner sc = new Scanner(new FileReader(this.name));

    // Keep splitting if there are more student entries
    while (sc.hasNext() == true) {

      // Read line and store in variable
      String studentFullEntry = sc.nextLine();

      // Parsing the line of data
      String[] studentSplitEntry = studentFullEntry.split(",");

      // Creating the student object with the parsed data
      Student studentInstance = new Student(studentSplitEntry[0], studentSplitEntry[1], studentSplitEntry[2], studentSplitEntry[3], studentSplitEntry[4], studentSplitEntry[5]);

      // Adding it to the arrayList attribute of students
      students.add(studentInstance);
    }
  }

  /**
   * Save database (overwrite updated database to text file)
   */
  public void saveDatabase() throws Exception {
    PrintWriter pw = new PrintWriter(new FileWriter(this.name));

    // Loop through each student in updated database
    for (int i = 0; i < students.size(); i++) {

      // Write student to file in database format
      pw.print(students.get(i).toDBEntry());

    }

    // Make sure all data has been written and close
    pw.flush();
    pw.close();
  }

  /**
   * Get number of students with specified criteria
   * 
   * @param String criteriaToCount, int criteriaType
   * @return String numOfStudents
   */
  public String getNumStudents(String criteriaToCount, int criteriaType) {

    // Declare and assign variables
    String foundEntries = "";
    this.criteriaType = criteriaType;

    // If sorted and criteriaType is not courses or ALL search then use binary search, everything else use linear
    if (sorted == criteriaType && criteriaType != 5 && criteriaType != 6) {
      foundEntries = this.binarySearch(criteriaToCount);
    } else {
      foundEntries = this.linearSearch(criteriaToCount, criteriaType);

      // If criteriaType is not courses or ALL then insertSort by criteriaType
      if (criteriaType != 5 && criteriaType != 6) {
        this.insertSort();
        sorted = criteriaType;
      }
    }

    // Parsing the entries found
    String[] entriesToCount = foundEntries.split("\n\n");

    // Declare counter for number of students
    String numOfStudents = "" + (entriesToCount.length - 1);

    return "\nNumber of " + criteriaToCount + " students " + numOfStudents;
  }

  /**
   * Binary search function
   * 
   * @param String criteriaToSearch
   * @return String studentEntries
   */
  public String binarySearch(String criteriaToSearch) {
    
    // Declare studentEntries and arrayList
    String studentEntries = "";
    ArrayList<Student> studentsFound = new ArrayList<Student>();

    // Declare variables
    int studFoundCounter = 0;
    int first = 0;
    int last = students.size() - 1;
    int mid;
    boolean done = false;

    // Keep dividing portion of list while first is less than or equal to last and done is not true
    while (first <= last && done != true) {

      // Assign position of mid
      mid = first + (last - first) / 2;

      // Determine if student is less than or more than given
      if (students.get(mid).getAttribute(criteriaType).compareTo(criteriaToSearch) < 0) {
        first = mid + 1;
      } else if (students.get(mid).getAttribute(criteriaType).compareTo(criteriaToSearch) > 0) {
        last = mid - 1;
      }

      // If mid student is equal to criteria being searched
      else if (students.get(mid).getAttribute(criteriaType).equals(criteriaToSearch)) {

        // Declare variables
        int midHigher = mid + 1;
        int midLower = mid - 1;
        boolean finished = false;

        // Add student to arrayList and increment
        studentsFound.add(studFoundCounter, students.get(mid));
        studFoundCounter++;

        // While is not finished and midHigher and midLower are not out of bounds
        while (finished != true && midHigher <= students.size() && midLower >= 0) {

          // Check higher mid sorted students
          if (students.get(midHigher).getAttribute(criteriaType).equals(criteriaToSearch)) {

            // Add student to arrayList and increments
            studentsFound.add(studFoundCounter, students.get(midHigher));
            studFoundCounter++;
            midHigher++;
          }

          // Check lower mid sorted students
          else if (students.get(midLower).getAttribute(criteriaType).equals(criteriaToSearch)) {

            // Add student to arrayList and increments
            studentsFound.add(studFoundCounter, students.get(midLower));
            studFoundCounter++;
            midLower--;
          }

          // Else break loop
          else {
            finished = true;
          }
        }
        done = true;
      }
    }

    // Students found list is empty
    if (studentsFound.size() == 0) {
      studentEntries = "Student entry cannot be found.";
    }

    // Loop through arrayList elements and add to studentEntries
    for (int h = 0; h < studentsFound.size(); h++) {
      studentEntries += "\n\n" + studentsFound.get(h);
    }

    return studentEntries;
  }

  /**
   * Search for specified criteria
   * 
   * @param String criteriaToSearch, int criteriaType (6 ALL)
   * @return String studentEntries
   */
  public String search(String criteriaToSearch, int criteriaType) {

    // Declare and assign variables
    String studentEntries = "";
    this.criteriaType = criteriaType;

    // If it is sorted and criteriaType is not all or courses do binary search,
    // everything else do linear
    if (sorted == criteriaType && criteriaType != 6 && criteriaType != 5) {
      studentEntries = this.binarySearch(criteriaToSearch);
    } else {
      studentEntries = this.linearSearch(criteriaToSearch, criteriaType);

      // If criteriaType is not courses or ALL then insertSort by criteriaType
      if (criteriaType != 5 && criteriaType != 6) {
        this.insertSort();
        sorted = criteriaType;
      }
    }
    return studentEntries;
  }

  /**
   * Linear search function
   * 
   * @param String criteriaToSearch, int criteriaType (6 ALL)
   * @return String studentEntries
   */
  public String linearSearch(String criteriaToSearch, int criteriaType) {

    // Declare variables and arrayList
    String studentEntries = "";
    ArrayList<Student> studentsFound = new ArrayList<Student>();
    int studFoundCounter = 0;

    // Loop through every student
    for (int i = 0; i < students.size(); i++) {

      // Assign variable to student
      Student currentStudent = students.get(i);

      // Criteria type is not ALL
      if (criteriaType != 6) {

        // Searching for courses
        if (criteriaType == 5) {

          // Parse student courses into single array
          String[] studentCourses = currentStudent.getAttribute(5).split(" ");

          // Loop through courses
          for (int k = 0; k < studentCourses.length; k++) {

            // If it matches the criteria
            if (studentCourses[k].equals(criteriaToSearch)) {

              // Add student to arrayList and increment
              studentsFound.add(studFoundCounter, currentStudent);
              studFoundCounter++;
            }
          }
        }

        // If attribute is equal to criteria being searched
        else if (currentStudent.getAttribute(criteriaType).equals(criteriaToSearch)) {

          // Add student to arrayList and increment
          studentsFound.add(studFoundCounter, currentStudent);
          studFoundCounter++;
        }
      }

      // Else search ALL criteria
      else {

        // Loop through each criteria for student
        for (int j = 0; j <= 5; j++) {

          // If checking student courses
          if (j == 5) {

            // Parse student courses into single array
            String[] studentCourses = currentStudent.getAttribute(j).split(" ");

            // Loop through each course
            for (int k = 0; k < studentCourses.length; k++) {

              // Check student courses with criteria given
              if (studentCourses[k].equals(criteriaToSearch)) {

                // Add student to arrayList and increment
                studentsFound.add(studFoundCounter, currentStudent);
                studFoundCounter++;
              }
            }
          }

          // Check every criteria
          else if (currentStudent.getAttribute(j).equals(criteriaToSearch)) {

            // Add student to arrayList and increment
            studentsFound.add(studFoundCounter, currentStudent);
            studFoundCounter++;
          }
        }
      }
    }
    // Students found list is empty
    if (studentsFound.size() == 0) {
      studentEntries = "Student entry cannot be found.";
    }

    // Loop through arrayList elements and add to studentEntries
    for (int h = 0; h < studentsFound.size(); h++) {
      studentEntries += "\n\n" + studentsFound.get(h);
    }
    return studentEntries;
  }

  /**
   * To string function override
   * 
   * @return String name, arrayList
   */
  public String toString() {
    return this.name + " students:" + students;
  }
}