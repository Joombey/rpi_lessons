import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import java.io.*
import kotlin.collections.toByteArray
import java.net.ServerSocket

import kotlin.concurrent.thread
import kotlin.math.roundToInt
import kotlin.properties.Delegates
import kotlin.streams.toList

fun main(args: Array<String>) {
    val server = ServerSocket(12345)
    val input: BufferedReader
    val output: BufferedWriter

    runBlocking {

        server.accept().apply {
            println("client connected $isConnected")

            input = BufferedReader(InputStreamReader(getInputStream()))
            output = BufferedWriter(OutputStreamWriter(getOutputStream()))

//        thread {
//            var incoming: Int
//            while(true) {
//                incoming = input.read()
//                resultString = isEven(incoming)
//                output.write(resultString + "\n")
//                output.flush()
//            }
//        }
//
//        thread{
//            var stringIncoming: String
//            while (true){
//                stringIncoming = input.readLine()
//                println("Received $stringIncoming")
//            }
//        }


        }
//        launch {
//            var stringIncoming: String
//            while (true) {
//                stringIncoming = input.readLine()
//                println("Received $stringIncoming")
//            }
//        }
        launch {
            var resultString: String
            var incoming: Int
            while (true) {
                incoming = input.read()
                resultString = isEven(incoming)
                output.write("$resultString\n")
                output.flush()
            }
        }
    }
}

fun isEven(value: Int) = when (value % 2 == 0) {
    true -> "$value - чётное число"
    false -> "$value - нечётнео число"
}


