package at.co.moetz.adventofcode.day11

import at.co.moetz.adventofcode.DayDelegate
import java.io.File
import java.io.FileInputStream
import java.io.InputStream

class Day11Processor {

    fun getNumberOfStepsInHexagon(input: InputStream): Int {
        val reader = input.reader()

        var positionX = 0
        var positionY = 0

        var character = reader.read()
        while(character != -1) {
            var directionString = ""
            while(character != -1 && character.toChar() != ',') {
                directionString += character.toChar()
                character = reader.read()
            }

            when(directionString) {
                "n" -> positionY-=2
                "ne" -> {
                    positionX++
                    positionY--
                }
                "se" -> {
                    positionX++
                    positionY++
                }
                "s" -> positionY+=2
                "sw" -> {
                    positionX--
                    positionY++
                }
                "nw" -> {
                    positionX--
                    positionY--
                }
            }

            character = reader.read()
        }



        val steps = calculateStepsToPosition(positionX, positionY)

        return steps
    }


    private fun calculateStepsToPosition(x: Int, y: Int): Int {
        val position = Pair(x, y)

        var positionX = 0
        var positionY = 0

        var steps = 0

        while(positionX != position.first || positionY != position.second) {
            steps++
            when {
                positionX < position.first && positionY > position.second -> {
                    //go to north west
                    positionX++
                    positionY--
                }

                positionX < position.first && positionY < position.second -> {
                    //go to south west
                    positionX++
                    positionY++
                }

                positionX > position.first && positionY > position.second -> {
                    //go to north east
                    positionX--
                    positionY--
                }

                positionX > position.first && positionY < position.second -> {
                    //go to south east
                    positionX--
                    positionY++
                }

                positionX == position.first && positionY > position.second -> {
                    //got to north
                    positionY-=2
                }

                positionX == position.first && positionY < position.second -> {
                    //got to south
                    positionY+=2
                }

                positionY == position.second && positionX > position.first -> {
                    //got to east
                    positionX-=2
                }

                positionY == position.second && positionX < position.first -> {
                    //got to west
                    positionX+=2
                }
            }
        }

        return steps
    }


    fun getFarestPositionGone(input: InputStream): Int {
        val reader = input.reader()

        var positionX = 0
        var positionY = 0

        var maxStepsAway = 0

        var character = reader.read()
        while(character != -1) {
            var directionString = ""
            while(character != -1 && character.toChar() != ',') {
                directionString += character.toChar()
                character = reader.read()
            }

            when(directionString) {
                "n" -> positionY-=2
                "ne" -> {
                    positionX++
                    positionY--
                }
                "se" -> {
                    positionX++
                    positionY++
                }
                "s" -> positionY+=2
                "sw" -> {
                    positionX--
                    positionY++
                }
                "nw" -> {
                    positionX--
                    positionY--
                }
            }

            calculateStepsToPosition(positionX, positionY).also { steps ->
                if(steps > maxStepsAway) {
                    maxStepsAway = steps
                }
            }


            character = reader.read()
        }

        return maxStepsAway
    }

}

class Day11Delegate : DayDelegate {

    override val day: Int = 11
    override val title: String = "Day 11: Hex Ed"

    companion object {
        val input: InputStream get() = FileInputStream(File("./src/main/resources/Day11Input.txt"))
    }


    private val processor: Day11Processor by lazy { Day11Processor() }

    override fun part1() {
        val numberOfSteps = processor.getNumberOfStepsInHexagon(input)
        System.out.println("The number of steps to go back is " + numberOfSteps)
    }

    override fun part2() {
        val numberOfFarestStepsAway = processor.getFarestPositionGone(input)
        System.out.println("The number of steps furthest away on the way is " + numberOfFarestStepsAway)
    }

}