package thomasvalentini.androiduiandlogin.sweng888.psu.edu.androiduiandlogin_thomasvalentini.activity;

import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;

import java.util.ArrayList;

import thomasvalentini.androiduiandlogin.sweng888.psu.edu.androiduiandlogin_thomasvalentini.R;
import thomasvalentini.androiduiandlogin.sweng888.psu.edu.androiduiandlogin_thomasvalentini.adapter.UserProfileAdapter;
import thomasvalentini.androiduiandlogin.sweng888.psu.edu.androiduiandlogin_thomasvalentini.model.dao.UserProfilePersistence;
import thomasvalentini.androiduiandlogin.sweng888.psu.edu.androiduiandlogin_thomasvalentini.model.entity.UserProfile;

public class ViewAllUsersActivity extends AppCompatActivity {

    private ActionBar mActionBarAllUsers;
    private ListView mlistViewUserProfiles;
    private UserProfileAdapter mprofileAdapter;
    private ArrayList<UserProfile> muserProfiles;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_all_users);

        mActionBarAllUsers=getSupportActionBar();
        mActionBarAllUsers.setTitle(R.string.title_bar_all_users_activity);

        mlistViewUserProfiles = (ListView) findViewById(R.id.list_view_all_users);

        UserProfilePersistence userProfilePersistence = new UserProfilePersistence(this);
        muserProfiles=userProfilePersistence.getDataFromDB();

        mprofileAdapter = new UserProfileAdapter(this,R.layout.custom_profile_item,muserProfiles);

        mlistViewUserProfiles.setAdapter(mprofileAdapter);

    }
}
