import kotlinx.coroutines.*
import java.io.*
import java.net.Inet4Address
import java.net.Socket
    import kotlin.concurrent.thread
    import kotlin.math.roundToInt
import kotlin.random.Random

fun main(args: Array<String>) {

    val client = Socket("127.0.0.1", 12345)
    val input: BufferedReader
    val output: BufferedWriter
    runBlocking {
        with(client) {
            input = BufferedReader(InputStreamReader(getInputStream()))
            output = BufferedWriter(OutputStreamWriter(getOutputStream()))
        }
//        launch {
//            var value = Random.nextInt(1, 100)
//            while (true) {
//                println(value)
//                output.write("${value}\n")
//                output.flush()
//                delay(Random.nextInt(500, 5000).toLong())
////                Thread.sleep(Random.nextInt(1000, 5000).toLong())
//            }
//        }
        launch {
            var intVal = 0
            while (true) {
                intVal  = Random.nextInt(1, 100)
//                println(intVal)
                output.write(intVal)
                output.flush()
                println(input.readLine())
//                    delay(Random.nextInt(100, 1000).toLong())
//                Thread.sleep(Random.nextInt(500, 1000).toLong())
            }
        }
    }
}