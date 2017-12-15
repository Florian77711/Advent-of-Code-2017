package at.co.moetz.adventofcode.day9

import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import java.io.InputStream


class Day9ProcessorTest {
    private lateinit var processor: Day9Processor


    private fun String.asInputStream(): InputStream {
        return this.byteInputStream()
    }

    @Before
    fun setUp() {
        processor = Day9Processor()
    }

    @Test
    fun countScoreOneEmptyGroup() {
        val input = "{}".asInputStream()

        val result = processor.findTotalScore(input)

        assertEquals(
                "{}, score of 1.",
                1,
                result
        )
    }

    @Test
    fun countScoreThreeGroupsRecursive() {
        val input = "{{{}}}".asInputStream()

        val result = processor.findTotalScore(input)

        assertEquals(
                "{{{}}}, score of 1 + 2 + 3 = 6.",
                6,
                result
        )
    }

    @Test
    fun countScoreTwoGroupsInOneGroup() {
        val input = "{{},{}}".asInputStream()

        val result = processor.findTotalScore(input)

        assertEquals(
                "{{},{}}, score of 1 + 2 + 2 = 5.",
                5,
                result
        )
    }

    @Test
    fun countScoreSomeGroupsTestWithScore16() {
        val input = "{{{},{},{{}}}}".asInputStream()

        val result = processor.findTotalScore(input)

        assertEquals(
                "{{{},{},{{}}}}, score of 1 + 2 + 3 + 3 + 3 + 4 = 16.",
                16,
                result
        )
    }

    @Test
    fun countScoreOneGroupWithGarbage() {
        val input = "{<a>,<a>,<a>,<a>}".asInputStream()

        val result = processor.findTotalScore(input)

        assertEquals(
                "{<a>,<a>,<a>,<a>}, score of 1.",
                1,
                result
        )
    }

    @Test
    fun countScoreSubGroupsWithGarbage() {
        val input = "{{<ab>},{<ab>},{<ab>},{<ab>}}".asInputStream()

        val result = processor.findTotalScore(input)

        assertEquals(
                "{{<ab>},{<ab>},{<ab>},{<ab>}}, score of 1 + 2 + 2 + 2 + 2 = 9.",
                9,
                result
        )
    }

    @Test
    fun countScoreSubGroupsWithGarbageAndEscapes() {
        val input = "{{<!!>},{<!!>},{<!!>},{<!!>}}".asInputStream()

        val result = processor.findTotalScore(input)

        assertEquals(
                "{{<!!>},{<!!>},{<!!>},{<!!>}}, score of 1 + 2 + 2 + 2 + 2 = 9.",
                9,
                result
        )
    }

    @Test
    fun countScoreSubGroupsWithGarbageAndEscapes2() {
        val input = "{{<a!>},{<a!>},{<a!>},{<ab>}}".asInputStream()

        val result = processor.findTotalScore(input)

        assertEquals(
                "{{<a!>},{<a!>},{<a!>},{<ab>}}, score of 1 + 2 = 3.",
                3,
                result
        )
    }

    @Test
    fun countScoreGetNameOfBottomProgramRealPuzzle() {
        val input = Day9Delegate.input

        val result = processor.findTotalScore(input)

        assertEquals(
                "Your puzzle answer was 10800.",
                10800,
                result
        )
    }


    @Test
    fun countGarbageCharactersExampleTest1() {
        val input = "{<>}".asInputStream()

        val result = processor.countValidGarbageCharacters(input)

        assertEquals(
                "<>, 0 characters.",
                0,
                result
        )
    }

    @Test
    fun countGarbageCharactersExampleTest2() {
        val input = "{<random characters>}".asInputStream()

        val result = processor.countValidGarbageCharacters(input)

        assertEquals(
                "<random characters>, 17 characters.",
                17,
                result
        )
    }

    @Test
    fun countGarbageCharactersExampleTest3() {
        val input = "{<<<<>}".asInputStream()

        val result = processor.countValidGarbageCharacters(input)

        assertEquals(
                "<<<<>, 3 characters.",
                3,
                result
        )
    }

    @Test
    fun countGarbageCharactersExampleTest4() {
        val input = "{<{!>}>}".asInputStream()

        val result = processor.countValidGarbageCharacters(input)

        assertEquals(
                "<{!>}>, 2 characters.",
                2,
                result
        )
    }

    @Test
    fun countGarbageCharactersExampleTest5() {
        val input = "{<!!>}".asInputStream()

        val result = processor.countValidGarbageCharacters(input)

        assertEquals(
                "<!!>, 0 characters.",
                0,
                result
        )
    }

    @Test
    fun countGarbageCharactersExampleTest6() {
        val input = "{<!!!>>}".asInputStream()

        val result = processor.countValidGarbageCharacters(input)

        assertEquals(
                "<!!!>>, 0 characters.",
                0,
                result
        )
    }

    @Test
    fun countGarbageCharactersExampleTest7() {
        val input = "{<{o\"i!a,<{i<a>}".asInputStream()

        val result = processor.countValidGarbageCharacters(input)

        assertEquals(
                "<{o\"i!a,<{i<a>, 10 characters.",
                10,
                result
        )
    }

    @Test
    fun countGarbageCharactersRealPuzzle() {
        val input = Day9Delegate.input

        val result = processor.countValidGarbageCharacters(input)

        assertEquals(
                "Your puzzle answer was 4522.",
                4522,
                result
        )
    }


}