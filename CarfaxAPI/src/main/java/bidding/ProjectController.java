package bidding;
import org.springframework.beans.factory.annotation.Autowired;
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
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import org.joda.time.LocalDate;
import java.util.UUID;
import java.util.ArrayList;

@RestController
public class ProjectController {

  @RequestMapping(
    value = "/newProject", 
    method = RequestMethod.POST,
    produces = "application/json"
  )
  @ResponseBody 
  public String getNewProjectResultStatus(@RequestBody Project data) throws Exception{

      ProjectDAO proj = new ProjectDAO();
      proj.insertNewProject(data);

      JSONObject jsonObj = new JSONObject();
      jsonObj.put("projectName", data.getProjectName());
      jsonObj.put("projectDescription", data.getProjectDescription());
      jsonObj.put("productOwner", data.getProductOwner());
      jsonObj.put("workType", data.getWorkType());
      jsonObj.put("deadline", data.getDeadline());
      jsonObj.put("maximumBudget", data.getMaximumBudget());

      return jsonObj.toJSONString();
  }

}




