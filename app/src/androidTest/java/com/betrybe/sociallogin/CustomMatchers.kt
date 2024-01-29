package com.betrybe.sociallogin

import android.view.View
import android.widget.ImageView
import androidx.test.espresso.matcher.BoundedMatcher
import com.google.android.material.textfield.TextInputLayout
import org.hamcrest.Description
import org.hamcrest.Matcher
import org.hamcrest.TypeSafeMatcher

fun withTextInputLayoutHint(
    expectedHint: String
): Matcher<View> = object : TypeSafeMatcher<View>() {

    override fun describeTo(description: Description?) {
        description?.appendText("with text input layout hint")
    }

    override fun matchesSafely(item: View?): Boolean {
        if (item !is TextInputLayout) return false
        val error = item.hint ?: return false
        val hint = error.toString()
        return expectedHint == hint
    }
}

fun withTextInputLayoutLeadingImage(): Matcher<View> = object : TypeSafeMatcher<View>() {

    override fun describeTo(description: Description?) {
        description?.appendText("textInputLayout should have startIconDrawable")
    }

    override fun matchesSafely(item: View?): Boolean {
        if (item !is TextInputLayout) return false
        return item.startIconDrawable !== null
    }
}

fun withTextInputLayoutTrailingImage(): Matcher<View> = object : TypeSafeMatcher<View>() {

    override fun describeTo(description: Description?) {
        description?.appendText("textInputLayout should have endIconDrawable")
    }

    override fun matchesSafely(item: View?): Boolean {
        if (item !is TextInputLayout) return false
        return item.endIconDrawable !== null
    }
}

fun withTextInputLayoutType(
    inputType: Int
): Matcher<View> = object : TypeSafeMatcher<View>() {

    override fun describeTo(description: Description?) {
        description?.appendText("withTextInputLayoutType: Received: $inputType")
    }

    override fun matchesSafely(item: View?): Boolean {
        if (item !is TextInputLayout) return false
        return item.editText?.inputType == inputType + 1
    }
}

fun hasDrawable(): BoundedMatcher<View, ImageView> =
    object : BoundedMatcher<View, ImageView>(ImageView::class.java) {

        override fun describeTo(description: Description?) {
            description?.appendText("has drawable")
        }

        override fun matchesSafely(item: ImageView?): Boolean {
            return item?.drawable != null
        }
    }
