# facecv
opencv人脸识别检测demo-加载本地的OpenCV库，这样就可以用它来调用Java API。 创建实例CascadeClassifier，将已加载的分类器的文件名传递给它。 接下来我们将图片转化成Java API能够接受使用Highui类的格式，铺垫在OpenCV C++的n维密集数组类上边。 然后，调用分类器上的detectMultiScale方法传递给它图象和MatOfRect对象。这个过程之后，MatOfRect将有面部检测。 我们遍历所有的脸部检测并用矩形标记图像。 最后，将图像写入输出的 .png 文件里。
