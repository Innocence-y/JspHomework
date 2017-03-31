package beans;

/**
 * Created by JiYongGuang on 2017/3/8.
 */
public class Admin {
    //user.setHeadUrl(String.format("http://images.nowcoder.com/head/%dt.png", random.nextInt(1000)));
    private String username;
    private String password;
    private int age;
    private int gender;
    private int key;
    private String headUrl;

    public Admin(String username, String password, int age, int gender, int key, String headUrl) {
        this.username = username;
        this.password = password;
        this.age = age;
        this.gender = gender;
        this.key = key;
        this.headUrl = headUrl;
    }

    public Admin() {
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public int getGender() {
        return gender;
    }

    public void setGender(int gender) {
        this.gender = gender;
    }

    public int getKey() {
        return key;
    }

    public void setKey(int key) {
        this.key = key;
    }

    public String getHeadUrl() {
        return headUrl;
    }

    public void setHeadUrl(String headUrl) {
        this.headUrl = headUrl;
    }
}
