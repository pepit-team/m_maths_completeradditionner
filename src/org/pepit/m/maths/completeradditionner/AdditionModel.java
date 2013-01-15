/**
 * @file org/pepit/m/maths/completeradditionner/AdditionModel.java
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

import java.util.Random;

public class AdditionModel {
    
    public AdditionModel(int number, int max) {
	Random r = new Random();
	
	firstOperands = new int[number];
	secondOperands = new int[number];
	for (int i = 0; i < number; ++i) {
	    firstOperands[i] = r.nextInt(max) + 1;
	    secondOperands[i] = r.nextInt(max) + 1;
	}
	index = 0;
    }
    
    public int firstOperand() {
	return firstOperands[index];
    }

    public int secondOperand() {
	return secondOperands[index];
    }

    private int firstOperands[];
    private int secondOperands[];
    private int index;
}
