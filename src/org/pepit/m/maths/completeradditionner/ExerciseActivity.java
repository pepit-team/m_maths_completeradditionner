/**
 * @file org/pepit/m/maths/completeradditionner/ExerciseActivity.java
 * 
 * PepitModel: an educational software
 * This file is a part of the PepitModel environment
 * http://pepit.be
 *
 * Copyright (C) 2012-2013 Pepit Team
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

package org.pepit.m.maths.completeradditionner;

import android.os.Bundle;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.graphics.Color;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

public class ExerciseActivity extends Activity implements OnClickListener {

    @Override
    public void onCreate(Bundle savedInstanceState) {
	super.onCreate(savedInstanceState);

	LinearLayout rootLayout = new LinearLayout(this);
	LinearLayout.LayoutParams rootLayoutParams = new LinearLayout.LayoutParams(
		LinearLayout.LayoutParams.MATCH_PARENT,
		LinearLayout.LayoutParams.MATCH_PARENT);

	rootLayout.setOrientation(LinearLayout.VERTICAL);

	LinearLayout topLayout = new LinearLayout(this);
	LinearLayout.LayoutParams topLayoutParams = new LinearLayout.LayoutParams(
		LinearLayout.LayoutParams.MATCH_PARENT,
		LinearLayout.LayoutParams.WRAP_CONTENT, 1);

	topLayout.setOrientation(LinearLayout.HORIZONTAL);
	for (int i = 0; i < 6; ++i) {
	    buildNumberLayout(i + 1, topLayout, true);
	}

	LinearLayout bottomLayout = new LinearLayout(this);
	LinearLayout.LayoutParams bottomLayoutParams = new LinearLayout.LayoutParams(
		LinearLayout.LayoutParams.MATCH_PARENT,
		LinearLayout.LayoutParams.WRAP_CONTENT, 1);

	bottomLayout.setOrientation(LinearLayout.HORIZONTAL);
	for (int i = 0; i < 6; ++i) {
	    buildNumberLayout(i + 7, bottomLayout, false);
	}

	LinearLayout middleLayout = new LinearLayout(this);
	LinearLayout.LayoutParams middleLayoutParams = new LinearLayout.LayoutParams(
		LinearLayout.LayoutParams.WRAP_CONTENT, 0, 10);
	
	additionView = new AdditionView(this);
	middleLayout.addView(additionView);

	rootLayout.addView(topLayout, topLayoutParams);
	rootLayout.addView(middleLayout, middleLayoutParams);
	rootLayout.addView(bottomLayout, bottomLayoutParams);

	setContentView(rootLayout, rootLayoutParams);
    }

    @SuppressLint("UseValueOf")
    private void buildNumberLayout(int i, LinearLayout layout, boolean below) {
	TextView t = new TextView(this);
	Button b = new Button(this);
	LinearLayout numberLayout = new LinearLayout(this);
	LinearLayout.LayoutParams numberLayoutParams = new LinearLayout.LayoutParams(
		LinearLayout.LayoutParams.MATCH_PARENT,
		LinearLayout.LayoutParams.WRAP_CONTENT, 1);

	numberLayout.setOrientation(LinearLayout.VERTICAL);
	b.setText(Integer.toString(i));
	b.setGravity(Gravity.CENTER_HORIZONTAL | Gravity.CENTER_VERTICAL);
	b.setShadowLayer(5, 2, 2, Color.GRAY);
	b.setTextSize(30);
	b.setOnClickListener(this);
	t.setText(numbers[i]);
	t.setGravity(Gravity.CENTER_HORIZONTAL);
	t.setTextSize(30);
	if (below) {
	    numberLayout.addView(b);
	    numberLayout.addView(t);
	} else {
	    numberLayout.addView(t);
	    numberLayout.addView(b);
	}
	layout.addView(numberLayout, numberLayoutParams);
    }

    private static String[] numbers = { "Zero", "Un", "Deux", "Trois",
	    "Quatre", "Cinq", "Six", "Sept", "Huit", "Neuf", "Dix", "Onze",
	    "Douze" };

    @Override
    public void onClick(View view) {
	String number = ((Button)view).getText().toString();
	
	additionView.checkNumber(Integer.valueOf(number));
    }
    
    private AdditionView additionView;
}