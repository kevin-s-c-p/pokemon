package mx.com.myapplication.util

fun Double.formatNumber(format: String = "%.2f"): String {
    return String.format(format, this)
}