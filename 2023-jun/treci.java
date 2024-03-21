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

