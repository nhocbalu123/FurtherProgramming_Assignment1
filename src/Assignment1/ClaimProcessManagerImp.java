package Assignment1;
/**
 * @author <Nguyen Quy Minh Thang - s3978302>
 */
import java.util.ArrayList;
import java.util.List;

public class ClaimProcessManagerImp implements ClaimProcessManager {
    private List<Claim> claims;
    public ClaimProcessManagerImp() {
        this.claims = new ArrayList<>();
    }
}
