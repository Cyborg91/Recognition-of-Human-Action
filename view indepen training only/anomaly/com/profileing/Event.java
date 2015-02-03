package anomaly.com.profileing;
//using this class we maintain the event values
import java.awt.Point;
import java.io.Serializable;

public class Event implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int Threshold = Integer.parseInt(Messages.getString("Threshold")); //$NON-NLS-1$
	private int Threshold1 = 100;
	private Point startPoint;
	private Point endPoint;
	private int height;
	private int width;
	private int numWhitePixels;
	private int numBlackPixels;
	private int numOccurences ;
	private int pch;
	private String BlobName;
	
	/**
	 * @param startPoint
	 * @param endPoint
	 * @param height
	 * @param width TODO
	 * @param numWhitePixels
	 * @param numBlackPixels
	 * @param pch
	 */
	public Event(String BlobName,Point startPoint, Point endPoint, int height, int width, int numWhitePixels, int numBlackPixels, int pch) {
		super();
		this.BlobName = BlobName;
		this.startPoint = startPoint;
		this.endPoint = endPoint;
		this.height = height;
		this.width = width;
		this.numWhitePixels = numWhitePixels;
		this.numBlackPixels = numBlackPixels;
		this.numOccurences = numOccurences;
		this.pch = pch;
	}
	public Point getEndPoint() {
		return endPoint;
	}
	public void setEndPoint(Point endPoint) {
		this.endPoint = endPoint;
	}
	public int getHeight() {
		return height;
	}
	public void setHeight(int height) {
		this.height = height;
	}
	public int getNumBlackPixels() {
		return numBlackPixels;
	}
	public void setNumBlackPixels(int numBlackPixels) {
		this.numBlackPixels = numBlackPixels;
	}
	public int getNumOccurences() {
		return numOccurences;
	}
	public void setNumOccurences(int numOccurences) {
		this.numOccurences = numOccurences;
	}
	public int getNumWhitePixels() {
		return numWhitePixels;
	}
	public void setNumWhitePixels(int numWhitePixels) {
		this.numWhitePixels = numWhitePixels;
	}
	public int getPch() {
		return pch;
	}
	public void setPch(int pch) {
		this.pch = pch;
	}
	public Point getStartPoint() {
		return startPoint;
	}
	public void setStartPoint(Point startPoint) {
		this.startPoint = startPoint;
	}
	public boolean equals(Event event){
//		if(this.startPoint.toString().equals(event.startPoint.toString()) && this.endPoint.toString().equals(event.endPoint.toString())){
//		if(this.startPoint.x == event.startPoint.x && this.startPoint.y == event.startPoint.y && this.height == event.height){
		if(this.width == event.width && this.height == event.height){
			if(Math.abs(this.pch-event.pch)<Threshold){
				if(Math.abs(this.numWhitePixels-event.numWhitePixels)<Threshold1){
					if(Math.abs(this.numBlackPixels-event.numBlackPixels)<Threshold1){
						this.numOccurences++;
						return true;
					}
				}
			}
		}
		return false;
	}
	public String toString(){
		String str = startPoint.x + ":"+startPoint.y + ","; //$NON-NLS-1$ //$NON-NLS-2$
		str+=endPoint.x + ":" +endPoint.y ; //$NON-NLS-1$
		str +="occurence: "+ numOccurences; //$NON-NLS-1$
		str +="changed pixel No: " + pch;
		return str;
	}
	public int getWidth() {
		return width;
	}
	public void setWidth(int width) {
		this.width = width;
	}
	public String getBlobName() {
		return BlobName;
	}
	public void setBlobName(String blobName) {
		BlobName = blobName;
	}
}
