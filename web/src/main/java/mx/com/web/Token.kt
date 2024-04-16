package mx.com.web

class Token {
    companion object {
        @JvmStatic
        var retrofitInstance: ApiService? = null

        @JvmStatic
        var token: String = ""
            set(value) {
                field = value
                retrofitInstance = null
            }
    }
}