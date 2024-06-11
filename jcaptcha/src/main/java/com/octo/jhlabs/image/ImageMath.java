/*
Copyright 2006 Jerry Huxtable

Licensed under the Apache License, Version 2.0 (the "License");
you may not use this file except in compliance with the License.
You may obtain a copy of the License at

   http://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing, software
distributed under the License is distributed on an "AS IS" BASIS,
WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
See the License for the specific language governing permissions and
limitations under the License.
*/

package com.octo.jhlabs.image;

/**
 * A class containing static math methods useful for image processing.
 */
public class ImageMath {

    /**
     * The value of pi as a float.
     */
	public final static float PI = (float)Math.PI;

    /**
     * The value of half pi as a float.
     */
	public final static float HALF_PI = (float)Math.PI/2.0f;

    /**
     * The value of quarter pi as a float.
     */
	public final static float QUARTER_PI = (float)Math.PI/4.0f;

    /**
     * The value of two pi as a float.
     */
	public final static float TWO_PI = (float)Math.PI*2.0f;

	/**
	 * Clamp a value to an interval.
	 * @param a the lower clamp threshold
	 * @param b the upper clamp threshold
	 * @param x the input parameter
	 * @return the clamped value
	 */
	public static int clamp(int x, int a, int b) {
		return (x < a) ? a : (x > b) ? b : x;
	}

	/**
	 * Return a mod b. This differs from the % operator with respect to negative numbers.
	 * @param a the dividend
	 * @param b the divisor
	 * @return a mod b
	 */
	public static double mod(double a, double b) {
		int n = (int)(a/b);
		
		a -= n*b;
		if (a < 0)
			return a + b;
		return a;
	}

	/**
	 * Return a mod b. This differs from the % operator with respect to negative numbers.
	 * @param a the dividend
	 * @param b the divisor
	 * @return a mod b
	 */
	public static float mod(float a, float b) {
		int n = (int)(a/b);
		
		a -= n*b;
		if (a < 0)
			return a + b;
		return a;
	}

	/**
	 * Return a mod b. This differs from the % operator with respect to negative numbers.
	 * @param a the dividend
	 * @param b the divisor
	 * @return a mod b
	 */
	public static int mod(int a, int b) {
		int n = a/b;
		
		a -= n*b;
		if (a < 0) {
			return a + b;
		}
		return a;
	}

	/**
	 * The triangle function. Returns a repeating triangle shape in the range 0..1 with wavelength 1.0
	 * @param x the input parameter
	 * @return the output value
	 */
	public static float triangle(float x) {
		float r = mod(x, 1.0f);
		return 2.0f*(r < 0.5 ? r : 1-r);
	}


	

	/**
	 * Bilinear interpolation of ARGB values.
	 * @param x the X interpolation parameter 0..1
	 * @param y the y interpolation parameter 0..1
	 * @param rgb array of four ARGB values in the order NW, NE, SW, SE
	 * @return the interpolated value
	 */
	public static int bilinearInterpolate(float x, float y, int nw, int ne, int sw, int se) {
		float m0, m1;
		int a0 = (nw >> 24) & 0xff;
		int r0 = (nw >> 16) & 0xff;
		int g0 = (nw >> 8) & 0xff;
		int b0 = nw & 0xff;
		int a1 = (ne >> 24) & 0xff;
		int r1 = (ne >> 16) & 0xff;
		int g1 = (ne >> 8) & 0xff;
		int b1 = ne & 0xff;
		int a2 = (sw >> 24) & 0xff;
		int r2 = (sw >> 16) & 0xff;
		int g2 = (sw >> 8) & 0xff;
		int b2 = sw & 0xff;
		int a3 = (se >> 24) & 0xff;
		int r3 = (se >> 16) & 0xff;
		int g3 = (se >> 8) & 0xff;
		int b3 = se & 0xff;

		float cx = 1.0f-x;
		float cy = 1.0f-y;

		m0 = cx * a0 + x * a1;
		m1 = cx * a2 + x * a3;
		int a = (int)(cy * m0 + y * m1);

		m0 = cx * r0 + x * r1;
		m1 = cx * r2 + x * r3;
		int r = (int)(cy * m0 + y * m1);

		m0 = cx * g0 + x * g1;
		m1 = cx * g2 + x * g3;
		int g = (int)(cy * m0 + y * m1);

		m0 = cx * b0 + x * b1;
		m1 = cx * b2 + x * b3;
		int b = (int)(cy * m0 + y * m1);

		return (a << 24) | (r << 16) | (g << 8) | b;
	}

}
