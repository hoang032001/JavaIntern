import java.util.Comparator;

public class SortName implements Comparator {
    @Override
    public int compare(Object o1, Object o2) {
        User uu1 = (User) o1;
        User uu2 = (User) o2;

        return uu1.getName().compareToIgnoreCase(uu2.getName());
    }
}
