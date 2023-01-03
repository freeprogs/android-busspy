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
import android.widget.EditText;


public class SecondFragment extends Fragment {

    TextView text_second;
    EditText input_second;
    String defaultRouteNumber;
    String routeNumber;
    Bundle second_bundle;

    @Override
    public View onCreateView(
            LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState
    ) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_second, container, false);
    }

    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        defaultRouteNumber = "11";

        input_second = (EditText) view.findViewById(R.id.textinput_second);

        text_second = (TextView) view.findViewById(R.id.textview_second);
        text_second.setText(getString(R.string.text_second_fmt, defaultRouteNumber));

        second_bundle = new Bundle();
        Fragment fragment = getParentFragment();
        if (fragment != null) {
            getParentFragment().setArguments(second_bundle);
        }
        
        view.findViewById(R.id.button_second_info).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                NavHostFragment.findNavController(SecondFragment.this)
                        .navigate(R.id.action_SecondFragment_to_FifthFragment);
            }
        });
        view.findViewById(R.id.button_second_find).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                routeNumber = input_second.getText().toString().trim();
                if (routeNumber.isEmpty()) {
                    routeNumber = defaultRouteNumber;
                }
                second_bundle.putString("route_number", routeNumber);
                
                NavHostFragment.findNavController(SecondFragment.this)
                        .navigate(R.id.action_SecondFragment_to_ThirdFragment);
            }
        });
    }
}
