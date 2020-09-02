package com.hiltdemoapp

import com.hiltdemoapp.utils.ValidatorUtil
import org.hamcrest.CoreMatchers.`is`
import org.junit.Assert
import org.junit.Assert.assertEquals
import org.junit.Test

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
class ExampleUnitTest {
    @Test
    fun addition_isCorrect() {
        assertEquals(4, 2 + 2)
    }

    @Test
    fun userNameStringNullCheck() {
        Assert.assertThat(ValidatorUtil.checkInputValid(null), `is`(""))
    }

    @Test
    fun userNameStringEmptyCheck() {
        Assert.assertThat(ValidatorUtil.checkInputValid(""), `is`(""))
    }
}