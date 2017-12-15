package at.co.moetz.adventofcode.day10

import at.co.moetz.adventofcode.DayDelegate

class Day10Processor {

    fun applyTwistHash(input: String, listSize: Int = 256): Long {
        val list = (0 until listSize).map { it }.toMutableList()

        var position = 0
        var skipSize = 0

        fun reverseOrderOfElements(startIndex: Int, numElements: Int) {
            val copiedList = list.toList()
            (0 until numElements)
                    .map { copyingIndex ->
                        val writeIndex = (startIndex + copyingIndex) % listSize
                        val readIndex = (startIndex + numElements - copyingIndex - 1) % listSize
                        Pair(writeIndex, readIndex)
                    }
                    .forEach { (writeIndex, readIndex) ->
                        list[writeIndex] = copiedList[readIndex]
                    }
        }

        input.split(",")
                .map(String::trim)
                .map(String::toInt)
                .forEach { length ->
                    if (length <= listSize) {
                        reverseOrderOfElements(position, length)
                        position += (length + skipSize) % listSize
                        skipSize++
                    }
                }

        return list[0].toLong() * list[1].toLong()
    }

    fun generateHash(input: String): String {
        val listSize = 256
        val list = (0 until listSize).map { it }.toMutableList()

        val numberOfRounds = 64

        var position = 0
        var skipSize = 0

        val realInput = input.trim()
                .toCharArray()
                .map { it.toInt() }
                .toMutableList()
                .apply { addAll(listOf(17, 31, 73, 47, 23)) }
                .toList()

        fun reverseOrderOfElements(startIndex: Int, numElements: Int) {
            val copiedList = list.toList()
            (0 until numElements)
                    .map { copyingIndex ->
                        val writeIndex = (startIndex + copyingIndex) % listSize
                        val readIndex = (startIndex + numElements - copyingIndex - 1) % listSize
                        Pair(writeIndex, readIndex)
                    }
                    .forEach { (writeIndex, readIndex) ->
                        list[writeIndex] = copiedList[readIndex]
                    }
        }

        (0 until numberOfRounds).forEach {
            realInput
                    .forEach { length ->
                        if (length <= listSize) {
                            reverseOrderOfElements(position, length)
                            position += (length + skipSize) % listSize
                            skipSize++
                        }
                    }
        }


        val sparseHash = list.toList()

        val denseHash = (0 until 16).map { denseHashIndex ->
            sparseHash[(denseHashIndex * 16)] xor
                    sparseHash[(denseHashIndex * 16) + 1] xor
                    sparseHash[(denseHashIndex * 16) + 2] xor
                    sparseHash[(denseHashIndex * 16) + 3] xor
                    sparseHash[(denseHashIndex * 16) + 4] xor
                    sparseHash[(denseHashIndex * 16) + 5] xor
                    sparseHash[(denseHashIndex * 16) + 6] xor
                    sparseHash[(denseHashIndex * 16) + 7] xor
                    sparseHash[(denseHashIndex * 16) + 8] xor
                    sparseHash[(denseHashIndex * 16) + 9] xor
                    sparseHash[(denseHashIndex * 16) + 10] xor
                    sparseHash[(denseHashIndex * 16) + 11] xor
                    sparseHash[(denseHashIndex * 16) + 12] xor
                    sparseHash[(denseHashIndex * 16) + 13] xor
                    sparseHash[(denseHashIndex * 16) + 14] xor
                    sparseHash[(denseHashIndex * 16) + 15]
        }

        return denseHash.joinToString(separator = "") { String.format("%02x", it) }
    }

}

class Day10Delegate : DayDelegate {

    override val day: Int = 10
    override val title: String = "Day 10: Knot Hash"

    companion object {
        val input: String = "34,88,2,222,254,93,150,0,199,255,39,32,137,136,1,167"
    }


    private val processor: Day10Processor by lazy { Day10Processor() }

    override fun part1() {
        val hash = processor.applyTwistHash(input)
        System.out.println("The multiplying of the first two numbers is " + hash)
    }

    override fun part2() {
        val hash = processor.generateHash(input)
        System.out.println("The dense hash in hex format is " + hash)
    }

}