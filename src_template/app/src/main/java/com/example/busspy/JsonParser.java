/**
 * __PROGRAM_COPYRIGHT__ __PROGRAM_AUTHOR__ __PROGRAM_AUTHOR_EMAIL__
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */

package com.example.busspy;

import org.json.JSONObject;
import org.json.JSONArray;
import org.json.JSONException;

import java.util.ArrayList;

class JsonParser {

    private String str;

    JsonParser(String jsonStr) {
        str = jsonStr;
    }

    ArrayList<BusData> parseToBusDataList() {
        JSONObject doc;
        JSONArray arrStates;
        String model;
        String stateNum;
        String garageNum;
        String lat;
        String lon;
        String course;
        ArrayList<BusData> out;

        out = new ArrayList<>();
        try {
            doc = new JSONObject(str);
            arrStates = doc.getJSONArray("states");
            for (int i = 0; i < arrStates.length(); i++) {
                model = arrStates.getJSONObject(i).getString("model");
                stateNum = arrStates.getJSONObject(i).getString("stateNum");
                garageNum = arrStates.getJSONObject(i).getString("garageNum");
                lat = String.valueOf(arrStates.getJSONObject(i).getDouble("lat"));
                lon = String.valueOf(arrStates.getJSONObject(i).getDouble("lon"));
                course = String.valueOf(arrStates.getJSONObject(i).getInt("course"));
                out.add(new BusData(model, stateNum, garageNum, lat, lon, course));
            }
        } catch (JSONException exc) {
            // System.out.println(exc);
            // nothing to do
        }
        return out;
    }

    ArrayList<RouteData> parseToRouteDataList() {
        JSONArray doc;
        String routeName;
        ArrayList<RouteData> out;
    
        out = new ArrayList<>();
        try {
            doc = new JSONArray(str);
            for (int i = 0; i < doc.length(); i++) {
                routeName = doc.getJSONObject(i).getString("name");
                out.add(new RouteData(routeName));
            }
        } catch (JSONException exc) {
            // System.out.println(exc);
            // nothing to do
        }
        return out;
    }
}
