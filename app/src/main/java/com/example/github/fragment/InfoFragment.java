package com.example.github.fragment;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.github.R;
import com.example.github.domain.GetUserItem;
import com.google.gson.Gson;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;

import javax.net.ssl.HttpsURLConnection;

public class InfoFragment extends Fragment {
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    public String content;
    private String user_name;
    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    private OnFragmentInteractionListener mListener;
    private String avatar_url,html_url,bio,created_at;
    private int followers;
    private int followings;

    public InfoFragment() {
        // Required empty public constructor
    }

    public static InfoFragment newInstance(int followers,int followings,String bio) {
        InfoFragment fragment = new InfoFragment();
        Bundle args = new Bundle();
        args.putInt("Followers",followers);
        args.putInt("Following", followings);
        args.putString("签名",bio);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.fragment_info, container, false);
        TextView Bio = (TextView)view.findViewById(R.id.info_text_name);
        TextView follower=(TextView)view.findViewById(R.id.info_text_followers_count);
        follower.setText(String.valueOf(followers));
        TextView following=view.findViewById(R.id.info_text_following_count);
        following.setText(String.valueOf(followings));
        if(bio!=null)Bio.setText(bio);
        else Bio.setText("无");
        return view;
    }
    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }
    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        Bundle argument=getArguments();
        if(argument!=null){
            bio=argument.getString("签名");
            followers=argument.getInt("Followers");
            followings=argument.getInt("Following");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }
}
