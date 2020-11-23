
package rockpaperscissorsserver;
import java.util.Scanner;
import java.net.*;
import java.io.*;
/**
 *
 * @author Safayat
 */
public class Client {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
         
        try {

            //Open connection towards clients 
            System.out.println("Connecting to localhost:8080");
            System.out.println("Please wait...");
            InetAddress address = InetAddress.getByName("localhost");

            //Open a new socket belonging to one client towards a server at a specific address and port
            Socket client = new Socket("localhost", 8080);
            System.out.print("Player ready.\n");
            System.out.println("Rules: ");
            System.out.println("Paper beates Rock"
                    + "Rock beats Scissors"
                    + "Scissors beats Paper");
            
            

            //Create classes to access outputs from the clients and write
            InputStreamReader reader = new InputStreamReader(client.getInputStream());
            BufferedReader inbox = new BufferedReader(reader);
            OutputStreamWriter writer = new OutputStreamWriter(client.getOutputStream());
            BufferedWriter outbox = new BufferedWriter(writer);
            
            
            // Ask user's name
            String name = userNameInput();
            // send user's name
            outbox.write(name + "\n",0, name.length() +1);
        
            //Ask the user to type 
            String inputStr = userInput();
            System.out.println(name+ " selected the letter : " + inputStr);
            
            //Sends the user's choice off to the server
            outbox.write(inputStr + "\n", 0, inputStr.length() + 1);
            //This method forces any buffered output bytes to be written out
            outbox.flush();

            //Receives result from server
            String playerResult = inbox.readLine();
            System.out.println(playerResult);

            //Exceptions are handled if no connections are present or program times out  
        } catch (IOException e) {
            System.out.println(e);
        }
    }
    //This method allows the user entering an input from the keyboard and store it as a string
    private static String userInput() {
        Scanner in = new Scanner(System.in);
        String inputStr = null;
       
    
        do{
            System.out.print("Enter 1 for Rock(R), 2 for Paper(P) or 3 for Scissor(S): ");
            inputStr = in.next();
        } while (!("1".equals(inputStr)) && !("2".equals(inputStr)) && !("3".equals(inputStr)));
        return inputStr;
       
    }
    //This method allows the user entering an input from the keyboard and store it as a string
    private static String userNameInput()
    {
        Scanner in = new Scanner(System.in);
        String name = null;
        do{
             System.out.print("Please type your name: \n");
             name = in.next();
        }
        while(name.isEmpty()); 
        return name;
    }
}
