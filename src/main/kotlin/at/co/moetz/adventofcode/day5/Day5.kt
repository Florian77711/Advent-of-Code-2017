package at.co.moetz.adventofcode.day5

import at.co.moetz.adventofcode.DayDelegate
import java.io.File
import java.io.FileInputStream
import java.io.InputStreamReader

class Day5Processor {

    fun countStepsToEscape(input: List<Int>): Int {
        val mutableInput = input.toMutableList()

        val inputListLastIndex = mutableInput.lastIndex

        var currentPosition = 0

        var moves = 0

        while (true) {
            val newPosition = currentPosition + mutableInput[currentPosition]
            moves++
            if (newPosition < 0 || newPosition > inputListLastIndex) {
                return moves
            }

            mutableInput[currentPosition]++

            currentPosition = newPosition
        }
    }


    fun countStepsToEscapeWithStrangeJumps(input: List<Int>): Int {
        val mutableInput = input.toMutableList()

        val inputListLastIndex = mutableInput.lastIndex

        var currentPosition = 0

        var moves = 0

        while (true) {
            val newPosition = currentPosition + mutableInput[currentPosition]
            moves++
            if (newPosition < 0 || newPosition > inputListLastIndex) {
                return moves
            }

            if (mutableInput[currentPosition] >= 3) {
                mutableInput[currentPosition]--
            } else {
                mutableInput[currentPosition]++
            }

            currentPosition = newPosition
        }
    }

}

class Day5Delegate : DayDelegate {

    override val day: Int = 5
    override val title: String = "Day 5: A Maze of Twisty Trampolines, All Alike"

    companion object {
        val input: List<Int> by lazy { InputStreamReader(FileInputStream(File("./src/main/resources/Day5Input.txt"))).readLines().map { it.toInt() } }
    }


    private val processor: Day5Processor by lazy { Day5Processor() }

    override fun part1() {
        val stepsToEscape = processor.countStepsToEscape(input)
        System.out.println("Number of steps to escape is " + stepsToEscape.toString())
    }

    override fun part2() {
        val stepsToEscape = processor.countStepsToEscapeWithStrangeJumps(input)
        System.out.println("Number of steps to escape with strange jumps is " + stepsToEscape.toString())
    }

}