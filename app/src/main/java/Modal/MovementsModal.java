package Modal;

public class MovementsModal {

    private int id;
    private String description;
    private String value;
    private int option;

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }

    public String getValue() {return value;}
    public void setValue(String value) {this.value = value;}

    public int getOption() { return option; }
    public void setOption(int option) { this.option = option; }


    //constructor
    public MovementsModal(String description, String value, int option){
        this.description = description;
        this.value = value;
        this.option = option;
    }

}
