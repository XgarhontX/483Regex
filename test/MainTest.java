import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Specific regex requirements are in each method of Main.java
 * @see Main
 */
class MainTest {

    /**
     * @see Main#regex1SSN(String)
     */
    @Test
    void regex1SSN() {
        System.out.println("\nregex1SSN" + "------------------------------");
        assertEquals(1, Main.regex1SSN("123-12-1234"));
        assertEquals(1, Main.regex1SSN("123121234"));
        assertEquals(1, Main.regex1SSN("123 12 1234"));
        assertEquals(1, Main.regex1SSN("          123-12-1234"));
        assertEquals(0, Main.regex1SSN("12D-12-1234"));
        assertEquals(0, Main.regex1SSN("123-1D-1234"));
        assertEquals(0, Main.regex1SSN("123-12-12a4"));
        assertEquals(0, Main.regex1SSN("1234-12-1234"));
        assertEquals(0, Main.regex1SSN("123-123-1234"));
        assertEquals(0, Main.regex1SSN("123-12-12345"));
        assertEquals(0, Main.regex1SSN("1234121234"));
        assertEquals(0, Main.regex1SSN("1234 123 12345"));
        assertEquals(0, Main.regex1SSN("1 234-12-1234"));
        assertEquals(0, Main.regex1SSN("123-121234"));
        assertEquals(0, Main.regex1SSN("12312-1234"));
        assertEquals(0, Main.regex1SSN("123-01-1234"));
        assertEquals(0, Main.regex1SSN("123-10-0001"));
    }

    /**
     * @see Main#regex2USPhone(String)
     */
    @Test
    void regex2USPhone() {
        System.out.println("\nregex2USPhone" + "------------------------------");
        assertEquals(1, Main.regex2USPhone("2531231234"));
        assertEquals(1, Main.regex2USPhone("(253)123-1234"));
        assertEquals(1, Main.regex2USPhone("(253123-1234"));
        assertEquals(1, Main.regex2USPhone("253)123-1234"));
        assertEquals(1, Main.regex2USPhone("253123-1234"));
        assertEquals(1, Main.regex2USPhone("253 123-1234"));
        assertEquals(1, Main.regex2USPhone("(253)1231234"));
        assertEquals(0, Main.regex2USPhone("(253)12341234"));
        assertEquals(0, Main.regex2USPhone("253 123 a234"));
        assertEquals(1, Main.regex2USPhone("1 253 123 1234"));
    }

    /**
     * @see Main#regex3Email(String)
     */
    @Test
    void regex3Email() {
        System.out.println("\nregex3Email" + "------------------------------");
        assertEquals(1, Main.regex3Email("bruh@gmail.com"));
        assertEquals(1, Main.regex3Email("bruh@gmail.com.gov.co"));
        assertEquals(0, Main.regex3Email("bruh@gmail.com.gov.co."));
        assertEquals(1, Main.regex3Email("b@gmail.com"));
        assertEquals(0, Main.regex3Email("bruh@gmail."));
        assertEquals(0, Main.regex3Email("@gmail.com"));
        assertEquals(0, Main.regex3Email("bruhgmail.com"));
        assertEquals(0, Main.regex3Email("bruh@.com"));
        assertEquals(0, Main.regex3Email("bruhgmail.com"));

        assertEquals(0, Main.regex3Email("&@gmail.com"));
        assertEquals(0, Main.regex3Email("!bruh@gmail.com"));
        assertEquals(0, Main.regex3Email("bruh$@gmail.com"));
        assertEquals(0, Main.regex3Email("br!!uh@gmail.com"));
    }

    /**
     * @see Main#regex4NameOnRoster(String)
     */
    @Test
    void regex4NameOnRoster() {
        System.out.println("\nregex4NameOnRoster" + "------------------------------");
        assertEquals(1, Main.regex4NameOnRoster("Last, First, M"));
        assertEquals(1, Main.regex4NameOnRoster("         Last, First, M"));
        assertEquals(1, Main.regex4NameOnRoster("Huynh, David, H"));
        assertEquals(1, Main.regex4NameOnRoster("Huynh, Gia-Bao, H"));
        assertEquals(1, Main.regex4NameOnRoster("Huynh, Gia-Bao (David), H"));
        assertEquals(1, Main.regex4NameOnRoster("Hills, De'Ante, B"));
        assertEquals(0, Main.regex4NameOnRoster("Huynh, Gia-Bao (David), Huu"));
        assertEquals(0, Main.regex4NameOnRoster("Last, First, Middle"));
        assertEquals(0, Main.regex4NameOnRoster("Last First M"));
        assertEquals(0, Main.regex4NameOnRoster("Last, First M"));
        assertEquals(0, Main.regex4NameOnRoster("Last First, M"));
    }

    /**
     * @see Main#regex5Date(String)
     */
    @Test
    void regex5Date() { //MM-DD-YYYY
        System.out.println("\nregex5Date" + "------------------------------");
        assertEquals(1, Main.regex5Date("01-01-2023"));
        assertEquals(1, Main.regex5Date("01/01/2023"));
        assertEquals(1, Main.regex5Date("1-1-2023"));
        assertEquals(1, Main.regex5Date("1/1/2023"));
        assertEquals(0, Main.regex5Date("02/29/2001"));
        assertEquals(0, Main.regex5Date("04/31/2001"));
        assertEquals(1, Main.regex5Date("4/30/2001"));
        assertEquals(0, Main.regex5Date("13/1/2023"));
        assertEquals(0, Main.regex5Date("12/32/2023"));
        assertEquals(0, Main.regex5Date("99/99/2023"));
        assertEquals(0, Main.regex5Date("0/0/2023"));
        assertEquals(0, Main.regex5Date("1-1/2023"));
        assertEquals(0, Main.regex5Date("1/1-2023"));
    }

    /**
     * @see Main#regex6HouseAddress(String)
     */
    @Test
    void regex6HouseAddress() {
        System.out.println("\nregex6HouseAddress" + "------------------------------");
        assertEquals(1, Main.regex6HouseAddress("910 S 77nd St"));
        assertEquals(1, Main.regex6HouseAddress("910 S. 77nd St."));
        assertEquals(1, Main.regex6HouseAddress("910 S. 77nd Blvd."));
        assertEquals(1, Main.regex6HouseAddress("910 s. 77nd blvd"));
        assertEquals(1, Main.regex6HouseAddress("84 South Rock Maple Ave."));
        assertEquals(1, Main.regex6HouseAddress("84 South Rock Maple Ave."));
        assertEquals(1, Main.regex6HouseAddress("84 South Rock Maple Avenue"));
        assertEquals(0, Main.regex6HouseAddress("84 South Rock Maple Aven."));
        assertEquals(0, Main.regex6HouseAddress("South Rock Maple Ave"));
        assertEquals(0, Main.regex6HouseAddress("2134 E Cool 21St"));
        assertEquals(0, Main.regex6HouseAddress("345 road"));
        assertEquals(0, Main.regex6HouseAddress("345road st"));
    }

    /**
     * @see Main#regex7CityState(String)
     */
    @Test
    void regex7CityState() {
        System.out.println("\nregex7CityState" + "------------------------------");
        assertEquals(1, Main.regex7CityState("Tacoma, WA 12345"));
        assertEquals(1, Main.regex7CityState("Tacoma, WA 12345"));
        assertEquals(1, Main.regex7CityState("NYC, NY 12345-1234"));
        assertEquals(1, Main.regex7CityState("Tacoma, WA 98402"));
        assertEquals(1, Main.regex7CityState("Seattle, CA 71123"));
        assertEquals(0, Main.regex7CityState("Tacoma,WA 12345-"));
        assertEquals(0, Main.regex7CityState("Tacoma, WA 1234"));
        assertEquals(0, Main.regex7CityState("Tacoma, WA 123456"));
        assertEquals(0, Main.regex7CityState("Tacoma WA 12345"));
        assertEquals(0, Main.regex7CityState("Tacoma, WA 1234-1234"));
        assertEquals(0, Main.regex7CityState("Tacoma, WA 12345-12345"));
        assertEquals(0, Main.regex7CityState("Tacoma, WZ 12345"));
    }

    /**
     * @see Main#regex8MilTime(String)
     */
    @Test
    void regex8MilTime() {
        System.out.println("\nregex8MilTime" + "------------------------------");
        assertEquals(1, Main.regex8MilTime("0000"));
        assertEquals(1, Main.regex8MilTime("0500"));
        assertEquals(1, Main.regex8MilTime("1000"));
        assertEquals(1, Main.regex8MilTime("1500"));
        assertEquals(1, Main.regex8MilTime("2359"));
        assertEquals(0, Main.regex8MilTime("500"));
        assertEquals(0, Main.regex8MilTime("0060"));
        assertEquals(0, Main.regex8MilTime("2400"));
        assertEquals(0, Main.regex8MilTime("12450"));
        assertEquals(0, Main.regex8MilTime("17 30"));
    }

    /**
     * @see Main#regex9USMoney(String)
     */
    @Test
    void regex9USMoney() {
        System.out.println("\nregex9USMoney" + "------------------------------");
        assertEquals(1, Main.regex9USMoney("$123,456,789.23"));
        assertEquals(1, Main.regex9USMoney("123,456,789.23"));
        assertEquals(1, Main.regex9USMoney("$123456789.23"));
        assertEquals(0, Main.regex9USMoney("$123456,789.23"));
        assertEquals(0, Main.regex9USMoney("$123,456789.23"));
        assertEquals(1, Main.regex9USMoney("$23,456,789.23"));
        assertEquals(1, Main.regex9USMoney("$2,456,789.23"));
        assertEquals(1, Main.regex9USMoney("$456,789.23"));
        assertEquals(1, Main.regex9USMoney("$56,789.23"));
        assertEquals(1, Main.regex9USMoney("$6,789.23"));
        assertEquals(1, Main.regex9USMoney("$789.23"));
        assertEquals(1, Main.regex9USMoney("$89.23"));
        assertEquals(1, Main.regex9USMoney("$9.23"));
        assertEquals(1, Main.regex9USMoney("$0.23"));
        assertEquals(1, Main.regex9USMoney("$1,000"));
        assertEquals(0, Main.regex9USMoney("$1,000.0"));
        assertEquals(0, Main.regex9USMoney("$123,456,789.2"));
        assertEquals(0, Main.regex9USMoney("$123,45,789.23"));
        assertEquals(0, Main.regex9USMoney("$123,456,78.23"));
        assertEquals(1, Main.regex9USMoney("$0.23"));
        assertEquals(1, Main.regex9USMoney("$.23"));
        assertEquals(0, Main.regex9USMoney("$wasdw.23"));
    }

    /**
     * @see Main#regex10URL(String)
     */
    @Test
    void regex10URL() {
        System.out.println("\nregex10URL" + "------------------------------");
        assertEquals(1, Main.regex10URL("https://www.google.com"));
        assertEquals(1, Main.regex10URL("https://www.google.com/"));
        assertEquals(1, Main.regex10URL("www.google.com"));
        assertEquals(1, Main.regex10URL("www.google.com/"));
        assertEquals(1, Main.regex10URL("https://www.regular-expressions.info/quickstart.html"));
        assertEquals(1, Main.regex10URL("https://docs.oracle.com/javase/tutorial/essential/regex/bounds.html"));
        assertEquals(1, Main.regex10URL("https://canvas.uw.edu/courses/1642545/assignments/8223051"));
        assertEquals(0, Main.regex10URL("https//canvas.uw.edu/courses/1642545/assignments/8223051"));
        assertEquals(0, Main.regex10URL("https:/canvas.uw.edu/courses/1642545/assignments/8223051"));
        assertEquals(0, Main.regex10URL("https:/canvas.uw.edu/courses/1642545/assignments//8223051"));
        assertEquals(0, Main.regex10URL("https:canvas.uw.edu/courses/1642545/assignments/8223051"));
        assertEquals(0, Main.regex10URL("https:canvas.uw.edu/courses?"));
        assertEquals(0, Main.regex10URL("https://wwwgooglecom"));
        assertEquals(0, Main.regex10URL("wwwgooglecom"));
        assertEquals(0, Main.regex10URL("//:"));
        assertEquals(0, Main.regex10URL("www"));
        assertEquals(1, Main.regex10URL("google.com"));
        assertEquals(1, Main.regex10URL("https://cfb3-tcss450-labs-2021sp.herokuapp.com/doc/#api-Phish-GetPhishBlogGet"));
        assertEquals(0, Main.regex10URL("https://cfb3-tcss450-labs-2021sp.herokuapp.com/#doc/#api-Phish-GetPhishBlogGet"));
        assertEquals(1, Main.regex10URL("https://phishnet.api-docs.io/v3/blog/blog-get"));
        assertEquals(1, Main.regex10URL("https://cfb3-tcss450-labs-2021sp.herokuapp.com/phish/blog/get?message=wow"));
        assertEquals(1, Main.regex10URL("https://cfb3-tcss450-labs-2021sp.herokuapp.com/phish/blog/get?message=wow&test=2929"));
        assertEquals(0, Main.regex10URL("https://cfb3-tcss450-labs-2021sp.herokuapp.com/phish/blog/get?message="));
    }

    /**
     * @see Main#regex11Password(String)
     */
    @Test
    void regex11Password() {
        System.out.println("\nregex11Password" + "------------------------------");
        assertEquals(1, Main.regex11Password("Wasd123456!"));
        assertEquals(1, Main.regex11Password("I'mSupeRC00l"));
        assertEquals(1, Main.regex11Password("AmaZinGCoolPass018287*!,"));
        assertEquals(1, Main.regex11Password("IAm.TheM1n"));
        assertEquals(1, Main.regex11Password("IAm.TheMAXIMUMMMMMMMMMMWMWMWMmmmWW12345as"));
        assertEquals(0, Main.regex11Password("I1mSupeRC00l"));
        assertEquals(0, Main.regex11Password("I'mSupeRcool"));
        assertEquals(0, Main.regex11Password("IAm.TheM1n2343 sdfg345 346 rtrgf d56"));
        assertEquals(0, Main.regex11Password("IAm.TheM1"));
        assertEquals(0, Main.regex11Password("wasd123456!"));
        assertEquals(0, Main.regex11Password("Wasdwasdwasd123456!"));
        assertEquals(0, Main.regex11Password("1"));
        assertEquals(0, Main.regex11Password("www.google.com"));
        assertEquals(1, Main.regex11Password("www.GoogLe.com/123"));
    }

    /**
     * @see Main#regex12IonOdd(String)
     */
    @Test
    void regex12IonOdd() {
        System.out.println("\nregex12IonOdd" + "------------------------------");
        assertEquals(1, Main.regex12IonOdd("ion"));
        assertEquals(1, Main.regex12IonOdd("quion"));
        assertEquals(1, Main.regex12IonOdd("million"));
        assertEquals(1, Main.regex12IonOdd("bruhion"));
        assertEquals(1, Main.regex12IonOdd("wWOWOWOowwion"));
        assertEquals(0, Main.regex12IonOdd("Trillion"));
        assertEquals(0, Main.regex12IonOdd("POTION"));
        assertEquals(0, Main.regex12IonOdd("lion"));
        assertEquals(0, Main.regex12IonOdd("ow ion"));
        assertEquals(0, Main.regex12IonOdd("ow-ion"));
        assertEquals(0, Main.regex12IonOdd("ioningy"));
    }
}