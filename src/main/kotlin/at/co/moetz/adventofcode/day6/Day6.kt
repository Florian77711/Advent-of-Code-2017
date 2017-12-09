package at.co.moetz.adventofcode.day6

import at.co.moetz.adventofcode.DayDelegate
import java.io.File
import java.io.FileInputStream
import java.io.InputStreamReader

class Day6Processor {

    fun countRedistributionCyclesTillSeenBefore(input: List<Int>): Int {
        val mutableInput = input.toMutableList()
        val numberOfBlocks = mutableInput.size

        val listOfSeenMappings = mutableListOf(input)

        var numberOfRedistributions = 0

        while(true) {
            var maxValue = 0
            var chosenIndex = 0
            mutableInput.forEachIndexed { index, it ->
                if(it > maxValue) {
                    chosenIndex = index
                    maxValue = it
                }
            }
            val blocksInChosenBank = mutableInput[chosenIndex]
            mutableInput[chosenIndex] = 0
            (1..blocksInChosenBank).forEach {
                mutableInput[(chosenIndex+it) % numberOfBlocks]++
            }

            numberOfRedistributions++

            if(listOfSeenMappings.contains(mutableInput)) {
                return numberOfRedistributions
            }
            listOfSeenMappings.add(mutableInput.toList())
        }
    }


    fun countRedistributionCyclesBetweenIdenticalStates(input: List<Int>): Int {
        val mutableInput = input.toMutableList()
        val numberOfBlocks = mutableInput.size

        val listOfSeenMappings = mutableListOf(input)

        var numberOfRedistributions = 0

        while(true) {
            var maxValue = 0
            var chosenIndex = 0
            mutableInput.forEachIndexed { index, it ->
                if(it > maxValue) {
                    chosenIndex = index
                    maxValue = it
                }
            }
            val blocksInChosenBank = mutableInput[chosenIndex]
            mutableInput[chosenIndex] = 0
            (1..blocksInChosenBank).forEach {
                mutableInput[(chosenIndex+it) % numberOfBlocks]++
            }

            numberOfRedistributions++

            if(listOfSeenMappings.contains(mutableInput)) {
                return numberOfRedistributions - listOfSeenMappings.indexOf(mutableInput)
            }
            listOfSeenMappings.add(mutableInput.toList())
        }
    }

}

class Day6Delegate : DayDelegate {

    override val day: Int = 6
    override val title: String = "Day 6: Memory Reallocation"

    companion object {
        val input: List<Int> by lazy { "11\t11\t13\t7\t0\t15\t5\t5\t4\t4\t1\t1\t7\t1\t15\t11".split("\t").map { it.toInt() } }
    }


    private val processor: Day6Processor by lazy { Day6Processor() }

    override fun part1() {
        val redistributionCircles = processor.countRedistributionCyclesTillSeenBefore(input)
        System.out.println("Number of redistribution circles is " + redistributionCircles.toString())
    }

    override fun part2() {
        val redistributionCircles = processor.countRedistributionCyclesBetweenIdenticalStates(input)
        System.out.println("Number of redistribution circles within identical states is " + redistributionCircles.toString())
    }

}