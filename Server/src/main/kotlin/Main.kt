import kotlinx.coroutines.*
import java.io.*
import java.net.ServerSocket
import kotlin.system.measureTimeMillis

const val MINIMAL_DILAY: Long = 100
fun main(args: Array<String>) {
    print("${measureTimeMillis {
        val server = ServerSocket(12345)
        val input: BufferedReader
        val output: BufferedWriter
        server.accept().apply {
            println("client connected $isConnected")
            input = BufferedReader(InputStreamReader(getInputStream()))
            output = BufferedWriter(OutputStreamWriter(getOutputStream()))
        }
        runBlocking {
            val receiveJob = 
                launch { receive(input) }  
//        receive(input)
            val sendJob = 
                launch { send(input, output) }
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

fun isEven(value: Int) = when (value % 2 == 0) {
    true -> "$value - чётное число"
    false -> "$value - нечётнео число"
}

suspend fun receive(input: BufferedReader) {
    coroutineScope {
        launch {
            var stringIncoming: String
            while (true) {
                stringIncoming = input.readLine()
                println("Received $stringIncoming")
                delay(MINIMAL_DILAY)
            }
        }
    }
}

suspend fun send(input: BufferedReader, output: BufferedWriter) {
    coroutineScope {
        launch {
            var resultString: String
            var incoming: Int
            while (true) {
                incoming = input.read()
                resultString = isEven(incoming)
                output.write("$resultString\n")
                output.flush()
                delay(MINIMAL_DILAY)
            }
        }
    }
}


