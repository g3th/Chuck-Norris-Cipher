# Chuck Norris Encryption
## The Chuck Norris Unary cipher in Kotlin

This is a common code puzzle found in a number of sites (Codewars, Leetcode etc..) and consists of encrypting words or a series of characters using only 0s. The characters are first converted to a binary string 7 bits long (i.e. 1001010), which is the length of ASCII character codes. These binary strings are then joined into a single string and form the input for the encrypting function. 

The rules of the cipher dictate that the first occurrence of:

1 (bit on) == 0 + a space
0 (bit off) == 00 + a space

Then, each subsequent recurring 1 or 0 will not include a space until a different digit occurs. These digits will indicate the total number of 1/0s until the sequence breaks. Example:

1100110 would be: 0 00 (two 1s) 00 00 (two 0s) 0 00 (two 1s) 00 0 (one zero)

This script does not include a decrypter for now.
