import myPackage.mybatis.Expression;
import myPackage.mybatis.Rule;
import org.apache.ibatis.session.SqlSession;

import java.util.LinkedList;
import java.util.List;

public class BDParser {

    public List<Rule> parseRules(SqlSession session) throws Exception {

        List<Rule> rules = new LinkedList<>();
        List<MetaRule> mRules = session.selectList("getRules");

        for (MetaRule mr : mRules) {
            rules.add(
                    new Rule(
                            getExpression(session, mr.expressionID),
                            mr.getResult()
                    )
            );
        }
        return rules;
    }

    private Expression getExpression(SqlSession session, int PID) {
        return (Expression) session.selectList("getRuleExpression", PID).get(0);
    }
}

enum expressionType {AND, OR, FACT}     // in mapper

class MetaRule {

    String result;
    int expressionID;

    MetaRule() {
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public void setexpressionID(int expressionID) {
        this.expressionID = expressionID;
    }

}