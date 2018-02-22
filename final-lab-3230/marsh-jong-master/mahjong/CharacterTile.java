
import java.util.*;
import java.awt.*;


public class CharacterTile extends Tile {

	private static final Map<Character, String> symbols;
	private static final Map<Character, String> symbolNames;

	protected char symbol;
	
	static {
		symbols = new HashMap<Character, String>();
		symbols.put('1', "\u4E00");
		symbols.put('2', "\u4E8C");
		symbols.put('3', "\u4E09");
		symbols.put('4', "\u56DB");
		symbols.put('5', "\u4E94");
		symbols.put('6', "\u516D");
		symbols.put('7', "\u4E03");
		symbols.put('8', "\u516B");
		symbols.put('9', "\u4E5D");
		symbols.put('N', "\u5317");
		symbols.put('E', "\u6771");
		symbols.put('W', "\u897F");
		symbols.put('S', "\u5357");
		symbols.put('C', "\u4E2D");
		symbols.put('F', "\u767C");
		symbols.put('w', "\u842C");
		
		symbolNames = new HashMap<Character, String>();
		for (int i = 1; i < 10; i++) {
			symbolNames.put(Character.forDigit(i, 10), "Character " + i);
		}
      
      
		symbolNames.put('N', "North Wind");
		symbolNames.put('E', "East Wind");
      symbolNames.put('S', "South Wind");
		symbolNames.put('W', "West Wind");
		symbolNames.put('C', "Red Dragon");
		symbolNames.put('F', "Green Dragon");
	}
    
    public CharacterTile(char symbol) {
		if (symbols.get(symbol) == null) {
			throw new IllegalArgumentException("Not Valid " +
					"chacreter tile (" + symbol + ")");
               
               
		}
        this.symbol = symbol;
		
		setToolTipText(toString());
      
      
    }
    // to make it round
	public CharacterTile(char symbol, boolean drawRound)
   
    {
		this(symbol);
		this.drawRound = drawRound;
	}
    
	@Override
    public boolean matches(Tile otherTile)
     {
        return super.matches(otherTile) && this.symbol == ((CharacterTile) otherTile).symbol;
    }

    @Override
    protected void paintComponent(Graphics g)
     {
        super.paintComponent(g);
		
		Font font = g.getFont();

		g.setColor(ORANGE);
		Font newFont = font.deriveFont(11f);
		g.setFont(newFont);
		g.drawString(String.valueOf(symbol), WIDTH, 14);
		
		g.setColor(BLACK);

		float nSize = 19f;
		float size = 42f;
		int x = WIDTH / 2 - 10;
		int y = HEIGHT / 2 + 15;
		if (symbol == 'C' || symbol == 'F') 
      {
			if (symbol == 'C') {
				g.setColor(ORANGE);
			}
			else {
				g.setColor(GREEN);
			}
		}
		else if (String.valueOf(symbol).matches("[1-9]"))
       {
			size = nSize;
			x = WIDTH / 2;
			y = HEIGHT / 2 - 9;
		}
		
		newFont = font.deriveFont(size);
        g.setFont(newFont);
		
		g.drawString(symbols.get(symbol), x, y);
		
		if (size == nSize) 
      {
      
      
			g.setColor(ORANGE);
			g.drawString(symbols.get('w'), x, y * 2);
		}
    }
    
	@Override
    public String toString()
     {
     
     
		String Sname = symbolNames.get(symbol);
		if (Sname == null) 
      {
			return "Not Valid";
		}
      
      
      
		return Sname;
    }
}
