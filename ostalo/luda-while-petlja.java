public class While extends Statement {
  public ArrayList<Expression> conditions;
  public ArrayList<Statement> stmtList;

  public While(ArrayList<Expression> condition, ArrayList<Statement> stmtList) {
    this.stmtList = stmtList;
    this.conditions = conditions;
  }

  @Override
  public void translate(BufferedWriter out) throws IOException { 
    String labelaKraj = ASTNode.genLab();
    for(Expression cond: this.conditions) {
      cond.translate(out);
      out.write(String.format("Load_Mem R1, %s\n", cond.result));
      out.write(String.format("JumpIfZero R1, %s\n", labelaKraj));
    }
    for(Statement stmt: this.stmtList) {
      stmt.translate(out);
    }
    out.write(String.format("%s:\n", labelaKraj));
  }
}

/*
 *
 *  while( 2 > x, 3 < y, x > 10) 
 *    int q = 5;
 *    int a = 7;
 *    int c = a + q << 2;
 *
 *  IMC<condition>
 *  Load_Mem R1, RESULT<condition>
 *  JumpIfZero R1, kraj
 *  ovo se ponavalja za svaki condition iz liste
 *  IMC<stmt> for stmt in stmtList
 *  kraj:
 *
 */

public void translate(BufferedWriter out) throws IOException {
}
