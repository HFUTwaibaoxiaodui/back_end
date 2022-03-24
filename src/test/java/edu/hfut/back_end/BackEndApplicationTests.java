package edu.hfut.back_end;

import edu.hfut.back_end.Utils.GenerateOrderCode;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class BackEndApplicationTests {

    @Test
    void contextLoads() {
        GenerateOrderCode.getInstance();
    }

}
