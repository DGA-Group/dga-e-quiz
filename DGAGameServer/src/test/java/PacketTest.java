import com.dga.game.EquizPacket.EquizPacket;
import com.dga.game.EquizPacket.Message.MessageRequest;
import org.apache.commons.lang3.RandomStringUtils;
import org.junit.jupiter.api.Test;

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

}
