import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class MainRewrite {
    public static int regex1SSN(String matcherString) { //TODO check consistent -'s or spaces?
        String seperator = "[- | \\s]*";
        return doRegex("^\\d{3}" + seperator + "\\d{2}" + seperator + "\\d{4}$",
                matcherString);
    }

    public static int regex2USPhone(String matcherString) {
        return doRegex("^[1]?[\\s]?[(]?\\d{3}[)]?[\\s]?\\d{3}[-|\\s]{0,3}\\d{4}$",
                matcherString);
    }

    public static int regex3Email(String matcherString) {
        return doRegex("^[\\S]{1,}[@][\\S]{1,}[.][\\S]{1,}$",
                matcherString);
    }

    public static int regex4NameOnRoster(String matcherString) {
        String name = "[A-Za-z.\\s-'()]{1,}";
        String comma = "[\\s]{0,}[,][\\s]{0,}";
        String initials = "[A-Za-z.\\s-'()]{1}";
        return doRegex("^" + name + comma + name + comma + initials + "$",
                matcherString);
    }

    public static int regex5Date(String matcherString) { //TODO helper to valid date
        String monthday = "[0-9]{2}";
        String seperate = "[- | /]";
        String year = "[0-9]{2}";
        return doRegex(monthday + seperate + monthday + year + "(?!.)",
                matcherString);
    }

    public static int regex6CityState(String matcherString) {
        String city = "[A-Za-z]{1,}";
        String comma = "[\\s]*[,][\\s]*";
        String state = "([A-Za-z][A-Za-z])";
        String spacing = "[\\s]*";
        String zip = "[0-9]{5}([-][0-9]+)?";
        return doRegex(city + comma + state + spacing + zip + "(?!.)",
                matcherString);
    }

//    public static int regex12Ion(String matcherString) {
//        return doRegex("",
//                matcherString);
//    }

    public static int doRegex(String patternString, String matcherString) {
        Pattern pattern = Pattern.compile(patternString);
        Matcher matcher = pattern.matcher(matcherString.trim());

        int found = 0;
        while (matcher.find()) {
            System.out.printf("\"%s\": Found match \"%s\" from index %d to %d.\n",
                    matcherString, matcher.group(), matcher.start(), matcher.end());
            found++;
        }
        if (found == 0) {
            System.out.printf("\"%s\": No match found.\n", matcherString);
        }

        return found;
    }
}
