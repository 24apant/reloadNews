package functions;

public class ModelComment {
    String display_name, comment;

    public ModelComment() {
    }

    public ModelComment(String display_name, String comment) {
        this.display_name = display_name;
        this.comment = comment;
    }

    public String getDisplay_name() {
        return display_name;
    }

    public void setDisplay_name(String display_name) {
        this.display_name = display_name;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }
}
