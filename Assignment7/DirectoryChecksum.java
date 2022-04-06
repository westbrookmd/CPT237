//*********************************************
// CPT237-S34 Advanced Java Programming
// Assignment 7
// Author: Marshall Westbrook
// Date: 4/5/22
//
// Recursive Directory Search method
//     from: http://stackoverflow.com/questions/2534632/list-all-files-from-a-directory-recursively-with-java
//
// A class that uses recursion to calculate checksums for each directory
// Warning: Can be inaccurate when files are being accessed by other applications
// TODO: add a way to properly format subdirectories to be be multiple tabs to the right
// This may be a good usecase for creating an inner-class and storing multiple values. The urge to use tuples is a bad sign.
//
//*********************************************
import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;

public class DirectoryChecksum {

   public static void main(String[] args) {
      String directory = "C:\\C#";
      System.out.println("-----Recursively searching directories-----");
      int checkSum = printFnames(directory);
      System.out.println("------------------Done---------------------");
   }

   public static int printFnames(String sDir) {
      System.out.println("Directory: " + sDir);
      File[] faFiles = new File(sDir).listFiles();
      int checkSum = 0;
      for (File file : faFiles) {
         if (file.exists()) {
            int fileSum = 0;
            if (file.getName().matches("^(.*?)")) {

               if (!file.isDirectory()){
                  try {
                     // byte[] fileBytes = Files.readAllBytes(file.toPath());
                     RandomAccessFile f = new RandomAccessFile(file, "r");
                     byte[] b = new byte[(int) f.length()];
                     f.readFully(b);
                     for (byte a : b) {
                        fileSum += a;
                     }
                  } catch (IOException ex) {
                     //suppressing IO exceptions due to random file system reading interference
                  }
                  checkSum += fileSum;
                  System.out.println("\tFile: " + file.getAbsolutePath() + " is " + fileSum + " bytes");
               }
            }
            if (file.isDirectory()) {
               int sum = printFnames(file.getAbsolutePath());
               checkSum += sum;
            }
         }
      }
      System.out.println("Directory Checksum: " + sDir + "'s checksum is " + checkSum + " bytes\n");
      return checkSum;
   }
}