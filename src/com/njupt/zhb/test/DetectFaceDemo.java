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
    // Create a face detector from the cascade file in the resources复杂背景下，alt_tree和LBP的检测结果都是一致的，但是LBP的用时要短很多，因此LBP相对来说实时性更强。
    // directory.
    //CascadeClassifier faceDetector = new CascadeClassifier(getClass().getResource("lbpcascade_frontalface.xml").getPath());
    //Mat image = Highgui.imread(getClass().getResource("lena.png").getPath());
    //注意：源程序的路径会多打印一个‘/’，因此总是出现如下错误
		/*
		 * Detected 0 faces Writing faceDetection.png libpng warning: Image
		 * width is zero in IHDR libpng warning: Image height is zero in IHDR
		 * libpng error: Invalid IHDR data
		 */
    //因此，我们将第一个字符去掉
    String xmlfilePath=getClass().getResource("lbpcascade_frontalface.xml").getPath().substring(1);
    CascadeClassifier faceDetector = new CascadeClassifier(xmlfilePath);//从配置文件lbpcascade_frontalface.xml中创建一个人脸识别器;创建实例CascadeClassifier，将已加载的分类器的文件名传递给它。
    Mat image = Highgui.imread(getClass().getResource("we.jpg").getPath().substring(1));
    // Detect faces in the image.
    // MatOfRect is a special container class for Rect.
    MatOfRect faceDetections = new MatOfRect();
    faceDetector.detectMultiScale(image, faceDetections);//调用分类器上的detectMultiScale方法传递给它图象和MatOfRect对象。这个过程之后，MatOfRect将有面部检测。

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

/*加载本地的OpenCV库，这样就可以用它来调用Java API。
创建实例CascadeClassifier，将已加载的分类器的文件名传递给它。
接下来我们将图片转化成Java API能够接受使用Highui类的格式，铺垫在OpenCV C++的n维密集数组类上边。
然后，调用分类器上的detectMultiScale方法传递给它图象和MatOfRect对象。这个过程之后，MatOfRect将有面部检测。
我们遍历所有的脸部检测并用矩形标记图像。
最后，将图像写入输出的 .png 文件里。*/
