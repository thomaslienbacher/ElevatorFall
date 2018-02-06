package dev.thomaslienbacher.elevatorfall.utils;

/**
 * @author Thomas Lienbacher
 */
public class Logger {

	public static void log(Object x){
		System.out.println(x);
	}

	public static void log(String x){
		System.out.println(x);
	}
	
	public static void lognl(String x){
		System.out.print(x);
	}
	
	public static void log(byte x){
		System.out.println(x);
	}
	
	public static void log(short x){
		System.out.println(x);
	}

	public static void log(long x){
		System.out.println(x);
	}

	public static void log(int x){
		System.out.println(x);
	}
	
	public static void log(float x){
		System.out.println(x);
	}

	public static void log(double x){
		System.out.println(x);
	}
	
	public static void log(byte[] array){
		for(byte i : array){
			System.out.println(i);
		}
	}
	
	public static void log(short[] array){
		for(short i : array){
			System.out.println(i);
		}
	}
	
	public static void log(int[] array){
		for(int i : array){
			System.out.println(i);
		}
	}
	
	public static void log(long[] array){
		for(long i : array){
			System.out.println(i);
		}
	}
	
	public static void log(double[] array){
		for(double i : array){
			System.out.println(i);
		}
	}

	public static void log(boolean b) {
		System.out.println(b);
	}
	
	public static void log(String[] array){
		for(String i : array){
			System.out.println(i);
		}
	}
	
	public static void err(String x){
		System.err.println(x);
	}
	
	public static void ln(){
		System.err.println();
	}
}
