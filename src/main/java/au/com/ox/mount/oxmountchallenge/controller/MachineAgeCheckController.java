package au.com.ox.mount.oxmountchallenge.controller;
import java.util.logging.Logger;

import org.springframework.web.bind.annotation.GetMapping;
public class MachineAgeCheckController {


      
      private static final Logger logger = Logger.getLogger(MachineAgeCheckController.class.getName());

      @GetMapping("/hello")
      public String sayHello() {
          logger.info("Hello endpoint called");
          return "Hello!";
      }


}
