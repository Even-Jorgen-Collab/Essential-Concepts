package no.ntnu.jorgfi.algorithm;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertThrows;

import org.junit.Test;

/**
 * Tests for encode() and decode() methods
 */
public final class TestEncoder {
    @Test
    public final void testNullEncode() {
        assertNull(Encoder.encode(null));
    }

    @Test
    public final void testEmptyEncode() {
        assertNull(Encoder.encode(null));
        assertEquals("", Encoder.encode(""));
    }

    @Test
    public final void testNullDecode() {
        assertNull(Encoder.decode(null));
    }

    @Test
    public final void testEmptyDecode() {
        assertNull(Encoder.decode(null));
        assertEquals("", Encoder.decode(""));
    }

    @Test
    public final void testInvalidInputEncode() {
        assertThrows(IllegalArgumentException.class, () -> {
            Encoder.encode("Yowza ;)");
        });
        assertThrows(IllegalArgumentException.class, () -> {
            Encoder.encode("Welcome to Ålesund!");
        });
    }

    @Test
    public final void testInvalidSingleBitDecode() {
        assertThrows(IllegalArgumentException.class, () -> {
            Encoder.decode("1");
        });
    }

    @Test
    public final void testInvalidBitBlockLengthDecode() {
        assertThrows(IllegalArgumentException.class, () -> {
            Encoder.decode("1010111");
        });
    }

    @Test
    public final void testInvalidBitValueDecode() {
        assertThrows(IllegalArgumentException.class, () -> {
            Encoder.decode("12001111");
        });
    }

    @Test
    public final void testLowercaseEncode() {
        assertEquals("01100001 01100010 01111010", Encoder.encode("abz"));
    }

    @Test
    public final void testUppercaseEncode() {
        assertEquals("01000001 01000010 01011010", Encoder.encode("ABZ"));
    }

    @Test
    public final void testUpperLowerEncode() {
        assertEquals("01001000 01100101 01001100 01001100 01101111",
                Encoder.encode("HeLLo"));
    }

    @Test
    public final void testAllAllowedEncode() {
        assertEquals("01100001 01000001 00101100 00100000 00110000 00101110 00110001 00111001 00100000 01111010 01011010 00100001",
                Encoder.encode("aA, 0.19 zZ!"));
    }

    @Test
    public final void testInvalidCharacterDecode() {
        assertThrows(IllegalArgumentException.class, () -> {
            Encoder.decode("00101000"); // Decodes to "(" -> invalid character
        });
    }

    @Test
    public final void testUpperLowerDecode() {
        assertEquals("HeLLo",
                Encoder.decode("01001000 01100101 01001100 01001100 01101111"));
    }

    @Test
    public final void testAllAllowedDecode() {
        assertEquals("aA, 0.19 zZ!",
                Encoder.decode("01100001 01000001 00101100 00100000 00110000 00101110 00110001 00111001 00100000 01111010 01011010 00100001"));
    }

    @Test
    public final void testInvalidSeparatorDecode() {
        assertThrows(IllegalArgumentException.class, () -> {
            Encoder.decode("01100001001000001"); // Space-separator replaced by 0
        });
    }
    @Test
    public final void testInvalidBitCountDecode() {
        assertThrows(IllegalArgumentException.class, () -> {
            Encoder.decode("0110000"); // Only 7 bits in the first part
        });
        assertThrows(IllegalArgumentException.class, () -> {
            Encoder.decode("01100001 0110000"); // Only 7 bits in the 2nd part
        });
    }
}
