package at.co.moetz.adventofcode.day10

import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test


class Day10ProcessorTest {
    private lateinit var processor: Day10Processor

    @Before
    fun setUp() {
        processor = Day10Processor()
    }

    @Test
    fun countScoreOneEmptyGroup() {
        val input = "3, 4, 1, 5"

        val result = processor.applyTwistHash(input, 5)

        assertEquals(
                "Suppose we instead only had a circular list containing five elements, 0, 1, 2, 3, 4, and were given input lengths of 3, 4, 1, 5.\n" +
                        "\n" +
                        "- The list begins as [0] 1 2 3 4 (where square brackets indicate the current position).\n" +
                        "- The first length, 3, selects ([0] 1 2) 3 4 (where parentheses indicate the sublist to be reversed).\n" +
                        "- After reversing that section (0 1 2 into 2 1 0), we get ([2] 1 0) 3 4.\n" +
                        "- Then, the current position moves forward by the length, 3, plus the skip size, 0: 2 1 0 [3] 4. Finally, the skip size increases to 1.\n" +
                        "- The second length, 4, selects a section which wraps: 2 1) 0 ([3] 4.\n" +
                        "- The sublist 3 4 2 1 is reversed to form 1 2 4 3: 4 3) 0 ([1] 2.\n" +
                        "- The current position moves forward by the length plus the skip size, a total of 5, causing it not to move because it wraps around: 4 3 0 [1] 2. The skip size increases to 2.\n" +
                        "- The third length, 1, selects a sublist of a single element, and so reversing it has no effect.\n" +
                        "- The current position moves forward by the length (1) plus the skip size (2): 4 [3] 0 1 2. The skip size increases to 3.\n" +
                        "- The fourth length, 5, selects every element starting with the second: 4) ([3] 0 1 2. Reversing this sublist (3 0 1 2 4 into 4 2 1 0 3) produces: 3) ([4] 2 1 0.\n" +
                        "- Finally, the current position moves forward by 8: 3 4 2 1 [0]. The skip size increases to 4.\n" +
                        "In this example, the first two numbers in the list end up being 3 and 4; to check the process, you can multiply them together to produce 12.",
                12,
                result
        )
    }

    @Test
    fun countScoreGetNameOfBottomProgramRealPuzzle() {
        val input = Day10Delegate.input

        val result = processor.applyTwistHash(input)

        assertEquals(
                "Your puzzle answer was 54675.",
                54675,
                result
        )
    }



    @Test
    fun generateDenseHashExampleTestCase1() {
        val input = ""

        val result = processor.generateHash(input)

        assertEquals(
                "The empty string becomes a2582a3a0e66e6e86e3812dcb672a272.",
                "a2582a3a0e66e6e86e3812dcb672a272",
                result
        )
    }


    @Test
    fun generateDenseHashExampleTestCase2() {
        val input = "AoC 2017"

        val result = processor.generateHash(input)

        assertEquals(
                "AoC 2017 becomes 33efeb34ea91902bb2f59c9920caa6cd.",
                "33efeb34ea91902bb2f59c9920caa6cd",
                result
        )
    }

    @Test
    fun generateDenseHashExampleTestCase3() {
        val input = "1,2,3"

        val result = processor.generateHash(input)

        assertEquals(
                "1,2,3 becomes 3efbe78a8d82f29979031a4aa0b16a9d.",
                "3efbe78a8d82f29979031a4aa0b16a9d",
                result
        )
    }

    @Test
    fun generateDenseHashExampleTestCase4() {
        val input = "1,2,4"

        val result = processor.generateHash(input)

        assertEquals(
                "1,2,4 becomes 63960835bcdc130f0b66d7ff4f6a5a8e.",
                "63960835bcdc130f0b66d7ff4f6a5a8e",
                result
        )
    }

    @Test
    fun generateDenseHashRealPuzzle() {
        val input = Day10Delegate.input

        val result = processor.generateHash(input)

        assertEquals(
                "Your puzzle answer was a7af2706aa9a09cf5d848c1e6605dd2a.",
                "a7af2706aa9a09cf5d848c1e6605dd2a",
                result
        )
    }

}