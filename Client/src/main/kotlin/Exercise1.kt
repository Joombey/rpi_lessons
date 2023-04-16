import java.io.IOException
import java.net.ServerSocket

class Exercise1 {
}

class Server : Thread(){
    private lateinit var serverSocket: ServerSocket
    init {
        try{
            serverSocket = ServerSocket(8080)
        } catch (e: Exception){
            println("Что-то пошло не так")
        }
        start()
    }

    override fun run(){
        try {
            while (true){
                val client = serverSocket.accept()
                val connection = Connection(client)
                connection.start()

            }
        } catch (e: Exception) {return}
    }
}