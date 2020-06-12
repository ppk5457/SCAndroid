package cn.edu.sc.sc01;

import androidx.annotation.NonNull;

public class News {
    private String title;
    private String content;

    public String getEditdate() {
        return editdate;
    }

    public void setEditdate(String editdate) {
        this.editdate = editdate;
    }

    private String editdate;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    @NonNull
    @Override
    public String toString() {
        return "title:"+title+",content:"+content+",editdate:"+editdate;
    }
}
