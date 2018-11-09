package thomasvalentini.androiduiandlogin.sweng888.psu.edu.androiduiandlogin_thomasvalentini.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

import thomasvalentini.androiduiandlogin.sweng888.psu.edu.androiduiandlogin_thomasvalentini.R;
import thomasvalentini.androiduiandlogin.sweng888.psu.edu.androiduiandlogin_thomasvalentini.model.entity.UserProfile;

public class RecycleProfileAdapter extends RecyclerView.Adapter<RecycleProfileAdapter.MyViewHolder> {
        private ArrayList<UserProfile> mDataset;

        // Provide a reference to the views for each data item
        // Complex data items may need more than one view per item, and
        // you provide access to all the views for a data item in a view holder
        public static class MyViewHolder extends RecyclerView.ViewHolder {
            // each data item is just a string in this case
            public View listItemView;
            public MyViewHolder(View v) {
                super(v);
                listItemView = v;
            }
        }

        // Provide a suitable constructor (depends on the kind of dataset)
        public RecycleProfileAdapter(ArrayList<UserProfile> myDataset) {
            mDataset = myDataset;
        }

        // Create new views (invoked by the layout manager)
        @Override
        public RecycleProfileAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent,
                                                         int viewType) {
            // create a new view
            View v = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.custom_profile_item, parent, false);
            MyViewHolder vh = new MyViewHolder(v);
            return vh;
        }

        // Replace the contents of a view (invoked by the layout manager)
        @Override
        public void onBindViewHolder(MyViewHolder holder, int position) {
            // - get element from your dataset at this position
            // - replace the contents of the view with that element
            // Get the object located at this position in the list
            UserProfile profile = (UserProfile) mDataset.get(position);

            // Find the TextView in the xml layout wit the ID
            TextView textViewName = (TextView) holder.listItemView.findViewById(R.id.textViewListNameVal);

            // Get the name from the current UserProfile object and
            // set this text on the name TextView
            textViewName.setText(profile.getName());


            TextView textViewSurname = (TextView) holder.listItemView.findViewById(R.id.textViewListSurnameVal);

            // Get the surname from the current UserProfile object and
            // set this text on the surname TextView
            textViewSurname.setText(profile.getSurname());

            // Find the TextView in the xml layout with the ID version_number
            TextView textViewUsername = (TextView) holder.listItemView.findViewById(R.id.textViewListUsernameVal);

            // Get the username from the current UserProfile object and
            // set this text on the number TextView
            textViewUsername.setText(profile.getUsername());
            // Find the TextView in the xml layout with the ID version_number
            TextView textViewBirthday = (TextView) holder.listItemView.findViewById(R.id.textViewListBirthdayVal);

            // Get the username from the current UserProfile object and
            // set this text on the number TextView
            textViewBirthday.setText(profile.getBirthday());
            // Find the TextView in the xml layout with the ID version_number
            TextView textViewMobilePhone = (TextView) holder.listItemView.findViewById(R.id.textViewListMobilePhoneVal);

            // Get the username from the current UserProfile object and
            // set this text on the number TextView
            textViewMobilePhone.setText(profile.getMobile_phone());
            // Find the TextView in the xml layout with the ID version_number
            TextView textViewEmail = (TextView) holder.listItemView.findViewById(R.id.textViewListEmailVal);

            // Get the username from the current UserProfile object and
            // set this text on the number TextView
            textViewEmail.setText(profile.getEmail());

        }

        // Return the size of your dataset (invoked by the layout manager)
        @Override
        public int getItemCount() {
            return mDataset.size();
        }
}
