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
    void regex5Date() {
        System.out.println("\nregex5Date" + "------------------------------");
        assertEquals(1, MainRewrite.regex5Date("01-01-2023"));
        assertEquals(1, MainRewrite.regex5Date("01/01/2023"));
        assertEquals(1, MainRewrite.regex5Date("1-1-2023"));
        assertEquals(1, MainRewrite.regex5Date("1/1/2023"));
        assertEquals(0, MainRewrite.regex5Date("1-1/2023"));
        assertEquals(0, MainRewrite.regex5Date("1/1-2023"));
    }

    @Test
    void regex6HouseAddress() {
        System.out.println("\nregex6HouseAddress" + "------------------------------");
        assertEquals(1, MainRewrite.regex6HouseAddress("901 E 72nd St"));
        assertEquals(1, MainRewrite.regex6HouseAddress("901 E. 72nd St."));
        assertEquals(1, MainRewrite.regex6HouseAddress("84 South Rock Maple Ave."));
        assertEquals(1, MainRewrite.regex6HouseAddress("84 South Rock Maple Ave."));
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
    }
}