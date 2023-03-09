package com.example.sz.Cv;

import com.example.sz.Dm.DmSoft;
import com.example.sz.Sz_Component.SendMessage;
import org.opencv.calib3d.Calib3d;
import org.opencv.core.*;
import org.opencv.features2d.DescriptorMatcher;
import org.opencv.features2d.SIFT;
import org.opencv.imgcodecs.Imgcodecs;
import org.opencv.imgproc.Imgproc;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.awt.Point;
import java.util.LinkedList;
import java.util.List;


@Component
public class Cv {

    @Autowired
    Cv_Result cv_result;
    @Autowired
    DmSoft dm;
    @Autowired
    SendMessage sendMessage;

    static {
        nu.pattern.OpenCV.loadShared();
        System.loadLibrary(Core.NATIVE_LIBRARY_NAME);
    }


    /**
     * TM_SQDIFF是平方差匹配、TM_SQDIFF_NORMED是标准平方差匹配,利用平方差来进行匹配,最好匹配为0.匹配越差,匹配值越大。
     * TM_CCORR是相关性匹配、TM_CCORR_NORMED是标准相关性匹配,采用模板和图像间的乘法操作,数越大表示匹配程度较高, 0表示最坏的匹配效果。
     * TM_CCOEFF是相关性系数匹配、TM_CCOEFF_NORMED是标准相关性系数匹配,将模版对其均值的相对值与图像对其均值的相关值进行匹配,1表示完美匹配,-1表示糟糕的匹配,0表示没有任何相关性(随机序列)。
     * 随着从简单的测量(平方差)到更复杂的测量(相关系数),我们可获得越来越准确的匹配(同时也意味着越来越大的计算代价)。
     * 相关性是越接近1越大越好，平方差是越小越好，所以TM_SQDIFF在使用时和其他的是有所区别的。
     * 模板匹配结果Mat要是32位的。
     */
    public static void main(String[] args) {

    }

    public Cv_Result Temp_Match(String temp) {

        /*
         * IMREAD_UNCHANGED = -1 ：不进行转化，比如保存为了16位的图片，读取出来仍然为16位。
         * IMREAD_GRAYSCALE = 0 ：进行转化为灰度图，比如保存为了16位的图片，读取出来为8位，类型为CV_8UC1。
         * IMREAD_COLOR = 1 ：进行转化为三通道图像。
         * IMREAD_ANYDEPTH = 2 ：如果图像深度为16位则读出为16位，32位则读出为32位，其余的转化为8位。
         * IMREAD_ANYCOLOR = 4 ：图像以任何可能的颜色格式读取
         * IMREAD_LOAD_GDAL = 8 ：使用GDAL驱动读取文件，GDAL(Geospatial Data Abstraction
         * Library)是一个在X/MIT许可协议下的开源栅格空间数据转换库。它利用抽象数据模型来表达所支持的各种文件格式。
         *	它还有一系列命令行工具来进行数据转换和处理。
         */

        //截图
        dm.CapturePng();
//        Mat src = Imgcodecs.imread("src/main/resources/pic/111.png");//待匹配图片
//        Mat template = Imgcodecs.imread("src/main/resources/pic/" + temp);// 获取匹配模板
        String property = System.getProperty("user.dir");
        Mat src = Imgcodecs.imread(property + "/pic/jietu.png");//待匹配图片
        Mat template = Imgcodecs.imread(property + temp);// 获取匹配模板

        /*
          TM_SQDIFF = 0, 平方差匹配法，最好的匹配为0，值越大匹配越差
          TM_SQDIFF_NORMED = 1,归一化平方差匹配法
          TM_CCORR = 2,相关匹配法，采用乘法操作，数值越大表明匹配越好
          TM_CCORR_NORMED = 3,归一化相关匹配法
          TM_CCOEFF = 4,相关系数匹配法，最好的匹配为1，-1表示最差的匹配
          TM_CCOEFF_NORMED = 5;归一化相关系数匹配法
         */
        int method = Imgproc.TM_CCOEFF_NORMED;

        int width = src.cols() - template.cols() + 1;
        int height = src.rows() - template.rows() + 1;
        // 创建32位模板匹配结果Mat
        Mat result = new Mat(width, height, CvType.CV_32FC1);

        /*
         * 将模板与重叠的图像区域进行比较。
         * @param image运行搜索的图像。 它必须是8位或32位浮点。
         * @param templ搜索的模板。 它必须不大于源图像并且具有相同的数据类型。
         * @param result比较结果图。 它必须是单通道32位浮点。 如果image是（W * H）并且templ是（w * h），则结果是（（W-w + 1）*（H-h + 1））。
         * @param方法用于指定比较方法的参数，请参阅默认情况下未设置的#TemplateMatchModes。
         * 当前，仅支持#TM_SQDIFF和#TM_CCORR_NORMED方法。
         */
        Imgproc.matchTemplate(src, template, result, method);

        // 归一化 详见https://blog.csdn.net/ren365880/article/details/103923813
//        Core.normalize(result, result, 0, 1, Core.NORM_MINMAX, -1, new Mat());

        // 获取模板匹配结果 minMaxLoc寻找矩阵(一维数组当作向量,用Mat定义) 中最小值和最大值的位置.
        Core.MinMaxLocResult mmr = Core.minMaxLoc(result);

        // 绘制匹配到的结果 不同的参数对结果的定义不同
        double x, y;


        //最佳匹配度
        double good_score = mmr.maxVal;

//        //最佳匹配坐标
        x = mmr.maxLoc.x;
        y = mmr.maxLoc.y;
        int center_x = (int) (x + template.cols() * 0.5);
        int center_y = (int) (y + template.rows() * 0.5);
        cv_result.setScore(good_score);
        cv_result.setMid(new Point(center_x, center_y));
        内存释放(new Mat[]{src, template, result});
        return cv_result;

    }

    /**
     * 因为该匹配方法 截图并不是截全图，所以匹配到的坐标也不是窗口坐标
     *
     * @param template
     * @param 截图范围
     * @return
     */
    public java.awt.Point Sift_Match(String template, int[] 截图范围) {

        Mat resT = new Mat();
        Mat resO = new Mat();

        //即当detector 又当Detector
        SIFT sift = SIFT.create();


        Mat templateImage = Imgcodecs.imread(System.getProperty("user.dir") + template);
        dm.CapturePng(截图范围[0], 截图范围[1], 截图范围[2], 截图范围[3], "jietu.png");
        Mat originalImage = Imgcodecs.imread(System.getProperty("user.dir") + "/pic/jietu.png");

        MatOfKeyPoint templateKeyPoints = new MatOfKeyPoint();
        MatOfKeyPoint originalKeyPoints = new MatOfKeyPoint();

        //获取模板图的特征点
        sift.detect(templateImage, templateKeyPoints);
        sift.detect(originalImage, originalKeyPoints);


        sift.compute(templateImage, templateKeyPoints, resT);
        sift.compute(originalImage, originalKeyPoints, resO);

        List<MatOfDMatch> matches = new LinkedList();
        DescriptorMatcher descriptorMatcher = DescriptorMatcher.create(DescriptorMatcher.FLANNBASED);
//        System.out.println("寻找最佳匹配");

//        printPic("ptest", templateImage);
//        printPic("ptesO", originalImage);
//
//        printPic("test", resT);
//        printPic("tesO", resO);

        /**
         * knnMatch方法的作用就是在给定特征描述集合中寻找最佳匹配
         * 使用KNN-matching算法，令K=2，则每个match得到两个最接近的descriptor，然后计算最接近距离和次接近距离之间的比值，当比值大于既定值时，才作为最终match。
         */
        descriptorMatcher.knnMatch(resT, resO, matches, 2);
//        System.out.println("计算匹配结果");
        LinkedList<DMatch> goodMatchesList = new LinkedList();
        //对匹配结果进行筛选，依据distance进行筛选
        matches.forEach(match -> {
            DMatch[] dmatcharray = match.toArray();
            DMatch m1 = dmatcharray[0];
            DMatch m2 = dmatcharray[1];
            double nndrRatio = 0.7;
            if (m1.distance <= m2.distance * nndrRatio) {
                goodMatchesList.addLast(m1);
            }
        });

        int matchesPointCount = goodMatchesList.size();
        //当匹配后的特征点大于等于 4 个，则认为模板图在原图中，该值可以自行调整
        if (matchesPointCount >= 4) {
//            System.out.println("模板图在原图匹配成功！");

            List<KeyPoint> templateKeyPointList = templateKeyPoints.toList();
            List<KeyPoint> originalKeyPointList = originalKeyPoints.toList();
            LinkedList<org.opencv.core.Point> objectPoints = new LinkedList();
            LinkedList<org.opencv.core.Point> scenePoints = new LinkedList();
            goodMatchesList.forEach(goodMatch -> {
                objectPoints.addLast(templateKeyPointList.get(goodMatch.queryIdx).pt);
                scenePoints.addLast(originalKeyPointList.get(goodMatch.trainIdx).pt);
            });
            MatOfPoint2f objMatOfPoint2f = new MatOfPoint2f();
            objMatOfPoint2f.fromList(objectPoints);
            MatOfPoint2f scnMatOfPoint2f = new MatOfPoint2f();
            scnMatOfPoint2f.fromList(scenePoints);
            //使用 findHomography 寻找匹配上的关键点的变换
            Mat homography = Calib3d.findHomography(objMatOfPoint2f, scnMatOfPoint2f, Calib3d.RANSAC, 3);

            /**
             * 透视变换(Perspective Transformation)是将图片投影到一个新的视平面(Viewing Plane)，也称作投影映射(Projective Mapping)。
             */
            Mat templateCorners = new Mat(4, 1, CvType.CV_32FC2);
            Mat templateTransformResult = new Mat(4, 1, CvType.CV_32FC2);
            templateCorners.put(0, 0, new double[]{0, 0});
            templateCorners.put(1, 0, new double[]{templateImage.cols(), 0});
            templateCorners.put(2, 0, new double[]{templateImage.cols(), templateImage.rows()});
            templateCorners.put(3, 0, new double[]{0, templateImage.rows()});
            //使用 perspectiveTransform 将模板图进行透视变以矫正图象得到标准图片
            Core.perspectiveTransform(templateCorners, templateTransformResult, homography);

            //矩形四个顶点  匹配的图片经过旋转之后就这个矩形的四个点的位置就不是正常的abcd了
            double[] pointA = templateTransformResult.get(0, 0);
//            double[] pointB = templateTransformResult.get(1, 0);
            double[] pointC = templateTransformResult.get(2, 0);
//            double[] pointD = templateTransformResult.get(3, 0);

            //将匹配的图像用用四条线框出来
//            Imgproc.rectangle(originalImage, new org.opencv.core.Point(pointA), new org.opencv.core.Point(pointC), new Scalar(0, 255, 0));
//            Imgcodecs.imwrite("sift.png",originalImage);
            //该坐标为截图区域坐标
            java.awt.Point mid = new java.awt.Point((int) ((pointA[0] + pointC[0]) / 2), (int) ((pointA[1] + pointC[1]) / 2));
            //转化为窗口坐标
            内存释放(new Mat[]{resO, resT, templateImage, originalImage, homography, templateCorners, templateTransformResult});
            templateKeyPoints.release();
            originalKeyPoints.release();
            return new Point(mid.x + 截图范围[0], mid.y + 截图范围[1]);
        } else {
            return null;
        }

    }

    public Point 模板匹配等待图片出现(String temp) {
        int num = 1;
        while (true) {
            cv_result = Temp_Match(temp);
            if (cv_result.getScore() > 0.8) {
                return cv_result.getMid();
            } else if (num > 5) {
                sendMessage.send("等待图片超时");

            }
            num += 1;
            dm.Delay(500);
        }
    }

    public Point SIFT匹配等待图片出现(String template, int[] 截图范围) {
        int num = 1;
        while (true) {
            Point point = Sift_Match(template, 截图范围);
            if (point != null) {
                return point;
            } else if (num > 5) {
                sendMessage.send("等待图片超时");
            }
            num += 1;
            dm.Delay(500);
        }
    }

    public Cv_Result 不截图匹配(String temp) {

        /*
         * IMREAD_UNCHANGED = -1 ：不进行转化，比如保存为了16位的图片，读取出来仍然为16位。
         * IMREAD_GRAYSCALE = 0 ：进行转化为灰度图，比如保存为了16位的图片，读取出来为8位，类型为CV_8UC1。
         * IMREAD_COLOR = 1 ：进行转化为三通道图像。
         * IMREAD_ANYDEPTH = 2 ：如果图像深度为16位则读出为16位，32位则读出为32位，其余的转化为8位。
         * IMREAD_ANYCOLOR = 4 ：图像以任何可能的颜色格式读取
         * IMREAD_LOAD_GDAL = 8 ：使用GDAL驱动读取文件，GDAL(Geospatial Data Abstraction
         * Library)是一个在X/MIT许可协议下的开源栅格空间数据转换库。它利用抽象数据模型来表达所支持的各种文件格式。
         *	它还有一系列命令行工具来进行数据转换和处理。
         */

//        //截图
//        dm.CapturePng(0, 0, 1100, 650, "111.png");
        Mat src = Imgcodecs.imread(System.getProperty("user.dir") + "/pic/jietu.png");//待匹配图片
        Mat template = Imgcodecs.imread(System.getProperty("user.dir") + temp);// 获取匹配模板


        /*
          TM_SQDIFF = 0, 平方差匹配法，最好的匹配为0，值越大匹配越差
          TM_SQDIFF_NORMED = 1,归一化平方差匹配法
          TM_CCORR = 2,相关匹配法，采用乘法操作，数值越大表明匹配越好
          TM_CCORR_NORMED = 3,归一化相关匹配法
          TM_CCOEFF = 4,相关系数匹配法，最好的匹配为1，-1表示最差的匹配
          TM_CCOEFF_NORMED = 5;归一化相关系数匹配法
         */
        int method = Imgproc.TM_CCOEFF_NORMED;

        int width = src.cols() - template.cols() + 1;
        int height = src.rows() - template.rows() + 1;
        // 创建32位模板匹配结果Mat
        Mat result = new Mat(width, height, CvType.CV_32FC1);

        /*
         * 将模板与重叠的图像区域进行比较。
         * @param image运行搜索的图像。 它必须是8位或32位浮点。
         * @param templ搜索的模板。 它必须不大于源图像并且具有相同的数据类型。
         * @param result比较结果图。 它必须是单通道32位浮点。 如果image是（W * H）并且templ是（w * h），则结果是（（W-w + 1）*（H-h + 1））。
         * @param方法用于指定比较方法的参数，请参阅默认情况下未设置的#TemplateMatchModes。
         * 当前，仅支持#TM_SQDIFF和#TM_CCORR_NORMED方法。
         */
        Imgproc.matchTemplate(src, template, result, method);

        // 归一化 详见https://blog.csdn.net/ren365880/article/details/103923813
//        Core.normalize(result, result, 0, 1, Core.NORM_MINMAX, -1, new Mat());

        // 获取模板匹配结果 minMaxLoc寻找矩阵(一维数组当作向量,用Mat定义) 中最小值和最大值的位置.
        Core.MinMaxLocResult mmr = Core.minMaxLoc(result);

        // 绘制匹配到的结果 不同的参数对结果的定义不同
        double x, y;


        //最佳匹配度
        double good_score = mmr.maxVal;

//        //最佳匹配坐标
        x = mmr.maxLoc.x;
        y = mmr.maxLoc.y;
        int center_x = (int) (x + template.cols() * 0.5);
        int center_y = (int) (y + template.rows() * 0.5);
        cv_result.setScore(good_score);
        cv_result.setMid(new Point(center_x, center_y));

        内存释放(new Mat[]{result, src, template});
        return cv_result;

    }

    private void 内存释放(Mat[] mats) {
        for (Mat mat : mats) {
            mat.release();
        }
    }


}
