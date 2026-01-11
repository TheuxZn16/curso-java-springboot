package TheuxZn16.com.github.utils;

import TheuxZn16.com.github.exception.UnsuportedMathOpperationException;

public class ConvertToDouble {
  public static Double convertToDouble(String strNumber) throws Exception {
    if (strNumber == null || strNumber.isEmpty())
      throw new UnsuportedMathOpperationException("Please set a numeric value");
    String number = strNumber.replaceAll(",", ".");
    return Double.parseDouble(number);
  }
}
