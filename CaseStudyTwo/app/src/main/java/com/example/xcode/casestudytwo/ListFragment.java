package com.example.xcode.casestudytwo;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import java.util.*;
import com.example.xcode.casestudytwo.dummy.DummyContent;
import com.example.xcode.casestudytwo.dummy.DummyContent.DummyItem;

import java.util.List;
// import all classes
public class ListFragment extends android.support.v4.app.ListFragment{
    ImageView image;
    private FragmentActivity myContext;
    int [] imgs = new int[] { R.drawable.data, R.drawable.pol, R.drawable.seven, R.drawable.spock, R.drawable.garrak };

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }
    @Override
    public void onActivityCreated(Bundle savedInstanceState) {


        super.onActivityCreated(savedInstanceState);

        String[] values = new String[] { "Enterprise", "Star Trek Original", "Next Generation", "Deep Space 9", "Voyager"};

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(getActivity(),
                        android.R.layout.simple_list_item_1, values);
        setListAdapter(adapter);
}
        @Override
        public void onListItemClick(ListView l, View v, int position, long id) {

            String item = (String) getListAdapter().getItem(position);
            System.out.println("item is " + item);
            System.out.println("position is " + position);
            DetailFragment frag = (DetailFragment)
                    getFragmentManager().findFragmentById(R.id.frag_capt);

            if (frag != null && frag.isInLayout()) {
                frag.setText(getCapt(item));
                frag.setImage(getImage(position));
                frag.setWeb(getWeb(item));
            }
        }

        public int getImage(int ship) {

            if (ship == 0) {
                return R.drawable.pol;
            }
            if (ship == 1) {
                return R.drawable.spock;
            }
            if (ship == 2) {
                return R.drawable.data;
            }
            if (ship == 3) {
                return R.drawable.garrak;
            }
            if (ship == 4) {
                return R.drawable.seven;
            }
             return 0;
        }


        private String getCapt(String ship) {

            if (ship.toLowerCase().contains("enterprise")) {
                return "TPol";
            }
            if (ship.toLowerCase().contains("star trek")) {
                return "Mr SpocK";
            }
            if (ship.toLowerCase().contains("next generation")) {
                return "Commander Data";
            }
            if (ship.toLowerCase().contains("deep space 9")) {
                return "Garrak";
            }
            if (ship.toLowerCase().contains("voyager")) {
                return "Seven of Nine";
            }
            return "???";
        }

    private String getWeb(String ship) {
        if(ship.toLowerCase().contains("enterprise")) {
            return "https://en.wikipedia.org/wiki/Star_Trek:_Enterprise";
        }
        if(ship.toLowerCase().contains("star trek")) {
            return "https://en.wikipedia.org/wiki/Star_Trek";
        }
        if(ship.toLowerCase().contains("next generation")) {
            return "https://en.wikipedia.org/wiki/Star_Trek:_The_Next_Generation";
        }
        if(ship.toLowerCase().contains("deep space 9")) {
            return "https://en.wikipedia.org/wiki/Star_Trek:_Deep_Space_Nine";
        }
        if(ship.toLowerCase().contains("voyager")) {
            return "https://en.wikipedia.org/wiki/Star_Trek:_Voyager";
        }
        return "https://google.ca";
    }
    }