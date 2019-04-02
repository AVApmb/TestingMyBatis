package myPackage.mybatis;



public class FactExp implements Expression {

    private int id;
    private String fact;
    private int parentID;

    public FactExp(String fact) {
        this.fact = fact;
    }

    public int getId() {
        return id;
    }
    public String getFact() {
        return fact;
    }
    public int getparentID() {
        return parentID;
    }

    public boolean calculate() {
        return true;
    }
    @Override
    public void print(String spaces) {
        System.out.println(spaces + " " + fact);
    }
    @Override
    public int saveInDB(int parent) {
        parentID = parent;
        CurrentSession.session.insert("insertFACT", this);
        return id;
    }
}
