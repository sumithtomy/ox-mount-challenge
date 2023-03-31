package au.com.ox.mount.oxmountchallenge.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * The Class MachineModel used to hold the machine details {id} and {age}
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class MachineModel {
  
  private String machineId;
  private String machineAge;

}
