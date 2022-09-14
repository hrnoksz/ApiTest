package day05_POJO;


public class Spartan {
/*
    {
        "id": 63,
            "name": "Clayton",
            "gender": "Male",
            "phone": 1782167106
    }
 */
    private int id;
    private String name;
    private String gender;
    private long phone;

    public Spartan() {

    }

    public int getId(){
        return this.id;
    }
    public void setId(int id){
        this.id = id;
    }

    public String getName(){
        return this.name;
    }
    public void setName(String name){
        this.name = name;
    }

    public String getGender(){
        return this.gender;
    }
    public void setGender(String gender){
        this.gender = gender;
    }

    public long getPhone(){
        return this.phone;
    }
    public void setPhone(long phone){
        this.phone = phone;
    }

    public String toString() {
        return "Spartan{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", gender='" + gender + '\'' +
                ", phone=" + phone +
                '}';
    }
}
