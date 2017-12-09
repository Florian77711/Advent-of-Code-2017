package at.co.moetz.adventofcode.day4

import at.co.moetz.adventofcode.DayDelegate
import java.io.File
import java.io.FileInputStream
import java.io.InputStreamReader

class Day4Processor {


    private fun isLineValidPassphrase(line: String): Boolean {
        val listOfWords = mutableListOf<String>()
        line.split(" ").forEach {
            if(listOfWords.contains(it)) {
                return false
            }
            listOfWords.add(it)
        }
        return true
    }

    fun countValidPassphrases(input: List<String>): Int {
        return input
                .map(this::isLineValidPassphrase)
                .map { if(it) 1 else 0 }
                .sum()
    }




    private fun isLineValidPassphraseWithAddedSecurity(line: String): Boolean {
        val listOfCharacters = mutableListOf<Map<Char, Int>>()
        line.split(" ").forEach { word ->
            val map = mutableMapOf<Char, Int>()

            word.forEach {
                if(map.containsKey(it)) {
                    map.put(it, map[it]!!+1)
                } else {
                    map.put(it, 1)
                }
            }

            if(listOfCharacters.contains(map)) {
                return false
            }
            listOfCharacters.add(map)
        }
        return true
    }

    fun countValidPassphrasesWithAddedSecurity(input: List<String>): Int {
        return input
                .map(this::isLineValidPassphraseWithAddedSecurity)
                .map { if(it) 1 else 0 }
                .sum()
    }

}

class Day4Delegate : DayDelegate {

    override val day: Int = 4
    override val title: String = "Day 4: High-Entropy Passphrases"

    companion object {
        val input: List<String> by lazy { InputStreamReader(FileInputStream(File("./src/main/resources/Day4Input.txt"))).readLines() }
    }


    private val processor: Day4Processor by lazy { Day4Processor() }

    override fun part1() {
        val validCount = processor.countValidPassphrases(input)
        System.out.println("Number of valid passphrases is " + validCount.toString())
    }

    override fun part2() {
        val validCount = processor.countValidPassphrasesWithAddedSecurity(input)
        System.out.println("Number of valid passphrases with added security is " + validCount.toString())
    }

}