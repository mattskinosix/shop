package c.www.carovignoviva.monuments;

import java.util.Comparator;

public class ComparatorDistance implements Comparator<Monumento> {

    @Override
    public int compare(Monumento o1, Monumento o2) {
        return Float.compare(o1.getDistance(), o2.getDistance());
    }
}
