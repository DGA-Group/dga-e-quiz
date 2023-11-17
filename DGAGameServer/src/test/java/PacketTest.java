import com.dga.game.DBHelper;
import com.dga.game.EquizPacket.EquizPacket;
import com.dga.game.EquizPacket.Message.MessageRequest;
import org.apache.commons.lang3.RandomStringUtils;
import org.junit.jupiter.api.Test;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class PacketTest {
    @Test
    public void testMessagePacket() {
        EquizPacket request = new MessageRequest("689751");
        assertEquals(request.getType(), "message");
    }

    @Test
    public void testRandomRoomId() {
        String generatedString = RandomStringUtils.randomNumeric(10);
        System.out.println(generatedString);
    }

    @Test
    public void testSQL(){
        ResultSet resultSet = null;
        Statement statement = null;
        Connection connection = null;
        try {
            String sql = "SELECT word FROM av WHERE word = 'rose';";
            resultSet = DBHelper.executeQuerySqlite(sql);
            while(resultSet.next()){
                System.out.println(resultSet.getString(1));
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try{
                DBHelper.closeQuery(resultSet, statement, connection);
            }catch (Exception ignore){}
        }
    }

}
