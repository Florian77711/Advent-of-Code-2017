package at.co.moetz.adventofcode.day8

import at.co.moetz.adventofcode.DayDelegate
import com.sun.org.apache.xerces.internal.impl.dv.xs.TypeValidator
import java.io.File
import java.io.FileInputStream
import java.io.InputStreamReader

class Day8Processor {

    enum class Instruction {
        Increment, Decrement
    }

    enum class Operation {
        GreaterThan, LessThan, Equals, GreaterOrEquals, LessOrEquals, NotEquals
    }

    fun getHighestValueInRegistersAfterInstructions(input: List<String>): Int {
        val registers: MutableMap<String, Int> = mutableMapOf()
        input.forEach { line ->
            val splitted = line.split(" ").map(String::trim)

            val registerToBeModified = splitted[0]
            val instruction = if(splitted[1] == "inc") Instruction.Increment else Instruction.Decrement
            val incOrDecBy = splitted[2].toInt()
            val registerToCheck = splitted[4]
            val operation = when(splitted[5]) {
                ">" -> Operation.GreaterThan
                "<" -> Operation.LessThan
                ">=" -> Operation.GreaterOrEquals
                "<=" -> Operation.LessOrEquals
                "==" -> Operation.Equals
                "!=" -> Operation.NotEquals
                else -> throw IllegalArgumentException("Illegal operation: '${splitted[5]}' at '$splitted'")
            }
            val compareWith = splitted[6].toInt()

            if(!registers.containsKey(registerToCheck)) {
                registers.put(registerToCheck, 0)
            }
            if(!registers.containsKey(registerToBeModified)) {
                registers.put(registerToBeModified, 0)
            }

            val conditionIsTrue = when(operation) {
                Operation.GreaterThan -> registers[registerToCheck]!! > compareWith
                Operation.LessThan -> registers[registerToCheck]!! < compareWith
                Operation.GreaterOrEquals -> registers[registerToCheck]!! >= compareWith
                Operation.LessOrEquals -> registers[registerToCheck]!! <= compareWith
                Operation.Equals -> registers[registerToCheck]!! == compareWith
                Operation.NotEquals -> registers[registerToCheck]!! != compareWith
            }

            if(conditionIsTrue) {
                val newValue = if(instruction == Instruction.Increment)
                    registers[registerToBeModified]!! + incOrDecBy
                else
                    registers[registerToBeModified]!! - incOrDecBy

                registers[registerToBeModified] = newValue
            }
        }

        return registers.maxBy { it.value }!!.value
    }


    fun getHighestValueInRegistersAnytime(input: List<String>): Int {
        val registers: MutableMap<String, Int> = mutableMapOf()
        var highestValue = Int.MIN_VALUE
        input.forEach { line ->
            val splitted = line.split(" ").map(String::trim)

            val registerToBeModified = splitted[0]
            val instruction = if(splitted[1] == "inc") Instruction.Increment else Instruction.Decrement
            val incOrDecBy = splitted[2].toInt()
            val registerToCheck = splitted[4]
            val operation = when(splitted[5]) {
                ">" -> Operation.GreaterThan
                "<" -> Operation.LessThan
                ">=" -> Operation.GreaterOrEquals
                "<=" -> Operation.LessOrEquals
                "==" -> Operation.Equals
                "!=" -> Operation.NotEquals
                else -> throw IllegalArgumentException("Illegal operation: '${splitted[5]}' at '$splitted'")
            }
            val compareWith = splitted[6].toInt()

            if(!registers.containsKey(registerToCheck)) {
                registers.put(registerToCheck, 0)
            }
            if(!registers.containsKey(registerToBeModified)) {
                registers.put(registerToBeModified, 0)
            }

            val conditionIsTrue = when(operation) {
                Operation.GreaterThan -> registers[registerToCheck]!! > compareWith
                Operation.LessThan -> registers[registerToCheck]!! < compareWith
                Operation.GreaterOrEquals -> registers[registerToCheck]!! >= compareWith
                Operation.LessOrEquals -> registers[registerToCheck]!! <= compareWith
                Operation.Equals -> registers[registerToCheck]!! == compareWith
                Operation.NotEquals -> registers[registerToCheck]!! != compareWith
            }

            if(conditionIsTrue) {
                val newValue = if(instruction == Instruction.Increment)
                    registers[registerToBeModified]!! + incOrDecBy
                else
                    registers[registerToBeModified]!! - incOrDecBy

                registers[registerToBeModified] = newValue

                if(newValue > highestValue) {
                    highestValue = newValue
                }
            }
        }

        return highestValue
    }

}

class Day8Delegate : DayDelegate {

    override val day: Int = 8
    override val title: String = "Day 8: I Heard You Like Registers"

    companion object {
        val input: List<String> by lazy { InputStreamReader(FileInputStream(File("./src/main/resources/Day8Input.txt"))).readLines() }
    }


    private val processor: Day8Processor by lazy { Day8Processor() }

    override fun part1() {
        val bottomProgram = processor.getHighestValueInRegistersAfterInstructions(input)
        System.out.println("Highest value in register is " + bottomProgram)
    }

    override fun part2() {
        val neededWeightInUnbalancedTower = processor.getHighestValueInRegistersAnytime(input)
        System.out.println("Highest value ever in register is " + neededWeightInUnbalancedTower.toString())
    }

}