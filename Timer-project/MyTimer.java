import java.awt.event.*;
import java.awt.*;
import javax.swing.*;
import java.net.URL;

//libraries for images (unused)
//import javax.imageio.*;
//import java.io.*;
//import java.awt.image.BufferedImage;

//libraries for limitation (unused)
//functionality problem
//import javax.swing.text.AbstractDocument;
//import javax.swing.text.AttributeSet;
//import javax.swing.text.BadLocationException;
//import javax.swing.text.DocumentFilter;

public class MyTimer extends JFrame implements ActionListener {

	//Variables
	int counter, hours, minutes, seconds;

	//the actuall timer function
	Timer timer;

	//a boolean used to check timer state,
	//used for setting the state of the startPause button
	private boolean timerRunning = false;
	private boolean resizable = false;

	private JPanel pnl, pnl1, pnl2, totalGui;

	//empty panels used for the layout
	private JPanel pnl4 = new JPanel();
	private JPanel pnl5 = new JPanel();

	private JMenuBar mnb;

	private JMenu mnuFile, mnuEdit, mnuHelp, mnuTheme;
	private JMenuItem mniAbout, mniExit, mniTheme, mniStart;
	private JCheckBoxMenuItem mniEditable, chbWhite, chbBlack;

	private JTextField tf, tf1, tf2;
	private JLabel colonLabel1, colonLabel2;
	private JButton startPauseButton, stopButton;

	public MyTimer() throws Exception {

		//a panel containing the textfields showing the timer
		pnl = new JPanel();
		pnl.setLayout(new BoxLayout(pnl, BoxLayout.LINE_AXIS));

		//a panel containing the buttons
		pnl1 = new JPanel();
		pnl1.setLayout(new GridLayout(1, 2));

		//Import font
		URL fontUrl = new URL("http://www.webpagepublicity.com/" + "free-fonts/d/Digital%20Readout%20Thick%20Upright.ttf");
		Font font = Font.createFont(Font.TRUETYPE_FONT, fontUrl.openStream());

		//Font typ and size
		font = font.deriveFont(Font.PLAIN,50);
		GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
		ge.registerFont(font);

		//The input textfield for hours
		tf2 = new JTextField("00");
		tf2.setForeground(Color.BLACK);
		tf2.setBorder(javax.swing.BorderFactory.createEmptyBorder());
		tf2.setFont(font);
		tf2.setHorizontalAlignment(JTextField.CENTER);
		pnl.add(tf2);

		//a label with a colon seperating the textfields
		colonLabel1 = new JLabel(":");
		colonLabel1.setFont(font);
		colonLabel1.setForeground(Color.BLACK);
		colonLabel1.setHorizontalAlignment(JTextField.CENTER);
		pnl.add(colonLabel1);

		//The input textfield for minutes
		tf1 = new JTextField("00");
		tf1.setForeground(Color.BLACK);
		tf1.setBorder(javax.swing.BorderFactory.createEmptyBorder());
		tf1.setHorizontalAlignment(JTextField.CENTER);
		tf1.setFont(font);
		pnl.add(tf1);

		//A colon seperating the textfields
		colonLabel2 = new JLabel(":");
		colonLabel2.setFont(font);
		colonLabel2.setForeground(Color.BLACK);
		colonLabel2.setHorizontalAlignment(JTextField.CENTER);
		pnl.add(colonLabel2);

		//The input textfield for seconds
		tf = new JTextField("00");
		tf.setForeground(Color.BLACK);
		tf.setBorder(javax.swing.BorderFactory.createEmptyBorder());
		tf.setFont(font);
		tf.setHorizontalAlignment(JTextField.CENTER);
		pnl.add(tf);

		//limit the input (check functions)
		//PROBLEM: timer wont work, blue marked wont erase
		//AbstractDocument d1 = (AbstractDocument) tf.getDocument();
		//AbstractDocument d2 = (AbstractDocument) tf1.getDocument();
		//AbstractDocument d3 = (AbstractDocument) tf2.getDocument();
		//d1.setDocumentFilter(new MyTimer.TextFieldListener());
		//d2.setDocumentFilter(new MyTimer.TextFieldListener());
		//d3.setDocumentFilter(new MyTimer.TextFieldListener());

		//Add a menubar and menubar items
		mnb = new JMenuBar();

		mniAbout = new JMenuItem("About...");
		mniAbout.addActionListener(this);

		mniEditable = new JCheckBoxMenuItem("Resizable window");
		mniEditable.addActionListener(this);

		mniTheme = new JMenuItem("Theme");

		mniStart = new JMenuItem("Start");
		mniStart.addActionListener(this);
		mniStart.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_ENTER, KeyEvent.KEY_LOCATION_UNKNOWN ));

		mniExit = new JMenuItem("Exit");
		mniExit.addActionListener(this);
		mniExit.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, KeyEvent.KEY_LOCATION_UNKNOWN ));


		mnuFile = new JMenu("File");
		mnuFile.add(mniStart);
		mnuFile.add(mniExit);

		chbWhite = new JCheckBoxMenuItem("White theme");
		chbWhite.addActionListener(this);
		chbWhite.setSelected(true);
		chbWhite.setEnabled(false);

		chbBlack = new JCheckBoxMenuItem("Black theme");
		chbBlack.addActionListener(this);

		mnuTheme = new JMenu("Theme");
		mnuTheme.add(chbBlack);
		mnuTheme.add(chbWhite);

		mnuEdit = new JMenu("Edit");
		mnuEdit.add(mnuTheme);
		mnuEdit.addSeparator();
		mnuEdit.add(mniEditable);

		mnuHelp = new JMenu("Help");
		mnuHelp.add(mniAbout);

		setJMenuBar(mnb);
		mnb.add(mnuFile);
        mnb.add(mnuEdit);
        mnb.add(mnuHelp);

		//start button
		startPauseButton = new JButton("Start");
		startPauseButton.setBackground(Color.GREEN);
		startPauseButton.setForeground(Color.BLACK);
		startPauseButton.setFocusPainted(false);
		startPauseButton.addActionListener(this);
		pnl1.add(startPauseButton);

		//stop button
		stopButton = new JButton("Stop");
		stopButton.setEnabled(false);
		stopButton.setBackground(Color.RED);
		stopButton.setForeground(Color.BLACK);
		stopButton.addActionListener(this);

		//focus listener to mark text quickly in textfields
		tf.addFocusListener(new java.awt.event.FocusAdapter() {
    	    public void focusGained(java.awt.event.FocusEvent evt) {
    	    	SwingUtilities.invokeLater( new Runnable() {
    				@Override
    				public void run() {
    					tf.selectAll();
    				}
    			});
    	    }
    	});
		tf1.addFocusListener(new java.awt.event.FocusAdapter() {
    	    public void focusGained(java.awt.event.FocusEvent evt) {
    	    	SwingUtilities.invokeLater( new Runnable() {
    				@Override
    				public void run() {
    					tf1.selectAll();
    				}
    			});
    	    }
    	});
		tf2.addFocusListener(new java.awt.event.FocusAdapter() {
    	    public void focusGained(java.awt.event.FocusEvent evt) {
    	    	SwingUtilities.invokeLater( new Runnable() {
    				@Override
    				public void run() {
    					tf2.selectAll();
    				}
    			});
    	    }
    	});

		//Add hotkeys to buttons
		//startPauseButton.setMnemonic(KeyEvent.VK_ENTER);


		//Set Boxlayout
		Container c = getContentPane();

		//Add a background panel (wrapper)
		totalGui = new JPanel() ;
		totalGui.setLayout(new BoxLayout(totalGui, BoxLayout.Y_AXIS));

		//Set the width of the program and let the elements decide the height
		totalGui.setSize(250, totalGui.getHeight());

		//set the element of buttons and timer
        pnl.setMaximumSize(new Dimension(150, 70));
        pnl1.setPreferredSize(new Dimension(totalGui.getWidth(), 50));

       	//set size of panel separators (space panels)
        pnl4.setPreferredSize(new Dimension(totalGui.getWidth(), 100));
        pnl5.setPreferredSize(new Dimension(totalGui.getWidth(), 100));

        //add panels to the window
        c.add(totalGui);

        totalGui.add(pnl4);
		totalGui.add(pnl);
		totalGui.add(pnl5);
		totalGui.add(pnl1);

		totalGui.setBackground(Color.WHITE);

		//Make the elements transparent for easier background settings
		tf.setOpaque(false);
		tf1.setOpaque(false);
		tf2.setOpaque(false);
		colonLabel1.setOpaque(false);
		colonLabel2.setOpaque(false);
		pnl.setOpaque(false);
		pnl1.setOpaque(false);
		pnl4.setOpaque(false);
		pnl5.setOpaque(false);

		//Title and icon
		setTitle("Timer");
		ImageIcon iicon = new ImageIcon("C:/Users/Adam/Downloads/KomaProjekt/hourglass.png");
		setIconImage(iicon.getImage());

		//some general commands for the gui
		pack();
        setVisible(true);
        setResizable(resizable);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
	}

	//Action Handler
	public void actionPerformed(ActionEvent e) {

		//the startPauseButton starts the timer
		//if timer is running it funtions as a pause button
		if(e.getSource() == startPauseButton || e.getSource() == mniStart) {

			if(!timerRunning)
			{
				if(	isInteger(tf.getText()) &&
				isInteger(tf1.getText()) &&
				isInteger(tf2.getText())) {

					//check that time isn't 0
					if(	(int)(Double.parseDouble(tf.getText()))  == 0 &&
						(int)(Double.parseDouble(tf1.getText())) == 0 &&
						(int)(Double.parseDouble(tf2.getText())) == 0) {
						return;
					}

					startPauseButton.setText("Pause");
					startPauseButton.setBackground(Color.YELLOW);
					timerRunning = true;
					stopButton.setEnabled(true);

					mniStart.setText("Pause");

					pnl1.add(stopButton);

					tf.setEditable(false);
					tf1.setEditable(false);
					tf2.setEditable(false);

					timerRunning = true;

					int count = 0;

					//read input and add to counter. If minutes or seconds are larger than 59
					//set respective to 59

					//seconds (read from tf)
					if((int)(Double.parseDouble(tf.getText())) < 60)
						count += (int)(Double.parseDouble(tf.getText()));
					else
						count += 59;
					//mminutes (read from tf1)
					if((int)(Double.parseDouble(tf1.getText())) < 60)
						count += (int)(Double.parseDouble(tf1.getText())) * 60;
					else
						count += 59 * 60;
					//hours (read from tf2)
					count += (int)(Double.parseDouble(tf2.getText())) * 3600;

					//calculate hours minutes and seconds
					hours = count/3600;
					minutes = (count/60)%60;
					seconds = count%60;

					//display initial time
					timeDisplay(hours, minutes, seconds);

					TimeClass tc = new TimeClass(count);
					timer = new Timer(1000, tc);
					timer.start();
				}
				//if the input doesn't match the type requirement, the function will not run
				else {
					return; //timerLabel.setText("invalid input");
				}
			}
			else
			{
				startPauseButton.setText("Resume");
				mniStart.setText("Resume");
				startPauseButton.setBackground(Color.GREEN);
				timerRunning = false;
				timer.stop();
			}
		}

		//The stopButton resets the timer
		else if(e.getSource() == stopButton) {

			startPauseButton.setText("Start");
			mniStart.setText("Start");
			startPauseButton.setBackground(Color.GREEN);
			timerRunning = false;

			tf.setEditable(true);
			tf1.setEditable(true);
			tf2.setEditable(true);

			timeDisplay(0, 0, 0);

			timer.stop();

			pnl1.remove(stopButton);
		}

		//Menu Items
		else if(e.getSource() == mniAbout)
		{
			 JOptionPane.showMessageDialog(null,
			 	"This timer was made for the project in \n the course TNM040, 'Kommunikation och \n användargränssnitt', by Adam Tellia, Jinwoo Yu,\n Martin Tran & Tobias Pihl."
			 );
		}
		else if(e.getSource() == mniExit)
		{
			 System.exit(0);
		}
		else if(e.getSource() == mniEditable)
		{
			if(resizable)
			{
				resizable = false;
				setResizable(resizable);
			}
			else
			{
				resizable = true;
				setResizable(resizable);
			}
		}
		else if(e.getSource() == chbWhite)
		{
			chbBlack.setSelected(false);
			chbBlack.setEnabled(true);
			chbWhite.setEnabled(false);
			totalGui.setBackground(Color.WHITE);

			tf.setForeground(Color.BLACK);
			tf1.setForeground(Color.BLACK);
			tf2.setForeground(Color.BLACK);
			colonLabel1.setForeground(Color.BLACK);
			colonLabel2.setForeground(Color.BLACK);
		}
		else if(e.getSource() == chbBlack)
		{
			chbWhite.setSelected(false);
			chbWhite.setEnabled(true);
			chbBlack.setEnabled(false);
			totalGui.setBackground(Color.BLACK);

			tf.setForeground(Color.WHITE);
			tf1.setForeground(Color.WHITE);
			tf2.setForeground(Color.WHITE);
			colonLabel1.setForeground(Color.WHITE);
			colonLabel2.setForeground(Color.WHITE);
		}
	}

	//This function checks if your input is of the required type (integer)
	//and returns false if it isn't
	public boolean isInteger(String s) {
		try {
			Integer.parseInt(s);
		}
		catch(NumberFormatException e) {
			return false;
		}
		return true;
	}

	//Limit the input in the textfields (unused function)
	//PROBLEM: timer wont work, blue marked wont erase
	/*private class TextFieldListener extends DocumentFilter
	{
		final int charLimit = 2;

		@Override
		public void insertString(DocumentFilter.FilterBypass fb, int offset, String string, AttributeSet attr) throws BadLocationException
		{
			if(fb.getDocument().getLength()+string.length()>charLimit)
			{
				return;
			}
			fb.insertString(offset, string, attr);
		}

		@Override
		public void remove(DocumentFilter.FilterBypass fb, int offset, int length) throws BadLocationException
		{
			fb.remove(offset, length);
		}

		@Override
		public void replace(DocumentFilter.FilterBypass fb, int offset, int length, String text, AttributeSet attrs)throws BadLocationException
		{
			if(fb.getDocument().getLength()+text.length()>charLimit)
			{
				return;
			}
			fb.insertString(offset, text, attrs);
		}
	}*/

	//A function that displays time and adds zeroes if values are less than ten
	public void timeDisplay(int h, int m, int s) {
		if(h<10)
			tf2.setText("0" + h);
		else
			tf2.setText("" + h);
		if(m<10)
			tf1.setText("0" + m);
		else
			tf1.setText("" + m);
		if(s<10)
			tf.setText("0" + s);
		else
			tf.setText("" + s);
	}

	//The timer funtion
	public class TimeClass implements ActionListener {
		int counter;

		public TimeClass(int counter) {
			this.counter = counter;
		}

		//If the counter hasn't reached zero, keep counting
		//else indicate that the program is finnished
		public void actionPerformed(ActionEvent tc) {

			counter--;
			if(counter >= 0) {

				//get hours minutes and seconds
				hours = counter/3600;
				minutes = (counter/60)%60;
				seconds = counter%60;

				//Display the current time
				timeDisplay(hours, minutes, seconds);
			}
			else {
				timer.stop();
				timerRunning = false;
				startPauseButton.setText("Start");
				startPauseButton.setBackground(Color.GREEN);

				Toolkit.getDefaultToolkit().beep(); //play a beep

				//hide stopbutton
				pnl1.remove(stopButton);

				//let user edit the time
				tf.setEditable(true);
				tf1.setEditable(true);
				tf2.setEditable(true);
			}
		}
	}

	//our main function calling the GUI
	public static void main(String args[]) throws Exception {
		MyTimer gui = new MyTimer();
	}
}