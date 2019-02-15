package bidding;

public class JSONMessage{
    private String jsonResultString;

    public JSONMessage(String jsonResultString){
        this.jsonResultString = jsonResultString;
    }
    public String getJsonResultString(){
        return jsonResultString;
    }
    public void setJsonResultString(String jsonResultString){
        this.jsonResultString = jsonResultString;
    }
}