[![Open in Codespaces](https://classroom.github.com/assets/launch-codespace-2972f46106e565e64193e422d61a12cf1da4916b45550586e14ef0a7c637dd04.svg)](https://classroom.github.com/open-in-codespaces?assignment_repo_id=23315005)
# Problem Set Unit 3: Email Validator and Parser

In this problem set, you will create a program that validates email addresses according to specific rules, extracts components, and formats them according to different domain requirements.

---

## Option 1: Basic Email Validation (80% Completion)

Write a program that:

1. Takes one input:
   - An email address (a string)

2. Outputs whether the email is valid or invalid based on these basic rules (in this exact order):
   - Email must contain exactly one @ symbol
   - Email must not start or end with a dot (.)
   - Email must not contain spaces
   - The local part (before @) must be between 1-64 characters
   - The domain part (after @) must contain at least one dot
   - The domain extension (after final dot) must be 2-6 characters

**Example:**
```
Input an email: john.doe@example.com
Valid
```

**Invalid Examples:**
```
Input an email: john.doe@example
Invalid

Input an email: @example.com
Invalid
```

**Requirements:**
- Check rules in the order specified above
- Stop checking after the first invalid rule is found
- Output only "Valid" or "Invalid"
- Ensure the code is well-structured with appropriate variable names, spacing, comments, and documentation
- Avoid unnecessary code repetition for efficiency

---

## Option 2: Domain-Specific Rules and Exception Handling (90% Completion)

Enhance the program to handle exceptions and domain-specific validation rules:

1. Keep all validation from Option 1, but now output which rule failed:
   - If invalid, output: "Invalid: [reason]"
   - Possible reasons: "Missing @", "Multiple @", "Starts or ends with dot", "Contains spaces", "Local part too long", "Local part too short", "No dot in domain", "Invalid domain extension length"
     
2. Add these exceptions to the basic rules:
   - **Exception A**: Subdomains are allowed (e.g., mail.google.com is valid)
   - **Exception B**: The characters + and _ are allowed in the local part only (follows same rules at .)
   - **Exception C**: Gmail addresses may have dots removed automatically (treat john.doe@gmail.com and johndoe@gmail.com as equivalent for validation purposes)

3. Handle edge cases:
   - If Exception C applies, output the normalized version: "Valid (Gmail normalized)"
   - If multiple exceptions apply, still output "Valid"
   - Validate that exceptions don't allow invalid characters in restricted areas

**Examples:**
```
Input an email: user+tag@mail.example.co.uk
Valid

Input an email: john_doe@sub.domain.com
Valid

Input an email: jane.doe@gmail.com
Valid (Gmail normalized)

Input an email: invalid@domain.5yz
Invalid: Domain extension contains non-letters
```

**Requirements:**
- Validate in the order specified in Option 1
- Check for exceptions AFTER determining if the email would be invalid under basic rules
- Apply exceptions only where they are applicable
- Maintain clear, efficient code structure

---

## Option 3: Multiple Email Input with Formatting (100% Completion)

Improve your code from the previous options to:

1. Take a single input containing two email addresses separated by a comma and space
2. Process each email individually using all validation rules from Option 2
3. Output the results for each email on separate lines
4. Extract and display the local part and domain separately for each valid email

**Example:**
```
Input two emails: john.doe@example.com, invalid@domain.5yz
john.doe@example.com: Valid | Local: john.doe | Domain: example.com
invalid@domain.5yz: Invalid: Domain extension contains non-letters
```

**Additional Example:**
```
Input two emails: user+tag@mail.example.co.uk, jane.doe@gmail.com
user+tag@mail.example.co.uk: Valid | Local: user+tag | Domain: mail.example.co.uk
jane.doe@gmail.com: Valid (Gmail normalized) | Local: jane.doe | Domain: gmail.com
```

**Requirements:**
- Parse exactly two emails from a single input (separated by ", " or comma)
- Maintain all validation from Option 2 for each email
- Extract local part and domain for valid emails
- Modify your existing code rather than starting from scratch

---
