package at.co.moetz.adventofcode.day12

import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test


class Day12ProcessorTest {
    private lateinit var processor: Day12Processor

    @Before
    fun setUp() {
        processor = Day12Processor()
    }


    @Test
    fun getNumberOfStepsInHexagonExampleTestcase() {
        val input = listOf(
                "0 <-> 2",
                "1 <-> 1",
                "2 <-> 0, 3, 4",
                "3 <-> 2, 4",
                "4 <-> 2, 3, 6",
                "5 <-> 6",
                "6 <-> 4, 5"
        )

        val result = processor.getNumberOfConnectedPrograms(input)

        assertEquals(
                "In this example, the following programs are in the group that contains program ID 0:\n" +
                        "\n" +
                        "- Program 0 by definition.\n" +
                        "- Program 2, directly connected to program 0.\n" +
                        "- Program 3 via program 2.\n" +
                        "- Program 4 via program 2.\n" +
                        "- Program 5 via programs 6, then 4, then 2.\n" +
                        "- Program 6 via programs 4, then 2.\n" +
                        "Therefore, a total of 6 programs are in this group; all but program 1, which has a pipe that connects it to itself.",
                6,
                result
        )

    }

    @Test
    fun getNumberOfStepsInHexagonRealPuzzle() {
        val input = Day12Delegate.input

        val result = processor.getNumberOfConnectedPrograms(input)

        assertEquals(
                "Your puzzle answer was 378.",
                378,
                result
        )

    }


    @Test
    fun getNumberOfGroupsExampleTestcase() {
        val input = listOf(
                "0 <-> 2",
                "1 <-> 1",
                "2 <-> 0, 3, 4",
                "3 <-> 2, 4",
                "4 <-> 2, 3, 6",
                "5 <-> 6",
                "6 <-> 4, 5"
        )

        val result = processor.getNumberOfGroups(input)

        assertEquals(
                "In the example above, there were 2 groups: one consisting of programs 0,2,3,4,5,6, and the other consisting solely of program 1.",
                2,
                result
        )

    }

    @Test
    fun getNumberOfGroupsRealPuzzle() {
        val input = Day12Delegate.input

        val result = processor.getNumberOfGroups(input)

        assertEquals(
                "Your puzzle answer was 204.",
                204,
                result
        )

    }


}