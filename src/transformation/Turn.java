package transformation;

import java.awt.Color;
import java.awt.image.BufferedImage;

public class Turn {

	private BufferedImage resultImg;
	private int r;
	private int width;
	private int height;
	private int dx;
	private int dy;
	private int newX;
	private int newY;
	double angelDotNormal;

	public Turn(BufferedImage startImg, double angle) {

		r = (int) (Math.sqrt(Math.pow(startImg.getWidth(), 2)+ Math.pow(startImg.getHeight(), 2)) / 2);
		
		width = 2 * r;
		height = 2 * r;
		
		dx = (width - startImg.getWidth()) / 2;
		dy = (height - startImg.getHeight()) / 2;
		
		resultImg = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);

		for (int x = 0; x < width; x++)
			for (int y = 0; y < height; y++) {
				double centerDot = Math.sqrt(Math.pow(width / 2 - (x), 2)+ Math.pow(height / 2 - (y), 2));
				double h = width / 2 - (x);
				if (centerDot != 0){
					angelDotNormal = Math.acos((Math.abs(h) / centerDot));
				} else {
					angelDotNormal = 0;
				}

				if (((y >= height / 2) && (x < width / 2))|| ((y < height / 2) && (x >= width / 2)))
					angelDotNormal = -angelDotNormal;
				if (h > 0) {
					newX = (int) (width / 2	- Math.cos(angelDotNormal + angle * Math.PI / 180) * centerDot - dx);
					newY = (int) (height / 2 - Math.sin(angelDotNormal + angle * Math.PI / 180) * centerDot - dy);
				} else {
					newX = (int) (width / 2	- Math.cos(angelDotNormal + angle * Math.PI / 180 + Math.PI) * centerDot - dx);
					newY = (int) (height / 2 - Math.sin(angelDotNormal + angle * Math.PI / 180 + Math.PI)	* centerDot - dy);
				}

				if ((newX >= 0) && (newY >= 0)&& (newX <= startImg.getWidth() - 1) && (newY <= startImg.getHeight() - 1)){
					resultImg.setRGB(x, y, startImg.getRGB(newX, newY));					
				} else {
					resultImg.setRGB(x, y, (new Color(238, 238, 238).getRGB()));
				}
			}
	}

	public BufferedImage getImage() {
		return resultImg;
	}
}