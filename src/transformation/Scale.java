package transformation;

import java.awt.image.BufferedImage;

public class Scale {
	
	private BufferedImage resultImg;
	private int newWidth;
	private int newHeight;
	private int width;
	private int height;
	private int rgb;

	public Scale(BufferedImage startImg, double coefficient) {
		
		width = startImg.getWidth();
		height = startImg.getHeight();
		
		newWidth = (int) (startImg.getWidth() * coefficient);
		newHeight = (int) (startImg.getHeight() * coefficient);
		
		resultImg = new BufferedImage(newWidth, newHeight, BufferedImage.TYPE_INT_RGB);

		for (int x = 0; x < newWidth; x++) {
			for (int y = 0; y < newHeight; y++) {
				rgb = startImg.getRGB(x * width / newWidth, y * height / newHeight);
				resultImg.setRGB(x, y, rgb);
			}
		}
	}

	public BufferedImage getImage() {
		return resultImg;
	}

}
