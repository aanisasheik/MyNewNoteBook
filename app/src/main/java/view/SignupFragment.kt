package view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.appers.mynewnotebook.SignInActivity
import com.appers.mynewnotebook.R
import kotlinx.android.synthetic.main.signup_fargment_layout.*
import model.SignUpUser

class SignupFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.signup_fargment_layout,container,false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        signup_button.setOnClickListener{
            val email =su_email_edittext.text.toString().trim()
            val password=su_password_edittext.text.toString().trim()
            (requireActivity() as SignInActivity).signupUser(SignUpUser(email,password))
        }
    }
}