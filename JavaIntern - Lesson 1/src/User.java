import java.util.Objects;

public class User{
    private String name;
    private String sex;
    private int id;
    private int age;

    public User(String name, int age) {
        this.name = name;
        this.age = age;
    }
    public User(){}

    public void addNameAge(String name, int age) {
        this.name = name;
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String toString() {
        return "User{" +
                "name='" + this.name + '\'' +
                ", sex='" + this.sex + '\'' +
                ", id=" + this.id +
                ", age=" + this.age +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return age == user.age && Objects.equals(name, user.name);

//        return name.equals(user.getName());
    }

    @Override
    public int hashCode() {
         return Objects.hash(name, age);
//        return name.hashCode();
    }
}
