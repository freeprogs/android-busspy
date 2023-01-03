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


public class FourthFragment extends Fragment {

    Bundle fourth_bundle;

    @Override
    public View onCreateView(
            LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState
    ) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_fourth, container, false);
    }

    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        Fragment fragment = getParentFragment();
        if (fragment != null) {
            fourth_bundle = fragment.getArguments();
            if (fourth_bundle != null) {
                TextView textview_fourth = (TextView) view.findViewById(R.id.textview_fourth);
                String selectedBus = fourth_bundle.getString("selected_bus");
                textview_fourth.setText(getString(R.string.text_fourth_header_fmt, selectedBus));
            }
        }

        view.findViewById(R.id.button_fourth_previous).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                NavHostFragment.findNavController(FourthFragment.this)
                        .navigate(R.id.action_FourthFragment_to_ThirdFragment);
            }
        });
    }
}
