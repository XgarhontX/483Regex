import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class MainRewrite {
    public static int regex1SSN(String matcherString) { //TODO check consistent -'s or spaces?
        String seperator = "[- | \\s]*";
        Pattern pattern = Pattern.compile("^\\d{3}" + seperator + "\\d{2}" + seperator + "\\d{4}$");
        return doRegex(pattern.matcher(matcherString.trim()), matcherString);
    }

    public static int regex2USPhone(String matcherString) {
        Pattern pattern = Pattern.compile("^[1]?[\\s]?[(]?\\d{3}[)]?[\\s]?\\d{3}[-|\\s]{0,3}\\d{4}$");
        return doRegex(pattern.matcher(matcherString.trim()), matcherString);
    }

    public static int regex3Email(String matcherString) { //TODO https://knowledge.validity.com/hc/en-us/articles/220560587-What-are-the-rules-for-email-address-syntax-
        Pattern pattern = Pattern.compile("^[\\S]{1,}[@][\\S]{1,}[.][\\S]{1,}$");
        return doRegex(pattern.matcher(matcherString.trim()), matcherString);
    }

    public static int regex4NameOnRoster(String matcherString) {
        String name = "[A-Za-z.\\s-'()]{1,}";
        String comma = "[\\s]{0,}[,][\\s]{0,}";
        String initials = "[A-Za-z.\\s-'()]{1}";
        Pattern pattern = Pattern.compile("^" + name + comma + name + comma + initials + "$");
        return doRegex(pattern.matcher(matcherString.trim()), matcherString);
    }

    public static int regex5Date(String matcherString) { //TODO helper to valid date
        String month = "(0[1-9]|1[12]|[0-9])";
        String separate = "(?<separator>[- | /])";
        String day = "(0[1-9])";
        String separate1 = "\\k<separator>";
        String year = "[0-9]{4}";
        Pattern pattern = Pattern.compile("^" + month + separate + day + separate1 + year + "$"); //MM-DD-YYYY
        return doRegex(pattern.matcher(matcherString.trim()), matcherString);
    }

    public static int regex6HouseAddress(String matcherString) {
        String houseNumber = "[0-9]+"; //street number
        String streetName = "[A-Za-z0-9..\\s]+";
        String streetAbb = "(street|st[.]?|road|rd[.]?|boulevard|blvd[.]?|avenue|ave[.]?)";
        Pattern pattern = Pattern.compile("^" + houseNumber + streetName + streetAbb + "$", Pattern.CASE_INSENSITIVE);
        return doRegex(pattern.matcher(matcherString.trim()), matcherString);
    }

    public static int regex7CityState(String matcherString) {
        String city = "[A-Za-z]{1,}";
        String comma = "[\\s]*[,][\\s]*";
//        String state = "([A-Za-z][A-Za-z])";
        String state = "(AL|AK|AZ|AR|CA|CO|CT|DE|FL|GA|HI|ID|IL|IN|IA|KS|KY|LA|ME|MD|MA|MI|MN|MS|MO|MT|NE|NV|NH|NJ|NM|NY|NC|ND|OH|OK|OR|PA|RI|SC|SD|TN|TX|UT|VT|VA|WA|WV|WI|WY)";
        String spacing = "[\\s]*";
        String zip = "[0-9]{5}([-][0-9]+)?";
        Pattern pattern = Pattern.compile("^" + city + comma + state + spacing + zip + "$");
        return doRegex(pattern.matcher(matcherString.trim()), matcherString);
    }

    public static int regex9USMoney(String matcherString) {
        Pattern pattern = Pattern.compile("^\\$?(([0-9]{1,3})(,[0-9]{3})*|[0-9]+)(\\.[0-9]{2}|)+$");
        return doRegex(pattern.matcher(matcherString.trim()), matcherString);
    }

    public static int regex10URL(String matcherString) {
        String protocol = "(http[s]?://)?";
//        String domain = "[A-Za-z0-9..@:]*/";
        String domain = ".*";
//        String path = "[A-Za-z0-9..@:]*/";
        Pattern pattern = Pattern.compile("^" + protocol + domain + "$", Pattern.CASE_INSENSITIVE);
        return doRegex(pattern.matcher(matcherString.trim()), matcherString);
    }

    public static int regex12IonOdd(String matcherString) { //https://stackoverflow.com/questions/3384207/match-string-of-odd-length
        Pattern pattern = Pattern.compile("^(..)*[i][o][n]$", Pattern.CASE_INSENSITIVE);
        return doRegex(pattern.matcher(matcherString.trim()), matcherString);
    }



    public static int doRegex(Matcher matcher, String matcherString) {
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
