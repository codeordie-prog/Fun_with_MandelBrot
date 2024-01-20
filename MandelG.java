



import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Graphics;
import java.awt.image.BufferedImage;

import javax.swing.JFrame;
import javax.swing.JPanel;



/*The purpose of this class is to create a graphical representation of the Mandelbrot set based on the algorithm written in the class
 * MandelBrot.java. However, the main goal here is to alleviate the computation intensity of the heavy calculations;
 *
 * Objectives;
 *
 * 1. Due to the heavy calculations involved i need to make sure they are done only once or only when necessary
 * 2. Therefore i option to use BufferedImage to store the drawing made and render it onto the display panel when needed
 * 3. I will use PaintComponent method for painting-this improves appearance due to double buffering
 * 4. **/

public class MandelG extends JFrame {

	private static final long serialVersionUID = 1L;


	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			@Override
			public void run() {
				try {
					MandelG frame = new MandelG();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public MandelG() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(200, 50, 900, 600);
		setTitle("The Graphical Representation of The MandelBrot Sets with Respect to z = z² + c");

		//add the drawing panel
		add(new Drawing());

	}


	class Drawing extends JPanel{


		//instances

		double xmin = -1.7;
		double xmax = 1.0;
		double ymin = -1.2;
		double ymax = 1.0;
		double dx = xmax - xmin;
		double dy = ymax - ymin;

		BufferedImage buffer;

		Drawing(){

			setPreferredSize(new Dimension(900,600));

		}

		@Override
		public void paintComponent(Graphics g) {

			super.paintComponent(g);

			if(buffer == null || buffer.getWidth() != getWidth() || buffer.getHeight() != getHeight()) {

				buffer = new BufferedImage(getWidth(),getHeight(),BufferedImage.TYPE_INT_ARGB);
				drawset();
			}

			g.drawImage(buffer, 0, 0, this);
		}


		private void drawset() {

			//get the graphics of the buffer

			Graphics g = buffer.getGraphics();

			//draw each pixel

			for(int x = 0; x<getWidth(); x++) {

				for(int y = 0; y<getHeight(); y++) {

					double real = xmin + (x/(double)getWidth())*dx;
					double imag = ymin + (y/(double)getHeight())*dy;

					Color color = colorMethod(real,imag);

					g.setColor(color);
					g.fillRect(x, y, 1, 1);
				}
			}

			g.dispose(); // dispose to free resources
		}

		private Color colorMethod(double real, double imag) {

			MandelBrotSet set = new MandelBrotSet();
			Color color;

			int iterations = set.calculateSets(real, imag);

			if(iterations == set.getMaxIterations()) {

				color = Color.black;
			}
			else {

				//determine color based on how far the points are from the mandelbrot set

				color = new Color(Color.HSBtoRGB((float)iterations/256, 1, iterations/iterations + 10.0f));
			}

			return color;
		}


	}

}
