package Util;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.File;

import static org.junit.jupiter.api.Assertions.*;

public class CryptoTest {
    private static File file;
    private Crypto crypto;

    private File created1;
    private File created2;

    @BeforeAll
    public static void SetUp(){
        file = new File("src/Main/TestResources/WłoskiCoded.txt");
    }

    @BeforeEach
    public void SetUpBeforeEach(){
        crypto = new Crypto(file);
    }

    @Test
    public void isCryptedFalse() {
        assertFalse(crypto.isCrypted());
    }

    @Test
    public void isCryptedTrue() {
        created1 = crypto.Encrypt();
        Crypto crypto1 = new Crypto(created1);
        assertTrue(crypto1.isCrypted());
    }

    @Test
    public void encryptAndDecrypt() {
        created1 = crypto.Encrypt();
        Crypto crypto1 = new Crypto(created1);
        created2 = crypto1.Decrypt();
        FileComparator comparator = new FileComparator(file,created2);
        assertTrue(comparator.assertEqual());
    }
}