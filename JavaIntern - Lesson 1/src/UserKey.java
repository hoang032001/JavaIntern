import java.util.Objects;

public class UserKey {
    private String name;
    private Integer Age;
    public UserKey(){}
    public UserKey(String name, Integer age) {
        this.name = name;
        Age = age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getAge() {
        return Age;
    }

    public void setAge(Integer age) {
        Age = age;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserKey userKey = (UserKey) o;
        return Objects.equals(name, userKey.name) && Objects.equals(Age, userKey.Age);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, Age);
    }
}
