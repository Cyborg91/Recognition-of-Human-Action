package anomaly.com.profileing;

/*
 * This class is used to serialize and deserialize the objects
 */
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class DataSerialization {
	//your given object is serialized in given file location
	public boolean dataSerialize(Object object,String fileName)
	{
		ObjectOutputStream objOut;
		FileOutputStream fos;
		try {
			fos = new FileOutputStream(fileName);
			objOut=new ObjectOutputStream(fos);
			objOut.writeObject(object);
			objOut.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
		return true;
	}
	//deserialize the object from the given your location
	public Object dataDeSerialize(String fileName)
	{
		Object object = null;
		ObjectInputStream objIn;
		try {
			objIn=new ObjectInputStream(new FileInputStream(fileName));
			object=objIn.readObject();
			objIn.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return object;
	}
}
