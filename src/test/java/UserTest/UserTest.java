/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package UserTest;

import java.io.UnsupportedEncodingException;
import java.nio.charset.Charset;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Faruk
 */
public class UserTest {

    @Test
    public void testMd5Password() throws NoSuchAlgorithmException, UnsupportedEncodingException {

        MessageDigest digest = MessageDigest.getInstance("MD5");

        digest.reset();

        String password = "Fk4791350,";

        digest.update(password.getBytes("UTF-8"));
        
        String result = new String(digest.digest());
        
        System.out.println(result);

    }

}
