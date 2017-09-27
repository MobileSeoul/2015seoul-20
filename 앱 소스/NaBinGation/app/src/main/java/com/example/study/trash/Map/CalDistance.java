package com.example.study.trash.Map;

public class CalDistance {
    public double calDistance(double my_lat, double my_lon, double bin_lat, double bin_lon){

        double theta, distance;
        theta = my_lon - bin_lon;
        distance = Math.sin(deg2rad(my_lat)) * Math.sin(deg2rad(bin_lat)) + Math.cos(deg2rad(my_lat))
                * Math.cos(deg2rad(bin_lat)) * Math.cos(deg2rad(theta));
        distance = Math.acos(distance);
        distance = rad2deg(distance);

        distance = distance * 60 * 1.1515;
        distance = distance * 1.609344;    // 단위 mile 에서 km 변환.
        distance = distance * 1000.0;      // 단위  km 에서 m 로 변환

        return distance;
    }

    // 주어진 도(degree) 값을 라디언으로 변환
    private double deg2rad(double deg){
        return (double)(deg * Math.PI / (double)180d);
    }

    // 주어진 라디언(radian) 값을 도(degree) 값으로 변환
    private double rad2deg(double rad){
        return (double)(rad * (double)180d / Math.PI);
    }
}
