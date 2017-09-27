package com.example.study.trash.Map;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.location.Criteria;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.PopupWindow;
import android.widget.TextView;
import android.widget.Toast;

import com.example.study.trash.R;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GooglePlayServicesUtil;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.MapsInitializer;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.CircleOptions;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.ArrayList;

public class MyLocation extends Fragment implements LocationListener, GoogleMap.OnMyLocationChangeListener {
    LocationManager locationManager;
    String provider;
    GoogleMap mmap;
    static final LatLng SEOUL = new LatLng(37.56, 126.97);
    com.google.android.gms.maps.model.Circle mCircle;
    Location mylocation;
    ArrayList<BinMap> list = new ArrayList<BinMap>();
    MapConnector connector;
    Context context;
    View view;
    MapView mapView;
    Bundle savedInstanceState;
    PopupWindow popupWindow;
    String bin_id;
    boolean pop=true;
    MyLocation my;
    TextView txt_dis;
    ImageButton bt_green, bt_orange, bt_red;

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.map_layout, container, false);
        my=this;
        this.savedInstanceState = savedInstanceState;
        this.context = getActivity();

        txt_dis=(TextView)view.findViewById(R.id.txt_dis);

        int googlePlayServiceResult = GooglePlayServicesUtil.isGooglePlayServicesAvailable(context);
        if (googlePlayServiceResult != ConnectionResult.SUCCESS) { //구글 플레이 서비스를 활용하지 못할경우 <계정이 연결이 안되어 있는 경우
            //실패
            GooglePlayServicesUtil.getErrorDialog(googlePlayServiceResult, (Activity) context, 0, new DialogInterface.OnCancelListener() {
                @Override
                public void onCancel(DialogInterface dialog) {
                    //context.finish();
                }
            }).show();
        } else { //구글 플레이가 활성화 된 경우
            locationManager = (LocationManager) context.getSystemService(Context.LOCATION_SERVICE);

            Criteria criteria = new Criteria();
            criteria.setAccuracy(Criteria.ACCURACY_FINE);
            provider = locationManager.getBestProvider(criteria, true);

            if (provider.equals("passive")) {  //위치정보 설정이 안되어 있으면 설정하는 엑티비티로 이동합니다
                new AlertDialog.Builder(context)
                        .setTitle("위치서비스 동의")
                        .setNeutralButton("이동", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                startActivityForResult(new Intent(android.provider.Settings.ACTION_LOCATION_SOURCE_SETTINGS), 0);
                            }
                        }).setOnCancelListener(new DialogInterface.OnCancelListener() {
                    @Override
                    public void onCancel(DialogInterface dialog) {
                        //context.finish();
                    }
                }).show();
            } else {   //위치 정보 설정이 되어 있으면 현재위치를 받아옵니다
                try {
                    locationManager.requestLocationUpdates(provider, 1000, 0, this); //기본 위치 값 설정
                } catch (SecurityException e) {
                    e.printStackTrace();
                }
            }
            setUpMapIfNeeded(); //Map ReDraw

            mmap.moveCamera(CameraUpdateFactory.newLatLngZoom(SEOUL, 15));
            mmap.animateCamera(CameraUpdateFactory.zoomTo(15), 2000, null);

            setMyLocation(); //내위치 정하는 함수
        }

        return view;
    }

    private void setMyLocation() {
        mmap.setOnMyLocationChangeListener(this);
    }


        private void setUpMapIfNeeded() {
            Log.d(getClass().getName(), context + "");
            if (mmap == null) {
                mapView = (MapView) view.findViewById(R.id.map);
                mapView.onCreate(savedInstanceState);
                mapView.onResume();
                try {
                    MapsInitializer.initialize((getActivity().getApplicationContext()));
                } catch (Exception e) {
                    e.printStackTrace();
                }
                mmap = mapView.getMap();
            }
            if (mmap != null) {
                setUpMap();
            }
        }

        private void setUpMap() {
            mmap.setMyLocationEnabled(true);
            mmap.getMyLocation();
        }

        private void updateMarkerWithCircle(LatLng position) {
            mCircle.setCenter(position);
        }

        private void drawMarkerWithCircle(LatLng position) {
            double radiusInMeters = 300.0;
            int strokeColor = 0xffff0000; //red outline
            int shadeColor = 0x44ff0000; //opaque red fill

            CircleOptions circleOptions = new CircleOptions().center(position).radius(radiusInMeters).fillColor(shadeColor).strokeColor(strokeColor).strokeWidth(8);
            mCircle = mmap.addCircle(circleOptions);

            if (mmap != null) {
                mmap.animateCamera(CameraUpdateFactory.newLatLngZoom(position, 16.0f));
            }
        }

        public void addBin(double latitude, double longitude, int status) {
            if (status == 2) {
                mmap.addMarker(new MarkerOptions()
                        .icon(BitmapDescriptorFactory.fromResource(R.drawable.redbin))
                        .position(new LatLng(latitude, longitude)));
            } else if (status == 1) {
                mmap.addMarker(new MarkerOptions()
                        .icon(BitmapDescriptorFactory.fromResource(R.drawable.orangebin))
                        .position(new LatLng(latitude, longitude)));
            } else if (status == 0) {
                mmap.addMarker(new MarkerOptions()
                        .icon(BitmapDescriptorFactory.fromResource(R.drawable.greenbin))
                        .position(new LatLng(latitude, longitude)));
            }
        }

        @Override
        public void onMyLocationChange(Location location) {

            LatLng loc = new LatLng(location.getLatitude(), location.getLongitude());
            mylocation = location;

            connector = new MapConnector(this);
            connector.execute("http://yellowpee.cafe24.com/binmap/selectAll.jsp?lati="+location.getLatitude()+"&longi="+location.getLongitude());

            for (int i = 0; i < list.size(); i++) {
                CalDistance calDistance = new CalDistance();
                double distance = calDistance.calDistance(Double.parseDouble(list.get(i).getLatitude()), Double.parseDouble(list.get(i).getLongitude()), location.getLatitude(), location.getLongitude());

                if ((distance <= 10)&&pop) {
                    Toast.makeText(context, "도착 했습니다.", Toast.LENGTH_SHORT).show();

                    bin_id = list.get(i).getBin_id();
                    final View popupView = getLayoutInflater(savedInstanceState).inflate(R.layout.popup_layout, null);

                    popupWindow = new PopupWindow(popupView, ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);

                    Button bt_exit=(Button)popupView.findViewById(R.id.bt_exit);
                    bt_green=(ImageButton)popupView.findViewById(R.id.bt_green);
                    bt_orange=(ImageButton)popupView.findViewById(R.id.bt_orange);
                    bt_red=(ImageButton)popupView.findViewById(R.id.bt_red);

                        popupWindow.showAtLocation(popupView, Gravity.CENTER, 0, 0);
                        popupWindow.setOutsideTouchable(true);

                        bt_green.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                String connectURL = "http://yellowpee.cafe24.com/binmap/status.jsp?bin_id=" + bin_id + "&status=";
                                BinStatus binStatus = new BinStatus();
                                binStatus.execute(connectURL + "0");
                                popupWindow.dismiss();
                                Toast.makeText(context, "참여해주셔서 감사합니다.", Toast.LENGTH_SHORT).show();
                            }
                        });

                        bt_orange.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                String connectURL = "http://yellowpee.cafe24.com/binmap/status.jsp?bin_id=" + bin_id + "&status=";
                                BinStatus binStatus = new BinStatus();
                                binStatus.execute(connectURL + "1");
                                popupWindow.dismiss();
                                Toast.makeText(context, "참여해주셔서 감사합니다.", Toast.LENGTH_SHORT).show();
                            }
                        });

                        bt_red.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                String connectURL = "http://yellowpee.cafe24.com/binmap/status.jsp?bin_id=" + bin_id + "&status=";
                                BinStatus binStatus = new BinStatus();
                                binStatus.execute(connectURL + "2");
                                popupWindow.dismiss();
                                Toast.makeText(context, "참여해주셔서 감사합니다.", Toast.LENGTH_SHORT).show();
                            }
                        });
                        bt_exit.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                popupWindow.dismiss();
                            }
                        });

                    pop=false;
                }

                if (distance <= 300) {
                    int status = Integer.parseInt(list.get(i).getStatus());
                    addBin(Double.parseDouble(list.get(i).getLatitude()), Double.parseDouble(list.get(i).getLongitude()), status);
                    txt_dis.setText("거리 : " + Double.toString(distance).substring(0, 5) + "m");
                }
            }

            if (mCircle == null) {
                drawMarkerWithCircle(loc);
            }
            updateMarkerWithCircle(loc);
        }

        public void onBackPressed() {
            //context.finish();
        }

        public void onResume() {
            super.onResume();
            setUpMapIfNeeded();
        }

        public void onPause() {
            super.onPause();
            try {
                locationManager.removeUpdates(this);
            } catch (SecurityException e) {
                e.printStackTrace();
            }
        }

        @Override
        public void onLocationChanged(Location location) {
        }

        @Override
        public void onStatusChanged(String provider, int status, Bundle extras) {
        }

        @Override
        public void onProviderEnabled(String provider) {
        }

        @Override
        public void onProviderDisabled(String provider) {
        }

    }
