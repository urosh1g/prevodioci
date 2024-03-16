public class Type extends SymbolNode {
	public static int REAL = 0;
	public static int CHAR = 1;
	public static int INT = 3;
	public static int ENUM = 4;
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
