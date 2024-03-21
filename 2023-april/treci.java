//1. Definicija klase
public class RepeatStatement extends Statement {
  public int times;
  public Expression condition;
  public ArrayList<Statement> stmtList;

  public RepeatStatement(int times, Expression condition, ArrayList<Statement> stmtList) {
    this.times = times;
    this.condition = condition;
    this.stmtList = stmtList;
  }

  @Override
  public void translate(BufferedWriter out) throws IOException { ... }
}
