package main;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.imageio.stream.FileImageInputStream;
import javax.swing.Box;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSlider;
import javax.swing.border.TitledBorder;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import transformation.Scale;
import transformation.Turn;

public class Frame extends JFrame  {
	
	private static final long serialVersionUID = 1L;
	
	private File file = new File("src/images/1.jpg");
	private JPanel optionsPanel = new JPanel();
	private JPanel startImgPanel = new JPanel();
	private JPanel resultImgPanel = new JPanel();
	private JLabel ImageLabel;
	private JSlider sliderScale = new JSlider();
	private JSlider sliderTurn = new JSlider();
	private BufferedImage startImage = null;
	private Box boxScale;
	private Box boxTurn;
	Turn turnImg;
	Scale scaleImg;

	Frame() {
		setLayout(new BorderLayout());

		boxScale = Box.createVerticalBox();
		boxScale.setBorder(new TitledBorder("Масштабирование"));
		boxTurn = Box.createVerticalBox();
		boxTurn.setBorder(new TitledBorder("Поворот"));
		
		sliderScale.setMinimum(1);
		sliderScale.setMaximum(501);
		sliderScale.setValue(250);
		sliderScale.setMajorTickSpacing(100);
		sliderScale.setPaintLabels(true);
		sliderScale.setPaintTicks(true);
		
		sliderTurn.setMinimum(-180);
		sliderTurn.setMaximum(180);
		sliderTurn.setValue(0);
		sliderTurn.setMajorTickSpacing(60);
		sliderTurn.setPaintLabels(true);
		sliderTurn.setPaintTicks(true);
		
		boxTurn.add(sliderTurn);
		boxScale.add(sliderScale);

		optionsPanel.add(boxScale);
		optionsPanel.add(boxTurn);

		try {
			startImage = ImageIO.read(new FileImageInputStream(file));
		} catch (IOException e) {
			e.printStackTrace();
		}

		JLabel label = new JLabel(new ImageIcon(startImage));
		startImgPanel.add(label);
		
		add(startImgPanel, BorderLayout.WEST);
		add(optionsPanel, BorderLayout.NORTH);
		add(resultImgPanel, BorderLayout.CENTER);
		
		sliderScale.addChangeListener(new ChangeListener() {
		      public void stateChanged(ChangeEvent event) {
					double val1 = sliderScale.getValue();
					double val = val1/100;
					try {
						scaleImg = new Scale(startImage, val);
					} catch (NumberFormatException e) {
						scaleImg = new Scale(startImage, 1);
					}

					ImageLabel = new JLabel(new ImageIcon(scaleImg.getImage()));
					JScrollPane pane = new JScrollPane(ImageLabel);
					pane.setPreferredSize(new Dimension(624, 719));
					resultImgPanel.removeAll();
					resultImgPanel.add(pane);
					pack();
		        }
		      });
		
		sliderTurn.addChangeListener(new ChangeListener() {
		      public void stateChanged(ChangeEvent event) {
				int val = sliderTurn.getValue();
				try {
					turnImg = new Turn(startImage, val);
				} catch (NumberFormatException e) {
					turnImg = new Turn(startImage, 0);
				}

				ImageLabel = new JLabel(new ImageIcon(turnImg.getImage()));
				setPreferredSize(new Dimension(1150, 801));
				resultImgPanel.removeAll();
				resultImgPanel.add(ImageLabel);
				resultImgPanel.repaint();
				pack();
			}
		});

		pack();
	}
}
