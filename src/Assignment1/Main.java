package Assignment1;
/**
 * @author <Nguyen Quy Minh Thang - s3978302>
 */

public class Main {
    public static void main(String[] args) {
        DataManager dataManager = new DataManager();
        dataManager.loadCustomers();
        dataManager.loadInsuranceCards();
        dataManager.loadClaims();

        UI ui = new UI(dataManager);
        ui.start();
    }
}




