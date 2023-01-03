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


public class SixthFragment extends Fragment {

    Fragment fragment;

    TextView textview_sixth_header_routes;
    TextView textview_sixth_header_result;

    DownloadManager downloadManager;
    String routesListUrl;
    JsonParser jsonParser;
    String routesJsonData;

    ArrayList<RouteData> routeDatas;
    RouteData routeData;
    RouteFormatter routeFormatter;
    
    ArrayList<String> items;
    int result;
    LinearLayout linearlayout_sixth;
    TextView textview_sixth_for_append;

    @Override
    public View onCreateView(
            LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState
    ) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_sixth, container, false);
    }

    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        fragment = getParentFragment();

        textview_sixth_header_routes = (TextView) view.findViewById(R.id.textview_sixth_header_routes);
        textview_sixth_header_routes.setText(R.string.text_sixth_header_routes);

        routesListUrl = getString(R.string.text_sixth_routes_list_url);

        downloadManager = new DownloadManager();
        try {
            routesJsonData = downloadManager.execute(routesListUrl).get();
        } catch (InterruptedException | ExecutionException exc) {
            // nothing to do
        }
        items = new ArrayList<String>();
        jsonParser = new JsonParser(routesJsonData);
        routeDatas = jsonParser.parseToRouteDataList();

        routeFormatter = new RouteFormatter();
        items.clear();
        for (int i = 0; i < routeDatas.size(); i++) {
            items.add(routeFormatter.format(routeDatas.get(i)));
        }
        result = items.size();

        textview_sixth_header_result = (TextView) view.findViewById(R.id.textview_sixth_header_result);
        textview_sixth_header_result.setText(getString(R.string.text_sixth_header_result_fmt, result));

        linearlayout_sixth = (LinearLayout) view.findViewById(R.id.linearlayout_sixth);

        for (int i = 0; i < items.size(); i++) {
            textview_sixth_for_append = new TextView(fragment.getContext());
            textview_sixth_for_append.setText(getString(R.string.text_sixth_item_name_fmt, items.get(i)));
            linearlayout_sixth.addView(textview_sixth_for_append);
        }

        view.findViewById(R.id.button_sixth_previous).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                NavHostFragment.findNavController(SixthFragment.this)
                        .navigate(R.id.action_SixthFragment_to_FifthFragment);
            }
        });
    }
}
