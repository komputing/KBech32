package kbech32

import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertFailsWith
import kotlin.test.fail

/**
 * Bech32 tests.
 *
 * Taken from [Bitcoinj Bech32 tests](https://github.com/bitcoinj/bitcoinj/blob/master/core/src/test/java/org/bitcoinj/core/Bech32Test.java)
 */
class Bech32Test {

    @Test
    fun valid() {
        for (valid in VALID) {
            val bechData = Bech32.decode(valid)
            var recode = Bech32.encode(bechData)
            assertEquals(valid.toLowerCase(), recode.toLowerCase(), "Failed to roundtrip '$valid' -> '$recode'")

            // Test encoding with an uppercase HRP
            recode = Bech32.encode(bechData.humanReadablePart.toUpperCase(), bechData.data)
            assertEquals(valid.toLowerCase(), recode.toLowerCase(), "Failed to roundtrip '$valid' -> '$recode'")
        }
    }

    @Test
    fun invalid() {
        for (invalid in INVALID) {
            try {
                Bech32.decode(invalid)
                fail("Parsed an invalid code: '$invalid'")
            } catch (x: AddressFormatException) {
                /* expected */
            }
        }
    }

    @Test
    fun decode_invalidCharacter_notInAlphabet() {
        assertFailsWith<AddressFormatException.InvalidCharacter> {
            Bech32.decode("A12OUEL5X")
        }
    }

    @Test
    fun decode_invalidCharacter_upperLowerMix() {
        assertFailsWith<AddressFormatException.InvalidCharacter> {
            Bech32.decode("A12UeL5X")
        }
    }

    @Test
    fun decode_invalidNetwork() {
        assertFailsWith<AddressFormatException.InvalidChecksum> {
            Bech32.decode("A12UEL5X")
        }
    }

    @Test
    fun decode_invalidHrp() {
        assertFailsWith<AddressFormatException.InvalidPrefix> {
            Bech32.decode("1pzry9x0s0muk")
        }
    }

    companion object {

        private val VALID = arrayOf(
            "A12UEL5L",
            "a12uel5l",
            "an83characterlonghumanreadablepartthatcontainsthenumber1andtheexcludedcharactersbio1tt5tgs",
            "abcdef1qpzry9x8gf2tvdw0s3jn54khce6mua7lmqqqxw",
            "11qqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqc8247j",
            "split1checkupstagehandshakeupstreamerranterredcaperred2y9e3w",
            "?1ezyfcl"
        )

        private val INVALID = arrayOf(
            " 1nwldj5", // HRP character out of range
            String(charArrayOf(0x7f.toChar())) + "1axkwrx", // HRP character out of range
            String(charArrayOf(0x80.toChar())) + "1eym55h", // HRP character out of range
            "an84characterslonghumanreadablepartthatcontainsthenumber1andtheexcludedcharactersbio1569pvx", // overall max length exceeded
            "pzry9x0s0muk", // No separator character
            "1pzry9x0s0muk", // Empty HRP
            "x1b4n0q5v", // Invalid data character
            "li1dgmt3", // Too short checksum
            "de1lg7wt" + String(charArrayOf(0xff.toChar())), // Invalid character in checksum
            "A1G7SGD8", // checksum calculated with uppercase form of HRP
            "10a06t8", // empty HRP
            "1qzzfhee" // empty HRP
        )
    }
}
