import java.io.*
import java.net.Inet4Address
import java.net.Socket
import kotlin.concurrent.thread
import kotlin.math.roundToInt
import kotlin.random.Random

fun main(args: Array<String>) {

    val client = Socket("127.0.0.1", 12345)

    with(client) {
        val input = BufferedReader(InputStreamReader(getInputStream()))
        val output = BufferedWriter(OutputStreamWriter(getOutputStream()))

        thread {
            while (true) {
                output.write(Random.nextInt(1, 100))
                output.flush()
                println(input.readLine())
//                Thread.sleep(Random.nextInt(500, 1000).toLong())
            }
        }

        thread {
            while (true) {
                output.write("${Random.nextInt(1, 100)}\n")
//                Thread.sleep(Random.nextInt(1000, 5000).toLong())
            }
        }
    }
}