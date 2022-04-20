//*********************************************
// CPT237-S34 Advanced Java Programming
// Assignment 8
// Author: Marshall Westbrook
// Date: 4/19/22
//
// A class that implements the serializable interface
// Includes a test method that demonstrates the built-in methods
// for reading and writing objects via input/output streams
// Converts the compareto() integer values to a more readable string
//*********************************************

import java.io.*;
import java.sql.Array;
import java.util.ArrayList;

public class Boat implements Cloneable, Comparable<Boat>, Serializable
{
    private String Name = "Original Boat Name";
    private double Cost = 62000;
    private int Weight = 3100;

    public String getName()
    {
        return Name;
    }
    public void setName(String newName)
    {
        Name = newName;
    }
    public double getCost()
    {
        return Cost;
    }
    public void setCost(double newCost)
    {
        Cost = newCost;
    }
    public int getWeight()
    {
        return Weight;
    }
    public void setWeight(int newWeight)
    {
        Weight = newWeight;
    }

    // Overriding compareTo() method
    // Adds/Subtracts the differences and returns an integer
    // "The compareTo() method should compare the String, double and integer object variables of the classes being compared to determine if the two objects are equal, less than or greater than each other."
    // Due to the directions requiring this to all be in one compareTo() method, this can lead to inaccurate results. Differences in name, cost, and weight can offset and result in 0.
    @Override
    public int compareTo(Boat o) {
        int value = 0;
        if(o.getName().toLowerCase().charAt(0) > this.getName().toLowerCase().charAt(0))
        {
            value += 1;
        }
        else if(o.getName().toLowerCase().charAt(0) < this.getName().toLowerCase().charAt(0))
        {
            value -= 1;
        }
        if(o.getCost() > this.getCost())
        {
            value += 1;
        }
        else if(o.getCost() < this.getCost())
        {
            value -= 1;
        }
        if(o.getWeight() > this.getWeight())
        {
            value += 1;
        }
        else if(o.getWeight() < this.getWeight())
        {
            value -= 1;
        }
        return value;
    }
    // Overriding clone() method
    // by verifying Strings are different objects (they always are since Strings are immutable)
    // If we were cloning dates, we could call clone on each object to verify this
    @Override
    public Boat clone() {
        try
        {
            Boat clonedBoat = (Boat)super.clone();
            //https://stackoverflow.com/questions/10607990/how-should-i-copy-strings-in-java
            //clonedBoat.Name = (String)(Name.clone()); //clone has protected access in java.lang.Object
            clonedBoat.setName(Name); //Strings are immutable in java so this is guaranteed to not be changed
            return clonedBoat;
        }
        catch (CloneNotSupportedException e)
        {
            return null;
        }

    }
    public static void main(String[] args) {

        //Writing the boats to a file
        File file = new File("SerializableTestFile_MarshallWestbrook.boatobject");
        ArrayList<Boat> writeBoats = new ArrayList<Boat>();
        try (ObjectOutputStream outputStream = new ObjectOutputStream(new FileOutputStream(file));)
        {
            System.out.println("Writing boat objects to " + file.getAbsolutePath());
            final int repeat = 26;
            for(int i = 0; i < repeat; i++)
            {
                Boat serializableBoat = new Boat();
                serializableBoat.setName(String.valueOf((char)(('A' + i))));
                serializableBoat.setCost(30000 * i);
                serializableBoat.setWeight(4000 * i + 20);
                outputStream.writeObject(serializableBoat);
                //Keeping track of our boats created to compare them later
                writeBoats.add(serializableBoat);
            }

        }
        catch(Exception ex)
        {
            System.out.println(ex.getMessage());
        }

        //Reading the boats from a file
        try (ObjectInputStream inputStream = new ObjectInputStream(new FileInputStream(file));)
        {
            System.out.println("-------Serializable Boats Reading to ArrayList-----");
            final int repeat = 26;
            ArrayList<Boat> readBoats = new ArrayList<>();
            for(int i = 0; i < repeat; i++)
            {
                Boat serializableBoat = (Boat) inputStream.readObject();
                readBoats.add(serializableBoat);
            }
            System.out.printf("%-10s %-10s %-10s %-10s%n", "Name",  "Weight", "Cost", "CompareTo");
            for(Boat b : readBoats)
            {
                int compareValue = b.compareTo(readBoats.get(readBoats.indexOf(b)));
                String compareDisplayValue = "";
                if(compareValue == 0)
                {
                    compareDisplayValue = "True";
                }
                else
                {
                    compareDisplayValue = "False";
                }
                System.out.printf("%-10s %-10s %-10s %-10s%n", b.getName(),  b.getWeight(), b.getCost(), compareDisplayValue);
            }

        }
        catch(Exception ex)
        {
            System.out.println(ex.getMessage());
        }
        System.out.println("--------Serializable Boats End---------");
    }

}
