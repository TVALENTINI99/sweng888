package thomasvalentini.androiduiandlogin.sweng888.psu.edu.androiduiandlogin_thomasvalentini.activity;

import android.content.Intent;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.*;

import thomasvalentini.androiduiandlogin.sweng888.psu.edu.androiduiandlogin_thomasvalentini.R;
import thomasvalentini.androiduiandlogin.sweng888.psu.edu.androiduiandlogin_thomasvalentini.model.dao.UserProfilePersistence;
import thomasvalentini.androiduiandlogin.sweng888.psu.edu.androiduiandlogin_thomasvalentini.model.entity.UserProfile;

public class SignUpActivity extends AppCompatActivity {
    //Action Bar
    private ActionBar mActionBarSignUp;
    //TextView
    private TextView mTextViewName;
    private TextView mTextViewSurname;
    private TextView mTextViewUsername;
    private TextView mTextViewBirthday;
    private TextView mTextViewMobilePhone;
    private TextView mTextViewEmail;
    private TextView mTextViewPassword;
    //EditText
    private EditText mEditTextName;
    private EditText mEditTextSurname;
    private EditText mEditTextUsername;
    private EditText mEditTextBirthday;
    private EditText mEditTextMobilePhone;
    private EditText mEditTextEmail;
    private EditText mEditTextPassword;
    //Button
    private Button mButtonConfirm;
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        //ActionBar Setup
        mActionBarSignUp=getSupportActionBar();
        mActionBarSignUp.setTitle(R.string.title_bar_signup_activity);
        //TextView Setup
        mTextViewName=(TextView) findViewById(R.id.textViewSignUpName);
        mTextViewSurname=(TextView) findViewById(R.id.textViewSignUpSurname);
        mTextViewUsername=(TextView) findViewById(R.id.textViewSignUpUsername);
        mTextViewBirthday=(TextView) findViewById(R.id.textViewSignUpBirthday);
        mTextViewMobilePhone=(TextView) findViewById(R.id.textViewSignUpMobilePhone);
        mTextViewEmail=(TextView) findViewById(R.id.textViewSignUpEmail);
        mTextViewPassword=(TextView) findViewById(R.id.textViewSignUpPassword);

        //EditText Setup
        mEditTextName=(EditText) findViewById(R.id.editTextSignUpName);
        mEditTextSurname=(EditText) findViewById(R.id.editTextSignUpSurname);
        mEditTextUsername=(EditText) findViewById(R.id.editTextSignUpUsername);
        mEditTextBirthday=(EditText) findViewById(R.id.editTextSignUpBirthday);
        mEditTextMobilePhone=(EditText) findViewById(R.id.editTextSignUpMobilePhone);
        mEditTextEmail=(EditText) findViewById(R.id.editTextSignUpEmail);
        mEditTextPassword=(EditText) findViewById(R.id.editTextSignUpPassword);

        //Button Setup
        mButtonConfirm=(Button) findViewById(R.id.buttonConfirm);

        mAuth=FirebaseAuth.getInstance();

        mButtonConfirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                UserProfilePersistence userProfilePersistence= new UserProfilePersistence(SignUpActivity.this);
                UserProfile profile=new UserProfile(mEditTextName.getText().toString(), mEditTextSurname.getText().toString(),
                        mEditTextUsername.getText().toString(),mEditTextBirthday.getText().toString(),mEditTextMobilePhone.getText().toString(),
                        mEditTextEmail.getText().toString(),mEditTextPassword.getText().toString());
                if(userProfilePersistence.checkUser(profile)){
                    userProfilePersistence.insert(profile);
                    Toast.makeText(getApplicationContext(),R.string.user_create,Toast.LENGTH_LONG).show();
                    Intent intent =new Intent(SignUpActivity.this,LoginActivity.class);
                    startActivity(intent);

                }
                else{
                    Toast.makeText(getApplicationContext(),R.string.signup_fail,Toast.LENGTH_LONG).show();
                    Intent intent =new Intent(SignUpActivity.this,SignUpActivity.class);
                    startActivity(intent);
                }
            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();
        mAuth =FirebaseAuth.getInstance();
    }
}
