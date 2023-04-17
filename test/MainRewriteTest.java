import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class MainRewriteTest {

    @Test
    void regex1SSN() {
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
        assertEquals(1, MainRewrite.regex2USPhone("2531231234"));
        assertEquals(1, MainRewrite.regex2USPhone("(253)123-1234"));
        assertEquals(1, MainRewrite.regex2USPhone("253123-1234"));
        assertEquals(1, MainRewrite.regex2USPhone("253 123-1234"));
        assertEquals(1, MainRewrite.regex2USPhone("(253)1231234"));
    }

    @Test
    void regex3Email() {
        assertEquals(1, MainRewrite.regex3Email("bruh@gmail.com"));
        assertEquals(0, MainRewrite.regex3Email("bruh@gmail."));
        assertEquals(0, MainRewrite.regex3Email("@gmail.com"));
        assertEquals(0, MainRewrite.regex3Email("bruhgmail.com"));
        assertEquals(0, MainRewrite.regex3Email("bruh@.com"));
    }

    @Test
    void regex4NameOnRoster() {
        assertEquals(1, MainRewrite.regex4NameOnRoster("Last, First, M"));
        assertEquals(1, MainRewrite.regex4NameOnRoster("         Last, First, M"));
        assertEquals(1, MainRewrite.regex4NameOnRoster("Huynh, David, H"));
        assertEquals(1, MainRewrite.regex4NameOnRoster("Huynh, Gia-Bao, H"));
        assertEquals(1, MainRewrite.regex4NameOnRoster("Huynh, Gia-Bao (David), H"));
        assertEquals(0, MainRewrite.regex4NameOnRoster("Huynh, Gia-Bao (David), Huu"));
        assertEquals(0, MainRewrite.regex4NameOnRoster("Last, First, Middle"));
    }

    @Test
    void regex5Date() {
        assertEquals(1, MainRewrite.regex5Date("01-01-1234"));
    }

    @Test
    void regex6CityState() {
        assertEquals(1, MainRewrite.regex6CityState("Tacoma, WA 12345"));
        assertEquals(1, MainRewrite.regex6CityState("Tacoma, WA 12345-1234"));
        assertEquals(1, MainRewrite.regex6CityState("Tacoma,WA 12345-1234"));
        assertEquals(0, MainRewrite.regex6CityState("Tacoma,WA 12345-"));
        assertEquals(0, MainRewrite.regex6CityState("Tacoma, WA 1234"));
        assertEquals(0, MainRewrite.regex6CityState("Tacoma WA 12345"));
        assertEquals(0, MainRewrite.regex6CityState("Tacoma, WA 1234-1234"));
    }
}