package sample;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;


public class Case {
    private static int counter = 0;
    private int nr;
    private String description;
    private List<Comment> comments = new ArrayList<>();
    private Category category;
    private String dateCreated;
    private String commentFormat;
    private String status;


    public Case(String description, Category category, String dateCreated) {
        this.description = description;
        this.category = category;
        this.nr = counter;
        this.dateCreated = DateToStringExample1();
        this.status = "Open";
        counter++;
    }

    public String getStatus() {
        return status;
    }

    public void closeCase() {
        status = "Closed";
    }

    public void openCase() {
        status = "Open";
    }


    public void addComment(String commentInfo, String user){
        comments.add(new Comment(commentInfo, user));
    }

    public String DateToStringExample1() {
        Date date = Calendar.getInstance().getTime();
        DateFormat dateFormat = new SimpleDateFormat("yyyy-mm-dd hh:mm:ss");
        String strDate = dateFormat.format(date);
        return "Converted String: " + strDate;
    }

    public String getDescription() {
        return description;
    }

    public int getNr() {
        return nr;
    }

    public Category getCategory() {
        return category;
    }

    public String getDateCreated() {
        return dateCreated;
    }

    public String getCommentsAsString() {
        String start = "";
        for (Comment comment : comments) {
            start += comment.getDateCreated()+" by "+comment.getUser()+" : "+"\n"+comment.getCommentInfo()+"\n\n";
        }
        return start;
    }

    public List<Comment> getComments() {
        return comments;
    }






        @Override
    public String toString() {
        return "Case nr: "+nr+
                "\nDescription: "+description+
                "\n"+category;
    }


}
