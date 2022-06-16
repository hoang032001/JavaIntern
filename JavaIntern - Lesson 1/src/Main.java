
import jdk.nashorn.internal.ir.annotations.Ignore;

import java.io.*;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.concurrent.ThreadLocalRandom;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class Main {

    private static final int maxCharactersName = 3;
    private static final int maxAge = 22;
    private static final int minAge = 20;

    public static User createUser() {
        User user = new User();
        // create a string of all characters
        String[] alphabet = {"f", "r", "u", "i", "t"};

        // create an object of Random class
        Random random = new Random();

        // create random string builder
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < maxCharactersName; i++) {

            // generate random index number
            int index = random.nextInt(alphabet.length);

            // get character specified by index
            // from the string
            String randomChar = alphabet[index];

            // append the character to string builder
            sb.append(randomChar);

            //remove the char has appended
            List<String> removedElement = new ArrayList<>(Arrays.asList(alphabet));
            removedElement.remove(index);
            alphabet = removedElement.toArray(new String[0]);
        }
        user.setName(sb.toString());
        user.setAge(ThreadLocalRandom.current().nextInt(minAge, maxAge + 1));

        //random gender
        if (random.nextBoolean()) {
            user.setSex("Male");
        } else {
            user.setSex("Female");
        }

        return user;
    }

    public static void createFile(ArrayList<User> list) throws IOException {
        //Release text file for user list

        //the data in txt file will be encoded
//        try {
//            FileOutputStream fos = new FileOutputStream("D:/t.txt");
//            ObjectOutputStream oos = new ObjectOutputStream(fos);
//            oos.writeObject("abc");
//            oos.close();
//        }catch (Exception e){
//
//        }
        //read data from txt file and decode
//        try{
//            FileInputStream readData = new FileInputStream("D:/t.txt");
//            ObjectInputStream readStream = new ObjectInputStream(readData);
//
//            ArrayList<User> list2 = (ArrayList<User>) readStream.readObject();
//            readStream.close();
//            System.out.println(list2.toString());
//        }catch (Exception e) {
//            e.printStackTrace();
//        }

        //release data normally
        FileWriter writer = new FileWriter("D:/UserList.txt");
        try {
            for (User obj : list) {
                writer.write(obj.toString() + System.lineSeparator());
            }
            writer.close();
        } catch (Exception ignored) {

        } finally {
            try {
                if(writer != null) {
                    writer.close();
                }
            }catch (Exception e){ }
        }
    }

    //print List
    public static void printUserList(ArrayList<User> list) {
        list.forEach(obj -> {
            System.out.println("============================");
            System.out.println("User ID: " + obj.getId());
            System.out.println("User Name: " + obj.getName());
            System.out.println("Age: " + obj.getAge());
            System.out.println("Sex: " + obj.getSex());
        });
    }

    //sort user name
    public static void sortUserName(ArrayList<User> list) {
        list.sort(Comparator.comparing(User::getName));
        printUserList(list);
    }

    //count male and female in list
    public static void countMaleAndFemale(ArrayList<User> userLists) {
//        Map<String, Integer> hm = new HashMap<>();
//        for (User obj : userLists) {
//            if (!hm.containsKey(obj.getSex())) {
//                hm.put(obj.getSex(), 1);
//            } else {
//                hm.put(obj.getSex(), hm.get(obj.getSex()) + 1);
//            }
//        }
//        System.out.println(hm);

        //count for total Male in list - the second way
        int countMale, countFemale;
        countMale = (int) userLists.stream().filter(i -> i.getSex().contains("Male")).count();
        //Male - array = Female
        countFemale = Math.abs(countMale - userLists.size());
        System.out.println("Male: "+countMale+" Female: "+countFemale);
    }

    //collect user has same name in the list
    public static void collectSameName(ArrayList<User> list) {

        Map<String, List<User>> map = new HashMap<>();

        list.forEach(user -> {
            //Use tempList for keep the first data and add more object,
            // then this new tempList will add to the old data
            List<User> tempList;
            if (map.containsKey(user.getName())) {
                tempList = map.get(user.getName());
            }else{
                tempList = new ArrayList<>();
                map.put(user.getName(), tempList);
            }
            tempList.add(user);
        });
        //Print list
//        for (String i : map.keySet()) {
//            System.out.println(map.get(i));
//        }
        map.entrySet().stream().forEach(System.out::println);
    }
    @FunctionalInterface
    interface INameAge{
        String add(User u);
    }
    //collect user has same name and age in the list
    public static void collectSameNameSameAge(ArrayList<User> list) {
        Map<Object, List<User>> map;
        map = list.stream().collect(Collectors.groupingBy(p -> Arrays.asList(p.getName(), p.getAge())));
//        map = list.stream().collect(Collectors.groupingBy(User::getName, Collectors.groupingBy(User::getAge)));
        map.entrySet().stream().forEach(System.out::println);
    }

    public static void collectSameNameSameAge2(ArrayList<User> list) {
        UserKey userKey = new UserKey();
        Map<UserKey, List<User>> map = list.stream().collect(Collectors.groupingBy(p -> new UserKey(p.getName(), p.getAge())));
        map.entrySet().forEach(System.out::println);
    }


    //collect all user name in list
    public static void collectUserName(ArrayList<User> userLists){
        userLists.forEach(i -> System.out.println("User Name: "+i.getName()));
    }

    public static void main(String[] args) {
        ArrayList<User> list = new ArrayList<>();

        //create user list
//        for(int i=1; i <= 300; i++) {
//            User user = createUser();
//            user.setId(i);
//            list.add(user);
//        }

        IntStream.range(1, 301).forEach(i -> {
            User user = createUser();
            user.setId(i);
            list.add(user);
        });

        //sort user id
//        Iterator<User> it = list.iterator();
//        while (it.hasNext()){
//            System.out.println(it.next());
//        }
        //Collections.sort(list, Collections.reverseOrder());

        //sort name and sort age
//        list.sort(Comparator.comparing(User::getName)
//                .thenComparingInt(User::getAge));
//        for(User u:list){
//            System.out.println(u);
//        }


        //print user list
        //printUserList(list);

        //collect user name in list
        //collectUserName(list);

        //Create User list to txt file
        //createFile(list);

        //print User list after sort name
        //sortUserName(list);

        //count male and female
        //countMaleAndFemale(list);

        //collect users has same name in a list
        //collectSameName(list);

        //collect users has same name and same age in a list
        //collectSameNameSameAge(list);

        //the second way for collect users has same name and same age
        //collectSameNameSameAge2(list);

    }
}
