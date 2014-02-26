/*
 *
 *    Licensed under the Apache License, Version 2.0 (the "License");
 *    you may not use this file except in compliance with the License.
 *    You may obtain a copy of the License at
 *
 *        http://www.apache.org/licenses/LICENSE-2.0
 *
 *    Unless required by applicable law or agreed to in writing, software
 *    distributed under the License is distributed on an "AS IS" BASIS,
 *    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *    See the License for the specific language governing permissions and
 *    limitations under the License.
 */

package com.ali.doctortipss;

import java.util.ArrayList;

import android.app.ActionBar.LayoutParams;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.ali.doctortipss.fancycoverflow.FancyCoverFlow;
import com.ali.doctortipss.fancycoverflow.FancyCoverFlowAdapter;
import com.nostra13.universalimageloader.core.ImageLoader;

public class FancyCoverFlowSampleAdapter extends FancyCoverFlowAdapter {

    // =============================================================================
    // Private members
    // =============================================================================
	ArrayList<String> _data;
//    private int[] images = {R.drawable.image1, R.drawable.image2, R.drawable.image3, R.drawable.image4, R.drawable.image5, R.drawable.image6,};

    // =============================================================================
    // Supertype overrides
    // =============================================================================

    public FancyCoverFlowSampleAdapter(TipsDetails tipsDetails,
			ArrayList<String> image) {
    	_data = image;
	}

	@Override
    public int getCount() {
        return _data.size();
    }

    @Override
    public Object getItem(int i) {
        return _data.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getCoverFlowItem(int i, View reuseableView, ViewGroup viewGroup) {
        ImageView imageView = null;
        Log.d("", "testing getCoverFlowItem"+i);
        if (reuseableView != null) {
            imageView = (ImageView) reuseableView;
            Log.d("", "testing getCoverFlowItem"+i);
        } else {
            imageView = new ImageView(viewGroup.getContext());
            imageView.setScaleType(ImageView.ScaleType.FIT_CENTER);
            imageView.setLayoutParams(new FancyCoverFlow.LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT));
        }
        String imageUrl = MySingleton.getInstance().storageUrl+"images/"+_data.get(i);
        ImageLoader.getInstance().displayImage(imageUrl, imageView);
//        imageView.setImageResource(this.getItem(i));
        return imageView;
    }
}
