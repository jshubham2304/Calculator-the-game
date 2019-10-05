package com.service.Allymitt.service;

import android.Manifest;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.os.IBinder;
import android.os.SystemClock;
import android.util.Log;

import androidx.core.app.ActivityCompat;

import com.google.android.gms.location.LocationServices;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import static com.service.Allymitt.ui.MapsActivity.mGoogleApiClient;


public class MyService extends Service {


    DatabaseReference mref = FirebaseDatabase.getInstance().getReference().child("UrbanClap").child("serviceProvider").child("worker");
    int count = 1;
    private Location location = null;

    FirebaseUser firebaseUser;



    public MyService(Context context)
    {
        super();

    }

    public MyService()
    {

    }


    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        super.onStartCommand(intent,flags,startId);
        firebaseUser= FirebaseAuth.getInstance().getCurrentUser();

        addValues();




        return START_STICKY;
    }



    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Intent broadcastIntent = new Intent(this, ServiceBroadCast.class);

        sendBroadcast(broadcastIntent);
      //  Intent intent=new Intent()
    }

    public void addValues() {
        try {
            while (count != 0) {
                if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                    // TODO: Consider calling
                    //    ActivityCompat#requestPermissions
                    // here to request the missing permissions, and then overriding
                    //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
                    //                                          int[] grantResults)
                    // to handle the case where the user grants the permission. See the documentation
                    // for ActivityCompat#requestPermissions for more details.

                }
                location = LocationServices.FusedLocationApi.getLastLocation(mGoogleApiClient);


                if(location!=null)
                {

                    mref.child(firebaseUser.getUid()).child("locationupdates").child("lat").setValue(location.getLatitude());
                    mref.child(firebaseUser.getUid()).child("locationupdates").child("long").setValue(location.getLongitude());
                    Log.e("LocationUpadtes",location.getLatitude()+":: "+location.getLongitude());
                    SystemClock.sleep(4000);

                }




            }
        }
        catch(Exception e)
        {

        }

    }


}
