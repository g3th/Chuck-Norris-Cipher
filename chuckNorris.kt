fun ifTheIndexIsNotOutOfBounds(i: Int, str: String): Boolean{
    return i-1 != -1 && i+1 != str.length
}

fun binaryConversionForCharCodes(): MutableList<String> {
    val input = mutableListOf<String>()
    println("Input string:")
    readln().forEach{
        if (Integer.toBinaryString(it.code).length == 6) {
            input.add("0" + Integer.toBinaryString(it.code))
        } else {
            input.add(Integer.toBinaryString(it.code))
        }
    }
    return input
}

fun chuckNorrisCipherConversion(str: String): String {
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
        } else if (resultList[i].contains('0')){
            cipher += "00 " + "0".repeat(resultList[i].length) + " "
        }
    }
    return cipher
}

fun main() {
    val str = binaryConversionForCharCodes()
    val result = chuckNorrisCipherConversion(str.joinToString(separator = ""))
    print("\nThe result:\n${result.trimEnd()}")
}

//strager's solution golf in Ruby:
//gets.chomp.bytes.map{|c|"%07b"%c}.join.scan(/0+|1+/).map{|w|(w<?1?"00 ":"0 ")+?0*w.size}*" "
