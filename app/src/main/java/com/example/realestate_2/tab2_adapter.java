package com.example.realestate_2;

import android.app.Activity;
import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.List;

public class tab2_adapter extends ArrayAdapter<SellProp> {
    /**
     * Constructor
     *
     * @param context  The current context.
     * @param resource The resource ID for a layout file containing a TextView to use when
     */
    public tab2_adapter(@NonNull Context context, int resource, List<SellProp> objects) {
        super(context, resource,objects);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
//        return super.getView(position, convertView, parent);
        if (convertView == null) {
            convertView = ((Activity) getContext()).getLayoutInflater().inflate(R.layout.tab2_listitem, parent, false);
        }

        SellProp prop = getItem(position);
        String address = prop.getAddress();

        TextView address_text = (TextView) convertView.findViewById(R.id.address1);
        address_text.setText(address);





        return convertView;
    }
}
