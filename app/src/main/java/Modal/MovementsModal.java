package Modal;

public class MovementsModal {

    private int id;
    private String description;
    private String value;

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public String getDescription() {return description;}
    public void setDescription(String description) {this.description = description;}

    public String getValue() {return value;}
    public void setValue(String value) {this.value = value;}


    //constructor
    public MovementsModal(String description, String value){
        this.description = description;
        this.value = value;
    }

}
