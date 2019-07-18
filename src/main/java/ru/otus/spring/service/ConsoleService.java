package ru.otus.spring.service;

public interface ConsoleService {
	
	String ANSI_RESET = "\u001B[0m";
	String ANSI_RED = "\u001B[31m";
	String ANSI_GREEN = "\u001B[32m";
	String ANSI_YELLOW = "\u001B[33m";
	
	void write(String msg, Object... args);

}
