package au.com.ox.mount.oxmountchallenge.controller;

import java.util.List;
import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import au.com.ox.mount.oxmountchallenge.model.MachineModel;
import au.com.ox.mount.oxmountchallenge.service.MachineAgeCheckerService;

@RestController
@RequestMapping("/api/machine")
public class MachineAgeCheckController {
  
  @Autowired
  MachineAgeCheckerService machineAgeCheckerService;
  private static final Logger logger = Logger.getLogger(MachineAgeCheckController.class.getName());

  @PostMapping("/checkage")
  public List<String> processModels(@Validated @RequestBody List<MachineModel> machines) {
    for (MachineModel model : machines) {
      logger.info(model.toString());  
    }
    
    List<String> outOfRangeValues = machineAgeCheckerService.getOutOfRangeValues(machines);
    
    return outOfRangeValues;
  }

}
