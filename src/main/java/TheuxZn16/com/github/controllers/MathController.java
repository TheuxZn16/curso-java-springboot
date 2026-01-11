package TheuxZn16.com.github.controllers;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import TheuxZn16.com.github.exception.UnsuportedMathOpperationException;
import TheuxZn16.com.github.utils.ConvertToDouble;
import TheuxZn16.com.github.utils.isNumericChecker;

@RestController
@RequestMapping("/math")
public class MathController {

  private Boolean isNumeric(String strNumber) {
    return isNumericChecker.isNumeric(strNumber);
  }

  private Double convertToDouble(String strNumber) throws Exception {
    return ConvertToDouble.convertToDouble(strNumber);
  }

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
}
