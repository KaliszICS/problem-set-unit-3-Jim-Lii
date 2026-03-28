/*
File Name: Problem Set Unit 3 (100%)
Author: Jim Li
Date Created: Mar. 27, 2026
Date Last Modified: Mar. 28, 2026
*/

import java.util.Scanner;
public class ProblemSet {

	public static void main(String[] args) {
		//receives then parses 2 emails
		Scanner input = new Scanner(System.in);
		System.out.print("Input two emails: ");
		String email = input.nextLine();
		email = email.toLowerCase(); //remove case sensitivity

		if (!email.contains(",")) {
			System.out.println("Separate emails with a comma.");
		}
		String email1 = (email.substring(0, email.indexOf(",")));
		String email2 = (email.substring(email.indexOf(",") + 2));

		//validation first attempt
		String reason1 = validate(email1);
		String reason2 = validate(email2);

		//exception checks
		reason1 = exceptionCheck(email1, reason1);
		reason2 = exceptionCheck(email2, reason2);

        //final outputs
		//email 1 output
		if (reason1.equals("Valid")) { //if valid
		    String local1 = email1.substring(0, email1.indexOf("@")); //extract local
	    	String domain1 = email1.substring(email1.indexOf("@") + 1); //extract domain
			System.out.print(email1 + ": Valid "); //print "email: valid "
			if (domain1.equals("gmail.com")) { //if gmail, print "(gmail normalized) "
				System.out.print("(Gmail normalized) ");
			}
			System.out.println("| Local: " + local1 + " | Domain: " + domain1); //print local + domain
		} else { //if not valid
			System.out.println(email1 + ": Invalid: " + reason1); //print email: invalid: reason
		}
		
		//email 2 output
		if (reason2.equals("Valid")) {
		    String local2 = email2.substring(0, email.indexOf("@"));
	    	String domain2 = email2.substring(email.indexOf("@") + 1);
			System.out.print(email2 + ": Valid ");
			if (domain2.equals("gmail.com")) {
				System.out.print("(Gmail normalized) ");
			}
			System.out.println("| Local: " + local2 + " | Domain: " + domain2);
		} else {
			System.out.println(email2 + ": Invalid: " + reason2);
		}

	}
	
	//validation method
	public static String validate(String email) {
		if (!email.contains("@")) { //step 1.1 - contains @?
			return "Missing @";
		} else if (email.indexOf("@") != email.lastIndexOf("@")) { //step 1.2 - only 1 @?
			return "Multiple @";
		} else if (email.startsWith(".") || email.endsWith(".")) { //step 2 - starts/ends with dot?
			return "Starts or ends with dot";
		} else if (email.contains(" ")) { //step 3 - contains spaces?
			return "Contains spaces";
		} else {
		    String local = email.substring(0, email.indexOf("@"));
	    	String domain = email.substring(email.indexOf("@") + 1);
			if (local.length() > 64) { //step 4.1 - local too long?
				return "Local part too long";
			} else if (local.length() < 1) { //step 4.2 - local too short?
				return "Local part too short";
			} else if (!domain.contains(".")) { //step 5 - domain contains dot?
				return "No dot in domain";
			} else { //extracts domain extension and its length
				String domainExtension = domain.substring(domain.indexOf(".") + 1);
				int domainExtensionLength = domainExtension.length();
				if (domainExtensionLength < 2 || domainExtensionLength > 6) { //step 6 - extension has 2 to 6 chars?
					return "Invalid domain extension length";
				} else {
					return "Valid"; //if all steps passed, return "Valid"
				}
			}
		}
	}
	
	//exception checks, combined
	public static String exceptionCheck(String email, String reason) {
	    //exception A
		if (!reason.equals("Valid")) {
		    String domain = email.substring(email.indexOf("@") + 1);
		    if (domain.indexOf(".") != domain.lastIndexOf(".")){ //checks for multiple dots
			    reason = exceptionA(email); //reattempt validation
		    }
		}
		//exception B
		if (reason.equals("Valid")) { //secondary check for exception B specific rules
			reason = exceptionB(email);
		}
		//exception C
		if (!reason.equals("Valid")) {
		    String domain = email.substring(email.indexOf("@") + 1);
			if (domain.equals("gmail.com")) {
				email = exceptionC(email); //if domain is gmail, remove dots from local
				reason = validate(email); //reattempt validation
			}
		}
		return reason;
	}

	//exception A - removes all dots in the domain (except the final one) then revalidates 
	//only applies if domain contains multiple dots
	public static String exceptionA(String email) {
	    String local = email.substring(0, email.indexOf("@"));
	    String domain = email.substring(email.indexOf("@") + 1);
		String domainExtension = domain.substring(domain.lastIndexOf(".")); //extract domain extension + dot
		String trimmedDomain = domain.substring(0, domain.lastIndexOf(".")); //remove domain extension
		trimmedDomain = trimmedDomain.replace(".", ""); //remove dots
		email = local + "@" + trimmedDomain + domainExtension; //reassemble everything
		return validate(email); //return new reason
	}

	//exception B - works by checking email beginning and domain extension for stuff
	//only applies if original attempt was valid
	public static String exceptionB(String email) {
		if (email.startsWith("+") || email.startsWith("_")) {
			return "Starts with plus or underscore";
		} else {
		    String domain = email.substring(email.indexOf("@") + 1);
			String domainExtension = domain.substring(domain.indexOf(".")); //extract domain extension
			if (containsNonLetter(domainExtension)) {
				return "Domain extension contains non-letters";
			} else {
				return "Valid";
			}
		}
	}

	//exception C - removes dots from local
	//only applies if domain is gmail.com
	public static String exceptionC(String email) {
	    String local = email.substring(0, email.indexOf("@"));
	    String domain = email.substring(email.indexOf("@") + 1);
		local = local.replace(".", "");
		return local + "@" + domain;
	}

	// method for detecting non-letters (+, _, 0-9); domain extension abbreviated to dE
	public static boolean containsNonLetter(String dE) {
		if (dE.contains("+") || dE.contains("_") || dE.contains("0") ||
		        dE.contains("1") || dE.contains("2") || dE.contains("3") ||
		        dE.contains("4") || dE.contains("5") || dE.contains("6") ||
		        dE.contains("7") || dE.contains("8") || dE.contains("9")) {
			return true;
		} else {
			return false;
		}
	}
}