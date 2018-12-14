package com.programmingmobile.pageviewer;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

public class MyFragment extends Fragment {
	public static final String EXTRA_MESSAGE = "EXTRA_MESSAGE";
	public static final String EXTRA_MESSAGE_1 = "EXTRA_IMAGE";
	public static final MyFragment newInstance(String message, int imageId)
	{
		MyFragment fragment = new MyFragment();
		Bundle bdl = new Bundle();
	    bdl.putString(EXTRA_MESSAGE, message);
	    bdl.putInt(EXTRA_MESSAGE_1, imageId);
	    fragment.setArguments(bdl);
	    return fragment;
	}

	@Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, 
        Bundle savedInstanceState) {
		String message = getArguments().getString(EXTRA_MESSAGE);
		int imageId = getArguments().getInt(EXTRA_MESSAGE_1);
		View v = inflater.inflate(R.layout.myfragment_layout, container, false);
		TextView messageTextView = v.findViewById(R.id.textView);
		ImageView showPicture = v.findViewById(R.id.imageView);
		messageTextView.setText(message);
		showPicture.setImageResource(imageId);
        return v;
    }
}
