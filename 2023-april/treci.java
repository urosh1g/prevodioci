//1. Definicija klase
public class RepeatStatement extends Statement {
  public int times;
  public Expression condition;
  public ArrayList<Statement> stmtList;

  public RepeatStatement(int times, Expression condition, ArrayList<Statement> stmtList) {
    this.times = times;
    this.condition = condition;
    this.stmtList = stmtList;

    condition.result = ASTNode.genVar();
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

//3. impl RepeatStatement
public void translate(BufferedWriter out) throws IOException {
  String startLabel = ASTNode.genLab();
  String endLabel = ASTNode.genLab();
  out.write("Load_Const R4, 0\n");
  out.write(String.format("Load_Const R5, %d\n", this.times));
  out.write(String.format("%s:\n", startLabel));
  out.write("Compare_Equal R4, R5\n");
  out.write(String.format("JumpIfNotZero %s\n", endLabel));
  condition.translate(out);
  out.write(String.format("Load_Mem R1, %s\n", condition.result));
  out.write("Load_Const R2, 0\n");
  out.write("Compare_Equal R1, R2\n");
  out.write(String.format("JumpIfNotZero %s\n", endLabel));
  for(Statement stmt : stmtList) {
    stmt.translate(out);
  }
  out.write("Load_Const R3, 1\n");
  out.write("ADD R4, R3\n");
  out.write(String.format("Jump %s\n", startLabel));
  out.write(String.format("%s:\n", endLabel));
}
