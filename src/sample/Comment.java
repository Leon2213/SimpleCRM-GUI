package sample;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class Comment {

    private String dateCreated;
    private String user;
    private String commentInfo;


    public Comment(String commentInfo, String user) {
        this.dateCreated = DateToString();
        this.commentInfo = commentInfo;
        this.user = user;

    }


    public String DateToString() {
        Date date = Calendar.getInstance().getTime();
        DateFormat dateFormat = new SimpleDateFormat("yyyy-mm-dd hh:mm:ss");
        String strDate = dateFormat.format(date);
        return strDate;
    }

    public String getDateCreated() {
        return dateCreated;
    }

    public String getUser(){
        return user;

    }
    public String getCommentInfo() {
        return commentInfo;
    }

}
