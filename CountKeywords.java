import java.io.File;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

public class CountKeywords {
  public static void main(String[] args) throws Exception {
    Scanner input = new Scanner(System.in);
    System.out.print("Enter a Java source file: ");
    String filename = input.nextLine();

    File file = new File(filename);
    if (file.exists()) {
      System.out.println("The number of keywords in " + filename
          + " is " + countKeywords(file));
    } else {
      System.out.println("File " + filename + " does not exist");
    }
  }

  public static int countKeywords(File file) throws Exception {
    // Array of all Java keywords + true, false, and null
    String[] keywordString = {"abstract", "assert", "boolean",
        "break", "byte", "case", "catch", "char", "class", "const",
        "continue", "default", "do", "double", "else", "enum",
        "extends", "for", "final", "finally", "float", "goto",
        "if", "implements", "import", "instanceof", "int",
        "interface", "long", "native", "new", "package", "private",
        "protected", "public", "return", "short", "static",
        "strictfp", "super", "switch", "synchronized", "this",
        "throw", "throws", "transient", "try", "void", "volatile",
        "while", "true", "false", "null"};

    Set<String> keywordSet =
      new HashSet<>(Arrays.asList(keywordString));
    int count = 0;

    Scanner input = new Scanner(file);

    boolean isInComment = false;

    while (input.hasNext()) {
      String word = input.next();

      // Check for comments and update isInComment flag
      if (word.startsWith("//")) {
        break; // Ignore the rest of the line
      } else if (word.startsWith("/*")) {
        isInComment = true;
      }

      // Check for the end of a multiline comment
      if (isInComment && word.endsWith("*/")) {
        isInComment = false;
        continue;
      }

      // Ignore words within comments or strings
      if (isInComment || word.startsWith("\"") || word.startsWith("'")) {
        continue;
      }

      // Remove punctuation from the word
      word = word.replaceAll("[^a-zA-Z0-9]", "");

      if (keywordSet.contains(word))
        count++;
    }

    return count;
  }
}
