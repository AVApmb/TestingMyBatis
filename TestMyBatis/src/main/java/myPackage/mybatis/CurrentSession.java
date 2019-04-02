package myPackage.mybatis;

import org.apache.ibatis.session.SqlSession;

public class CurrentSession{
    public CurrentSession(SqlSession session) {
        this.session = session;
    }

    static SqlSession session;
}
