package Assignment1;
/**
 * @author <Nguyen Quy Minh Thang - s3978302>
 */

import java.text.ParseException;

public class Main {
    public static void main(String[] args) throws ParseException {
        DataManager dataManager = new DataManager();
        dataManager.loadCustomers();
        dataManager.loadInsuranceCards();
        dataManager.loadClaims();

        UI ui = new UI(dataManager);
        ui.start();
    }
}




