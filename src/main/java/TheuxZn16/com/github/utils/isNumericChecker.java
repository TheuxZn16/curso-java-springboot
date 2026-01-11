package TheuxZn16.com.github.utils;

public class isNumericChecker {
  public static boolean isNumeric(String strNumber) {
    if (strNumber == null || strNumber.isEmpty())
      return false;
    String number = strNumber.replaceAll(",", ".");
    return number.matches("[-+]?[0-9]*\\.?[0-9]+");
  }
}
