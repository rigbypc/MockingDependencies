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
	    String checksum = "460EC32FDC0A09B5814E18FCF92421FC361CE7C9617415A1D55BA60428BD3473";
	         
	    HashCode hash = com.google.common.io.Files.
	    					asByteSource(new File(filename)).hash(Hashing.sha256());
	    String myChecksum = hash.toString().toUpperCase();
	    
	    System.out.println("Checksum: " + myChecksum);
	    assertTrue(myChecksum.equals(checksum));
	}

}
