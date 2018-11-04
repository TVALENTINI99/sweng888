package thomasvalentini.androiduiandlogin.sweng888.psu.edu.androiduiandlogin_thomasvalentini.activity;

import android.content.Intent;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import thomasvalentini.androiduiandlogin.sweng888.psu.edu.androiduiandlogin_thomasvalentini.R;
import thomasvalentini.androiduiandlogin.sweng888.psu.edu.androiduiandlogin_thomasvalentini.model.dao.UserProfilePersistence;
import thomasvalentini.androiduiandlogin.sweng888.psu.edu.androiduiandlogin_thomasvalentini.model.entity.UserProfile;

public class LoginSuccessActivity extends AppCompatActivity {
    //ActionBar
    private ActionBar mActionBarLoginSuccess;
    //TextView
    private TextView mTextViewName;
    private TextView mTextViewSurname;
    private TextView mTextViewUsername;
    private TextView mTextViewBirthday;
    private TextView mTextViewMobilePhone;
    private TextView mTextViewEmail;
    private TextView mTextViewNameVal;
    private TextView mTextViewSurnameVal;
    private TextView mTextViewUsernameVal;
    private TextView mTextViewBirthdayVal;
    private TextView mTextViewMobilePhoneVal;
    private TextView mTextViewEmailVal;
    //Button
    private Button mButtonViewAllUsers;
    //ImageView
    private ImageView mImageViewProfilePicture;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_success);
        //ActionBar Setup
        mActionBarLoginSuccess=getSupportActionBar();
        mActionBarLoginSuccess.setTitle(R.string.title_bar_success_activity);
        //TextView Setup
        mTextViewName= (TextView) findViewById(R.id.textViewSuccessName);
        mTextViewSurname= (TextView) findViewById(R.id.textViewSuccessSurname);
        mTextViewUsername= (TextView) findViewById(R.id.textViewSuccessUserName);
        mTextViewBirthday= (TextView) findViewById(R.id.textViewSuccessBirthday);
        mTextViewMobilePhone= (TextView) findViewById(R.id.textViewSuccessMobilePhone);
        mTextViewEmail= (TextView) findViewById(R.id.textViewSuccessEmail);

        mTextViewNameVal= (TextView) findViewById(R.id.textViewSuccessNameVal);
        mTextViewSurnameVal= (TextView) findViewById(R.id.textViewSuccessSurnameVal);
        mTextViewUsernameVal= (TextView) findViewById(R.id.textViewSuccessUsernameVal);
        mTextViewBirthdayVal= (TextView) findViewById(R.id.textViewSuccessBirthdayVal);
        mTextViewMobilePhoneVal= (TextView) findViewById(R.id.textViewSuccessMobilePhoneVal);
        mTextViewEmailVal= (TextView) findViewById(R.id.textViewSuccessEmailVal);

        //Button Setup
        mButtonViewAllUsers= (Button) findViewById(R.id.buttonViewAllUsers);

        //ImageView Setup
        mImageViewProfilePicture= (ImageView) findViewById(R.id.imageViewProfilePicture);


        Intent intent=getIntent();
        String username= intent.getStringExtra("EMAIL");
        UserProfilePersistence profilePersistence=new UserProfilePersistence(this);
        UserProfile profile =profilePersistence.getProfile(username);

        mTextViewNameVal.setText(profile.getName());
        mTextViewSurnameVal.setText(profile.getSurname());
        mTextViewUsernameVal.setText(profile.getUsername());
        mTextViewBirthdayVal.setText(profile.getBirthday());
        mTextViewMobilePhoneVal.setText(profile.getMobile_phone());
        mTextViewEmailVal.setText(profile.getEmail());


        mButtonViewAllUsers.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LoginSuccessActivity.this,ViewAllUsersActivity.class);
                startActivity(intent);
            }
        });

    }
}
