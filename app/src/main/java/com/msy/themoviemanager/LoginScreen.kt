package com.msy.themoviemanager

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.Toast
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.Fragment
import com.msy.themoviemanager.databinding.FragmentLoginScreenBinding
import java.util.regex.Matcher
import java.util.regex.Pattern


class LoginScreen : Fragment() {

    private var _binding : FragmentLoginScreenBinding? = null
    private val loginScreenBinding get() = _binding!!

    private lateinit var loginButton : Button

    var mailIsOk: Boolean = false
    var passwordIsOk: Boolean = false

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {

        _binding = FragmentLoginScreenBinding.inflate(inflater, container, false)

        loginButton = loginScreenBinding.loginButton


        loginButton.setOnClickListener {

            checkInputs()

            if (mailIsOk && passwordIsOk){
                login()
            }
        }

        return loginScreenBinding.root
    }

    private fun login(){
        (activity as MainActivity).toMainActivity2()
        Toast.makeText(loginScreenBinding.root.context, "Welcome ${loginScreenBinding.emailTextInput.text} !", Toast.LENGTH_LONG).show()
    }

    private fun checkInputs() {
        val mailEditText = loginScreenBinding.emailTextInput
        val passwordEditText = loginScreenBinding.passwordTextInput

        val mailEditTextLayout = loginScreenBinding.emailTextInputLayout
        val passwordEditTextLayout = loginScreenBinding.passwordTextInputLayout

        mailEditText.addTextChangedListener {
            mailEditTextLayout.error = ""
        }

        passwordEditText.addTextChangedListener {
            passwordEditTextLayout.error = ""
        }

        if (mailEditText.text?.isEmpty() == false){
            if (emailValidation(mailEditText.text.toString())){
                mailIsOk = true
            }else{
                mailEditTextLayout.error = "Email is invalid !"
            }
        }else{
            mailEditTextLayout.error = "Enter an email address !"
        }
        if (passwordEditText.text?.isEmpty() == false){
            if (passwordEditText.text?.length!! >= 6){
               if (passwordValidation(passwordEditText.text.toString())){
                   passwordIsOk = true
               }
                else{
                   passwordEditTextLayout.error = "The password must contain a capital letter, a special character, and a number !"
               }

            }else{
                passwordEditTextLayout.error = "The password must be at least 6 characters !"
            }

        }else{
            passwordEditTextLayout.error = "Enter password !"
        }
    }

    private fun emailValidation(emailString: String): Boolean{

        val emailPattern: Pattern = Pattern.compile("^[_A-Za-z0-9-+]+(\\.[_A-Za-z0-9-]+)*@" + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$")
        val emailMatcher: Matcher = emailPattern.matcher(emailString)
        return emailMatcher.matches()
    }

    private fun passwordValidation(passwordString: String): Boolean{

        val passwordPattern: Pattern = Pattern.compile("^(?=.*[0-9])(?=.*[A-Z])(?=.*[@#$%^&+=!])(?=\\S+$).{4,}$")
        val passwordMatcher: Matcher = passwordPattern.matcher(passwordString)
        return  passwordMatcher.matches()
    }



    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null

    }
}