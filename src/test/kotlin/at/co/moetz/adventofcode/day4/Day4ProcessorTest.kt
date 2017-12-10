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

        val result = processor.countValidPassphrases(input)

        assertEquals(
                "aa bb cc dd ee is valid.",
                1,
                result
        )
    }

    @Test
    fun countInvalidPassphrasesExampleTestcase2() {
        val input = listOf("aa bb cc dd aa")

        val result = processor.countValidPassphrases(input)

        assertEquals(
                "aa bb cc dd aa is not valid - the word aa appears more than once.",
                0,
                result
        )
    }

    @Test
    fun countInvalidPassphrasesExampleTestcase3() {
        val input = listOf("aa bb cc dd ee")

        val result = processor.countValidPassphrases(input)

        assertEquals(
                "aa bb cc dd aaa is valid - aa and aaa count as different words.",
                1,
                result
        )
    }

    @Test
    fun countInvalidPassphrasesRealPuzzle() {
        val input = Day4Delegate.input

        val result = processor.countValidPassphrases(input)

        assertEquals(
                "Your puzzle answer was 383.",
                383,
                result
        )
    }



    @Test
    fun countInvalidPassphrasesWithAddedSecurityExampleTestcase1() {
        val input = listOf("abcde fghij")

        val result = processor.countValidPassphrasesWithAddedSecurity(input)

        assertEquals(
                "abcde fghij is a valid passphrase.",
                1,
                result
        )
    }

    @Test
    fun countInvalidPassphrasesWithAddedSecurityExampleTestcase2() {
        val input = listOf("abcde xyz ecdab")

        val result = processor.countValidPassphrasesWithAddedSecurity(input)

        assertEquals(
                "abcde xyz ecdab is not valid - the letters from the third word can be rearranged to form the first word.",
                0,
                result
        )
    }

    @Test
    fun countInvalidPassphrasesWithAddedSecurityExampleTestcase3() {
        val input = listOf("a ab abc abd abf abj")

        val result = processor.countValidPassphrasesWithAddedSecurity(input)

        assertEquals(
                "a ab abc abd abf abj is a valid passphrase, because all letters need to be used when forming another word.",
                1,
                result
        )
    }

    @Test
    fun countInvalidPassphrasesWithAddedSecurityExampleTestcase4() {
        val input = listOf("iiii oiii ooii oooi oooo")

        val result = processor.countValidPassphrasesWithAddedSecurity(input)

        assertEquals(
                "iiii oiii ooii oooi oooo is valid.",
                1,
                result
        )
    }

    @Test
    fun countInvalidPassphrasesWithAddedSecurityExampleTestcase5() {
        val input = listOf("oiii ioii iioi iiio")

        val result = processor.countValidPassphrasesWithAddedSecurity(input)

        assertEquals(
                "oiii ioii iioi iiio is not valid - any of these words can be rearranged to form any other word.",
                0,
                result
        )
    }

    @Test
    fun countInvalidPassphrasesWithAddedSecurityRealPuzzle() {
        val input = Day4Delegate.input

        val result = processor.countValidPassphrasesWithAddedSecurity(input)

        assertEquals(
                "Your puzzle answer was 265.",
                265,
                result
        )
    }

}