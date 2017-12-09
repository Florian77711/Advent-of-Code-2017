package at.co.moetz.adventofcode.day3

import org.junit.Before

import org.junit.Assert.*
import org.junit.Test


class Day3ProcessorTest {
    private lateinit var processor: Day3Processor

    @Before
    fun setUp() {
        processor = Day3Processor()
    }

    @Test
    fun calculateManhattanDistanceExampleTestcase1() {
        val input = 1

        val captcha = processor.calculateManhattanDistance(input)

        assertEquals(
                "Data from square 1 is carried 0 steps, since it's at the access port.",
                0,
                captcha
        )
    }

    @Test
    fun calculateManhattanDistanceExampleTestcase2() {
        val input = 12

        val captcha = processor.calculateManhattanDistance(input)

        assertEquals(
                "Data from square 12 is carried 3 steps, such as: down, left, left.",
                3,
                captcha
        )
    }

    @Test
    fun calculateManhattanDistanceExampleTestcase3() {
        val input = 23

        val captcha = processor.calculateManhattanDistance(input)

        assertEquals(
                "Data from square 23 is carried only 2 steps: up twice.",
                2,
                captcha
        )
    }

    @Test
    fun calculateManhattanDistanceExampleTestcase4() {
        val input = 1024

        val captcha = processor.calculateManhattanDistance(input)

        assertEquals(
                "Data from square 1024 must be carried 31 steps.",
                31,
                captcha
        )
    }

    @Test
    fun calculateManhattanDistanceRealPuzzle() {
        val input = 277678

        val captcha = processor.calculateManhattanDistance(input)

        assertEquals(
                "Your puzzle answer was 475.",
                475,
                captcha
        )
    }


    @Test
    fun calculateFirstLargerValueStoredExampleTestcase1() {
        val input = 2

        val captcha = processor.calculateFirstWrittenValueLargerThanInput(input)

        assertEquals(
                4,
                captcha
        )
    }

    @Test
    fun calculateFirstLargerValueStoredExampleTestcase2() {
        val input = 304

        val captcha = processor.calculateFirstWrittenValueLargerThanInput(input)

        assertEquals(
                330,
                captcha
        )
    }

    @Test
    fun calculateFirstLargerValueStoredExampleTestcase3() {
        val input = 800

        val captcha = processor.calculateFirstWrittenValueLargerThanInput(input)

        assertEquals(
                806,
                captcha
        )
    }

    @Test
    fun calculateFirstLargerValueStoredRealPuzzle() {
        val input = 277678

        val captcha = processor.calculateFirstWrittenValueLargerThanInput(input)

        assertEquals(
                "Your puzzle answer was 279138.",
                279138,
                captcha
        )
    }

}