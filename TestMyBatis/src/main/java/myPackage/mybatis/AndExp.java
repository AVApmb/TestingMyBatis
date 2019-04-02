package myPackage.mybatis;

import java.util.List;

public class AndExp implements Expression {

    private int id;
    private List<Expression> expressions;
    private int parentID;

    public AndExp(List<Expression> facts) {
        this.expressions = facts;
    }
    public AndExp(Integer id) {
        this.id = id.intValue();
        this.expressions = CurrentSession.session.selectList("getExpression", id);
    } //for mybatis

    public int getId() {
        return id;
    }
    public int getparentID() {
        return parentID;
    }

    public boolean calculate() {
        for (Expression expression : expressions)
            if (calculate() == false)
                return false;
        return true;
    }
    @Override
    public void print(String spaces) {
        System.out.println(spaces + "&&");
        for (Expression e : expressions)
            e.print(spaces + " ");
    }
    @Override
    public int saveInDB(int parent) {
        parentID = parent;
        CurrentSession.session.insert("insertAND", this);
        for (Expression expression : expressions)
            expression.saveInDB(id);
        return id;
    }


}
