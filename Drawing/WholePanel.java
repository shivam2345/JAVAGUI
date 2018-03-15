// Assignment #: 7
//         title: Shivaani Methuku
//    StudentID: 1212619354
//      Lecture: T, Th 4:30pm
//  Description: Create a program that allows user to change color, fill in, and manipulate the size of a rectangle

import java.awt.*;
import javax.swing.*;
import java.awt.event.*;  // to use listener interfaces

public class WholePanel extends JPanel
{
	private Color currentColor;
	private int currentWidth, currentHeight;
	private CanvasPanel canvas;
	private JPanel menuPanel;
	private JCheckBox fillCheck;
	private int x1, y1;
	private JRadioButton white, red, blue, green, orange;
	boolean fillColor=false;

	public WholePanel()
	{
		//white is the default color
		currentColor = Color.WHITE;

		//default x-y cooridnate, width, and height of a rectangle
		currentWidth = currentHeight = 100;
		x1 = 100; y1 = 100;

		fillCheck = new JCheckBox("Filled");

		//menupanel to display the color options and fill checkbox
		menuPanel = new JPanel();
		menuPanel.add(fillCheck);

		//Fill Listener part 
		FillListener listen = new FillListener();
		fillCheck.addItemListener(listen);

		//make a canvas for the rectangle to be placed on
		canvas = new CanvasPanel();

		//layout of the panel is a splitpane with a borderlayout
		JSplitPane sPane = new JSplitPane(JSplitPane.VERTICAL_SPLIT, menuPanel, canvas);

		setLayout(new BorderLayout());
		add(sPane, BorderLayout.CENTER);

		//create the different radio buttons
		white = new JRadioButton("white", true);
		red = new JRadioButton("red");
		blue = new JRadioButton("blue");
		green = new JRadioButton("green");
		orange = new JRadioButton("orange");

		//add the radio buttons to a buttongroup so that only one is selected at a time
		ButtonGroup group = new ButtonGroup(); //in swing
		group.add(white);
		group.add(red);
		group.add(blue);
		group.add(green);
		group.add(orange);

		//add the colorlistener to each button to tell what buttons are being pressed
		ColorListener listener = new ColorListener();
		white.addActionListener(listener);
		red.addActionListener(listener);
		blue.addActionListener(listener);
		green.addActionListener(listener);
		orange.addActionListener(listener);

		//add the buttons to the menu panel
		menuPanel.add(white);  //radio buttons are added to the ChoiceGUI panel
		menuPanel.add(red);
		menuPanel.add(blue);
		menuPanel.add(green);
		menuPanel.add(orange);
	}

	//insert ColorListener and FillListener classes
	private class ColorListener implements ActionListener
	{
		//  Sets the color of square depending on which
		// radio button was pressed.
		public void actionPerformed (ActionEvent event)
		{
			//get the information of which radio button is actually selected
			Object source = event.getSource();

			if (source == white)
			{
				currentColor = Color.WHITE;
			}
			else if (source == red) 
			{
				currentColor = Color.RED;
			}
			else if (source == blue)
			{
				currentColor = Color.BLUE;				
			}
			else if (source == green)
			{
				currentColor = Color.GREEN;
			}
			else if (source == orange)
			{
				currentColor = Color.ORANGE;
			}
			repaint();
		}
	}

	private class FillListener implements ItemListener 
	{
		//  Sets the fill color of square based on checkbox 
		//  determines the fill color
		public void itemStateChanged (ItemEvent event)
		{
			//check if the fill color choice is selcted
			if (fillCheck.isSelected() == true)
			{
				fillColor = true;
				repaint();
			}
			else if (fillCheck.isSelected() == false) 
			{
				fillColor = false;
				repaint();
			}
		}
	}

	//CanvasPanel is the panel where pressed keys will be drawn
	private class CanvasPanel extends JPanel
	{
		//Constructor to initialize the canvas panel
		public CanvasPanel( )
		{
			// make this canvas panel listen to mouse
			addMouseListener(new PointListener());
			addMouseMotionListener(new PointListener());

			setBackground(Color.BLACK);
		}


		//this method draws all characters pressed by a user so far
		public void paintComponent(Graphics page)
		{
			super.paintComponent(page);

			//set color, then draw a rectangle
			page.setColor(currentColor);

			//draw a rectangle onto the page
			page.drawRect(x1, y1, currentWidth, currentHeight);

			//if the fill color is selcted a certain color must also appear based on the radio buttons
			if (fillColor) {
				page.setColor(currentColor);
				page.fillRect(x1, y1, currentWidth, currentHeight);
			}
		}

		private Point mousePt, ptClick;
		int xValue;
		int yValue;
		int xValue2;
		int yValue2;
		int xWidth;
		int yHeight;
		boolean right;
		boolean left;
		boolean top;
		boolean bottom;
		// listener class that listens to the mouse
		public class PointListener implements MouseListener, MouseMotionListener
		{
			//in case that a user presses using a mouse,
			//record the point where it was pressed.
			public void mousePressed (MouseEvent event) {
				//get the position of which side is pressed based on where mouse is pressed and the current size of rect
				//use if statements and booleans to determine which side is pressed
				ptClick = event.getPoint();
				xValue = ptClick.x;
				yValue = ptClick.y;	
				yHeight = y1 + currentHeight;
				xWidth = x1 + currentWidth;
				top = false;
				right = false;
				bottom = false;
				left = false;
				//using +5 -5 for the range of the side pressed
				if (yValue >= y1-5 && yValue <= y1+5 && xValue <= xWidth ) 
				{
					//if top is pressed
					top = true;
					right = false;
					bottom = false;
					left = false;
				}
				if (yValue >= yHeight - 5 && yValue <= yHeight + 5 && xValue <= xWidth)
				{
					//if the bottom is pressed
					bottom = true;
					top = false;
					right = false;
					left = false;
				}
				if (xValue >= x1-5 && xValue <= x1+5)
				{
					//left is pressed
					left = true;
					top = false;
					bottom = false;
					right = false;
				}
				if (xValue >= xWidth -5 && xValue <= xWidth + 5) 
				{
					//right is pressed
					right = true;
					top = false;
					bottom = false;
					left = false;
				}
			}
			public void mouseClicked (MouseEvent event) {}
			public void mouseReleased (MouseEvent event) {}
			public void mouseEntered (MouseEvent event) {}
			public void mouseExited (MouseEvent event) {}
			public void mouseMoved(MouseEvent event) {}
			public void mouseDragged(MouseEvent event)
			{
				//check which points are being dragged at
				mousePt = event.getPoint();
				xValue2 = mousePt.x;
				yValue2 = mousePt.y;
				//int xend = x1 + currentWidth;
				//int yend = y1 - currentHeight;
				if (bottom)
				{
					currentHeight = currentHeight + (event.getY() - y1 - currentHeight);
					//if bottom change the height 
					if (currentHeight <= 10)
					{
						currentHeight = 10;
					}
					repaint();
				}
				if (top)
				{
					currentHeight = currentHeight - (yValue2 - y1);
						y1 = yValue2;
					if (currentHeight <= 10)
						//if top change the height and the y coordiante
					{
						currentHeight = 10;
						y1  -= 10;						
					}
					repaint();
				}
				if (right)
				{
					currentWidth = event.getX() - x1;
					if (currentWidth <= 10)
						//if right the width changes
					{
						currentWidth = 10;
					}
					repaint();
				}
				if (left) 
				{
					currentWidth = currentWidth + x1 - event.getX();
					x1 = xValue2;
					if (currentWidth <= 10)
						//if left the x and the width change
					{
						currentWidth = 10;
						x1 = xWidth - 10;
					}
					repaint();
				} 
			}

		} // end of PointListener

	} // end of Canvas Panel Class

} // end of Whole Panel Class
