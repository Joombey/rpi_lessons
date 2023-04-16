import kotlinx.coroutines.*
import kotlinx.coroutines.channels.actor
import java.io.*
import java.net.Socket
import kotlin.random.Random
import kotlin.system.measureTimeMillis
import kotlin.time.ExperimentalTime
import kotlin.time.measureTime

const val MINIMAL_DILAY: Long = 1

@OptIn(ExperimentalTime::class)
fun main(args: Array<String>) {
    println("${measureTimeMillis {
        val client = Socket("127.0.0.1", 12345)
        val input: BufferedReader
        val output: BufferedWriter
        with(client) {
            input = BufferedReader(InputStreamReader(getInputStream()))
            output = BufferedWriter(OutputStreamWriter(getOutputStream()))
        }
        runBlocking {
            val sendJob = 
                launch { send(output) }
            val receiveJob = 
                launch { receive(input, output) }

            launch {
                repeat(100){
//                    println(it)
                    delay(MINIMAL_DILAY)
                }
                sendJob.cancel()
                receiveJob.cancel()
            }
        }
    }.toFloat() / 1000} секунд работы")
}

suspend fun send(output: BufferedWriter){
    coroutineScope {
        launch {
            while (true) {
                var value = Random.nextInt(1, 100)
                output.write("${value}\n")
                delay(MINIMAL_DILAY)
            }
        }
    }
}

suspend fun receive(input: BufferedReader, output: BufferedWriter){
    coroutineScope {
        launch {
            var intVal = 0
            while (true) {
                intVal  = Random.nextInt(1, 100)
                output.write(intVal)
                output.flush()
                println(input.readLine())
                delay(MINIMAL_DILAY)
            }
        }
    }
}