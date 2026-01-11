package TheuxZn16.com.github.controllers;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import TheuxZn16.com.github.exception.UnsuportedMathOpperationException;

@RestController
@RequestMapping("/math")
public class MathController {

  @RequestMapping("/sum/{numberOne}/{numberTwo}")
  public Double sum(
      @PathVariable("numberOne") String numberOne,
      @PathVariable("numberTwo") String numberTwo) throws Exception {

    if (!isNumeric(numberOne) || !isNumeric(numberTwo)) {
      throw new UnsuportedMathOpperationException("Please set a numeric value");
    }
    return convertToDouble(numberOne) + convertToDouble(numberTwo);
  }

  @RequestMapping("/sub/{numberOne}/{numberTwo}")
  public Double sub(
      @PathVariable("numberOne") String numberOne,
      @PathVariable("numberTwo") String numberTwo) throws Exception {

    if (!isNumeric(numberOne) || !isNumeric(numberTwo)) {
      throw new UnsuportedMathOpperationException("Please set a numeric value");
    }
    return convertToDouble(numberOne) - convertToDouble(numberTwo);
  }

  @RequestMapping("/mult/{numberOne}/{numberTwo}")
  public Double mult(
      @PathVariable("numberOne") String numberOne,
      @PathVariable("numberTwo") String numberTwo) throws Exception {

    if (!isNumeric(numberOne) || !isNumeric(numberTwo)) {
      throw new UnsuportedMathOpperationException("Please set a numeric value");
    }
    return convertToDouble(numberOne) * convertToDouble(numberTwo);
  }

  @RequestMapping("/div/{numberOne}/{numberTwo}")
  public Double div(
      @PathVariable("numberOne") String numberOne,
      @PathVariable("numberTwo") String numberTwo) throws Exception {

    if (!isNumeric(numberOne) || !isNumeric(numberTwo)) {
      throw new UnsuportedMathOpperationException("Please set a numeric value");
    }
    return convertToDouble(numberOne) / convertToDouble(numberTwo);
  }

  @RequestMapping("/mean/{numberOne}/{numberTwo}")
  public Double mean(
      @PathVariable("numberOne") String numberOne,
      @PathVariable("numberTwo") String numberTwo) throws Exception {

    if (!isNumeric(numberOne) || !isNumeric(numberTwo)) {
      throw new UnsuportedMathOpperationException("Please set a numeric value");
    }
    return (convertToDouble(numberOne) + convertToDouble(numberTwo)) / 2;
  }

  @RequestMapping("/squareRoot/{numberOne}")
  public Double squareRoot(
      @PathVariable("numberOne") String numberOne) throws Exception {

    if (!isNumeric(numberOne)) {
      throw new UnsuportedMathOpperationException("Please set a numeric value");
    }
    return Math.sqrt(convertToDouble(numberOne));
  }

  private boolean isNumeric(String strNumber) {
    if (strNumber == null || strNumber.isEmpty())
      return false;
    String number = strNumber.replaceAll(",", ".");
    return number.matches("[-+]?[0-9]*\\.?[0-9]+");
  }

  private Double convertToDouble(String strNumber) throws Exception {
    if (strNumber == null || strNumber.isEmpty())
      throw new UnsuportedMathOpperationException("Please set a numeric value");
    String number = strNumber.replaceAll(",", ".");
    return Double.parseDouble(number);
  }
}
