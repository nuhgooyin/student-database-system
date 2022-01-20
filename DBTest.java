/**
 * Test program for specific features
 **/
class DBTest {
  public static void main(String[] args) throws Exception {
    // Create a student database for stfx
    StudentDatabase stfx = new StudentDatabase("STFXTest.txt");

    // Read and take in sets of student data for stfx
    stfx.readDatabase();

    // Sort it
    // stfx.insertSort();
    // stfx.bubbleSort();
    // System.out.print(stfx);

    // Print arrayList to check sorting
    // System.out.print(stfx);

    // Searching with specific criteria and type
    // System.out.print(stfx.search("19900906", 4));

    // Binary search with specific criteria and type
    // System.out.print(stfx.binarySearch("19900906"));

    // Number of students with specific criteria
    // System.out.print(stfx.getNumStudents("F", 6));

    // Update database
    // stfx.saveDatabase();
  }
}