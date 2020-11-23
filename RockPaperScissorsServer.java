
package rockpaperscissorsserver;
import java.net.*;
import java.io.*;
/**
 *
 * @author Safayat
 */
public class RockPaperScissorsServer {

    /**
     * @param args the command line arguments
     * @throws java.lang.Exception
     */
    public static void main(String[] args) throws Exception{
        
        //Runs method runSocket on port 1337
        runSocket(8080);
    }
    
    //This method opens and runs a socket 
    public static void runSocket(int port)
    {
         
        try 
        {
            //New class that implements the socket
            ServerSocket Services = new ServerSocket(port);

            //Listening time set to 30 seconds
            Services.setSoTimeout(30000);
            System.out.println("Wating for players...");
            System.out.println("1 is Rock, 2 is Paper, 3 is Scissors");

            
            while(true)
            {
                 // connecting clients
                Socket connectionSocket1 = Services.accept();
                Socket connectionSocket2 = Services.accept();
                
               
                
                
                //buffered object for reading and writting for player 1
                InputStreamReader input1 = new InputStreamReader(connectionSocket1.getInputStream());
                OutputStreamWriter output1 = new OutputStreamWriter(connectionSocket1.getOutputStream());
                BufferedReader player1 = new BufferedReader(input1);
                BufferedWriter Client1Output = new BufferedWriter(output1);
              
                // Getting player 1's name
                String name1 = player1.readLine();
  
                // Getting input from player 1 
                String player1Input = player1.readLine();
                System.out.println("Player 1 is : "+name1);
                System.out.println(name1+"'s choice is : "+player1Input);
                
                // Converting string to integer
                int playerMove1 = Integer.parseInt(player1Input);
                String Result = null;
                
                //buffered object for reading and writting for player 1
                InputStreamReader input2 = new InputStreamReader(connectionSocket2.getInputStream());
                OutputStreamWriter output2 = new OutputStreamWriter(connectionSocket2.getOutputStream());
                BufferedReader player2 = new BufferedReader(input2);
                BufferedWriter Client2Output = new BufferedWriter(output2);
                 
                // Getting player 2's name
                String name2 = player2.readLine();
              
                // Getting input from player 2 
                String player2Input = player2.readLine();
                System.out.println("Player 2 is : "+name2);
                System.out.println(name2+"'s choice is : "+player2Input);
                
                // Converting string to integer
                int playerMove2 = Integer.parseInt(player2Input);
                String Result2 = null;
                
                //COnditions of victory or loss for each player
                if (playerMove1 == playerMove2) {
                    
                    
                    Result = "DRAW";
                    Result2 = "DRAW";
                } 
                // player 1 rock
                // player 2 paper
                else if (playerMove1 == 1 && playerMove2 == 2) 
                {
                    Result = "YOU LOSE :(";
                    Result2 = "YOU WIN!";
                } 
                // player 1 rock
                // player 2 scissors
                else if (playerMove1 == 1 && playerMove2 == 3) 
                {
                    Result = "YOU WIN!";
                    Result2 = "YOU LOSE :(";
                } 
                // player 1 paper
                // player 2 rock
                else if (playerMove1 == 2 && playerMove2 == 1) 
                {
                    Result = "YOU WIN!";
                    Result2 = "YOU LOSE :(";
                } 
                // player 1 paper
                // player 2 scissors
                else if (playerMove1 == 2 && playerMove2 == 3) 
                {
                    Result = "YOU LOSE";
                    Result2 = "YOU WIN!";
                } 
                // player 1 scissors
                // player 2 rock
                else if (playerMove1 == 3 && playerMove2 == 1) 
                {
                    Result = "YOU LOSE :(";
                    Result2 = "YOU WIN!";
                } 
                // player 1 scissors
                // player 2 paper
                else if (playerMove1 == 3 && playerMove2 == 2) 
                {
                    Result = "YOU WIN!";
                    Result2 = "YOU LOSE :(";
                }
                
                 //Sends out output to each client
                Client1Output.write(Result, 0, Result.length());
                Client2Output.write(Result2, 0, Result2.length());
                Client1Output.flush();
                Client2Output.flush();
                System.out.println(name1+ " : " + Result);
                System.out.println(name2+ " : " + Result2);
               

                //Closes the sockets
                connectionSocket1.close();
                connectionSocket2.close();
                          
            }
             
        } 
        catch (IOException e) 
        {
             System.out.println("Error:");
             System.out.println(e);
        }
        
    }
    
}
