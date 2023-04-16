import java.io.*
import kotlin.collections.toByteArray
import java.net.ServerSocket

import kotlin.concurrent.thread
import kotlin.math.roundToInt
import kotlin.properties.Delegates
import kotlin.streams.toList

fun main(args: Array<String>) {
    val server = ServerSocket(12345)
    with(server.accept()) {
        println("client connected $isConnected")

        val input = BufferedReader(InputStreamReader(getInputStream()))
        val output = BufferedWriter(OutputStreamWriter(getOutputStream()))
        var resultString: String
        thread {
            var incoming: Int
            while(true) {
                incoming = input.read()
                resultString = isEven(incoming)
                output.write(resultString + "\n")
                output.flush()
            }
        }

        thread{
            var stringIncoming: String
            while (true){
                stringIncoming = input.readLine()
                println("Received $stringIncoming")
            }
        }
    }
}

fun isEven(value: Int) = when (value % 2 == 0) {
    true -> "$value - чётное число"
    false -> "$value - нечётнео число"
}