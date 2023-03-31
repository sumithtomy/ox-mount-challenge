package au.com.ox.mount.oxmountchallenge.service;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import au.com.ox.mount.oxmountchallenge.constants.Constants;
import au.com.ox.mount.oxmountchallenge.model.MachineModel;
import au.com.ox.mount.oxmountchallenge.utils.CommonUtils;
import lombok.NoArgsConstructor;
import lombok.NonNull;

@Service
@NoArgsConstructor
public class MachineAgeCheckerService {

  private static final Logger LOGGER = LoggerFactory.getILoggerFactory().getLogger(MachineAgeCheckerService.class.getName());

  /**
   * Gets the out of range values from the input given. This method make use of the Media and Std-Deviation to find the odd/out of range
   * values from the given input.
   *
   * @param machines the machines
   * @return the out of range values
   */
  // number of standard deviations from the mean to consider an outlier
  public  List<String> getOutOfRangeValues(@NonNull List<MachineModel> machines) {
    
    double[] machineAges = machines.stream().map(MachineModel::getMachineAge).mapToDouble(CommonUtils::parseMachineAgeValue).toArray();
    double mean = CommonUtils.calculateMean(machineAges);
    LOGGER.info("Mean Value for Input: {}", mean);
    double stdDev = CommonUtils.calculateStandardDeviation(machineAges, mean);
    LOGGER.info("StdDev Value for Input: {}", stdDev);
    List<String> outOfRangeValues = new ArrayList<>();
    machines.stream().forEach(machine -> {
      double value = CommonUtils.parseMachineAgeValue(machine.getMachineAge());
      if (Math.abs(value - mean) > Constants.OUTLIER_THRESHOLD * stdDev) {
        outOfRangeValues.add(machine.getMachineId());
      }
    });

    return outOfRangeValues;
  }

}
