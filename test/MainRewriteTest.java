import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class MainRewriteTest {

    @Test
    void regex1SSN() {
        System.out.println("\nregex1SSN" + "------------------------------");
        assertEquals(1, MainRewrite.regex1SSN("123-12-1234"));
        assertEquals(1, MainRewrite.regex1SSN("123121234"));
        assertEquals(1, MainRewrite.regex1SSN("123 12 1234"));
        assertEquals(1, MainRewrite.regex1SSN("          123-12-1234"));
        assertEquals(0, MainRewrite.regex1SSN("1234-12-1234"));
        assertEquals(0, MainRewrite.regex1SSN("123-123-1234"));
        assertEquals(0, MainRewrite.regex1SSN("123-12-12345"));
        assertEquals(0, MainRewrite.regex1SSN("1234121234"));
        assertEquals(0, MainRewrite.regex1SSN("1234 123 12345"));
        assertEquals(0, MainRewrite.regex1SSN("1 234-12-1234"));
    }

    @Test
    void regex2USPhone() {
        System.out.println("\nregex2USPhone" + "------------------------------");
        assertEquals(1, MainRewrite.regex2USPhone("2531231234"));
        assertEquals(1, MainRewrite.regex2USPhone("(253)123-1234"));
        assertEquals(1, MainRewrite.regex2USPhone("253123-1234"));
        assertEquals(1, MainRewrite.regex2USPhone("253 123-1234"));
        assertEquals(1, MainRewrite.regex2USPhone("(253)1231234"));
    }

    @Test
    void regex3Email() {
        System.out.println("\nregex3Email" + "------------------------------");
        assertEquals(1, MainRewrite.regex3Email("bruh@gmail.com"));
        assertEquals(0, MainRewrite.regex3Email("bruh@gmail."));
        assertEquals(0, MainRewrite.regex3Email("@gmail.com"));
        assertEquals(0, MainRewrite.regex3Email("bruhgmail.com"));
        assertEquals(0, MainRewrite.regex3Email("bruh@.com"));
    }

    @Test
    void regex4NameOnRoster() {
        System.out.println("\nregex4NameOnRoster" + "------------------------------");
        assertEquals(1, MainRewrite.regex4NameOnRoster("Last, First, M"));
        assertEquals(1, MainRewrite.regex4NameOnRoster("         Last, First, M"));
        assertEquals(1, MainRewrite.regex4NameOnRoster("Huynh, David, H"));
        assertEquals(1, MainRewrite.regex4NameOnRoster("Huynh, Gia-Bao, H"));
        assertEquals(1, MainRewrite.regex4NameOnRoster("Huynh, Gia-Bao (David), H"));
        assertEquals(1, MainRewrite.regex4NameOnRoster("Hills, De'Ante, B"));
        assertEquals(0, MainRewrite.regex4NameOnRoster("Huynh, Gia-Bao (David), Huu"));
        assertEquals(0, MainRewrite.regex4NameOnRoster("Last, First, Middle"));
    }

    @Test
    void regex5Date() { //MM-DD-YYYY
        System.out.println("\nregex5Date" + "------------------------------");
        assertEquals(1, MainRewrite.regex5Date("01-01-2023"));
        assertEquals(1, MainRewrite.regex5Date("01/01/2023"));
        assertEquals(1, MainRewrite.regex5Date("1-1-2023"));
        assertEquals(1, MainRewrite.regex5Date("1/1/2023"));
        assertEquals(0, MainRewrite.regex5Date("02/29/2001"));
        assertEquals(0, MainRewrite.regex5Date("13/1/2023"));
        assertEquals(0, MainRewrite.regex5Date("12/32/2023"));
        assertEquals(0, MainRewrite.regex5Date("99/99/2023"));
        assertEquals(0, MainRewrite.regex5Date("0/0/2023"));
        assertEquals(0, MainRewrite.regex5Date("1-1/2023"));
        assertEquals(0, MainRewrite.regex5Date("1/1-2023"));
    }

    @Test
    void regex6HouseAddress() {
        System.out.println("\nregex6HouseAddress" + "------------------------------");
        assertEquals(1, MainRewrite.regex6HouseAddress("910 S 77nd St"));
        assertEquals(1, MainRewrite.regex6HouseAddress("910 S. 77nd St."));
        assertEquals(1, MainRewrite.regex6HouseAddress("910 S. 77nd Blvd."));
        assertEquals(1, MainRewrite.regex6HouseAddress("910 s. 77nd blvd"));
        assertEquals(1, MainRewrite.regex6HouseAddress("84 South Rock Maple Ave."));
        assertEquals(1, MainRewrite.regex6HouseAddress("84 South Rock Maple Ave."));
        assertEquals(1, MainRewrite.regex6HouseAddress("84 South Rock Maple Avenue"));
        assertEquals(0, MainRewrite.regex6HouseAddress("84 South Rock Maple Aven."));
    }

    @Test
    void regex7CityState() {
        System.out.println("\nregex7CityState" + "------------------------------");
        assertEquals(1, MainRewrite.regex7CityState("Tacoma, WA 12345"));
        assertEquals(1, MainRewrite.regex7CityState("Tacoma, WA 12345-1234"));
        assertEquals(1, MainRewrite.regex7CityState("Tacoma,WA 12345-1234"));
        assertEquals(0, MainRewrite.regex7CityState("Tacoma,WA 12345-"));
        assertEquals(0, MainRewrite.regex7CityState("Tacoma, WA 1234"));
        assertEquals(0, MainRewrite.regex7CityState("Tacoma WA 12345"));
        assertEquals(0, MainRewrite.regex7CityState("Tacoma, WA 1234-1234"));
        assertEquals(0, MainRewrite.regex7CityState("Tacoma, WZ 12345"));
    }

    @Test
    void regex9USMoney() {
        System.out.println("\nregex9USMoney" + "------------------------------");
        assertEquals(1, MainRewrite.regex9USMoney("$123,456,789.23"));
        assertEquals(1, MainRewrite.regex9USMoney("123,456,789.23"));
        assertEquals(1, MainRewrite.regex9USMoney("$123456789.23"));
        assertEquals(0, MainRewrite.regex9USMoney("$123456,789.23"));
        assertEquals(0, MainRewrite.regex9USMoney("$123,456789.23"));
        assertEquals(1, MainRewrite.regex9USMoney("$23,456,789.23"));
        assertEquals(1, MainRewrite.regex9USMoney("$2,456,789.23"));
        assertEquals(1, MainRewrite.regex9USMoney("$456,789.23"));
        assertEquals(1, MainRewrite.regex9USMoney("$56,789.23"));
        assertEquals(1, MainRewrite.regex9USMoney("$6,789.23"));
        assertEquals(1, MainRewrite.regex9USMoney("$789.23"));
        assertEquals(1, MainRewrite.regex9USMoney("$89.23"));
        assertEquals(1, MainRewrite.regex9USMoney("$9.23"));
        assertEquals(1, MainRewrite.regex9USMoney("$0.23"));
        assertEquals(1, MainRewrite.regex9USMoney("$1,000"));
        assertEquals(0, MainRewrite.regex9USMoney("$1,000.0"));
        assertEquals(0, MainRewrite.regex9USMoney("$123,456,789.2"));
        assertEquals(0, MainRewrite.regex9USMoney("$123,45,789.23"));
        assertEquals(0, MainRewrite.regex9USMoney("$123,456,78.23"));
    }

    @Test
    void regex10URL() {
        System.out.println("\nregex10URL" + "------------------------------");
        assertEquals(1, MainRewrite.regex10URL("https://www.google.com"));
        assertEquals(1, MainRewrite.regex10URL("www.google.com"));
    }

    @Test
    void regex12IonOdd() {
        System.out.println("\nregex12IonOdd" + "------------------------------");
        assertEquals(1, MainRewrite.regex12IonOdd("ion"));
        assertEquals(1, MainRewrite.regex12IonOdd("quion"));
        assertEquals(1, MainRewrite.regex12IonOdd("million"));
        assertEquals(0, MainRewrite.regex12IonOdd("Trillion"));
        assertEquals(0, MainRewrite.regex12IonOdd("POTION"));
        assertEquals(0, MainRewrite.regex12IonOdd("lion"));
    }
}