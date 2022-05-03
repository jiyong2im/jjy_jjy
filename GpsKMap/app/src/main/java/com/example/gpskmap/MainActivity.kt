package com.example.gpskmap


import android.Manifest
import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.location.Location
import android.location.LocationManager
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat

import android.graphics.Color
import android.net.Uri
import android.provider.Settings
import androidx.appcompat.app.AlertDialog
import androidx.core.content.ContextCompat.getSystemService
import com.example.gpskmap.databinding.ActivityMainBinding
import net.daum.mf.map.api.*

////8
//val PERMISSIONS_REQUEST_CODE = 100
//var REQUIRED_PERMISSIONS = arrayOf<String>( Manifest.permission.ACCESS_FINE_LOCATION)
//

class MainActivity : AppCompatActivity() {
    private val ACCESS_FINE_LOCATION = 1000

     private lateinit var binding: ActivityMainBinding
    val PERMISSIONS_REQUEST_CODE = 100
    var REQUIRED_PERMISSIONS = arrayOf<String>(Manifest.permission.ACCESS_FINE_LOCATION)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater);
        setContentView(binding.root)
//        val mapView = MapView(this)
//        binding.clKakaoMapView.addView(mapView)

        //setContentView(R.layout.activity_main)
        val mapView = MapView(this)
        val mapViewContainer = binding.mapView
        mapViewContainer.addView(mapView)
//        val mapView = MapView(this)
//        val mapViewContainer = map_view
//        mapViewContainer.addView(mapView)
//
//
//        if (checkLocationService()) {
//            permissionCheck()
//
//        } else {
//            Toast.makeText(this, "GPS를 켜주세요", Toast.LENGTH_SHORT).show()
//        }
//
//
//        if (checkLocationService()) {
//            permissionCheck()
//        } else {
//            Toast.makeText(this, "GPS를 켜주세요", Toast.LENGTH_SHORT).show()
//        }


        val permissionCheck =
            ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION)
        if (permissionCheck == PackageManager.PERMISSION_GRANTED) {
            try {
                mapView.currentLocationTrackingMode = MapView.CurrentLocationTrackingMode.TrackingModeOnWithoutHeading

                val lm: LocationManager = getSystemService(Context.LOCATION_SERVICE) as LocationManager
                val userNowLocation: Location = lm.getLastKnownLocation(LocationManager.NETWORK_PROVIDER)!!
                val uLatitude = 37.4467912//userNowLocation.latitude
                val uLongitude = 127.165324//userNowLocation.longitude
                val uNowPosition = MapPoint.mapPointWithGeoCoord(uLatitude, uLongitude)
                mapView.setMapCenterPoint(uNowPosition, true)
                //    현 위치에 마커 찍기
                val marker = MapPOIItem()
                marker.itemName = "현 위치"
                marker.mapPoint = uNowPosition
                marker.markerType = MapPOIItem.MarkerType.BluePin
                marker.selectedMarkerType = MapPOIItem.MarkerType.RedPin
                mapView.addPOIItem(marker)
                //원 그리기
                val circle1 = MapCircle(
                    MapPoint.mapPointWithGeoCoord(uLongitude, uLatitude),  // center
                    400,  // radius
                    Color.argb(255, 0, 0, 0),  // strokeColor
                    Color.argb(255, 255, 255, 255)
                ) //fillcolor
                circle1.tag = 1234
                //지도뷰의 중심좌표와 줌레벨을 Circle이 모두 나오도록 조정.
                val mapPointBoundsArray = arrayOf(circle1.bound,) //circle2.bound)
                val mapPointBounds = MapPointBounds(mapPointBoundsArray)
                val padding = 50 // px
                mapView.addCircle(circle1)


            } catch (e: NullPointerException) {
                Log.e("LOCATION_ERROR", e.toString())
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
                    ActivityCompat.finishAffinity(this)
                } else {
                    ActivityCompat.finishAffinity(this)
                }

                val intent = Intent(this, MainActivity::class.java)
                startActivity(intent)
                System.exit(0)
            }
        } else {
            Toast.makeText(this, "위치 권한이 없습니다.", Toast.LENGTH_SHORT).show()
            ActivityCompat.requestPermissions(this, REQUIRED_PERMISSIONS, PERMISSIONS_REQUEST_CODE)
        }
    }




//    //
//
//
//
//private fun permissionCheck() {
//    val preference = getPreferences(MODE_PRIVATE)
//    val isFirstCheck = preference.getBoolean("isFirstPermissionCheck", true)
//    if (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
//        // 권한이 없는 상태
//        if (ActivityCompat.shouldShowRequestPermissionRationale(this,
//                Manifest.permission.ACCESS_FINE_LOCATION)) {
//            // 권한 거절
//            val builder = AlertDialog.Builder(this)
//            builder.setMessage("현재 위치를 확인하시려면 위치 권한을 허용해주세요.")
//            builder.setPositiveButton("확인") { dialog, which ->
//                ActivityCompat.requestPermissions(this,
//                    arrayOf(Manifest.permission.ACCESS_FINE_LOCATION), ACCESS_FINE_LOCATION)
//            }
//            builder.setNegativeButton("취소") { dialog, which ->
//
//            }
//            builder.show()
//        } else {
//            if (isFirstCheck) {
//                // 최초 권한 요청
//                preference.edit().putBoolean("isFirstPermissionCheck", false).apply()
//                ActivityCompat.requestPermissions(this,
//                    arrayOf(Manifest.permission.ACCESS_FINE_LOCATION), ACCESS_FINE_LOCATION)
//            } else {
//                val builder = AlertDialog.Builder(this)
//                builder.setMessage("현재 위치를 확인하시려면 설정에서 위치 권한을 허용해주세요.")
//                builder.setPositiveButton("설정으로 이동") { dialog, which ->
//                    val intent = Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS, Uri.parse("package:$packageName"))
//                    startActivity(intent)
//                }
//                builder.setNegativeButton("취소") { dialog, which ->
//
//                }
//                builder.show()
//            }
//        }
//    } else {
//
//    }
//
//}
//
//// 권한 요청
//override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
//    super.onRequestPermissionsResult(requestCode, permissions, grantResults)
//    if (requestCode == ACCESS_FINE_LOCATION) {
//        if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
//            Toast.makeText(this, "위치 권한이 승인되었습니다", Toast.LENGTH_SHORT).show()
//
//        } else {
//            Toast.makeText(this, "위치 권한이 거절되었습니다", Toast.LENGTH_SHORT).show()
//
//        }
//    }
//}
//
//// GPS가 켜져있는지 확인
//private fun checkLocationService(): Boolean {
//    val locationManager = getSystemService(Context.LOCATION_SERVICE) as LocationManager
//    return locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER)
//}
/////////////////////////////////////여기 까지 원래 코드 였############################333333333333333333333
}


// 이건 아님
//    @SuppressLint("MissingPermission")
//            private fun startTracking() {
//            mapView.currentLocationTrackingMode = MapView.CurrentLocationTrackingMode.TrackingModeOnWithoutHeading  //이 부분
//
//            val lm: LocationManager = getSystemService(Context.LOCATION_SERVICE) as LocationManager
//            val userNowLocation: Location? = lm.getLastKnownLocation(LocationManager.NETWORK_PROVIDER)
//            //위도 , 경도
//            val uLatitude = userNowLocation?.latitude
//            val uLongitude = userNowLocation?.longitude
//            val uNowPosition = MapPoint.mapPointWithGeoCoord(uLatitude!!, uLongitude!!)
//        // 지금 위치에 마커 추가
//        val marker = MapPOIItem()
//        marker.apply {
//            itemName = "지용 집"   // 마커 이름
//            //mapPoint = MapPoint.mapPointWithGeoCoord(uLatitude, uLongitude)   // 좌표
//            mapPoint = uNowPosition
//            markerType = MapPOIItem.MarkerType.CustomImage          // 마커 모양 (커스텀)
//            customImageResourceId = R.drawable.map_pin_blue               // 커스텀 마커 이미지
//            selectedMarkerType = MapPOIItem.MarkerType.CustomImage  // 클릭 시 마커 모양 (커스텀)
//            customSelectedImageResourceId = R.drawable.map_pin_red       // 클릭 시 커스텀 마커 이미지
//            isCustomImageAutoscale = false      // 커스텀 마커 이미지 크기 자동 조정
//            setCustomImageAnchor(0.5f, 1.0f)    // 마커 이미지 기준점
//        }
//        mapView.addPOIItem(marker) // 마커추가
//
//        // 줌 인
//        mapView.zoomIn(true);
//        // 줌 아웃
//        mapView.zoomOut(true);
//
//        // 중심점 변경 + 줌 레벨 변경
//        mapView.setMapCenterPointAndZoomLevel(
//            MapPoint.mapPointWithGeoCoord(uLatitude, uLongitude), 3, true);
//
//         //    현 위치에 마커 찍기
////            val marker = MapPOIItem()
////            marker.itemName = "현 위치"
////            marker.mapPoint = uNowPosition
////            marker.markerType = MapPOIItem.MarkerType.BluePin
////            marker.selectedMarkerType = MapPOIItem.MarkerType.RedPin
////            mapView.addPOIItem(marker)
//        }
//
//        // 위치추적 중지
//        private fun stopTracking() {
//            mapView.currentLocationTrackingMode = MapView.CurrentLocationTrackingMode.TrackingModeOff
//        }
//
//        override fun onDestroy() {
//            super.onDestroy()
//            stopTracking()
//        }






