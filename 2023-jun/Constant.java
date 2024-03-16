public class Constant {
	public Type type;
	public Object value;
	public Constant( Type constType, Object constValue )
	{
		type = constType;
		value = constValue;
	}
  public String toString() {
    return value.toString();
  }
}
