/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package TokenTest;

import com.faruk.karadenizkonfeksiyon.domain.UserBuilder;
import com.faruk.karadenizkonfeksiyon.domain.User;
import org.junit.Test;

/**
 *
 * @author Faruk
 */
public class JwtTest {

    @Test
    public void test() {
        
        User faruk = new UserBuilder()
                .id((long)255)
                .name("Faruk")
                .lastName("Karadeniz")
                .password("Fk123123123")
                .userName("faruk_fk")
                .build();
        
    }
}
