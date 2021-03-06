package Java_How_to_Programm_Early_Objects_Paul_Deitel.Chapter_15.Chapter_15_Exercises;// Fig. 15.10: CreateSequentialFile.java
// Writing objects sequentially to a file with class ObjectOutputStream.


import java.io.IOException;
import java.io.ObjectOutputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.NoSuchElementException;
import java.util.Scanner;

class CreateSequentialFile_Array
{
   private static ObjectOutputStream output; // outputs data to file
    public static Account[] accountArray;

   // open file clients.ser
   public static void openFile()

   {
      try
      {
         output = new ObjectOutputStream(
            Files.newOutputStream(Paths.get("clients_Array.ser")));
      }
      catch (IOException ioException)
      {
         System.err.println("Error opening file. Terminating.");
         System.exit(1); // terminate the program
      }
   }

   // add records to file
   public static void addRecords()
   {
      Scanner input = new Scanner(System.in);

      System.out.printf("%s%n%s%n? ",
         "Enter account number, first name, last name and balance.",
         "Enter end-of-file indicator to end input.");

      while (input.hasNext()) // loop until end-of-file indicator
      {
          int i=0;
         try
         {
            // create new record; this example assumes valid input
            Account record = new Account(input.nextInt(),
               input.next(), input.next(), input.nextDouble());


            accountArray[i]= record;
             System.out.printf("\n%d %s %s %.2f", record.getAccount(),record.getFirstName(), record.getLastName(), record.getBalance());
            i++;
         }
         catch (NoSuchElementException elementException)
         {
            System.err.println("Invalid input. Please try again.");
            input.nextLine(); // discard input so user can try again
         }
//         catch (IOException ioException)
//         {
//            System.err.println("Error writing to file. Terminating.");
//            break;
//         }

         System.out.print("? ");
      }
      try {
         output.writeObject(accountArray);
      } catch (IOException e) {
         System.err.println("Error writing to file. Terminating.");

      }
   }

   // close file and terminate application
   public static void closeFile()
   {
      try
      {
         if (output != null)
            output.close();
      }
      catch (IOException ioException)
      {
         System.err.println("Error closing file. Terminating.");
      }
   }
    public static void main(String[] args) {
        openFile();
        addRecords();
        closeFile();
    }
} // end class CreateSequentialFile


