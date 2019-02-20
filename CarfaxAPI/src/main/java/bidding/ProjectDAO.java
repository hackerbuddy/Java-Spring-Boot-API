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

    public static void insertProjectNameAndDescriptionAndPO(Project proj) throws Exception {

        //Split up the array of whole names into an array of first/last names
        ArrayList<Object[]> listOfProjects = new ArrayList<Object[]>();
        String[] arrayOfStringFields = new String[3];
        arrayOfStringFields[0] = proj.getProjectName();
        arrayOfStringFields[1] = proj.getProjectDescription();
        arrayOfStringFields[2] = proj.getProductOwner();
        listOfProjects.add(arrayOfStringFields);

        //this jdbc template format comes from https://spring.io/guides/gs/relational-data-access/

        // Use a Java 8 stream to print out each tuple of the list
        listOfProjects.forEach(name -> log.info(String.format(
            "Inserting project record for %s %s %s", 
             arrayOfStringFields[0],
             arrayOfStringFields[1],
             arrayOfStringFields[2])));

        // Uses JdbcTemplate's batchUpdate operation to bulk load data
        jdbcTemplate.batchUpdate("INSERT INTO projects(project_name, project_description, product_owner) VALUES (?,?,?)", listOfProjects);

        log.info("Querying for all project records:");
        jdbcTemplate.query(
                "SELECT id, project_name, project_description, product_owner FROM projects WHERE id > ?", new Object[] { "0" },
                (rs, rowNum) -> new Project(rs.getLong("id"), rs.getString("project_name"), 
                                            rs.getString("project_description"), rs.getString("product_owner"))
        ).forEach(project -> log.info(project.toString()));
    }

    public static void initializeDBTables(){
        log.info("Creating tables");
        jdbcTemplate.execute("DROP TABLE projects IF EXISTS");
        jdbcTemplate.execute("CREATE TABLE projects(" +
                "id BIGINT NOT NULL AUTO_INCREMENT, project_name VARCHAR(255), project_description VARCHAR(255), product_owner VARCHAR(255))");
    }

}