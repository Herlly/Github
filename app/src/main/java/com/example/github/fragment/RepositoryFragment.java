package com.example.github.fragment;


import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;


import android.view.LayoutInflater;

import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Toast;

import com.example.github.R;
import com.example.github.UserActivity;
import com.example.github.adapter.GetRepositoryItemAdapter;
import com.example.github.domain.GetRepositoryItem;
import com.example.github.domain.LocalRepository;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.litepal.LitePal;

import java.io.Serializable;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link RepositoryFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link RepositoryFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class RepositoryFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    public String content,json;
    private String user_image;
    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    private List<GetRepositoryItem> list;
    private OnFragmentInteractionListener mListener;
    private GetRepositoryItemAdapter adapter;

    public RepositoryFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *

     * @return A new instance of fragment RepositoryFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static RepositoryFragment newInstance(String json,String user_image) {
        RepositoryFragment fragment = new RepositoryFragment();
        Bundle args = new Bundle();
       args.putString("json",json);
       args.putString("user_image",user_image);
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
        View view=inflater.inflate(R.layout.fragment_repository, container, false);
        Gson gson=new Gson();
        list=gson.fromJson(json,new TypeToken<List<GetRepositoryItem>>(){}.getType());
        RecyclerView recyclerView=view.findViewById(R.id.repository_list);
        adapter = new GetRepositoryItemAdapter(getActivity(),list,user_image);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerView.setAdapter(adapter);

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
            json=argument.getString("json");
            user_image=argument.getString("user_image");
        }
        /*if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }*/

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

    @Override
    public boolean onContextItemSelected(@NonNull MenuItem item) {
        GetRepositoryItem getRepositoryItem=adapter.getRepositoryItem();
        switch (item.getItemId()){
            case 1:
                Intent intent=new Intent(Intent.ACTION_VIEW);
                intent.setData(Uri.parse(getRepositoryItem.getHtml_url()));
                startActivity(intent);
                break;
            case 0:
                if( LitePal.where("html_url=?",getRepositoryItem.getHtml_url()).find(LocalRepository.class).isEmpty()){
                    LocalRepository localRepository=new LocalRepository();
                    localRepository.setDescription(getRepositoryItem.getDescription());
                    localRepository.setFork(getRepositoryItem.getForks_count());
                    localRepository.setLanguage(getRepositoryItem.getLanguage());
                    localRepository.setRepository_name(getRepositoryItem.getName());
                    localRepository.setStar(getRepositoryItem.getStargazers_count());
                    localRepository.setUser_name(user_image);
                    localRepository.setHtml_url(getRepositoryItem.getHtml_url());
                    localRepository.save();
                    Toast.makeText(getContext(),"收藏成功",Toast.LENGTH_SHORT).show();
                }else {
                    Toast.makeText(getContext(),"你已收藏该仓库",Toast.LENGTH_SHORT).show();
                }
                break;
        }
        return super.onContextItemSelected(item);
        }


}
