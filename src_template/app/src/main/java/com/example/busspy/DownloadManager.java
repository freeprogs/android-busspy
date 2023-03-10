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

import android.os.AsyncTask;

import java.net.URL;
import java.net.MalformedURLException;
import java.net.HttpURLConnection;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

class DownloadManager extends AsyncTask<String, Void, String> {
    @Override
    protected String doInBackground(String... WebAddress) {
        URL url;
        HttpURLConnection conn;
        InputStream is;
        BufferedReader br;
        String line;
        String out;
        
        is = null;
        br = null;
        conn = null;
        out = "";
        try {
            url = new URL(WebAddress[0]);
            conn = (HttpURLConnection) url.openConnection();
            conn.setRequestProperty("User-Agent", "Firefox");
            is = conn.getInputStream();
            br = new BufferedReader(new InputStreamReader(is, "UTF-8"));
            while ((line = br.readLine()) != null) {
                out += line;
            }
        } catch (MalformedURLException mue) {
            mue.printStackTrace();
        } catch (IOException ioe) {
            ioe.printStackTrace();
        } finally {
            try {
                if (is != null)
                    is.close();
                if (br != null)
                    br.close();
                if (conn != null)
                    conn.disconnect();
            } catch (IOException ioe) {
                // nothing to see here
            }
        }
        return out;
    }
}
