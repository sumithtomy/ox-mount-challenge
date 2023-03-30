package au.com.ox.mount.oxmountchallenge;

import java.util.List;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import au.com.ox.mount.oxmountchallenge.service.MachineAgeCheckerService;

@SpringBootApplication
public class OxMountChallengeApplication {
  private static final List<String> SAMPLE_VALUES = List.of("1 month", "6 months", "1 year", "2 years", "5 years", "10 years","90 years");
	public static void main(String[] args) {

		SpringApplication.run(OxMountChallengeApplication.class, args);
		
	   
    List<String> outOfRangeValues = MachineAgeCheckerService.getOutOfRangeValues(SAMPLE_VALUES);
    System.out.println(outOfRangeValues);
	}

}
