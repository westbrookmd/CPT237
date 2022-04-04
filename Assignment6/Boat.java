//*********************************************
// CPT237-S34 Advanced Java Programming
// Assignment 6
// Author: Marshall Westbrook
// Date: 4/3/22
//
// A class that implements the cloneable and comparable interfaces
// Boat hides the access/editing/implementation of variables behind methods
// Note: The compareto() method has limited functionality due to the requirements of the assignment
// I wanted to add a randomized way to display this, but it would've required the import for Random
// I created a simple loop to display the different variations of comparing Boats
//*********************************************

public class Boat implements Cloneable, Comparable<Boat>
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
        Boat firstBoat = new Boat();
        firstBoat.setName("Little");
        firstBoat.setCost(10.20);
        firstBoat.setWeight(200);
        Boat secondBoat = (Boat)firstBoat.clone();
        Boat thirdBoat = (Boat)firstBoat.clone();
        thirdBoat.setName("BIG BOAT");
        thirdBoat.setCost(1000000);
        thirdBoat.setWeight(87210);
        System.out.println("Identical boats compare as: " + firstBoat.compareTo(secondBoat));
        System.out.println("Different boats compare as: " + firstBoat.compareTo(thirdBoat));

        System.out.println("-------Semi-Random Boats Start--------");
        final int repeat = 10;
        for(int i = 0; i < repeat; i++)
        {
            Boat firstRepeatingBoat = new Boat();
            firstRepeatingBoat.setName(String.valueOf(('A' + i)));
            firstRepeatingBoat.setCost(30000 * i);
            firstRepeatingBoat.setWeight(4000 * i + 20);
            Boat secondRepeatingBoat = new Boat();
            System.out.println("Variation " + (i+1) + " compare as: " + firstRepeatingBoat.compareTo(secondRepeatingBoat));
        }
        System.out.println("--------Semi-Random Boats End---------");
    }

}
