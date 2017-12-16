package at.co.moetz.adventofcode.day12

import at.co.moetz.adventofcode.DayDelegate
import java.io.File
import java.io.FileInputStream
import java.io.InputStream

class Day12Processor {

    fun getNumberOfConnectedPrograms(input: List<String>): Int {
        val programMap: MutableMap<Int, List<Int>> = mutableMapOf()

        input.forEach { line ->
            val programId = line.substring(0, line.indexOfFirst { it == ' ' }).toInt()
            val connectedPrograms = line
                    .substring(line.indexOfFirst { it == '>' } + 2)
                    .split(",")
                    .map(String::trim)
                    .map(String::toInt)

            programMap.put(programId, connectedPrograms)
        }

        val listOfProgramsConnectedWith0: MutableList<Int> = mutableListOf(0)

        fun addConnectedProgramIds(programId: Int) {
            programMap[programId]?.forEach { connectedProgramId ->
                if(!listOfProgramsConnectedWith0.contains(connectedProgramId)) {
                    listOfProgramsConnectedWith0.add(connectedProgramId)
                    addConnectedProgramIds(connectedProgramId)
                }
            }
        }

        addConnectedProgramIds(0)


        return listOfProgramsConnectedWith0.size
    }

    fun getNumberOfGroups(input: List<String>): Int {
//        val programMap: MutableMap<Int, List<Int>> = mutableMapOf()
//
//        input.forEach { line ->
//            val programId = line.substring(0, line.indexOfFirst { it == ' ' }).toInt()
//            val connectedPrograms = line
//                    .substring(line.indexOfFirst { it == '>' } + 2)
//                    .split(",")
//                    .map(String::trim)
//                    .map(String::toInt)
//
//            programMap.put(programId, connectedPrograms)
//        }
//
//
//
//        fun identifyAndDeleteGroupMembers(programId: Int) {
//            programMap[programId]?.forEach { connectedProgramId ->
//                if(programMap.containsKey(connectedProgramId) && connectedProgramId != programId) {
//                    identifyAndDeleteGroupMembers(connectedProgramId)
//                    programMap.remove(connectedProgramId)
//                }
//            }
//        }
//
//        var groups = 0
//
//        while(programMap.isNotEmpty()) {
//            groups++
//            val firstProgramInList = programMap.keys.first()
//            identifyAndDeleteGroupMembers(firstProgramInList)
//        }
//
//
//        return groups


        val programMap: MutableMap<Int, List<Int>> = mutableMapOf()

        input.forEach { line ->
            val programId = line.substring(0, line.indexOfFirst { it == ' ' }).toInt()
            val connectedPrograms = line
                    .substring(line.indexOfFirst { it == '>' } + 2)
                    .split(",")
                    .map(String::trim)
                    .map(String::toInt)

            programMap.put(programId, connectedPrograms)
        }

        val listOfProgramsInAnyGroup: MutableList<Int> = mutableListOf()

        fun addConnectedProgramIds(programId: Int) {
            if(!listOfProgramsInAnyGroup.contains(programId)) {
                listOfProgramsInAnyGroup.add(programId)
            }
            programMap[programId]?.forEach { connectedProgramId ->
                if(!listOfProgramsInAnyGroup.contains(connectedProgramId)) {
                    listOfProgramsInAnyGroup.add(connectedProgramId)
                    addConnectedProgramIds(connectedProgramId)
                }
            }
        }

        var numberOfGroups = 0

        while(listOfProgramsInAnyGroup.size < programMap.size) {
            numberOfGroups++
            addConnectedProgramIds(programMap.keys.first { !listOfProgramsInAnyGroup.contains(it) })
        }



        return numberOfGroups

    }

}

class Day12Delegate : DayDelegate {

    override val day: Int = 12
    override val title: String = "Day 12: Digital Plumber"

    companion object {
        val input: List<String> get() = FileInputStream(File("./src/main/resources/Day12Input.txt")).reader().readLines()
    }


    private val processor: Day12Processor by lazy { Day12Processor() }

    override fun part1() {
        val numberOfPrograms = processor.getNumberOfConnectedPrograms(input)
        System.out.println("The number of programs connected to program ID 0 is " + numberOfPrograms)
    }

    override fun part2() {
        val numberOfGroups = processor.getNumberOfGroups(input)
        System.out.println("The number of groups with connected programs is " + numberOfGroups)
    }

}