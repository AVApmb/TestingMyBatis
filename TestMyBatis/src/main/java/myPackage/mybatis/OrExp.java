package myPackage.mybatis;

import java.util.List;

public class OrExp implements Expression {

    private int id;
    private List<Expression> expressions;
    private int parentID;

    public OrExp(List<Expression> expressions) {
        this.expressions = expressions;
    } //raw
    public OrExp(Integer id) {
        this.id = id.intValue();
        this.expressions = CurrentSession.session.selectList("getExpression", id);
    } // for mybatis

    public int getId() {
        return id;
    }
    public int getparentID() {
        return parentID;
    }

    public boolean calculate() {
        for (Expression expression : expressions)
            if (calculate() == true)
                return true;
        return false;
    }
    @Override
    public void print(String spaces) {
        System.out.println(spaces + "||");
        for (Expression expression : expressions)
            expression.print(spaces + " ");
    }
    @Override
    public int saveInDB(int parent) {
        parentID = parent;
        CurrentSession.session.insert("insertOR", this);
        for (Expression expression : expressions)
            expression.saveInDB(id);
        return id;
    }


}
