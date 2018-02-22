//package math;

import java.util.Scanner;

public class FractionCalculator {

   // Function to check if the input is 'quit'
   public static void processCommand(String input)
   {
       if(input.equalsIgnoreCase("quit"))
           System.exit(0);
   }
  
   //Function to process the frac and convert it to mixed or improper fraction
   public static String processFraction(String frac)
   {
       if(frac.matches("\\d+"))
           return frac+"/1";
       //I was not provided with GCF function, hence used regex to match (-) sign
       else if(frac.matches("[-]*\\d+[_][-]*\\d+[/\\\\][-]*\\d+"))
           return ConvertToOutput(frac);
       else
           return frac;
   }
  
   public static String ConvertToOutput(String frac)
   {
       int wholeNumber = Integer.valueOf(frac.substring(0,frac.indexOf("_")));
       int num = Integer.valueOf(frac.substring(frac.indexOf("_")+1, frac.indexOf("/")));
       int denom = Integer.valueOf(frac.substring(frac.indexOf("/")+1, frac.length()));
       int value = (wholeNumber*denom)+num;
       return value+"/"+denom;
   }
  
   public static int gcm(int a, int b) {
   return b == 0 ? a : gcm(b, a % b); // Not bad for one line of code :)
   }
  
   public static String calculation(String frac1, String frac2, char op)
   {
       frac1 = processFraction(frac1);
       frac2 = processFraction(frac2);
       int num = 0, denom, num1, num2, denom1, denom2;
       num1 = Integer.valueOf(frac1.substring(0, frac1.indexOf("/")));
       num2 = Integer.valueOf(frac2.substring(0, frac2.indexOf("/")));
       denom1 = Integer.valueOf(frac1.substring(frac1.indexOf("/")+1, frac1.length()));
       denom2 = Integer.valueOf(frac2.substring(frac2.indexOf("/")+1, frac2.length()));
      
      
       if(op == '/')
       {
           int temp = num2;
           num2 = denom2;
           denom2 = temp;
           op = '*';
       }
       denom = denom1*denom2;
       if(op != '*')
       {
           num1 = num1 * denom2;
           num2 = num2 * denom1;
       }
       switch(op)
       {
           case '+': num = num1+num2;break;
           case '-': num = num1-num2;break;
           case '*': num = num1*num2;break;
       }
       int gcm = gcm(num, denom);
       return (num / gcm) + "/" + (denom / gcm);
   }
   public static void main(String[] args) {
       String input = "";
       Scanner reader = new Scanner(System.in);
       while(true)
       {
           System.out.print("Input> ");
           input = reader.nextLine();
           processCommand(input);
           String fracs[] = input.split(" ");
           System.out.println("Output> " + calculation(fracs[0], fracs[2], fracs[1].charAt(0)));
       }
      
      

   }

}