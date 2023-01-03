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

class BusWithRouteFormatter {

    String format(BusWithRouteRelat busWithRouteRelat) {
        String out;
        out = String.format(
            ""
            + "Модель: %s%n"
            + "Госномер: %s%n"
            + "Гаражный номер: %s%n"
            + "Направление часы: %s%n",
            busWithRouteRelat.busModel,
            busWithRouteRelat.busStateNum,
            busWithRouteRelat.busGarageNum,
            busWithRouteRelat.busCourseInHours
        );
        out += String.format("Точки:%n");
        for (int i = 0; i < busWithRouteRelat.routePoints.size(); i++) {
            out += String.format(
                ""
                + "  %s%n"
                + "    %s %s%n",
                busWithRouteRelat.routePoints.get(i).pointName,
                busWithRouteRelat.routePointsDistances.get(i),
                busWithRouteRelat.routePointsPositions.get(i)
            );
        }
        return out;
    }
}
