package com.betrybe.sociallogin

import android.content.Context
import android.text.InputType
import androidx.test.espresso.Espresso.closeSoftKeyboard
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.clearText
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.action.ViewActions.typeText
import androidx.test.espresso.assertion.PositionAssertions.isCompletelyBelow
import androidx.test.espresso.assertion.ViewAssertions.doesNotExist
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.isEnabled
import androidx.test.espresso.matcher.ViewMatchers.withHint
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.espresso.matcher.ViewMatchers.withParent
import androidx.test.espresso.matcher.ViewMatchers.withText
import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.platform.app.InstrumentationRegistry
import org.hamcrest.CoreMatchers.not
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

private fun getId(id: String): Int {
    val targetContext: Context = InstrumentationRegistry.getInstrumentation().targetContext
    val packageName: String = targetContext.packageName
    return targetContext.resources.getIdentifier(id, "id", packageName)
}

@RunWith(AndroidJUnit4::class)
class MainActivityTest {

    @get:Rule
    val activityRule = ActivityScenarioRule(MainActivity::class.java)

    @Test
    fun test_req_1_crie_a_estrutura_inicial_da_tela_com_a_logo_da_rede_social() {
        onView(withId(getId("main")))
            .check(matches(isDisplayed()))

        onView((withId(getId("linear_container"))))
            .check(matches(isDisplayed()))
            .check(matches(withParent(withId(getId("main")))))

        onView((withId(getId("logo"))))
            .check(matches(isDisplayed()))
            .check(matches(hasDrawable()))
            .check(matches(withParent(withId(getId("linear_container")))))
    }

    @Test
    fun test_req_2_crie_o_campo_de_email() {
        onView((withId(getId("email_text_input_layout"))))
            .check(matches(isDisplayed()))
            .check(matches(withParent(withId(getId("linear_container")))))
            .check(matches(withTextInputLayoutHint("Email")))
            .check(matches(isEnabled()))
            .check(isCompletelyBelow(withId(getId("logo"))))
            .check(matches(withTextInputLayoutType(InputType.TYPE_TEXT_VARIATION_EMAIL_ADDRESS)))
            .check(matches(withTextInputLayoutLeadingImage()))
    }

    @Test
    fun test_req_3_crie_o_campo_de_senha() {
        onView((withId(getId("password_text_input_layout"))))
            .check(matches(isDisplayed()))
            .check(matches(withParent(withId(getId("linear_container")))))
            .check(matches(withTextInputLayoutHint("Senha")))
            .check(matches(isEnabled()))
            .check(isCompletelyBelow(withId(getId("email_text_input_layout"))))
            .check(matches(withTextInputLayoutType(InputType.TYPE_TEXT_VARIATION_PASSWORD)))
            .check(matches(withTextInputLayoutLeadingImage()))
            .check(matches(withTextInputLayoutTrailingImage()))
    }

    @Test
    fun test_req_4_crie_o_botao_de_entrar() {
        onView((withId(getId("login_button"))))
            .check(matches(isDisplayed()))
            .check(matches(withParent(withId(getId("linear_container")))))
            .check(matches(withText("Entrar")))
            .check(matches(not(isEnabled())))
            .check(isCompletelyBelow(withId(getId("password_text_input_layout"))))
    }

    @Test
    fun test_req_5_crie_o_botao_de_recuperar_senha() {
        onView((withId(getId("forgot_password_button"))))
            .check(matches(isDisplayed()))
            .check(matches(withParent(withId(getId("linear_container")))))
            .check(matches(withText("Esqueceu a senha?")))
            .check(matches(isEnabled()))
            .check(isCompletelyBelow(withId(getId("login_button"))))
    }

    @Test
    fun test_req_6_crie_o_botao_de_cadastro() {
        onView((withId(getId("sign_up_button"))))
            .check(matches(isDisplayed()))
            .check(matches(withParent(withId(getId("main")))))
            .check(matches(withText("Criar nova conta")))
            .check(matches(isEnabled()))
    }

    @Test
    fun test_req_7_implemente_a_regra_de_habilitacao_do_botao_entrar() {
        onView(withHint("Email"))
            .perform(clearText(), typeText("a"))
        closeSoftKeyboard()
        onView(withHint("Senha"))
            .perform(clearText())
        closeSoftKeyboard()
        onView((withId(getId("login_button"))))
            .check(matches(not(isEnabled())))
        closeSoftKeyboard()

        onView(withHint("Email"))
            .perform(clearText())
        closeSoftKeyboard()
        onView(withHint("Senha"))
            .perform(clearText(), typeText("a"))
        closeSoftKeyboard()
        onView((withId(getId("login_button"))))
            .check(matches(not(isEnabled())))
        closeSoftKeyboard()

        onView(withHint("Email"))
            .perform(clearText(), typeText("a"))
        closeSoftKeyboard()
        onView(withHint("Senha"))
            .perform(clearText(), typeText("a"))
        closeSoftKeyboard()
        onView((withId(getId("login_button"))))
            .check(matches(isEnabled()))
    }

    @Test
    fun test_req_8_implemente_a_validacao_do_campo_de_email() {
        onView(withHint("Email"))
            .perform(typeText("a"))

        closeSoftKeyboard()

        onView(withHint("Senha"))
            .perform(typeText("12345"))

        closeSoftKeyboard()

        onView((withId(getId("login_button"))))
            .perform(click())

        onView(withText("Email inválido"))
            .check(matches(isDisplayed()))

        onView(withHint("Email"))
            .perform(clearText(), typeText("teste@betrybe.com"))

        closeSoftKeyboard()

        onView((withId(getId("login_button"))))
            .perform(click())

        onView(withText("Email inválido"))
            .check(doesNotExist())
    }

    @Test
    fun test_req_9_implemente_a_validacao_do_campo_de_senha() {
        onView(withHint("Email"))
            .perform(typeText("teste@betrybe.com"))

        closeSoftKeyboard()

        onView(withHint("Senha"))
            .perform(typeText("a"))

        closeSoftKeyboard()

        onView((withId(getId("login_button"))))
            .perform(click())

        onView(withText("Senha deve ter mais de 4 caracteres"))
            .check(matches(isDisplayed()))

        onView(withHint("Senha"))
            .perform(clearText(), typeText("123456"))

        closeSoftKeyboard()

        onView((withId(getId("login_button"))))
            .perform(click())

        onView(withText("Senha deve ter mais de 4 caracteres"))
            .check(doesNotExist())
    }

    @Test
    fun test_req_10_implemente_uma_mensagem_de_sucesso_no_login() {
        onView(withHint("Email"))
            .perform(typeText("teste@betrybe.com"))

        closeSoftKeyboard()

        onView(withHint("Senha"))
            .perform(typeText("12345"))

        closeSoftKeyboard()

        onView((withId(getId("login_button"))))
            .perform(click())

        onView(withText("Login efetuado com sucesso"))
            .check(matches(isDisplayed()))
    }
}
