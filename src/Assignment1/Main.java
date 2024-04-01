package Assignment1;

import java.util.List;

public class Main {
    public static void main(String[] args) {
        //Create an instance of ClaimProcessManagerImpl
        ClaimProcessManagerImp manager = new ClaimProcessManagerImp();

        //Create an instance of Claim
        Claim newClaim = new Claim("f-1234567890");
        manager.add(newClaim);
        Claim newClaim2 = new Claim("f-111111111");
        manager.add(newClaim2);
        System.out.println(newClaim.getId());

        // Get all claims
        List<Claim> allClaims = manager.getAll();

        // Print all claims
        for (Claim claim : allClaims) {
            System.out.println(claim);
        }
    }
}
