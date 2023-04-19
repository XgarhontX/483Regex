import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.time.format.DateTimeFormatter;
import java.util.Locale;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class MainRewrite {
    //https://www.ssa.gov/history/ssn/geocard.html#:~:text=The%20nine%2Ddigit%20SSN%20is,digits%20is%20the%20Serial%20Number
    public static int regex1SSN(String matcherString) { //(000-999)-(01-99)-(0001-9999)
        Pattern pattern = Pattern.compile("^\\d{3}(?<separator>(-|\\s?))((?!01)\\d\\d)\\k<separator>(((?!0001))\\d\\d\\d\\d)$"); //neg lookahead https://www.regular-expressions.info/lookaround.html
        return doRegex(pattern.matcher(matcherString.trim()), matcherString);
    }

    public static int regex2USPhone(String matcherString) {
        Pattern pattern = Pattern.compile("^[1]?[\\s]?[(]?\\d{3}[)]?[\\s]?\\d{3}[-|\\s]{0,3}\\d{4}$");
        return doRegex(pattern.matcher(matcherString.trim()), matcherString);
    }

    public static int regex3Email(String matcherString) { //TODO https://knowledge.validity.com/hc/en-us/articles/220560587-What-are-the-rules-for-email-address-syntax-
        String name = "(?!^[^a-z0-9].*@.*$)(?!^.*[^a-z0-9]@.*$)(?!.*[^a-z0-9]{2,}.*@.*)(?!^[^a-z0-9]@.*$).+";
        String domain = "[a-z0-9]+\\.";
        String topDomain = "[a-z0-9.\\.]+(?!\\.)[a-z0-9]";
        Pattern pattern = Pattern.compile("^" + name + "@" + domain + topDomain + "$", Pattern.CASE_INSENSITIVE);
        System.out.println(pattern.pattern());
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
        String month = "(?<month>0[1-9]|1[12]|[1-9])";
        String separate = "(?<separator>[-|/])";
        String day = "(?<day>0[1-9]|[12][0-9]|3[01]|[1-9])";
        String separate1 = "\\k<separator>";
        String year = "(?<year>[0-9]+)";
        Pattern pattern = Pattern.compile("^" + month + separate + day + separate1 + year + "$"); //MM-DD-YYYY
        //Validate Date
        Matcher matcher = pattern.matcher(matcherString.trim());
        if (doRegex(matcher, matcherString) == 1) {
            return validateDate(
                    Integer.parseInt(matcher.group("month")),
                    Integer.parseInt(matcher.group("day")),
                    Integer.parseInt(matcher.group("year"))
            );
        }
        return 0;
    }

    private static int validateDate(int month, int day, int year) { //https://www.scaler.com/topics/date-validation-in-javascript/
        int[] ListofDays = {31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};
        if (month == 1 || month > 2) {
            if (day > ListofDays[month - 1]) {
                return 0;
            }
        } else if (month == 2) {
            boolean leapYear = false;
            if ((year % 4 == 0 && year % 100 != 0) || year % 400 == 0) leapYear = true;
            if (!leapYear && (day >= 29)) return 0;
            else
            if (leapYear && (day > 29)) {
                return 0;
            }
        }
        return 1;
    }

    public static int regex6HouseAddress(String matcherString) {
        String houseNumber = "[0-9]+\\s+"; //street number
        String streetName = "[A-Za-z0-9..\\s]+\\s+";
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
        String zip = "[0-9]{5}([-][0-9]{4})?";
        Pattern pattern = Pattern.compile("^" + city + comma + state + spacing + zip + "$");
        return doRegex(pattern.matcher(matcherString.trim()), matcherString);
    }

    public static int regex8MilTime(String matcherString) {
        Pattern pattern = Pattern.compile("^([01][0-9]|2[0-3])([0-5][0-9])$");
        return doRegex(pattern.matcher(matcherString.trim()), matcherString);
    }

    public static int regex9USMoney(String matcherString) {
        Pattern pattern = Pattern.compile("^\\$?(([0-9]{1,3})(,[0-9]{3})*|[0-9]+)(\\.[0-9]{2}|)+$");
        return doRegex(pattern.matcher(matcherString.trim()), matcherString);
    }

    public static int regex10URL(String matcherString) { //(?(?=regex)then|else)
        String protocol = "((?=.*{4}http)[s]?://)|(?!.\\.)(?=\\.*).+";
        String domain = ".*";
        Pattern pattern = Pattern.compile("^" + protocol + domain + "$", Pattern.CASE_INSENSITIVE);
        return doRegex(pattern.matcher(matcherString.trim()), matcherString);
    }

    public static int regex11Password(String matcherString) {
        Pattern pattern = Pattern.compile("^(?=.*[A-Z]+)(?=.*[a-z]+)(?=.*[^A-Za-z0-9]+)(?=.*[a-z]+)(?=.*\\d+)(?!.*[a-z]{4,}).{10,}$");
        return doRegex(pattern.matcher(matcherString.trim()), matcherString);
    }

    public static int regex12IonOdd(String matcherString) { //https://stackoverflow.com/questions/3384207/match-string-of-odd-length
        Pattern pattern = Pattern.compile("^(..)*[i][o][n]$", Pattern.CASE_INSENSITIVE);
        return doRegex(pattern.matcher(matcherString.trim()), matcherString);
    }

    public static int doRegex(Matcher matcher, String matcherString) {
        int found = 0;
        if (matcher.find()) {
            System.out.printf("\"%s\": Found match \"%s\" from index %d to %d.\n",
                    matcherString, matcher.group(), matcher.start(), matcher.end());
            found++;
        }
//        while (matcher.find()) {
//            System.out.printf("\"%s\": Found match \"%s\" from index %d to %d.\n",
//                    matcherString, matcher.group(), matcher.start(), matcher.end());
//            found++;
//        }
        if (found == 0) {
            System.out.printf("\"%s\": No match found.\n", matcherString);
        }

        return found;
    }
}
