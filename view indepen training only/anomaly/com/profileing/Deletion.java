package anomaly.com.profileing;


/*
	delete all the
*/
import java.io.File;

public class Deletion {
	private String pract_path = "anomaly/SplitedFrames";
	private String train_path = "anomaly/train_image";
	private String blob_path = "anomaly/blob-frames"; //$NON-NLS-1$
	public Deletion(){
		delete(pract_path);
		delete(train_path);
		delete(blob_path);
	}
	private void delete(String path){
		File file[] = new File(path).listFiles(); //$NON-NLS-1$
		try{
		if(file.length>0)
			for(int i=0;i<file.length;i++){
				file[i].deleteOnExit();
				System.out.println(file[i].delete());
			}
		}catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}
	public static void main(String[] args) {
		new Deletion();
	}
}
