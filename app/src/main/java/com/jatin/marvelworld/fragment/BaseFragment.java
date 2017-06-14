package com.jatin.marvelworld.fragment;


import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.jatin.marvelworld.R;

/**
 * Created by jatinjha on 14/06/17.
 */

public class BaseFragment extends Fragment {

    void showFavouriteHeroDialog() {

        ImageView image = new ImageView(getActivity());
        image.setImageResource(R.drawable.deadpool);
        image.setLayoutParams(new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.WRAP_CONTENT,
                LinearLayout.LayoutParams.WRAP_CONTENT));

        AlertDialog.Builder alertDialog = new AlertDialog.Builder(getActivity());
        alertDialog.setTitle("DeadPool");
        alertDialog.setIcon(R.drawable.ic_favorite_red);
        alertDialog.setMessage(getResources().getString(R.string.dealpool_description));
        alertDialog.setView(image);
        alertDialog.create().show();
    }


    public String MD5(String md5) {
        try {
            java.security.MessageDigest md = java.security.MessageDigest.getInstance("MD5");
            byte[] array = md.digest(md5.getBytes());
            StringBuffer sb = new StringBuffer();
            for (int i = 0; i < array.length; ++i) {
                sb.append(Integer.toHexString((array[i] & 0xFF) | 0x100).substring(1, 3));
            }
            return sb.toString();
        } catch (java.security.NoSuchAlgorithmException e) {
            Toast.makeText(getActivity(), e.toString(), Toast.LENGTH_SHORT).show();
        }
        return null;
    }


    public boolean hasData(String text) {
        return !(text == null || text.length() == 0);
    }

}
