package com.zq.fin.seckill.util;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.imageio.ImageIO;

import com.jhlabs.image.ScaleFilter;
import com.zq.fin.seckill.common.BaseConstant;

public class CodeRecognitionUtil {

	//****************** 锟街讹拷锟借定锟斤拷值 *********************
	
	private static int isBlackWhiteInt = 300;
	private static int isBlackOrWhiteIntFrom = 30;
	private static int isBlackOrWhiteIntTo = 730;
	private static int colorBrightInt = 100;
	
	//*****************************************************
	
	private static Map<BufferedImage, String> trainMap = null;
	
	/**
	 * @return 0:锟斤拷 1:锟斤拷
	 */
	public static int isBlack(int colorInt) {
		Color color = new Color(colorInt);
		if (color.getRed() + color.getGreen() + color.getBlue() <= isBlackWhiteInt) {
			return 1;
		}
		return 0;
	}

	/**
	 * @return 0:锟斤拷 1:锟斤拷
	 */
	public static int isWhite(int colorInt) {
		Color color = new Color(colorInt);
		if (color.getRed() + color.getGreen() + color.getBlue() > isBlackWhiteInt) {
			return 1;
		}
		return 0;
	}

	/**
	 * @return 锟斤拷锟斤拷值
	 */
	public static int getColorBright(int colorInt) {
		Color color = new Color(colorInt);
		return color.getRed() + color.getGreen() + color.getBlue();

	}

	/**
	 * @return 0:锟阶猴拷锟斤拷锟斤拷 1:锟斤拷or锟斤拷
	 */
	public static int isBlackOrWhite(int colorInt) {
		if (getColorBright(colorInt) < isBlackOrWhiteIntFrom || getColorBright(colorInt) > isBlackOrWhiteIntTo) {
			return 1;
		}
		return 0;
	}

	/**
	 * 去锟斤拷锟斤拷锟斤拷色
	 * @param picFile
	 * @return
	 * @throws Exception
	 */
	public static BufferedImage removeBackgroud(String picFile)
			throws Exception {
		BufferedImage img = ImageIO.read(new File(picFile));
		int width = img.getWidth();
		int height = img.getHeight();
		for (int x = 1; x < width - 1; ++x) {
			for (int y = 1; y < height - 1; ++y) {
				if (getColorBright(img.getRGB(x, y)) < colorBrightInt) {
					if (isBlackOrWhite(img.getRGB(x - 1, y))
							+ isBlackOrWhite(img.getRGB(x + 1, y))
							+ isBlackOrWhite(img.getRGB(x, y - 1))
							+ isBlackOrWhite(img.getRGB(x, y + 1)) == 4) {
						img.setRGB(x, y, Color.WHITE.getRGB());
					}
				}
			}
		}
		for (int x = 1; x < width - 1; ++x) {
			for (int y = 1; y < height - 1; ++y) {
				if (getColorBright(img.getRGB(x, y)) < colorBrightInt) {
					if (isBlackOrWhite(img.getRGB(x - 1, y))
							+ isBlackOrWhite(img.getRGB(x + 1, y))
							+ isBlackOrWhite(img.getRGB(x, y - 1))
							+ isBlackOrWhite(img.getRGB(x, y + 1)) == 4) {
						img.setRGB(x, y, Color.WHITE.getRGB());
					}
				}
			}
		}
		img = img.getSubimage(1, 1, img.getWidth() - 2, img.getHeight() - 2);
		return img;
	}

	/**
	 * 去锟斤拷锟秸帮拷
	 * @param img
	 * @return
	 * @throws Exception
	 */
	public static BufferedImage removeBlank(BufferedImage img) throws Exception {
		int width = img.getWidth();
		int height = img.getHeight();
		int start = 0;
		int end = 0;
		Label1: for (int y = 0; y < height; ++y) {
			for (int x = 0; x < width; ++x) {
				if (isBlack(img.getRGB(x, y)) == 1) {
					start = y;
					break Label1;
				}
			}
		}
		Label2: for (int y = height - 1; y >= 0; --y) {
			for (int x = 0; x < width; ++x) {
				if (isBlack(img.getRGB(x, y)) == 1) {
					end = y;
					break Label2;
				}
			}
		}
		return img.getSubimage(0, start, width, end - start + 1);
	}
	
	/**
	 * 锟斤拷锟街凤拷锟酵硷拷锟斤拷染锟斤拷校锟�
	 * @param img
	 * @return
	 * @throws Exception
	 */
	public static List<BufferedImage> splitImage2(BufferedImage img)
			throws Exception {
		List<BufferedImage> subImgs = new ArrayList<BufferedImage>();
		
		int startx = 5;
		int starty = 5;
		int width = 11;
		int height = 14;

		// 锟斤拷1锟斤拷
		subImgs.add(removeBlank(img.getSubimage(startx, starty, width, height)));
		// 锟斤拷2锟斤拷
		subImgs.add(removeBlank(img.getSubimage(startx + width, starty, width, height)));
		// 锟斤拷3锟斤拷
		subImgs.add(removeBlank(img.getSubimage(startx + (width * 2), starty, width, height)));
		// 锟斤拷4锟斤拷
		subImgs.add(removeBlank(img.getSubimage(startx + (width * 3), starty, width, height)));
		// 锟斤拷5锟斤拷
		subImgs.add(removeBlank(img.getSubimage(startx + (width * 4), starty, width, height)));
		
		return subImgs;
	}

	/**
	 * 取锟斤拷训锟斤拷锟斤拷锟�
	 * @return
	 * @throws Exception
	 */
	public static Map<BufferedImage, String> loadTrainData() throws Exception {
		if (trainMap == null) {
			Map<BufferedImage, String> map = new HashMap<BufferedImage, String>();
			File dir = new File(BaseConstant.CODERECOGNITIONUTIL_URL);
			File[] files = dir.listFiles();
			for (File file : files) {
				map.put(scaleImage(ImageIO.read(file)), file.getName().charAt(0) + "");
			}
			trainMap = map;
		}
		return trainMap;
	}

	/**
	 * 
	 * @param img
	 * @return
	 */
	public static boolean isNotEight(BufferedImage img) {
		int width = img.getWidth();
		int height = img.getHeight();
		int minCount = width;
		for (int y = height / 2 - 2; y < height / 2 + 2; ++y) {
			int count = 0;
			for (int x = 0; x < width / 2 + 2; ++x) {
				if (isBlack(img.getRGB(x, y)) == 1) {
					count++;
				}
			}
			minCount = Math.min(count, minCount);
		}
		return minCount < 2;
	}

	/**
	 * 
	 * @param img
	 * @return
	 */
	public static boolean isNotThree(BufferedImage img) {
		int width = img.getWidth();
		int height = img.getHeight();
		int minCount = width;
		for (int y = height / 2 - 3; y < height / 2 + 3; ++y) {
			int count = 0;
			for (int x = 0; x < width / 2 + 1; ++x) {
				if (isBlack(img.getRGB(x, y)) == 1) {
					count++;
				}
			}
			minCount = Math.min(count, minCount);
		}
		return minCount > 0;
	}

	/**
	 * 
	 * @param img
	 * @return
	 */
	public static boolean isNotFive(BufferedImage img) {
		int width = img.getWidth();
		int height = img.getHeight();
		int minCount = width;
		for (int y = 0; y < height / 3; ++y) {
			int count = 0;
			for (int x = width * 2 / 3; x < width; ++x) {
				if (isBlack(img.getRGB(x, y)) == 1) {
					count++;
				}
			}
			minCount = Math.min(count, minCount);
		}
		return minCount > 0;
	}

	/**
	 * 匹锟斤拷识锟斤拷锟街凤拷
	 * @param img
	 * @param map
	 * @return
	 * @throws Exception
	 */
	public static String getSingleCharOcr(BufferedImage img,
			Map<BufferedImage, String> map) throws Exception {
		String result = "#";
		img = scaleImage(img);
		int width = img.getWidth();
		int height = img.getHeight();
		int min = width * height;
		boolean bNotEight = isNotEight(img);
		boolean bNotThree = isNotThree(img);
		boolean bNotFive = isNotFive(img);
		for (BufferedImage bi : map.keySet()) {
			if (bNotThree && map.get(bi).startsWith("3"))
				continue;
			if (bNotEight && map.get(bi).startsWith("8"))
				continue;
			if (bNotFive && map.get(bi).startsWith("5"))
				continue;
			double count1 = getBlackCount(img);
			double count2 = getBlackCount(bi);
			if (Math.abs(count1 - count2) / Math.max(count1, count2) > 0.25)
				continue;
			int count = 0;
			if (width < bi.getWidth() && height < bi.getHeight()) {
				for (int m = 0; m <= bi.getWidth() - width; m++) {
					for (int n = 0; n <= bi.getHeight() - height; n++) {
						Label1: for (int x = m; x < m + width; ++x) {
							for (int y = n; y < n + height; ++y) {
								if (isWhite(img.getRGB(x - m, y - n)) != isWhite(bi
										.getRGB(x, y))) {
									count++;
									if (count >= min)
										break Label1;
								}
							}
						}
					}
				}
			} else {
				int widthmin = width < bi.getWidth() ? width : bi.getWidth();
				int heightmin = height < bi.getHeight() ? height : bi
						.getHeight();
				Label1: for (int x = 0; x < widthmin; ++x) {
					for (int y = 0; y < heightmin; ++y) {
						if (isWhite(img.getRGB(x, y)) != isWhite(bi
								.getRGB(x, y))) {
							count++;
							if (count >= min)
								break Label1;
						}
					}
				}
			}
			if (count < min) {
				min = count;
				result = map.get(bi);
			}
		}
		return result;
	}

	/**
	 * 匹锟斤拷识锟斤拷全锟斤拷锟街凤拷
	 * @param file
	 * @return
	 * @throws Exception
	 */
	public static String getAllOcr(String file) throws Exception {
		BufferedImage img = removeBackgroud(file);
		List<BufferedImage> listImg = splitImage2(img);
		Map<BufferedImage, String> map = loadTrainData();
		String result = "";
		for (BufferedImage bi : listImg) {
			result += getSingleCharOcr(bi, map);
		}
		//System.out.println(result);
		//ImageIO.write(img, "JPG", new File("code_result.jpg"));
		return result;
	}

	/**
	 * 
	 * @param img
	 * @return
	 */
	public static int getBlackCount(BufferedImage img) {
		int width = img.getWidth();
		int height = img.getHeight();
		int count = 0;
		for (int x = 0; x < width; ++x) {
			for (int y = 0; y < height; ++y) {
				if (isBlack(img.getRGB(x, y)) == 1) {
					count++;
				}
			}
		}
		return count;
	}

	public static BufferedImage scaleImage(BufferedImage img)
	{
		//ScaleFilter sf = new ScaleFilter(16,16);
		ScaleFilter sf = new ScaleFilter(11,14);
		//BufferedImage imgdest = new BufferedImage(11,14, img.getType());
		BufferedImage imgdest = new BufferedImage(11,14, BufferedImage.TYPE_INT_RGB);
		return sf.filter(img, imgdest);
	}
	

	/**
	 * @param args
	 * @throws Exception
	 */
	public static void main(String[] args) throws Exception {
		
			String code = getAllOcr("code.jpg");
			System.out.println("code.jpg = " + code);
	}
}
