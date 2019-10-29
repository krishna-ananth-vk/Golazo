package tech.the_code_builder.golazo;

import java.util.Comparator;

public class CustomComparator implements Comparator<User> {

    @Override
    public int compare(User user, User t1) {
        return (Integer)user.getPoints().compareTo(t1.getPoints());
    }
}
