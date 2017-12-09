package at.co.moetz.adventofcode.day3

import at.co.moetz.adventofcode.DayDelegate


class Day3Processor {

    enum class Instruction {
        RIGHT, UP, LEFT, DOWN
    }

    companion object {
        val instructionList = listOf(
                Instruction.RIGHT,
                Instruction.UP,
                Instruction.LEFT,
                Instruction.DOWN
        )
    }


    fun calculateManhattanDistance(input: Int): Int {
        var currentX = 0
        var currentY = 0

        var instructionVar = 0

        var howManyMoves = 1
        var movesDone = 0

        var differentInstructionsDone = 0

        (2..input).forEach {
            when (instructionList[instructionVar % 4]) {
                Instruction.RIGHT -> currentX++
                Instruction.UP -> currentY--
                Instruction.LEFT -> currentX--
                Instruction.DOWN -> currentY++
            }
            movesDone++
            if (howManyMoves == movesDone) {
                movesDone = 0
                differentInstructionsDone++
                instructionVar++
                if (differentInstructionsDone == 2) {
                    differentInstructionsDone = 0
                    howManyMoves++
                }
            }
        }
        return Math.abs(currentX) + Math.abs(currentY)
    }

    fun calculateFirstWrittenValueLargerThanInput(input: Int): Int {
        var currentX = 0
        var currentY = 0

        var instructionVar = 0

        var howManyMoves = 1
        var movesDone = 0

        val mapOfPositionAndValue: MutableMap<Pair<Int, Int>, Int> = mutableMapOf(
                Pair(Pair(0, 0), 1)
        )

        var differentInstructionsDone = 0

        (2..Int.MAX_VALUE).forEach {
            when (instructionList[instructionVar % 4]) {
                Instruction.RIGHT -> currentX++
                Instruction.UP -> currentY--
                Instruction.LEFT -> currentX--
                Instruction.DOWN -> currentY++
            }

            //calculating field value
            var value = 0
            (-1..1).map { currentY + it }.forEach { y ->
                (-1..1).map { currentX + it }.forEach { x ->
                    if(mapOfPositionAndValue.containsKey(Pair(x, y))) {
                        value += mapOfPositionAndValue[Pair(x, y)]!!
                    }
                }
            }
            mapOfPositionAndValue[Pair(currentX, currentY)] = value

            if(value > input) {
                return value
            }


            movesDone++
            if (howManyMoves == movesDone) {
                movesDone = 0
                differentInstructionsDone++
                instructionVar++
                if (differentInstructionsDone == 2) {
                    differentInstructionsDone = 0
                    howManyMoves++
                }
            }
        }

        return 0
    }

}


class Day3Delegate : DayDelegate {

    override val day: Int = 3
    override val title: String = "Day 3: Spiral Memory"

    companion object {
        const val input = 277678
    }


    private val processor: Day3Processor by lazy { Day3Processor() }

    override fun part1() {
        val distance = processor.calculateManhattanDistance(input)
        System.out.println("Manhattan distance is " + distance.toString())
    }

    override fun part2() {
        val value = processor.calculateFirstWrittenValueLargerThanInput(input)
        System.out.println("First value larger than $input stored is " + value.toString())
    }

}
