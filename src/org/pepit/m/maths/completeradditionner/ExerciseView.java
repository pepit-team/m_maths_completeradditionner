/**
 * @file org/pepit/m/maths/completeradditionner/ExerciseActivity.java
 * 
 * PepitMobil: an educational software
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

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Color;
import android.view.Gravity;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

public class ExerciseView implements OnClickListener {

    public ExerciseView(Context ctx, org.pepit.plugin.Interface plugin,
	    int number, int max) {
	context = ctx;

	rootLayout = new LinearLayout(ctx);
	rootLayout.setOrientation(LinearLayout.VERTICAL);

	LinearLayout topLayout = new LinearLayout(ctx);
	LinearLayout.LayoutParams topLayoutParams = new LinearLayout.LayoutParams(
		LinearLayout.LayoutParams.MATCH_PARENT,
		LinearLayout.LayoutParams.WRAP_CONTENT, 1);

	topLayout.setOrientation(LinearLayout.HORIZONTAL);
	for (int i = 0; i < 6; ++i) {
	    buildNumberLayout(i + 1, topLayout, true);
	}

	LinearLayout bottomLayout = new LinearLayout(ctx);
	LinearLayout.LayoutParams bottomLayoutParams = new LinearLayout.LayoutParams(
		LinearLayout.LayoutParams.MATCH_PARENT,
		LinearLayout.LayoutParams.WRAP_CONTENT, 1);

	bottomLayout.setOrientation(LinearLayout.HORIZONTAL);
	for (int i = 0; i < 6; ++i) {
	    buildNumberLayout(i + 7, bottomLayout, false);
	}

	LinearLayout middleLayout = new LinearLayout(ctx);
	LinearLayout.LayoutParams middleLayoutParams = new LinearLayout.LayoutParams(
		LinearLayout.LayoutParams.WRAP_CONTENT, 0, 10);

	additionView = new AdditionView(ctx);
	additionView.build(plugin, number, max);
	middleLayout.addView(additionView);

	rootLayout.addView(topLayout, topLayoutParams);
	rootLayout.addView(middleLayout, middleLayoutParams);
	rootLayout.addView(bottomLayout, bottomLayoutParams);
    }

    @SuppressLint("UseValueOf")
    private void buildNumberLayout(int i, LinearLayout layout, boolean below) {
	TextView t = new TextView(context);
	Button b = new Button(context);
	LinearLayout numberLayout = new LinearLayout(context);
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

    public boolean check() {
	return additionView.check();
    }

    public LinearLayout getLayout() {
	return rootLayout;
    }

    public void next() {
	additionView.next();
    }

    private static String[] numbers = { "Zero", "Un", "Deux", "Trois",
	    "Quatre", "Cinq", "Six", "Sept", "Huit", "Neuf", "Dix", "Onze",
	    "Douze" };

    public void onClick(View view) {
	String number = ((Button) view).getText().toString();

	additionView.checkNumber(Integer.valueOf(number));
    }

    private Context context;

    private LinearLayout rootLayout;

    private AdditionView additionView;
}