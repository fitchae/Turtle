/* Copyright (c) 2007-2014 MIT 6.005 course staff, all rights reserved.
 * Redistribution of original or derived work requires permission of course staff.
 */
package turtle;

import java.awt.Color;
import java.lang.Math;
import java.util.List;
import java.util.ArrayList;

public class TurtleSoup {

    /**
     * Draw a square.
     * 
     * @param turtle the turtle context
     * @param sideLength length of each side
     */
    public static void drawSquare(Turtle turtle, int sideLength) {
        
    	for(int i =0;i<4;i++){
    		turtle.forward(sideLength);
    		turtle.draw();
    		turtle.turn(90);
    	}
        
    }

    /**
     * Determine inside angles of a regular polygon.
     * 
     * There is a simple formula for calculating the inside angles of a polygon;
     * you should derive it and use it here.
     * 
     * @param sides number of sides, where sides must be > 2
     * @return angle in degrees, where 0 <= angle < 360 
     */
    public static double calculateRegularPolygonAngle(int sides){
		double angle = 0.0;
		if(angle <360 && angle >=0){
	    	
	    	if(sides>2){
	    		angle = 180-(360/(double)sides);
	    	}
		}
    	return angle;
    }

    /**
     * Determine number of sides given the size of interior angles of a regular polygon.
     * 
     * There is a simple formula for this; you should derive it and use it here.
     * Make sure you *properly round* the answer before you return it (see java.lang.Math).
     * HINT: it is easier if you think about the exterior angles.
     * 
     * @param angle size of interior angles in degrees
     * @return the integer number of sides
     */
    public static int calculatePolygonSidesFromAngle(double angle) {
        
    	int sides = (int) Math.ceil((360/(180-angle)));
    	return sides;
    }

    /**
     * Given the number of sides, draw a regular polygon.
     * 
     * (0,0) is the lower-left corner of the polygon; use only right-hand turns to draw.
     * 
     * @param turtle the turtle context
     * @param sides number of sides of the polygon to draw
     * @param sideLength length of each side
     */
    public static void drawRegularPolygon(Turtle turtle, int sides, int sideLength) {
        for(int i = 0; i < sides; i++){
        	turtle.forward(sideLength);
        	turtle.draw();
        	turtle.turn(360/sides);
        }
    }

    /**
     * Given the current direction, current location, and a target location, calculate the heading
     * towards the target point.
     * 
     * The return value is the angle input to turn() that would point the turtle in the direction of
     * the target point (targetX,targetY), given that the turtle is already at the point
     * (currentX,currentY) and is facing at angle currentHeading. The angle must be expressed in
     * degrees, where 0 <= angle < 360. 
     *
     * HINT: look at http://en.wikipedia.org/wiki/Atan2 and Java's math libraries
     * 
     * @param currentHeading current direction as clockwise from north
     * @param currentX currentY current location
     * @param targetX targetY target point
     * @return adjustment to heading (right turn amount) to get to target point,
     *         must be 0 <= angle < 360.
     */
    public static double calculateHeadingToPoint(double currentHeading, int currentX, int currentY,
                                                 int targetX, int targetY) {
        double shiftX = targetX-currentX;
        double shiftY = targetY-currentY;
        
        double heading = Math.toDegrees(Math.atan2(shiftX, shiftY));
        System.out.println(heading);
        
       double angle = 0.0;
        if(heading<currentHeading){
        	angle = Math.ceil(360-currentHeading+heading);
        }else{
        	angle = Math.ceil(currentHeading+heading);
        }
        
        System.out.println(angle);
       
        System.out.println(angle);
        return angle;
    }
    

    /**
     * Given a sequence of points, calculate the heading adjustments needed to get from each point
     * to the next.
     * 
     * Assumes that the turtle starts at the first point given, facing up (i.e. 0 degrees).
     * For each subsequent point, assumes that the turtle is still facing in the direction it was
     * facing when it moved to the previous point.
     * You should use calculateHeadingToPoint() to implement this function.
     * 
     * @param xCoords list of x-coordinates (must be same length as yCoords)
     * @param yCoords list of y-coordinates (must be same length as xCoords)
     * @return list of heading adjustments between points, of size (# of points) - 1.
     */
    public static List<Double> calculateHeadings(List<Integer> xCoords, List<Integer> yCoords) {
        List <Double> list = new ArrayList<Double>();
        double direction = 0.0;
    	for(int i = 0;i<xCoords.size()-1;i++){
        	list.add(calculateHeadingToPoint(direction,xCoords.get(i),yCoords.get(i),xCoords.get(i+1),yCoords.get(i+1)));
        	direction = list.get(i);
        	System.out.print(list.get(i));
        }
        return list;
    }

    /**
     * Draw your personal, custom art.
     * 
     * Many interesting images can be drawn using the simple implementation of a turtle.  For this
     * function, draw something interesting; the complexity can be as little or as much as you want.
     * We'll be peer-voting on the different images, and the highest-rated one will win a prize. 
     * 
     * @param turtle the turtle context
     */
    public static void drawPersonalArt(Turtle turtle) {
    	// This is an ArrayList made in order to have a way to randomly select a PenColor
    	// value when cycling through a for loop.  For my art, I liked the uncommented colors
    	// together the best, and chose to omit the other colors seen here.
    	List<PenColor> colors = new ArrayList<PenColor>();
    	//colors.add(PenColor.BLACK);
    	colors.add(PenColor.BLUE);
    	colors.add(PenColor.CYAN);
    	//colors.add(PenColor.GRAY);
    	colors.add(PenColor.GREEN);
    	colors.add(PenColor.MAGENTA);
    	colors.add(PenColor.ORANGE);
    	//colors.add(PenColor.PINK);
    	//colors.add(PenColor.RED);
    	//colors.add(PenColor.YELLOW);
    	
    	int length = 1;
    	int numSides = 1200;
    	double angle = calculateRegularPolygonAngle(numSides);
    	
    	for(int i = 0; i < numSides-1; i++) {
    		turtle.color(colors.get((int)(Math.random()*(colors.size()))));
    		turtle.forward(length);
    		turtle.turn(angle);
    		length++;
    	}
    	
    	int length2 = 5;
    	int sq1 = 91; // The 91 will help keep the "polygon" contained to be square-like
    	
    	// The ending length is divided by 2 in order to get the starting point for the effect.
    	// The new length is also divided so the pattern isn't offset(rather than the line starting
    	// from the middle, we want the central shape to be centered.
    	turtle.forward((length/2) + (length2/2));
    	turtle.turn(50);
    	turtle.forward((0-(length2/2)));
    	
    	// The numSides variable is also divided by 4 in order to keep scaling the same.
    	for (int in = 0; in < (numSides/4) - 1; in++) {
    		turtle.color(PenColor.BLACK);
    		turtle.forward(length2);
    		turtle.turn(sq1);
    		length2 += 5;

    	}
        
    }

    /**
     * Main method.
     * 
     * This is the method that runs when you run "java TurtleSoup".
     */
    public static void main(String args[]) throws Exception {
        DrawableTurtle turtle = new DrawableTurtle();

        //drawSquare(turtle, 40);
        //drawRegularPolygon(turtle, 5, 40);
        drawPersonalArt(turtle);
        
        // draw the window
        turtle.draw();
    }

}
