package c.sakshi.lab5;

public class Note {
    private String date;
    private String username;
    private String content;
    private String title;

    public Note(String date, String username, String content, String title){
        this.content=content;
        this.date=date;
        this.username=username;
        this.title=title;
    }

    public String getDate(){return date;}
    public String getContent(){return content;}
    public String getTitle(){return title;}
    public String getUsername(){return username;}

}
