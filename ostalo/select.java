public class SelectExpression extends Expression {
  public Expression expr;
  public ArrayList<Expression> exprList;

  public SelectExpression(Expression expr, ArrayList<Expression> exprList) {
    this.expr = expr;
    this.exprList = expr;
  }

  @Override
  public void translate(BufferedWriter out) throws IOExpression { ... }
}

/*
 *
 *  select (
 *    2 + 1,
 *    banana,
 *    3 * koren,
 *    marlboro()
 *  )
 *
 *  IMC<expr>
 *  IMC<exprList<Result<expr>>
 *
 *
 */

void translate(BufferedWriter out) throws IOExpression {
  expr.translate(out);
  int index = expr.result;
  if(index == 0 || index >= this.exprList.size()) {
    this.result = 0;
    return;
  }
  this.exprList.at(index).translate(out);
  this.result = this.exprList.at(index).result;
}
