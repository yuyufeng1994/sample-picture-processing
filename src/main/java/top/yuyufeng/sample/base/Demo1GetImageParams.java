package top.yuyufeng.sample.base;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;

/**
 * 获取图片的参数：宽、高
 *
 * @author yuyufeng
 * @date 2018/11/26.
 */
public class Demo1GetImageParams {
    public static void main(String[] args) {
        File pic1 = new File(Demo1GetImageParams.class.getResource("/pic-1.jpg").getFile());
        System.out.println(pic1.getName() + " width:" + getImgWidth(pic1) + " height:" + getImgHeight(pic1));
    }

    /**
     * 获取图片宽度
     *
     * @param file 图片文件
     * @return 宽度
     */
    public static int getImgWidth(File file) {
        InputStream is = null;
        BufferedImage src = null;
        int ret = -1;
        try {
            is = new FileInputStream(file);
            src = javax.imageio.ImageIO.read(is);
            // 得到源图宽
            ret = src.getWidth(null);
            is.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ret;
    }


    /**
     * 获取图片高度
     *
     * @param file 图片文件
     * @return 高度
     */
    public static int getImgHeight(File file) {
        InputStream is = null;
        BufferedImage src = null;
        int ret = -1;
        try {
            is = new FileInputStream(file);
            src = javax.imageio.ImageIO.read(is);
            // 得到源图高
            ret = src.getHeight(null);
            is.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ret;
    }
}
