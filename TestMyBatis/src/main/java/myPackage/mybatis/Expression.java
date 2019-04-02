package myPackage.mybatis;

public interface Expression {
    boolean calculate();
    void print(String spaces);
    int saveInDB(int parent);
}

