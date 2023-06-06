import java.io.BufferedReader
import java.io.InputStreamReader
import java.io.PrintWriter
import java.net.*


class Server : Result {
    private var socket: ServerSocket = ServerSocket(12345)
    private lateinit var client: Socket
    private var latestRequest: String = ""
    private lateinit var  out: PrintWriter
    private lateinit var  input: BufferedReader

    fun startListening() {
        client = socket.accept()
        out = PrintWriter(client.getOutputStream(), true)
        input = BufferedReader(InputStreamReader(client.getInputStream()))
    }

    private fun handleRequest(request: String) : String{
        val intList = request.split(", ")
            .map { it.toInt() }

        var prev: Int? = null
        var accCounter = 0

        val sameCounter = mutableListOf<Int>()

        var interLeavedHelper = if (intList[0] >= 0 ) 1 else -1
        var interleaved = true
        for (i in intList.indices) {
            prev?.let {
                if (it < intList[i]) {
                    accCounter++
                }
                if (it >= 0 && intList[i] < 0 && interLeavedHelper == 1) {
                    interLeavedHelper = -1
                } else if (it < 0 && intList[i] >= 0 && interLeavedHelper == -1) {
                    interLeavedHelper = 1
                } else {
                    interleaved = false
                }
            }
            prev = intList[i]
            if (intList[i] !in sameCounter) {
                sameCounter.add(intList[i])
            }
        }
        var result = ""
        val sameElems = intList.size - sameCounter.size
        result += if (accCounter == (intList.size - 1)) {
            "Возрастающий "
        } else if (accCounter == 0 && (intList.size != sameElems + 1)){
            "Убывающий "
        } else {
            "Беспорядочный "
        }

        result += "Повторений $sameElems "
        result += if (interleaved) {
            "Знакочередующийся"
        } else {
            "Незнакочередующийся"
        }
        return result
    }


    override fun getResult(): String {
        val request = input.readLine()
        latestRequest = handleRequest(request)
        out.println(latestRequest)
        return latestRequest
    }
}