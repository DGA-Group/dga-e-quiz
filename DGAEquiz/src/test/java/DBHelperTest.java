import com.dga.equiz.model.Campaign;
import com.dga.equiz.utils.ApplicationData;
import com.dga.equiz.utils.DBHelper;
import org.junit.jupiter.api.Test;

import java.sql.ResultSet;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

public class DBHelperTest {
    @Test
    void test1() {
        String sqlTestQuery = "SELECT * FROM account";
        try {
            ResultSet resultSet = DBHelper.query(sqlTestQuery);
            while (resultSet.next()) {
                System.out.println("Record " + resultSet.getInt(1) + " : \n"
                        + "     Username : " + resultSet.getString(2) + "\n"
                        + "     Password : " + resultSet.getString(3) + "\n");
            }
        } catch (Exception e) {
            e.printStackTrace();
            fail();
        }
        assertTrue(true);
    }

    @Test
    void test2() {
        // This situation the table is invalid "account" instead of "accout".
        String sqlTestQuery = "SELECT * FROM accout";
        try {
            ResultSet resultSet = DBHelper.query(sqlTestQuery);
            while (resultSet.next()) {
                System.out.println("Record " + resultSet.getInt(1) + " : \n"
                        + "     Username : " + resultSet.getString(2) + "\n"
                        + "     Password : " + resultSet.getString(3) + "\n");
            }
        } catch (Exception e) {
            e.printStackTrace();
            fail();
        }
        assertTrue(true);
    }

    @Test
    void testFetchCampaginData() {
        ApplicationData.getInstance().loadAllCampaign();
        boolean success = false;

        Map<Long, Campaign> campaignData = ApplicationData.getInstance().getCampaignData();
        for (var campaign : campaignData.values()) {
            System.out.println("Campaign number: " + campaign.getId());
            System.out.println("\tCampaign title: " + campaign.getTitle());
            System.out.println("\tCampaign description: " + campaign.getDescription());
            success = true;
        }
        assertEquals(success, true);

    }
}
