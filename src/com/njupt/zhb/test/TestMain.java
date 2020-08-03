package com.njupt.zhb.test;
public class TestMain {
  public static void main(String[] args) {
    System.out.println("Hello, OpenCV");
    // Load the native library.
    System.loadLibrary("opencv_java246");
    new DetectFaceDemo().run();
  }
}
