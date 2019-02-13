package bidding;

import java.util.concurrent.atomic.AtomicLong;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.bind.annotation.ResponseBody;

import org.joda.time.LocalDate;
import java.util.UUID;
import java.util.ArrayList;

@CrossOrigin(origins = "*")
@RestController
public class ProjectController {

@RequestMapping(
  value = "/newProductOwner", 
  method = RequestMethod.POST,
  produces = "application/json"
)
@ResponseBody 
public Description getDescription(@RequestBody ProductOwner data){
    return new Description(data.getFirstName() + " " + data.getLastname() + " hates wacky wabbits"
     + " and his username is " + data.getUsername() + " and his email is " + data.getEmail() + " and his password is "
     + data.getPassword());
}

@RequestMapping(
  value = "/newProject", 
  method = RequestMethod.POST,
  produces = "application/json"
)
@ResponseBody 
public Description getDescription(@RequestBody Project data){
    return new Description(
    data.getProjectName() + System.lineSeparator() +
    data.getOwnerName() + System.lineSeparator() +
    data.getOffer() + System.lineSeparator() +
    data.getWorktype() + System.lineSeparator() +
    data.getDeadline() + System.lineSeparator() +
    data.getMaximumBudget() + System.lineSeparator() +
    data.getLowestBid() + System.lineSeparator() +
    data.getLowestBidder());
}

}


