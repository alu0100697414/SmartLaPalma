package com.user.jose.smartlapalma.Controllers.Utils;

import android.content.Context;
import android.util.Log;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.user.jose.smartlapalma.Models.InterestPlaces.ArcheologicalSite;
import com.user.jose.smartlapalma.Models.Meteorology.DayWeather;
import com.user.jose.smartlapalma.Models.Meteorology.Humidity;
import com.user.jose.smartlapalma.Models.Meteorology.Precipitation;
import com.user.jose.smartlapalma.Models.Meteorology.SkyState;
import com.user.jose.smartlapalma.Models.Meteorology.Temperature;
import com.user.jose.smartlapalma.Models.Meteorology.ThermalSensation;
import com.user.jose.smartlapalma.Models.Meteorology.UV;
import com.user.jose.smartlapalma.Models.Meteorology.Weather;
import com.user.jose.smartlapalma.Models.Meteorology.Wind;
import com.user.jose.smartlapalma.Models.Transports.BusStop;
import com.user.jose.smartlapalma.Models.InterestPlaces.Church;
import com.user.jose.smartlapalma.Models.InterestPlaces.Library;
import com.user.jose.smartlapalma.Models.InterestPlaces.Monument;
import com.user.jose.smartlapalma.Models.OpenDataLaPalma;
import com.user.jose.smartlapalma.Models.Transports.TaxiStop;
import com.user.jose.smartlapalma.Models.InterestPlaces.TouristAccommodation;
import com.user.jose.smartlapalma.Views.Activities.BusActivity;
import com.user.jose.smartlapalma.Views.Activities.TaxiActivity;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class Request {

    private static final String TAG = "Request";

    public static RequestQueue requestQueue;

    private static final String BUS_STOPS_URL = "https://services.arcgis.com/hkQNLKNeDVYBjvFE/" +
            "arcgis/rest/services/Transportes/FeatureServer/1/query?where=1%3D1&outFields=ID," +
            "PARADA,DMSLat,DMSLon,LINEAS&returnGeometry=false&outSR=4326&f=json";

    private static final String TAXI_STOPS_URL = "https://services.arcgis.com/hkQNLKNeDVYBjvFE/" +
            "arcgis/rest/services/Transportes/FeatureServer/0/query?where=1%3D1&outFields=Id," +
            "Municipio,Telefono,Direccion,Nombre&returnGeometry=false&outSR=4326&f=json";

    private static final String TOURIST_ACCOMMODATION_URL = "https://services.arcgis.com/" +
            "hkQNLKNeDVYBjvFE/arcgis/rest/services/Alojamientos_turisticos/FeatureServer/0/" +
            "query?where=1%3D1&outFields=ID,NOMBRE,TELEFONO,UTM_X,UTM_Y&returnGeometry=false&" +
            "outSR=4326&f=json";

    private static final String CHURCH_URL = "https://services.arcgis.com/hkQNLKNeDVYBjvFE/" +
            "arcgis/rest/services/Iglesias_Patrimonio/FeatureServer/0/query?where=1%3D1&outFields" +
            "=OBJECTID,NOMBRE,DIRECCICN,UTM_X,UTM_Y&returnGeometry=false&outSR=4326&f=json";

    private static final String ARCHEOLOGICAL_SITE_URL = "https://services.arcgis.com/" +
            "hkQNLKNeDVYBjvFE/arcgis/rest/services/BIC/FeatureServer/3/query?where=1%3D1&outFields" +
            "=OBJECTID,Nombre,Dirección,UTM_X,UTM_Y&returnGeometry=false&outSR=4326&f=json";

    private static final String LIBRARY_URL = "https://services.arcgis.com/hkQNLKNeDVYBjvFE/" +
            "arcgis/rest/services/bibliotecas/FeatureServer/0/query?where=1%3D1&outFields=" +
            "OBJECTID,NOMBRE,DIRECCIàN,UTM_X,UTM_Y&returnGeometry=false&outSR=4326&f=json";

    private static final String MONUMENT_URL = "https://services.arcgis.com/hkQNLKNeDVYBjvFE/" +
            "arcgis/rest/services/BIC/FeatureServer/1/query?where=1%3D1&outFields=OBJECTID," +
            "Nombre,Dirección,UTM_X,UTM_Y&returnGeometry=false&outSR=4326&f=json";

    private static final String WEATHER_API = "https://opendata.aemet.es/opendata/api/prediccion" +
            "/especifica/municipio/diaria/38037?api_key=eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJqb3NlYm" +
            "FnYW5ldGUxM0BnbWFpbC5jb20iLCJqdGkiOiIyNTI5NmRiZC0zY2E4LTQyMWItOWY0Ni05ZjhjODM3MDM" +
            "2OWIiLCJpc3MiOiJBRU1FVCIsImlhdCI6MTUyMjYwMzg2MywidXNlcklkIjoiMjUyOTZkYmQtM2NhOC00" +
            "MjFiLTlmNDYtOWY4YzgzNzAzNjliIiwicm9sZSI6IiJ9.lpqOLri3v0KsJKF4N2Vf1R2LfMuGz5A9zeqmgS" +
            "1uVWo";

    // Get JSON with bus stops of the island
    public static void getBusStops(){

        StringRequest stringRequest = new StringRequest(com.android.volley.Request.Method.GET, BUS_STOPS_URL,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {

                        // Get instance of singleton class and clear previuos data
                        OpenDataLaPalma openDataLaPalma = OpenDataLaPalma.getInstance();
                        openDataLaPalma.getmBusStopList().clear();

                        try {
                            // Convert string to JSON and get item json array
                            JSONObject obj = new JSONObject(response);
                            JSONArray jArray = obj.getJSONArray(BusStop.mFeaturesKey);

                            // Save items in singleton
                            for (int i=0; i<jArray.length(); i++) {

                                JSONObject currentObject = jArray.getJSONObject(i);

                                String latitude = currentObject.getJSONObject(BusStop.mAttributesKey).getString(BusStop.mLatKey);
                                String longitude = currentObject.getJSONObject(BusStop.mAttributesKey).getString(BusStop.mLngKey);

                                if(!latitude.isEmpty() && !longitude.isEmpty() &&
                                        !latitude.equals("null") && !longitude.equals("null")){

                                    // Get latitude and longitude in double
                                    double customLat = CustomUtils.getLat(latitude);
                                    double customLng = CustomUtils.getLng(longitude);

                                    // Save the stop
                                    openDataLaPalma.getmBusStopList().add(new BusStop(
                                            currentObject.getJSONObject(BusStop.mAttributesKey).getInt(BusStop.mIdKey),
                                            customLat,
                                            customLng,
                                            currentObject.getJSONObject(BusStop.mAttributesKey).getString(BusStop.mNameKey),
                                            currentObject.getJSONObject(BusStop.mAttributesKey).getString(BusStop.mLineKey)
                                    ));
                                }
                            }

                            BusActivity.loadData();
                        } catch (Throwable t) {
                            Log.e(TAG, "Error while parsing the json bus.", t);
                        }

                        GetApplicationDataTask.setBusStopCallState(true);
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        GetApplicationDataTask.setBusStopCallState(true);
                    }
        });

        requestQueue.add(stringRequest);
    }

    // Get JSON with taxi stops of the island
    public static void getTaxiStops(){

        StringRequest stringRequest = new StringRequest(com.android.volley.Request.Method.GET, TAXI_STOPS_URL,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {

                        // Get instance of singleton class and clear previuos data
                        OpenDataLaPalma openDataLaPalma = OpenDataLaPalma.getInstance();
                        openDataLaPalma.getmTaxiStopList().clear();

                        try {
                            // Convert string to JSON and get item json array
                            JSONObject obj = new JSONObject(response);
                            JSONArray jArray = obj.getJSONArray(TaxiStop.mFeaturesKey);

                            // Save items in singleton
                            for (int i=0; i<jArray.length(); i++) {

                                JSONObject currentObject = jArray.getJSONObject(i);

                                String direction = currentObject.getJSONObject(TaxiStop.mAttributesKey).getString(TaxiStop.mDireccionKey)
                                        + ", " + currentObject.getJSONObject(TaxiStop.mAttributesKey).getString(TaxiStop.mMunicipioKey);

                                // Save the stop
                                openDataLaPalma.getmTaxiStopList().add(new TaxiStop(
                                        currentObject.getJSONObject(TaxiStop.mAttributesKey).getInt(TaxiStop.mIdKey),
                                        currentObject.getJSONObject(TaxiStop.mAttributesKey).getString(TaxiStop.mNombreKey),
                                        direction,
                                        currentObject.getJSONObject(TaxiStop.mAttributesKey).getString(TaxiStop.mTelefonoKey)
                                ));

                            }

                            TaxiActivity.loadData();
                        } catch (Throwable t) {
                            Log.e(TAG, "Error while parsing the json taxi.");
                        }

                        GetApplicationDataTask.setTaxiStopCallState(true);
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        GetApplicationDataTask.setTaxiStopCallState(true);
                    }
                });

        requestQueue.add(stringRequest);
    }

    // Get JSON with bus stops of the island
    public static void getTouristAccommodation(){

        StringRequest stringRequest = new StringRequest(com.android.volley.Request.Method.GET, TOURIST_ACCOMMODATION_URL,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {

                        // Get instance of singleton class and clear previuos data
                        OpenDataLaPalma openDataLaPalma = OpenDataLaPalma.getInstance();
                        openDataLaPalma.getmTouristAccommodationList().clear();

                        try {
                            // Convert string to JSON and get item json array
                            JSONObject obj = new JSONObject(response);
                            JSONArray jArray = obj.getJSONArray(TouristAccommodation.mFeaturesKey);

                            // Save items in singleton
                            for (int i=0; i<jArray.length(); i++) {

                                JSONObject currentObject = jArray.getJSONObject(i);

                                String utm_latitude = currentObject.getJSONObject(TouristAccommodation.mAttributesKey).getString(TouristAccommodation.mUtmXKey);
                                String utm_longitude = currentObject.getJSONObject(TouristAccommodation.mAttributesKey).getString(TouristAccommodation.mUtmYKey);

                                if(!utm_latitude.isEmpty() && !utm_longitude.isEmpty() &&
                                        !TouristAccommodation.notAccommodation.contains(
                                                currentObject.getJSONObject(TouristAccommodation.mAttributesKey).getInt(TouristAccommodation.mIdKey))){

                                    // Get latitude and longitude
                                    UtmToLatLng latLng = new UtmToLatLng("28 R " + utm_latitude + " " + utm_longitude);

                                    // Save the stop
                                    openDataLaPalma.getmTouristAccommodationList().add(new TouristAccommodation(
                                            currentObject.getJSONObject(TouristAccommodation.mAttributesKey).getInt(TouristAccommodation.mIdKey),
                                            latLng.getmLatitude(),
                                            latLng.getmLongitude(),
                                            currentObject.getJSONObject(TouristAccommodation.mAttributesKey).getString(TouristAccommodation.mNameKey),
                                            currentObject.getJSONObject(TouristAccommodation.mAttributesKey).getString(TouristAccommodation.mPhoneKey)
                                    ));
                                }
                            }

                        } catch (Throwable t) {
                            Log.e(TAG, "Error while parsing the json tourist acommodation.");
                        }

                        GetApplicationDataTask.setTouristAccommodationCallState(true);
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        GetApplicationDataTask.setTouristAccommodationCallState(true);
                    }
                });

        requestQueue.add(stringRequest);
    }

    // Get JSON with the churchs of the island
    public static void getChurchs(){

        StringRequest stringRequest = new StringRequest(com.android.volley.Request.Method.GET, CHURCH_URL,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {

                        // Get instance of singleton class and clear previuos data
                        OpenDataLaPalma openDataLaPalma = OpenDataLaPalma.getInstance();
                        openDataLaPalma.getmChurchList().clear();

                        try {
                            // Convert string to JSON and get item json array
                            JSONObject obj = new JSONObject(response);
                            JSONArray jArray = obj.getJSONArray(Church.mFeaturesKey);

                            // Save items in singleton
                            for (int i=0; i<jArray.length(); i++) {

                                JSONObject currentObject = jArray.getJSONObject(i);

                                String utm_latitude = currentObject.getJSONObject(Church.mAttributesKey).getString(Church.mUtmXKey);
                                String utm_longitude = currentObject.getJSONObject(Church.mAttributesKey).getString(Church.mUtmYKey);

                                if(!utm_latitude.isEmpty() && !utm_longitude.isEmpty()){

                                    // Get latitude and longitude
                                    UtmToLatLng latLng = new UtmToLatLng("28 R " + utm_latitude + " " + utm_longitude);

                                    // Save the church
                                    openDataLaPalma.getmChurchList().add(new Church(
                                            currentObject.getJSONObject(Church.mAttributesKey).getInt(Church.mIdKey),
                                            latLng.getmLatitude(),
                                            latLng.getmLongitude(),
                                            currentObject.getJSONObject(Church.mAttributesKey).getString(Church.mNameKey),
                                            currentObject.getJSONObject(Church.mAttributesKey).getString(Church.mDirectionKey)
                                    ));
                                }
                            }

                        } catch (Throwable t) {
                            Log.e(TAG, "Error while parsing the json church.");
                        }

                        GetApplicationDataTask.setChurchCallState(true);
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        GetApplicationDataTask.setChurchCallState(true);
                    }
                });

        requestQueue.add(stringRequest);
    }

    // Get JSON with the archeological sites of the island
    public static void getArcheologicalSites(){

        StringRequest stringRequest = new StringRequest(com.android.volley.Request.Method.GET, ARCHEOLOGICAL_SITE_URL,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {

                        // Get instance of singleton class and clear previuos data
                        OpenDataLaPalma openDataLaPalma = OpenDataLaPalma.getInstance();
                        openDataLaPalma.getmArcheologicalSiteList().clear();

                        try {
                            // Convert string to JSON and get item json array
                            JSONObject obj = new JSONObject(response);
                            JSONArray jArray = obj.getJSONArray(ArcheologicalSite.mFeaturesKey);

                            // Save items in singleton
                            for (int i=0; i<jArray.length(); i++) {

                                JSONObject currentObject = jArray.getJSONObject(i);

                                String utm_latitude = currentObject.getJSONObject(ArcheologicalSite.mAttributesKey).getString(ArcheologicalSite.mUtmXKey);
                                String utm_longitude = currentObject.getJSONObject(ArcheologicalSite.mAttributesKey).getString(ArcheologicalSite.mUtmYKey);

                                if(!utm_latitude.isEmpty() && !utm_longitude.isEmpty()){

                                    // Get latitude and longitude
                                    UtmToLatLng latLng = new UtmToLatLng("28 R " + utm_latitude + " " + utm_longitude);

                                    // Save the archeological site
                                    openDataLaPalma.getmArcheologicalSiteList().add(new ArcheologicalSite(
                                            currentObject.getJSONObject(ArcheologicalSite.mAttributesKey).getInt(ArcheologicalSite.mIdKey),
                                            latLng.getmLatitude(),
                                            latLng.getmLongitude(),
                                            currentObject.getJSONObject(ArcheologicalSite.mAttributesKey).getString(ArcheologicalSite.mNameKey),
                                            currentObject.getJSONObject(ArcheologicalSite.mAttributesKey).getString(ArcheologicalSite.mDirectionKey)
                                    ));
                                }
                            }

                        } catch (Throwable t) {
                            Log.e(TAG, "Error while parsing the json archeological.");
                        }

                        GetApplicationDataTask.setArcheologicalSiteCallState(true);
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        GetApplicationDataTask.setArcheologicalSiteCallState(true);
                    }
                });

        requestQueue.add(stringRequest);
    }

    // Get JSON with the libraries of the island
    public static void getLibraries(){

        StringRequest stringRequest = new StringRequest(com.android.volley.Request.Method.GET, LIBRARY_URL,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {

                        // Get instance of singleton class and clear previuos data
                        OpenDataLaPalma openDataLaPalma = OpenDataLaPalma.getInstance();
                        openDataLaPalma.getmLibraryList().clear();

                        try {
                            // Convert string to JSON and get item json array
                            JSONObject obj = new JSONObject(response);
                            JSONArray jArray = obj.getJSONArray(Library.mFeaturesKey);

                            // Save items in singleton
                            for (int i=0; i<jArray.length(); i++) {

                                JSONObject currentObject = jArray.getJSONObject(i);

                                String utm_latitude = currentObject.getJSONObject(Library.mAttributesKey).getString(Library.mUtmXKey);
                                String utm_longitude = currentObject.getJSONObject(Library.mAttributesKey).getString(Library.mUtmYKey);

                                if(!utm_latitude.isEmpty() && !utm_longitude.isEmpty()){

                                    // Get latitude and longitude
                                    UtmToLatLng latLng = new UtmToLatLng("28 R " + utm_latitude + " " + utm_longitude);

                                    // Save the library site
                                    openDataLaPalma.getmLibraryList().add(new Library(
                                            currentObject.getJSONObject(Library.mAttributesKey).getInt(Library.mIdKey),
                                            latLng.getmLatitude(),
                                            latLng.getmLongitude(),
                                            currentObject.getJSONObject(Library.mAttributesKey).getString(Library.mNameKey),
                                            currentObject.getJSONObject(Library.mAttributesKey).getString(Library.mDirectionKey)
                                    ));
                                }
                            }

                        } catch (Throwable t) {
                            Log.e(TAG, "Error while parsing the json library.");
                        }

                        GetApplicationDataTask.setLibraryCallState(true);
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        GetApplicationDataTask.setLibraryCallState(true);
                    }
                });

        requestQueue.add(stringRequest);
    }

    // Get JSON with the monuments of the island
    public static void getMonuments(){

        StringRequest stringRequest = new StringRequest(com.android.volley.Request.Method.GET, MONUMENT_URL,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {

                        // Get instance of singleton class and clear previuos data
                        OpenDataLaPalma openDataLaPalma = OpenDataLaPalma.getInstance();
                        openDataLaPalma.getmMonumentList().clear();

                        try {
                            // Convert string to JSON and get item json array
                            JSONObject obj = new JSONObject(response);
                            JSONArray jArray = obj.getJSONArray(Monument.mFeaturesKey);

                            // Save items in singleton
                            for (int i=0; i<jArray.length(); i++) {

                                JSONObject currentObject = jArray.getJSONObject(i);

                                String utm_latitude = currentObject.getJSONObject(Monument.mAttributesKey).getString(Monument.mUtmXKey);
                                String utm_longitude = currentObject.getJSONObject(Monument.mAttributesKey).getString(Monument.mUtmYKey);

                                if(!utm_latitude.isEmpty() && !utm_longitude.isEmpty()){

                                    // Get latitude and longitude
                                    UtmToLatLng latLng = new UtmToLatLng("28 R " + utm_latitude + " " + utm_longitude);

                                    // Save the monument site
                                    openDataLaPalma.getmMonumentList().add(new Monument(
                                            currentObject.getJSONObject(Monument.mAttributesKey).getInt(Monument.mIdKey),
                                            latLng.getmLatitude(),
                                            latLng.getmLongitude(),
                                            currentObject.getJSONObject(Monument.mAttributesKey).getString(Monument.mNameKey),
                                            currentObject.getJSONObject(Monument.mAttributesKey).getString(Monument.mDirectionKey)
                                    ));
                                }
                            }

                        } catch (Throwable t) {
                            Log.e(TAG, "Error while parsing the json monumental.");
                        }

                        GetApplicationDataTask.setMonumentCallState(true);
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        GetApplicationDataTask.setMonumentCallState(true);
                    }
                });

        requestQueue.add(stringRequest);
    }

    // Get JSON with the weather information
    public static void getWeatherInfo(final Context context){

        StringRequest stringRequest = new StringRequest(com.android.volley.Request.Method.GET, WEATHER_API,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {

                        try {
                            // Get url with data
                            JSONObject obj = new JSONObject(response);
                            String url = obj.get(Weather.dataKey).toString();

                            // Send request for wheather information
                            StringRequest stringRequest = new StringRequest(com.android.volley.Request.Method.GET, url,
                                    new Response.Listener<String>() {
                                        @Override
                                        public void onResponse(String response) {
                                            try {
                                                // Get instance of singleton class and clear previuos data
                                                OpenDataLaPalma openDataLaPalma = OpenDataLaPalma.getInstance();
                                                openDataLaPalma.setmWeather(new Weather());

                                                // Save wheather information
                                                Weather weather = new Weather();

                                                int j = 0;

                                                JSONArray obj = new JSONArray(response);
                                                JSONArray jArray = obj.getJSONObject(0).getJSONObject(Weather.predictionKey).getJSONArray(Weather.dayKey);

                                                // Save prediction for each day
                                                for (int i=0; i<jArray.length(); i++) {

                                                    JSONObject currentObject = jArray.getJSONObject(i);

                                                    // Object where the day weather is saved
                                                    DayWeather day = new DayWeather();

                                                    // Get precipitation probability
                                                    Precipitation precipitation = new Precipitation();

                                                    JSONArray precipitationArray = currentObject.getJSONArray(Precipitation.probPrecipitationKey);

                                                    // Get first value
                                                    while(precipitationArray.getJSONObject(j).get(
                                                            Precipitation.probPrecipitationValueKey).toString().isEmpty()){
                                                        j++;
                                                    }

                                                    // Save precipitation value
                                                    precipitation.setmPrecipitationValue(precipitationArray.getJSONObject(j).get(
                                                            Precipitation.probPrecipitationValueKey).toString());

                                                    // Get skyState
                                                    SkyState skyState = new SkyState();

                                                    JSONArray skyStateArray = currentObject.getJSONArray(SkyState.skyStateKey);

                                                    // Get first value
                                                    j = 0;
                                                    while(skyStateArray.getJSONObject(j).get(
                                                            SkyState.valueKey).toString().isEmpty()){
                                                        j++;
                                                    }

                                                    // Save sky state values
                                                    skyState.setmValue(skyStateArray.getJSONObject(j).get(
                                                            SkyState.valueKey).toString());
                                                    skyState.setmDescription(CustomUtils.getSkyStateDescriptionFromInteger(
                                                            context,
                                                            skyStateArray.getJSONObject(j).get(
                                                                    SkyState.valueKey).toString()
                                                    ));

                                                    // Get wind
                                                    Wind wind = new Wind();

                                                    JSONArray windArray = currentObject.getJSONArray(Wind.windKey);

                                                    // Get first value
                                                    j = 0;
                                                    while(windArray.getJSONObject(j).get(
                                                            Wind.directionKey).toString().isEmpty()){
                                                        j++;
                                                    }

                                                    // Save wind values
                                                    wind.setmDirection(windArray.getJSONObject(j).get(
                                                            Wind.directionKey).toString());
                                                    wind.setmVelocity(windArray.getJSONObject(j).get(
                                                            Wind.velocityKey).toString());

                                                    // Get temperature
                                                    Temperature temperature = new Temperature();

                                                    JSONObject temperatureObject = currentObject.getJSONObject(Temperature.temperatureKey);

                                                    // Save temperature values
                                                    temperature.setmMax(temperatureObject.get(
                                                            Temperature.maxKey).toString());
                                                    temperature.setmMin(temperatureObject.get(
                                                            Temperature.minKey).toString());

                                                    // Get thermal sensation
                                                    ThermalSensation thermalSensation = new ThermalSensation();

                                                    JSONObject thermalSensationObject = currentObject.getJSONObject(ThermalSensation.sensationThermalKey);

                                                    // Save thermal sensation values
                                                    thermalSensation.setmMax(thermalSensationObject.get(
                                                            ThermalSensation.maxKey).toString());
                                                    thermalSensation.setmMin(thermalSensationObject.get(
                                                            ThermalSensation.minKey).toString());

                                                    // Get humidity
                                                    Humidity humidity = new Humidity();

                                                    JSONObject humidityObject = currentObject.getJSONObject(Humidity.humidityKey);

                                                    // Save humidity values
                                                    humidity.setmMax(humidityObject.get(
                                                            Humidity.maxKey).toString());
                                                    humidity.setmMin(humidityObject.get(
                                                            Humidity.minKey).toString());

                                                    // Get UV
                                                    UV uv = new UV();

                                                    if(currentObject.has(UV.uVKey)){
                                                        uv.setmUV(currentObject.get(UV.uVKey).toString());
                                                    } else {
                                                        uv.setmUV("");
                                                    }

                                                    // Save day prediction
                                                    day.setmPrecipitation(precipitation);
                                                    day.setmSkyState(skyState);
                                                    day.setmWind(wind);
                                                    day.setmTemperature(temperature);
                                                    day.setmThermalSensation(thermalSensation);
                                                    day.setmHumidity(humidity);
                                                    day.setmUV(uv);
                                                    day.setmDate(currentObject.get(DayWeather.dateKey).toString());

                                                    // Add day prediction to weather information
                                                    weather.getmDayWeatherList().add(day);
                                                }

                                                // Save weather information
                                                openDataLaPalma.setmWeather(weather);

                                                GetApplicationDataTask.setWeatherCallState(true);

                                            } catch (JSONException e) {
                                                GetApplicationDataTask.setWeatherCallState(true);
                                                e.printStackTrace();
                                            }
                                        }
                                    },
                                    new Response.ErrorListener() {
                                        @Override
                                        public void onErrorResponse(VolleyError error) {
                                            GetApplicationDataTask.setWeatherCallState(true);
                                        }
                                    });

                            requestQueue.add(stringRequest);
                        } catch (JSONException e) {
                            GetApplicationDataTask.setWeatherCallState(true);
                            e.printStackTrace();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        GetApplicationDataTask.setWeatherCallState(true);
                    }
                });

        requestQueue.add(stringRequest);
    }
}
