package thomasvalentini.androiduiandlogin.sweng888.psu.edu.androiduiandlogin_thomasvalentini.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

import thomasvalentini.androiduiandlogin.sweng888.psu.edu.androiduiandlogin_thomasvalentini.R;
import thomasvalentini.androiduiandlogin.sweng888.psu.edu.androiduiandlogin_thomasvalentini.model.entity.UserProfile;

public class UserProfileAdapter extends ArrayAdapter {
    public UserProfileAdapter(Context context, int resource, List objects){
        super(context,resource,objects);
    }
    @NonNull
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        // It recover the view to the inflate

        View listItemView = convertView;

        if(listItemView == null) {
            listItemView = LayoutInflater.from(getContext()).inflate(
                    R.layout.custom_profile_item, parent, false);
        }

        // Get the object located at this position in the list
        UserProfile profile = (UserProfile) getItem(position);

        // Find the TextView in the xml layout wit the ID
        TextView textViewName = (TextView) listItemView.findViewById(R.id.textViewListNameVal);

        // Get the name from the current UserProfile object and
        // set this text on the name TextView
        textViewName.setText(profile.getName());


        TextView textViewSurname = (TextView) listItemView.findViewById(R.id.textViewListSurnameVal);

        // Get the surname from the current UserProfile object and
        // set this text on the surname TextView
        textViewSurname.setText(profile.getSurname());

        // Find the TextView in the xml layout with the ID version_number
        TextView textViewUsername = (TextView) listItemView.findViewById(R.id.textViewListUsernameVal);

        // Get the username from the current UserProfile object and
        // set this text on the number TextView
        textViewUsername.setText(profile.getUsername());
        // Find the TextView in the xml layout with the ID version_number
        TextView textViewBirthday = (TextView) listItemView.findViewById(R.id.textViewListBirthdayVal);

        // Get the username from the current UserProfile object and
        // set this text on the number TextView
        textViewBirthday.setText(profile.getBirthday());
        // Find the TextView in the xml layout with the ID version_number
        TextView textViewMobilePhone = (TextView) listItemView.findViewById(R.id.textViewListMobilePhoneVal);

        // Get the username from the current UserProfile object and
        // set this text on the number TextView
        textViewMobilePhone.setText(profile.getMobile_phone());
        // Find the TextView in the xml layout with the ID version_number
        TextView textViewEmail = (TextView) listItemView.findViewById(R.id.textViewListEmailVal);

        // Get the username from the current UserProfile object and
        // set this text on the number TextView
        textViewEmail.setText(profile.getEmail());

        // Return the whole list item layout
        // so that it can be shown in the ListView
        return listItemView;


    }
}
