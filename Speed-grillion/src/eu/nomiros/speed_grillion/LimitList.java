package eu.nomiros.speed_grillion;

import java.util.*;
import java.util.regex.Pattern;

/**
 * Created by Nomiros on 28/05/13.
 * TODO doc
 */
public final class LimitList {
    private static final Pattern regex = Pattern.compile("\\d{1,3}(,\\d{1,3})*");


    public static List<Integer> parseString(String s) {
        if (! checkString(s)) throw new IllegalArgumentException("Input string ("+ s +") isn't a valid speed list.");
        List<Integer> list = new ArrayList<Integer>();

        s = s.replace(" ", ""); // Ignoring spaces
        while (s.length() > 0) {
            int comma = s.indexOf(',');
            int val = Integer.parseInt(s.substring(0, (comma != -1 ? comma : s.length())));
            list.add(val);
            s = s.substring(comma != -1 ? comma+1 : s.length());
        }

        return list;
    }

    public static String getString(List<Integer> limits) {
        StringBuilder sb = new StringBuilder();

        Collections.sort(limits);
        for (Integer i : limits) {
            if (sb.length() > 0)
                sb.append(", ");
            sb.append(i);
        }

        return sb.toString();
    }

    public static String standardize(String in) {
        return getString(parseString(in));
    }

    public static boolean checkString(String s) {
        return s != null && regex.matcher(s.replace(" ", "")).matches();
    }
}
