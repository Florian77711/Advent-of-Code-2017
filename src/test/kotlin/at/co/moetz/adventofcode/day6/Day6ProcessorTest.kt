package at.co.moetz.adventofcode.day6

import org.junit.Before

import org.junit.Assert.*
import org.junit.Test


class Day6ProcessorTest {
    private lateinit var processor: Day6Processor

    @Before
    fun setUp() {
        processor = Day6Processor()
    }

    @Test
    fun countRedistributionCyclesTillSeenBeforeExampleTestcase() {
        val input = listOf(0, 2, 7, 0)

        val captcha = processor.countRedistributionCyclesTillSeenBefore(input)

        assertEquals(
                "For example, imagine a scenario with only four memory banks:\n" +
                        "\n" +
                        "The banks start with 0, 2, 7, and 0 blocks. The third bank has the most blocks, so it is chosen for redistribution.\n" +
                        "Starting with the next bank (the fourth bank) and then continuing to the first bank, the second bank, and so on, the 7 blocks are spread out over the memory banks. The fourth, first, and second banks get two blocks each, and the third bank gets one back. The final result looks like this: 2 4 1 2.\n" +
                        "Next, the second bank is chosen because it contains the most blocks (four). Because there are four memory banks, each gets one block. The result is: 3 1 2 3.\n" +
                        "Now, there is a tie between the first and fourth memory banks, both of which have three blocks. The first bank wins the tie, and its three blocks are distributed evenly over the other three banks, leaving it with none: 0 2 3 4.\n" +
                        "The fourth bank is chosen, and its four blocks are distributed such that each of the four banks receives one: 1 3 4 1.\n" +
                        "The third bank is chosen, and the same thing happens: 2 4 1 2.\n" +
                        "At this point, we've reached a state we've seen before: 2 4 1 2 was already seen. The infinite loop is detected after the fifth block redistribution cycle, and so the answer in this example is 5.",
                5,
                captcha
        )
    }

    @Test
    fun countRedistributionCyclesTillSeenBeforeRealPuzzle() {
        val input = Day6Delegate.input

        val captcha = processor.countRedistributionCyclesTillSeenBefore(input)

        assertEquals(
                "Your puzzle answer was 4074.",
                4074,
                captcha
        )
    }

    @Test
    fun countRedistributionCyclesBetweenIdenticalStatesExampleTestcase() {
        val input = listOf(0, 2, 7, 0)

        val captcha = processor.countRedistributionCyclesBetweenIdenticalStates(input)

        assertEquals(
                "In the example above, 2 4 1 2 is seen again after four cycles, and so the answer in that example would be 4.",
                4,
                captcha
        )
    }

    @Test
    fun countRedistributionCyclesBetweenIdenticalStatesRealPuzzle() {
        val input = Day6Delegate.input

        val captcha = processor.countRedistributionCyclesBetweenIdenticalStates(input)

        assertEquals(
                "Your puzzle answer was 2793.",
                2793,
                captcha
        )
    }

}