package thomasvalentini.androiduiandlogin.sweng888.psu.edu.androiduiandlogin_thomasvalentini.activity;

import android.content.Intent;
import android.support.constraint.ConstraintLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import thomasvalentini.androiduiandlogin.sweng888.psu.edu.androiduiandlogin_thomasvalentini.R;
import thomasvalentini.androiduiandlogin.sweng888.psu.edu.androiduiandlogin_thomasvalentini.model.dao.UserProfilePersistence;

public class LoginActivity extends AppCompatActivity {
    //ActionBar
    private ActionBar mActionBarLogin;
    //ImageView
    private ImageView mImageViewSplashScreen;
    //EditText
    private EditText mEditTextEmail;
    private EditText mEditTextPassword;
    //TextView
    private TextView mTextViewOr;
    //Button
    private Button mButtonLogin;
    private Button mButtonSignUp;
    //Constraint Layout
    private ConstraintLayout mConstraintLayout;
    //Linear Layout
    private LinearLayout mLinearLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        //Set Up Action Bar
        mActionBarLogin = getSupportActionBar();
        mActionBarLogin.setTitle(R.string.title_bar_login_activity);

        //Set Up ImageView
        mImageViewSplashScreen = (ImageView) findViewById(R.id.imageViewSplashScreen);

        //Set Up EditText
        mEditTextEmail = (EditText) findViewById(R.id.editTextEmail);
        mEditTextPassword = (EditText) findViewById(R.id.editTextPassword);

        //Set Up TextView
        mTextViewOr = (TextView) findViewById(R.id.textViewOr);

        //Set Up Button
        mButtonLogin = (Button) findViewById(R.id.buttonLogin);
        mButtonSignUp = (Button) findViewById(R.id.buttonSignUp);

        //Set Up Constraint Layout
        mConstraintLayout = (ConstraintLayout) findViewById(R.id.constraint_layout);

        //Set Up Linear Layout
        mLinearLayout = (LinearLayout) findViewById(R.id.linear_layout);

        mButtonLogin.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                UserProfilePersistence userProfilePersistence= new UserProfilePersistence(LoginActivity.this);
                if(userProfilePersistence.loginAttempt(mEditTextEmail.getText().toString(),mEditTextPassword.getText().toString())){
                    Intent intent= new Intent(LoginActivity.this, LoginSuccessActivity.class);
                    intent.putExtra("EMAIL",mEditTextEmail.getText().toString());
                    startActivity(intent);
                }
                else{
                    Toast.makeText(getApplicationContext(),R.string.login_fail,Toast.LENGTH_LONG).show();
                }
            }
        });
        mButtonSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(LoginActivity.this, SignUpActivity.class);
                startActivity(intent);
            }
        });
    }
}
