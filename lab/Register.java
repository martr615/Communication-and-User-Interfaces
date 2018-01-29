/**
 * @(#)Register.java
 *
 *
 * @author
 * @version 1.00 2013/3/1
 */

import java.io.*;
import java.util.*;
import javax.swing.*;


public class Register
{
	//skapa huvudvektorn
	private Vector<Person> vec;

	public Register()
	{
		vec = new Vector<Person>();
	}
	//lägga alla namn och nummer i vektorn.

		// öppna infil

		//läser in alla namn och nummer till vektorn freån filen reg.txt
		public void openRegister(JTextField message) throws IOException{

			BufferedReader infile = new BufferedReader
	                               (new FileReader("reg.txt"));
	        String line, fiName, laName, theNumber;

	        StringTokenizer tokens;

			while ((line = infile.readLine()) != null)
	        {
				tokens = new StringTokenizer(line, " ");


	        while(tokens.hasMoreTokens())
	        {
	        	laName = tokens.nextToken();
				fiName = tokens.nextToken();
				theNumber = tokens.nextToken();
				vec.add(new Person(laName, fiName, theNumber)); //skickas till Person variabelsättningen.
	        }
	        }
	        infile.close();

			message.setText("Registret har öppnats");


		}




	//Om Programmet-knapp
	public void about(){
		    String utdata; // utdata
    		utdata = "Developed by Adam Tellia, Martin Tran, Jinwoo The Reef Yu";
    		JOptionPane.showMessageDialog(null, utdata);

	}


	public void sokNummer(JTextField name1, JTextField name2, JTextField number, JTextField message)
	{
		String firstName = name1.getText();
		String lastName = name2.getText();
		name1.setEditable(false);
		name2.setEditable(false);
		number.setEditable(false);

		for(int i = 0; i < vec.size(); i++)
		{
			if(firstName.compareTo(vec.get(i).getFirstname()) == 0 && lastName.compareTo(vec.get(i).getSurname()) == 0)
			{
				number.setText(vec.get(i).getNumber());
				message.setText("Numret har hittats");
				break;
			}

			else
			{
				message.setText("Numret finns ej");
			}
	}
	}

	public void sokNamn(JTextField name1, JTextField name2, JTextField number, JTextField message)
	{
		String firstName = name1.getText();
		String lastName = name2.getText();
		String telephone = number.getText();
		name1.setEditable(false);
		name2.setEditable(false);
		number.setEditable(false);
		for(int i = 0; i < vec.size(); i++)
		{
		if(telephone.equals(vec.get(i).getNumber()))
		{
			name1.setText(vec.get(i).getFirstname());
			name2.setText(vec.get(i).getSurname());
			message.setText("Personen har hittats");
			break;
		}

		else
		{
			message.setText("Personen kan inte hittas");
		}
		}
	}
	public void rensa(JTextField name1, JTextField name2, JTextField number, JTextField message)
	{
		name1.setText("");
		name2.setText("");
		number.setText("");
		message.setText("");
		name1.setEditable(true);
		name2.setEditable(true);
		number.setEditable(true);


	}

	public void saveToFile(JTextField message) throws IOException
	{
		File f = new File("regge.txt");
		PrintWriter fileOut = new PrintWriter(new FileWriter(f));
		String word1, word2, number;
		for(int i = 0; i < vec.size(); i++)
			{
				word1 = vec.get(i).getSurname();
				word2 = vec.get(i).getFirstname();
				number = vec.get(i).getNumber();
				fileOut.format("%-16s%-16s%-16s\r\n", word1, word2, number);
			}
		fileOut.close();
		word1 = "";
		word2 = "";
		number = "";
		message.setText("Filen har sparats");
	}

	public void erasePost(JTextField name1, JTextField name2, JTextField number, JTextField message)
	{
		String firstName = name1.getText();
		String lastName = name2.getText();
		String telephone = number.getText();

		for(int i = 0; i < vec.size(); i++)
		{
			if(telephone.equals(vec.get(i).getNumber()) && firstName.compareTo(vec.get(i).getFirstname()) == 0 && lastName.compareTo(vec.get(i).getSurname()) == 0)
			{
				vec.remove(i);
				message.setText("Personen är raderad ur registret");
				break;
			}

			else
			{
				message.setText("Kan ej hitta numret eller/och personen i registret");
			}


		}


	}

	public void setPost(JTextField name1, JTextField name2, JTextField number, JTextField message)
	{
		String firstName = name1.getText();
		String lastName = name2.getText();
		String telephone = number.getText();
		String tNumber;
		String namn1;
		String namn2;
		
		if(firstName.length() == 0 || lastName.length() == 0 || telephone.length() == 0)
		{
			message.setText("Snälla fyll i resterande fält");
		}
		
		else
		{
		for(int i = 0; i < vec.size(); i++)
		{
			namn1 = vec.get(i).getFirstname();
			namn2 = vec.get(i).getSurname();
			tNumber = vec.get(i).getNumber();
			
			
		
			if(telephone.equals(tNumber))
			{
				message.setText("Numret existerar redan");
				break;
			}
			
			else if(firstName.compareTo(namn1) == 0 && lastName.compareTo(namn2) != 0)
			{
				message.setText("Personen finns redan");
				break;
			}
			else
			{
				vec.add(new Person(lastName, firstName, telephone));
				Collections.sort(vec);
				message.setText("Personen har lagts till");
				break;
			}
		}
	}

	}







}

