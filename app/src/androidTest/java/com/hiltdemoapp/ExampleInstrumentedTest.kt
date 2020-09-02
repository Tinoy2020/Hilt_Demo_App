package com.hiltdemoapp

import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.*
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.RootMatchers.withDecorView
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.platform.app.InstrumentationRegistry
import androidx.test.rule.ActivityTestRule
import com.hiltdemoapp.ui.main.view.TestActivity
import org.hamcrest.CoreMatchers.`is`
import org.hamcrest.CoreMatchers.not
import org.junit.Assert.assertEquals
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith


/**
 * Instrumented test, which will execute on an Android device.
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
@RunWith(AndroidJUnit4::class)
class ExampleInstrumentedTest {

    private val USERNAME_TYPED = "Test User"

    private val LOGIN_TEXT = "Hello Test User"

    @Test
    fun useAppContext() {
        // Context of the app under test.
        val appContext = InstrumentationRegistry.getInstrumentation().targetContext
        assertEquals("com.hiltdemoapp", appContext.packageName)
    }

    @get:Rule
    var mActivityRule: ActivityTestRule<TestActivity> = ActivityTestRule(
        TestActivity::class.java
    )

    @Test
    fun loginClickedSuccess() {
        onView(withId(R.id.inUsername))
            .perform(typeText(USERNAME_TYPED))
        onView(withId(R.id.inNumber))
            .perform(typeText("12345"))
        onView(withId(R.id.inConfirmNumber))
            .perform(typeText("12345"))
        onView(withId(R.id.btnLogin)).perform(click())
        onView(withId(R.id.txtLoginResult)).check(matches(withText(LOGIN_TEXT)))
    }

    @Test
    fun shouldShowToastError() {
        onView(withId(R.id.inUsername))
            .perform(typeText(USERNAME_TYPED))
        onView(withId(R.id.inNumber))
            .perform(typeText("123456"))
        onView(withId(R.id.inConfirmNumber))
            .perform(typeText("12345"), closeSoftKeyboard())
        onView(withId(R.id.btnLogin)).perform(click())
        onView(withText(R.string.toast_error))
            .inRoot(withDecorView(not(`is`(mActivityRule.activity.window.decorView))))
            .check(matches(isDisplayed()))
    }

    @Test
    fun shouldShowToastUsernameEmpty() {
        onView(withId(R.id.inNumber))
            .perform(typeText("12345"))
        onView(withId(R.id.inConfirmNumber))
            .perform(typeText("12345"))
        onView(withId(R.id.btnLogin)).perform(click())
        onView(withText(R.string.username_empty))
            .inRoot(withDecorView(not(`is`(mActivityRule.activity.window.decorView))))
            .check(matches(isDisplayed()))
    }
}