package javavanais;

import java.util.LinkedList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Main {

	public final static String CONSONANTS = "bcdfghjklmnpqrstvwxz";
	public final static String VOWELS = "aeiouy";
	
	/**
	 * Main
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		
		final List<String> sentences = new LinkedList<>();
		sentences.add("bonjour");
		sentences.add("chante");
		sentences.add("moyen");
		sentences.add("exemple");
		sentences.add("au");
		sentences.add("so long and thanks for all the fish");
		
		final List<String> saventavencaves = new LinkedList<>();
		saventavencaves.add("bavonjavour");
		saventavencaves.add("chavantave");
		saventavencaves.add("mavoyen");
		saventavencaves.add("avexavemplave");
		saventavencaves.add("avau");
		saventavencaves.add("savo lavong and thavanks favor all thave favish");
		
		System.out.println("Current >>> Javanais");
		
		for (String sentence : sentences) {
			System.out.println(javanize(sentence));
		}
		
		System.out.println("\nCurrent <<< Javanais");
		
		for (String sentence : saventavencaves) {
			System.out.println(unJavanize(sentence));
		}
	}
	
	/**
	 * Convert a sentence to Javanais
	 * 
	 * @param String sentence
	 * @return String sentence javanized
	 */
	public static String javanize(final String sentence) {
		
		final String replacement = "av";
		final String regexStep1 = "([" + Main.CONSONANTS + "]{1})([" + Main.VOWELS + "]{1})";
		final String regexStep2 = "^[" + Main.VOWELS + "]{1}$";
		final Pattern pattern = Pattern.compile(regexStep1, Pattern.CASE_INSENSITIVE);
		final Matcher matcher = pattern.matcher(sentence);
		final StringBuffer phravasave = new StringBuffer();
		
		/* Apply pattern n°1 for main replacement rule */
		while (matcher.find()) {
			matcher.appendReplacement(phravasave, matcher.group(1) + replacement + matcher.group(2));
		}
		matcher.appendTail(phravasave);
		
		/* Apply Pattern n°2 to deal with first vowel replacement*/
		if (String.valueOf(phravasave.charAt(0)).matches(regexStep2)) {
			phravasave.insert(0, replacement);
		}

		return phravasave.toString();
	}
	
	/**
	 * Translate from Javanais to current language
	 * 
	 * @param String sentence
	 * @return String sentence translated in current language
	 */
	public static String unJavanize(String sentence) {
		
		final String seekRegex = "(av){1}";
		final String regex = "([" + Main.CONSONANTS + "]{1})" + seekRegex + "([" + Main.VOWELS + "]{1})";
		final Pattern pattern = Pattern.compile(regex, Pattern.CASE_INSENSITIVE);
		final StringBuffer phravasave = new StringBuffer();
		
		/* Remove first seek if exists */
		sentence = sentence.replaceFirst(seekRegex, "");
		
		/* Search for main pattern replacement */
		final Matcher matcher = pattern.matcher(sentence);
		while (matcher.find()) {
			matcher.appendReplacement(phravasave, matcher.group(1) + matcher.group(3));
		}
		matcher.appendTail(phravasave);
		
		return phravasave.toString();
	}
}
