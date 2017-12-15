package org.levraievangile.View.Fragments;


import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import org.levraievangile.Model.DownloadFile;
import org.levraievangile.Presenter.DownloadPresenter;
import org.levraievangile.R;
import org.levraievangile.View.Activities.DownloadActivity;
import org.levraievangile.View.Adapters.DownloadRecyclerAdapter;
import org.levraievangile.View.Interfaces.DownloadView;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 */
public class DownloadVideoFragment extends Fragment implements DownloadView.IDownloadVideoView{
    // Ref Download interface
    private DownloadView.IDownload iDownload;
    // Ref widgets
    private RecyclerView downloadRecyclerView;
    private ProgressBar downloadProgressBar;
    // Presenter
    private DownloadPresenter downloadPresenter;

    public DownloadVideoFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_download_video, container, false);
        downloadPresenter = new DownloadPresenter(this);
        downloadPresenter.loadDownloadVideoFragData(rootView);
        return rootView;
    }

    @Override
    public void initialize(View rootView) {
        downloadRecyclerView = rootView.findViewById(R.id.downloadRecyclerView);
        downloadProgressBar = rootView.findViewById(R.id.downloadProgressBar);
    }

    @Override
    public void events() {

    }

    @Override
    public void loadDownloadVideoData(ArrayList<DownloadFile> downloads, int numberColumns) {
        GridLayoutManager gridLayout = new GridLayoutManager(getActivity(), numberColumns);
        downloadRecyclerView.setLayoutManager(gridLayout);
        downloadRecyclerView.setHasFixedSize(true);
        DownloadRecyclerAdapter adapter = new DownloadRecyclerAdapter(downloads, this);
        downloadRecyclerView.setAdapter(adapter);
    }

    @Override
    public void progressBarVisibility(int visibility) {
        downloadProgressBar.setVisibility(visibility);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        DownloadPresenter downloadPresenter = new DownloadPresenter(iDownload, this);
        downloadPresenter.downloadVideoFragmentAttachSuccess(getActivity());
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        context = getActivity();
        iDownload =(DownloadView.IDownload) context;
        ((DownloadActivity)context).instanciateIDownloadVideoView(this);
    }
}
