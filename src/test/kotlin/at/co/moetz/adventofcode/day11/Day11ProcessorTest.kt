package at.co.moetz.adventofcode.day11

import org.junit.Assert.*
import org.junit.Before
import org.junit.Test
import java.io.InputStream


class Day11ProcessorTest {
    private lateinit var processor: Day11Processor


    private fun String.asInputStream(): InputStream {
        return this.byteInputStream()
    }

    @Before
    fun setUp() {
        processor = Day11Processor()
    }

    @Test
    fun getNumberOfStepsInHexagonExampleTestcase1() {
        val input = "ne,ne,ne".asInputStream()

        val result = processor.getNumberOfStepsInHexagon(input)

        assertEquals(
                "ne,ne,ne is 3 steps away.",
                3,
                result
        )
    }

    @Test
    fun getNumberOfStepsInHexagonExampleTestcase2() {
        val input = "ne,ne,sw,sw".asInputStream()

        val result = processor.getNumberOfStepsInHexagon(input)

        assertEquals(
                "ne,ne,sw,sw is 0 steps away (back where you started).",
                0,
                result
        )
    }

    @Test
    fun getNumberOfStepsInHexagonExampleTestcase3() {
        val input = "ne,ne,s,s".asInputStream()

        val result = processor.getNumberOfStepsInHexagon(input)

        assertEquals(
                "ne,ne,s,s is 2 steps away (se,se).",
                2,
                result
        )
    }

    @Test
    fun getNumberOfStepsInHexagonExampleTestcase4() {
        val input = "se,sw,se,sw,sw".asInputStream()

        val result = processor.getNumberOfStepsInHexagon(input)

        assertEquals(
                "se,sw,se,sw,sw is 3 steps away (s,s,sw).",
                3,
                result
        )
    }

    @Test
    fun getNumberOfStepsInHexagonRealPuzzle() {
        val input = Day11Delegate.input

        val result = processor.getNumberOfStepsInHexagon(input)

        assertEquals(
                "Your puzzle answer was 685.",
                685,
                result
        )
    }


    @Test
    fun getFarestPositionGoneRealPuzzle() {
        val input = Day11Delegate.input

        val result = processor.getFarestPositionGone(input)

        assertEquals(
                "Your puzzle answer was 1457.",
                1457,
                result
        )
    }

}