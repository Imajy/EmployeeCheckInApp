package com.abs.technology.presentation.home

import android.Manifest
import android.content.Context
import android.content.pm.PackageManager
import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.VerticalDivider
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.app.ActivityCompat
import com.abs.technology.R
import com.google.accompanist.permissions.ExperimentalPermissionsApi
import com.google.accompanist.permissions.rememberMultiplePermissionsState
import com.google.android.gms.location.LocationServices
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Date
import java.util.Locale

@Preview
@Composable
private fun CheckInOutPrev() {
    CheckInOut()
}

private fun fetchLocationAndShowToast(context: Context) {
    val fusedLocationClient = LocationServices.getFusedLocationProviderClient(context)

    if (ActivityCompat.checkSelfPermission(context, Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED &&
        ActivityCompat.checkSelfPermission(context, Manifest.permission.ACCESS_COARSE_LOCATION) == PackageManager.PERMISSION_GRANTED) {

        fusedLocationClient.lastLocation.addOnSuccessListener { location ->
            location?.let {
                val latitude = it.latitude
                val longitude = it.longitude
                Toast.makeText(context, "Latitude: $latitude, Longitude: $longitude", Toast.LENGTH_LONG).show()
            }
        }
    }
}

@OptIn(ExperimentalPermissionsApi::class)
@Composable
fun CheckInOut() {
    val context = LocalContext.current

    val currentDate = Calendar.getInstance()
    val dateFormat = SimpleDateFormat("dd", Locale.getDefault())
    val dayFormat = SimpleDateFormat("EEE", Locale.getDefault())


    val locationPermissionState = rememberMultiplePermissionsState(
        permissions = listOf(
            Manifest.permission.ACCESS_FINE_LOCATION,
            Manifest.permission.ACCESS_COARSE_LOCATION
        )
    )

    LaunchedEffect(Unit) {
        if (locationPermissionState.allPermissionsGranted) {
            fetchLocationAndShowToast(context)
        } else {
            locationPermissionState.launchMultiplePermissionRequest()
        }
    }
    Column {

        Row (
            modifier = Modifier
                .fillMaxWidth()
                .background(Color(0xFF063970))
        ){
            Column (
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(4.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.spacedBy(1.5.dp)
            ){
//                Spacer(modifier = Modifier.height(4.dp))
                Text(text = "Employee name", fontSize = 17.sp, color = Color.White, lineHeight = 25.sp, fontWeight = FontWeight.Medium)
                Text(text = "Employee ID", fontSize = 14.sp, color = Color.White)
            }
        }

        LazyColumn(
            modifier = Modifier
                .fillMaxWidth()
                .background(Color.LightGray),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            val today = currentDate.get(Calendar.DAY_OF_MONTH)

            items(today) {index ->
                val day = Calendar.getInstance().apply { set(Calendar.DAY_OF_MONTH, today - index) }
                val date = dateFormat.format(day.time)
                val dayName = dayFormat.format(day.time)

                val isToday = currentDate.get(Calendar.DAY_OF_MONTH) == day.get(Calendar.DAY_OF_MONTH)

                InOutComp(dayName = dayName, date = date, isToday = isToday)
                Spacer(modifier = Modifier.height(6.dp))
            }
        }
    }
}

@Composable
fun InOutComp(dayName: String, date: String, isToday: Boolean) {

    val width = LocalConfiguration.current.screenWidthDp.dp

    var checkInButton by remember { mutableStateOf(true) }
    var checkOutButton by remember { mutableStateOf(true) }
    var checkInTime by remember { mutableStateOf("") }
    var checkOutTime by remember { mutableStateOf("") }
    var workingHours by remember { mutableStateOf("") }
    var checkoutColor by remember { mutableStateOf(Color.Black) }

    Row (
        modifier = Modifier
            .background(Color.White)
            .fillMaxWidth(.97f)
            .clip(RoundedCornerShape(5.dp))
            .background(Color.White)
        ,
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceEvenly
    ) {
        Column(
            modifier = Modifier
                .padding(4.dp)
                .width(width * 0.16f)
                .height(width / 7)
                .clip(RoundedCornerShape(10.dp))
                .background(Color.Gray.copy(.24f))
                .padding(vertical = 2.dp, horizontal = 4.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Text(text = date, fontSize = 18.sp, fontWeight = FontWeight.Medium, color = Color.Black)
            Text(text = dayName, fontSize = 14.sp, fontWeight = FontWeight.Medium, color = Color.Black)
        }

        VerticalDivider(thickness = 10.dp, color = Color.Gray, modifier = Modifier
            .weight(0.01f)
            .fillMaxHeight())

        if (isToday && checkInButton) {
            Row(modifier = Modifier
                .clip(RoundedCornerShape(10.dp))
                .background(Color.Gray.copy(.2f))
                .weight(.3f)
                .padding(5.dp)
                .clickable(interactionSource = remember {
                    MutableInteractionSource ()
                }, indication = null ) {
                    checkInButton = false

                    checkInTime = SimpleDateFormat("HH:mm:ss", Locale.getDefault()).format(Date())
                },
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Center
            ){
                Text("Check In", color = Color.Black, fontSize = 12.sp)
            }
        } else if (!isToday) {
            // Disable check-in for past dates
            Text(
                text = "N/A",
                color = Color.Gray,
                fontWeight = FontWeight.Bold,
                fontSize = 12.sp,
                modifier = Modifier.weight(.4f),
                textAlign = TextAlign.Center
            )
        } else {
            Row(
                modifier = Modifier
                    .weight(.4f)
                    .fillMaxHeight(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Center
            ) {
                Image(
                    painter = painterResource(id = R.drawable.arrow_down),
                    contentDescription = null,
                    modifier = Modifier.size(18.dp)
                )

                Column(
                    modifier = Modifier.padding(start = 2.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text(text = checkInTime, fontSize = 10.sp, color = Color.Black, lineHeight = 14.sp)
                    Text(text = "Office", fontSize = 9.sp, color = Color.Black, lineHeight = 14.sp)
                }
            }
        }

        VerticalDivider(thickness = 1.dp, color = Color.Gray, modifier = Modifier
            .weight(0.01f)
            .fillMaxHeight())

        if (isToday && checkOutButton && !checkInButton) {
            Row(modifier = Modifier
                .clip(RoundedCornerShape(10.dp))
                .background(Color.Gray.copy(.2f))
                .weight(.3f)
                .padding(5.dp)
                .clickable(interactionSource = remember {
                    MutableInteractionSource ()
                }, indication = null ) {
                    if (!checkInButton) {
                        checkOutButton = false
                        checkOutTime =
                            SimpleDateFormat("HH:mm:ss", Locale.getDefault()).format(Date())

                        // Calculate working hours
                        val checkInDate =
                            SimpleDateFormat("HH:mm:ss", Locale.getDefault()).parse(checkInTime)
                        val checkOutDate =
                            SimpleDateFormat("HH:mm:ss", Locale.getDefault()).parse(checkOutTime)
                        checkInDate?.let { inTime ->
                            checkOutDate?.let { outTime ->
                                val diffInMillis = outTime.time - inTime.time
                                val hours = (diffInMillis / (1000 * 60 * 60)).toInt()
                                val minutes = ((diffInMillis / (1000 * 60)) % 60).toInt()
                                val seconds = (diffInMillis / 1000 % 60).toInt()
                                checkoutColor = if (hours < 9) Color.Black else Color.Black
                                workingHours =
                                    String.format("%02d:%02d:%02d", hours, minutes, seconds)
                            }
                        }
                    }
                },
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Center
            ){
                Text("Check Out", color = Color.Black, fontSize = 12.sp)
            }
        }
        else if (!isToday) {
            // Disable check-in for past dates
            Text(
                text = "N/A",
                color = Color.Gray,
                fontWeight = FontWeight.Bold,
                fontSize = 12.sp,
                modifier = Modifier.weight(.4f),
                textAlign = TextAlign.Center
            )
        } else {
            Row(
                modifier = Modifier
                    .weight(.4f)
                    .fillMaxHeight(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Center
            ) {
                if (checkOutTime.isNotBlank()) {
                    Image(
                        painter = painterResource(id = R.drawable.arrow_up),
                        contentDescription = null,
                        modifier = Modifier.size(18.dp)
                    )

                    Column(
                        modifier = Modifier.padding(start = 2.dp),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Text(
                            text = checkOutTime,
                            fontSize = 10.sp,
                            color = Color.Black,
                            lineHeight = 14.sp
                        )
                        Text(
                            text = "Office",
                            fontSize = 9.sp,
                            color = Color.Black,
                            lineHeight = 14.sp
                        )
                    }
                }else{
                    Text(
                        text =  "--:--",
                        fontSize = 15.sp,
                        color = Color.Black,
                        lineHeight = 14.sp,
                        fontWeight = FontWeight.Medium
                    )
                }
            }
        }

        VerticalDivider(thickness = 1.dp, color = Color.Red, modifier = Modifier
            .weight(0.01f)
            .fillMaxHeight())

        Row (
            modifier = Modifier
                .weight(.4f)
                .fillMaxHeight(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center
        ) {
            if (workingHours.isNotBlank()) {
                Image(
                    painter = painterResource(id = R.drawable.clock),
                    contentDescription = null,
                    modifier = Modifier.size(18.dp)
                )

                Column(
                    modifier = Modifier.padding(3.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text(
                        text = workingHours.ifEmpty { "--:--" },
                        fontSize = 10.sp,
                        color = checkoutColor,
                        fontWeight = FontWeight.Medium,
                        lineHeight = 14.sp
                    )
                    Text(
                        text = "Working Hour",
                        fontSize = 8.sp,
                        color = Color.Black,
                        lineHeight = 14.sp
                    )
                }
            }else{
                Text(
                    text =  "--:--",
                    fontSize = 15.sp,
                    color = Color.Black,
                    lineHeight = 14.sp,
                    fontWeight = FontWeight.Medium
                )
            }
        }
    }
}