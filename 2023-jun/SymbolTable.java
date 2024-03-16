public class SymbolTable {
	
	/*tabela simbola za "language scope"
	u ovom slucaju tu pripadaju samo tipovi*/
	private SymbolNode types;
	
	/* tabela simbola za oblast vazenja programa */
	private SymbolNode elements;
	
	public SymbolTable( )
	{
		types = new Type( "char", Type.CHAR, null);
		types = new Type( "int", Type.INT, types );
		types = new Type( "real", Type.REAL, types );
		types = new Type( "enum", Type.ENUM, types );
		elements = null;
	}
	
	public SymbolNode addVar( String name, Type type, Object value )
	{
    if (getVar(name) != null) {
      return null;
    }
		elements = new Element( name, type, value, elements );
		return elements;
	}
	
	public Element getVar( String name )
	{
		SymbolNode current = elements;
		while ( current != null && 
				current.name.compareTo( name ) != 0 )
			current = current.next;
		return ( Element ) current;
	}
	
	public Type getType(String typeName)
	{
		SymbolNode current = types;
		while ( current != null && 
				current.name.compareTo( typeName ) != 0 )
			current = current.next;
		return ( Type ) current;
	}
	
	public SymbolNode getElements()
	{
		return elements;
	}

}
