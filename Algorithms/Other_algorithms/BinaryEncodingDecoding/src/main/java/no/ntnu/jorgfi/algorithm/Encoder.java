package no.ntnu.jorgfi.algorithm;

import static java.util.Arrays.fill;

/**
 * The encoder for exercise in IDATA2304. Your task is to implement the missing functions according to the protocol.
 * @version 2.0
 */
public final class Encoder {
    /**
     * Private constructor.
     * @deprecated Access Encoder-methods through static reference
     */
    @Deprecated
    private Encoder(){}
    
    /*
     * ------------------------------------------------------------
     * The protocol for encoding
     * ------------------------------------------------------------
     * Human-readable message strings are encoded to bit-strings according to the following rules:
     * 1) Only the following characters are allowed in the plain-text, human-readable messages:
     *   - lowercase letters a-z
     *   - uppercase letters A-Z
     *   - digits 0-9
     *   - space ' '
     *   - comma ','
     *   - period '.'
     *   - exclamation mark '!'
     * 2) Each character of the message is translated to an integer code first, by taking the ASCII code of that
     *    character. See ASCII table here: https://www.asciitable.com/
     *    Examples: uppercase letter `A` has ASCII code 65, letter `B` has ASCII code 66, etc.; while lowercase
     *    letter `a` has code 97, lowercase `b` has code 98, etc.
     * 3) Then the integer code is translated to a binary string, always eight bits for each integer code.
     *    The binary string contains the most significant bit first.
     *    Examples: number 65 is translated to "01000001", 66 is translated to "01000010", 98 is translated to
     *    "01100010". Number 0 is translated to "00000000" and number 255 is translated to "11111111".
     * 4) The binary strings for each character are then concatenated in the order as they appear.
     *    Examples:
     *      "ABC" -> 65, 66, 67 -> "010000010100001001000011"
     *      "Hi!" -> 72, 105, 33 -> "010010000110100100100001"
     *
     * Conversion in the opposite direction happens according to the same rules, just in the opposite direction:
     * 1) Split the bit string into 8-character chunks (8-bit blocks)
     * 2) Convert each 8-bit block to an integer
     * 3) Convert the integer to a character, using the ASCII table
     * 4) Only the same limited set of characters is allowed (a-z, A-Z, 0-9, space, comma, period, exclamation mark).
     * If the decoded symbols is not in the allowed character set, an exception must be thrown.
     * 5) Concatenate the individual characters together to form a message
     */





    /**
     * Error codes
     */
    // Exception when decoding a message results in an IllegalArgumentException
    private static final IllegalArgumentException DECODE_EXCEPTION = new IllegalArgumentException("Invalid binary string");
    // Exception when encoding a message results in an IllegalArgumentException
    private static final IllegalArgumentException ENCODE_EXCEPTION = new IllegalArgumentException("Invalid string");

    
    /**
     * Regular expressions
     */
    // Strings should only contain letters, numbers, spaces, commas, periods, and exclamation marks
    private static final String REGEX_MESSAGE = "^[a-zA-Z0-9,.! ]*$";   
    // Binary strings should only contain the number 0 and 1 in packs of 8, separated by a blank space
    private static final String REGEX_BINARY_STRING = "^[0-1]{8}(?: [0,1]{8})*$";
    

    



    /**
     * An encoding function. Takes in a "plaintext message", returns a bit array
     *
     * @param message A message where each character can only be one of the allowed symbols, see the protocol
     * @return The message encoded as a bit string according to the protocol described above
     * each bit must be represented as one character in a string. For example, if the result is 10101011,
     * return the string "10101011"
     * If the input message is null or an empty string, return null
     * If the input message is an empty string, return empty string
     * @throws IllegalArgumentException If the message contains an illegal character (for example, Å, -, [, etc)
     */
    public static String encode(final String message) throws IllegalArgumentException {

        /** 
         * Strings which are null or blank are just returned
         * Strings which doesn't match the defined string RegEx 
         * will result in an IllegalArgumentException
        */
        if (message == null || message.isBlank()) {return message;}
        else if (!message.matches(REGEX_MESSAGE)) {throw ENCODE_EXCEPTION;}

        
        final StringBuilder stringBuilder = extracted();
        final char[] characterSequence = message.toCharArray(); 


        // Converts the characters to binary representation
        for (final char character : characterSequence) { 
            final String binaryByte = Integer.toBinaryString(Byte.toUnsignedInt((byte) character));

            // Adds 0 in front of the binary representation so all sequences consists of 8 bits
            final char[] zeros = new char[8 - binaryByte.length()]; fill(zeros,'0');
            stringBuilder.append(String.valueOf(zeros) + binaryByte + " ");
        }


        // Removes the last blank space in the StringBuilder object
        stringBuilder.deleteCharAt(stringBuilder.lastIndexOf(" "));
        

        // Makes sure that the binary string matches the defined binary string RegEx
        final String binaryString = stringBuilder.toString();
        if (!binaryString.matches(REGEX_BINARY_STRING)) {throw DECODE_EXCEPTION;}

        return binaryString;
    }





    /**
     * Read a binary signal (ones and zeroes), decode it to a human-readable message.
     * Inverse function for encode(m). When implemented correctly, decode(encode(m)) == m
     *
     * @param binaryString The signal in a binary-string format, consisting of ones and zeroes, as a string. For example,
     *        binary 10101011 will be represented as a string "10101011".
     *        Constraints for the input data:
     *        - Each character in the binaryString must be either '0' or '1', no other values allowed
     *        - The binaryString must always consist of 8-character blocks, where each block will be
     *        translated to one character in the decoded message. For example, "01000001" is
     *        translated to "A".
     * @return Decoded message, as a string.
     * If the binaryString is null, return null.
     * If the binaryString is an empty string, return empty string
     * @throws IllegalArgumentException If the format for binaryString is invalid
     */
    public static String decode(final String binaryString) throws IllegalArgumentException {

        /** 
         * Binary strings which are null or blank are just returned
         * Binary strings which doesn't match the defined binary string regex
         * results in an IllegalArgumentException
        */
        if (binaryString == null || binaryString.isBlank()) {return binaryString;}
        else if (!binaryString.matches(REGEX_BINARY_STRING)) {throw DECODE_EXCEPTION;}


        // Collects the binary sequences in a String Array
        final StringBuilder stringBuilder = extracted();
        final String[] bytesAsArray = binaryString.split("\\s+");


        // Converts the sequences and appends them to the StringBuilder object
        for (final String bitSequence : bytesAsArray) {
            final char character = Character.toString((char) Integer.parseInt(bitSequence, 2)).charAt(0);
            stringBuilder.append(character);
        }

        
        // Makes sure that the message matches the message RegEx
        final String message = stringBuilder.toString();
        if (!message.matches(REGEX_MESSAGE)){throw ENCODE_EXCEPTION;}

        return message;
    }




    
    /**
     * Create a StringBuilder object
     * @return StrinBuilder object
     */
    private static StringBuilder extracted() {return new StringBuilder();}
}