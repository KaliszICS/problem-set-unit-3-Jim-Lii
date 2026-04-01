/*
File Name: Problem Set Unit 3 (100%)
Author: Jim Li
Date Created: Mar. 27, 2026
Date Last Modified: Apri. 1, 2026
*/

import java.util.Scanner;
public class ProblemSet {

	public static void main(String[] args) {
		//input emails
		Scanner input = new Scanner(System.in);
		System.out.print("Input two emails: ");
		String emails = input.nextLine();
		emails = emails.toLowerCase(); //remove case sensitivity

		if (emails.contains(",")) {
			//parse emails
		    String email1 = (emails.substring(0, emails.indexOf(",")));
		    String email2 = (emails.substring(emails.indexOf(",") + 2));
	    	//validation
		    String reason1 = validate(email1);
	    	String reason2 = validate(email2);
	    	//final result
	    	System.out.println(result(reason1, email1));
	    	System.out.println(result(reason2, email2));
		} else {
		    String reason = validate(emails);
		    System.out.println(result(reason, emails));
		}
	}

	//validation
	public static String validate(String email) {
		if (!email.contains("@")) { //step 1.1 - contains @?
			return "Missing @";
		}
		if (email.indexOf("@") != email.lastIndexOf("@")) { //step 1.2 - only 1 @?
			return "Multiple @";
		}
		if (email.startsWith(".") || email.endsWith(".")) { //step 2 - starts/ends with dot?
			return "Starts or ends with dot";
		}
		if (email.contains(" ")) { //step 3 - contains spaces?
			return "Contains spaces";
		}
		String local = email.substring(0, email.indexOf("@"));
		String domain = email.substring(email.indexOf("@") + 1);
		//exception C------------------------
		if (domain.equals("gmail.com")) {
			local = local.replace(".", "");
		}
		//-----------------------------------
		if (local.length() > 64) { //step 4.1 - local too long?
			return "Local part too long";
		}
		if (local.length() < 1) { //step 4.2 - local too short?
			return "Local part too short";
		}
		if (!domain.contains(".")) { //step 5 - domain contains dot?
			return "No dot in domain";
		}
		String domainExtension = domain.substring(domain.lastIndexOf(".") + 1);
		int domainExtensionLength = domainExtension.length();
		if (domainExtensionLength < 2 || domainExtensionLength > 6) { //step 6 - extension has 2 to 6 chars?
			return "Invalid domain extension length";
		}

		//exception B check
		return exceptionB(email, domain);
	}

	//exception B - works by checking email beginning and domain extension for stuff, basically more rules
	public static String exceptionB(String email, String domain) {
		if (email.startsWith("+") || email.endsWith("+")) { //starts or ends with plus?
			return "Starts or ends with plus";
		}
		if (email.startsWith("_") || email.endsWith("_")) { //starts or ends with underscore?
			return "Starts or ends with underscore";
		}
		if (domain.contains("+")) { //domain contains plus?
			return "Domain contains plus";
		}
		if (domain.contains("_")) { //domain contains underscore?
			return "Domain contains underscore";
		}
		return "Valid"; //if all passed
	}

	//result method
	public static String result(String reason, String email) {
		//if valid
		if (reason.equals("Valid")) {
			String local = email.substring(0, email.indexOf("@"));
			String domain = email.substring(email.indexOf("@") + 1);
			System.out.print(email + ": Valid ");
			if (domain.equals("gmail.com")) {
				System.out.print("(Gmail normalized) ");
			}
			return "| Local: " + local + " | Domain: " + domain;
		}
		//if invalid
		return email + ": Invalid: " + reason;
	}
}