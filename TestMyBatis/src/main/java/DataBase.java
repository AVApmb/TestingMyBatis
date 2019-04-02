import myPackage.mybatis.*;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.InputStream;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class DataBase {
    static SqlSession session;
    public static void main(String[] args) {
        String resource = "mybatis-config.xml";
        try  {

            InputStream inputStream = Resources.getResourceAsStream(resource);

            SqlSessionFactory sqlSessionFactory = new
                    SqlSessionFactoryBuilder().build(inputStream);

            session = sqlSessionFactory.openSession();
            new CurrentSession(session);
//*
            //session.update("truncate"); //delete all existing data in tables
            Expression f = new FactExp("T");
            Expression f2 = new FactExp("Y");
            Expression express = new OrExp(new ArrayList<>(Arrays.asList(f, f2)));
            Expression f3 = new FactExp("U");
            Expression express2 = new AndExp(new ArrayList<>(Arrays.asList(express, f3)));
            Expression f4 = new FactExp("I");
            Expression f5 = new FactExp("O");
            Expression express3 = new OrExp(new ArrayList<>(Arrays.asList(f4, f5)));
            Expression f6 = new FactExp("L");
            Expression express4 = new AndExp(new ArrayList<>(Arrays.asList(express3, f6)));
            express = new OrExp(new ArrayList<>(Arrays.asList(express2, express4)));
            //expression hass been formed
            //express.print("");
            //System.out.println("_______________");

            Rule rule = new Rule(express, "ForthResult");
            rule.SaveInDB();
            session.commit();
            //*/
            BDParser parser = new BDParser();

            List<Rule> e = parser.parseRules(session);

            for(Rule somerule: e) {
                somerule.print();
                System.out.println(somerule.getResult() + "\n___\n");
            }
//*/
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        finally {
            session.close();
        }
    }
}
