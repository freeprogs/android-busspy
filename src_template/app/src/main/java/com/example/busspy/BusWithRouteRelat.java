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

import java.util.ArrayList;

class BusWithRouteRelat {
    public String busModel;
    public String busStateNum;
    public String busGarageNum;
    public String busLat;
    public String busLon;
    public String busCourse;
    public String busCourseInHours;
    public ArrayList<RoutePoint> routePoints;
    public final ArrayList<String> routePointsDistances;
    public final ArrayList<String> routePointsPositions;

    BusWithRouteRelat() {
        this.routePointsDistances = new ArrayList<>();
        this.routePointsPositions = new ArrayList<>();
    }

    void initFromBusDataAndRoutePoints(BusData busData, ArrayList<RoutePoint> routePoints) {
        String distance;
        String position;

        this.busModel = busData.model;
        this.busStateNum = busData.stateNum;
        this.busGarageNum = busData.garageNum;
        this.busLat = busData.lat;
        this.busLon = busData.lon;
        this.busCourse = busData.course;
        this.busCourseInHours = getHoursFromDegrees(busData.course);
        this.routePoints = routePoints;
        this.routePointsDistances.clear();
        this.routePointsPositions.clear();
        for (int i = 0; i < this.routePoints.size(); i++) {
            distance = getGeoDistance(
                this.busLat,
                this.busLon,
                this.routePoints.get(i).pointLat,
                this.routePoints.get(i).pointLon);
            this.routePointsDistances.add(distance);
            position = getGeoRelativePosition(
                this.busLat,
                this.busLon,
                this.routePoints.get(i).pointLat,
                this.routePoints.get(i).pointLon);
            this.routePointsPositions.add(position);
        }
    }

    String getHoursFromDegrees(String degrees) {
        int valueDegrees = Integer.parseInt(degrees);
        int valueHours;
        String out;

        valueHours = 0;
        if ((valueDegrees >= 345 && valueDegrees <= 360)
            || (valueDegrees >= 0 && valueDegrees < 15)) {
            valueHours = 12;
        }
        else if (valueDegrees >= 15 && valueDegrees < 45) {
            valueHours = 1;
        }
        else if (valueDegrees >= 45 && valueDegrees < 75) {
            valueHours = 2;
        }
        else if (valueDegrees >= 75 && valueDegrees < 105) {
            valueHours = 3;
        }
        else if (valueDegrees >= 105 && valueDegrees < 135) {
            valueHours = 4;
        }
        else if (valueDegrees >= 135 && valueDegrees < 165) {
            valueHours = 5;
        }
        else if (valueDegrees >= 165 && valueDegrees < 195) {
            valueHours = 6;
        }
        else if (valueDegrees >= 195 && valueDegrees < 225) {
            valueHours = 7;
        }
        else if (valueDegrees >= 225 && valueDegrees < 255) {
            valueHours = 8;
        }
        else if (valueDegrees >= 255 && valueDegrees < 285) {
            valueHours = 9;
        }
        else if (valueDegrees >= 285 && valueDegrees < 315) {
            valueHours = 10;
        }
        else if (valueDegrees >= 315 && valueDegrees < 345) {
            valueHours = 11;
        }
        out = String.valueOf(valueHours);
        return out;
    }

    String getGeoDistance(String latY1, String lonX1, String latY2, String lonX2) {
        double x1 = Double.parseDouble(lonX1);
        double y1 = Double.parseDouble(latY1);
        double x2 = Double.parseDouble(lonX2);
        double y2 = Double.parseDouble(latY2);
        double res;
        String out;

        res = Math.sqrt(Math.pow(x2 - x1, 2) + Math.pow(y2 - y1, 2));
        res = Math.round(res * 10000);
        out = String.valueOf(res).replaceFirst("\\.0$", "");
        return out;
    }

    String getGeoRelativePosition(String latY1, String lonX1, String latY2, String lonX2) {
        double x1 = Double.parseDouble(lonX1);
        double y1 = Double.parseDouble(latY1);
        double x2 = Double.parseDouble(lonX2);
        double y2 = Double.parseDouble(latY2);
        String out;

        out = "";
        if (x1 <= x2) {
            out += "левее";
        }
        else {
            out += "правее";
        }
        out += " и ";
        if (y1 <= y2) {
            out += "ниже";
        }
        else {
            out += "выше";
        }
        return out;
    }
}
