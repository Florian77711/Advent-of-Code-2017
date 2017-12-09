package at.co.moetz.adventofcode.day4

import org.junit.Before

import org.junit.Assert.*
import org.junit.Test


class Day4ProcessorTest {
    private lateinit var processor: Day4Processor

    @Before
    fun setUp() {
        processor = Day4Processor()
    }

    @Test
    fun countInvalidPassphrasesExampleTestcase1() {
        val input = listOf("aa bb cc dd ee")

        val captcha = processor.countValidPassphrases(input)

        assertEquals(
                "aa bb cc dd ee is valid.",
                1,
                captcha
        )
    }

    @Test
    fun countInvalidPassphrasesExampleTestcase2() {
        val input = listOf("aa bb cc dd aa")

        val captcha = processor.countValidPassphrases(input)

        assertEquals(
                "aa bb cc dd aa is not valid - the word aa appears more than once.",
                0,
                captcha
        )
    }

    @Test
    fun countInvalidPassphrasesExampleTestcase3() {
        val input = listOf("aa bb cc dd ee")

        val captcha = processor.countValidPassphrases(input)

        assertEquals(
                "aa bb cc dd aaa is valid - aa and aaa count as different words.",
                1,
                captcha
        )
    }

    @Test
    fun countInvalidPassphrasesRealPuzzle() {
        val input = Day4Delegate.input

        val captcha = processor.countValidPassphrases(input)

        assertEquals(
                "Your puzzle answer was 383.",
                383,
                captcha
        )
    }



    @Test
    fun countInvalidPassphrasesWithAddedSecurityExampleTestcase1() {
        val input = listOf("abcde fghij")

        val captcha = processor.countValidPassphrasesWithAddedSecurity(input)

        assertEquals(
                "abcde fghij is a valid passphrase.",
                1,
                captcha
        )
    }

    @Test
    fun countInvalidPassphrasesWithAddedSecurityExampleTestcase2() {
        val input = listOf("abcde xyz ecdab")

        val captcha = processor.countValidPassphrasesWithAddedSecurity(input)

        assertEquals(
                "abcde xyz ecdab is not valid - the letters from the third word can be rearranged to form the first word.",
                0,
                captcha
        )
    }

    @Test
    fun countInvalidPassphrasesWithAddedSecurityExampleTestcase3() {
        val input = listOf("a ab abc abd abf abj")

        val captcha = processor.countValidPassphrasesWithAddedSecurity(input)

        assertEquals(
                "a ab abc abd abf abj is a valid passphrase, because all letters need to be used when forming another word.",
                1,
                captcha
        )
    }

    @Test
    fun countInvalidPassphrasesWithAddedSecurityExampleTestcase4() {
        val input = listOf("iiii oiii ooii oooi oooo")

        val captcha = processor.countValidPassphrasesWithAddedSecurity(input)

        assertEquals(
                "iiii oiii ooii oooi oooo is valid.",
                1,
                captcha
        )
    }

    @Test
    fun countInvalidPassphrasesWithAddedSecurityExampleTestcase5() {
        val input = listOf("oiii ioii iioi iiio")

        val captcha = processor.countValidPassphrasesWithAddedSecurity(input)

        assertEquals(
                "oiii ioii iioi iiio is not valid - any of these words can be rearranged to form any other word.",
                0,
                captcha
        )
    }

    @Test
    fun countInvalidPassphrasesWithAddedSecurityRealPuzzle() {
        val input = Day4Delegate.input

        val captcha = processor.countValidPassphrasesWithAddedSecurity(input)

        assertEquals(
                "Your puzzle answer was 265.",
                265,
                captcha
        )
    }

}