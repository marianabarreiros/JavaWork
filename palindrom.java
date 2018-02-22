import java.util.Scanner;
public class Palindrome
	{

    public static void main(String [] args)
    {
    	int count = 0;
    	int theCount = args.length;
    	String term = null;
    	
    	for (String s : args)
			count += s.length();
    	count += args.length - 1;   		
    	 Scanner input = new Scanner(System.in);
		Palindrome pn = new Palindrome();
			
		if (args.length == 2)
			 	term = args[0] + args[1];
		if (args.length == 3)
			term = args[0] + args[1] + args[2];
			if (args.length == 4)
				term = args[0] + args[1] + args[2] + args[3];
				if (args.length == 5)
					term = args[0] + args[1] + args[2] + args[3] + args[4];
					if (args.length == 6)
						term = args[0] + args[1] + args[2] + args[3] + args[4] + args[5];
						if (args.length == 7)
							term = args[0] + args[1] + args[2] + args[3] + args[4] + args[5] + args[6];
							if (args.length == 8)
								term = args[0] + args[1] + args[2] + args[3] + args[4] + args[5] + args[6] + args[7];
								if (args.length == 9)
									term = args[0] + args[1] + args[2] + args[3] + args[4] + args[5] + args[6] + args[7] + args[8];
									if (args.length == 10)
										term = args[0] + args[1] + args[2] + args[3] + args[4] + args[5] + args[6] + args[7] + args[8] + args[9];
									if (args.length == 11)
										term = args[0] + args[1] + args[2] + args[3] + args[4] + args[5] + args[6] + args[7] + args[8] + args[9] + args[10];
									if (args.length == 12)
										term = args[0] + args[1] + args[2] + args[3] + args[4] + args[5] + args[6] + args[7] + args[8] + args[9] + args[10] + args[11];
									if (args.length == 13)
										term = args[0] + args[1] + args[2] + args[3] + args[4] + args[5] + args[6] + args[7] + args[8] + args[9] + args[10] + args[11] + args[12];
									
										
		    		if (term != null)
			 	   if(pn.isPalindrome(term))
			        {
			            System.out.println("Terminal Is A Palindrome");
			            //System.out.println("what is term" + term);
			        }
			        else 
			        {
			            System.out.println("Terminal Is Not A Palindrome");
			            //System.out.println("what is term" + term);
			        } 
					
		   	//System.out.print("this is t[thecount]" + t[i]);
				
				
				

					else
						
			 		System.out.print("please enter text: ");
			 		String name = input.nextLine();
			        
			        
			        if (name == null)
					{
						System.err.println("failed to enter a name");
						System.exit(1);
					}
			    		
					name = name.replaceAll("\\s+", "");
						
						if(pn.isPalindrome(name))
        {
            System.out.println("Is A Palindrome");
        }
        else 
        {
            System.out.println("Is Not A Palindrome");
        } 
					}
    


    public boolean isPalindrome(String original)
    {
    
    		int i = original.length()-1;
        int j=0;
        while(i > j) {
            if(original.charAt(i) != original.charAt(j)) 
            {
                return false;
            }
            i--;
            j++;
        }
        return true;
    }

}
