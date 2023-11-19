package com.example.hotel.special.watcher

import android.text.Editable
import android.text.TextWatcher

class CustomPhoneNumberWatcher : TextWatcher {

    var length_before = 0
    var oldNum = ""
    var isAdd = true

    override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) {
        isAdd = s.length > length_before
        length_before = s.length
    }

    override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}

    override fun afterTextChanged(s: Editable) {
        if (s.isEmpty() || s.length == 16)
            return
        var num: String = s.toString()
        s.replace(0, s.length, "")

        num = num.removeRange(0, 2)
        num = num
            .replace("(", "")
            .replace(")", "")
            .replace("-", "")
            .replace("*", "")
            .replace("+", "")
            .replace("#", "")

        if (!isAdd && oldNum.length == num.length && oldNum != "")
            num = num.removeRange(num.length - 1, num.length)

        oldNum = num

        while (num.length < 10)
            num += "*"

        num = "+7(${num.substring(0, 3)})${num.substring(3,6)}-${num.substring(6,8)}-${num.substring(8)}"
        s.append(num)
    }
}
