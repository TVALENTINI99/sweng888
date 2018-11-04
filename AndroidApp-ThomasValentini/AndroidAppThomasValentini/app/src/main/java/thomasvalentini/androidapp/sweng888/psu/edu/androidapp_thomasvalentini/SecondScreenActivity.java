package thomasvalentini.androidapp.sweng888.psu.edu.androidapp_thomasvalentini;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class SecondScreenActivity extends AppCompatActivity {

    //private Text View Variables
    private TextView mTextViewParams;

    //private String Variables
    private String iemail;
    private String isubject;
    private String imessage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second_screen);
        mTextViewParams=(TextView) findViewById(R.id.textViewMsg);
        String paramMsg = mTextViewParams.getText().toString();

        Intent intent = getIntent();
        this.iemail = intent.getStringExtra("EMAIL_STR");
        this.isubject = intent.getStringExtra("SUBJECT_STR");
        this.imessage = intent.getStringExtra("MESSAGE_STR");

        StringBuilder stringBuilder = new StringBuilder(350);
        stringBuilder.append(paramMsg);
        stringBuilder.append("'\n'email: " + iemail);
        stringBuilder.append("'\n'subject: " + isubject);
        stringBuilder.append("'\n'message: " + imessage);

        mTextViewParams.setText(stringBuilder);

    }
}
