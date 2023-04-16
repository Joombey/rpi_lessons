import java.io.BufferedReader
import java.io.InputStreamReader
import java.io.PrintWriter
import java.net.Socket

class Connection(
    protected var netClient: Socket? = null

) : Thread() {
    protected var fromClient: BufferedReader? = null
    protected var toClient: PrintWriter? = null

    init {
        netClient?.use {
            fromClient = BufferedReader(InputStreamReader(it.getInputStream()))
            toClient = PrintWriter(it.getOutputStream())
        }
        start()
    }

    override fun run() {
        netClient.use {
            var login: String? = null
            var password: String? = null

            while (true){
                toClient?.println("Login: ")
                login = fromClient?.readLine()
                if (login?.equals("bye") == true){
                    println("exit")
                    return
                }else{
                    println("$login logged in")
                    toClient?.println("Password: ")
                    password = fromClient?.readLine()
                    println(password)
                }
            }
        }
    }
}