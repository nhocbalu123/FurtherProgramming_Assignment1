package Assignment1;
/**
 * @author <Nguyen Quy Minh Thang - s3978302>
 */
import java.util.List;

public interface ClaimProcessManager {
    void add(Claim claim);
    void update(Claim claim);
    void delete(Claim claim);
    Claim getOne(String id);
    List<Claim> getAll();
}
