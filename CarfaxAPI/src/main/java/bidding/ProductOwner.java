package bidding;
import java.util.ArrayList;
import java.util.UUID;
import org.springframework.web.bind.annotation.CrossOrigin;

public class ProductOwner{
    private String firstName;
    private String lastName;
    private String username;
    private String password;
    private String email;

    private final UUID id = java.util.UUID.randomUUID();
    private ArrayList<Project> projectsForSale = new ArrayList<Project>(); 
    private ArrayList<Project> projectsSold = new ArrayList<Project>(); 

    public String getFirstName(){
        return firstName;
    }
    public String getLastname(){
        return lastName;
    }
    public String getUsername(){
        return username;
    }
    public String getPassword(){
        return password;
    }
    public String getEmail(){
        return email;
    }

    public void setFirstName(String firstName){
        this.firstName = firstName;
    }
    public void setLastName(String lastName){
        this.lastName = lastName;
    }
    public void setUsername(String username){
        this.username = username;
    }
    public void setPassword(String password){
        this.password = password;
    }
    public void setEmail(String email){
        this.email = email;
    }
}