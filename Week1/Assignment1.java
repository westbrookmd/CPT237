//***************************************************
//
// Assignment 1 - Practicing exception handling
//
// Author: Marshall Westbrook
// Date: 1/18/2022
//
//***************************************************

import java.util.Scanner;

public class Assignment1 {

    public static void main(String[] args) {
        // looping condition
        boolean stop = false;
        while(!stop)
        {
            Scanner input = new Scanner(System.in);

            // store the integer for use later
            int userInteger = GetValidInteger(input);

            System.out.println("Integer input was: " + userInteger);

            // store the double for use later
            double userDouble = GetValidDouble(input);

            System.out.println("Double input was: " + userDouble);

            // perform division and output quotient
            Divide(userInteger, userDouble);

            // Prompt the user to see whether they want to stop the application
            System.out.print("Input \"stop\" to stop: ");
            // look for if the user entered stop
            stop = input.hasNext("stop");
            // basic formatting in-between loops
            System.out.println("-------------------------------");
        }
        System.out.println("Thanks for using my application.");
    }

    /**
     * summary: Prompts the user in the console for a valid integer input and returns the input once validated
     * @return int
     */
    private static int GetValidInteger(Scanner input) {
        try{
            System.out.print("Input integer value: ");
            return input.nextInt();
        }
        // if any exception happens, notify the user, disregard the input, and prompt the user again
        catch (Exception ex)
        {
            System.out.println("Please enter a valid integer.");
            input.nextLine();
            // call the method again and give the user another chance to input correctly
            return GetValidInteger(input);
        }
    }

    /**
     * summary: Prompts the user in the console for a valid double input and returns the input once validated
     * @return double
     */
    private static double GetValidDouble(Scanner input) {
        try{
            System.out.print("Input double value: ");
            return input.nextDouble();
        }
        // if any exception happens, notify the user, disregard the input, and prompt the user again
        catch (Exception ex)
        {
            System.out.println("Please enter a valid double.");
            input.nextLine();
            // call the method again and give the user another chance to input correctly
            return GetValidDouble(input);
        }
    }

    /**
     * summary: Accepts an int and double, divides them, and outputs the quotient in the console.
     *          Will exit application if division by zero is attempted.
     */
    private static void Divide(int userInteger, double userDouble) {
        try{
            double answer = userInteger / userDouble;
            if(Double.isInfinite(answer))
            {
                throw new ArithmeticException();
            }
            System.out.println(userInteger + " divided by " + userDouble + " is " + answer);
        }
        // handle the division by zero exception in a special message
        catch (ArithmeticException ex)
        {
            System.out.println("Unable to divide by zero.");
            System.exit(1);
        }
        // catch-all generic exceptions still result in the application closing
        catch (Exception ex)
        {
            System.out.println("Unable to divide. " + ex.getMessage());
            System.exit(1);
        }
    }

}