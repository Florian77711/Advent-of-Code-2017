package at.co.moetz.adventofcode.day7

import at.co.moetz.adventofcode.DayDelegate
import java.io.File
import java.io.FileInputStream
import java.io.InputStreamReader

class Day7Processor {

    data class Tower(
            val weight: Int,
            var weightWithChildren: Int = 0,
            val holds: List<String>
    )

    fun getNameOfBottomProgram(input: List<String>): String {
        val towerMap: MutableMap<String, Tower> = mutableMapOf()
        input
                .forEach { line ->
                    val firstSpaceIndex = line.indexOfFirst { it == ' ' }
                    val name = line.substring(0, firstSpaceIndex)
                    val weight = line.substring(
                            line.indexOfFirst { it == '(' } + 1,
                            line.indexOfFirst { it == ')' }
                    )
                            .toInt()
                    val splitted = line.split(" -> ", limit = 2)

                    val holds = if (splitted.size == 2) {
                        splitted.component2().split(",").map(String::trim)
                    } else {
                        emptyList()
                    }

                    towerMap.put(name, Tower(
                            weight = weight,
                            holds = holds
                    ))
                }
        towerMap.forEach { inspectingTower ->
            if (!towerMap.any { it.value.holds.contains(inspectingTower.key) }) {
                return inspectingTower.key
            }
        }

        throw IllegalStateException("No bottom program found")
    }


    fun calculateNeededWeightForUnbalancedTower(input: List<String>): Int {
        val towerMap: MutableMap<String, Tower> = mutableMapOf()
        input
                .forEach { line ->
                    val firstSpaceIndex = line.indexOfFirst { it == ' ' }
                    val name = line.substring(0, firstSpaceIndex)
                    val weight = line.substring(
                            line.indexOfFirst { it == '(' } + 1,
                            line.indexOfFirst { it == ')' }
                    )
                            .toInt()
                    val splitted = line.split(" -> ", limit = 2)

                    val holds = if (splitted.size == 2) {
                        splitted.component2().split(",").map(String::trim)
                    } else {
                        emptyList()
                    }

                    towerMap.put(name, Tower(
                            weight = weight,
                            weightWithChildren = if(holds.isEmpty()) weight else 0,
                            holds = holds
                    ))
                }
        //fill all weights with children in towers:
        while(towerMap.any { it.value.weightWithChildren == 0 }) {
            towerMap.forEach { tower ->
                if (tower.value.weightWithChildren == 0) {
                    if(tower.value.holds.all { towerMap[it]!!.weightWithChildren != 0 }) {
                        tower.value.weightWithChildren = tower.value.holds.sumBy { towerMap[it]!!.weightWithChildren } + tower.value.weight
                    }
                }
            }
        }

        //get bottom tower:
        var bottomTower: Tower? = null
        towerMap.forEach { inspectingTower ->
            if (!towerMap.any { it.value.holds.contains(inspectingTower.key) }) {
                bottomTower = inspectingTower.value
            }
        }

        var unbalancedTower = bottomTower!!
        var prevTower = unbalancedTower

        while(!unbalancedTower.holds.map { towerMap[it]!!.weightWithChildren }.all { it == towerMap[unbalancedTower.holds.first()]!!.weightWithChildren }) {

            val weightList = unbalancedTower.holds.map { towerMap[it]!!.weightWithChildren }
            val neededWeight = if(weightList.component1() == weightList.component2()) {
                weightList.component1()
            } else if(weightList.component2() == weightList.component3()) {
                weightList.component2()
            } else {
                weightList.component1()
            }

            prevTower = unbalancedTower
            unbalancedTower = unbalancedTower.holds.first { towerMap[it]!!.weightWithChildren != neededWeight }.let { towerMap[it]!! }
        }

        val weightNeedsToBe = prevTower.holds.map { towerMap[it]!!.weightWithChildren }.first { it != unbalancedTower.weightWithChildren }

        return weightNeedsToBe - (unbalancedTower.holds.first().let { towerMap[it]!!.weightWithChildren } * unbalancedTower.holds.size)
    }

}

class Day7Delegate : DayDelegate {

    override val day: Int = 7
    override val title: String = "Day 7: Recursive Circus"

    companion object {
        val input: List<String> by lazy { InputStreamReader(FileInputStream(File("./src/main/resources/Day7Input.txt"))).readLines() }
    }


    private val processor: Day7Processor by lazy { Day7Processor() }

    override fun part1() {
        val bottomProgram = processor.getNameOfBottomProgram(input)
        System.out.println("Name of bottom program is " + bottomProgram)
    }

    override fun part2() {
        val neededWeightInUnbalancedTower = processor.calculateNeededWeightForUnbalancedTower(input)
        System.out.println("Number of redistribution circles within identical states is " + neededWeightInUnbalancedTower.toString())
    }

}