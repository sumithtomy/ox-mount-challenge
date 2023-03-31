package au.com.ox.mount.oxmountchallenge;

import java.util.List;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import au.com.ox.mount.oxmountchallenge.model.MachineModel;
import au.com.ox.mount.oxmountchallenge.service.MachineAgeCheckerService;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

// @EnableWebMvc
// @EnableSwagger2
@SpringBootApplication
public class OxMountChallengeApplication {
  // // private static final List<String> SAMPLE_VALUES = List.of(new MachineModel("M-1122","1 month"), "6 months", "1 year", "2 years", "5
  // // years", "10 years","90 years");
  // private static final List<MachineModel> SAMPLE_VALUES = List.of(new MachineModel("M-1122","1 month"),
  // new MachineModel("M-1122","1 month"),
  // new MachineModel("M-2233","6 month"),
  // new MachineModel("M-2222","10 years"),
  // new MachineModel("M-1144","90 years"),
  // new MachineModel("M-1155","15 Years"));

  public static void main(String[] args) {

    SpringApplication.run(OxMountChallengeApplication.class, args);

    // List<String> outOfRangeValues = new MachineAgeCheckerService().getOutOfRangeValues(SAMPLE_VALUES);
    // System.out.println(outOfRangeValues);
  }

}
