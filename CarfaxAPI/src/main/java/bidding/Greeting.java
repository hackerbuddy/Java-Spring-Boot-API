package bidding;
import org.springframework.web.bind.annotation.CrossOrigin;

public class Greeting {

    private final String content;

    public Greeting(String content) {
        this.content = content;
    }

    public String getContent() {
        return content;
    }
}