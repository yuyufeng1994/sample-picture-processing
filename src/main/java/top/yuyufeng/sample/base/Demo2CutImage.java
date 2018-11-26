package top.yuyufeng.sample.base;

import javax.imageio.ImageIO;
import javax.imageio.ImageReadParam;
import javax.imageio.ImageReader;
import javax.imageio.stream.ImageInputStream;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.*;
import java.util.Arrays;

/**
 * @author yuyufeng
 * @date 2018/11/26.
 */
public class Demo2CutImage {
    public static void main(String[] args) throws FileNotFoundException {
        File pic1 = new File(Demo1GetImageParams.class.getResource("/pic-1.jpg").getFile());
        Rectangle rectangle = new Rectangle(1500, 2000, 500, 500);
        FileOutputStream fileOutputStream = new FileOutputStream("G://test/pic-1-part.jpg");
        cutImage(pic1, fileOutputStream, rectangle);
    }

    /**
     * 裁剪图片
     *
     * @param srcImg
     * @param output
     * @param rect
     */
    public static void cutImage(File srcImg, OutputStream output, Rectangle rect) {
        if (srcImg.exists()) {
            FileInputStream fis = null;
            ImageInputStream iis = null;
            try {
                fis = new FileInputStream(srcImg);
                // ImageIO 支持的图片类型 : [BMP, bmp, jpg, JPG, wbmp, jpeg, png, PNG, JPEG, WBMP, GIF, gif]
                String types = Arrays.toString(ImageIO.getReaderFormatNames()).replace("]", ",");
                String suffix = null;
                // 获取图片后缀
                if (srcImg.getName().indexOf(".") > -1) {
                    suffix = srcImg.getName().substring(srcImg.getName().lastIndexOf(".") + 1);
                }// 类型和图片后缀全部小写，然后判断后缀是否合法
                if (suffix == null || types.toLowerCase().indexOf(suffix.toLowerCase() + ",") < 0) {
                    System.out.println("Sorry, the image suffix is illegal. the standard image suffix is {}." + types);
                    return;
                }
                // 将FileInputStream 转换为ImageInputStream
                iis = ImageIO.createImageInputStream(fis);
                // 根据图片类型获取该种类型的ImageReader
                ImageReader reader = ImageIO.getImageReadersBySuffix(suffix).next();
                reader.setInput(iis, true);
                ImageReadParam param = reader.getDefaultReadParam();
                param.setSourceRegion(rect);
                BufferedImage bi = reader.read(0, param);
                ImageIO.write(bi, suffix, output);
            } catch (FileNotFoundException e) {
                System.out.println(e.getMessage());
            } catch (IOException e) {
                System.out.println(e.getMessage());
            } finally {
                try {
                    if (fis != null) {
                        fis.close();
                    }
                    if (iis != null) {
                        iis.close();
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        } else {
            System.out.println(srcImg.getPath() + "the src image is not exist.");
        }
    }

}
