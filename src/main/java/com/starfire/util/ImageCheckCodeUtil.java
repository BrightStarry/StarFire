package com.starfire.util;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Random;

import javax.imageio.ImageIO;

/**
 * 生成验证码图片 工具类 用来发送手机验证码前的验证
 */
public class ImageCheckCodeUtil {
	private int width = 175;// 图片宽度
	private int height = 35;// 图片高度
	private int size = 4;// 字符个数
	private int line = 20;// 干扰线条数
	private String code;// 验证码
	private BufferedImage bufferedImage;// 验证码图片buffer
	Random random = new Random();

	public String getCode() {
		return this.code.toLowerCase();
	}

	// 指定宽高
	public ImageCheckCodeUtil(int width, int height) {
		this.width = width;
		this.height = height;
		createImage();
	}

	// 不指定宽高
	public ImageCheckCodeUtil() {
		createImage();
	}

	// 指定宽高,字符个数
	public ImageCheckCodeUtil(int width, int height, int size) {
		this.width = width;
		this.height = height;
		this.size = size;
		createImage();
	}

	// 指定字符个数
	public ImageCheckCodeUtil(int size) {
		this.size = size;
		createImage();
	}

	// 生成图片
	private void createImage() {
		int fontWidth = width / size;// 字体宽度
		int fontHeight = height - 5;// 字体高度

		// 图像Buffer
		bufferedImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
		Graphics graphics = bufferedImage.getGraphics();
		// 设置背景色
		graphics.setColor(getRandColor(200, 250));
		graphics.fillRect(0, 0, width, height);
		// 设置字体
		Font font = new Font("Fixedsys", Font.BOLD, fontHeight);
		graphics.setFont(font);
		// 设置干扰线
		for (int i = 0; i < line; i++) {
			int xs = random.nextInt(width);
			int ys = random.nextInt(height);
			int xe = xs + random.nextInt(width);
			int ye = ys + random.nextInt(height);
			graphics.setColor(getRandColor(1, 255));
			graphics.drawLine(xs, ys, xe, ye);
		}
		// 添加噪点
		float yawRate = 0.1f;// 噪声率
		int area = (int) (yawRate * width * height);
		for (int i = 0; i < area; i++) {
			int x = random.nextInt(width);
			int y = random.nextInt(height);
			bufferedImage.setRGB(x, y, random.nextInt(255));
		}
		// 得到随机字符
		String code = getRandomStr(size);
		this.code = code;
		int codeY = height - 8;
		for (int i = 0; i < size; i++) {
			String str = code.substring(i, i + 1);
			graphics.setColor(getRandColor(1, 255));
			graphics.drawString(str, i * fontWidth, codeY);
		}

	}

	/**
	 * 得到随机字符
	 */
	private String getRandomStr(int size) {
		String str1 = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz1234567890";
		String str2 = "";
		int len = str1.length();
		int r;
		for (int i = 0; i < size; i++) {
			r = random.nextInt(len);
			str2 += str1.charAt(r);
		}
		return str2;
	}

	/**
	 * 得到随机颜色 给定范围
	 */
	public Color getRandColor(int star, int end) {
		// 限定 两个参数在 0-255之间，且 end > star
		if (star > 255)
			star = 255;
		if (star < 0)
			star = 0;
		if (end > 255)
			end = 255;
		if (end < 0)
			end = 0;
		if (end < star) {
			int temp = star;
			star = end;
			end = temp;
		}
		int r = star + random.nextInt(end - star);
		int g = star + random.nextInt(end - star);
		int b = star + random.nextInt(end - star);
		return new Color(r, g, b);
	}

	public void write(OutputStream os) throws IOException {
		ImageIO.write(bufferedImage, "png", os);
		os.close();
	}

//	public static void main(String[] args) throws IOException {
//		ImageCheckCodeUtil i = new ImageCheckCodeUtil();
//		File file = new File("D:/桌面/Desktop/111.png");
//		FileOutputStream fos = new FileOutputStream(file);
//		i.write(fos);
//
//	}

}
