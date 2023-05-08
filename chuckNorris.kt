import kotlin.math.pow
import kotlin.system.exitProcess

fun ifTheIndexIsNotOutOfBounds(i: Int, str: String): Boolean{
    return i-1 != -1 && i+1 != str.length
}

fun binaryToDecimalToCharConversion(str: String): Char {
    var result = 0.0
    var counter = 0
    for (i in str.length - 1 downTo  0) {
        if (str[i] == '1') {
            result += 1.0 * 2.0.pow(counter)
        }
        counter += 1
    }
    return result.toInt().toChar()
}

fun cipherStringBinaryConversion(str: String): String{
    var space = 0
    var tempStringStorage = ""
    val splitString = mutableListOf<String>()
    for (i in str){
        if (i == ' ' && space != 1){
            tempStringStorage += i
            space += 1
        } else if (i == '0'){
            tempStringStorage += i
        } else if (space == 1){
            splitString.add(tempStringStorage)
            space = 0
            tempStringStorage = ""
        }
    }
    var binaryStringResult = ""
    splitString.add(tempStringStorage)

    for (i in splitString.indices) {
        if (splitString[i].split(" ")[0] == "0"){
            binaryStringResult += "1".repeat(splitString[i].split(" ")[1].length)
        } else if (splitString[i].split(" ")[0] == "00")
            binaryStringResult += "0".repeat(splitString[i].split(" ")[1].length)
    }
    return binaryStringResult
}

fun binaryConversionForCharCodes(str: String): MutableList<String> {
    val input = mutableListOf<String>()
    str.forEach{
        if (Integer.toBinaryString(it.code).length < 7) {
            input.add("0" + Integer.toBinaryString(it.code))
        } else {
            input.add(Integer.toBinaryString(it.code))
        }
    }
    return input
}

fun chuckNorrisCipherEncryption(str: String): String {
    var result = ""
    val resultList = mutableListOf<String>()
    var cipher = ""
    for (i in str.indices) {
        when {
            i == 0 -> {
                if (str[i + 1] == str[i]) {
                    result += str[i]
                } else if (str[i+1] != str[i]) {
                    result += str[i] + "_"
                }
            }
            i == str.length - 1 -> result += str[i]
            ifTheIndexIsNotOutOfBounds(i, str) && str[i + 1] != str[i] -> result += str[i] + "_"
            ifTheIndexIsNotOutOfBounds(i, str) && str[i - 1] != str[i] -> result += str[i]
            ifTheIndexIsNotOutOfBounds(i, str) && str[i + 1] == str[i] -> result += str[i]
            ifTheIndexIsNotOutOfBounds(i, str) && str[i - 1] == str[i] -> result += str[i]
        }
    }
    result.split("_").forEach{
        resultList.add(it)
    }
    for (i in resultList.indices){
        if (resultList[i].contains('1')) {
            cipher += "0 " + "0".repeat(resultList[i].length) + " "
        } else if (resultList[i].contains('0')) {
            cipher += "00 " + "0".repeat(resultList[i].length) + " "
        }
    }
    return cipher
}

fun encode(str: String){
    println("Encoded string:")
    println(chuckNorrisCipherEncryption(binaryConversionForCharCodes(str).joinToString(separator="")))
}

fun decode(str: String): String{
    val a = cipherStringBinaryConversion(str)
    if (a.length % 7 != 0) {
        return ""
    }
    var result = ""
    val charCodeList = a.chunked(7)
    for (i in charCodeList){
        result += binaryToDecimalToCharConversion(i)
    }
    return result
}

fun errorChecking(str: String): Boolean{
    val strList = str.split(" ").toMutableList()
    var error = false
    for (i in strList.indices){
        if (i % 2 == 0) {
            error = strList[i].length > 2 || strList.size % 2 != 0
        }
    }
    for (i in strList){
        for (j in i){
            if (j != '0'){
                error = true
            }
        }
    }
    return error
}
fun main() {
    while(true) {
        println("Please input operation (encode/decode/exit):")
        val operation = readln()
        when {
            operation == "encode" -> {
                println("Input string:")
                val clearInput = readln()
                encode(clearInput)
            }
            operation == "decode" -> {
                println("Input encoded string:")
                val encodedInput = readln()
                if (errorChecking(encodedInput) || decode(encodedInput) == ""){// || decode(encodedInput).length % 7 != 0){
                    println("Encoded string is not valid.\n")
                } else {
                    println("Decoded string:")
                    println("${decode(encodedInput)} \n")
                }
            }
            operation == "exit" -> {
                println("Bye!")
                exitProcess(0)
            } else -> println("There is no '<input>' operation")
        }
    }
}
