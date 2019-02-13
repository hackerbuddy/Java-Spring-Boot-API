package bidding;
import org.joda.time.LocalDate;
import java.util.UUID;
import java.util.ArrayList;
import org.springframework.web.bind.annotation.CrossOrigin;

/*
Create a Project with at least the following fields: ID, Name, Owner, Description, Work Type (eg, frontend,
backend, ux, sysadmin), Deadline, Maximum Budget (starting bid), Lowest Bid (defaults to null), and Lowest
Bidder (defaults to null)
Get a Project by ID
Bid on a Project by ID
Query for Projects based on Name (fuzzy match), Work Type, and where the Deadline has not yet passed
*/

public class Project {

    private int id; //use uuid later
    private String projectName;
    private String ownerName;
    private int ownerId;  //use uuid later
    private String description;
    private String[] workType;
  
    //private final LocalDate deadline; // = new LocalDate().now();
    private String deadline;
    private double maximumBudget;
    private double lowestBid = -1;
    private String lowestBidder; //string for now..
    private double offer;

    public String getOffer(){
        return Double.toString(offer);
    }
    
    public String getId(){
        return Integer.toString(id);
    }

    public String getOwnerName(){
        return ownerName.toString();
    }
   
    public String getOwnerId(){
        return Integer.toString(ownerId);
    }

    public String getDescription(){
        return description.toString();
    }

    public String getWorktype(){
        return workType.toString();
    }

    public String getDeadline(){
        return deadline.toString();
    }
  
    public String getMaximumBudget(){
        return Double.toString(maximumBudget);
    }
    
    public String getProjectName(){
        return projectName.toString();
    }

    public String getlowestBid(){
        return Double.toString(lowestBid);
    }

    public String getLowestBidder(){
        return lowestBidder.toString();
    }

    public String getLowestBid(){
        return Double.toString(lowestBid);
    }

    public void setOffer(String offer){
        this.offer = Float.parseFloat(offer);
    }
    public void setOwnerName(String ownerName){
        this.ownerName = ownerName;
    }
    public void setDescription(String description){
        this.description = description;
    }

    public void setWorktype(String worktype){
        String[] array = worktype.split(" ", 30);
        this.workType = array.clone();
    }

    public void setDeadline(String deadline){
        this.deadline = deadline;
    }

    public void setMaximumBudget(String maximumBudget){
        this.maximumBudget = Float.parseFloat(maximumBudget);
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
        this.lowestBidder = lowestBidder.toString();
    }

}