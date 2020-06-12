package sc.train.storage_6_6;

public class Stuff {
    private String mName;
    private String mAge;
    private String mApartment;

    public Stuff(String name, String age, String apart){
        this.mAge = age;
        this.mName = name;
        this.mApartment = apart;
    }

    public String getAge() {
        return mAge;
    }

    public String getName() {
        return mName;
    }

    public String getApartment() {
        return mApartment;
    }
}
