import com.dga.equiz.controller.editProfile.ProfileController;
import com.dga.equiz.model.Campaign;
import com.dga.equiz.model.question.ImageQuestion;
import com.dga.equiz.utils.ApplicationData;
import com.dga.equiz.utils.DBHelper;
import org.junit.jupiter.api.Test;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Map;
import java.util.Scanner;

import static org.junit.jupiter.api.Assertions.*;

public class DBHelperTest {
    @Test
    void test1() {
        String sqlTestQuery = "SELECT * FROM account";
        try {
            ResultSet resultSet = DBHelper.executeQuery(sqlTestQuery);
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
        String sqlTestQuery = "SELECT * FROM account";
        try {
            ResultSet resultSet = DBHelper.executeQuery(sqlTestQuery);
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

    @Test
    void testabc() {
        String sqlQuery = "SELECT * FROM `account`";
        ResultSet resultSet = null;
        Statement statement = null;
        Connection connection = null;
        try {
            resultSet = DBHelper.executeQuery(sqlQuery);
            statement = resultSet.getStatement();
            connection = statement.getConnection();
            while (resultSet.next()) {
                long id = resultSet.getLong(1);
                String username = resultSet.getString(2);
                String password = resultSet.getString(3);

                System.out.println(id + " " + username + " " + password);
            }
        } catch (Exception e) {
            System.out.println("===========================");
            System.out.println("Unable to load all image question");
            System.out.println("===========================");
            e.printStackTrace();
        } finally {
            try {
                DBHelper.closeQuery(resultSet,statement,connection);
            } catch (SQLException e) {
                System.out.println("Unable to close connection!");
            }
        }
    }

    @Test
    void insertTest() throws SQLException {
        Scanner sc = new Scanner(System.in);
        String sqlQuery = "insert into account (id, username, user_password, mail, dob, phone, github)";
        String username = "ninggianggg";
        String user_password = "123";
        String mail = "123@gmail.com";
        String dob = "2004-01-01";
        String phone = "012323323";
        String github = "asbcsdsd.com";

        sqlQuery += "values ('" + 5 + "','" + username + "','" + user_password+ "','" + mail + "','" + dob + "','" + phone + "','" + github + "');";

        DBHelper.executeUpdate(sqlQuery);
    }

}
