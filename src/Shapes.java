public class Shapes {


    private String color;
    private boolean seen;
    public Shapes(String color){
        this.color = color;
    }

    public String getColor() {
        return color;
    }

    public boolean isSeen() {
        return seen;
    }
    public void see(){
        seen = true;
    }
}
