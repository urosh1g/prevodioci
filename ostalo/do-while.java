import java.util.ArrayList;

public class DoWhile extends Statement{
  public ArrayList<Statement> StatementList;
  public Expression expression;

  public DoWhile(ArrayList<Statement> lista, Expression expression){
    this.StatementList = lista;
    this.expression = expression;
  }
  @Override void translate(BufferedWriter out) throws IOException {
      ...
  }
}

/*
 *  do {
 *    int a = 5; => Statement        ;
 *    printf("%d", a); => Statement  ;  ova dva su zajedno u StatementList
 *    scanf("%d", &a);
 *    x++;
 *  } while( x != 5);
 *
 */


/*
 *
 * start_petlje:
 *    IMC<statement> * za svaki iz StatementList
 *    IMC<expr>
 *    Load_Mem R1, RESULT<expr>
 *    JumpIfNotZero R1, start_petlje
 *    ...
 */

void translate(BufferedWriter out) throws IOException {
  String labela = ASTNode.genLab();
  out.write(labela + ":" + "\n");
  for(Statement stmt: StatementList) {
    stmt.translate(out);
  }
  expression.translate(out);
  out.write(String.format("Load_Mem R1, %s\n", expression.result));
  out.write(String.format("JumpIfNotZero R1, %s\n", labela));
}
