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

@SpringBootApplication
public class Application implements CommandLineRunner{
    public static void main(String args[]) {
        SpringApplication.run(Application.class, args);
    }
    @Autowired
    JdbcTemplate jdbcTemplate;
    
    @Override
    public void run(String... strings) throws Exception {
        ProductOwnerDAO poDAO = new ProductOwnerDAO(jdbcTemplate);
        poDAO.initializeDBTables();
        ProjectDAO projDAO = new ProjectDAO(jdbcTemplate);
        projDAO.initializeDBTables();
    }

}