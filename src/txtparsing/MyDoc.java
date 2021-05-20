package txtparsing;

import java.util.ArrayList;

public class MyDoc {
    private String id;
    private String title;
    private String author;
    private String body;
    private ArrayList<String[]> citation = new ArrayList<>() ;


    public MyDoc(String id, String title, String author, String body, ArrayList<String[]> citation) {
        this.id = id;
        this.title = title;
        this.author = author;
        this.body = body;
        this.citation = citation;
    }

    @Override
    public String toString() {
        String ret = "MyDoc{"

                + "\n\tId: " + id
                + "\n\tTitle: " + title
                + "\n\tAuthor: " + author
                + "\n\tBody: " + body;
        return ret + "\n}";
    }

    //---- Getters & Setters definition ----
    public String getId(){return id;}

    public void setId(String id){this.id = id;}

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public ArrayList<String[]> getCitation() {
        return citation;
    }

    public void setCitation(ArrayList<String[]> citation) {
        this.citation = citation;
    }

}
