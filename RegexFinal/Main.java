import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Main {
    /**
     * SSN <br>
     * format: aaa-bb-cccc <br>
     * - aaa goes from 0 to 999 <br>
     * - bb goes from 01 to 99 (EC) <br>
     * - cccc goes from 0001 to 9999 (EC) <br>
     * <a href="https://www.ssa.gov/history/ssn/geocard.html#:~:text=The%20nine%2Ddigit%20SSN%20is,digits%20is%20the%20Serial%20Number">Rules</a href>
     *
     * @param matcherString
     * @return
     */
    public static int regex1SSN(String matcherString) { //(000-999)-(01-99)-(0001-9999)
        Pattern pattern = Pattern.compile("^\\d{3}(?<separator>(-|\\s?))((?!01)\\d\\d)\\k<separator>(((?!0001))\\d\\d\\d\\d)$"); //neg lookahead https://www.regular-expressions.info/lookaround.html
        return doRegex(pattern.matcher(matcherString.trim()), matcherString);
    }

    /**
     * US Phone <br>
     * 1 (###) ### - #### <br>
     * - As long as there are 10 #'s, it'll pass. <br>
     * - "1" is optional <br>
     * - either "()" are optional <br>
     * - spaces are optional <br>
     * - "-" is optional <br>
     *
     * @param matcherString
     * @return
     */
    public static int regex2USPhone(String matcherString) {
        Pattern pattern = Pattern.compile("^[1]?[\\s]?[(]?\\d{3}[)]?[\\s]?\\d{3}[-|\\s]{0,3}\\d{4}$");
        return doRegex(pattern.matcher(matcherString.trim()), matcherString);
    }

    /**
     * Email <br>
     * format: name@domain.topDomain <br>
     * - All parts must exist to pass. <br>
     * - name: Can't start or end with special character. Can't have 2 consecutive special characters. <br>
     * - domain: Any letter or number String <br>
     * - topDomain: Any letter or number String, but can't end with a "." <br>
     * <a href="https://knowledge.validity.com/hc/en-us/articles/220560587-What-are-the-rules-for-email-address-syntax-">Rules</a>
     *
     * @param matcherString
     * @return
     */
    public static int regex3Email(String matcherString) {
        String name = "(?!^[^a-z0-9].*@.*$)(?!^.*[^a-z0-9]@.*$)(?!.*[^a-z0-9]{2,}.*@.*)(?!^[^a-z0-9]@.*$).+";
        String domain = "[a-z0-9]+\\.";
        String topDomain = "[a-z0-9.\\.]*(?!\\.)[a-z0-9]";
        Pattern pattern = Pattern.compile("^" + name + "@" + domain + topDomain + "$", Pattern.CASE_INSENSITIVE);
        return doRegex(pattern.matcher(matcherString.trim()), matcherString);
    }

    /**
     * Name <br>
     * Last Name, First Name, M
     * - All parts must exist to pass
     * - Last Name & First Name: Letters, spaces, "-", "'"
     * - M: (Middle Initial) One Letter only
     *
     * @param matcherString
     * @return
     */
    public static int regex4NameOnRoster(String matcherString) {
        String name = "[A-Za-z.\\s-'()]{1,}";
        String comma = "[\\s]{0,}[,][\\s]{0,}";
        String initials = "[A-Za-z.\\s-'()]{1}";
        Pattern pattern = Pattern.compile("^" + name + comma + name + comma + initials + "$");
        return doRegex(pattern.matcher(matcherString.trim()), matcherString);
    }

    /**
     * Date <br>
     * format: MM-DD-YYYY <br>
     * - MM: Can be 01 to 12, 0 digit optional <br>
     * - DD: Can be 01 to 31, 0 digit optional <br>
     * - YYYY: Can be 0 or up, doesn't have to be 4 digits <br>
     * Uses validateDate() to check
     *
     * @param matcherString
     * @return
     */
    public static int regex5Date(String matcherString) {
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

    /**
     * Helper to validate if date exists. <br>
     * Adapted from <a href="https://www.scaler.com/topics/date-validation-in-javascript/">Code</a>
     *
     * @param month
     * @param day
     * @param year
     * @return
     */
    private static int validateDate(int month, int day, int year) {
        int[] ListofDays = {31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};
        if (month == 1 || month > 2) {
            if (day > ListofDays[month - 1]) {
                return 0;
            }
        } else if (month == 2) {
            boolean leapYear = false;
            if ((year % 4 == 0 && year % 100 != 0) || year % 400 == 0) leapYear = true;
            if (!leapYear && (day >= 29)) return 0;
            else if (leapYear && (day > 29)) {
                return 0;
            }
        }
        return 1;
    }

    /**
     * House address <br>
     * houseNumber streetName streetAbb <br>
     * - All parts must exist to pass, each separated by a space. <br>
     * - houseNumber: Numbers only <br>
     * - streetName: Letters and Numbers only <br>
     * - streetAbb: "street", "road", "boulevard", "avenue" and their abbreviations. "." optional.
     *
     * @param matcherString
     * @return
     */
    public static int regex6HouseAddress(String matcherString) {
        String houseNumber = "[0-9]+\\s+"; //street number
        String streetName = "[A-Za-z0-9..\\s]+\\s+";
        String streetAbb = "(street|st[.]?|road|rd[.]?|boulevard|blvd[.]?|avenue|ave[.]?)";
        Pattern pattern = Pattern.compile("^" + houseNumber + streetName + streetAbb + "$", Pattern.CASE_INSENSITIVE);
        return doRegex(pattern.matcher(matcherString.trim()), matcherString);
    }

    /**
     * House Address Continued <br>
     * format: city, state zip <br>
     * - All parts must exist to pass. <br>
     * - city: Letters <br>
     * - state: Pair of Letters of the real states (EC) <br>
     * - zip: 5 digit number, optionally "-" and 4 more digits.
     *
     * @param matcherString
     * @return
     */
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

    /**
     * Military Time <br>
     * format: aabb <br>
     * - aa: 00 to 23 <br>
     * - bb: 00 to 59 <br>
     * - No spacing allowed <br>
     * - Must be 4 digits total
     *
     * @param matcherString
     * @return
     */
    public static int regex8MilTime(String matcherString) {
        Pattern pattern = Pattern.compile("^([01][0-9]|2[0-3])([0-5][0-9])$");
        return doRegex(pattern.matcher(matcherString.trim()), matcherString);
    }

    /**
     * US Currency <br>
     * format: $dollars.cents <br>
     * - "$": optional <br>
     * - dollars: comma separated per 3 digits, no commas, or nothing <br>
     * - ".": optional (meaning no cents) <br>
     * - cents: 2 digits, or nothing <br>
     *
     * @param matcherString
     * @return
     */
    public static int regex9USMoney(String matcherString) {
        Pattern pattern = Pattern.compile("^\\$?(([0-9]{1,3})(,[0-9]{3})*|[0-9]+)?(\\.[0-9]{2}|)+$");
        return doRegex(pattern.matcher(matcherString.trim()), matcherString);
    }

    //I started using https://regex101.com/ to debug

    /**
     * URL <br>
     * format: protocol://domain.subdomain:port/path?query=arg <br>
     * - protocol: "http" or "https", optional if "://" doesn't exist <br>
     * - domain: Letters and "_.+!*'()" <br>
     * - subdomain: "." followed by letters and "_.+!*'()". Must have one or more. <br>
     * - port: ":" followed by numbers. Optional.  <br>
     * - path: "/" followed by letters. Must have one or more. Last path can by "/#" followed by letters. <br>
     * - query: "?" followed by letters "=" letters. Optional. Multiple is seperated by "&" instead of "?" again. <br>
     * <a href="https://www.ibm.com/docs/en/cics-ts/5.2?topic=concepts-components-url">Rules1</a>, <a href="https://support.optimizely.com/hc/en-us/articles/4413199990669-Characters-allowed-in-URLs">Rules2</a>
     *
     * @param matcherString
     * @return
     */
    public static int regex10URL(String matcherString) { //(?(?=regex)then|else)
        Pattern pattern = Pattern.compile("^((http[s]?://.+)|(?!://))(([a-z0-9$\\-_.+!*'()]+)(\\.[a-z0-9$\\-_.+!*'()]+)+)((:[0-9]+)|(?!:))((/([a-z0-9$\\-_.+!*'()]+))*(/#([a-z0-9$\\-_.+!*'()]+))?((\\?(([^&]+)=([^&]+))(&([^&]+)=([^&]+))*)?|(?!\\?)))?/?$", Pattern.CASE_INSENSITIVE);
        return doRegex(pattern.matcher(matcherString.trim()), matcherString);
    }

    //I started using https://regex101.com/ to debug

    /**
     * Password <br>
     * - Must be 10+ chars <br>
     * - Must include a lower case letter <br>
     * - Must include an upper case letter <br>
     * - Can't have 4+ lower cases consecutively <br>
     * - Must include a number <br>
     * - Must include a special char
     *
     * @param matcherString
     * @return
     */
    public static int regex11Password(String matcherString) {
        Pattern pattern = Pattern.compile("^(?=.*[A-Z]+)(?=.*[a-z]+)(?=.*[^A-Za-z0-9]+)(?=.*[a-z]+)(?=.*\\d+)(?!.*[a-z]{4,}).{10,}$");
        return doRegex(pattern.matcher(matcherString.trim()), matcherString);
    }

    /**
     * Odd word ending with "ion" <br>
     * - Must have odd amount of chars <br>
     * - Only letters, "'", "-" allowed <br>
     * - must end with ion
     *
     * @param matcherString
     * @return
     */
    public static int regex12IonOdd(String matcherString) { //https://stackoverflow.com/questions/3384207/match-string-of-odd-length
        Pattern pattern = Pattern.compile("^([a-z.'-]{2})*ion$", Pattern.CASE_INSENSITIVE);
        return doRegex(pattern.matcher(matcherString.trim()), matcherString);
    }

    /**
     * Helper to use regex. <br>
     * From Java tutorials.
     *
     * @param matcher
     * @param matcherString
     * @return
     */
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
