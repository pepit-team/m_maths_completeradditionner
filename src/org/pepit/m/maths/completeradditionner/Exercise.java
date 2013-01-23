/**
 * @file org/pepit/m/maths/completeradditionner/Exercise.java
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

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;

import org.pepit.plugin.Info;

import android.content.Context;
import android.graphics.Color;
import android.util.Log;
import android.util.TypedValue;
import android.view.Gravity;
import android.widget.LinearLayout;
import android.widget.TextView;

public class Exercise implements org.pepit.plugin.Interface {

    /**
     * Plugin metadata
     */
    public Info getInfo() {
	org.pepit.plugin.Info info = new org.pepit.plugin.Info();
	info.level = org.pepit.plugin.Level.M;
	info.subject = org.pepit.plugin.Subject.MATHEMATICS;
	info.theme = "completeradditionner";
	info.version = 1;
	String pepitPage = "http://www.pepit.be/exercices/maternelles/mathematique/completeradditionner/page.html";
	try {
	    info.pepitPage = new URL(pepitPage);
	} catch (MalformedURLException e) {
	    Log.e("Pepit", "Bad URL: " + pepitPage);
	}
	return info;
    }

    public LinearLayout getExercisePresentationLayout(Context ctx,
	    File rootResource) {
	LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
		LinearLayout.LayoutParams.MATCH_PARENT,
		LinearLayout.LayoutParams.MATCH_PARENT);
	params.gravity = Gravity.CENTER_HORIZONTAL;

	LinearLayout lil = new LinearLayout(ctx);
	lil.setLayoutParams(params);
	lil.setOrientation(LinearLayout.VERTICAL);
	lil.setBackgroundColor(0xFF99CC66);

	TextView tv1 = new TextView(ctx);
	tv1.setGravity(Gravity.CENTER_HORIZONTAL);
	tv1.setTextColor(Color.BLACK);
	tv1.setTextSize(TypedValue.COMPLEX_UNIT_SP, 50);
	tv1.setText("COMPLETER et ADDITIONNER");
	lil.addView(tv1);

	return lil;
    }

    public String[] getExerciseList() {
	String[] l = { "Addition - niveau 1", "Addition - niveau 2",
		"Addition - niveau 3", "Addition - niveau 4",
		"Addition - niveau 5" };

	return l;
    }

    public LinearLayout getExplanationPresentationLayout(Context ctx,
	    File rootResource, int selectedExercise) {
	LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
		LinearLayout.LayoutParams.MATCH_PARENT,
		LinearLayout.LayoutParams.MATCH_PARENT);
	params.gravity = Gravity.CENTER_HORIZONTAL;

	LinearLayout lil = new LinearLayout(ctx);
	lil.setLayoutParams(params);
	lil.setOrientation(LinearLayout.VERTICAL);
	lil.setBackgroundColor(0xFF99CC66);

	TextView tv1 = new TextView(ctx);
	tv1.setGravity(Gravity.CENTER_HORIZONTAL);
	tv1.setTextColor(Color.BLACK);
	tv1.setTextSize(TypedValue.COMPLEX_UNIT_SP, 40);
	tv1.setText("Comment jouer ?");
	lil.addView(tv1);

	TextView tv2 = new TextView(ctx);
	tv2.setGravity(Gravity.CENTER_HORIZONTAL);
	tv2.setTextColor(Color.BLACK);
	tv2.setTextSize(TypedValue.COMPLEX_UNIT_SP, 35);
	String[] t = this.getExerciseList();
	tv2.setText("\n" + t[selectedExercise] + "\n");
	lil.addView(tv2);

	return lil;
    }

    public String[] getModuleList(int exercise) {
	String[] l = { "Module 1", "Module 2", "Module 3", "Module 4",
		"Module 5" };

	return l;
    }

    public LinearLayout getQuestionLayout(Context ctx, File rootResource,
	    int selectedExercise, int selectedModule, int numQuestion) {
	if (numQuestion == 1) {
	    if (selectedExercise == 0) {
		view = new ExerciseView(ctx, this, 5, 5);
	    } else if (selectedExercise == 1) {
		view = new ExerciseView(ctx, this, 5, 6);
	    } else if (selectedExercise == 2) {
		view = new ExerciseView(ctx, this, 5, 8);
	    } else if (selectedExercise == 3) {
		view = new ExerciseView(ctx, this, 5, 10);
	    } else if (selectedExercise == 4) {
		view = new ExerciseView(ctx, this, 5, 12);
	    }
	} else {
	    view.next();
	}
	return view.getLayout();
    }

    public int getQuestionCount(int selectedExercise, int selectedModule) {
	return 5;
    }

    public void startQuestionSequence() {

    }

    public void finishQuestionSequence() {
    }

    public String getNextQuestionButtonText() {
	return "Valider";
    }

    public boolean currentAnswerIsRight() {
	return view.check();
    }

    public void showAnswerIsRight() {
	// view.displayCorrectMessage();
    }

    public void showAnswerIsWrong() {
	// view.displayErrorMessage();
    }

    public int getScore() {
	// TODO Auto-generated method stub
	return 0;
    }

    private ExerciseView view = null;
}
