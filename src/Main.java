import java.util.Scanner;
import java.util.concurrent.Callable;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.io.Console;

public class Main {
    private static final String string_SSN = "SSN";
    private static final String string_USPhone = "US Phone #";
    private static final String string_Email = "Email Address";
    private static final String string_NameOnRoster = "Name on a class roster (\"Last name, First name, MI\")";
    private static final String string_Date = "Date (\"MM-DD-YYYY\" or \"MM/DD/YYYY\")";
    private static final Scanner in = new Scanner(System.in);

    public static void main(String[] args) {
        while (true) {
            int response = getWhatToScreen();
            if (response == -1) {
                System.exit(0);
            }
        }
    }

    private static int getWhatToScreen() {
        //prompt
        System.out.println("\nSelect what to screen: ");
        System.out.println("1) " + string_SSN);
        System.out.println("2) " + string_USPhone);
        System.out.println("3) " + string_Email);
        System.out.println("4) " + string_NameOnRoster);
        System.out.println("5) " + string_Date);
        System.out.println("0) Exit");
        System.out.print(">");
        int selection = Integer.parseInt(in.nextLine());

        //get user in & doRegex
        int response = 1;
        String s = "";
        while (response == 1) { //TODO make a seperate func each
            switch (selection) { //neg lookahead: (?!<chars>) https://www.regular-expressions.info/lookaround.html
                case 1: //TODO EC
                    s = promptForString("a " + string_SSN);
                    String ssnSeperator = "[- | \s]{0,3}?";
                    response = doRegex(
                            "\\d{3}" + ssnSeperator + "\\d{2}" + ssnSeperator + "\\d{4}(?!.)",
                            s //s.length() == 11 || s.length() == 9 ? s : ""
                    );
                    break;
                case 2: //TODO EC
                    s = promptForString("a " + string_USPhone);
                    response = doRegex(
                            "[(]?\\d{3}[)]?[\\s]?\\d{3}[-|\\s]{0,3}\\d{4}(?!.)",
                            s //s.length() <= 10 && s.length() >= 16 ? s : ""
                    );
                    break;
                case 3:
                    s = promptForString("an " + string_Email);
                    response = doRegex(
                            "[\\S]{1,}[@][\\S]{1,}[.][\\S]{1,}",
                            s
                    );
                    break;
                case 4:
                    s = promptForString("a " + string_NameOnRoster);
                    String name = "[---\\s-\\sA-Za-z]{1,}";
                    String comma = "[\\s]{0,}[,][\\s]{0,}";
                    String initials = "[---\\s-\\sA-Za-z]{1}(?!.)";
                    response = doRegex(
                            name + comma + name + comma + initials,
                            s
                    );
                    break;
                case 5: //TODO check valid dates
                    s = promptForString("a " + string_Date);
                    String monthday = "[0-9]{2}";
                    String seperate = "[- | /]";
                    String year = "[0-9]{2}";
                    response = doRegex(
                            monthday + seperate + monthday + year + "(?!.)",
                            s
                    );
                    break;
                default:
                    response = -1;
                    break;
            }
        }

        return response;
    }

    private static int doRegex(String patternString, String matcherString) {
        Pattern pattern = Pattern.compile(patternString);
        Matcher matcher = pattern.matcher(matcherString);

        boolean found = false;
        while (matcher.find()) {
            System.out.printf("Found match \"%s\" from index %d to %d.%n",
                    matcher.group(), matcher.start(), matcher.end());
            found = true;
        }
        if (!found) {
            System.out.println("No match found.");
        }

        return promptRetry();
    }

    private static String promptForString(String message) {
        System.out.printf("\nEnter %s\n>", message);
        return in.nextLine().trim();
    }

    private static int promptRetry() {
        System.out.print("\nRetry? (Y or N)\n>");
        return in.nextLine().trim().equalsIgnoreCase("y") ? 1 : 0;
    }
}
