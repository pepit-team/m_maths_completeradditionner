/**
 * @file org/pepit/m/maths/completeradditionner/AdditionView.java
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

import java.io.IOException;

import org.pepit.plugin.Utils;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Paint.Style;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

@SuppressLint("DrawAllocation")
public class AdditionView extends View {

    public AdditionView(Context context) {
	super(context);
	init(context);
    }

    public AdditionView(Context context, AttributeSet attrs, int defStyle) {
	super(context, attrs, defStyle);
	init(context);
    }

    public AdditionView(Context context, AttributeSet attrs) {
	super(context, attrs);
	init(context);
    }

    public boolean check() {
	return resultFound;
    }

    public void checkNumber(int number) {
	if (firstOperandSelected && model.firstOperand() == number) {
	    firstOperandSelected = false;
	    firstOperandFound = true;
	    invalidate();
	} else if (secondOperandSelected && model.secondOperand() == number) {
	    secondOperandSelected = false;
	    secondOperandFound = true;
	    invalidate();
	}
	if (resultSelected
		&& model.firstOperand() + model.secondOperand() == number) {
	    resultFound = true;
	    resultSelected = false;
	    invalidate();
	}
    }

    private void computeDimensions() {
	margin_x = Constants.MARGIN_X;
	margin_y = Constants.MARGIN_Y;

	width = this.getWidth() - (margin_x * 2);
	height = this.getHeight() - (margin_y * 2);

	card_width = width / 5;
	card_height = 3 * height / 4;

	symbol_size = card_height / 6;

	shift_x = width / 2 - 2 * card_width;
    }

    private void drawCard(int x, int i, boolean selected, boolean found,
	    Canvas canvas, Paint paint) {
	RectF dst = new RectF(x, 10, x + card_width, card_height);
	Bitmap bitmap = null;

	try {
	    bitmap = Utils.getImage(plugin, "card_" + i + ".png");
	} catch (IOException e) {
	    e.printStackTrace();
	}

	canvas.drawBitmap(bitmap, null, dst, paint);
	if (selected) {
	    paint.setColor(Color.RED);
	    paint.setStrokeWidth(5);
	    paint.setStyle(Style.STROKE);
	    canvas.drawRect(dst, paint);
	}
	paint.setStrokeWidth(1);
	paint.setStyle(Style.FILL_AND_STROKE);
	paint.setTextSize(50);
	if (found) {
	    float width = paint.measureText("" + i);

	    canvas.drawText("" + i, x + (card_width - width) / 2,
		    card_height + 60, paint);
	} else {
	    float width = paint.measureText("???");

	    canvas.drawText("???", x + (card_width - width) / 2,
		    card_height + 60, paint);
	}
    }

    private void drawEqual(int x, Canvas canvas, Paint paint) {
	paint.setColor(Color.GREEN);
	paint.setStrokeWidth(10);
	canvas.drawLine(x - symbol_size,
		10 + card_height / 2 - symbol_size / 2, x + symbol_size, 10
			+ card_height / 2 - symbol_size / 2, paint);
	canvas.drawLine(x - symbol_size,
		10 + card_height / 2 + symbol_size / 2, x + symbol_size, 10
			+ card_height / 2 + symbol_size / 2, paint);
    }

    private void drawPlus(int x, Canvas canvas, Paint paint) {
	paint.setColor(Color.GREEN);
	paint.setStrokeWidth(10);
	canvas.drawLine(x, 10 + card_height / 2 - symbol_size, x, 10
		+ card_height / 2 + symbol_size, paint);
	canvas.drawLine(x - symbol_size, 10 + card_height / 2, x + symbol_size,
		10 + card_height / 2, paint);

    }

    private void init(Context context) {
	model = null;
	firstOperandFound = false;
	secondOperandFound = false;
	resultFound = false;
	firstOperandSelected = false;
	secondOperandSelected = false;
	resultSelected = false;
	plugin = null;
    }
    
    public void next() {
	model.next();
    }

    private boolean onCard(int ref, float x, float y) {
	return x > ref && x < ref + card_width && y > 10 && y < card_height;
    }

    @Override
    protected void onDraw(Canvas canvas) {
	super.onDraw(canvas);

	if (plugin != null) {
	    computeDimensions();

	    Paint paint = new Paint();

	    drawCard(shift_x, model.firstOperand(), firstOperandSelected,
		    firstOperandFound, canvas, paint);
	    drawCard(shift_x + card_width + card_width / 2,
		    model.secondOperand(), secondOperandSelected,
		    secondOperandFound, canvas, paint);
	    if (resultFound) {
		drawCard(shift_x + 3 * card_width,
			model.firstOperand() + model.secondOperand(),
			resultSelected, true, canvas, paint);
	    } else {
		drawCard(shift_x + 3 * card_width, 0, resultSelected, false,
			canvas, paint);
	    }

	    drawPlus(shift_x + card_width + card_width / 4, canvas, paint);
	    drawEqual(shift_x + 2 * card_width + 3 * card_width / 4, canvas,
		    paint);
	}
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {

	if (onCard(shift_x, event.getX(), event.getY())) {
	    firstOperandSelected = !firstOperandSelected;
	    if (firstOperandSelected) {
		secondOperandSelected = false;
		resultSelected = false;
	    }
	    invalidate();
	} else if (onCard(shift_x + card_width + card_width / 2, event.getX(),
		event.getY())) {
	    secondOperandSelected = !secondOperandSelected;
	    if (secondOperandSelected) {
		firstOperandSelected = false;
		resultSelected = false;
	    }
	    invalidate();
	} else if (firstOperandFound && secondOperandFound
		&& onCard(shift_x + 3 * card_width, event.getX(), event.getY())) {
	    resultSelected = !resultSelected;
	    if (resultSelected) {
		firstOperandSelected = false;
		secondOperandSelected = false;
	    }
	    invalidate();
	}

	return super.onTouchEvent(event);

    }

    public void build(org.pepit.plugin.Interface plugin, int number, int max) {
	this.plugin = plugin;
	model = new AdditionModel(number, max);
    }

    private org.pepit.plugin.Interface plugin;

    private int margin_x;
    private int margin_y;

    private int width;
    private int height;

    private int card_width;
    private int card_height;

    private int symbol_size;

    private int shift_x;

    private AdditionModel model;

    private boolean firstOperandFound;
    private boolean secondOperandFound;
    private boolean resultFound;

    private boolean firstOperandSelected;
    private boolean secondOperandSelected;
    private boolean resultSelected;
}