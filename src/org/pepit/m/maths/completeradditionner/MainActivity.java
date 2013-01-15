/**
 * @file org/pepit/m/maths/completeradditionner/MainActivity.java
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
import android.app.Activity;
import android.content.Intent;
import android.view.View;
import android.widget.Button;

public class MainActivity extends Activity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
	super.onCreate(savedInstanceState);
	setContentView(R.layout.activity_main);

	Button button_exo = (Button) findViewById(R.id.button1);
	button_exo.setOnClickListener(new View.OnClickListener() {
	    public void onClick(View v) {
		Intent intent = new Intent(MainActivity.this,
			ExerciseActivity.class);
		
		startActivity(intent);
	    }
	});

	Button quit = (Button) findViewById(R.id.button3);
	quit.setOnClickListener(new View.OnClickListener() {
	    public void onClick(View v) {
		finish();
	    }
	});
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
	super.onSaveInstanceState(outState);
    }
}
