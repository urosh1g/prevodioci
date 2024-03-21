//1. Definicije klasa
public class ConditionalExpression extends Expression {
  public Expression testExpr;
  public Expression firstExpr;
  public Expression secondExpr;
  public Expression thirdExpr;

  public ConditionalExpression(Expression test, Expression firstExpr, Expression secondExpr, Expression thirdExpr) {
    this.textExpr = test;
    this.firstExpr = firstExpr;
    this.secondExpr = secondExpr;
    this.thirdExpr = thirdExpr;

    this.result = ASTNode.genVar();
    this.textExpr.result = ASTNode.genVar();
    this.firstExpr.result = ASTNode.genVar();
    this.secondExpr.result = ASTNode.genVar();
    this.thirdExpr.result = ASTNode.genVar();
  }

  @Override
  public void translate(BufferedWriter out) throws IOException { ... }

}

//2. Definicija medjukoda
/*
 * ConditionalExpression:
 * IMC<testExpr>
 * Load_Mem R1, RESULT<textExpr>
 * Load_Const R2, 0
 * Compare_Less R1, R2
 * JumpIfNotZero firstExpr
 * Compare_Equal R1, R2
 * JumpIfNotZero secondExpr
 * Jump thirdExpr
 * firstExpr:
 * IMC<firstExpr>
 * Load R3, RESULT<firstExpr>
 * Jump KRAJ
 * secondExpr:
 * IMC<secondExpr>
 * Load R3, RESULT<secondExpr>
 * Jump KRAJ
 * thirdExpr:
 * IMC<thirdExpr>
 * Load R3, RESULT<thirdExpr>
 * KRAJ:
 * Store R3, result
 * */

//3. Impl ConditionalExpression
public void translate(BufferedWriter out) throws IOException {
  String firstLabel = ASTNode.genLab();
  String secondLabel = ASTNode.genLab();
  String thirdLabel = ASTNode.genLab();
  String endLabel = ASTNode.genLab();

  testExpr.translate(out);
  out.write(String.format("Load_Mem R1, %s\n", textExpr.result));
  out.write("Load_Const R2, 0\n");
  out.write("Compare_Less R1, R2\n");
  out.write(String.format("JumpIfNotZero %s\n", firstLabel));
  out.write("Compare_Equal R1, R2\n");
  out.write(String.format("JumpIfNotZero %s\n", secondLabel));
  out.write(String.format("Jump %s\n", thirdLabel));
  out.write(String.format("%s:\n", firstLabel));
  firstExpr.translate(out);
  out.write(String.format("Load_Mem R3, %s\n", firstExpr.result));
  out.write(String.format("Jump %s\n", endLabel));
  secondExpr.translate(out);
  out.write(String.format("Load_Mem R3, %s\n", secondExpr.result));
  out.write(String.format("Jump %s\n", endLabel));
  thirdExpr.translate(out);
  out.write(String.format("Load_Mem R3, %s\n", thirdExpr.result));
  out.write(String.format("%s:\n", endLabel));
  out.write(String.format("Store R3, %s\n", this.result));

}
