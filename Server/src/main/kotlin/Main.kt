import java.io.IOException

fun main(args: Array<String>) {
//    start1()
    start2()
}

fun start1() {
    val server = Server.Server()
    server.startListening()
    println(server.getResult())
}

fun start2() {
    val server = Server.Server()
    server.startListening()
    while (true) {
        try {
            println(server.getResult())
        } catch (_: IOException) {
            println("Client disconnected")
            break
        }
    }
}