package team2.mobileapp.gplx.Volley.model;

public class QuestionSet {
    private String id;
    private String name;
    private boolean status;
    private int quantity;
    private int wrongAns;
    private int rightAns;

    public QuestionSet(String name, boolean status, int quantity, int wrongAns, int rightAns) {
        super();
        this.name = name;
        this.status = status;
        this.quantity = quantity;
        this.wrongAns = wrongAns;
        this.rightAns = rightAns;
    }
    public QuestionSet(String id, String name, boolean status, int quantity, int wrongAns, int rightAns) {
        super();
        this.id = id;
        this.name = name;
        this.status = status;
        this.quantity = quantity;
        this.wrongAns = wrongAns;
        this.rightAns = rightAns;
    }
    public QuestionSet() {

    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public boolean getStatus() {
        return status;
    }
    public void setStatus(boolean status) {
        this.status = status;
    }
    public int getQuantity() {
        return quantity;
    }
    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
    public int getWrongAns() {
        return wrongAns;
    }
    public void setWrongAns(int wrongAns) {
        this.wrongAns = wrongAns;
    }
    public int getRightAns() {
        return rightAns;
    }
    public void setRightAns(int rightAns) {
        this.rightAns = rightAns;
    }

    @Override
    public String toString() {
        return "QuestionSet{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", status=" + status +
                ", quantity=" + quantity +
                ", wrongAns=" + wrongAns +
                ", rightAns=" + rightAns +
                '}';
    }
}
