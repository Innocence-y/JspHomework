package beans;

/**
 * Created by JiYongGuang on 2017/2/27.
 */
public class User {
    //user.setHeadUrl(String.format("http://images.nowcoder.com/head/%dt.png", random.nextInt(1000)));
    private int id;//用户ID
    private String username;//用户的用户名
    private String password;//用户的密码
    private int age;//用户的年龄
    private int gender;//用户的性别
    private int key;//判别是否是管理员的键值
    private String headUrl;//用户的头像

    public User(int id, String username, String password, int age, int gender, int key, String headUrl) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.age = age;
        this.gender = gender;
        this.key = key;
        this.headUrl = headUrl;
    }

    public User() {
    }

    /**
     * Getter for property 'id'.
     *
     * @return Value for property 'id'.
     */
    public int getId() {
        return id;
    }

    /**
     * Setter for property 'id'.
     *
     * @param id Value to set for property 'id'.
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * Getter for property 'username'.
     *
     * @return Value for property 'username'.
     */
    public String getUsername() {
        return username;
    }

    /**
     * Setter for property 'username'.
     *
     * @param username Value to set for property 'username'.
     */
    public void setUsername(String username) {
        this.username = username;
    }

    /**
     * Getter for property 'password'.
     *
     * @return Value for property 'password'.
     */
    public String getPassword() {
        return password;
    }

    /**
     * Setter for property 'password'.
     *
     * @param password Value to set for property 'password'.
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * Getter for property 'age'.
     *
     * @return Value for property 'age'.
     */
    public int getAge() {
        return age;
    }

    /**
     * Setter for property 'age'.
     *
     * @param age Value to set for property 'age'.
     */
    public void setAge(int age) {
        this.age = age;
    }

    /**
     * Getter for property 'gender'.
     *
     * @return Value for property 'gender'.
     */
    public int getGender() {
        return gender;
    }

    /**
     * Setter for property 'gender'.
     *
     * @param gender Value to set for property 'gender'.
     */
    public void setGender(int gender) {
        this.gender = gender;
    }

    /**
     * Getter for property 'key'.
     *
     * @return Value for property 'key'.
     */
    public int getKey() {
        return key;
    }

    /**
     * Setter for property 'key'.
     *
     * @param key Value to set for property 'key'.
     */
    public void setKey(int key) {
        this.key = key;
    }

    /**
     * Getter for property 'headUrl'.
     *
     * @return Value for property 'headUrl'.
     */
    public String getHeadUrl() {
        return headUrl;
    }

    /**
     * Setter for property 'headUrl'.
     *
     * @param headUrl Value to set for property 'headUrl'.
     */
    public void setHeadUrl(String headUrl) {
        this.headUrl = headUrl;
    }
}
