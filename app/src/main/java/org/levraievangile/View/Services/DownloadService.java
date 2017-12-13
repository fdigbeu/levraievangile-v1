package org.levraievangile.View.Services;

import android.app.DownloadManager;
import android.app.Service;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.IBinder;

import org.levraievangile.Presenter.HomePresenter;

/**
 * Created by Maranatha on 30/08/2017.
 */

public class DownloadService extends Service
{
    private BroadcastReceiver downloadReceiver;

    @Override
    public IBinder onBind(Intent arg0)
    {
        return null;
    }

    @Override
    public void onCreate()
    {
        registerScreenOffReceiver();
    }

    @Override
    public void onDestroy()
    {
        unregisterReceiver(downloadReceiver);
        downloadReceiver = null;
    }

    private void registerScreenOffReceiver()
    {
        downloadReceiver = new BroadcastReceiver()
        {
            @Override
            public void onReceive(Context context, Intent intent)
            {
                HomePresenter homePresenter = new HomePresenter();
                homePresenter.fileIsDownloadSuccessFully(context, intent);
            }
        };

        IntentFilter filter = new IntentFilter(DownloadManager.ACTION_DOWNLOAD_COMPLETE);
        registerReceiver(downloadReceiver, filter);
    }
}
