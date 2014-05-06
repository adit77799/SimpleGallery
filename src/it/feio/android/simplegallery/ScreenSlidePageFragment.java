/*
 * Copyright 2012 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package it.feio.android.simplegallery;

import it.feio.android.simplegallery.async.ImageLoadTask;
import it.feio.android.simplegallery.util.Display;
import it.feio.android.simplegallery.views.TouchImageView;
import android.annotation.SuppressLint;
import android.graphics.Point;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

/**
 * A fragment representing a single step in a wizard. The fragment shows a dummy title indicating
 * the page number, along with some dummy text.
 *
 * <p>This class is used by the {@link CardFlipActivity} and {@link
 * ScreenSlideActivity} samples.</p>
 */
public class ScreenSlidePageFragment extends Fragment {
    /**
     * The argument key for the page number this fragment represents.
     */
    public static final String ARG_PAGE = "page";
    /**
     * The argument key for the image path that must be loaded.
     */
    public static final String ARG_PATH = "path";

    /**
     * The fragment's page number, which is set to the argument value for {@link #ARG_PAGE}.
     */
    private int mPageNumber;

    /**
     * The fragment's image path, which is set to the argument value for {@link #ARG_PATH}.
     */
    private String mImagePath;

    /**
     * Factory method for this fragment class. Constructs a new fragment for the given page number.
     * @param imagePath 
     */
    public static ScreenSlidePageFragment create(int pageNumber, String imagePath) {
        ScreenSlidePageFragment fragment = new ScreenSlidePageFragment();
        Bundle args = new Bundle();
        args.putInt(ARG_PAGE, pageNumber);
        args.putString(ARG_PATH, imagePath);
        fragment.setArguments(args);
        return fragment;
    }

    public ScreenSlidePageFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mPageNumber = getArguments().getInt(ARG_PAGE);
        mImagePath = getArguments().getString(ARG_PATH);
    }

    @Override
    @SuppressLint("NewApi")
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {
    	
        // Inflate the layout containing a title and body text.
//        ViewGroup rootView = (ViewGroup) inflater
//                .inflate(R.layout.fragment_screen_slide_page, container, false);
        
        TouchImageView rootView = new TouchImageView(getActivity());
//        rootView.setImageResource(R.drawable.ic_launcher);
        Point dimensions = Display.getUsableSize(getActivity());
		if (Build.VERSION.SDK_INT >= 11) {
			new ImageLoadTask(getActivity(), rootView, dimensions.x, dimensions.y).executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, mImagePath);
		} else {
			new ImageLoadTask(getActivity(), rootView, dimensions.x, dimensions.y).execute(mImagePath);
		}
//        rootView.se
//        android:id="@+id/image"
//        android:layout_width="match_parent"
//        android:layout_height="match_parent"
//        android:contentDescription="@string/image"

        // Set the title view to show the page number.
//        ((TextView) rootView.findViewById(android.R.id.text1)).setText(
//                getString(R.string.title_template_step, mPageNumber + 1));

        return rootView;
    }

    /**
     * Returns the page number represented by this fragment object.
     */
    public int getPageNumber() {
        return mPageNumber;
    }
}