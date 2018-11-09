package thomasvalentini.androiduiandlogin.sweng888.psu.edu.androiduiandlogin_thomasvalentini.activity;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.constraint.ConstraintLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.*;

import thomasvalentini.androiduiandlogin.sweng888.psu.edu.androiduiandlogin_thomasvalentini.R;
import thomasvalentini.androiduiandlogin.sweng888.psu.edu.androiduiandlogin_thomasvalentini.model.dao.UserProfilePersistence;
import thomasvalentini.androiduiandlogin.sweng888.psu.edu.androiduiandlogin_thomasvalentini.model.entity.UserProfile;

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
    //Firebase
    private FirebaseAuth mAuth = null;

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
                signIn(mEditTextEmail.getText().toString(),mEditTextPassword.getText().toString());
                /*if(userProfilePersistence.loginAttempt(mEditTextEmail.getText().toString(),mEditTextPassword.getText().toString())){
                    Intent intent= new Intent(LoginActivity.this, LoginSuccessActivity.class);
                    intent.putExtra("EMAIL",mEditTextEmail.getText().toString());
                    startActivity(intent);
                }
                else{
                    Toast.makeText(getApplicationContext(),R.string.login_fail,Toast.LENGTH_LONG).show();
                }
                */
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

    @Override
    protected void onStart() {
        super.onStart();
        mAuth = FirebaseAuth.getInstance();
    }
    private void signIn(String email, String password){

        mAuth = FirebaseAuth.getInstance();

        // Tries to sign in a user with the given email address and password.
        mAuth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // If sign is sucessful, update UI with the signed-in user's information
                            Log.d("TAG", "signInWithEmail:success");
                            FirebaseUser user = mAuth.getCurrentUser();

                            // Accessing the user information
                            // Creating a Toast to update the UI
                            String msg = "User: "+user.getEmail()+" , ID: "+user.getProviderId();
                            Toast.makeText(LoginActivity.this, msg, Toast.LENGTH_SHORT).show();

                            // Pass the user info as paramater to the next activity.
                            Intent intent = new Intent(LoginActivity.this, LoginSuccessActivity.class);
                            intent.putExtra("EMAIL",user.getEmail());
                            startActivity(intent);

                        } else {
                            // TODO Implemnt the validation in case password or user is wrong.
                            // If sign in fails, display a message to the user.
                            Log.w("TAG", "signInWithEmail:failure", task.getException());
                            String msgFailed = "Authentication failed: "+task.getException().getMessage();
                            Toast.makeText(LoginActivity.this,msgFailed,
                                    Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }
}
