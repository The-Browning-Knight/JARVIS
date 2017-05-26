import org.opencv.core.Core;
import org.opencv.core.CvType;
import org.opencv.core.Mat;
import org.opencv.imgcodecs.Imgcodecs;
import org.opencv.imgproc.Imgproc;

public class EdgeDetection {
	public static void main (String[] args)
	{
		long start_time = System.currentTimeMillis();
		
		System.loadLibrary(Core.NATIVE_LIBRARY_NAME);
		String img_file = "C:\\Users\\pc\\Dropbox (Personal)\\GTA_Test\\test-case2.jpg";
		Mat color = Imgcodecs.imread(img_file);
		
		Mat gray = new Mat();
		Mat draw = new Mat();
		Mat wide = new Mat();
		
		Imgproc.cvtColor(color, gray, Imgproc.COLOR_BGR2GRAY);
		Imgproc.Canny(gray, wide, 50, 150, 3, false);
		wide.convertTo(draw, CvType.CV_8U);
		
		if (Imgcodecs.imwrite(img_file, draw)){
			System.out.println("edge is detected.....");
		}
		
		long end_time = System.currentTimeMillis();
		long total_time = end_time - start_time;
		System.out.println("This took " + total_time + " ms.");			// Shouldn't take more than couple hundred ms
	}
}