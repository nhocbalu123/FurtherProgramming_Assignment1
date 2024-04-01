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
    // Add claim
    public void add(Claim claim) {
        claims.add(claim);
    }

    //Update claim
    public void update(Claim claim) {
        for (int i = 0; i < claims.size(); i++) {
            if (claims.get(i).getId().equals(claim.getId())) { //check if there is the correct claim ID
                claims.set(i, claim);
                break; // break out once found the ID
            }
        }
    }

    //Delete claim
    public void delete(Claim claim) {
        claims.removeIf(c -> c.getId().equals(claim.getId()));
    }

    //Return one claim
    public Claim getOne(String id) {
        for (Claim claim : claims) {
            if (claim.getId().equals(id)) {
                return claim;
            }
        }
        return null;
    }
    //Return list of claims
    public List<Claim> getAll() {
        return claims;
    }
}
