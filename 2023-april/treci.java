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

//2. Definicja medjukoda
/*
 * RepeatStatement:
 * Load_Const R4, 0 ; brojac
 * Load_Const R5, times ; limit
 * startLabel:
 * Compare_Equal R4, R5
 * JumpIfNotZero endLabel
 * IMC<condition>
 * Load_Mem R1, RESULT<condition>
 * Load_Const R2, 0
 * Compare_Equal R1, R2
 * JumpIfNotZero endLabel
 * IMC<statement> * number of statements
 * Load_Const R3, 1
 * ADD R4, R3 
 * Jump startLabel
 * endLabel:
 * 
 */
