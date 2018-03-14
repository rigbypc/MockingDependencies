package point.of.sale.test;

import static org.junit.Assert.*;

import java.io.File;
import java.io.IOException;

import org.apache.commons.codec.digest.DigestUtils;
import org.junit.Test;

import com.google.common.hash.HashCode;
import com.google.common.hash.Hashing;

public class TestHash {

	//from http://www.baeldung.com/java-md5
	@Test
	public void givenPassword_whenHashingUsingCommons_thenVerifying()  {
	    String hash = "35454B055CC325EA1AF2126E27707052";
	    String password = "ILoveJava";
	 
	    String md5Hex = DigestUtils
	      .md5Hex(password).toUpperCase();
	         
	    assertTrue(md5Hex.equals(hash));
	}
	
	@Test
	public void givenFile_whenChecksumUsingGuava_thenVerifying() 
	  throws IOException {
	    String filename = System.getProperty("user.dir") + "/" + "SaleStorage.ser";
	    String checksum = "B5D6FD3A5475E8CAFCCB0E098E86426326BDC30DAE001DE7EFC8CCCB8463D4C6";
	         
	    HashCode hash = com.google.common.io.Files.
	    					asByteSource(new File(filename)).hash(Hashing.sha256());
	    String myChecksum = hash.toString().toUpperCase();
	    
	    System.out.println("Checksum: " + myChecksum);
	    assertTrue(myChecksum.equals(checksum));
	}

}
