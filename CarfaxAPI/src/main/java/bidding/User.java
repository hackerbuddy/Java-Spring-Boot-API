package bidding;
import java.util.ArrayList;
import java.util.UUID;
import org.springframework.web.bind.annotation.CrossOrigin;

public class User{
    private final UUID id = UUID.randomUUID();
    private String name;
    private String email;
    private String username;
    private String password;

}