package at.co.moetz.adventofcode.day1

import at.co.moetz.adventofcode.DayDelegate


/**
 * --- Day 1: Inverse Captcha ---
 *
 * The night before Christmas, one of Santa's Elves calls you in a panic.
 * `"The printer's broken! We can't print the Naughty or Nice List!"`
 * By the time you make it to sub-basement 17, there are only a few minutes until midnight.
 * `"We have a big problem,"` she says; `"there must be almost fifty bugs in this system, but nothing else can print
 * The List. Stand in this square, quick! There's no time to explain; if you can convince them to pay you in stars,
 * you'll be able to--"`
 * She pulls a lever and the world goes blurry.
 *
 * When your eyes can focus again, everything seems a lot more pixelated than before. She must have sent you inside the
 * computer! You check the system clock: 25 milliseconds until midnight. With that much time, you should be able to
 * collect all fifty stars by December 25th.
 *
 * Collect stars by solving puzzles. Two puzzles will be made available on each day millisecond in the advent calendar;
 * the second puzzle is unlocked when you complete the first. Each puzzle grants one star. Good luck!
 *
 * You're standing in a room with `"digitization quarantine"` written in LEDs along one wall.
 * The only door is locked, but it includes a small interface.
 * `"Restricted Area - Strictly No Digitized Users Allowed."`
 */
class Day1Processor {

    /**
     * You're standing in a room with "digitization quarantine" written in LEDs along one wall.
     * The only door is locked, but it includes a small interface.
     * `"Restricted Area - Strictly No Digitized Users Allowed."`
     *
     * It goes on to explain that you may only leave by solving a captcha to prove you're not a human.
     * Apparently, you only get one millisecond to solve the captcha: too fast for a normal human,
     * but it feels like hours to you.
     *
     * The captcha requires you to review a sequence of digits (your puzzle input) and find the sum of all digits that
     * match the next digit in the list. The list is circular, so the digit after the last digit is the first digit
     * in the list.
     *
     * For example:
     * * `1122` produces a sum of 3 (1 + 2) because the first digit (1) matches the second digit and the third digit (2) matches the fourth digit.
     * * `1111` produces 4 because each digit (all 1) matches the next.
     * * `1234` produces 0 because no digit matches the next.
     * * `91212129` produces 9 because the only digit that matches the next one is the last digit, 9.
     * * What is the solution to your captcha?
     *
     * @param input The puzzle input as returned at `http://adventofcode.com/2017/day/1/input`
     * @return a Long containing the captcha (which is the solution to the puzzle)
     */
    fun calculateCaptchaComparingNextElement(input: String): Long {
        val lastIndex = input.lastIndex

        val characters = input.asIterable()
        var captchaSum: Long = 0

        characters.forEachIndexed { index, character ->
            //compare with the next character, the next character for the last element is the first element
            val nextCharacter = when (index) {
                lastIndex -> characters.first()
                else -> characters.elementAt(index.plus(1))
            }

            if (character == nextCharacter) {
                //number needs to be added to sum
                //substract by '0' as not the ASCII-value, but the 'real' integer value is needed
                captchaSum += character.minus('0')
            }
        }
        return captchaSum
    }


    /**
     * --- Part Two ---
     *
     * You notice a progress bar that jumps to 50% completion.
     * Apparently, the door isn't yet satisfied, but it did emit a star as encouragement.
     * The instructions change:
     *
     * Now, instead of considering the next digit, it wants you to consider the digit halfway around the circular list.
     * That is, if your list contains 10 items, only include a digit in your sum if the digit 10/2 = 5 steps forward
     * matches it. Fortunately, your list has an even number of elements.
     *
     * For example:
     * * `1212` produces 6: the list contains 4 items, and all four digits match the digit 2 items ahead.
     * * `1221` produces 0, because every comparison is between a 1 and a 2.
     * * `123425` produces 4, because both 2s match each other, but no other digit has a match.
     * * `123123` produces 12.
     * * `12131415` produces 4.
     *
     * What is the solution to your new captcha?
     *
     * @param input The puzzle input as returned at `http://adventofcode.com/2017/day/1/input`
     * @return a Long containing the new captcha (which is the solution to the puzzle's second part)
     */
    fun calculateCaptchaComparingHalfwayRound(input: String): Long {
        val count = input.length
        val halfCount = count / 2

        var captchaSum: Long = 0

        val characters = input.asIterable()
        characters.forEachIndexed { index, character ->
            //compare the character with the one halfway across the sequence (first item follows the last item -> modulo)
            val comparingCharacter = characters.elementAt(index.plus(halfCount).rem(count))

            if (character == comparingCharacter) {
                //number needs to be added to sum
                //substract by '0' as not the ASCII-value, but the 'real' integer value is needed
                captchaSum += character.minus('0')
            }
        }

        return captchaSum
    }

}


class Day1Delegate : DayDelegate {

    override val day: Int = 1
    override val title: String = "Day 1: Inverse Captcha"

    companion object {
        const val input = "892195969991735837915273868729548694237967495115412399373194562526947585337233793568278265279199883197167634791293177986152566236718332617536487236879747167999983363832257912445756887314879229925864477761357139855548522513798899853896612387146687716264599943289416326727256525173953861534244979466587895429399159924916364476319573895566795393368411672387263615582128377676293612892723762237191146714286233543514411813323197995953854871628225358543514157867372265718724276911699514971458844849349726276329135118243155698271218844347387457343656446381799296893222256198484465873714311777937421161581798189554141474236239447612421883232173914183732126332838194648583472419154369952477422666389517569944428464617457124369349242479612422673241361777576466946622932243728551273284837934497511114334421486262244982914734452113946361245377351849815584855691778894798219822463298387771923329337634394654439458564233259451453345316753241438267739439225497515276522424441532462541528195782818326918562247278496495764435386667383577543385186827269732261223156824351164841648424564925198783625721396988984481558391866483955533972212164693898955412719161648411279149413443192896864258215498543827458438871355879336892721675937111952479183496982825163456282747678364612135596373533447719867384667516572262124225585623974278833981365494628646614588114147473559138853453189448624976774641922469183942857695986376428944876851497914443873513862319484181787593572987444669767939526294424531262999564948571142342741129862311311313166798363442745792896227642881893134498151552326647933689596516859342242244584714818773791567187322217164347852843751875979415198165627534263527828414549217234322361937785185174993256753483876378332521824515977173397535784236923629636713469151526399149548322849831431526219478653861754364155275865511643923249858589466142474763778413826829226663398467569555747267195129525138917561785436449855933951538973995881954521124753369223898312843734771532342383282987422334196585128526526324291777689689492346231786335851551413876834969878"
    }

    private val processor: Day1Processor by lazy { Day1Processor() }

    override fun part1() {
        val captcha = processor.calculateCaptchaComparingNextElement(input)
        System.out.println("Captcha1 (compare with next character) is " + captcha.toString())
    }

    override fun part2() {
        val captcha = processor.calculateCaptchaComparingHalfwayRound(input)
        System.out.println("Captcha (compare with character halfway round) is " + captcha.toString())
    }

}
