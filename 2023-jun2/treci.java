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
