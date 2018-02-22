//import	java.util.Scanner;	// version 2
import	javax.swing.*;

public class NameBox
{
	public static void main(String[] args)
	{
		/*if (args.length == 0)		// version 1
		{
			System.out.println("USAGE: java NameBox <name>");
			System.exit(1);
		}
		
		int	count = 0;*/
		
		/*Scanner	input = new Scanner(System.in);	// version 2
		System.out.print("Please enter your name: ");
		String	name = input.nextLine();*/
		
		String	name = JOptionPane.showInputDialog(null,	// version 3
				"Please enter your name", "Data Input",
				JOptionPane.QUESTION_MESSAGE);
		if (name == null)
		{
			System.err.println("No name entered");
			System.exit(1);
		}
		
		System.out.print("+");
		for(int i = 0; i < name.length(); i++)
			System.out.print("-");
		System.out.println("+");
		
		System.out.println("|" + name + "|");
		
		System.out.print("+");
		for(int i = 0; i < name.length(); i++)
			System.out.print("-");
		System.out.println("+");
		
		/*int	count = name.length();
		
		for (int i = 0; i < args.length; i++)	// version 1
			count += args[i].length();
		count += args.length - 1;
		
		System.out.print("+");
		for(int i = 0; i < count; i++)
			System.out.print("-");
		System.out.println("+");
		
		System.out.print("|");
		for (int i = 0; i < args.length - 1; i++)
			System.out.print(args[i] + " ");
		System.out.print(args[args.length - 1]);
		System.out.println("|");
		
		System.out.print("+");
		for(int i = 0; i < count; i++)
			System.out.print("-");
		System.out.println("+");*/
	}
}
