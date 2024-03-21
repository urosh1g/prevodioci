//1. Definicija klase
public class SelectStatement extends Statement {
    public ArrayList<Alternative> alternativeList;

    public SelectStatement(ArrayList<Alternative> alternativeList) {
        this.alternativeList = alternativeList;
    }

    @Override
    public void translate(BufferedWriter out) throws IOException { ... }
}

public class Alternative extends Statement {
    public Expression expr;
    public Statement stmt;

    public Alternative(Expression expr, Statement stmt) {
        this.expr = expr;
        this.stmt = stmt;
    }

    @Override
    public void translate(BufferedWriter out) throws IOException { ... }
}

//2. Definicija medjukoda
/*
 * ALTERNATIVE:
 * IMC<expr>
 * Load R1, RESULT<expr>
 * JumpIfZero R1, KRAJ
 * IMC<stmt>
 *
 * */

//3. Impl za Alternative
public void translate(BufferedWriter out) throws IOException {
    expr.translate(out);
    expr.genLoad("R1", out);
    out.write("JumpIfZero R1, KRAJ");
    stmt.translate(out);
}
