/////////////////////////////////////
//
// CPT 236
//
// Assignment 10 Solution
//
// Author: Kim Cannon
//
// Date: 1/26/16
//
/////////////////////////////////////


// for test purposes
//import java.util.Scanner;


public class AnyString {

    // declare object variables
    protected String str;


    // constructor - initialize object variable
    public AnyString(String s) {

        str = s;
    }

    // constructor - initialize object variable
    public AnyString(char[] chArray) {

        str = new String(chArray);
    }


    // return the object variable
    public String getString() {

        return str;
    }


    // return lowercase version of object varaible
    public String lowercase() {

        return str.toLowerCase();
    }


    // return uppercase version of the object variable
    public String uppercase() {

        return str.toUpperCase();
    }


    // return the length of the object variable
    public int getLength() {

        return str.length();
    }


    // return the object variable
    public String toString() {

        return str;
    }


    // determine if two AnyString objects are equal
    public boolean equals(AnyString obj) {

        return str.equals(obj.getString());
    }


    // determine if object variable is all letters
    public boolean isLetters() {

        boolean letters = true;

        for(int i = 0; i < str.length(); i++) {

            if(!Character.isLetter(str.charAt(i))) {

                letters = false;
                break;
            }
        }

        return letters;
    }


    // determine if object variable is all letters
    public boolean isNumeric() {

        boolean digits = true;

        for(int i = 0; i < str.length(); i++) {

            if(!Character.isDigit(str.charAt(i))) {

                digits = false;
                break;
            }
        }

        return digits;
    }

/*
   // main method to test class code
   public static void main(String[] args) {

      // get string from keyboard
      Scanner input = new Scanner(System.in);
      System.out.print("Enter a string: ");
      String in = input.nextLine();

      // create object using the string
      AnyString aString = new AnyString(in);

      // output the results from each method call
      System.out.println();
      System.out.println("***** OUTPUT - STRING *****");
      System.out.println("String input: " + in);
      System.out.println("String from: " + aString.getString());
      System.out.println("String lowercase from: " + aString.lowercase());
      System.out.println("String uppercase from: " + aString.uppercase());
      System.out.println("String toString: " + aString.toString());
      System.out.println("String isLetters: " + aString.isLetters());
      System.out.println("String isNumeric: " + aString.isNumeric());

      char[] charArray = {'a', 'b', 'c'};
      AnyString aString2 = new AnyString(charArray);
      System.out.println("***** OUTPUT - CHAR ARRAY *****");
      System.out.println("String from: " + aString2.getString());
      System.out.println("String lowercase from: " + aString2.lowercase());
      System.out.println("String uppercase from: " + aString2.uppercase());
      System.out.println("String toString: " + aString2.toString());
      System.out.println("String isLetters: " + aString2.isLetters());
      System.out.println("String isNumeric: " + aString2.isNumeric());

      System.out.println("***** TEST EQUALS *****");
      System.out.println("Strings equal: " + aString.equals(aString2));
   }
*/
}