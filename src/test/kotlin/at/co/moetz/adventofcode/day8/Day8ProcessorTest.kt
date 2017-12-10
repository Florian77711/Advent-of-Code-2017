package at.co.moetz.adventofcode.day8

import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test


class Day8ProcessorTest {
    private lateinit var processor: Day8Processor

    @Before
    fun setUp() {
        processor = Day8Processor()
    }

    @Test
    fun getNameOfBottomProgramExampleTestcase() {
        val input = listOf("b inc 5 if a > 1",
                "a inc 1 if b < 5",
                "c dec -10 if a >= 1",
                "c inc -20 if c == 10")

        val result = processor.getHighestValueInRegistersAfterInstructions(input)

        assertEquals(
                "These instructions would be processed as follows:\n" +
                        "\n" +
                        "- Because a starts at 0, it is not greater than 1, and so b is not modified.\n" +
                        "- a is increased by 1 (to 1) because b is less than 5 (it is 0).\n" +
                        "- c is decreased by -10 (to 10) because a is now greater than or equal to 1 (it is 1).\n" +
                        "- c is increased by -20 (to -10) because c is equal to 10.\n" +
                        "After this process, the largest value in any register is 1.",
                1,
                result
        )
    }

    @Test
    fun getNameOfBottomProgramRealPuzzle() {
        val input = Day8Delegate.input

        val result = processor.getHighestValueInRegistersAfterInstructions(input)

        assertEquals(
                "Your puzzle answer was 5752.",
                5752,
                result
        )
    }


    @Test
    fun getHighestValueInRegistersAnytimeExampleTestcase() {
        val input = listOf("b inc 5 if a > 1",
                "a inc 1 if b < 5",
                "c dec -10 if a >= 1",
                "c inc -20 if c == 10")

        val result = processor.getHighestValueInRegistersAnytime(input)

        assertEquals(
                "For example, in the above instructions, the highest value ever held was 10 (in register c after the third instruction was evaluated).",
                10,
                result
        )
    }

    @Test
    fun getHighestValueInRegistersAnytimeRealPuzzle() {
        val input = Day8Delegate.input

        val result = processor.getHighestValueInRegistersAnytime(input)

        assertEquals(
                "Your puzzle answer was 6366.",
                6366,
                result
        )
    }

}