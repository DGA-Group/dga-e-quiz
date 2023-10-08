import com.dga.equiz.utils.DBHelper;
import org.junit.jupiter.api.Test;

import java.sql.ResultSet;

import static org.junit.jupiter.api.Assertions.*;

public class DBHelperTest {
    @Test
    void test1() {
        String sqlTestQuery = "SELECT * FROM account";
        try {
            ResultSet resultSet = DBHelper.query(sqlTestQuery);
            while (resultSet.next()){
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
            while (resultSet.next()){
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
}
