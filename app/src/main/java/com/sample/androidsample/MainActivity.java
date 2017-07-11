package com.sample.androidsample;

import android.content.Context;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import com.bumptech.glide.Glide;
import com.flaviofaria.kenburnsview.KenBurnsView;
import com.mikhaellopez.circularimageview.CircularImageView;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private static int count=1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d("on Create","count : "+count++);
        setContentView(R.layout.activity_main);
        ListView contactsListView = (ListView) findViewById(R.id.contact_list);
        final KenBurnsView contactPicExpandedView = (KenBurnsView) findViewById(R.id.contact_pic_expanded);
        contactsListView.setAdapter(new ContactAdapter(this, Contact.getDummyContacts()));
        contactsListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                contactPicExpandedView.setVisibility(View.VISIBLE);
                Glide.with(MainActivity.this).load(Contact.getDummyContacts().get(i).getPhotoUrl()).into(contactPicExpandedView);
            }
        });
        contactPicExpandedView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                contactPicExpandedView.setVisibility(View.GONE);
            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.d("on Start","count : "+count++);
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.d("on Pause","count : "+count++);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.d("on Destroy","count : "+count++);
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.d("on Resume","count : "+count++);
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.d("on Stop","count : "+count++);
    }

    @Override
    public void onSaveInstanceState(Bundle outState, PersistableBundle outPersistentState) {
        super.onSaveInstanceState(outState, outPersistentState);
        Log.d("on savedInstance","count : "+count++);
    }



    private class ContactAdapter extends ArrayAdapter<Contact> {
        private Context context;
        private List<Contact> contacts;

        ContactAdapter(@NonNull Context context, @NonNull List<Contact> objects) {
            super(context, R.layout.contact_item, objects);
            this.context = context;
            contacts = objects;
        }

        class ViewHolder {
            TextView contactName;
            TextView contactNumber;
            CircularImageView contactPic;
        }

        @NonNull
        @Override
        public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
            ViewHolder viewHolder;
            if (convertView == null) {
                viewHolder = new ViewHolder();
                convertView = View.inflate(context, R.layout.contact_item, null);
                viewHolder.contactName = (TextView) convertView.findViewById(R.id.contact_name);
                viewHolder.contactNumber = (TextView) convertView.findViewById(R.id.contact_number);
                viewHolder.contactPic = (CircularImageView) convertView.findViewById(R.id.contact_pic);
                convertView.setTag(viewHolder);
            } else {
                viewHolder = (ViewHolder) convertView.getTag();
            }
            Contact currentContact = contacts.get(position);

            viewHolder.contactName.setText(currentContact.getName());
            viewHolder.contactNumber.setText(currentContact.getNumber());
            Glide.with(context).load(currentContact.getPhotoUrl()).into(viewHolder.contactPic);

            return convertView;
        }
    }
}
