package bidding;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.CrossOrigin;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class ProjectDAO{

    private static JdbcTemplate jdbcTemplate;
    private static Logger log = LoggerFactory.getLogger(Application.class);

    public ProjectDAO(JdbcTemplate jdbcTemplate){
        this.jdbcTemplate = jdbcTemplate;
    }
    public ProjectDAO(){
    }

    public static void insertNewProject(Project proj) throws Exception {

        try{
        //Split up the array of whole names into an array of first/last names
        ArrayList<Object[]> listOfProjects = new ArrayList<Object[]>();
        String[] arrayOfStringFields = new String[10];
        arrayOfStringFields[0] = proj.getProjectName();
        arrayOfStringFields[1] = proj.getProjectDescription();
        arrayOfStringFields[2] = proj.getProductOwner();
        arrayOfStringFields[3] = proj.getDeadlineAsSqlDate();
        arrayOfStringFields[4] = proj.getMaximumBudget();
        //Worktype flags
        arrayOfStringFields[5] = proj.boolAsIntStr(proj.getFrontend());
        arrayOfStringFields[6] = proj.boolAsIntStr(proj.getBackend());
        arrayOfStringFields[7] = proj.boolAsIntStr(proj.getUx());
        arrayOfStringFields[8] = proj.boolAsIntStr(proj.getSysadmin());
        arrayOfStringFields[9] = proj.boolAsIntStr(proj.getOther());
        
        listOfProjects.add(arrayOfStringFields);

        //this jdbc template format comes from https://spring.io/guides/gs/relational-data-access/
        log.info("\n");
        log.info("New entry into db: ");
        listOfProjects.forEach(name -> Arrays.stream(arrayOfStringFields).forEach(i -> log.info(i)));
        
        // Uses JdbcTemplate's batchUpdate opesration to bulk load data
        jdbcTemplate.batchUpdate("INSERT INTO projects(project_name, project_description, product_owner, deadline, maximum_budget, frontend, backend, ux, sysadmin, other) VALUES (?,?,?,?,?,?,?,?,?,?)", listOfProjects);

        log.info("Querying for all project records:");
        jdbcTemplate.query(
                "SELECT id, project_name, project_description, product_owner, deadline, maximum_budget, frontend, backend, ux, sysadmin, other FROM projects WHERE id > ?", new Object[] { "0" },
                (rs, rowNum) -> new Project(rs.getLong("id"), rs.getString("project_name"), 
                                            rs.getString("project_description"), rs.getString("product_owner"),
                                            rs.getString("deadline"), rs.getFloat("maximum_budget"),
                                            rs.getBoolean("frontend"), rs.getBoolean("backend"), 
                                            rs.getBoolean("ux"), rs.getBoolean("sysadmin"),
                                            rs.getBoolean("other"))
        ).forEach(project -> log.info(project.toString()));

        }
        catch(Exception ex){
            log.info("Project DAO died a painful death with this Exception: " + ex);
        }
    }

    public static void initializeDBTables(){
        log.info("Creating tables");
        jdbcTemplate.execute("DROP TABLE projects IF EXISTS");
        jdbcTemplate.execute("CREATE TABLE projects(" +
                "id BIGINT NOT NULL AUTO_INCREMENT, project_name VARCHAR(255), project_description VARCHAR(255), product_owner VARCHAR(255), deadline DATE, maximum_budget FLOAT, frontend BIT, backend BIT, ux BIT, sysadmin BIT, other BIT)");
    }

}