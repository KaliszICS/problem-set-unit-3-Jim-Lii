/*
File Name: Problem Set Unit 3 (100%)
Author: Jim Li
Date Created: Mar. 27, 2026
Date Last Modified: Mar. 31, 2026
*/

import java.util.Scanner;
public class ProblemSet {

	public static void main(String[] args) {
		//input emails
		Scanner input = new Scanner(System.in);
		System.out.print("Input two emails: ");
		String emails = input.nextLine();
		emails = emails.toLowerCase(); //remove case sensitivity

		if (!emails.contains(",")) {
			System.out.println("Separate emails with a comma.");
		}

		//parse emails
		String email1 = (emails.substring(0, emails.indexOf(",")));
		String email2 = (emails.substring(emails.indexOf(",") + 2));

		//validation first attempt
		String reason1 = validate(email1);
		String reason2 = validate(email2);

		//exception checks
		reason1 = exceptionCheck(email1, reason1);
		reason2 = exceptionCheck(email2, reason2);

        //final outputs
        System.out.println(result(reason1, email1));
        System.out.println(result(reason2, email2));
	}

	//validation
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
			} else {
				String domainExtension = domain.substring(domain.indexOf(".") + 1);
				int domainExtensionLength = domainExtension.length();
				if (domainExtensionLength < 2 || domainExtensionLength > 6) { //step 6 - extension has 2 to 6 chars?
					return "Invalid domain extension length";
				} else {
					return "Valid"; //if all passed
				}
			}
		}
	}
	
	//exception checks, combined
	public static String exceptionCheck(String email, String reason) {
	    //exception A
		if (!reason.equals("Valid")) {
		    String domain = email.substring(email.indexOf("@") + 1);
		    if (domain.indexOf(".") != domain.lastIndexOf(".")){
			    reason = exceptionA(email);
		    }
		}
		//exception B
		boolean exceptionB = false;
		if (reason.equals("Valid")) {
			reason = exceptionB(email);
		    //Exception C was cancelling exception B so I added this
			if (!reason.equals("Valid")){
			    exceptionB = true;
			}
		}
		//exception C
		if (!reason.equals("Valid") && exceptionB == false) {
		    String domain = email.substring(email.indexOf("@") + 1);
			if (domain.equals("gmail.com")) {
				reason = validate(exceptionC(email));
			}
		}
		return reason;
	}

	//exception A - removes all dots in the domain
	public static String exceptionA(String email) {
	    String local = email.substring(0, email.indexOf("@"));
	    String domain = email.substring(email.indexOf("@") + 1);
		String domainExtension = domain.substring(domain.lastIndexOf("."));
		String trimmedDomain = domain.substring(0, domain.lastIndexOf("."));
		trimmedDomain = trimmedDomain.replace(".", "");
		email = local + "@" + trimmedDomain + domainExtension;
		return validate(email);
	}

	//exception B - works by checking email beginning and domain extension for stuff
	public static String exceptionB(String email) {
		if (email.startsWith("+") || email.endsWith("+")) { //starts or ends with plus?
			return "Starts or ends with plus";
		} else if (email.startsWith("_") || email.endsWith("_")) { //starts or ends with underscore?
			return "Starts or ends with underscore";
		} else {
		    String domain = email.substring(email.indexOf("@") + 1);
			if (domain.contains("+")) { //domain contains plus?
				return "Domain contains plus";
			} else if (domain.contains("_")){ //domain contains underscore?
			    return "Domain contains underscore";
			} else {
				return "Valid"; //if all passed
			}
		}
	}

	//exception C - removes dots from local
	public static String exceptionC(String email) {
	    String local = email.substring(0, email.indexOf("@"));
	    String domain = email.substring(email.indexOf("@") + 1);
		local = local.replace(".", "");
		return local + "@" + domain;
	}
	
	//result method
	public static String result(String reason, String email){
	    //if valid
	    if (reason.equals("Valid")) {
		String local = email.substring(0, email.indexOf("@"));
	    String domain = email.substring(email.indexOf("@") + 1);
		System.out.print(email + ": Valid ");
		if (domain.equals("gmail.com")) {
			System.out.print("(Gmail normalized) ");
		}
		return "| Local: " + local + " | Domain: " + domain;
		//if invalid
		} else {
		    return email + ": Invalid: " + reason;
		}
	}
}