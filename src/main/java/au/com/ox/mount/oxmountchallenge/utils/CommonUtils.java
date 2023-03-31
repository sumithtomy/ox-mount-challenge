package au.com.ox.mount.oxmountchallenge.utils;

import lombok.experimental.UtilityClass;

@UtilityClass
public class CommonUtils {

  public static double calculateMean(double[] values) {
    double sum = 0.0;
    for (double value : values) {
      sum += value;
    }
    return sum / values.length;
  }

  public static double calculateStandardDeviation(double[] values, double mean) {
    double sum = 0.0;
    for (double value : values) {
      sum += Math.pow(value - mean, 2);
    }
    return Math.sqrt(sum / values.length);
  }

  public static double parseMachineAgeValue(String machineAge) {
    String[] ageParts = machineAge.split("\\s+");
    double ageInYears = 0;
    
    if (ageParts.length != 2) {
        throw new IllegalArgumentException("Invalid age format, please provide age in years or months.");
    }
    
    int ageValue = Integer.parseInt(ageParts[0]);
    String ageUnit = ageParts[1].toLowerCase();
    
    if (ageUnit.equals("years") || ageUnit.equals("year")) {
        ageInYears = ageValue;
    } else if (ageUnit.equals("months") || ageUnit.equals("month")) {
        ageInYears = ageValue / 12.0;
    } else {
        throw new IllegalArgumentException("Invalid age unit, please provide age in years or months.");
    }
    
    return ageInYears;
    
    
    
    // extract the numeric value from the machine_age string and convert it to years
//    if (machineAge.endsWith("years") || machineAge.endsWith("year")) {
//      return Double.parseDouble(machineAge.replaceAll("year(s)?", "").trim());
//    } else if (machineAge.endsWith("months")) {
//      return Double.parseDouble(machineAge.replace("months", "").trim()) / 12.0;
//    } else if (machineAge.endsWith("month")) {
//      return 1.0 / 12.0;
//    } else {
//      throw new IllegalArgumentException("Invalid machine_age format: " + machineAge);
//    }
  }

}
