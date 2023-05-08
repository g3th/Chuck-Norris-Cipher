import kotlin.math.pow

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

fun cipherStringBinaryConversion(): String{
    println("Input encoded string:")
    val str = readln()
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

fun binaryConversionForCharCodes(): MutableList<String> {
    val input = mutableListOf<String>()
    println("Input string:")
    readln().forEach{
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

fun main() {
//    val str = binaryConversionForCharCodes()
//    val result = chuckNorrisCipherEncryption( str.joinToString(separator = "") )
//    print("\nThe result:\n${result.trimEnd()}")
    val a = cipherStringBinaryConversion()
    var charCodeList = a.chunked(7)
    println("\nThe result:")
    for (i in charCodeList){
        print(binaryToDecimalToCharConversion(i))
    }
}

//strager's solution golf in Ruby:
//gets.chomp.bytes.map{|c|"%07b"%c}.join.scan(/0+|1+/).map{|w|(w<?1?"00 ":"0 ")+?0*w.size}*" "
