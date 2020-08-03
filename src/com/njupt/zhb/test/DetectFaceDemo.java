package com.njupt.zhb.test;
import org.opencv.core.Core;
import org.opencv.core.Mat;
import org.opencv.core.MatOfRect;
import org.opencv.core.Point;
import org.opencv.core.Rect;
import org.opencv.core.Scalar;
import org.opencv.highgui.Highgui;
import org.opencv.objdetect.CascadeClassifier;

//
// Detects faces in an image, draws boxes around them, and writes the results
// to "faceDetection.png".
//
public class DetectFaceDemo {
  public void run() {
    System.out.println("\nRunning DetectFaceDemo");
    System.out.println(getClass().getResource("lbpcascade_frontalface.xml").getPath());
    // Create a face detector from the cascade file in the resources���ӱ����£�alt_tree��LBP�ļ��������һ�µģ�����LBP����ʱҪ�̺ܶ࣬���LBP�����˵ʵʱ�Ը�ǿ��
    // directory.
    //CascadeClassifier faceDetector = new CascadeClassifier(getClass().getResource("lbpcascade_frontalface.xml").getPath());
    //Mat image = Highgui.imread(getClass().getResource("lena.png").getPath());
    //ע�⣺Դ�����·������ӡһ����/����������ǳ������´���
		/*
		 * Detected 0 faces Writing faceDetection.png libpng warning: Image
		 * width is zero in IHDR libpng warning: Image height is zero in IHDR
		 * libpng error: Invalid IHDR data
		 */
    //��ˣ����ǽ���һ���ַ�ȥ��
    String xmlfilePath=getClass().getResource("lbpcascade_frontalface.xml").getPath().substring(1);
    CascadeClassifier faceDetector = new CascadeClassifier(xmlfilePath);//�������ļ�lbpcascade_frontalface.xml�д���һ������ʶ����;����ʵ��CascadeClassifier�����Ѽ��صķ��������ļ������ݸ�����
    Mat image = Highgui.imread(getClass().getResource("we.jpg").getPath().substring(1));
    // Detect faces in the image.
    // MatOfRect is a special container class for Rect.
    MatOfRect faceDetections = new MatOfRect();
    faceDetector.detectMultiScale(image, faceDetections);//���÷������ϵ�detectMultiScale�������ݸ���ͼ���MatOfRect�����������֮��MatOfRect�����沿��⡣

    System.out.println(String.format("Detected %s faces", faceDetections.toArray().length));

    // Draw a bounding box around each face.
    for (Rect rect : faceDetections.toArray()) {
        Core.rectangle(image, new Point(rect.x, rect.y), new Point(rect.x + rect.width, rect.y + rect.height), new Scalar(0, 255, 0));
    }

    // Save the visualized detection.
    String filename = "faceDetection.png";
    System.out.println(String.format("Writing %s", filename));
    Highgui.imwrite(filename, image);
  }
}

/*���ر��ص�OpenCV�⣬�����Ϳ�������������Java API��
����ʵ��CascadeClassifier�����Ѽ��صķ��������ļ������ݸ�����
���������ǽ�ͼƬת����Java API�ܹ�����ʹ��Highui��ĸ�ʽ���̵���OpenCV C++��nά�ܼ��������ϱߡ�
Ȼ�󣬵��÷������ϵ�detectMultiScale�������ݸ���ͼ���MatOfRect�����������֮��MatOfRect�����沿��⡣
���Ǳ������е�������Ⲣ�þ��α��ͼ��
��󣬽�ͼ��д������� .png �ļ��*/
