import at.co.moetz.adventofcode.DayDelegate
import at.co.moetz.adventofcode.day1.Day1Delegate
import at.co.moetz.adventofcode.day2.Day2Delegate
import at.co.moetz.adventofcode.day3.Day3Delegate
import at.co.moetz.adventofcode.day4.Day4Delegate
import at.co.moetz.adventofcode.day5.Day5Delegate


fun main(args: Array<String>) {
    DayDelegateManager().run()
}


class DayDelegateManager {

    val lastDay: Int = 5

    private val delegates: MutableMap<Int, DayDelegate> = mutableMapOf()


    init {
        add(Day1Delegate())
        add(Day2Delegate())
        add(Day3Delegate())
        add(Day4Delegate())
        add(Day5Delegate())
    }

    private fun add(delegate: DayDelegate) {
        delegates.put(delegate.day, delegate)
    }

    fun run() {
        (1..lastDay)
                .map { delegates[it]!! }
                .forEach {
                    wrapDayWithConsoleOutput(it.title, it::part1, it::part2)
                }
    }


    private inline fun wrapDayWithConsoleOutput(title: String, part1: () -> Unit, part2: () -> Unit) {
        System.out.println("\n\n--- $title ---\n")
        part1.invoke()
        System.out.println("\n--- Part Two ---\n")
        part2.invoke()
    }

}
