package com.example.myapplication;

import android.graphics.Typeface;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import com.ajts.androidmads.fontutils.FontUtils;
import retrofit.RxJavaCallAdapterFactory;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

    public static String BaseUrl = "http://api.openweathermap.org/";
    public static String AppId = "e8a0eea80fb3cf3b499343766222f5f8";
    public static String lat = "35";
    public static String lon = "139";

    private TextView weatherData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //getCurrentData();

        weatherData = findViewById(R.id.textView);

//        Typeface typeface = Typeface.createFromAsset(getAssets(), "Lato-Bold.ttf");
//        FontUtils fontUtils = new FontUtils();
//        fontUtils.applyFontToView(weatherData, typeface);

        findViewById(R.id.button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getCurrentData();
            }
        });
    }

    void getCurrentData() {
        System.out.println("calling weather api----");
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BaseUrl)
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        WeatherService service = retrofit.create(WeatherService.class);
        Call call = service.getCurrentWeatherData(lat, lon, AppId);
        call.enqueue(new Callback() {
            @Override
            public void onResponse(@NonNull Call call, @NonNull Response response) {
                if (response.code() == 200) {
                    Log.d("response----------", response.body().toString());
                }

            }

            @Override
            public void onFailure(Call call, Throwable t) {
                Log.e("error", "error");
            }

        });
    }
}