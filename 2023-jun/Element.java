public class Element extends SymbolNode {
	
  Object value;
	public Element( String name, 
			Type type, 
      Object value,
			SymbolNode next )
	{
		super( name, SymbolNode.ELEMENT, type, next );
    this.value = value;
	}
	
}
