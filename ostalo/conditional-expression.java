/*
 *   ConditionalExpression = condition ? expr : expr
 */
public class ConditionalExpression extends Expression{
  public Expression condition;
  public Expression exprTrue;
  public Expression exprFalse;

  public ConditionalExpression(Statement condition, Expression exprTrue, Expression exprFalse){
    this.condition = condition;
    this.exprTrue = exprTrue;
    this.exprFalse = exprFalse;
  }

  @Override
  public void translate(BufferedWriter out) throws IOException {
    String neistina = AST.genLab();
    String kraj = AST.genLab();

    condition.translate(out);
    out.write("LoadMem R1, " + condition.result + "\n");
    out.write("JumpIfZero R1, " + neistina + "\n");
    exprTrue.translate(out);
    out.write("Jump " + kraj + "\n");
    out.write(neistina + ": \n");
    exprFalse.translate(out);
    out.write(kraj + ":\n");

  }

}

/*
 *  lavabo = 2 > 3 ? trta : mrta;
 */

/*
 * <IMC>(condition)
 *  Load_Mem R1, RESULT(condition)
 *  JumpIfZero R1, false 
 *  <IMC>(exprTrue)
 *  Jump kraj
 *  false:
 *  <IMC>(expreFalse)
 *  kraj:
 * /
