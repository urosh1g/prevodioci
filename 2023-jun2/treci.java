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
    public String endLabel = "KRAJ";

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
 * JumpIfZero R1, NEXTALTERNATIVE
 * IMC<stmt>
 * Jump KRAJ
 * NEXTALTERNATIVE:
 *
 * */

//3. Impl za Alternative
public void translate(BufferedWriter out) throws IOException {
    String nextAlternative = ASTNode.genLab();
    expr.translate(out);
    expr.genLoad("R1", out);
    out.write(String.format("JumpIfZero R1, %s\n", nextAlternative));
    stmt.translate(out);
    out.write(String.format("Jump %s\n", endLabel));
    out.write(String.format("%s:\n", nextAlternative));
}

//4. Impl za SelectStatement
public void translate(BufferedWriter out) throws IOException {
    String endLabel = ASTNode.genLab();
    for(Alternative alternative : alternativeList) {
        alternative.endLabel = endLabel;
        alternative.translate(out);
    }
    out.write(String.format("%s:\n", endLabel));
}
