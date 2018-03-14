package point.of.sale;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.HashMap;

public class SerializeHashMap {

	@SuppressWarnings("unchecked")
	public static HashMap<String, String> loadMap(String fileName) {
		try {
			FileInputStream fileIn = new FileInputStream(fileName);
			ObjectInputStream in = new ObjectInputStream(fileIn);
			HashMap<String, String> map = (HashMap<String, String>)in.readObject();
			in.close();
			fileIn.close();
			return map;
		} 
		catch(FileNotFoundException | ClassCastException e) {
			return null;
		}
		catch (IOException i) {
			i.printStackTrace();
			return null;
		} catch (ClassNotFoundException c) {
			c.printStackTrace();
			return null;
		}
	}
	
	public static void persistMap(HashMap<String, String> map, String fileName) {
		
		try {
			FileOutputStream fileOut = new FileOutputStream(fileName);
			ObjectOutputStream out = new ObjectOutputStream(fileOut);
			out.writeObject(map);
			out.close();
			fileOut.close();
		} 
		catch (IOException i) {
			i.printStackTrace();
			return;
		} 
	}

}
