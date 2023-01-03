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

class BusData {
    public String model;
    public String stateNum;
    public String garageNum;
    public String lat;
    public String lon;
    public String course;

    BusData(String model, String stateNum, String garageNum, String lat, String lon, String course) {
        this.model = model;
        this.stateNum = stateNum;
        this.garageNum = garageNum;
        this.lat = lat;
        this.lon = lon;
        this.course = course;
    }
}
