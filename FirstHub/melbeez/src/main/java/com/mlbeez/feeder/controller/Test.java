package com.mlbeez.feeder.controller;

import java.io.File;

public class Test {

	public static void main(String[] args) {
		String fileName="script.txt";
		String[] partStrings=fileName.split("\\.");
		System.out.println(partStrings);
		String file = partStrings[1];
		System.out.println(file);
		String extension = (partStrings.length>1)? partStrings[1]:"";
		 
		System.out.println("fileName :"+ file);
		
		System.out.println("fileExtension :"+ extension);

	}

}

