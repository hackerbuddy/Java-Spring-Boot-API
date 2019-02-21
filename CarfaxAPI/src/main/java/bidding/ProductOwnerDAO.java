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

public class ProductOwnerDAO{

    private static JdbcTemplate jdbcTemplate;
    private static Logger log = LoggerFactory.getLogger(Application.class);

    public ProductOwnerDAO(JdbcTemplate jdbcTemplate){
        this.jdbcTemplate = jdbcTemplate;
    }
    public ProductOwnerDAO(){
    }

    public static void insertNewPO(ProductOwner po) throws Exception {

        //Split up the array of whole names into an array of first/last names
        ArrayList<Object[]> listOfProductOwners = new ArrayList<Object[]>();
        String[] arrayOfStringFields = new String[5];
        arrayOfStringFields[0] = po.getFirstName();
        arrayOfStringFields[1] = po.getLastName();
        arrayOfStringFields[2] = po.getUsername();
        arrayOfStringFields[3] = po.getPassword();
        arrayOfStringFields[4] = po.getEmail();

        listOfProductOwners.add(arrayOfStringFields);

        //this jdbc template format comes from https://spring.io/guides/gs/relational-data-access/

        // Use a Java 8 stream to print out each tuple of the list
        log.info("\n");
        log.info("New entry into db: ");
        listOfProductOwners.forEach(name -> Arrays.stream(arrayOfStringFields).forEach(i -> log.info(i)));
        
        // Uses JdbcTemplate's batchUpdate operation to bulk load data
        jdbcTemplate.batchUpdate("INSERT INTO productOwners(first_name, last_name, username, password, email) VALUES (?,?,?,?,?)", listOfProductOwners);

        log.info("\nQuerying for all product owner records:");
        jdbcTemplate.query(
                "SELECT id, first_name, last_name, username, password, email FROM productOwners WHERE id > ?", new Object[] { "0" },
                (rs, rowNum) -> new ProductOwner(rs.getLong("id"), rs.getString("first_name"), 
                                                 rs.getString("last_name"), rs.getString("username"), 
                                                 rs.getString("password"), rs.getString("email"))
        ).forEach(productOwner -> log.info(productOwner.toString()));
    }

    public static void initializeDBTables(){
        log.info("Creating tables");
        jdbcTemplate.execute("DROP TABLE productOwners IF EXISTS");
        jdbcTemplate.execute("CREATE TABLE productOwners(" +
                "id BIGINT NOT NULL AUTO_INCREMENT, first_name VARCHAR(255), last_name VARCHAR(255), username VARCHAR(255), password VARCHAR(255), email VARCHAR(255))");
        log.info("");
    }

}