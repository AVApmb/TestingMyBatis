package myPackage.mybatis;

import org.apache.ibatis.session.SqlSession;

public class Rule {

    int expressionID;
    Expression expression;

    String result;

    public Rule(Expression expression, String result) {
        this.expression = expression;
        this.result = result;
    }

    public void SaveInDB() {
        expressionID = expression.saveInDB(0);
        CurrentSession.session.insert("insertRule", this);
    }

    public void print() {
        expression.print("");
    }

    public int getExpressionID() {
        return expressionID;
    }

    public String getResult() {
        return result;
    }
}
