package bidding;
import java.util.ArrayList;
import java.util.UUID;

public class ProductOwner{

    private String firstName;
    private String lastName;
    private String username;
    private String password;
    private String email;

    private long id;
    private ArrayList<Project> projectsForSale = new ArrayList<Project>(); 
    private ArrayList<Project> projectsSold = new ArrayList<Project>(); 

    public ProductOwner(long id, String firstName, String lastName, String username, String password, String email){
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.username = username;
        this.password = password;
        this.email = email;
    }

    public ProductOwner(String firstName, String lastName){
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public ProductOwner(){
        
    }

    public String getFirstName(){
        return firstName;
    }
    public String getLastName(){
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
    public long getId(){
        return id; 
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

    @Override
    public String toString() {
        return String.format(
                "ProductOwner[id=%d, firstName='%s', lastName='%s', username=%s, password='%s', email='%s']",
                id, firstName, lastName, username, password, email);
    }
}