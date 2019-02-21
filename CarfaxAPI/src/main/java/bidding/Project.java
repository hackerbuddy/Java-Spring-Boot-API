package bidding;
import org.joda.time.LocalDate;
import java.util.UUID;
import java.util.ArrayList;
import java.util.Date;
import java.text.SimpleDateFormat;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/*
Create a Project with at least the following fields: ID, Name, Owner, Description, Work Type (eg, frontend,
backend, ux, sysadmin), Deadline, Maximum Budget (starting bid), Lowest Bid (defaults to null), and Lowest
Bidder (defaults to null)
Get a Project by ID
Bid on a Project by ID
Query for Projects based on Name (fuzzy match), Work Type, and where the Deadline has not yet passed
*/

public class Project {

    private static Logger log = LoggerFactory.getLogger(Application.class);

    private long id; //use uuid later
    private String projectName="none";
    private String productOwner="none";
    private int ownerId=-1; //use uuid later
    private String projectDescription= "none";
    private String workType = "empty"; //json string
  
    private Date deadline;
    private double maximumBudget=-1.0;
    private double lowestBid = -1.0;
    private String lowestBidder="none"; //string for now..
    private double offer=-1.0;

    private boolean frontend = false;
    private boolean backend = false;
    private boolean ux = false;
    private boolean sysadmin = false;
    private boolean other = false;

    public Project(long id, String projectName, String projectDescription, String productOwner, String deadline, float maximumBudget,
                   boolean frontend, boolean backend, boolean ux, boolean sysadmin, boolean other) {
        this.id = id;
        this.projectDescription = projectDescription;
        this.projectName = projectName;
        this.productOwner = productOwner;
        try{
        this.deadline = sqlDateStringToUtilDate(deadline);
        }
        catch(Exception ex){
            log.info("Failed to convert date: " + ex);
        }
        //Work type flags
        this.maximumBudget = maximumBudget;
        this.frontend = frontend;
        this.backend = backend;
        this.ux = ux;
        this.sysadmin = sysadmin;
        this.other = other;
    }

    public Project(String projectName, String projectDescription, String productOwner){
        this.projectName = projectName;
        this.productOwner = productOwner;
    }

    public Project(){
        
    }

    public String getOffer(){
        return Double.toString(offer);
    }
    
    public long getId(){
        return id;
    }

    public String getProductOwner(){
        return productOwner;
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

    public boolean getFrontend(){
        return frontend;
    }

    public boolean getBackend(){
        return backend;
    }

    public boolean getUx(){
        return ux;
    }

    public boolean getSysadmin(){
        return sysadmin;
    }

    public boolean getOther(){
        return other;
    }

    public static String boolAsIntStr(boolean value){   
        
        return (value ? "1" : "0");
    }

    public String getDeadline(){
        return deadline.toString();
    }

    public String getDeadlineAsSqlDate() throws Exception{
        return new java.sql.Date(deadline.getTime()).toString();
    }

    public Date sqlDateStringToUtilDate(String date) throws Exception{
        Date myDate = new SimpleDateFormat("yyyy-MM-dd").parse(date);
        return myDate;
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
    public void setProductOwner(String productOwner){
        this.productOwner = productOwner;
    }
    public void setProjectDescription(String projectDescription){
        this.projectDescription = projectDescription;
    }

    public void setWorkType(JSONObject workType){ 
        log.info("json before parsing is " + workType.toJSONString() + " \n\n\n");
        
        try{
        this.frontend = Boolean.parseBoolean(workType.get("frontend").toString());
        this.backend = Boolean.parseBoolean(workType.get("backend").toString());
        this.ux = Boolean.parseBoolean(workType.get("ux").toString());
        this.sysadmin = Boolean.parseBoolean(workType.get("sysadmin").toString());
        this.other = Boolean.parseBoolean (workType.get("other").toString());
        this.workType = workType.toJSONString();
        log.info("worktype as json string is " + workType + "\n");
        }
        catch(Exception ex){
            log.info("Setting worktype failed with exception of: " + ex + "\n");
        }

    }

    public void setDeadline(String deadline) throws Exception{
        this.deadline = sqlDateStringToUtilDate(deadline);
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

    @Override
    public String toString() {
        return String.format(
                "Project[id=%d, projectName='%s', projectDescription='%s', productOwner='%s', deadline='%s', maximumBudget='%f', frontend='%s', backend='%s', ux='%s', sysadmin='%s', other='%s']",
                 id, projectName, projectDescription, productOwner, deadline, maximumBudget,
                 frontend, backend, ux, sysadmin, other);
    }

}