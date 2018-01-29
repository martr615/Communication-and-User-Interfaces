/**
 * @(#)Person.java
 *
 *
 * @author
 * @version 1.00 2013/3/1
 */


import java.io.*;
import java.util.*;

public class Person implements Comparable<Person> //vet inte om rätt än haha
{
	    //instansvariabler
    private String firstname, surname;
    private String number;


    public Person(String theSurname, String theFirstname, String theNumber)
    {
    		surname = theSurname;
    		firstname = theFirstname;
    		number = theNumber;
    }

    public String getFirstname()
    {
    	return firstname;
    }

  	public String getSurname()
    {
    	return surname;
    }

	public String getNumber()
	{
		return number;
	}


	public int compareTo(Person e)
	{
		if(surname.compareTo(e.surname) != 0)
		{
		return surname.compareTo(e.surname);
		}
		else
		{
		return firstname.compareTo(e.firstname);
		}
	}


	//NUMBER
	public boolean equals(Person e)
	{
		return number.equals(e.number);
	}























    }


