package at.co.moetz.adventofcode.day2

import at.co.moetz.adventofcode.DayDelegate


/**
 * --- Day 2: Corruption Checksum ---
 *
 * As you walk through the door, a glowing humanoid shape yells in your direction.
 * `"You there! Your state appears to be idle. Come help us repair the corruption in this spreadsheet - if we take
 * another millisecond, we'll have to display an hourglass cursor!"`
 */
class Day2Processor {

    /**
     * The spreadsheet consists of rows of apparently-random numbers. To make sure the recovery process is on the right
     * track, they need you to calculate the spreadsheet's checksum.
     * For each row, determine the difference between the largest value and the smallest value; the checksum is the
     * sum of all of these differences.
     *
     * For example, given the following spreadsheet:
     * * `5 1 9 5`
     * * `7 5 3`
     * * `2 4 6 8`
     *
     *
     * * The first row's largest and smallest values are 9 and 1, and their difference is 8.
     * * The second row's largest and smallest values are 7 and 3, and their difference is 4.
     * * The third row's difference is 6.
     * * In this example, the spreadsheet's checksum would be 8 + 4 + 6 = 18.
     *
     * What is the checksum for the spreadsheet in your puzzle input?
     */
    fun calculateChecksum(input: String): Long {
        return input.split("\n")
                .map { line ->
                    var min: Int = Int.MAX_VALUE
                    var max: Int = Int.MIN_VALUE
                    line.split("\t")
                            .map { item -> item.toInt() }
                            .forEach { number ->
                                if (number < min) {
                                    min = number
                                }
                                if (number > max) {
                                    max = number
                                }
                            }
                    //calculate diff between min and max:
                    max - min
                }
                .sum().toLong()
    }

    /**
     * --- Part Two ---
     *
     * "Great work; looks like we're on the right track after all. Here's a star for your effort."
     * However, the program seems a little worried. Can programs be worried?
     *
     * "Based on what we're seeing, it looks like all the User wanted is some information about the evenly divisible
     * values in the spreadsheet. Unfortunately, none of us are equipped for that kind of calculation - most of us
     * specialize in bitwise operations."
     *
     * It sounds like the goal is to find the only two numbers in each row where one evenly divides the other - that is,
     * where the result of the division operation is a whole number. They would like you to find those numbers on each
     * line, divide them, and add up each line's result.
     *
     * For example, given the following spreadsheet:
     * * `5 9 2 8`
     * * `9 4 7 3`
     * * `3 8 6 5`
     *
     *
     * * In the first row, the only two numbers that evenly divide are 8 and 2; the result of this division is 4.
     * * In the second row, the two numbers are 9 and 3; the result is 3.
     * * In the third row, the result is 2.
     * * In this example, the sum of the results would be 4 + 3 + 2 = 9.
     *
     * What is the sum of each row's result in your puzzle input?
     */
    fun calculateChecksumWithEvenlyDivisibleValues(input: String): Long {
        return input.split("\n")
                .map<String, Int> { line ->
                    //map line to a list of numbers
                    val numbers = line.split("\t").map { item -> item.toInt() }

                    // iterate through each item, and in each iteration iterate through all items which have a bigger
                    // index than the item iterating, thus getting each combination of values in the list
                    numbers.forEachIndexed { outerIndex, outerNumber ->
                        //don't need to check last item, as it would be checking against itself (or in this implementation throw a IndexOutOfBoundsException)
                        if (outerIndex < numbers.lastIndex) {
                            numbers.subList(fromIndex = outerIndex + 1, toIndex = numbers.lastIndex + 1)
                                    .forEachIndexed { index, innerNumber ->
                                        //check if the values are evenly divisible (need to check which number is the bigger one)
                                        val isEvenlyDivisible = when {
                                            outerNumber > innerNumber -> outerNumber.rem(innerNumber) == 0
                                            else -> innerNumber.rem(outerNumber) == 0
                                        }

                                        //return the result of the division (if they are evenly divisible) at the map,
                                        // as this is the resulting checksum of the whole line
                                        if (isEvenlyDivisible) {
                                            return@map when {
                                                outerNumber > innerNumber -> outerNumber.div(innerNumber)
                                                else -> innerNumber.div(outerNumber)
                                            }
                                        }
                                    }
                        }
                    }
                    throw IllegalStateException("No evenly divisible numbers found")
                }
                .sum().toLong()
    }

}


class Day2Delegate : DayDelegate {

    override val day: Int = 2
    override val title: String = "Day 2: Corruption Checksum"

    companion object {
        const val input = "1364\t461\t1438\t1456\t818\t999\t105\t1065\t314\t99\t1353\t148\t837\t590\t404\t123\n" +
                "204\t99\t235\t2281\t2848\t3307\t1447\t3848\t3681\t963\t3525\t525\t288\t278\t3059\t821\n" +
                "280\t311\t100\t287\t265\t383\t204\t380\t90\t377\t398\t99\t194\t297\t399\t87\n" +
                "7698\t2334\t7693\t218\t7344\t3887\t3423\t7287\t7700\t2447\t7412\t6147\t231\t1066\t248\t208\n" +
                "3740\t837\t4144\t123\t155\t2494\t1706\t4150\t183\t4198\t1221\t4061\t95\t148\t3460\t550\n" +
                "1376\t1462\t73\t968\t95\t1721\t544\t982\t829\t1868\t1683\t618\t82\t1660\t83\t1778\n" +
                "197\t2295\t5475\t2886\t2646\t186\t5925\t237\t3034\t5897\t1477\t196\t1778\t3496\t5041\t3314\n" +
                "179\t2949\t3197\t2745\t1341\t3128\t1580\t184\t1026\t147\t2692\t212\t2487\t2947\t3547\t1120\n" +
                "460\t73\t52\t373\t41\t133\t671\t61\t634\t62\t715\t644\t182\t524\t648\t320\n" +
                "169\t207\t5529\t4820\t248\t6210\t255\t6342\t4366\t5775\t5472\t3954\t3791\t1311\t7074\t5729\n" +
                "5965\t7445\t2317\t196\t1886\t3638\t266\t6068\t6179\t6333\t229\t230\t1791\t6900\t3108\t5827\n" +
                "212\t249\t226\t129\t196\t245\t187\t332\t111\t126\t184\t99\t276\t93\t222\t56\n" +
                "51\t592\t426\t66\t594\t406\t577\t25\t265\t578\t522\t57\t547\t65\t564\t622\n" +
                "215\t2092\t1603\t1001\t940\t2054\t245\t2685\t206\t1043\t2808\t208\t194\t2339\t2028\t2580\n" +
                "378\t171\t155\t1100\t184\t937\t792\t1436\t1734\t179\t1611\t1349\t647\t1778\t1723\t1709\n" +
                "4463\t4757\t201\t186\t3812\t2413\t2085\t4685\t5294\t5755\t2898\t200\t5536\t5226\t1028\t180"
    }


    private val processor: Day2Processor by lazy { Day2Processor() }

    override fun part1() {
        val checksum = processor.calculateChecksumWithEvenlyDivisibleValues(input)
        System.out.println("Checksum with evenly divisible numbers is " + checksum.toString())
    }

    override fun part2() {
        val checksum = processor.calculateChecksumWithEvenlyDivisibleValues(input)
        System.out.println("Checksum with evenly divisible numbers is " + checksum.toString())
    }

}
