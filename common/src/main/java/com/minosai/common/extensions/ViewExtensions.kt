package com.minosai.common.extensions

import android.util.Patterns
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.google.android.material.textfield.TextInputLayout
import com.minosai.common.R
import java.util.regex.Pattern

fun ViewGroup.inflate(layoutRes: Int): View {
    return LayoutInflater.from(context).inflate(layoutRes, this, false)
}

fun View.hide() {
    this.visibility = View.GONE
}

fun View.show() {
    this.visibility = View.VISIBLE
}

fun View.show(shouldShow: Boolean) {
    if (shouldShow) {
        show()
    } else {
        hide()
    }
}

fun TextInputLayout.getInput() = this.editText?.text.toString()

fun TextInputLayout.isValidInput(): Boolean {

    var isValid = true

    if (getInput().isBlank()) {
        error = context.getString(R.string.input_error_blank)
        isValid = false
    } else {
        this.error = null
    }

    return isValid
}

fun TextInputLayout.isValid(pattern: Pattern, messageRes: Int): Boolean {

    var isValid = true

    if (isValidInput()) {
        val input = this.getInput()
        if (!pattern.matcher(input).matches()) {
            this.error = context.getString(messageRes)
            isValid = false
        } else {
            this.error = null
        }
    } else {
        isValid = false
    }

    return isValid
}

fun TextInputLayout.isValidPhone() = isValid(Patterns.PHONE, R.string.input_error_phone)

fun TextInputLayout.isValidEmail() = isValid(Patterns.EMAIL_ADDRESS, R.string.input_error_email)

fun TextInputLayout.isMatching(regex: Regex, messageRes: Int?): Boolean {

    var isValid = true

    if (isValidInput()) {
        val input = this.getInput()
        if (!input.matches(regex)) {
            this.error = context.getString(messageRes ?: R.string.input_error_invalid)
        } else {
            isValid = false
        }
    } else {
        isValid = false
    }

    return isValid
}
