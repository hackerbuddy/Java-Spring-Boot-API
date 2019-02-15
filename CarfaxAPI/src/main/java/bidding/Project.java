package bidding;
import org.joda.time.LocalDate;
import java.util.UUID;
import java.util.ArrayList;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
/*
Create a Project with at least the following fields: ID, Name, Owner, Description, Work Type (eg, frontend,
backend, ux, sysadmin), Deadline, Maximum Budget (starting bid), Lowest Bid (defaults to null), and Lowest
Bidder (defaults to null)
Get a Project by ID
Bid on a Project by ID
Query for Projects based on Name (fuzzy match), Work Type, and where the Deadline has not yet passed
*/

public class Project {

    private int id=-1; //use uuid later
    private String projectName="none";
    private String productOwnerName="none";
    private int ownerId=-1; //use uuid later
    private String projectDescription= "none";
    private String workType = "empty"; //json string
  
    //private final LocalDate deadline; // = new LocalDate().now();
    private String deadline="none";
    private double maximumBudget=-1.0;
    private double lowestBid = -1.0;
    private String lowestBidder="none"; //string for now..
    private double offer=-1.0;

    private boolean frontend = false;
    private boolean backend = false;
    private boolean ux = false;
    private boolean sysadmin = false;
    private boolean other = false;

    public String getOffer(){
        return Double.toString(offer);
    }
    
    public String getId(){
        return Integer.toString(id);
    }

    public String getProductOwnerName(){
        return productOwnerName;
    }
   
    public String getOwnerId(){
        return Integer.toString(ownerId);
    }

    public String getProjectDescription(){
        return projectDescription;
    }

    public String getWorkType(){
        return workType;
    }

    public String getDeadline(){
        return deadline.toString();
    }
  
    public String getMaximumBudget(){
        return Double.toString(maximumBudget);
    }
    
    public String getProjectName(){
        return projectName;
    }

    public String getLowestBid(){
        return Double.toString(lowestBid);
    }

    public String getLowestBidder(){
        return lowestBidder;
    }

    public void setOffer(String offer){
        this.offer = Float.parseFloat(offer);
    }
    public void setProductOwnerName(String productOwnerName){
        this.productOwnerName = productOwnerName;
    }
    public void setProjectDescription(String projectDescription){
        this.projectDescription = projectDescription;
    }

    public void setWorkType(String workType){
        JSONParser parser = new JSONParser();

        JSONObject json;
        try{
        json = (JSONObject) parser.parse(workType);
        this.frontend = (boolean) json.get("frontend");
        this.backend = (boolean) json.get("backend");
        this.ux = (boolean) json.get("ux");
        this.sysadmin = (boolean) json.get("sysadmin");
        this.other = (boolean) json.get("other");
        this.workType = json.toJSONString();
        }
        catch(Exception ex){
            System.out.println("Setting worktype failed");
        }

    }

    public void setDeadline(String deadline){
        this.deadline = deadline;
    }

    public void setMaximumBudget(String maximumBudget){
        if (maximumBudget.equals("")){
            this.maximumBudget = 0;
        }
        else{
        this.maximumBudget = Float.parseFloat(maximumBudget);
        }
    }

    public void setProjectName(String projectName){
        this.projectName = projectName;
    }

    public void setlowestBid(String lowestBid){
        if(Double.parseDouble(lowestBid) > 0 && Double.parseDouble(lowestBid) < maximumBudget){
            if(this.lowestBid == -1 || offer < this.lowestBid ){
                this.lowestBid = Double.parseDouble(lowestBid);
                //this.lowestBidder = ownerId;
            }
        }
    }

    public void setLowestBidder(String lowestBidder){
        this.lowestBidder = lowestBidder;
    }


}