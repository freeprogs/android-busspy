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

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;

import android.widget.TextView;
import android.widget.LinearLayout;
import java.util.ArrayList;
import java.util.concurrent.ExecutionException;


public class ThirdFragment extends Fragment {

    Fragment fragment;
    Bundle third_bundle;
    TextView textview_third_header_route;
    TextView textview_third_header_result;
    String routeNumber;

    DownloadManager downloadManager;
    String busRouteUrl;
    JsonParser jsonParser;
    String busRouteJsonData;

    ArrayList<BusData> busDatas;
    BusData busData;
    RoutePoints routePoints;
    ArrayList<RoutePoint> routePointsRoute;
    BusWithRouteRelat busWithRouteRelat;
    ArrayList<BusWithRouteRelat> busWithRouteRelats;
    BusWithRouteFormatter busWithRouteFormatter;
    
    ArrayList<String> items;
    int result;
    LinearLayout linearlayout_third;
    TextView textview_third_for_append;

    @Override
    public View onCreateView(
            LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState
    ) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_third, container, false);
    }

    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        routeNumber = null;

        fragment = getParentFragment();
        if (fragment != null) {
            third_bundle = fragment.getArguments();
            if (third_bundle != null) {
                routeNumber = third_bundle.getString("route_number");
            }
        }

        if (routeNumber != null) {
            textview_third_header_route = (TextView) view.findViewById(R.id.textview_third_header_route);
            textview_third_header_route.setText(getString(R.string.text_third_header_route_fmt, routeNumber));

            busRouteUrl = getString(R.string.text_third_bus_route_url_fmt, routeNumber);

            downloadManager = new DownloadManager();
            try {
                busRouteJsonData = downloadManager.execute(busRouteUrl).get();
            } catch (InterruptedException | ExecutionException exc) {
                // nothing to do
            }
            items = new ArrayList<String>();
            jsonParser = new JsonParser(busRouteJsonData);
            busDatas = jsonParser.parseToBusDataList();
            routePoints = new RoutePoints();
            routePointsRoute = routePoints.getRoutePoints(routeNumber);
            busWithRouteRelats = new ArrayList<>();
            for (int i = 0; i < busDatas.size(); i++) {
                busData = busDatas.get(i);
                busWithRouteRelat = new BusWithRouteRelat();
                busWithRouteRelat.initFromBusDataAndRoutePoints(busData, routePointsRoute);
                busWithRouteRelats.add(busWithRouteRelat);
            }
            busWithRouteFormatter = new BusWithRouteFormatter();
            items.clear();
            for (int i = 0; i < busWithRouteRelats.size(); i++) {
                items.add(busWithRouteFormatter.format(busWithRouteRelats.get(i)));
            }
            result = items.size();

            textview_third_header_result = (TextView) view.findViewById(R.id.textview_third_header_result);
            textview_third_header_result.setText(getString(R.string.text_third_header_result_fmt, result));
        }

        linearlayout_third = (LinearLayout) view.findViewById(R.id.linearlayout_third);

        for (int i = 0; i < items.size(); i++) {
            textview_third_for_append = new TextView(fragment.getContext());
            textview_third_for_append.setText(getString(R.string.text_third_item_index_name_fmt, i + 1, items.get(i)));

            final String itemName = items.get(i);
            textview_third_for_append.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        third_bundle.putString("selected_bus", getString(R.string.text_third_bundle_name_fmt, itemName));
                        NavHostFragment.findNavController(ThirdFragment.this)
                            .navigate(R.id.action_ThirdFragment_to_FourthFragment);
                    }
                }
            );
            linearlayout_third.addView(textview_third_for_append);
        }

        view.findViewById(R.id.button_third_previous).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                NavHostFragment.findNavController(ThirdFragment.this)
                        .navigate(R.id.action_ThirdFragment_to_SecondFragment);
            }
        });
        view.findViewById(R.id.button_third_update).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                NavHostFragment.findNavController(ThirdFragment.this)
                        .navigate(R.id.action_ThirdFragment_to_ThirdFragment);
                // nothing to do
            }
        });
    }
}
