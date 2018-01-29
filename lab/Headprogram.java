/**
 * @(#)jjjjjjj.java
 *
 *
 * @author
 * @version 1.00 2013/3/1
 */


import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.util.*;
import java.io.*;


public class Headprogram extends JFrame implements ActionListener
{
    // instansvariabler

    private JButton soknummer, soknamn, post, bortPost, om, rensa, oppna, spara;
    private JTextField surname, firstname, msg, number;
	private JPanel pnlText = new JPanel();
	private JPanel pnlEast = new JPanel();

	//NY KOD
	private JMenuBar mnb = new JMenuBar();  //////
	
	//menyer i toppfältet
	private JMenu mnuFil = new JMenu("Fil");  ///////
	private JMenu mnuSok = new JMenu("Sök");  ///////
	private JMenu mnuRedigera = new JMenu("Redigera");  ///////
	private JMenu mnuOm = new JMenu("Om");  ///////
	
	//dropdown menyer alternativ
    private JMenuItem mniOpen = new JMenuItem("Öppna ..."); ///
    private JMenuItem mniSave = new JMenuItem("Spara ..."); ///	
    private JMenuItem mniAvsluta = new JMenuItem("Avsluta ..."); ///
    
    private JMenuItem mniNr = new JMenuItem("Sök nummer ..."); ///
    private JMenuItem mniName = new JMenuItem("Sök namn..."); ///
        
	//VET EJ OM BEHÖVS
	//skapa ett Register objekt
	Register r1 = new Register();	
	
	//////////////////////////////DROPDWON MENY
	// private ShapeDrawer drawer = new ShapeDrawer();
	
	private JPopupMenu pmnEdit = new JPopupMenu("Edit"); 

    // konstruktor

    public Headprogram()
    {
    	//DROPDOWN
    	mniOpen.addActionListener(this);
        mniSave.addActionListener(this); 
        mniAvsluta.addActionListener(this);
        mniNr.addActionListener(this);
        mniName.addActionListener(this);
        
        
        setJMenuBar(mnb); //
        
        //Toppmenyer
        mnb.add(mnuFil);
        mnb.add(mnuSok);
        mnb.add(mnuRedigera);
        mnb.add(mnuOm);	

		//Dropdown meny alternativ för FIL
        mnuFil.add(mniOpen);
        mnuFil.add(mniSave);
        mnuFil.add(mniAvsluta);
        //Dropdown meny alternativ för SÖK
        mnuSok.add(mniNr);
        mnuSok.add(mniName);
       

				//ÄNDRA BARA ALLA DROPDOWN MENY ALTERNATIV HÄR        
		        /*
		        //Dropdown meny alternativ för SÖK
		        mnuFil.add(mniOpen);
		        mnuFil.add(mniSave);
		        mnuFil.add(mniAvsluta);
		        
		        //Dropdown meny alternativ för EDIT
		        mnuFil.add(mniOpen);
		        mnuFil.add(mniSave);
		        mnuFil.add(mniAvsluta);
		        
		        //Dropdown meny alternativ för OM
		        mnuFil.add(mniOpen);
		        mnuFil.add(mniSave);
		        mnuFil.add(mniAvsluta);*/
        
        //TOPPMENYN FIL
        FileHandler fileHandler = new FileHandler();        
        mniOpen.addActionListener(fileHandler); //första menyn
        mniSave.addActionListener(fileHandler);
        mniAvsluta.addActionListener(fileHandler);
        
        
        EditHandler editHandler = new EditHandler();
		mniNr.addActionListener(editHandler);
		mniName.addActionListener(editHandler);
        
        
        
        

       	//ny kod
       	//Knapparna
       	soknummer =  new JButton("Sök Nummer");
       	soknummer.addActionListener(this);
       	soknamn =  new JButton("Sök Namn");
       	soknamn.addActionListener(this);
       	post =  new JButton("Sätt in post");
       	post.addActionListener(this);
       	bortPost =  new JButton("Ta bort post");
       	bortPost.addActionListener(this);
       	om =  new JButton("Om programmet");
       	om.addActionListener(this);
       	rensa =  new JButton("Rensa fält");
       	rensa.addActionListener(this);
       	oppna =  new JButton("Öppna register");
       	oppna.addActionListener(this);
       	spara =  new JButton("Spara register");
       	spara.addActionListener(this);

       	/*guess = new JTextField();
       	guess.addActionListener(this);
       	guess.setEditable(false);
       	txtNoGuesses = new JTextField();
       	txtNoGuesses.setEditable(false); //ändra i en textruta går ej*/

		//textfälten
		firstname = new JTextField();
       	firstname.addActionListener(this);
       	surname = new JTextField();
       	surname.addActionListener(this);
       	number = new JTextField();
       	number.addActionListener(this);
       	msg = new JTextField();
       	msg.addActionListener(this);
       	msg.setEditable(false); //meddelande txtrutan kan ej editas

        //textsidan
        pnlText.setLayout(new GridLayout(4,1));
        pnlText.add(new JLabel("Förnamn:", JLabel.CENTER));
        pnlText.add(firstname);
        pnlText.add(new JLabel("Efternamn:", JLabel.CENTER));
        pnlText.add(surname);
        pnlText.add(new JLabel("Telefonnr:", JLabel.CENTER));
        pnlText.add(number);
        pnlText.add(new JLabel("Meddelande:", JLabel.CENTER));
        pnlText.add(msg);


      	//knappsidan
        pnlEast.setLayout(new GridLayout(4, 2));  // default
        pnlEast.add(soknummer);
        pnlEast.add(soknamn);
       	pnlEast.add(post);
       	pnlEast.add(bortPost);
        pnlEast.add(om);
       	pnlEast.add(rensa);
       	pnlEast.add(oppna);
       	pnlEast.add(spara);

        Container c = getContentPane();

        c.setLayout(new BorderLayout());     // default
        c.add(pnlText, BorderLayout.CENTER);
        c.add(pnlEast, BorderLayout.EAST);

        setSize(500, 135);
        setTitle("Awesome shit");
        setVisible(true);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
    }
    
 
    public void actionPerformed(ActionEvent e)
    {
    	//About us knappen
    	if(e.getSource() == om){ // om man trycker på knappen om programmet så visas en dialog ruta med
    		r1.about();
    	}
    	//Öppna fil knappen
    	else if (e.getSource() == oppna)
		{
		//ursprungliga koden
       	/*start =  new JButton("Start");
       	start.addActionListener(this);*/

		try{
    		//All your IO Operations
    		r1.openRegister(msg);
		}catch(IOException ioe){
 		   //Handle exception here, most of the time you will just log it.
		}

	    }
	    else if (e.getSource() == soknummer)
	    {
			r1.sokNummer(firstname, surname, number, msg);
	    }
	    else if (e.getSource()== soknamn)
	    {
	    	r1.sokNamn(firstname, surname, number, msg);
	    }
	    else if (e.getSource() == rensa)
	    {
	    	r1.rensa(firstname, surname, number, msg);
	    }
	    else if (e.getSource() == spara ) //måste testa
	    {
		try{
    		//All your IO Operations
    		r1.saveToFile(msg);
		}catch(IOException ioe){
 		   //Handle exception here, most of the time you will just log it.
		}
	    }
	    else if (e.getSource() == bortPost)
	    {
	    	r1.erasePost(firstname, surname, number, msg);
	    }
	    else if (e.getSource() == post)
	    {
	    	r1.setPost(firstname, surname, number, msg);
	    }
	    
	    //LÄGG FILEHANDLER GREJEN HÄR
	    
			    //NYA VAL I MENU TEST BRE
			    /*else if (e.getSource() == mniOpen)
		        {
		            		try{
				    		//All your IO Operations
				    		r1.openRegister(msg);
							}catch(IOException ioe){
				 		   //Handle exception here, most of the time you will just log it.
							}

		        }
		        else if (e.getSource() == mniSave)
		        {
		            int test2= 2;
		        }
		        else if (e.getSource() == mniAvsluta)
		        {
		            int test3= 3;
		        }   */     

      }
      
     private class FileHandler implements ActionListener
    {
        public void actionPerformed(ActionEvent e)
        {
            if (e.getSource() == mniOpen)
            {
               				try{
				    		//All your IO Operations
				    		r1.openRegister(msg);
							}catch(IOException ioe){
				 		   //Handle exception here, most of the time you will just log it.
							}
            }
            else if (e.getSource() == mniSave)
            {
					try{
			    		//All your IO Operations
			    		r1.saveToFile(msg);
					}catch(IOException ioe){
			 		   //Handle exception here, most of the time you will just log it.
					}
            }
            else if (e.getSource() == mniAvsluta)
            {
                	System.exit(0);
            }
        }
    }
       
    private class EditHandler implements ActionListener
    {
        public void actionPerformed(ActionEvent e)
        {	
            if (e.getSource() == mniNr)
            {
				r1.sokNummer(firstname, surname, number, msg);
            }
            else if (e.getSource() == mniName)
            {
	    		r1.sokNamn(firstname, surname, number, msg);
            }    	        	
        }
    } 
    
    
    
    
    
    
    
    /////////NTYKOD FÖR POPUPMENT
    // innerklass för muslyssnare mha adapterklass ////////////////////////////////////////

    private class PopupHandler extends MouseAdapter
    {
        public void mousePressed(MouseEvent e)
        {
            System.out.println("mousePressed");

            if (e.isMetaDown())  // right mouse button pressed?
            {
                pmnEdit.show(e.getComponent(), e.getX(), e.getY());
            }
        }
    }
    /////////////////////////////////////////////////////////////////////////////////////


   


    public static void main(String[] args)
    {
    	Headprogram frame = new Headprogram();
    }
}