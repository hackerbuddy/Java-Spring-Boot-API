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

    public static void insertFirstAndLastName(ProductOwner po) throws Exception {

        //Split up the array of whole names into an array of first/last names
        ArrayList<Object[]> listOfProductOwners = new ArrayList<Object[]>();
        String[] arrayOfStringFields = new String[2];
        arrayOfStringFields[0] = po.getFirstName();
        arrayOfStringFields[1] = po.getLastName();
        listOfProductOwners.add(arrayOfStringFields);

        //this jdbc template format comes from https://spring.io/guides/gs/relational-data-access/

        // Use a Java 8 stream to print out each tuple of the list
        listOfProductOwners.forEach(name -> log.info(String.format("Inserting productOwner record for %s %s", arrayOfStringFields[0], arrayOfStringFields[1])));

        // Uses JdbcTemplate's batchUpdate operation to bulk load data
        jdbcTemplate.batchUpdate("INSERT INTO productOwners(first_name, last_name) VALUES (?,?)", listOfProductOwners);

        log.info("Querying for productOwner records where first_name = 'Braden':");
        jdbcTemplate.query(
                "SELECT id, first_name, last_name FROM productOwners WHERE first_name = ?", new Object[] { "Braden" },
                (rs, rowNum) -> new ProductOwner(rs.getLong("id"), rs.getString("first_name"), rs.getString("last_name"))
        ).forEach(productOwner -> log.info(productOwner.toString()));
    }

    public static void initializeDBTables(){
        log.info("Creating tables");
        jdbcTemplate.execute("DROP TABLE productOwners IF EXISTS");
        jdbcTemplate.execute("CREATE TABLE productOwners(" +
                "id BIGINT NOT NULL AUTO_INCREMENT, first_name VARCHAR(255), last_name VARCHAR(255))");
    }

}