package au.com.ox.mount.oxmountchallenge.service;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class MachineAgeCheckerService {
  
  private static final Logger LOGGER = LoggerFactory.getILoggerFactory().getLogger(MachineAgeCheckerService.class.getName());
  private static final double OUTLIER_THRESHOLD = 2.0; // number of standard deviations from the mean to consider an outlier

  public static List<String> getOutOfRangeValues(List<String> machineAges) {
    double[] sampleValues = machineAges.stream().mapToDouble(MachineAgeCheckerService::parseMachineAgeValue).toArray();
    double mean = calculateMean(sampleValues);
    LOGGER.info(String.format("Mean Value for Sample %s" , mean));
    double stdDev = calculateStandardDeviation(sampleValues, mean);
    LOGGER.info(String.format("stdDev Value for Sample %s" , stdDev));
    List<String> outOfRangeValues = new ArrayList<>();
    for (String machineAge : machineAges) {
      double value = parseMachineAgeValue(machineAge);
      if (Math.abs(value - mean) > OUTLIER_THRESHOLD * stdDev) {
        outOfRangeValues.add(machineAge);
      }
    }
    return outOfRangeValues;
  }

  private static double parseMachineAgeValue(String machineAge) {
    // extract the numeric value from the machine_age string and convert it to years
    if (machineAge.endsWith("years")||machineAge.endsWith("year")) {
      return Double.parseDouble(machineAge.replaceAll("year(s)?", "").trim());
    } else if (machineAge.endsWith("months")) {
      return Double.parseDouble(machineAge.replace("months", "").trim()) / 12.0;
    } else if (machineAge.endsWith("month")) {
      return 1.0 / 12.0;
    } else {
      throw new IllegalArgumentException("Invalid machine_age format: " + machineAge);
    }
  }

  private static double calculateMean(double[] values) {
    double sum = 0.0;
    for (double value : values) {
      sum += value;
    }
    return sum / values.length;
  }

  private static double calculateStandardDeviation(double[] values, double mean) {
    double sum = 0.0;
    for (double value : values) {
      sum += Math.pow(value - mean, 2);
    }
    return Math.sqrt(sum / values.length);
  }
}
