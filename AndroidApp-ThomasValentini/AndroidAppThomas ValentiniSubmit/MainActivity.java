package thomasvalentini.androidapp.sweng888.psu.edu.androidapp_thomasvalentini;

import android.content.Intent;
import android.support.constraint.ConstraintLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    //Private Text View Variables
    private TextView mWelcomeView;
    //Private Button Variables
    private Button mButtonTest;
    private Button mButtonPassParams;
    private Button mButtonThirdPartyComponent;
    //Private Edit Text Variables
    private EditText mEditTextEmail;
    private EditText mEditTextSubject;
    private EditText mEditTextMessage;
    //Private String Variables
    private String email;
    private String subject;
    private String message;
    //Private Constraint layout
    private ConstraintLayout mConstraintLayout;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Text View Initializations
        mWelcomeView = (TextView) findViewById(R.id.textViewWelcome);

        //Button Initializations
        mButtonTest = (Button) findViewById(R.id.buttonTest);
        mButtonPassParams = (Button) findViewById(R.id.btn_pass_params);
        mButtonThirdPartyComponent = (Button) findViewById(R.id.buttonThirdParty);

        //Edit Text Initializations
        mEditTextEmail = (EditText) findViewById(R.id.editTextEmail);
        mEditTextSubject = (EditText) findViewById(R.id.editTextSubject);
        mEditTextMessage = (EditText) findViewById(R.id.editTextMessage);

        //Constraint Layout Initializations
        mConstraintLayout= (ConstraintLayout) findViewById(R.id.constraint_layout);

        //define simple on click listener
        mButtonTest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(),R.string.on_click,Toast.LENGTH_LONG).show();
            }
        });

        //define on long click listener
        mButtonPassParams.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                email = mEditTextEmail.getText().toString();
                subject = mEditTextSubject.getText().toString();
                message =mEditTextMessage.getText().toString();
                Intent intent = new Intent(MainActivity.this, SecondScreenActivity.class);
                intent.putExtra("EMAIL_STR", email);
                intent.putExtra("SUBJECT_STR", subject);
                intent.putExtra("MESSAGE_STR", message);
                startActivity(intent);
                return true;
            }
        });

        //define on click listener to open third party application
        mButtonThirdPartyComponent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                email = mEditTextEmail.getText().toString();
                subject = mEditTextSubject.getText().toString();
                message =mEditTextMessage.getText().toString();
                Intent intent = new Intent(Intent.ACTION_SEND);

                intent.setType("text/html");
                intent.putExtra(Intent.EXTRA_EMAIL, new String[]{email});
                intent.putExtra(Intent.EXTRA_SUBJECT, subject);
                intent.putExtra(Intent.EXTRA_TEXT, message);

                startActivity(Intent.createChooser(intent,"Send email ..."));

            }
        });

    }
    public void simpleClick(View view){
        Toast.makeText(getApplicationContext(),R.string.simple_click,Toast.LENGTH_LONG).show();;
    }
}
