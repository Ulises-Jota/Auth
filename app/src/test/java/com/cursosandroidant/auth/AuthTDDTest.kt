package com.cursosandroidant.auth

import org.junit.Assert.assertEquals
import org.junit.Assert.assertThrows
import org.junit.Ignore
import org.junit.Test

class AuthTDDTest {

    @Test
    fun login_completeFrom_existUser_returnsSuccessEvent() {
        val result = userAuthenticationTDD("ant@gmail.com", "1234")
        assertEquals(AuthEvent.USER_EXIST, result)
    }

    @Test
    fun login_completeForm_notExistUser_returnsFailEvent() {
        val result = userAuthenticationTDD("nt@gmail.com", "1234")
        assertEquals(AuthEvent.NOT_USER_EXIST, result)
    }

    @Test
    fun login_emptyEmail_returnsFailEvent() {
        val result = userAuthenticationTDD("", "1234")
        assertEquals(AuthEvent.EMPTY_EMAIL, result)
    }

    @Test
    fun loginUser_emptyPassword_returnsFailEvent() {
        val result = userAuthenticationTDD("ant@gmail.com", "")
        assertEquals(AuthEvent.EMPTY_PASSWORD, result)
    }

    @Test
    fun loginUser_emptyForm_returnsFailEvent() {
        val result = userAuthenticationTDD("", "")
        assertEquals(AuthEvent.EMPTY_FORM, result)
    }

    @Test
    fun loginUser_invalidEmail_returnsFailEvent() {
        val result = userAuthenticationTDD("ant@gmailcom", "1234")
        assertEquals(AuthEvent.INVALID_EMAIL, result)
    }

    @Test
    fun loginUser_invalidPassword_returnsFailEvent() {
        val result = userAuthenticationTDD("ant@gmail.com", "123e")
        assertEquals(AuthEvent.INVALID_PASSWORD, result)
    }

    @Test
    fun loginUser_invalidEmailAndPassword_returnsFailEvent() {
        val result = userAuthenticationTDD("ant@gmailcom", "123e")
        assertEquals(AuthEvent.INVALID_USER, result)
    }

    @Test(expected = AuthException::class)
    fun loginUser_nullEmail_returnsCustomException() {
        val result = userAuthenticationTDD(null, "123e")
        assertEquals(AuthEvent.NULL_EMAIL, result)
    }

    @Test
    fun loginUser_nullPassword_returnsCustomException() {
        assertThrows(AuthException::class.java) {
            print(userAuthenticationTDD("ant@gmail.com", null))
        }
    }

    @Test
    fun loginUser_nullEmailAndPassword_returnsCustomException() {
        try {
            val result = userAuthenticationTDD(null, null)
            assertEquals(AuthEvent.NULL_FORM, result)
        } catch (e: Exception) {
            (e as? AuthException)?.let {
                assertEquals(AuthEvent.NULL_FORM, it.authEvent)
            }
        }
    }

    @Test
    fun loginUser_invalidLengthPassword_returnsFailEvent() {
        val result = userAuthenticationTDD("ant@gmail.com", "12345")
        assertEquals(AuthEvent.INVALID_PASSWORD_LENGTH, result)
    }

    @Ignore("Falta definir un requisito del cliente...")
    @Test
    fun login_completeForm_errorLengthPassword_returnsFailEvent() {
        val result = userAuthenticationTDD("ant@gmailcom", "123")
        assertEquals(AuthEvent.LENGTH_PASSWORD, result)
    }
}