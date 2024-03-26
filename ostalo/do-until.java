public class DoUntill extends Statement {

  public ArrayList<Statement> Body;
  public Expression ConditionExpression;
  public Expression MaxExpression;
  public Statement stmt;

  public DoUntill(ArrayList<Statement> body, Expression conditionExpression, Expression maxExpression, Statement statement){
    this.Body = body;
    this. ConditionExpression = conditionExpression;
    this.MaxExpression = maxExpression;
    this.stmt = statement;
  }

  @Override
  public void translate(BufferedWriter out) throws IOException {

    String pocetak = ASTNode.genLab();
    String def = ASTNode.genLab();
    String kraj = ASTNode.genLab();

    out.write("Load_Const R3, 0" + "\n");
    this.MaxExpression.translate(out);
    int max = this.MaxExpression.result;
    out.write("Load_Mem R2, " + max);
    
    out.write(pocetak + "\n");
    this.Body.translate(out);
    out.write("Compare_Equal R2, R3" + "\n");
    out.write("JumpIfNotZero R2, " + def + "\n");
    this.ConditionExpression.translate(out);
    out.write("Load_Mem R1, " + ConditionExpression.result);
    out.write("Load_Const R4, 1" + "\n");
    out.write("Add R3, R4" + "\n");
    out.write("JumpIfZero R1, " + pocetak + "\n");
    out.write("Jump " + kraj + "\n");
    out.write(def + "\n");
    this.Statement.translate(out);
    out.write(kraj + "\n");
  }
}
/*
 * Load_Const R3, 0    ovo je brojac
 * IMC<MaxExpression>
 * Load_Mem R2, RESULT<MaxExpression>
 *
 * pocetak:
 *  IMC<Body>
 *  Compare_Equal R2, R3
 *  JumpIfNotZero R2, default
 *  IMC<ConditionExpression>
 *  Load_Mem R1, RESULT<ConditionExpression>
 *  Load_Const R4, 1
 *  Add R3, R4
 *  JumpIfZero R1, pocetak
 *  Jump kraj
 * default:
 *  IMC<Statement>
 * kraj:
 *
 */
