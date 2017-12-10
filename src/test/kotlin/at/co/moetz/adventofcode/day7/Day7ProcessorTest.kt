package at.co.moetz.adventofcode.day7

import org.junit.Assert.*
import org.junit.Before
import org.junit.Test


class Day7ProcessorTest {
    private lateinit var processor: Day7Processor

    @Before
    fun setUp() {
        processor = Day7Processor()
    }

    @Test
    fun getNameOfBottomProgramExampleTestcase() {
        val input = listOf("pbga (66)",
                "xhth (57)",
                "ebii (61)",
                "havc (66)",
                "ktlj (57)",
                "fwft (72) -> ktlj, cntj, xhth",
                "qoyq (66)",
                "padx (45) -> pbga, havc, qoyq",
                "tknk (41) -> ugml, padx, fwft",
                "jptl (61)",
                "ugml (68) -> gyxo, ebii, jptl",
                "gyxo (61)",
                "cntj (57)")

        val captcha = processor.getNameOfBottomProgram(input)

        assertEquals(
                "In this example, tknk is at the bottom of the tower (the bottom program), and is holding up ugml, padx, and fwft. Those programs are, in turn, holding up other programs; in this example, none of those programs are holding up any other programs, and are all the tops of their own towers. (The actual tower balancing in front of you is much larger.)",
                "tknk",
                captcha
        )
    }

    @Test
    fun getNameOfBottomProgramRealPuzzle() {
        val input = Day7Delegate.input

        val captcha = processor.getNameOfBottomProgram(input)

        assertEquals(
                "Your puzzle answer was azqje.",
                "azqje",
                captcha
        )
    }



    @Test
    fun calculateNeededWeightForUnbalancedTowerExampleTestcase() {
        val input = listOf("pbga (66)",
                "xhth (57)",
                "ebii (61)",
                "havc (66)",
                "ktlj (57)",
                "fwft (72) -> ktlj, cntj, xhth",
                "qoyq (66)",
                "padx (45) -> pbga, havc, qoyq",
                "tknk (41) -> ugml, padx, fwft",
                "jptl (61)",
                "ugml (68) -> gyxo, ebii, jptl",
                "gyxo (61)",
                "cntj (57)")

        val captcha = processor.calculateNeededWeightForUnbalancedTower(input)

        assertEquals(
                "However, for tknk to be balanced, each of the programs standing on its disc and all programs above it must each match. This means that the following sums must all be the same:\n" +
                        "\n" +
                        "ugml + (gyxo + ebii + jptl) = 68 + (61 + 61 + 61) = 251\n" +
                        "padx + (pbga + havc + qoyq) = 45 + (66 + 66 + 66) = 243\n" +
                        "fwft + (ktlj + cntj + xhth) = 72 + (57 + 57 + 57) = 243\n" +
                        "As you can see, tknk's disc is unbalanced: ugml's stack is heavier than the other two. Even though the nodes above ugml are balanced, ugml itself is too heavy: it needs to be 8 units lighter for its stack to weigh 243 and keep the towers balanced. If this change were made, its weight would be 60.",
                60,
                captcha
        )
    }

    @Test
    fun calculateNeededWeightForUnbalancedTowerRealPuzzle() {
        val input = Day7Delegate.input

        val captcha = processor.calculateNeededWeightForUnbalancedTower(input)

        assertEquals(
                "Your puzzle answer was 646.",
                646,
                captcha
        )
    }

}