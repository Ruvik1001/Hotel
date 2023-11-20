package com.example.hotel.special.watcher

import android.text.Editable
import android.text.TextWatcher

/**
 * Custom TextWatcher for formatting phone numbers as they are entered.
 */
class CustomPhoneNumberWatcher : TextWatcher {

    // Variables to track the state of the phone number input
    var length_before = 0
    var oldNum = ""
    var isAdd = true

    /**
     * Called to notify you that somewhere within s, the text has been changed.
     *
     * @param s The editable that was changed.
     * @param start The beginning of the range of the text that was replaced.
     * @param before The length of the former text that has been replaced.
     * @param after The length of the replacement text.
     */
    override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) {
        // Update the length_before variable
        length_before = s.length
    }

    /**
     * This method is called to notify you that somewhere within s, the text has been changed.
     *
     * @param s The text that has been changed.
     * @param start The starting point of the text that has been changed.
     * @param before The length of the text before the change occurred.
     * @param count The length of the text that has been changed.
     */
    override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
        // Determine if text is being added or removed
        if (s != null) {
            isAdd = s.length > length_before
        }
    }

    /**
     * This method is called to notify you that somewhere within s, the text has been changed.
     *
     * @param s The editable that has been changed.
     */
    override fun afterTextChanged(s: Editable) {
        // Skip formatting if the input is empty or already formatted
        if (s.isEmpty() || s.length == 16)
            return

        // Extract and process the phone number
        var num: String = s.toString()
        num = num.removeRange(0, 2)  // Remove the leading "+7"
        num = num
            .replace("(", "")
            .replace(")", "")
            .replace("-", "")
            .replace("*", "")
            .replace("+", "")
            .replace("#", "")

        // If a digit is removed, adjust the processed number accordingly
        if (!isAdd && oldNum.length == num.length && oldNum != "")
            num = oldNum.substring(0, oldNum.length - 1)

        // Remove needless symbols
        if (num.length > 10)
            num = num.substring(0, 11)

        // Update oldNum for the next iteration
        oldNum = num

        // Pad the number with '*' until it reaches a length of 10
        while (num.length < 10)
            num += "*"

        // Format the number as "+7(XXX)XXX-XX-XX"
        num = "+7(${num.substring(0, 3)})${num.substring(3,6)}-${num.substring(6,8)}-${num.substring(8)}"

        // Replace the input text with the formatted number
        s.replace(0, s.length, num)
    }
}
