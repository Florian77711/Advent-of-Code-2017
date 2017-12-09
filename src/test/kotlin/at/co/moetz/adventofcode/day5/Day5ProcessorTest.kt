package at.co.moetz.adventofcode.day5

import org.junit.Before

import org.junit.Assert.*
import org.junit.Test


class Day5ProcessorTest {
    private lateinit var processor: Day5Processor

    @Before
    fun setUp() {
        processor = Day5Processor()
    }

    @Test
    fun countStepsToEscapeExampleTestcase() {
        val input = listOf(0, 3, 0, 1, -3)

        val captcha = processor.countStepsToEscape(input)

        assertEquals(
                "- (0) 3  0  1  -3  - before we have taken any steps.\n" +
                        "- (1) 3  0  1  -3  - jump with offset 0 (that is, don't jump at all). Fortunately, the instruction is then incremented to 1.\n" +
                        "-  2 (3) 0  1  -3  - step forward because of the instruction we just modified. The first instruction is incremented again, now to 2.\n" +
                        "-  2  4  0  1 (-3) - jump all the way to the end; leave a 4 behind.\n" +
                        "-  2 (4) 0  1  -2  - go back to where we just were; increment -3 to -2.\n" +
                        "-  2  5  0  1  -2  - jump 4 steps forward, escaping the maze.\n" +
                        "In this example, the exit is reached in 5 steps.",
                5,
                captcha
        )
    }

    @Test
    fun countStepsToEscapeRealPuzzle() {
        val input = Day5Delegate.input

        val captcha = processor.countStepsToEscape(input)

        assertEquals(
                "Your puzzle answer was 364539.",
                364539,
                captcha
        )
    }


    @Test
    fun countStepsToEscapeWithStrangeJumpsExampleTestcase() {
        val input = listOf(0, 3, 0, 1, -3)

        val captcha = processor.countStepsToEscapeWithStrangeJumps(input)

        assertEquals(
                "Using this rule with the above example (0, 3, 0, 1, -3), the process now takes 10 steps, and the offset values after finding the exit are left as 2 3 2 3 -1.",
                10,
                captcha
        )
    }

    @Test
    fun countStepsToEscapeWithStrangeJumpsRealPuzzle() {
        val input = Day5Delegate.input

        val captcha = processor.countStepsToEscapeWithStrangeJumps(input)

        assertEquals(
                "Your puzzle answer was 27477714.",
                27477714,
                captcha
        )
    }

}