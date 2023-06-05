import kotlinx.coroutines.*
import java.io.*
import java.net.Socket
import kotlin.random.Random

const val MINIMAL_DILAY: Long = 1

fun main(args: Array<String>) {
//    startPart1()
    startPart2()
}


fun startPart2(){
    val server = Socket("localhost", 12345)
    val input: BufferedReader
    val output: PrintWriter
    with(server) {
        input = BufferedReader(InputStreamReader(getInputStream()))
        output = PrintWriter(getOutputStream(), true)
        var text: String
        println("/stop\nВвод в формате [num],[space]")
        while (true) {
            text = readln().trim()
            if (text == "/stop") {
                break
            }
            output.println(text)
            println("Server says: ${input.readLine()}")
        }
    }
}
fun startPart1(){
    val server = Socket("127.0.0.1", 12345)
    val input: BufferedReader
    val output: PrintWriter
    with(server) {
        input = BufferedReader(InputStreamReader(getInputStream()))
        output = PrintWriter(getOutputStream(), true)
        output.println("1, -1, 2, 2, -3, 5, -2")
        val line = input.readLine()
        println(line)
    }
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