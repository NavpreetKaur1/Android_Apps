package com.example.xcode.casestudytwo;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageView;
import android.widget.TextView;


// you have to import all classes
public class DetailFragment extends Fragment{
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }
    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle
            savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_detail, container, false);
        return view;
    }
    public void setText(String item) {
        TextView view = (TextView) getView().findViewById(R.id.captain);
        view.setText(item);
    }

    public void setImage (int item) {
        ImageView image = (ImageView) getView().findViewById(R.id.imageView);
        System.out.println("image is " + image);
        image.setImageResource(item);
    }
    public void setWeb (String item) {
        WebView web =  getView().findViewById(R.id.webView);
        web.setWebViewClient(new WebViewClient());
        web.loadUrl(item);
    }

}

