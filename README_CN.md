[English](https://github.com/lintianlin/MonthPicker) | **中文版** </br>
QuantityControlView(kotlin)
==========
    一个数量控制控件，一般用于商城类app加减或修改商品数量

  
截图
===========

 
<div align=center><img width="216" height="384" src="https://github.com/lintianlin/QuantityControlView/blob/master/Gif/quantityControlView.gif"/>
<img width="216" height="384" src="https://github.com/lintianlin/QuantityControlView/blob/master/Gif/quantityControlView2.gif"/>
</div>


## Gradle 
		allprojects {
    		repositories {
        		maven { url 'https://jitpack.io' }
				...
    		}
		}
###
		dependencies{
			compile 'com.github.lintianlin:QuantityControlView_kt:v1.0.0'
		 }
 
      
## 用法
      <?xml version="1.0" encoding="utf-8"?>
	<LinearLayout xmlns:android="http://schemas.android.com/apk/res/android"
    	android:layout_width="match_parent"
    	android:layout_height="match_parent">

    	<com.sinfeeloo.library.QuantityControlView
	        android:id="@+id/quantity_control_view"
	        android:layout_width="wrap_content"
	        android:layout_height="wrap_content"
	        android:layout_alignParentEnd="true"
	        android:layout_centerVertical="true"
	        android:layout_marginEnd="20dp"
	        app:qc_btnHeight="30dp"
	        app:qc_btnWidth="30dp"
	        app:qc_editable="false"
	        app:qc_tvWidth="80dp" />

	</LinearLayout>

###
	QuantityControlView quantityControlView = view.findViewById(R.id.quantity_control_view);
		quantityControlView.setAmount(item.getCount());
        quantityControlView.setMaxAmount(5);
        quantityControlView.setOnAmountButtonListener(new OnAmountButtonListener() {

            @Override
            public void onAmountChange(int type, int count) {
                if (Constant.DECREASE == type) {

                } else if (Constant.INCREASE == type) {

                } else {

                }
            }

        });
  
    

 
 # License
    Copyright 2018 SinFeeLoo

    Licensed under the Apache License, Version 2.0 (the "License");
    you may not use this file except in compliance with the License.
    You may obtain a copy of the License at

      http://www.apache.org/licenses/LICENSE-2.0

    Unless required by applicable law or agreed to in writing, software
    distributed under the License is distributed on an "AS IS" BASIS,
    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
    See the License for the specific language governing permissions and
    limitations under the License.
