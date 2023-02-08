package functions;

public class ModelJSON {
    private String text, body;

    public ModelJSON() {
    }

    public ModelJSON(String text, String body) {
        this.text = text;
        this.body = body;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }
}
