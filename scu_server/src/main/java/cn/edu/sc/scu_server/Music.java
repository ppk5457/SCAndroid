package cn.edu.sc.scu_server;

public class Music {
    private String path;
    private String name;
    private String album;
    private String artist;
    private long size;
    private int duration;
    private int time;
    public Music(String name,String path,String album,String artist,long size,int duration,int time){
        this.path=path;
        this.name=name;
        this.album=album;
        this.artist=artist;
        this.duration=duration;
        this.size=size;
        this.time=time;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAlbum() {
        return album;
    }

    public void setAlbum(String album) {
        this.album = album;
    }

    public String getArtist() {
        return artist;
    }

    public void setArtist(String artist) {
        this.artist = artist;
    }

    public long getSize() {
        return size;
    }

    public void setSize(long size) {
        this.size = size;
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    public int getTime() {
        return time;
    }

    public void setTime(int time) {
        this.time = time;
    }
}
