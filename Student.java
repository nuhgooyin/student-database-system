/**
 * Student class
 **/
public class Student {
  // Attributes
  private String lastName = "";
  private String firstName = "";
  private String gender = "";
  private String studentID = "";
  private String dateOfBirth = "";
  private String courses;

  /**
   * Constructor
   * 
   * @params String lastName, String firstName, String gender, String studentID, String dateOfBirth, String courses
   */
  public Student(String lastName, String firstName, String gender, String studentID, String dateOfBirth,
      String courses) {
    // Assign attributes parameters
    this.lastName = lastName;
    this.firstName = firstName;
    this.gender = gender;
    this.studentID = studentID;
    this.dateOfBirth = dateOfBirth;
    this.courses = courses;
  }

  // Behaviours

  /**
   * Get attribute
   * 
   * @param int attributeToGet
   * @return String studentAttribute
   */
  public String getAttribute(int attributeToGet) {

    // Declaring student attribute variable
    String studentAttribute = "";

    // Determine which attribute to return
    if (attributeToGet == 0) {
      studentAttribute = this.firstName;
    } else if (attributeToGet == 1) {
      studentAttribute = this.lastName;
    } else if (attributeToGet == 2) {
      studentAttribute = this.gender;
    } else if (attributeToGet == 3) {
      studentAttribute = this.studentID;
    } else if (attributeToGet == 4) {
      studentAttribute = this.dateOfBirth;
    } else if (attributeToGet == 5) {
      studentAttribute = this.courses;
    }
    return studentAttribute;
  }

  /**
   * Compare to another student
   * 
   * @param Student other, int attributeToGet
   * @return int (this attribute compared to another students)
   */
  public int compareTo(Student other, int attributeToGet) {
    return this.getAttribute(attributeToGet).compareTo(other.getAttribute(attributeToGet));
  }

  /**
   * toString override (readable output to console)
   * 
   * @return String (student attributes)
   */
  public String toString() {
    return "First Name: " + this.firstName + "\n" + "Last Name: " + this.lastName + "\n" + "Date of Birth: "
        + this.dateOfBirth + "\n" + "Student ID: " + this.studentID + "\n" + "Gender: " + this.gender + "\n"
        + "Courses: " + this.courses;
  }

  /**
   * toDBEntry (attributes in original DB format)
   * 
   * @return String (student attributes)
   */
  public String toDBEntry() {
    return this.lastName + "," + this.firstName + "," + this.gender + "," + this.studentID + "," + this.dateOfBirth
        + "," + this.courses + "\n";
  }
}