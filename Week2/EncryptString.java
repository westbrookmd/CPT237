///////////////////////////////////////////
//
// CPT 236
//
// Assignment 10
//
// Author: Kim Cannon
//
// Date: 2/2/16
//
///////////////////////////////////////////

// code for testing
import java.util.Scanner;


public class EncryptString extends AnyString{

    // constructor
    public EncryptString(String str) {

        super(str);
    }


    // constructor
    public EncryptString(char[] charArray) {

        super(charArray);
    }


    // object/instance encryption method
    public String encryptString() {

        String resultString = "";
        String lowerString = str.toLowerCase();

        for(int i = 0; i < lowerString.length(); i++) {

            resultString += (char)(lowerString.charAt(i) + 1);
        }

        return resultString;
    }


    // static/class encryption method
    public static String encryptString(String str) {

        String resultString = "";
        String lowerString = str.toLowerCase();

        for(int i = 0; i < lowerString.length(); i++) {

            resultString += (char)(lowerString.charAt(i) + 1);
        }

        return resultString;
    }


    // static/class decryption method
    public static String decryptString(String str) {

        String resultString = "";
        String lowerString = str.toLowerCase();

        for(int i = 0; i < lowerString.length(); i++) {

            resultString += (char)(lowerString.charAt(i) - 1);
        }

        return resultString;
    }


    // main method to test class code
    public static void main(String[] args) {

        // input a String
        Scanner input = new Scanner(System.in);
        System.out.print("Enter a string: ");
        String in = input.nextLine();

        // create an object
        EncryptString obj = new EncryptString(in);

        //output object string
        System.out.println();
        System.out.println("***** STRING OBJECT OUTPUT *****");
        System.out.println();
        System.out.println("String input: " + in);
        System.out.println("Object string: " + obj.getString());

        // output encrypted string
        String encrypt = obj.encryptString();
        System.out.println("Encrypted object method:" + encrypt);

        // output decrypted string
        String decrypt = EncryptString.decryptString(encrypt);
        System.out.println("Decrypted class method:" + decrypt);

        // output encrypted string
        encrypt = EncryptString.encryptString(in);
        System.out.println("Encrypted class method:" + encrypt);

        // output decrypted string
        decrypt = EncryptString.decryptString(encrypt);
        System.out.println("Decrypted class method:" + decrypt);


        // create an object using character array
        char[] charArray = {'a', 'b', 'c'};
        obj = new EncryptString(charArray);

        //output object string
        System.out.println();
        System.out.println("***** ARRAY OBJECT OUTPUT *****");
        System.out.println();
        System.out.println("Object string: " + obj.getString());

        // output encrypted string
        encrypt = obj.encryptString();
        System.out.println("Encrypted object method:" + encrypt);

        // output decrypted string
        decrypt = EncryptString.decryptString(encrypt);
        System.out.println("Decrypted class method:" + decrypt);

        // output encrypted string
        encrypt = EncryptString.encryptString(in);
        System.out.println("Encrypted class method:" + encrypt);

        // output decrypted string
        decrypt = EncryptString.decryptString(encrypt);
        System.out.println("Decrypted class method:" + decrypt);
    }
}