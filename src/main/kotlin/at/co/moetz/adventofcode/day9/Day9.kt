package at.co.moetz.adventofcode.day9

import at.co.moetz.adventofcode.DayDelegate
import java.io.File
import java.io.FileInputStream
import java.io.InputStream
import java.io.InputStreamReader

class Day9Processor {

    fun findTotalScore(inputStream: InputStream): Int {
        val reader = InputStreamReader(inputStream)

        fun getNextChar(): Char {
            return reader.read().toChar()
        }

        var score = 0

        fun moveTillEndOfGarbage() {
            var isNextCharacterEscaped = false
            var character = getNextChar()
            while (character != '>' || isNextCharacterEscaped) {
                if (character == '!' && !isNextCharacterEscaped) {
                    isNextCharacterEscaped = true
                } else {
                    isNextCharacterEscaped = false
                }
                character = getNextChar()
            }
        }

        fun processPiece(level: Int) {
            score += level

            var character = getNextChar()
            while (character != '}') {
                when (character) {
                    '{' -> processPiece(level + 1)
                    '<' -> moveTillEndOfGarbage()
                }
                character = getNextChar()
            }
        }


        var character = reader.read()
        while (character != -1) {
            if (character.toChar() == '{') {
                processPiece(1)
                character = reader.read()
            }
        }


        return score
    }

    fun countValidGarbageCharacters(inputStream: InputStream): Int {
        val reader = InputStreamReader(inputStream)

        fun getNextChar(): Char {
            return reader.read().toChar()
        }

        var validGarbageCharacterCount = 0

        fun moveTillEndOfGarbage() {
            var isNextCharacterEscaped = false
            var character = getNextChar()
            while (character != '>' || isNextCharacterEscaped) {
                if (character == '!' && !isNextCharacterEscaped) {
                    isNextCharacterEscaped = true
                } else {
                    if (!isNextCharacterEscaped) {
                        validGarbageCharacterCount++
                    }
                    isNextCharacterEscaped = false
                }
                character = getNextChar()
            }
        }

        fun processPiece() {
            var character = getNextChar()
            while (character != '}') {
                when (character) {
                    '{' -> processPiece()
                    '<' -> moveTillEndOfGarbage()
                }
                character = getNextChar()
            }
        }


        var character = reader.read()
        while (character != -1) {
            if (character.toChar() == '{') {
                processPiece()
                character = reader.read()
            }
        }


        return validGarbageCharacterCount
    }

}

class Day9Delegate : DayDelegate {

    override val day: Int = 9
    override val title: String = "Day 9: Stream Processing"

    companion object {
        val input: InputStream get() = FileInputStream(File("./src/main/resources/Day9Input.txt"))
    }


    private val processor: Day9Processor by lazy { Day9Processor() }

    override fun part1() {
        val totalScore = processor.findTotalScore(input)
        System.out.println("The total score is " + totalScore)
    }

    override fun part2() {
        val garbageCharacterCount = processor.countValidGarbageCharacters(input)
        System.out.println("Number of non-canceled characters within the garbage is " + garbageCharacterCount)
    }

}