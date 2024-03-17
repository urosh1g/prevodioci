public class Type extends SymbolNode {
	public static int STRING = 1;
	public static int FLOAT = 2;
	public static int HEX = 3;
	public static int INT = 4;
	public int tkind;
	
	public Type ( String name, 
			int typeKind, 
			SymbolNode next)
	{
		super( name, SymbolNode.TYPE, null, next );
		this.tkind = typeKind;
		this.type = this;
	}
}
