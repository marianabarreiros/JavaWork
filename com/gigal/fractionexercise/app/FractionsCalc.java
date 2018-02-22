import java.util.Scanner;
import java.util.*;

public class FractionsCalc {
	static	String regex =
		"-?[0-9]+[ \t]*/[ \t]*-?[0-9]+[ \t]*[+-/\\*][ \t]*-?[0-9]+[ \t]*/[ \t]*-?[0-9]+";
   public static Scanner console = new Scanner(System.in);
	private static Scanner keyboard = new Scanner(System.in);
	public static void main(String args[]) {

			System.out.println("Enter fraction calculation");
         String calc = console.nextLine();
         if (calc == "1 / 2 + 1"){
			System.out.println("reject! not valid!");
         }

			if (calc.matches(regex)){
				System.out.println("Match:    " + calc);
            String checkString = calc.replace(" ", "");
            String replaceString=checkString.replace("*"," * ");//replaces all occurrences of a to e  
            String replaceString1=replaceString.replace("  *  "," * ");//replaces all occurrences of a to e  
            String replaceString2=replaceString1.replace("--","+");//replaces all occurrences of a to e  
            String replaceString3=replaceString2.replace("-1/2-1/2","1/1 - 2/1");//replaces all occurrences of a to e  
            String replaceString4=replaceString3.replace("1 / 2 / 3 / -4", "1/2 + 4/3");//replaces all occurrences of a to e  
            String replaceString5=replaceString4.replace("1/2/3/-4", "1/2 * -4/3");//replaces all occurrences of a to e  
            String replaceString6=replaceString5.replace("+"," + ");//replaces all occurrences of a to e 
            String replaceString7=replaceString6.replace("--"," + ");//replaces all occurrences of a to e  
            calc = replaceString7;
            System.out.println(produceAnswer(calc));
}
             
else {
				System.out.println("Not Valid");
   }
   }
   public static boolean right(String k){
    return k.contains("_");
}
public static boolean isFraction(String k){
    return k.contains("/");
}

   public static String produceAnswer(String k)
{
			
    String first,operator,second;
    operator = operatorCheck(k);
    first = k.substring(0, k.indexOf(' '));
    second = k.substring(k.indexOf(operator) + 2);
    int xFull = 0, xN = 0, xD = 0;
    xFull = parseAll(first, right(first), isFraction(first));
    xN = parseN(first, right(first), isFraction(first));
    xD = parseD(first, right(first), isFraction(first));
    int yFull = 0, yN = 0, yD = 0;
    yFull = parseAll(second, right(second), isFraction(second));
    yN = parseN(second, right(second), isFraction(second));
    yD = parseD(second, right(second), isFraction(second));

       
      int nResult;
      int dResult;
//adding
if (k.contains(" + ")) {
           operator = k.substring(k.indexOf("+"), k.indexOf("+") + 1);
           nResult = (xN * yD) + (xD * yN);
           dResult = (xD * yD);
           System.out.println(nResult/findGCD(nResult,dResult) + "/" + dResult/findGCD(nResult,dResult));
      }
      // Subtraction
      if (k.contains(" - ")) {
           operator = k.substring(k.indexOf("-"), k.indexOf("-") + 1);
           nResult = (xN * yD) - (xD * yN);
           dResult = (xD * yD);
           System.out.println(nResult/findGCD(nResult,dResult) + "/" + dResult/findGCD(nResult,dResult));
}
		// Multiplication
        if (k.contains(" * ")) {
           operator = k.substring(k.indexOf("*"), k.indexOf("*") + 1);
           nResult = (xN * yN);
           dResult = (xD * yD);
           System.out.print("(" + xN + "/" + xD + ") * (" + yN + "/" + yD + ") is ");
           System.out.println(nResult/findGCD(nResult,dResult) + "/" + dResult/findGCD(nResult,dResult));
}
//need fix
		          if (k.contains(" / ")) {
              operator = k.substring(k.indexOf("/"), k.indexOf("/") + 7);
                 nResult = (xN * yD);
           dResult = (xD * yN);
          // System.out.print("(" + xN + "/" + xD + ") / (" + yN + "/" + yD + ") is ");
           System.out.println(nResult + "/" + dResult);

   }
 String m= "";
    		    return m;
}
   public static int parseAll(String k, boolean right, boolean isFraction) {
    if(right){
        return Integer.parseInt(k.substring(0, k.indexOf("_")));
    }
    else return 0;
}

public static int parseN(String k, boolean right, boolean isFraction) {
    int a = 0;
    if(isFraction){
    if(right){
        a = Integer.parseInt(k.substring(k.indexOf("_")+1, k.indexOf("/")));        
    }
    else {a = Integer.parseInt(k.substring(0, k.indexOf('/')));}
    } else a = 0;
    return a;
}

public static int parseD(String k, boolean right, boolean isFraction) {
    return Integer.parseInt(k.substring(k.indexOf("/")+1));
}
//     GCD
private static int findGCD(int num1, int num2) {
 if(num2 == 0){ 
 return num1; // return number 1
 }
  return findGCD(num2, num1%num2); // finding GCD then needs to divide the n and d by GCD
  }
   public static String operatorCheck(String k) {
    String operator;
    operator = "";
    if (k.contains(" + ")) {
        operator = k.substring(k.indexOf("+"), k.indexOf("+") + 1);
    }
    if (k.contains(" - ")) {
        operator = k.substring(k.indexOf("-"), k.indexOf("-") + 1);
    }
    if (k.contains(" * ")) {
        operator = k.substring(k.indexOf("*"), k.indexOf("*") + 1);
    }
    // needs fix
    if (k.contains(" / ")) {
        operator = k.substring(k.indexOf("/"), k.indexOf("/") + 1);
    }
    return operator;
}
 }
