package cn.edu.sc.scu_internet;

import androidx.annotation.NonNull;

import java.util.ArrayList;


public class News {
    private String title;
    private String content;
    private String author;
    private String editData;
    private String type;
    private ArrayList<String> keywords;

    public News(String title, String content, String author, String editData, String type) {
        this.title = title;
        this.content = content;
        this.author = author;
        this.editData = editData;
        this.type = type;
    }

    @NonNull
    @Override
    public String toString() {
        return "标题："+this.title+"\n内容："+this.content+"\n作者："+this.author;
    }

    public News(String title, String content, String author) {
        this.title = title;
        this.content = content;
        this.author = author;
    }

    public ArrayList<String> getKeywords() {
        return keywords;
    }

    public void setKeywords(ArrayList<String> keywords) {
        this.keywords = keywords;
    }

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

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getEditData() {
        return editData;
    }

    public void setEditData(String editData) {
        this.editData = editData;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
