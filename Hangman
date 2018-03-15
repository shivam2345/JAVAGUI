// http://math.hws.edu/eck/cs124/s13/lab12/
// http://math.hws.edu/eck/cs124/s13/lab4/index.html
// https://gist.github.com/SedaKunda/79e1d9ddc798aec3a366919f0c14a078

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.util.ArrayList;

/**
 *  A GUI version of the game of Hangman.  The user tries to guess letters in
 *  a secret word, and loses after 7 guesses that are not in the word.  The
 *  user guesses a letter by clicking a button whose text is that letter.
 */
public class Hangman extends JPanel {

	private Display display; // The central panel of the GUI, where things are drawn

	private ArrayList<JButton> alphabetButtons = new ArrayList<JButton>(); // 26 buttons, with lables "A", "B", ..., "Z"
	private JButton nextButton;    // A button the user can click after one game ends to go on to the next word.
	private JButton giveUpButton;  // A button that the user can click during a game to give up and end the game.

	private String message;     // A message that is drawn in the Display.
	private WordList wordlist;  // An object holding the list of possible words that can be used in the game.
	private String word;    	// The current secret word.
	String current = "";
	private char [] currentWord = new char [20];
	private String guesses;     // A string containing all the letters that the user has guessed so far.
	private boolean gameOver;   // False when a game is in progress, true when a game has ended and a new one not yet begun.
	private int wrongGuess;     // The number of incorrect letters that the user has guessed in the current game.
	/**
	 * This class defines a listener that will respond to the events that occur
	 * when the user clicks any of the buttons in the button.  The buttons are
	 * labeled "Next word", "Give up", "Quit", "A", "B", "C", ..., "Z".
	 */
	private class ButtonHandler implements ActionListener {
		public void actionPerformed( ActionEvent evt ) {
			JButton whichButton = (JButton)evt.getSource();  // The button that the user clicked.
			String cmd = evt.getActionCommand();  // The test from the button that the user clicked.
			if (cmd.equals("Quit") || cmd.equals("Give up") || cmd.equals("Next"))
			{

				if (cmd.equals("Quit")) { // Respond to Quit button by ending the program.
					System.exit(0);
				}
				else {
					message = "The command is " + cmd;		
				}
				if (cmd.equals("Give up"))
				{
					startGame();
					giveUpButton.setEnabled(true);
				}
			}
			else {
				char ch = cmd.charAt(0);
				int in = word.indexOf(ch);
				if (in != -1)
				{
					char[] myNameChars = current.toCharArray();
					whichButton.setEnabled(false);
					in = word.indexOf(ch);
					while(in >= 0) {
						myNameChars[in] = ch;
						current = String.valueOf(myNameChars);
						in = word.indexOf(ch, in+1);
					}
				}
				else 
				{
					guesses += ch;
					wrongGuess++;
					whichButton.setEnabled(false);
				}
				if (wordIsComplete() == true)
				{
					message = "You guessed right. Congrats!";
					startGame();
				}
			}
			display.repaint();  // Causes the display to be redrawn, to show any changes made in this method.
		}
	}

	/**
	 * This class defines the panel that occupies the large central area in the
	 * main panel.  The paintComponent() method in this class is responsible for
	 * drawing the content of that panel.  It shows everything that that the user
	 * is supposed to see, based on the current values of all the instance variables.
	 * 
	 * drawLine(int x1, int y1, int x2, int y2)
	 * is used to draw a straight line from point (x1,y1) to (x2,y2).
	 */
	private class Display extends JPanel {
		Display() {
			setPreferredSize(new Dimension(620,420));
			setBackground( new Color(250, 230, 180) );
			setFont( new Font("Serif", Font.BOLD, 20) );
		}
		protected void paintComponent(Graphics g) {
			super.paintComponent(g);
			((Graphics2D)g).setStroke(new BasicStroke(3));
			if (message != null) {
				g.setColor(Color.RED);
				g.drawString(message, 30, 40);
				Font currentFont = g.getFont();
				Font newFont = currentFont.deriveFont(currentFont.getSize() * 2.0F);
				g.setFont(newFont);
				g.drawString(current, 30,400);
			}
		}
	}

	/**
	 * The constructor that creates the main panel, which is represented
	 * by this class.  It makes all the buttons and subpanels and adds 
	 * them to the main panel.
	 */
	public Hangman() {

		ButtonHandler buttonHandler = new ButtonHandler(); // The ActionListener that will respond to button clicks.

		/* Create the subpanels and add them to the main panel.
		 */

		display = new Display();  // The display panel that fills the large central area of the main panel.
		JPanel bottom = new JPanel();  // The small panel on the bottom edge of the main panel.
		JPanel top = new JPanel();

		top.setLayout( new GridLayout(2,13));
		setLayout(new BorderLayout(3,3));  // Use a BorderLayout layout manager on the main panel.
		add(top, BorderLayout.NORTH);
		add(display, BorderLayout.CENTER); // Put display in the central position in the "CENTER" position.
		add(bottom, BorderLayout.SOUTH);   // Put bottom in the "SOUTH" position of the layout.

		/* Create alphabet buttons, register the ActionListener to respond to clicks on the
		 * buttons, add them to alphabet button, and add them to the top panel.
		 */
		for(int i = 65; i <= 90; i++) 
		{
			System.out.println((char)i + " ");
			String str = (char)i + "";
			JButton button = new JButton(str);
			button.addActionListener(buttonHandler);
			top.add(button);
			alphabetButtons.add(button);
		}

		/* Create three buttons, register the ActionListener to respond to clicks on the
		 * buttons, and add them to the bottom panel.
		 */

		nextButton = new JButton("Next word");
		nextButton.addActionListener(buttonHandler);
		bottom.add(nextButton);

		giveUpButton = new JButton("Give up");
		giveUpButton.addActionListener(buttonHandler);
		bottom.add(giveUpButton);

		JButton quit = new JButton("Quit");
		quit.addActionListener(buttonHandler);
		bottom.add(quit);

		/* Make the main panel a little prettier
		 */

		setBackground( new Color(100,0,0) );
		setBorder(BorderFactory.createLineBorder(new Color(100,0,0), 3));

		/* Get the list of possible secret words from the resource file named "wordlist.txt".
		 */

		wordlist = new WordList("wordlist.txt");

		/* Start the first game.
		 */

		startGame();

	} // end constructor

	/**
	 * This method should be called any time a new game starts. It picks a new
	 * secret word, initializes all the variables that record the state of the
	 * game, and sets the enabled/disabled state of all the buttons.
	 */
	private void startGame() {
		gameOver = false;
		guesses = "";
		wrongGuess = 0;
		nextButton.setEnabled(false);
		for (int i = 0; i < alphabetButtons.size(); i++) {
			alphabetButtons.get(i).setEnabled(true);
		}
		giveUpButton.setEnabled(true);
		int index = (int)(Math.random()*wordlist.getWordCount());

		System.out.print(index);
		word = wordlist.removeWord(index);
		word = word.toUpperCase();
		for (int k = 0; k < word.length(); k++)
		{
			currentWord[k] = '-';
		}
		//g.drawChars(currentWord, 0, currentWord.length, 30, 400);
		for (int k = 0; k < word.length(); k++)
		{
			current += currentWord[k];
		} 
		message = "The word has " + word.length() + " letters.  Let's play Hangman!";
	}

	/**
	 * This method can be called to test whether the user has guessed all the letters
	 * in the current secret word.  That would mean the user has won the game.
	 */
	private boolean wordIsComplete() {
		for (int i = 0; i < word.length(); i++) {
			char ch = word.charAt(i);
			if (current.indexOf(ch) == -1 ) {
				return false;
			}
		}
		return true;
	}

	/**
	 * This main program makes it possible to run this class as an application.  The main routine
	 * creates a window, sets it to contain a panel of type Hangman, and shows the window in the
	 * center of the screen.
	 */
	public static void main(String[] args) {
		JFrame window = new JFrame("Hangman"); // The window, with "Hangman" in the title bar.
		Hangman panel = new Hangman();  // The main panel for the window.
		window.setContentPane(panel);   // Set the main panel to be the content of the window
		window.pack();  // Set the size of the window based on the preferred sizes of what it contains.
		window.setResizable(false);  // Don't let the user resize the window.
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);  // End the program if the user closes the window.
		Dimension screen = Toolkit.getDefaultToolkit().getScreenSize();  // The width/height of the screen.
		window.setLocation( (screen.width - window.getWidth())/2, 
				(screen.height - window.getHeight())/2 );  // Position window in the center of screen.
		window.setVisible(true);  // Make the window visible on the screen.
	}

} // end class Hangman
