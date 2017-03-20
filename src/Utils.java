
public class Utils {

  public static void exceptionMessage(Exception e) {

    System.out.println("Caught an exception, it said:");
    System.out.println(e.getMessage());
    System.out.println("Stack at the time when it was thrown:");

  }

}
